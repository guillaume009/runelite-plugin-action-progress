package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.api.event.LocalAnimationChanged;
import com.github.calebwhiting.runelite.data.Magic;
import com.github.calebwhiting.runelite.data.Sailing;
import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.runelite.api.gameval.AnimationID;
import net.runelite.api.Player;
import net.runelite.api.Client;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.ItemContainer;
import net.runelite.client.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class SalvageDetector extends ActionDetector
{

	@Inject private Client client;
	private static final Logger log = LoggerFactory.getLogger(SalvageDetector.class);
	@Subscribe
	public void onLocalAnimationChanged(LocalAnimationChanged evt)
	{
		Player me = evt.getLocalPlayer();
		if (me.getAnimation() != AnimationID.SAILING_HUMAN_SALVAGE_HOOK_KANDARIN_1X3_INTERACT01) {
			return;
		}
		if (this.actionManager.getCurrentAction() == Action.SAILING_SALVAGE) {
			return;
		}
		ItemContainer inventory = this.client.getItemContainer(InventoryID.INV);
		if (inventory == null) {
			return;
		}

		int salvage = 0;
		int itemId = 0;
		for (int salvageId : Sailing.SALVAGE) {
			int sCount = inventory.count(salvageId);
			if (sCount > 0){
				salvage += sCount;
				itemId = salvageId;
			}
		}
		if (salvage <= 0) {
			return;
		}
		this.actionManager.setAction(Action.SAILING_SALVAGE, salvage, itemId);
	}

}
