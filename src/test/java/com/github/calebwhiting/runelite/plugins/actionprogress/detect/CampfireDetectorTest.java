package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.api.InventoryManager;
import com.github.calebwhiting.runelite.api.event.LocalAnimationChanged;
import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.github.calebwhiting.runelite.plugins.actionprogress.ActionManager;
import net.runelite.api.Item;
import net.runelite.api.MenuEntry;
import net.runelite.api.ObjectID;
import net.runelite.api.Player;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.gameval.AnimationID;
import net.runelite.api.gameval.ItemID;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CampfireDetectorTest
{
	@Test
	public void singleLogTypeStartsCampfireActionAfterAnimation()
	{
		FakeActionManager actionManager = new FakeActionManager();
		CampfireDetector detector = detector(actionManager, new Item(ItemID.OAK_LOGS, 14));

		detector.onMenuOptionClicked(menuClick("Tend-to", ObjectID.FORESTERS_CAMPFIRE));
		detector.onLocalAnimationChanged(animationChanged(AnimationID.FORESTRY_CAMPFIRE_BURNING_OAK_LOGS));

		assertEquals(Action.FIREMAKING_CAMPFIRE, actionManager.startedAction);
		assertEquals(14, actionManager.startedCount);
		assertEquals(ItemID.OAK_LOGS, actionManager.startedItemId);
	}

	@Test
	public void multipleLogTypesRemainHandledByMakeX()
	{
		FakeActionManager actionManager = new FakeActionManager();
		CampfireDetector detector = detector(actionManager,
				new Item(ItemID.OAK_LOGS, 7), new Item(ItemID.WILLOW_LOGS, 7));

		detector.onMenuOptionClicked(menuClick("Tend-to", ObjectID.FORESTERS_CAMPFIRE));
		detector.onLocalAnimationChanged(animationChanged(AnimationID.FORESTRY_CAMPFIRE_BURNING_OAK_LOGS));

		assertNull(actionManager.startedAction);
	}

	@Test
	public void campfireAnimationWithoutTendToClickDoesNotStartAction()
	{
		FakeActionManager actionManager = new FakeActionManager();
		CampfireDetector detector = detector(actionManager, new Item(ItemID.OAK_LOGS, 14));

		detector.onLocalAnimationChanged(animationChanged(AnimationID.FORESTRY_CAMPFIRE_BURNING_OAK_LOGS));

		assertNull(actionManager.startedAction);
	}

	@Test
	public void unrelatedObjectDoesNotStartAction()
	{
		FakeActionManager actionManager = new FakeActionManager();
		CampfireDetector detector = detector(actionManager, new Item(ItemID.OAK_LOGS, 14));

		detector.onMenuOptionClicked(menuClick("Tend-to", ObjectID.CAMPFIRE));
		detector.onLocalAnimationChanged(animationChanged(AnimationID.FORESTRY_CAMPFIRE_BURNING_OAK_LOGS));

		assertNull(actionManager.startedAction);
	}

	@Test
	public void existingMakeXActionIsNotRestarted()
	{
		FakeActionManager actionManager = new FakeActionManager();
		actionManager.currentAction = Action.FIREMAKING_CAMPFIRE;
		CampfireDetector detector = detector(actionManager, new Item(ItemID.OAK_LOGS, 14));

		detector.onMenuOptionClicked(menuClick("Tend-to", ObjectID.FORESTERS_CAMPFIRE));
		detector.onLocalAnimationChanged(animationChanged(AnimationID.FORESTRY_CAMPFIRE_BURNING_OAK_LOGS));

		assertNull(actionManager.startedAction);
	}

	private static CampfireDetector detector(FakeActionManager actionManager, Item... items)
	{
		CampfireDetector detector = new CampfireDetector();
		detector.actionManager = actionManager;
		detector.inventoryManager = new FakeInventoryManager(items);
		return detector;
	}

	private static MenuOptionClicked menuClick(String option, int identifier)
	{
		MenuEntry entry = (MenuEntry) Proxy.newProxyInstance(
				MenuEntry.class.getClassLoader(),
				new Class[]{MenuEntry.class},
				(proxy, method, args) -> {
					if (method.getName().equals("getOption")) {
						return option;
					}
					if (method.getName().equals("getIdentifier")) {
						return identifier;
					}
					return defaultValue(method.getReturnType());
				}
		);
		return new MenuOptionClicked(entry);
	}

	private static LocalAnimationChanged animationChanged(int animation)
	{
		Player player = (Player) Proxy.newProxyInstance(
				Player.class.getClassLoader(),
				new Class[]{Player.class},
				(proxy, method, args) -> method.getName().equals("getAnimation") ? animation : defaultValue(method.getReturnType())
		);
		return new LocalAnimationChanged(player);
	}

	private static Object defaultValue(Class<?> type)
	{
		if (!type.isPrimitive()) {
			return null;
		}
		if (type == boolean.class) {
			return false;
		}
		if (type == int.class) {
			return 0;
		}
		if (type == long.class) {
			return 0L;
		}
		if (type == double.class) {
			return 0D;
		}
		if (type == float.class) {
			return 0F;
		}
		if (type == short.class) {
			return (short) 0;
		}
		if (type == byte.class) {
			return (byte) 0;
		}
		if (type == char.class) {
			return '\0';
		}
		throw new IllegalArgumentException("Unsupported primitive: " + type);
	}

	private static class FakeInventoryManager extends InventoryManager
	{
		private final Item[] items;

		private FakeInventoryManager(Item... items)
		{
			this.items = items;
		}

		@Override
		public Stream<Item> getItems()
		{
			return Arrays.stream(this.items);
		}
	}

	private static class FakeActionManager extends ActionManager
	{
		private Action currentAction;
		private Action startedAction;
		private int startedCount;
		private int startedItemId;

		@Override
		public Action getCurrentAction()
		{
			return this.currentAction;
		}

		@Override
		public void setAction(Action action, int actionCount, int itemId)
		{
			this.startedAction = action;
			this.startedCount = actionCount;
			this.startedItemId = itemId;
		}
	}
}
