package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.api.event.LocalAnimationChanged;
import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.runelite.api.Client;
import net.runelite.api.ItemContainer;
import net.runelite.api.Player;
import net.runelite.api.gameval.AnimationID;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;
import net.runelite.client.eventbus.Subscribe;

@Singleton
public class SandpitDetector extends ActionDetector
{

	@Inject private Client client;

	@Subscribe
	public void onLocalAnimationChanged(LocalAnimationChanged evt)
	{
		Player me = evt.getLocalPlayer();
		if (me.getAnimation() != AnimationID.HUMAN_FILLBUCKET_SANDPIT) {
			return;
		}
		if (this.actionManager.getCurrentAction() == Action.COLLECT_SAND) {
			return;
		}
		ItemContainer inventory = this.client.getItemContainer(InventoryID.INV);
		if (inventory == null) {
			return;
		}
		int buckets = inventory.count(ItemID.BUCKET_EMPTY);
		this.actionManager.setAction(Action.COLLECT_SAND, buckets, ItemID.BUCKET_SAND);
	}

}
