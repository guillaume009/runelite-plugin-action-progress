package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.data.Crafting;
import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.events.VarClientIntChanged;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.widgets.Widget;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;

import java.awt.event.KeyEvent;
import java.util.Arrays;

/**
 * Detects actions initiated from the furnace casting interface (Gold/Silver products)
 */

@Slf4j
public class FurnaceCastingDetector extends ActionDetector implements KeyListener
{


	@Inject private KeyManager keyManager;

	/**
	 * Widgets that contain item buttons in the silver casting interface.
	 */
	private static final int[] FURNACE_SILVER_PARENTS;

	/**
	 * Widgets that contain item buttons in the gold casting interface.
	 */
	private static final int[] FURNACE_GOLD_PARENTS;

	/**
	 * Indicates how many items are to be created in the crafting dialogue.
	 */
	private static final int VAR_FURNACE_MAKE_AMOUNT = 2224;

	/**
	 * The interface ID for the gold/silver casting interface.
	 */
	private static final int VARCINT_GOLD_SILVER_SMELTING_INTERFACE = 990;

	/**
	 * The varbit that indicates the selected item in the gold/silver casting interface.
	 */
	private static final int VARBIT_SELECTED_GOLD_ITEM =  13892;
	private static final int VARBIT_SELECTED_SILVER_ITEM =  13893;


	/**
	 * The widget ID for the gold/silver casting interface. might be needed or not.
	 * FIXME: check if this is needed.
	 */
	private static final int WIDGET_ID_GOLD_CASTING = 29229056;
	private static final int WIDGET_ID_SILVER_CASTING = 393220;


	/**
	 * These are to keep track of the selected item in the gold/silver casting interface.
	 */
	private int selectedGoldItem = 0;
	private int selectedSilverItem = 0;

	/**
	 * Indicates whether the user has the gold/silver casting interface open.VARCLIENTINT 990
	 */
	private boolean waitingForSmithingSelection = false;


	static {
		FURNACE_SILVER_PARENTS = new int[]{393222, 393226, 393230, 393234, 393238};
		Arrays.sort(FURNACE_SILVER_PARENTS);
		FURNACE_GOLD_PARENTS = new int[]{29229059, 29229062, 29229073, 29229076, 29229077, 29229086, 29229091, 29229103, 29229107};
		Arrays.sort(FURNACE_GOLD_PARENTS);
	}

	@Inject private Client client;

	@Override
	public void setup()
	{
		this.registerAction(Action.CRAFT_CAST_GOLD_AND_SILVER, Crafting.SILVER_AND_GOLD_ITEMS);
		keyManager.registerKeyListener(this);
	}

	@Override
	public void shutDown()
	{
		keyManager.unregisterKeyListener(this);
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged evt)
	{
		if (evt.getVarbitId() == VARBIT_SELECTED_GOLD_ITEM) {
			selectedGoldItem = client.getVarbitValue(VARBIT_SELECTED_GOLD_ITEM);
			log.debug("selectedGoldItem: {}", selectedGoldItem);
		} else if (evt.getVarbitId() == VARBIT_SELECTED_SILVER_ITEM) {
			selectedSilverItem = client.getVarbitValue(VARBIT_SELECTED_SILVER_ITEM);
			log.debug("selectedSilverItem: {}", selectedSilverItem);
		}
	}

	@Subscribe
	public void onVarClientIntChanged(VarClientIntChanged evt)
	{
		// If the user opens the gold/silver casting interface, we need to know when they close it.
		// this should be fine
		if (evt.getIndex() == VARCINT_GOLD_SILVER_SMELTING_INTERFACE) {
			waitingForSmithingSelection = !waitingForSmithingSelection;
			log.debug("toggled waitingForSmithingSelection to {}", waitingForSmithingSelection);
		}
	}

	@Override
	public void keyTyped(KeyEvent event)
	{

	}

	@Override
	public void keyReleased(KeyEvent event)
	{

	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		if (waitingForSmithingSelection && event.getKeyCode() == KeyEvent.VK_SPACE) {

			// FIXME: this needs to be worked on.
			// needs to get correct widget and to make ure it is not null.
			Widget widget = client.getWidget(WIDGET_ID_GOLD_CASTING);


			if (widget == null) {
				return;
			}

			int actionCount = this.client.getVarpValue(VAR_FURNACE_MAKE_AMOUNT);

			// FIXME: make sure this works. copied from onMenuOptionClicked
			if (Arrays.binarySearch(FURNACE_SILVER_PARENTS, widget.getParentId()) >= 0) {
				Widget itemContainer = widget.getChild(0);
				if (itemContainer != null) {
					int product = itemContainer.getItemId();
					this.setActionByItemId(product, actionCount);
				}
			} else if (Arrays.binarySearch(FURNACE_GOLD_PARENTS, widget.getParentId()) >= 0) {
				int product = widget.getItemId();
				this.setActionByItemId(product, actionCount);
			}

		}
	}

	@Subscribe
	@Singleton
	public void onMenuOptionClicked(net.runelite.api.events.MenuOptionClicked evt)
	{
		if (evt.getParam1() <= 0 || evt.getMenuAction() != MenuAction.CC_OP) {
			return;
		}
		Widget widget = this.client.getWidget(evt.getParam1());
		if (widget == null) {
			return;
		}
		int actionCount = this.client.getVarpValue(VAR_FURNACE_MAKE_AMOUNT);
		if (Arrays.binarySearch(FURNACE_SILVER_PARENTS, widget.getParentId()) >= 0) {
			Widget itemContainer = widget.getChild(0);
			if (itemContainer != null) {
				int product = itemContainer.getItemId();
				this.setActionByItemId(product, actionCount);
			}
		} else if (Arrays.binarySearch(FURNACE_GOLD_PARENTS, widget.getParentId()) >= 0) {
			int product = widget.getItemId();
			this.setActionByItemId(product, actionCount);
		}
	}

}
