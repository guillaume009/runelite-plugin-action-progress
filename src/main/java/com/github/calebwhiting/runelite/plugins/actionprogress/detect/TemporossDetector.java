package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.api.InventoryManager;
import com.github.calebwhiting.runelite.api.event.LocalAnimationChanged;
import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Player;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.gameval.ObjectID;
import net.runelite.client.eventbus.Subscribe;

import java.util.Arrays;

@Slf4j
@Singleton
public class TemporossDetector extends ActionDetector
{

	private static final int TEMPOROSS_REGION = 12076;

	private boolean lastClickOnShrine = false;

	private static final int[] TEMPOROSS_AMMUNITION_CRATE = {
			NpcID.TEMPOROSS_NPC_CRATE_AMMUNITION_1, NpcID.TEMPOROSS_NPC_CRATE_AMMUNITION_2, NpcID.TEMPOROSS_NPC_CRATE_AMMUNITION_3,
			NpcID.TEMPOROSS_NPC_CRATE_AMMUNITION_4
	};

	private static final int[] TEMPOROSS_AMMUNITION = {
			ItemID.TEMPOROSS_RAW_HARPOONFISH, ItemID.TEMPOROSS_HARPOONFISH, ItemID.TEMPOROSS_CRYSTALLISED_HARPOONFISH
	};

	static {
		Arrays.sort(TEMPOROSS_AMMUNITION_CRATE);
		Arrays.sort(TEMPOROSS_AMMUNITION);
	}

	@Inject private Client client;

	@Inject private InventoryManager inventoryManager;

	@Subscribe
	public void onLocalAnimationChanged(LocalAnimationChanged evt)
	{
		Action action = this.actionManager.getCurrentAction();
		Player me = evt.getLocalPlayer();
		int region = WorldPoint.fromLocalInstance(this.client, me.getLocalLocation()).getRegionID();
		if (region != TEMPOROSS_REGION){
			return;
		}
		if (action == Action.TEMPOROSS_FILL_CRATE || action == Action.TEMPOROSS_COOKING) {
			log.debug("action is already {}", action);
			return;
		}
		Actor interacting = me.getInteracting();
		int id = interacting instanceof NPC ? ((NPC) interacting).getId() : -1;
		if (id != -1 && Arrays.binarySearch(TEMPOROSS_AMMUNITION_CRATE, id) >= 0) {
			log.debug("filling crate");
			int amount = this.inventoryManager.getItemCountById(TEMPOROSS_AMMUNITION);
			this.actionManager.setAction(Action.TEMPOROSS_FILL_CRATE, amount, -1);
		} else if(this.inventoryManager.getItemCountById(ItemID.TEMPOROSS_RAW_HARPOONFISH) > 0 && lastClickOnShrine){
			log.debug("cooking fish");
			int amount = this.inventoryManager.getItemCountById(ItemID.TEMPOROSS_RAW_HARPOONFISH);
			this.actionManager.setAction(Action.TEMPOROSS_COOKING, amount, ItemID.TEMPOROSS_HARPOONFISH);
		}
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		int objectId = event.getId();
		lastClickOnShrine = objectId == ObjectID.TEMPOROSS_SHRINE_FIRE;
	}

}
