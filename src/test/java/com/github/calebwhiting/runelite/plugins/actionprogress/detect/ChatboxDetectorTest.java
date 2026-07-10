package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.api.InventoryManager;
import com.github.calebwhiting.runelite.data.Recipe;
import com.github.calebwhiting.runelite.plugins.actionprogress.Product;
import net.runelite.api.Item;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static com.github.calebwhiting.runelite.plugins.actionprogress.Action.FLETCH_ATTACH_TIPS;
import static net.runelite.api.ItemID.DIAMOND_BOLT_TIPS;
import static net.runelite.api.ItemID.DIAMOND_DRAGON_BOLTS;
import static net.runelite.api.ItemID.DRAGON_BOLTS;
import static net.runelite.api.ItemID.DRAGONSTONE_BOLT_TIPS;
import static net.runelite.api.ItemID.DRAGONSTONE_DRAGON_BOLTS;
import static net.runelite.api.ItemID.EMERALD_BOLT_TIPS;
import static net.runelite.api.ItemID.EMERALD_DRAGON_BOLTS;
import static net.runelite.api.ItemID.JADE_BOLT_TIPS;
import static net.runelite.api.ItemID.JADE_DRAGON_BOLTS;
import static net.runelite.api.ItemID.ONYX_BOLT_TIPS;
import static net.runelite.api.ItemID.ONYX_DRAGON_BOLTS;
import static net.runelite.api.ItemID.OPAL_BOLT_TIPS;
import static net.runelite.api.ItemID.OPAL_DRAGON_BOLTS;
import static net.runelite.api.ItemID.PEARL_BOLT_TIPS;
import static net.runelite.api.ItemID.PEARL_DRAGON_BOLTS;
import static net.runelite.api.ItemID.RUBY_BOLT_TIPS;
import static net.runelite.api.ItemID.RUBY_DRAGON_BOLTS;
import static net.runelite.api.ItemID.SAPPHIRE_BOLT_TIPS;
import static net.runelite.api.ItemID.SAPPHIRE_DRAGON_BOLTS;
import static net.runelite.api.ItemID.TOPAZ_BOLT_TIPS;
import static net.runelite.api.ItemID.TOPAZ_DRAGON_BOLTS;

public class ChatboxDetectorTest
{
	@Test
	public void dragonBoltRecipesUseMatchingTips() throws ReflectiveOperationException
	{
		Map<Integer, Integer> expectedTips = Map.of(
			DIAMOND_DRAGON_BOLTS, DIAMOND_BOLT_TIPS,
			DRAGONSTONE_DRAGON_BOLTS, DRAGONSTONE_BOLT_TIPS,
			EMERALD_DRAGON_BOLTS, EMERALD_BOLT_TIPS,
			JADE_DRAGON_BOLTS, JADE_BOLT_TIPS,
			ONYX_DRAGON_BOLTS, ONYX_BOLT_TIPS,
			OPAL_DRAGON_BOLTS, OPAL_BOLT_TIPS,
			PEARL_DRAGON_BOLTS, PEARL_BOLT_TIPS,
			RUBY_DRAGON_BOLTS, RUBY_BOLT_TIPS,
			SAPPHIRE_DRAGON_BOLTS, SAPPHIRE_BOLT_TIPS,
			TOPAZ_DRAGON_BOLTS, TOPAZ_BOLT_TIPS
		);

		Field recipesField = ChatboxDetector.class.getDeclaredField("MULTI_MATERIAL_PRODUCTS");
		recipesField.setAccessible(true);
		Product[] recipes = (Product[]) recipesField.get(null);

		for (Map.Entry<Integer, Integer> expectedTip : expectedTips.entrySet()) {
			Product recipe = findRecipe(recipes, expectedTip.getKey());
			Assert.assertNotNull("Missing recipe for dragon bolt product " + expectedTip.getKey(), recipe);
			Assert.assertEquals(expectedTip.getValue().intValue(), recipe.getRequirements()[1].getItemId());
		}
	}

	private Product findRecipe(Product[] recipes, int productId)
	{
		for (Product recipe : recipes) {
			if (recipe.getProductId() == productId) {
				return recipe;
			}
		}
		return null;
	}

	@Test
	public void rubyDragonBoltsAreDetectedWithoutDiamondTips() throws ReflectiveOperationException
	{
		InventoryManager inventory = new InventoryManager()
		{
			@Override
			public Stream<Item> getItems()
			{
				return Stream.of(new Item(DRAGON_BOLTS, 20), new Item(RUBY_BOLT_TIPS, 20));
			}
		};

		Field recipesField = ChatboxDetector.class.getDeclaredField("MULTI_MATERIAL_PRODUCTS");
		recipesField.setAccessible(true);
		Product[] recipes = (Product[]) recipesField.get(null);
		Product recipe = Recipe.forProduct(recipes, RUBY_DRAGON_BOLTS, inventory);

		Assert.assertNotNull(recipe);
		Assert.assertEquals(FLETCH_ATTACH_TIPS, recipe.getAction());
		Assert.assertEquals(2, recipe.getMakeProductCount(inventory));
	}
}
