package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.api.event.LocalAnimationChanged;
import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.google.inject.Singleton;
import net.runelite.api.Player;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.gameval.AnimationID;
import net.runelite.api.gameval.ItemID;
import net.runelite.client.eventbus.Subscribe;

/**
 * Detects the Shape-golem action at Wyrmscraig's golem crafting activity: carving one side of an
 * unfinished golem takes a fixed 20 ticks, done up to 4 times (one per side) to complete it.
 */
@Singleton
public class GolemCraftingDetector extends ActionDetector
{
	private static final String SHAPE_GOLEM_OPTION = "Shape-golem";

	private boolean shapeGolemClicked;

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked evt)
	{
		// Inserting a Sunstone core uses the same chisel animation as shaping. Remember the most recent
		// interaction so that only an explicit Shape-golem click can start the longer shaping timer.
		this.shapeGolemClicked = SHAPE_GOLEM_OPTION.equalsIgnoreCase(evt.getMenuOption());
	}

	@Subscribe
	public void onLocalAnimationChanged(LocalAnimationChanged evt)
	{
		Player me = evt.getLocalPlayer();
		if (me.getAnimation() != AnimationID.HUMAN_GOLEM_CHISEL || !this.shapeGolemClicked) {
			return;
		}
		this.shapeGolemClicked = false;
		if (this.actionManager.getCurrentAction() == Action.GOLEM_SHAPE) {
			return;
		}
		this.actionManager.setAction(Action.GOLEM_SHAPE, 1, ItemID.SUNSTONE_CORE);
	}

}
