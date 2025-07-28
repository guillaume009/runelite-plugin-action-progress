package com.github.calebwhiting.runelite.plugins.actionprogress;

import com.github.calebwhiting.runelite.api.InterruptManager;
import com.github.calebwhiting.runelite.api.TickManager;
import com.github.calebwhiting.runelite.api.event.Interrupt;
import com.github.calebwhiting.runelite.data.Fletching;
import com.github.calebwhiting.runelite.plugins.actionprogress.event.ActionStartedEvent;
import com.github.calebwhiting.runelite.plugins.actionprogress.event.ActionStoppedEvent;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.ItemID;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;

@Slf4j
@Singleton
public class ActionManager
{

	@Inject private EventBus eventBus;

	@Inject private Client client;

	@Inject private InterruptManager interruptManager;

	@Inject private TickManager tickManager;

	@Inject private ActionProgressConfig config;

	/**
	 * The tick which started the series of actions
	 */
	@Getter private int actionStartTick;

	/**
	 * The time when the series of actions was started
	 */
	@Getter private long actionStartMs;

	/**
	 * The tick when the current series of actions will be complete
	 */
	@Getter private int actionEndTick;

	/**
	 * The time when the series of actions will be complete
	 */
	@Getter private long actionEndMs;

	/**
	 * The amount of actions in the sequence of actions
	 */
	@Getter private int actionCount;

	@Getter private Action currentAction;

	@Getter @Setter private int currentProductId = -1;

	private int calculateActionTicks(Action action, int actionCount)
	{
		int nTicksElapsed = 0;
		int[] timings = getActionTickTimes(action);

		//Could be done more cleanly elsewhere, but it would require changing everything from int to double
		int realActionCount = action == Action.SMITHING_WITH_SMITH_OUTFIT ? (int) (actionCount * .8) : actionCount;
		for (int i = 0; i < realActionCount; i++) {
			nTicksElapsed += timings[i >= timings.length ? timings.length - 1 : i];
		}
		return nTicksElapsed;
	}

	public void setAction(Action action, int actionCount, int itemId)
	{
		if (!action.getEnabledFunction().apply(this.config)) {
			log.debug("action {} is disabled", action);
			return;
		}
		if (actionCount <= 1 && this.config.ignoreSingleActions()) {
			log.debug("ignoring single action");
			return;
		}
		if (actionCount == 0){
			log.debug("Nothing to do");
			return;
		}
		this.currentAction = action;
		this.currentProductId = itemId;
		this.actionStartTick = this.client.getTickCount();
		this.actionEndTick = this.actionStartTick + calculateActionTicks(action, actionCount);
		long duration = calculateActionTicks(action, actionCount) * TickManager.PERFECT_TICK_TIME;
		this.actionStartMs = System.currentTimeMillis();
		this.actionEndMs = this.actionStartMs + duration;
		this.actionCount = actionCount;
		this.interruptManager.setWaiting(true);
		log.debug("Started action: {} x {} ({} -> {})", this.actionCount, action.name(), this.actionStartTick,
				this.actionEndTick
		);
		this.eventBus.post(
				new ActionStartedEvent(action, itemId, actionCount, this.actionStartTick, this.actionEndTick));
	}

	private void resetAction()
	{
		log.debug("resetting action");
		if (this.currentAction != null) {
			this.eventBus.post(new ActionStoppedEvent(this.currentAction, this.currentProductId, this.actionCount,
					this.actionStartTick, this.actionEndTick,
					this.client.getTickCount() < this.actionEndTick
			));
		}
		this.currentAction = null;
		this.currentProductId = this.actionStartTick = this.actionEndTick = this.actionCount = -1;
		this.actionStartMs = this.actionEndMs = 0L;
	}

	@Subscribe(priority = -1)
	public void onInterrupt(Interrupt evt)
	{
		if (!evt.isConsumed()) {
			this.resetAction();
		}
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		this.actionEndMs = System.currentTimeMillis() + this.getApproximateCompletionTime();
		if (this.actionEndTick != -1 && this.client.getTickCount() >= this.actionEndTick) {
			log.debug("action end tick has passed");
			if (this.interruptManager.isWaiting()) {
				this.interruptManager.setWaiting(false);
			}
			this.resetAction();
		}
	}

	public int getCurrentActionProcessed()
	{
		if (this.currentAction == null) {
			return 0;
		}
		int actions = 0;
		int rem = this.client.getTickCount() - this.actionStartTick;
		int[] timings = getActionTickTimes(this.currentAction);
		for (int tickTime : timings) {
			rem -= tickTime;
			if (rem >= 0) {
				actions++;
			} else {
				rem = 0;
				break;
			}
		}
		return actions + (rem / timings[timings.length - 1]);
	}

	public float getTicksLeft()
	{
		int tick = this.client.getTickCount();
		float ticksLeft = ((float) this.actionEndTick - tick);
		if (ticksLeft <= 0) {
			return 0;
		}
		return ticksLeft;
	}

	public int[] getActionTickTimes(Action action) {
		if (!Fletching.FLETCHING_KNIFE_BOOSTED_ACTIONS.contains(action)) {
			return action.getTickTimes();
		}

		// Should maybe be more generic in the future to handle different "modifiers" to actions
		boolean hasFletchingKnife = hasFletchingKnifeInInventory() || isWearingFletchingKnife();
		int[] timings = action.getTickTimes().clone();
		if (hasFletchingKnife) {
			for (int i = 0; i < timings.length; i++) {
				timings[i] = Math.max(1, timings[i] - 1);
			}
		}

		return timings;
	}

	public long getApproximateCompletionTime()
	{
		float ticksLeft = getTicksLeft();
		long timeSinceTick = System.currentTimeMillis() - this.tickManager.getLastTickTime();
		return Math.round((ticksLeft * TickManager.PERFECT_TICK_TIME) - timeSinceTick);
	}

	private boolean hasFletchingKnifeInInventory() {
		ItemContainer inventory = this.client.getItemContainer(InventoryID.INVENTORY);
		if (inventory == null) {
			return false;
		}

		return inventory.contains(ItemID.FLETCHING_KNIFE);
	}

	private boolean isWearingFletchingKnife() {
		ItemContainer gear = this.client.getItemContainer(InventoryID.EQUIPMENT);
		if (gear == null) {
			return false;
		}

		return gear.contains(ItemID.FLETCHING_KNIFE);
	}

}