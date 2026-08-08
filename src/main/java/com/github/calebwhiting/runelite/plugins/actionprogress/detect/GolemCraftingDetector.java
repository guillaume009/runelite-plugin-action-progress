package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.api.event.LocalAnimationChanged;
import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.google.inject.Singleton;
import net.runelite.api.Player;
import net.runelite.api.gameval.AnimationID;
import net.runelite.api.gameval.ItemID;
import net.runelite.client.eventbus.Subscribe;

/**
 * Detects the Shape-golem action at Wyrmscraig's golem crafting activity: carving one side of an
 * unfinished golem takes a fixed 15 ticks, done up to 4 times (one per side) to complete it.
 */
@Singleton
public class GolemCraftingDetector extends ActionDetector
{

	@Subscribe
	public void onLocalAnimationChanged(LocalAnimationChanged evt)
	{
		Player me = evt.getLocalPlayer();
		if (me.getAnimation() != AnimationID.HUMAN_GOLEM_CHISEL) {
			return;
		}
		if (this.actionManager.getCurrentAction() == Action.GOLEM_SHAPE) {
			return;
		}
		this.actionManager.setAction(Action.GOLEM_SHAPE, 1, ItemID.SUNSTONE_CORE);
	}

}
