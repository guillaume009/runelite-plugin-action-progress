package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.data.Magic;
import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.github.calebwhiting.runelite.plugins.actionprogress.ActionProgressConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.runelite.api.Client;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.MenuAction;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.widgets.Widget;
import net.runelite.client.eventbus.Subscribe;

@Singleton
public class PlankMakeSpellDetector extends ActionDetector
{

	@Inject private ActionProgressConfig config;

	@Inject private Client client;

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked evt)
	{
		if (!this.config.magicPlankMake()) {
			return;
		}
		if (evt.getMenuAction() != MenuAction.WIDGET_TARGET_ON_WIDGET) {
			return;
		}
		ItemContainer inventory = this.client.getItemContainer(InventoryID.INV);
		if (inventory == null) {
			return;
		}

		// There is not a space in the middle of Plank Make, it is a '\u00A0' afaik
		// We are matching "Plank[standard ASCII plus the whitespace codepoints]Make"
		if(!evt.getMenuTarget().matches(".*Plank\\p{Z}Make.*")){
			return;
		}
		for (Magic.PlankMakeSpell plankMakeSpell : Magic.PlankMakeSpell.values()) {
			Magic.Spell spell = plankMakeSpell.getSpell();
			Widget widget = this.client.getWidget(spell.getWidgetId());
			if (widget == null || widget.getBorderType() != 0) {
				continue;
			}
			int itemId = evt.getItemId();
			if (plankMakeSpell.getPlank() != itemId || !inventory.contains(ItemID.COINS)) {
				continue;
			}

			int coinsQuantity = 0;
			for (Item item : inventory.getItems()) {
				if(item.getId() == ItemID.COINS){
					coinsQuantity = item.getQuantity();
				}
			}

			int amount = Math.min(inventory.count(itemId), Math.min(spell.getAvailableCasts(this.client), coinsQuantity / plankMakeSpell.getCost()));
			this.actionManager.setAction(Action.MAGIC_PLANK_MAKE, amount, itemId);
			break;
		}
	}

}
