package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.ItemID;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.events.ScriptPreFired;
import net.runelite.api.events.VarClientIntChanged;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;

import java.awt.event.KeyEvent;
import java.util.HashMap;

@Singleton
@Slf4j
public class SmithingDetector extends ActionDetector implements KeyListener
{

	@Inject private Client client;

	@Inject private ClientThread clientThread;
	
	@Inject private KeyManager keyManager;

	private static final int VAR_AVAILABLE_MATERIALS = 2224;

	private static final int VAR_SMITHING_INTERFACE = 989;

	private static final int VAR_SELECTED_SMITHING_INDEX = 13895;

	private static final int SCRIPT_SMITHING_INIT = 431;

	private static final int ENUM_SMITHING_WIDGET_INDEX = 1101;

	private static final int ENUM_SMITHING_ITEM_BAR = 845;

	private int smithingItemid;

	private int numberOfBarsForSelectedItem;

	private boolean waitingForSmithingSelection = false;

	private boolean pendingSmithingClickStart = false;

	private HashMap<Integer, Integer> indexToItemId = new HashMap<Integer,Integer>();

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
		if (waitingForSmithingSelection && event.getKeyCode() == KeyEvent.VK_SPACE)
		{
			clientThread.invokeLater(() -> {
				int availableBars = this.client.getVarpValue(VAR_AVAILABLE_MATERIALS);
				if(numberOfBarsForSelectedItem == 0)
				{
					return;
				}

				if(isWearingSmithOutfit()){
					this.actionManager.setAction(Action.SMITHING_WITH_SMITH_OUTFIT, (availableBars / numberOfBarsForSelectedItem), smithingItemid);
				}
				else {
					this.actionManager.setAction(Action.SMITHING, (availableBars / numberOfBarsForSelectedItem), smithingItemid);
				}
			});
		}
	}

	@Override
	public void setup()
	{
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
		if (evt.getVarbitId() == VAR_SELECTED_SMITHING_INDEX) {			
			int index = evt.getValue();
			if(indexToItemId.containsKey(index)){
				smithingItemid = indexToItemId.get(index);
				numberOfBarsForSelectedItem = client.getEnum(ENUM_SMITHING_ITEM_BAR).getIntValue(smithingItemid);
			}			
		}
	}

	@Subscribe
	public void onVarClientIntChanged(VarClientIntChanged varClientIntChanged)
	{
		if (varClientIntChanged.getIndex() == VAR_SMITHING_INTERFACE)
		{
			boolean wasWaitingForSmithingSelection = waitingForSmithingSelection;
			waitingForSmithingSelection = !waitingForSmithingSelection;

			if (!waitingForSmithingSelection) {
				if (wasWaitingForSmithingSelection && pendingSmithingClickStart) {
					pendingSmithingClickStart = false;
					clientThread.invokeLater(() -> {
						if (smithingItemid <= 0 || numberOfBarsForSelectedItem <= 0) {
							return;
						}

						startSmithingAction(numberOfBarsForSelectedItem, smithingItemid);
					});
				}
			}
			else {
				pendingSmithingClickStart = false;
			}
		}
	}

	@Subscribe
	public void onScriptPreFired(ScriptPreFired evt)
	{
		if (evt.getScriptId() == SCRIPT_SMITHING_INIT){
			final int[] intStack = client.getIntStack();
			final int intStackSize = client.getIntStackSize();
			final int widgetId = intStack[intStackSize - 4];			
			final int itemId = intStack[intStackSize - 3];

			int index = client.getEnum(ENUM_SMITHING_WIDGET_INDEX).getIntValue(widgetId);
			indexToItemId.put(index, itemId);

			int var_index = this.client.getVarbitValue(VAR_SELECTED_SMITHING_INDEX);

			try {
				smithingItemid = indexToItemId.get(var_index);
				numberOfBarsForSelectedItem = client.getEnum(ENUM_SMITHING_ITEM_BAR).getIntValue(smithingItemid);
			}catch (NullPointerException e){
				log.debug("Item not found in indexToItemId map");
			}

		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked evt)
	{
		if (!waitingForSmithingSelection || !isSmithingProductClick(evt)) {
			return;
		}

		pendingSmithingClickStart = true;
	}

	private void startSmithingAction(int barsPerItem, int productId)
	{
		if (barsPerItem <= 0) {
			return;
		}

		int availableBars = this.client.getVarpValue(VAR_AVAILABLE_MATERIALS);
		if(isWearingSmithOutfit()){
			this.actionManager.setAction(Action.SMITHING_WITH_SMITH_OUTFIT, (availableBars / barsPerItem), productId);
		}
		else {
			this.actionManager.setAction(Action.SMITHING, (availableBars / barsPerItem), productId);
		}
	}

	private boolean isSmithingProductClick(MenuOptionClicked evt)
	{
		Widget current = evt.getWidget();
		if (current == null && evt.getParam1() != -1) {
			current = this.client.getWidget(evt.getParam1());
		}

		while (current != null) {
			if (current.getParentId() == ComponentID.SMITHING_INVENTORY_ITEM_CONTAINER) {
				return true;
			}
			if ((current.getId() >>> 16) == InterfaceID.SMITHING) {
				return true;
			}

			int parentId = current.getParentId();
			if (parentId == -1 || parentId == current.getId()) {
				break;
			}

			Widget parent = current.getParent();
			current = parent != null ? parent : client.getWidget(parentId);
		}

		return evt.getParam1() != -1 && (evt.getParam1() >>> 16) == InterfaceID.SMITHING;
	}

	private boolean isWearingSmithOutfit(){
		ItemContainer gear = this.client.getItemContainer(InventoryID.EQUIPMENT);
		if (gear == null) {
			return false;
		}
        return gear.contains(ItemID.SMITHS_TUNIC) &&
                gear.contains(ItemID.SMITHS_TROUSERS) &&
                gear.contains(ItemID.SMITHS_BOOTS) &&
                (gear.contains(ItemID.SMITHS_GLOVES) || gear.contains(ItemID.SMITHS_GLOVES_I));
    }

}
