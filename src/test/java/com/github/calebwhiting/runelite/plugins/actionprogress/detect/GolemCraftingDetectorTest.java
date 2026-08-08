package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.api.event.LocalAnimationChanged;
import com.github.calebwhiting.runelite.plugins.actionprogress.Action;
import com.github.calebwhiting.runelite.plugins.actionprogress.ActionManager;
import net.runelite.api.MenuEntry;
import net.runelite.api.Player;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.gameval.AnimationID;
import net.runelite.api.gameval.ItemID;
import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GolemCraftingDetectorTest
{
	@Test
	public void golemShapingUsesTwentyTicks()
	{
		assertArrayEquals(new int[]{20}, Action.GOLEM_SHAPE.getTickTimes());
	}

	@Test
	public void shapeGolemClickStartsShapingAction()
	{
		FakeActionManager actionManager = new FakeActionManager();
		GolemCraftingDetector detector = detector(actionManager);

		detector.onMenuOptionClicked(menuClick("Shape-golem"));
		detector.onLocalAnimationChanged(animationChanged(AnimationID.HUMAN_GOLEM_CHISEL));

		assertEquals(Action.GOLEM_SHAPE, actionManager.startedAction);
		assertEquals(1, actionManager.startedCount);
		assertEquals(ItemID.SUNSTONE_CORE, actionManager.startedItemId);
	}

	@Test
	public void insertingCoreDoesNotStartShapingAction()
	{
		FakeActionManager actionManager = new FakeActionManager();
		GolemCraftingDetector detector = detector(actionManager);

		detector.onMenuOptionClicked(menuClick("Insert-core"));
		detector.onLocalAnimationChanged(animationChanged(AnimationID.HUMAN_GOLEM_CHISEL));

		assertNull(actionManager.startedAction);
	}

	@Test
	public void laterNonShapingClickClearsPendingShapingInteraction()
	{
		FakeActionManager actionManager = new FakeActionManager();
		GolemCraftingDetector detector = detector(actionManager);

		detector.onMenuOptionClicked(menuClick("Shape-golem"));
		detector.onMenuOptionClicked(menuClick("Insert-core"));
		detector.onLocalAnimationChanged(animationChanged(AnimationID.HUMAN_GOLEM_CHISEL));

		assertNull(actionManager.startedAction);
	}

	private static GolemCraftingDetector detector(FakeActionManager actionManager)
	{
		GolemCraftingDetector detector = new GolemCraftingDetector();
		detector.actionManager = actionManager;
		return detector;
	}

	private static MenuOptionClicked menuClick(String option)
	{
		MenuEntry entry = (MenuEntry) Proxy.newProxyInstance(
				MenuEntry.class.getClassLoader(),
				new Class[]{MenuEntry.class},
				(proxy, method, args) -> method.getName().equals("getOption") ? option : defaultValue(method.getReturnType())
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
		if (type == byte.class) {
			return (byte) 0;
		}
		if (type == short.class) {
			return (short) 0;
		}
		if (type == int.class) {
			return 0;
		}
		if (type == long.class) {
			return 0L;
		}
		if (type == float.class) {
			return 0F;
		}
		if (type == double.class) {
			return 0D;
		}
		if (type == char.class) {
			return '\0';
		}
		throw new IllegalArgumentException("Unsupported primitive: " + type);
	}

	private static class FakeActionManager extends ActionManager
	{
		private Action startedAction;
		private int startedCount;
		private int startedItemId;

		@Override
		public Action getCurrentAction()
		{
			return null;
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
