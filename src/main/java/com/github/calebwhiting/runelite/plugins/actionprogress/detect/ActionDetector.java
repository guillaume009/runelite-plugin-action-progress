package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.github.calebwhiting.runelite.plugins.actionprogress.ActionManager;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.game.ItemManager;
import java.util.HashMap;

@Slf4j
public class ActionDetector
{

	protected final HashMap<Integer, Action> itemActions = new HashMap<Integer, Action>();

	@Inject protected ItemManager itemManager;

	@Inject protected ActionManager actionManager;

	protected void registerAction(Action action, int... itemIds)
	{
		for (int id : itemIds) {
			this.itemActions.put(id, action);
			log.debug("Registered action {} for item: {}", action, id);
		}
	}

	protected void setActionByItemId(int itemId, int amount)
	{
		log.debug("looking for action by item id: {}", itemId);
		Action action = this.itemActions.get(itemId);
		if (action == null) {
			this.unhandled(itemId);
		} else {
			this.actionManager.setAction(action, amount, itemId);
		}
	}

	protected void unhandled(int itemId)
	{
		log.error("Unhandled product: {}", itemId);
	}

	public void setup()
	{
	}

	public void shutDown()
	{
	}

}