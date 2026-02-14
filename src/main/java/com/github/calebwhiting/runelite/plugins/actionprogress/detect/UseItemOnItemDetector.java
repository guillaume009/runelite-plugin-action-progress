package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.api.InventoryManager;
import com.github.calebwhiting.runelite.data.Ingredient;
import com.github.calebwhiting.runelite.data.Magic;
import com.github.calebwhiting.runelite.plugins.actionprogress.Product;
import com.google.inject.Inject;
import net.runelite.api.*;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.widgets.Widget;
import net.runelite.client.eventbus.Subscribe;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import static com.github.calebwhiting.runelite.plugins.actionprogress.Action.*;
import static net.runelite.api.ItemID.*;

public class UseItemOnItemDetector extends ActionDetector
{

	private static final Ingredient PESTLE_AND_MORTAR = new Ingredient(ItemID.PESTLE_AND_MORTAR, 1, false);
	private static final Ingredient CHISEL = new Ingredient(ItemID.CHISEL, 1, false);
	private static final Ingredient KNIFE = new Ingredient(ItemID.KNIFE, 1, false);
	private static final Ingredient FILLED_PLANT_POT = new Ingredient(ItemID.FILLED_PLANT_POT);
	private static final String BREAK_DOWN = "Break-down";

	private static final Product[] PRODUCTS = {
			new Product(GRIND, 				GROUND_ASHES, 					new Ingredient[]{ new Ingredient(ASHES)}, 														PESTLE_AND_MORTAR),
			new Product(GRIND, 				CRUSHED_NEST, 					new Ingredient[]{ new Ingredient(BIRD_NEST_5075)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				DRAGON_SCALE_DUST, 				new Ingredient[]{ new Ingredient(BLUE_DRAGON_SCALE)}, 											PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_CHARCOAL, 				new Ingredient[]{ new Ingredient(CHARCOAL)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				CHOCOLATE_DUST, 				new Ingredient[]{ new Ingredient(CHOCOLATE_BAR)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_GIANT_CRAB_MEAT, 		new Ingredient[]{ new Ingredient(GIANT_CRAB_MEAT)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				GOAT_HORN_DUST, 				new Ingredient[]{ new Ingredient(DESERT_GOAT_HORN)}, 											PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_THISTLE, 				new Ingredient[]{ new Ingredient(DRIED_THISTLE)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				GORAK_CLAW_POWDER, 				new Ingredient[]{ new Ingredient(GORAK_CLAWS)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_GUAM, 					new Ingredient[]{ new Ingredient(GUAM_LEAF)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				KEBBIT_TEETH_DUST, 				new Ingredient[]{ new Ingredient(KEBBIT_TEETH)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_KELP, 					new Ingredient[]{ new Ingredient(KELP)}, 														PESTLE_AND_MORTAR),
			new Product(GRIND, 				LAVA_SCALE_SHARD, 				new Ingredient[]{ new Ingredient(LAVA_SCALE)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_MUD_RUNES,				new Ingredient[]{ new Ingredient(MUD_RUNE)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				MYSTERIOUS_CRUSHED_MEAT, 		new Ingredient[]{ new Ingredient(MYSTERIOUS_MEAT)}, 											PESTLE_AND_MORTAR),
			new Product(GRIND, 				NIHIL_DUST, 					new Ingredient[]{ new Ingredient(NIHIL_SHARD)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				KARAMBWAN_PASTE, 				new Ingredient[]{ new Ingredient(POISON_KARAMBWAN)}, 											PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_COD, 					new Ingredient[]{ new Ingredient(RAW_COD)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				RUNE_DUST, 						new Ingredient[]{ new Ingredient(RUNE_SHARDS)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_SEAWEED, 				new Ingredient[]{ new Ingredient(SEAWEED)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_TOOTH, 					new Ingredient[]{ new Ingredient(SUQAH_TOOTH)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				UNICORN_HORN_DUST, 				new Ingredient[]{ new Ingredient(UNICORN_HORN)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				CRUSHED_SUPERIOR_DRAGON_BONES, 	new Ingredient[]{ new Ingredient(SUPERIOR_DRAGON_BONES)}, 										PESTLE_AND_MORTAR),
			new Product(GRIND, 				SQUID_PASTE, 					new Ingredient[]{ new Ingredient(RAW_JUMBO_SQUID)}, 											PESTLE_AND_MORTAR),
			new Product(GRIND, 				SQUID_PASTE, 					new Ingredient[]{ new Ingredient(RAW_SWORDTIP_SQUID)},	 										PESTLE_AND_MORTAR),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_BONES)}, 												CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_BAT_BONES)}, 											CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_BIG_BONES)}, 											CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_ZOGRE_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_BABYWYRM_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_BABYDRAGON_BONES)}, 									CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_WYRM_BONES)}, 											CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(SUNKISSED_BONES)}, 											CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_WYVERN_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_DRAGON_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_DRAKE_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_FAYRG_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_LAVA_DRAGON_BONES)}, 									CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_RAURG_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_HYDRA_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(DAGANNOTH_BONES_29376)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_OURG_BONES)}, 											CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_SUPERIOR_DRAGON_BONES)}, 								CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_BONE_STATUETTE)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_BONE_STATUETTE_29340)}, 								CHISEL), //Might not be required. Not sure what the difference is
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARDS, 			new Ingredient[]{ new Ingredient(BLESSED_BONE_STATUETTE_29342)}, 								CHISEL), //Might not be required. Not sure what the difference is
			new Product(COOKING_GUTTING_AERIAL, 	FISH_OFFCUTS,		 	new Ingredient[]{ new Ingredient(BLUEGILL)}, 			 										KNIFE),
			new Product(COOKING_GUTTING_AERIAL, 	FISH_OFFCUTS,		 	new Ingredient[]{ new Ingredient(COMMON_TENCH)}, 			 									KNIFE),
			new Product(COOKING_GUTTING_AERIAL, 	FISH_OFFCUTS,		 	new Ingredient[]{ new Ingredient(MOTTLED_EEL)}, 			 									KNIFE),
			new Product(COOKING_GUTTING_AERIAL, 	FISH_OFFCUTS,		 	new Ingredient[]{ new Ingredient(GREATER_SIREN)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_SHRIMPS)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_ANCHOVIES)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_TROUT)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_SALMON)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_LOBSTER)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_COD)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_PIKE)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_MACKEREL)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_BASS)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_HERRING)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_MONKFISH)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_SARDINE)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_SWORDFISH)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_TUNA)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_GIANT_KRILL)}, 			 								KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_HADDOCK)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	FISH_OFFCUTS,		 			new Ingredient[]{ new Ingredient(RAW_SWORDTIP_SQUID)}, 											KNIFE),
			new Product(COOKING_GUTTING, 	FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_SHARK)}, 													KNIFE),
			new Product(COOKING_GUTTING, 	FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_SEA_TURTLE)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_ANGLERFISH)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_DARK_CRAB)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_MANTA_RAY)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_YELLOWFIN)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_HALIBUT)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_BLUEFIN)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_JUMBO_SQUID)}, 											KNIFE),
			new Product(COOKING_GUTTING, 	FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_MARLIN)}, 													KNIFE),
			new Product(GRIND_DARK_ESSENCE, DARK_ESSENCE_FRAGMENTS, 		new Ingredient[]{ new Ingredient(DARK_ESSENCE_BLOCK)},				 							CHISEL),
			new Product(SUNFIRE_WINE, 		JUG_OF_SUNFIRE_WINE,			new Ingredient[]{ new Ingredient(JUG_OF_WINE), new Ingredient(SUNFIRE_SPLINTERS, 2)}, 	PESTLE_AND_MORTAR),

			new Product(FARM_PLANT_TREE_SEEDS, OAK_SEEDLING,		        new Ingredient(ACORN), FILLED_PLANT_POT),
			new Product(FARM_PLANT_TREE_SEEDS, WILLOW_SEEDLING,		        new Ingredient(WILLOW_SEED), FILLED_PLANT_POT),
			new Product(FARM_PLANT_TREE_SEEDS, MAPLE_SEEDLING,		        new Ingredient(MAPLE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, YEW_SEEDLING,		        new Ingredient(YEW_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, MAGIC_SEEDLING,		        new Ingredient(MAGIC_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, APPLE_SEEDLING,		        new Ingredient(APPLE_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, BANANA_SEEDLING,		        new Ingredient(BANANA_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, ORANGE_SEEDLING,		        new Ingredient(ORANGE_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, CURRY_SEEDLING,		        new Ingredient(CURRY_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PINEAPPLE_SEEDLING,	        new Ingredient(PINEAPPLE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PAPAYA_SEEDLING,		        new Ingredient(PAPAYA_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PALM_SEEDLING,		        new Ingredient(PALM_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, DRAGONFRUIT_SEEDLING,      	new Ingredient(DRAGONFRUIT_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, TEAK_SEEDLING,		        new Ingredient(TEAK_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, MAHOGANY_SEEDLING,	        new Ingredient(MAHOGANY_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, CAMPHOR_SEEDLING,	        new Ingredient(CAMPHOR_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, CALQUAT_SEEDLING,	        new Ingredient(CALQUAT_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, CRYSTAL_SEEDLING,	        new Ingredient(CRYSTAL_ACORN), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, IRONWOOD_SEEDLING,	        new Ingredient(IRONWOOD_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, SPIRIT_SEEDLING,		        new Ingredient(SPIRIT_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, CELASTRUS_SEEDLING,	        new Ingredient(CELASTRUS_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, REDWOOD_SEEDLING,	        new Ingredient(REDWOOD_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, ROSEWOOD_SEEDLING,	        new Ingredient(ROSEWOOD_SEED), FILLED_PLANT_POT )
	};

	@Inject private InventoryManager inventoryManager;

	@Inject private Client client;

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked evt)
	{
		if(evt.getMenuOption().equals(BREAK_DOWN)){
			for (Product product : PRODUCTS) {
				if(product.IngredientsIsIncludedIn(evt.getMenuTarget(), client)){
					int amount = product.getMakeProductCount(this.inventoryManager);
					if (amount > 0) {
						this.actionManager.setAction(product.getAction(), amount, product.getProductId());
					}
				}
			}
		}
		if (evt.getMenuAction() != MenuAction.WIDGET_TARGET_ON_WIDGET) {
			return;
		}
		ItemContainer inventory = this.client.getItemContainer(InventoryID.INVENTORY);
		Widget widget = this.client.getSelectedWidget();
		if (inventory == null|| widget == null) {
			return;
		}

		//Given evt.getMenuTarget() is in the following format <col=ff9040>Bird nest</col><col=ffffff> -> <col=ff9040>Bird nest</col>
		//The code below will check if the source and the target are the same, and return if it is the case
		Pattern r = Pattern.compile("<.*>(.*)</.*><.*>(.*)</.*>");
		Matcher m = r.matcher(evt.getMenuTarget());
		if (m.find() && Objects.equals(m.group(1), m.group(2))){
			return;
		}
		//Gets the item that is being clicked
		Item[] items = IntStream.of(widget.getId(), evt.getParam0())
								.mapToObj(inventory::getItem)
								.filter(n -> n!= null)
								.toArray(Item[]::new);
		//Gets the selected item to fix order of operation issues
		Item selectedItem = new Item(widget.getItemId(),1);
		for (Product product : PRODUCTS) {
			boolean selectedIsTool = product.getTool() == null || product.isToolUsed(selectedItem);
			if (product.isMadeWith(items) && selectedIsTool) {
				int amount = product.getMakeProductCount(this.inventoryManager);
				if (amount > 0) {
					this.actionManager.setAction(product.getAction(), amount, product.getProductId());
				}
			}
			boolean itemsIncludeTool = product.getTool() == null || product.isToolUsed(items);
			if(product.isMadeWith(selectedItem) && itemsIncludeTool){
				int amount = product.getMakeProductCount(this.inventoryManager);
				if (amount > 0) {
					this.actionManager.setAction(product.getAction(), amount, product.getProductId());
				}
			}
		}
	}
}
