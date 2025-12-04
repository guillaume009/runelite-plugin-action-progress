package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.data.Magic;
import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.github.calebwhiting.runelite.plugins.actionprogress.ActionProgressConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.runelite.api.Client;
import net.runelite.api.ItemContainer;
import net.runelite.api.MenuAction;
import net.runelite.api.Skill;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.eventbus.Subscribe;

@Singleton
public class BakePieSpellDetector extends ActionDetector
{

	@Inject private ActionProgressConfig config;

	@Inject private Client client;

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked evt)
	{
		if (!this.config.magicBakePie()) {
			return;
		}
		if (evt.getMenuAction() != MenuAction.CC_OP) {
			return;
		}
		ItemContainer inventory = this.client.getItemContainer(InventoryID.INV);
		if (inventory == null) {
			return;
		}
		int cookingLevel = this.client.getBoostedSkillLevel(Skill.COOKING);

		// There is not a space in the middle of Bake Pie, it is a '\u00A0' afaik
		// We are matching "Bake[standard ASCII plus the whitespace codepoints]Pie"
		if(!evt.getMenuTarget().matches(".*Bake\\p{Z}Pie.*")) {
			return;
		}
		int amount = 0;
		int itemId = 0;
		for (Magic.BakePieSpell bakePieSpell : Magic.BakePieSpell.values()) {
			Magic.Spell spell = bakePieSpell.getSpell();
			Widget widget = this.client.getWidget(spell.getWidgetId());
			if (widget != null && widget.getBorderType() == 0) {
				int curItemId = bakePieSpell.getPieId();
				// filter out pies that player does not have the level to cook
				if (bakePieSpell.getLevel() > cookingLevel) {
					continue;
				}
				if (inventory.count(curItemId) <= 0) {
					continue;
				}
				// We are storing the last itemID and appending amount because
				// Bake Pie will cook all pies in the inv that the user has the level for.
				itemId = curItemId;
				amount += Math.min(inventory.count(itemId), spell.getAvailableCasts(this.client));
			}
		}
		// Check if any item was found
		if (itemId > 0) {
			this.actionManager.setAction(Action.MAGIC_BAKE_PIE, amount, itemId);
		}
	}

}
