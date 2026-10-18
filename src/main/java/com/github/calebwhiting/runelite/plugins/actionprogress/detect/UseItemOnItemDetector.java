package com.github.calebwhiting.runelite.plugins.actionprogress.detect;

import com.github.calebwhiting.runelite.api.InventoryManager;
import com.github.calebwhiting.runelite.data.Ingredient;
import com.github.calebwhiting.runelite.plugins.actionprogress.Product;
import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.MenuAction;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.eventbus.Subscribe;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import static com.github.calebwhiting.runelite.plugins.actionprogress.Action.*;
import static net.runelite.api.gameval.ItemID.*;

public class UseItemOnItemDetector extends ActionDetector
{

	private static final Ingredient PESTLE_AND_MORTAR = new Ingredient(ItemID.PESTLE_AND_MORTAR, 1, false);
	private static final Ingredient CHISEL = new Ingredient(ItemID.CHISEL, 1, false);
	private static final Ingredient KNIFE = new Ingredient(ItemID.KNIFE, 1, false);
	private static final Ingredient FILLED_PLANT_POT = new Ingredient(ItemID.PLANTPOT_COMPOST);
	private static final String BREAK_DOWN = "Break-down";

	private static final Product[] PRODUCTS = {
			new Product(GRIND, 				WAR_STRENGTH_FINE_ASHES, 					new Ingredient[]{ new Ingredient(ASHES)}, 														PESTLE_AND_MORTAR),
			new Product(GRIND, 				CRUSHED_BIRD_NEST, 					new Ingredient[]{ new Ingredient(BIRD_NEST_EMPTY)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				DRAGON_SCALE_DUST, 				new Ingredient[]{ new Ingredient(BLUE_DRAGON_SCALE)}, 											PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_CHARCOAL, 				new Ingredient[]{ new Ingredient(CHARCOAL)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				CHOCOLATE_DUST, 				new Ingredient[]{ new Ingredient(CHOCOLATE_BAR)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				HUNDRED_PIRATE_GROUND_GIANT_CRAB_MEAT, 		new Ingredient[]{ new Ingredient(HUNDRED_PIRATE_GIANT_CRAB_MEAT)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_DESERT_GOAT_HORN, 				new Ingredient[]{ new Ingredient(DESERT_GOAT_HORN)}, 											PESTLE_AND_MORTAR),
			new Product(GRIND, 				EADGAR_GROUND_TROLL_THISTLE, 				new Ingredient[]{ new Ingredient(EADGAR_DRIED_TROLL_THISTLE)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				FAIRYTALE2_GROUND_GORAK_CLAWS, 				new Ingredient[]{ new Ingredient(FAIRYTALE2_GORAK_CLAWS)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_GUAM, 					new Ingredient[]{ new Ingredient(GUAM_LEAF)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				HUNTINGBEAST_SABRETEETH_DUST, 				new Ingredient[]{ new Ingredient(HUNTINGBEAST_SABRETEETH)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				HUNDRED_PIRATE_GROUND_KELP, 					new Ingredient[]{ new Ingredient(HUNDRED_PIRATE_KELP)}, 														PESTLE_AND_MORTAR),
			new Product(GRIND, 				LAVA_SHARD, 				new Ingredient[]{ new Ingredient(LAVA_SCALE)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				EYEGLO_GROUND_MUD_RUNES,				new Ingredient[]{ new Ingredient(MUDRUNE)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				MYQ4_MEAT_CRUSHED, 		new Ingredient[]{ new Ingredient(MYQ4_MEAT)}, 											PESTLE_AND_MORTAR),
			new Product(GRIND, 				NIHIL_DUST, 					new Ingredient[]{ new Ingredient(NIHIL_SHARD)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				TBWT_RAW_KARAMBWAN_PASTE, 				new Ingredient[]{ new Ingredient(TBWT_POORLY_COOKED_KARAMBWAN)}, 											PESTLE_AND_MORTAR),
			new Product(GRIND, 				HUNDRED_PIRATE_GROUND_COD, 					new Ingredient[]{ new Ingredient(RAW_COD)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				RUNE_DUST, 						new Ingredient[]{ new Ingredient(RUNE_SHARDS)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				GROUND_SEAWEED, 				new Ingredient[]{ new Ingredient(SEAWEED)}, 													PESTLE_AND_MORTAR),
			new Product(GRIND, 				LUNAR_GROUNDTOOTH, 					new Ingredient[]{ new Ingredient(SUQKA_TOOTH)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				UNICORN_HORN_DUST, 				new Ingredient[]{ new Ingredient(UNICORN_HORN)}, 												PESTLE_AND_MORTAR),
			new Product(GRIND, 				CRUSHED_DRAGON_BONES, 	new Ingredient[]{ new Ingredient(DRAGON_BONES_SUPERIOR)}, 										PESTLE_AND_MORTAR),
			new Product(GRIND, 				SQUID_PASTE, 					new Ingredient[]{ new Ingredient(RAW_JUMBO_SQUID)}, 											PESTLE_AND_MORTAR),
			new Product(GRIND, 				SQUID_PASTE, 					new Ingredient[]{ new Ingredient(RAW_SWORDTIP_SQUID)},	 										PESTLE_AND_MORTAR),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_BONES)}, 												CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_BAT_BONES)}, 											CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_BIG_BONES)}, 											CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_ZOGRE_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_BABYWYRM_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_BABYDRAGON_BONES)}, 									CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_WYRM_BONES)}, 											CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(SUN_KISSED_BONE)}, 											CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_WYVERN_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_DRAGON_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_DRAKE_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_FAYRG_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_LAVA_DRAGON_BONES)}, 									CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_RAURG_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_HYDRA_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_DAGANNOTH_BONES)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_OURG_BONES)}, 											CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(BLESSED_DRAGON_BONES_SUPERIOR)}, 								CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(VARLAMORE_BONE_STATUETTE01)}, 										CHISEL),
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(VARLAMORE_BONE_STATUETTE02)}, 								CHISEL), //Might not be required. Not sure what the difference is
			new Product(GRIND_BONE_SHARDS, 	BLESSED_BONE_SHARD, 			new Ingredient[]{ new Ingredient(VARLAMORE_BONE_STATUETTE03)}, 								CHISEL), //Might not be required. Not sure what the difference is
			new Product(COOKING_GUTTING_AERIAL, 	BRUT_FISH_CUTS,		 	new Ingredient[]{ new Ingredient(AERIAL_FISHING_BLUEGILL)}, 			 										KNIFE),
			new Product(COOKING_GUTTING_AERIAL, 	BRUT_FISH_CUTS,		 	new Ingredient[]{ new Ingredient(AERIAL_FISHING_COMMON_TENCH)}, 			 									KNIFE),
			new Product(COOKING_GUTTING_AERIAL, 	BRUT_FISH_CUTS,		 	new Ingredient[]{ new Ingredient(AERIAL_FISHING_MOTTLED_EEL)}, 			 									KNIFE),
			new Product(COOKING_GUTTING_AERIAL, 	BRUT_FISH_CUTS,		 	new Ingredient[]{ new Ingredient(AERIAL_FISHING_GREATER_SIREN)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_SHRIMP)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_ANCHOVIES)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_TROUT)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_SALMON)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_LOBSTER)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_COD)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_PIKE)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_MACKEREL)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_BASS)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_HERRING)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_MONKFISH)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_SARDINE)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_SWORDFISH)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_TUNA)}, 			 										KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_GIANT_KRILL)}, 			 								KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_HADDOCK)}, 			 									KNIFE),
			new Product(COOKING_GUTTING, 	BRUT_FISH_CUTS,		 			new Ingredient[]{ new Ingredient(RAW_SWORDTIP_SQUID)}, 											KNIFE),
			new Product(COOKING_GUTTING, 	SAILING_FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_SHARK)}, 													KNIFE),
			new Product(COOKING_GUTTING, 	SAILING_FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_SEATURTLE)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	SAILING_FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_ANGLERFISH)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	SAILING_FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_DARK_CRAB)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	SAILING_FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_MANTARAY)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	SAILING_FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_YELLOWFIN)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	SAILING_FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_HALIBUT)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	SAILING_FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_BLUEFIN)}, 												KNIFE),
			new Product(COOKING_GUTTING, 	SAILING_FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_JUMBO_SQUID)}, 											KNIFE),
			new Product(COOKING_GUTTING, 	SAILING_FINE_FISH_OFFCUTS,	 			new Ingredient[]{ new Ingredient(RAW_MARLIN)}, 													KNIFE),
			new Product(GRIND_DARK_ESSENCE, BIGBLANKRUNE, 		new Ingredient[]{ new Ingredient(ARCEUUS_ESSENCE_BLOCK_DARK)},				 							CHISEL),
			new Product(SUNFIRE_WINE, 		JUG_SUNFIRE_WINE,			new Ingredient[]{ new Ingredient(JUG_WINE), new Ingredient(SUNFIRESPLINTER, 2)}, 	PESTLE_AND_MORTAR),

			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_ACORN,		        new Ingredient(ACORN), FILLED_PLANT_POT),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_WILLOW_SEED,		        new Ingredient(WILLOW_SEED), FILLED_PLANT_POT),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_MAPLE_SEED,		        new Ingredient(MAPLE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_YEW_SEED,		        new Ingredient(YEW_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_MAGIC_TREE_SEED,		        new Ingredient(MAGIC_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_APPLE_SEED,		        new Ingredient(APPLE_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_BANANA_SEED,		        new Ingredient(BANANA_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_ORANGE_SEED,		        new Ingredient(ORANGE_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_CURRY_SEED,		        new Ingredient(CURRY_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_PINEAPPLE_SEED,	        new Ingredient(PINEAPPLE_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_PAPAYA_SEED,		        new Ingredient(PAPAYA_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_PALM_SEED,		        new Ingredient(PALM_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_DRAGONFRUIT_SEED,      	new Ingredient(DRAGONFRUIT_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_TEAK_SEED,		        new Ingredient(TEAK_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_MAHOGANY_SEED,	        new Ingredient(MAHOGANY_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_CAMPHOR_SEED,	        new Ingredient(CAMPHOR_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_CALQUAT_SEED,	        new Ingredient(CALQUAT_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_CRYSTAL_TREE_SEED,	        new Ingredient(CRYSTAL_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_IRONWOOD_SEED,	        new Ingredient(IRONWOOD_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_SPIRIT_TREE_SEED,		        new Ingredient(SPIRIT_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_CELASTRUS_TREE_SEED,	        new Ingredient(CELASTRUS_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_REDWOOD_TREE_SEED,	        new Ingredient(REDWOOD_TREE_SEED), FILLED_PLANT_POT ),
			new Product(FARM_PLANT_TREE_SEEDS, PLANTPOT_ROSEWOOD_SEED,	        new Ingredient(ROSEWOOD_SEED), FILLED_PLANT_POT ),

			new Product(BUTTERFLY_MIX, HUNTER_MIX_RUBY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(BUTTERFLY_JAR_RUBY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_RUBY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_WILD_MEAT), new Ingredient(BUTTERFLY_JAR_RUBY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_RUBY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_LARUPIA_MEAT), new Ingredient(BUTTERFLY_JAR_RUBY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_RUBY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_BARBED_MEAT), new Ingredient(BUTTERFLY_JAR_RUBY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_RUBY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_GRAAHK_MEAT), new Ingredient(BUTTERFLY_JAR_RUBY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_RUBY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_KYATT_MEAT), new Ingredient(BUTTERFLY_JAR_RUBY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_RUBY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(BUTTERFLY_JAR_RUBY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_RUBY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPESUN_MEAT), new Ingredient(BUTTERFLY_JAR_RUBY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_RUBY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_SPEEDY2_MEAT), new Ingredient(BUTTERFLY_JAR_RUBY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_RUBY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPEMOON_MEAT), new Ingredient(BUTTERFLY_JAR_RUBY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_GLACIALIS_2DOSE, 	new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(BUTTERFLY_JAR_GLACIALIS)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_GLACIALIS_2DOSE, 	new Ingredient[]{new Ingredient(HUNTINGBEAST_WILD_MEAT), new Ingredient(BUTTERFLY_JAR_GLACIALIS)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_GLACIALIS_2DOSE, 	new Ingredient[]{new Ingredient(HUNTING_LARUPIA_MEAT), new Ingredient(BUTTERFLY_JAR_GLACIALIS)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_GLACIALIS_2DOSE, 	new Ingredient[]{new Ingredient(HUNTINGBEAST_BARBED_MEAT), new Ingredient(BUTTERFLY_JAR_GLACIALIS)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_GLACIALIS_2DOSE, 	new Ingredient[]{new Ingredient(HUNTING_GRAAHK_MEAT), new Ingredient(BUTTERFLY_JAR_GLACIALIS)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_GLACIALIS_2DOSE, 	new Ingredient[]{new Ingredient(HUNTING_KYATT_MEAT), new Ingredient(BUTTERFLY_JAR_GLACIALIS)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_GLACIALIS_2DOSE, 	new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(BUTTERFLY_JAR_GLACIALIS)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_GLACIALIS_2DOSE, 	new Ingredient[]{new Ingredient(HUNTING_ANTELOPESUN_MEAT), new Ingredient(BUTTERFLY_JAR_GLACIALIS)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_GLACIALIS_2DOSE, 	new Ingredient[]{new Ingredient(HUNTINGBEAST_SPEEDY2_MEAT), new Ingredient(BUTTERFLY_JAR_GLACIALIS)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_GLACIALIS_2DOSE, 	new Ingredient[]{new Ingredient(HUNTING_ANTELOPEMOON_MEAT), new Ingredient(BUTTERFLY_JAR_GLACIALIS)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SNOWY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(BUTTERFLY_JAR_SNOWY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SNOWY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_WILD_MEAT), new Ingredient(BUTTERFLY_JAR_SNOWY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SNOWY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_LARUPIA_MEAT), new Ingredient(BUTTERFLY_JAR_SNOWY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SNOWY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_BARBED_MEAT), new Ingredient(BUTTERFLY_JAR_SNOWY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SNOWY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_GRAAHK_MEAT), new Ingredient(BUTTERFLY_JAR_SNOWY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SNOWY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_KYATT_MEAT), new Ingredient(BUTTERFLY_JAR_SNOWY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SNOWY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(BUTTERFLY_JAR_SNOWY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SNOWY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPESUN_MEAT), new Ingredient(BUTTERFLY_JAR_SNOWY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SNOWY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_SPEEDY2_MEAT), new Ingredient(BUTTERFLY_JAR_SNOWY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SNOWY_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPEMOON_MEAT), new Ingredient(BUTTERFLY_JAR_SNOWY)}, 				PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_WARLOCK_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(BUTTERFLY_JAR_WARLOCK)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_WARLOCK_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_WILD_MEAT), new Ingredient(BUTTERFLY_JAR_WARLOCK)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_WARLOCK_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_LARUPIA_MEAT), new Ingredient(BUTTERFLY_JAR_WARLOCK)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_WARLOCK_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_BARBED_MEAT), new Ingredient(BUTTERFLY_JAR_WARLOCK)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_WARLOCK_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_GRAAHK_MEAT), new Ingredient(BUTTERFLY_JAR_WARLOCK)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_WARLOCK_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_KYATT_MEAT), new Ingredient(BUTTERFLY_JAR_WARLOCK)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_WARLOCK_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(BUTTERFLY_JAR_WARLOCK)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_WARLOCK_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPESUN_MEAT), new Ingredient(BUTTERFLY_JAR_WARLOCK)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_WARLOCK_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_SPEEDY2_MEAT), new Ingredient(BUTTERFLY_JAR_WARLOCK)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_WARLOCK_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPEMOON_MEAT), new Ingredient(BUTTERFLY_JAR_WARLOCK)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_WILD_MEAT), new Ingredient(HUNTGUIDE_SUNLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_LARUPIA_MEAT), new Ingredient(HUNTGUIDE_SUNLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_BARBED_MEAT), new Ingredient(HUNTGUIDE_SUNLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_GRAAHK_MEAT), new Ingredient(HUNTGUIDE_SUNLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_KYATT_MEAT), new Ingredient(HUNTGUIDE_SUNLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(HUNTGUIDE_SUNLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPESUN_MEAT), new Ingredient(HUNTGUIDE_SUNLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_SPEEDY2_MEAT), new Ingredient(HUNTGUIDE_SUNLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPEMOON_MEAT), new Ingredient(HUNTGUIDE_SUNLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_WILD_MEAT), new Ingredient(BUTTERFLY_JAR_SUNMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_LARUPIA_MEAT), new Ingredient(BUTTERFLY_JAR_SUNMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_BARBED_MEAT), new Ingredient(BUTTERFLY_JAR_SUNMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_GRAAHK_MEAT), new Ingredient(BUTTERFLY_JAR_SUNMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_KYATT_MEAT), new Ingredient(BUTTERFLY_JAR_SUNMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(BUTTERFLY_JAR_SUNMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPESUN_MEAT), new Ingredient(BUTTERFLY_JAR_SUNMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_SPEEDY2_MEAT), new Ingredient(BUTTERFLY_JAR_SUNMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_SUNMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPEMOON_MEAT), new Ingredient(BUTTERFLY_JAR_SUNMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(HUNTGUIDE_MOONLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_WILD_MEAT), new Ingredient(HUNTGUIDE_MOONLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_LARUPIA_MEAT), new Ingredient(HUNTGUIDE_MOONLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_BARBED_MEAT), new Ingredient(HUNTGUIDE_MOONLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_GRAAHK_MEAT), new Ingredient(HUNTGUIDE_MOONLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_KYATT_MEAT), new Ingredient(HUNTGUIDE_MOONLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(HUNTGUIDE_MOONLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPESUN_MEAT), new Ingredient(HUNTGUIDE_MOONLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_SPEEDY2_MEAT), new Ingredient(HUNTGUIDE_MOONLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPEMOON_MEAT), new Ingredient(HUNTGUIDE_MOONLIGHT_MOTH)}, 			PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(BUTTERFLY_JAR_MOONMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_WILD_MEAT), new Ingredient(BUTTERFLY_JAR_MOONMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_LARUPIA_MEAT), new Ingredient(BUTTERFLY_JAR_MOONMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_BARBED_MEAT), new Ingredient(BUTTERFLY_JAR_MOONMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_GRAAHK_MEAT), new Ingredient(BUTTERFLY_JAR_MOONMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_KYATT_MEAT), new Ingredient(BUTTERFLY_JAR_MOONMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_FENNECFOX_MEAT), new Ingredient(BUTTERFLY_JAR_MOONMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPESUN_MEAT), new Ingredient(BUTTERFLY_JAR_MOONMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTINGBEAST_SPEEDY2_MEAT), new Ingredient(BUTTERFLY_JAR_MOONMOTH)}, 		PESTLE_AND_MORTAR),
			new Product(BUTTERFLY_MIX, HUNTER_MIX_MOONMOTH_2DOSE, 		new Ingredient[]{new Ingredient(HUNTING_ANTELOPEMOON_MEAT), new Ingredient(BUTTERFLY_JAR_MOONMOTH)}, 		PESTLE_AND_MORTAR),
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
		ItemContainer inventory = this.client.getItemContainer(InventoryID.INV);
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
			boolean toolIsInInventory = product.getTool() == null || this.inventoryManager.getItemCountById(product.getTool().getItemId()) > 0;
			if (product.isMadeWith(items) && product.isMadeWith(selectedItem)) {
				int amount = product.getMakeProductCount(this.inventoryManager);
				if (amount > 0) {
					this.actionManager.setAction(product.getAction(), amount, product.getProductId());
				}
			}
		}
	}
}
