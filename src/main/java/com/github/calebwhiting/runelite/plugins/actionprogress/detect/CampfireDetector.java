package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.api.InventoryManager;
import com.github.calebwhiting.runelite.api.event.LocalAnimationChanged;
import com.github.calebwhiting.runelite.data.Woodcutting;
import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.runelite.api.Item;
import net.runelite.api.ObjectID;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.gameval.AnimationID;
import net.runelite.client.eventbus.Subscribe;

import java.util.Arrays;

/**
 * Detects the direct Forester's Campfire flow used when the inventory contains only one log type.
 * The multi-log flow still opens Make-X and is handled by {@link ChatboxDetector}.
 */
@Singleton
public class CampfireDetector extends ActionDetector
{
	private static final String TEND_TO_OPTION = "Tend-to";

	private static final int[] CAMPFIRE_ANIMATIONS = {
			AnimationID.FORESTRY_CAMPFIRE_BURNING_GENERIC,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_ACHEY_TREE_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_ARCTIC_PINE_LOG,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_BLISTERWOOD_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_MAGIC_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_MAHOGANY_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_MAPLE_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_OAK_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_REDWOOD_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_TEAK_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_WILLOW_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_YEW_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_JATOBA_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_CAMPHOR_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_IRONWOOD_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_ROSEWOOD_LOGS,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_GENERIC_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_ACHEY_TREE_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_ARCTIC_PINE_LOG_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_BLISTERWOOD_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_MAGIC_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_MAHOGANY_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_MAPLE_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_OAK_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_REDWOOD_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_TEAK_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_WILLOW_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_YEW_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_JATOBA_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_CAMPHOR_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_IRONWOOD_LOGS_NOLOOP,
			AnimationID.FORESTRY_CAMPFIRE_BURNING_ROSEWOOD_LOGS_NOLOOP
	};

	@Inject InventoryManager inventoryManager;

	private int pendingLogId = -1;
	private int pendingLogCount;

	@Override
	public void setup()
	{
		this.clearPendingAction();
	}

	@Override
	public void shutDown()
	{
		this.clearPendingAction();
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked evt)
	{
		this.clearPendingAction();
		if (!TEND_TO_OPTION.equalsIgnoreCase(evt.getMenuOption()) ||
			!isForestersCampfire(evt.getId())) {
			return;
		}

		for (int logId : Woodcutting.CAMPFIRE_LOGS) {
			int count = this.inventoryManager.getItems()
					.filter(item -> item.getId() == logId)
					.mapToInt(Item::getQuantity)
					.sum();
			if (count == 0) {
				continue;
			}
			if (this.pendingLogId != -1) {
				this.clearPendingAction();
				return;
			}
			this.pendingLogId = logId;
			this.pendingLogCount = count;
		}
	}

	@Subscribe
	public void onLocalAnimationChanged(LocalAnimationChanged evt)
	{
		if (this.pendingLogId == -1 || !isCampfireAnimation(evt.getLocalPlayer().getAnimation())) {
			return;
		}

		int logId = this.pendingLogId;
		int logCount = this.pendingLogCount;
		this.clearPendingAction();
		if (this.actionManager.getCurrentAction() != Action.FIREMAKING_CAMPFIRE) {
			this.actionManager.setAction(Action.FIREMAKING_CAMPFIRE, logCount, logId);
		}
	}

	private void clearPendingAction()
	{
		this.pendingLogId = -1;
		this.pendingLogCount = 0;
	}

	private static boolean isCampfireAnimation(int animationId)
	{
		return Arrays.stream(CAMPFIRE_ANIMATIONS).anyMatch(id -> id == animationId);
	}

	private static boolean isForestersCampfire(int objectId)
	{
		return objectId == ObjectID.FORESTERS_CAMPFIRE ||
				objectId == ObjectID.FORESTERS_CAMPFIRE_49928 ||
				objectId == ObjectID.FORESTERS_CAMPFIRE_49929 ||
				objectId == ObjectID.FORESTERS_CAMPFIRE_49930 ||
				objectId == ObjectID.FORESTERS_CAMPFIRE_49931 ||
				objectId == ObjectID.FORESTERS_CAMPFIRE_49932;
	}
}
