package com.github.calebwhiting.runelite.data;

import net.runelite.api.gameval.ItemID;

/**
 * NOTE:
 * The difference between a finished and unfinished potion is that
 * unfinished potions take 1 tick to produce, whereas finished potions take 2 ticks.
 * This means you need to "wrongly" categorize certain recipes for correct functionality.
 */
public interface Herblore
{

	Recipe[] UNFINISHED_POTIONS = {
			// @formatter:off
			new Recipe(ItemID.GUAMVIAL, Ingredients.VIAL_OF_WATER, Ingredients.GUAM_LEAF),
			new Recipe(ItemID.ROGUES_PURSE_SOL, Ingredients.VIAL_OF_WATER, Ingredients.ROGUES_PURSE),
			new Recipe(ItemID.MARRENTILLVIAL, Ingredients.VIAL_OF_WATER, Ingredients.MARRENTILL),
			new Recipe(ItemID.TARROMINVIAL, Ingredients.VIAL_OF_WATER, Ingredients.TARROMIN),
			new Recipe(ItemID.ASHESVIAL, Ingredients.VIAL_OF_WATER, Ingredients.ASHES),
			new Recipe(ItemID.HARRALANDERVIAL, Ingredients.VIAL_OF_WATER, Ingredients.HARRALANDER),
			new Recipe(ItemID.FAIRYTALE2_STARFLOWERVIAL, Ingredients.VIAL_OF_WATER, Ingredients.STAR_FLOWER),
			new Recipe(ItemID.RANARRVIAL, Ingredients.VIAL_OF_WATER, Ingredients.RANARR_WEED),
			new Recipe(ItemID.TOADFLAXVIAL, Ingredients.VIAL_OF_WATER, Ingredients.TOADFLAX),
			new Recipe(ItemID.IRITVIAL, Ingredients.VIAL_OF_WATER, Ingredients.IRIT_LEAF),
			new Recipe(ItemID.AVANTOEVIAL, Ingredients.VIAL_OF_WATER, Ingredients.AVANTOE),
			new Recipe(ItemID.KWUARMVIAL, Ingredients.VIAL_OF_WATER, Ingredients.KWUARM),
			new Recipe(ItemID.SNAPDRAGONVIAL, Ingredients.VIAL_OF_WATER, Ingredients.SNAPDRAGON),
			new Recipe(ItemID.CADANTINEVIAL, Ingredients.VIAL_OF_WATER, Ingredients.CADANTINE),
			new Recipe(ItemID.LANTADYMEVIAL, Ingredients.VIAL_OF_WATER, Ingredients.LANTADYME),
			new Recipe(ItemID.DWARFWEEDVIAL, Ingredients.VIAL_OF_WATER, Ingredients.DWARF_WEED),
			new Recipe(ItemID.TORSTOLVIAL, Ingredients.VIAL_OF_WATER, Ingredients.TORSTOL),
			new Recipe(ItemID.CADANTINE_BLOODVIAL, Ingredients.VIAL_OF_BLOOD, Ingredients.CADANTINE),
			new Recipe(ItemID.BURGH_UNFINISHED_GUTHIX_BALANCE_1, Ingredients.RESTORE_1, Ingredients.GARLIC),
			new Recipe(ItemID.BURGH_UNFINISHED_GUTHIX_BALANCE_2, Ingredients.RESTORE_2, Ingredients.GARLIC),
			new Recipe(ItemID.BURGH_UNFINISHED_GUTHIX_BALANCE_3, Ingredients.RESTORE_3, Ingredients.GARLIC),
			new Recipe(ItemID.BURGH_UNFINISHED_GUTHIX_BALANCE_4, Ingredients.RESTORE_4, Ingredients.GARLIC),
			// Seriously, fuck this recipe with a barbed harpoon :(
			new Recipe(HerbTeaMix.HERB_TEA_MIX_G, Ingredients.CUP_OF_HOT_WATER, Ingredients.GUAM_LEAF),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_M, Ingredients.CUP_OF_HOT_WATER, Ingredients.MARRENTILL),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_H, Ingredients.CUP_OF_HOT_WATER, Ingredients.HARRALANDER),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_GG, Ingredients.HERB_TEA_MIX_G, Ingredients.GUAM_LEAF),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_GM, Ingredients.HERB_TEA_MIX_G, Ingredients.MARRENTILL),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_GM, Ingredients.HERB_TEA_MIX_M, Ingredients.GUAM_LEAF),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_HG, Ingredients.HERB_TEA_MIX_G, Ingredients.HARRALANDER),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_HG, Ingredients.HERB_TEA_MIX_H, Ingredients.GUAM_LEAF),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_HM, Ingredients.HERB_TEA_MIX_H, Ingredients.MARRENTILL),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_HM, Ingredients.HERB_TEA_MIX_M, Ingredients.HARRALANDER),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_GGH, Ingredients.HERB_TEA_MIX_GG, Ingredients.HARRALANDER),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_GGH, Ingredients.HERB_TEA_MIX_HG, Ingredients.GUAM_LEAF),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_GGM, Ingredients.HERB_TEA_MIX_GG, Ingredients.MARRENTILL),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_GGM, Ingredients.HERB_TEA_MIX_GM, Ingredients.GUAM_LEAF),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_HMG, Ingredients.HERB_TEA_MIX_GM, Ingredients.HARRALANDER),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_HMG, Ingredients.HERB_TEA_MIX_HM, Ingredients.GUAM_LEAF),
			new Recipe(HerbTeaMix.HERB_TEA_MIX_HMG, Ingredients.HERB_TEA_MIX_HG, Ingredients.MARRENTILL),
			// @formatter:on
	};
	Recipe[] POTIONS = {
			// @formatter:off
			new Recipe(ItemID._3DOSE1ATTACK, Ingredients.GUAM_UNF, Ingredients.EYE_OF_NEWT),
			new Recipe(ItemID._3DOSEANTIPOISON, Ingredients.MARRENTILL_UNF, Ingredients.UNICORN_HORN_DUST),
			new Recipe(ItemID.RELICYMS_BALM3, Ingredients.ROGUES_PURSE_UNF, Ingredients.SNAKE_WEED),
			new Recipe(ItemID._3DOSE1STRENGTH, Ingredients.TARROMIN_UNF, Ingredients.LIMPWURT_ROOT),
			new Recipe(ItemID.MORT_SERUM3, Ingredients.TARROMIN_UNF, Ingredients.ASHES),
			new Recipe(ItemID.MORT_SERUM3, Ingredients.ASH_POTION, Ingredients.TARROMIN),
			new Recipe(ItemID.SUPERCOMPOST_POTION_3, Ingredients.HARRALANDER_UNF, Ingredients.VOLCANIC_ASH),
			new Recipe(ItemID._3DOSESTATRESTORE, Ingredients.HARRALANDER_UNF, Ingredients.RED_SPIDERS_EGGS),
			new Recipe(ItemID.BURGH_GUTHIX_BALANCE_1, Ingredients.GUTHIX_BALANCE_UNF_1, Ingredients.SILVER_DUST),
			new Recipe(ItemID.BURGH_GUTHIX_BALANCE_2, Ingredients.GUTHIX_BALANCE_UNF_2, Ingredients.SILVER_DUST),
			new Recipe(ItemID.BURGH_GUTHIX_BALANCE_3, Ingredients.GUTHIX_BALANCE_UNF_3, Ingredients.SILVER_DUST),
			new Recipe(ItemID.BURGH_GUTHIX_BALANCE_4, Ingredients.GUTHIX_BALANCE_UNF_4, Ingredients.SILVER_DUST),
			new Recipe(ItemID.BLAMISH_OIL, Ingredients.HARRALANDER_UNF, Ingredients.BLAMISH_SNAIL_SLIME),
			new Recipe(ItemID._3DOSE1ENERGY, Ingredients.HARRALANDER_UNF, Ingredients.CHOCOLATE_DUST),
			new Recipe(ItemID._3DOSE1DEFENSE, Ingredients.RANARR_UNF, Ingredients.WHITE_BERRIES),
			new Recipe(ItemID._3DOSE1AGILITY, Ingredients.TOADFLAX_UNF, Ingredients.TOADS_LEGS),
			new Recipe(ItemID._3DOSECOMBAT, Ingredients.HARRALANDER_UNF, Ingredients.GOAT_HORN_DUST),
			new Recipe(ItemID._3DOSEPRAYERRESTORE, Ingredients.RANARR_UNF, Ingredients.SNAPE_GRASS),
			new Recipe(ItemID._3DOSE2ATTACK, Ingredients.IRIT_UNF, Ingredients.EYE_OF_NEWT),
			new Recipe(ItemID.LOTG_3DOSEGOBLIN, Ingredients.TOADFLAX_UNF, Ingredients.PHARMAKOS_BERRIES),
			new Recipe(ItemID._3DOSE2ANTIPOISON, Ingredients.IRIT_UNF, Ingredients.UNICORN_HORN_DUST),
			new Recipe(ItemID._3DOSEFISHERSPOTION, Ingredients.AVANTOE_UNF, Ingredients.SNAPE_GRASS),
			new Recipe(ItemID._3DOSE2ENERGY, Ingredients.AVANTOE_UNF, Ingredients.MORT_MYRE_FUNGUS),
			new Recipe(ItemID.GRIM_SHRINKING_POTION, Ingredients.TARROMIN_UNF, Ingredients.SHRUNK_OGLEROOT),
			new Recipe(ItemID._3DOSEHUNTING, Ingredients.AVANTOE_UNF, Ingredients.KEBBIT_TEETH_DUST),
			new Recipe(ItemID._3DOSEGOADING, Ingredients.HARRALANDER_UNF, Ingredients.ALDARIUM),
			new Recipe(ItemID._3DOSE1PRAYER_REGENERATION, Ingredients.HUASCA_UNF, Ingredients.ALDARIUM),
			new Recipe(ItemID._3DOSE2STRENGTH, Ingredients.KWUARM_UNF, Ingredients.LIMPWURT_ROOT),
			new Recipe(ItemID._3DOSEMAGICESS, Ingredients.MAGIC_ESSENCE_UNF, Ingredients.GORAK_CLAW_POWDER),
			new Recipe(ItemID.HAEMOSTATIC_POULTICE, Ingredients.ELKHORN_UNF, Ingredients.SQUID_PASTE),
			new Recipe(ItemID._3DOSEHAEMOSTATICDRESSING, Ingredients.HAEMOSTATIC_POULTICE, Ingredients.COTTON_YARN),
			new Recipe(ItemID.WEAPON_POISON, Ingredients.KWUARM_UNF, Ingredients.DRAGON_SCALE_DUST),
			new Recipe(ItemID._3DOSE2FISHERSPOTION, Ingredients.PILLAR_UNF, Ingredients.HADDOCK_EYE),
			new Recipe(ItemID._3DOSE2RESTORE, Ingredients.SNAPDRAGON_UNF, Ingredients.RED_SPIDERS_EGGS),
			new Recipe(SanfewSerum.MIX_1_1, Ingredients.SUPER_RESTORE_1, Ingredients.UNICORN_HORN_DUST),
			new Recipe(SanfewSerum.MIX_1_2, Ingredients.SUPER_RESTORE_2, Ingredients.UNICORN_HORN_DUST),
			new Recipe(SanfewSerum.MIX_1_3, Ingredients.SUPER_RESTORE_3, Ingredients.UNICORN_HORN_DUST),
			new Recipe(SanfewSerum.MIX_1_4, Ingredients.SUPER_RESTORE_4, Ingredients.UNICORN_HORN_DUST),
			new Recipe(SanfewSerum.MIX_2_1, Ingredients.MIXTURE_STEP_1_1, Ingredients.SNAKE_WEED),
			new Recipe(SanfewSerum.MIX_2_2, Ingredients.MIXTURE_STEP_1_2, Ingredients.SNAKE_WEED),
			new Recipe(SanfewSerum.MIX_2_3, Ingredients.MIXTURE_STEP_1_3, Ingredients.SNAKE_WEED),
			new Recipe(SanfewSerum.MIX_2_4, Ingredients.MIXTURE_STEP_1_4, Ingredients.SNAKE_WEED),
			new Recipe(ItemID.SANFEW_SALVE_1_DOSE, Ingredients.MIXTURE_STEP_2_1, Ingredients.NAIL_BEAST_NAILS),
			new Recipe(ItemID.SANFEW_SALVE_2_DOSE, Ingredients.MIXTURE_STEP_2_2, Ingredients.NAIL_BEAST_NAILS),
			new Recipe(ItemID.SANFEW_SALVE_3_DOSE, Ingredients.MIXTURE_STEP_2_3, Ingredients.NAIL_BEAST_NAILS),
			new Recipe(ItemID.SANFEW_SALVE_4_DOSE, Ingredients.MIXTURE_STEP_2_4, Ingredients.NAIL_BEAST_NAILS),
			new Recipe(ItemID._3DOSE2DEFENSE, Ingredients.CADANTINE_UNF, Ingredients.WHITE_BERRIES),
			new Recipe(ItemID._3DOSE2HUNTING, Ingredients.PILLAR_UNF, Ingredients.CRAB_PASTE),
			new Recipe(ItemID.ANTIDOTE_3, Ingredients.ANTIDOTE_P_UNF, Ingredients.YEW_ROOTS),
			new Recipe(ItemID._3DOSE1ANTIDRAGON, Ingredients.LANTADYME_UNF, Ingredients.DRAGON_SCALE_DUST),
			new Recipe(ItemID._3DOSERANGERSPOTION, Ingredients.DWARF_WEED_UNF, Ingredients.WINE_OF_ZAMORAK),
			new Recipe(ItemID.WEAPON_POISON_, Ingredients.WEAPON_POISON_P_UNF, Ingredients.RED_SPIDERS_EGGS),
			new Recipe(ItemID._3DOSE1MAGIC, Ingredients.LANTADYME_UNF, Ingredients.POTATO_CACTUS),
			new Recipe(ItemID._3DOSEPOTIONOFZAMORAK, Ingredients.TORSTOL_UNF, Ingredients.JANGERBERRIES),
			new Recipe(ItemID.ANTIDOTE__3, Ingredients.ANTIDOTE_PP_UNF, Ingredients.MAGIC_ROOTS),
			new Recipe(ItemID._3DOSEBASTION, Ingredients.CADANTINE_BLOOD_UNF, Ingredients.WINE_OF_ZAMORAK),
			new Recipe(ItemID._3DOSEBATTLEMAGE, Ingredients.CADANTINE_BLOOD_UNF, Ingredients.POTATO_CACTUS),
			new Recipe(ItemID._3DOSEPOTIONOFSARADOMIN, Ingredients.TOADFLAX_UNF, Ingredients.CRUSHED_NEST),
			new Recipe(ItemID._3DOSESURGE, Ingredients.TORSTOL_UNF, Ingredients.DEMONIC_TALLOW),
			new Recipe(ItemID.WEAPON_POISON__, Ingredients.WEAPON_POISON_PP_UNF, Ingredients.POISON_IVY_BERRIES),
			new Recipe(ItemID._3DOSEANCIENTBREW, Ingredients.DWARF_WEED_UNF, Ingredients.NIHIL_DUST),
			new Recipe(ItemID._4DOSEFORGOTTENBREW, Ingredients.ANCIENT_BREW_4, Ingredients.ANCIENT_ESSENCE.clone(80)),
			new Recipe(ItemID._4DOSE2COMBAT, Ingredients.SUPER_ATTACK_4, Ingredients.SUPER_STRENGTH_4, Ingredients.SUPER_DEFENCE_4, Ingredients.TORSTOL),
			new Recipe(ItemID._4DOSE2COMBAT, Ingredients.SUPER_ATTACK_4, Ingredients.SUPER_STRENGTH_4, Ingredients.SUPER_DEFENCE_4, Ingredients.TORSTOL_UNF),
			new Recipe(ItemID._4DOSE3ANTIDRAGON, Ingredients.ANTIFIRE_4, Ingredients.CRUSHED_SUPERIOR_DRAGON_BONES),
			new Recipe(ItemID.ANTIVENOM_4, Ingredients.ANTIVENOM_4, Ingredients.TORSTOL),
			new Recipe(ItemID._1DOSESTAMINA, true, Ingredients.SUPER_ENERGY_1, Ingredients.AMYLASE_CRYSTAL),
			new Recipe(ItemID._2DOSESTAMINA, true, Ingredients.SUPER_ENERGY_2, Ingredients.AMYLASE_CRYSTAL.clone(2)),
			new Recipe(ItemID._3DOSESTAMINA, true, Ingredients.SUPER_ENERGY_3, Ingredients.AMYLASE_CRYSTAL.clone(3)),
			new Recipe(ItemID._4DOSESTAMINA, true, Ingredients.SUPER_ENERGY_4, Ingredients.AMYLASE_CRYSTAL.clone(4)),
			new Recipe(ItemID._1DOSE2STAMINA, Ingredients.STAMINA_1, Ingredients.MARLIN_SCALES),
			new Recipe(ItemID._2DOSE2STAMINA, Ingredients.STAMINA_2, Ingredients.MARLIN_SCALES.clone(2)),
			new Recipe(ItemID._3DOSE2STAMINA, Ingredients.STAMINA_3, Ingredients.MARLIN_SCALES.clone(3)),
			new Recipe(ItemID._4DOSE2STAMINA, Ingredients.STAMINA_4, Ingredients.MARLIN_SCALES.clone(4)),
			new Recipe(ItemID._3DOSEARMADYLBREW, Ingredients.UMBRAL_UNF, Ingredients.RAINBOW_CRAB_PASTE),
			new Recipe(ItemID._1DOSE2ANTIDRAGON, true,Ingredients.ANTIFIRE_1, Ingredients.LAVA_SCALE_SHARD),
			new Recipe(ItemID._2DOSE2ANTIDRAGON, true, Ingredients.ANTIFIRE_2, Ingredients.LAVA_SCALE_SHARD.clone(2)),
			new Recipe(ItemID._3DOSE2ANTIDRAGON, true, Ingredients.ANTIFIRE_3, Ingredients.LAVA_SCALE_SHARD.clone(3)),
			new Recipe(ItemID._4DOSE2ANTIDRAGON, true, Ingredients.ANTIFIRE_4, Ingredients.LAVA_SCALE_SHARD.clone(4)),
			new Recipe(ItemID.ANTIVENOM1, true, Ingredients.ANTIDOTE_PP_1, Ingredients.ZULRAHS_SCALES.clone(5)),
			new Recipe(ItemID.ANTIVENOM2, true, Ingredients.ANTIDOTE_PP_2, Ingredients.ZULRAHS_SCALES.clone(10)),
			new Recipe(ItemID.ANTIVENOM3, true, Ingredients.ANTIDOTE_PP_3, Ingredients.ZULRAHS_SCALES.clone(15)),
			new Recipe(ItemID.ANTIVENOM4, true, Ingredients.ANTIDOTE_PP_4, Ingredients.ZULRAHS_SCALES.clone(20)),
			new Recipe(ItemID._1DOSE4ANTIDRAGON, true,  Ingredients.SUPER_ANTIFIRE_1, Ingredients.LAVA_SCALE_SHARD),
			new Recipe(ItemID._2DOSE4ANTIDRAGON, true,  Ingredients.SUPER_ANTIFIRE_2, Ingredients.LAVA_SCALE_SHARD.clone(2)),
			new Recipe(ItemID._3DOSE4ANTIDRAGON, true, Ingredients.SUPER_ANTIFIRE_3, Ingredients.LAVA_SCALE_SHARD.clone(3)),
			new Recipe(ItemID._4DOSE4ANTIDRAGON, true, Ingredients.SUPER_ANTIFIRE_4, Ingredients.LAVA_SCALE_SHARD.clone(4)),
			// Divine potions
			new Recipe(ItemID._1DOSEDIVINEATTACK, Ingredients.SUPER_ATTACK_1, Ingredients.CRYSTAL_DUST),
			new Recipe(ItemID._2DOSEDIVINEATTACK, Ingredients.SUPER_ATTACK_2, Ingredients.CRYSTAL_DUST.clone(2)),
			new Recipe(ItemID._3DOSEDIVINEATTACK, Ingredients.SUPER_ATTACK_3, Ingredients.CRYSTAL_DUST.clone(3)),
			new Recipe(ItemID._4DOSEDIVINEATTACK, Ingredients.SUPER_ATTACK_4, Ingredients.CRYSTAL_DUST.clone(4)),
			new Recipe(ItemID._1DOSEDIVINESTRENGTH, Ingredients.SUPER_STRENGTH_1, Ingredients.CRYSTAL_DUST),
			new Recipe(ItemID._2DOSEDIVINESTRENGTH, Ingredients.SUPER_STRENGTH_2, Ingredients.CRYSTAL_DUST.clone(2)),
			new Recipe(ItemID._3DOSEDIVINESTRENGTH, Ingredients.SUPER_STRENGTH_3, Ingredients.CRYSTAL_DUST.clone(3)),
			new Recipe(ItemID._4DOSEDIVINESTRENGTH, Ingredients.SUPER_STRENGTH_4, Ingredients.CRYSTAL_DUST.clone(4)),
			new Recipe(ItemID._1DOSEDIVINEDEFENCE, Ingredients.SUPER_DEFENCE_1, Ingredients.CRYSTAL_DUST),
			new Recipe(ItemID._2DOSEDIVINEDEFENCE, Ingredients.SUPER_DEFENCE_2, Ingredients.CRYSTAL_DUST.clone(2)),
			new Recipe(ItemID._3DOSEDIVINEDEFENCE, Ingredients.SUPER_DEFENCE_3, Ingredients.CRYSTAL_DUST.clone(3)),
			new Recipe(ItemID._4DOSEDIVINEDEFENCE, Ingredients.SUPER_DEFENCE_4, Ingredients.CRYSTAL_DUST.clone(4)),
			new Recipe(ItemID._1DOSEDIVINERANGE, Ingredients.RANGING_1, Ingredients.CRYSTAL_DUST),
			new Recipe(ItemID._2DOSEDIVINERANGE, Ingredients.RANGING_2, Ingredients.CRYSTAL_DUST.clone(2)),
			new Recipe(ItemID._3DOSEDIVINERANGE, Ingredients.RANGING_3, Ingredients.CRYSTAL_DUST.clone(3)),
			new Recipe(ItemID._4DOSEDIVINERANGE, Ingredients.RANGING_4, Ingredients.CRYSTAL_DUST.clone(4)),
			new Recipe(ItemID._1DOSEDIVINEMAGIC, Ingredients.MAGIC_1, Ingredients.CRYSTAL_DUST),
			new Recipe(ItemID._2DOSEDIVINEMAGIC, Ingredients.MAGIC_2, Ingredients.CRYSTAL_DUST.clone(2)),
			new Recipe(ItemID._3DOSEDIVINEMAGIC, Ingredients.MAGIC_3, Ingredients.CRYSTAL_DUST.clone(3)),
			new Recipe(ItemID._4DOSEDIVINEMAGIC, Ingredients.MAGIC_4, Ingredients.CRYSTAL_DUST.clone(4)),
			new Recipe(ItemID._1DOSEDIVINEBASTION, Ingredients.BASTION_1, Ingredients.CRYSTAL_DUST),
			new Recipe(ItemID._2DOSEDIVINEBASTION, Ingredients.BASTION_2, Ingredients.CRYSTAL_DUST.clone(2)),
			new Recipe(ItemID._3DOSEDIVINEBASTION, Ingredients.BASTION_3, Ingredients.CRYSTAL_DUST.clone(3)),
			new Recipe(ItemID._4DOSEDIVINEBASTION, Ingredients.BASTION_4, Ingredients.CRYSTAL_DUST.clone(4)),
			new Recipe(ItemID._1DOSEDIVINEBATTLEMAGE, Ingredients.BATTLEMAGE_1, Ingredients.CRYSTAL_DUST),
			new Recipe(ItemID._2DOSEDIVINEBATTLEMAGE, Ingredients.BATTLEMAGE_2, Ingredients.CRYSTAL_DUST.clone(2)),
			new Recipe(ItemID._3DOSEDIVINEBATTLEMAGE, Ingredients.BATTLEMAGE_3, Ingredients.CRYSTAL_DUST.clone(3)),
			new Recipe(ItemID._4DOSEDIVINEBATTLEMAGE, Ingredients.BATTLEMAGE_4, Ingredients.CRYSTAL_DUST.clone(4)),
			new Recipe(ItemID._1DOSEDIVINECOMBAT, Ingredients.SUPER_COMBAT_1, Ingredients.CRYSTAL_DUST),
			new Recipe(ItemID._2DOSEDIVINECOMBAT, Ingredients.SUPER_COMBAT_2, Ingredients.CRYSTAL_DUST.clone(2)),
			new Recipe(ItemID._3DOSEDIVINECOMBAT, Ingredients.SUPER_COMBAT_3, Ingredients.CRYSTAL_DUST.clone(3)),
			new Recipe(ItemID._4DOSEDIVINECOMBAT, Ingredients.SUPER_COMBAT_4, Ingredients.CRYSTAL_DUST.clone(4)),
			// These potions are more correctly classified as unfinished, however they take 2 ticks to make
			new Recipe(ItemID.UNFINISHED_ANTIDOTE_, Ingredients.COCONUT_MILK, Ingredients.TOADFLAX),
			new Recipe(ItemID.UNFINISHED_ANTIDOTE__, Ingredients.COCONUT_MILK, Ingredients.IRIT_LEAF),
			new Recipe(ItemID.UNFINISHED_WEAPON_POISON_, Ingredients.COCONUT_MILK, Ingredients.CACTUS_SPINE),
			new Recipe(ItemID.UNFINISHED_WEAPON_POISON__, Ingredients.COCONUT_MILK, Ingredients.CAVE_NIGHTSHADE)
			// @formatter:on
	};
	Recipe[] POTIONS_3T = {
			new Recipe(ItemID.EXTENDED_ANTIVENOM_1,  Ingredients.ANTIVENOM_1, Ingredients.ARAXYTE_VENOM_SACK.clone(1)),
			new Recipe(ItemID.EXTENDED_ANTIVENOM_2,  Ingredients.ANTIVENOM_2, Ingredients.ARAXYTE_VENOM_SACK.clone(2)),
			new Recipe(ItemID.EXTENDED_ANTIVENOM_3,  Ingredients.ANTIVENOM_3, Ingredients.ARAXYTE_VENOM_SACK.clone(3)),
			new Recipe(ItemID.EXTENDED_ANTIVENOM_4,  Ingredients.ANTIVENOM_4, Ingredients.ARAXYTE_VENOM_SACK.clone(4)),
	};
	int[] GRIMY_HERBS = {
			ItemID.UNIDENTIFIED_GUAM, ItemID.UNIDENTIFIED_MARENTILL, ItemID.UNIDENTIFIED_TARROMIN, ItemID.UNIDENTIFIED_HARRALANDER,
			ItemID.UNIDENTIFIED_RANARR, ItemID.UNIDENTIFIED_TOADFLAX, ItemID.UNIDENTIFIED_IRIT, ItemID.UNIDENTIFIED_AVANTOE,
			ItemID.UNIDENTIFIED_KWUARM, ItemID.UNIDENTIFIED_SNAPDRAGON, ItemID.UNIDENTIFIED_CADANTINE, ItemID.UNIDENTIFIED_LANTADYME,
			ItemID.UNIDENTIFIED_DWARF_WEED, ItemID.UNIDENTIFIED_TORSTOL, ItemID.UNIDENTIFIED_ARDRIGAL, ItemID.UNIDENTIFIED_SITO_FOIL,
			ItemID.UNIDENTIFIED_ROGUES_PURSE, ItemID.UNIDENTIFIED_SNAKE_WEED, ItemID.UNIDENTIFIED_VOLENCIA_MOSS, ItemID.RAIDS_GRIMY_BUCHULEAF,
			ItemID.RAIDS_GRIMY_NOXIFER, ItemID.RAIDS_GRIMY_GOLPAR
	};

	interface HerbTeaMix
	{

		int HERB_TEA_MIX_H = ItemID.FAVOUR_CUP_HARRALANDER;
		int HERB_TEA_MIX_G = ItemID.FAVOUR_CUP_GUAM;
		int HERB_TEA_MIX_M = ItemID.FAVOUR_CUP_MARRENTILL;
		int HERB_TEA_MIX_HM = ItemID.FAVOUR_CUP_HARRALANDER_MARRENTILL;
		int HERB_TEA_MIX_HG = ItemID.FAVOUR_CUP_HARRALANDER_GUAM;
		int HERB_TEA_MIX_GG = ItemID.FAVOUR_CUP_GUAM_GUAM;
		int HERB_TEA_MIX_GM = ItemID.FAVOUR_CUP_GUAM_MARRENTILL;
		int HERB_TEA_MIX_HMG = ItemID.FAVOUR_CUP_HARRALANDER_MARRENTILL_GUAM;
		int HERB_TEA_MIX_GGM = ItemID.FAVOUR_CUP_GUAM_GUAM_MARRENTILL;
		int HERB_TEA_MIX_GGH = ItemID.FAVOUR_CUP_GUAM_GUAM_HARRALANDER;

	}

	interface SanfewSerum
	{

		int MIX_1_1 = ItemID.SANFEW_SALVE_STEP_1_1_DOSE;
		int MIX_1_2 = ItemID.SANFEW_SALVE_STEP_1_2_DOSE;
		int MIX_1_3 = ItemID.SANFEW_SALVE_STEP_1_3_DOSE;
		int MIX_1_4 = ItemID.SANFEW_SALVE_STEP_1_4_DOSE;
		int MIX_2_1 = ItemID.SANFEW_SALVE_STEP_2_1_DOSE;
		int MIX_2_2 = ItemID.SANFEW_SALVE_STEP_2_2_DOSE;
		int MIX_2_3 = ItemID.SANFEW_SALVE_STEP_2_3_DOSE;
		int MIX_2_4 = ItemID.SANFEW_SALVE_STEP_2_4_DOSE;

	}

	interface Ingredients
	{

		// Base
		Ingredient VIAL_OF_WATER = new Ingredient(ItemID.VIAL_WATER);
		Ingredient VIAL_OF_BLOOD = new Ingredient(ItemID.MYQ4_BLOOD_VIAL);
		Ingredient COCONUT_MILK = new Ingredient(ItemID.VIAL_COCONUT_MILK);
		Ingredient CUP_OF_HOT_WATER = new Ingredient(ItemID.CUP_HOT_WATER);
		Ingredient GUAM_UNF = new Ingredient(ItemID.GUAMVIAL);
		Ingredient ROGUES_PURSE_UNF = new Ingredient(ItemID.ROGUES_PURSE_SOL);
		Ingredient MARRENTILL_UNF = new Ingredient(ItemID.MARRENTILLVIAL);
		Ingredient TARROMIN_UNF = new Ingredient(ItemID.TARROMINVIAL);
		Ingredient ASH_POTION = new Ingredient(ItemID.ASHESVIAL);
		Ingredient HARRALANDER_UNF = new Ingredient(ItemID.HARRALANDERVIAL);
		Ingredient MAGIC_ESSENCE_UNF = new Ingredient(ItemID.FAIRYTALE2_STARFLOWERVIAL);
		Ingredient RANARR_UNF = new Ingredient(ItemID.RANARRVIAL);
		Ingredient TOADFLAX_UNF = new Ingredient(ItemID.TOADFLAXVIAL);
		Ingredient IRIT_UNF = new Ingredient(ItemID.IRITVIAL);
		Ingredient AVANTOE_UNF = new Ingredient(ItemID.AVANTOEVIAL);
		Ingredient HUASCA_UNF = new Ingredient(ItemID.HUASCAVIAL);
		Ingredient KWUARM_UNF = new Ingredient(ItemID.KWUARMVIAL);
		Ingredient SNAPDRAGON_UNF = new Ingredient(ItemID.SNAPDRAGONVIAL);
		Ingredient CADANTINE_UNF = new Ingredient(ItemID.CADANTINEVIAL);
		Ingredient LANTADYME_UNF = new Ingredient(ItemID.LANTADYMEVIAL);
		Ingredient DWARF_WEED_UNF = new Ingredient(ItemID.DWARFWEEDVIAL);
		Ingredient TORSTOL_UNF = new Ingredient(ItemID.TORSTOLVIAL);
		Ingredient CADANTINE_BLOOD_UNF = new Ingredient(ItemID.CADANTINE_BLOODVIAL);
		Ingredient ELKHORN_UNF = new Ingredient(ItemID.ELKHORNVIAL);
		Ingredient PILLAR_UNF = new Ingredient(ItemID.PILLARVIAL);
		Ingredient UMBRAL_UNF = new Ingredient(ItemID.UMBRALVIAL);
		// Combination base
		Ingredient RESTORE_1 = new Ingredient(ItemID._1DOSESTATRESTORE);
		Ingredient RESTORE_2 = new Ingredient(ItemID._2DOSESTATRESTORE);
		Ingredient RESTORE_3 = new Ingredient(ItemID._3DOSESTATRESTORE);
		Ingredient RESTORE_4 = new Ingredient(ItemID._4DOSESTATRESTORE);
		Ingredient SUPER_RESTORE_1 = new Ingredient(ItemID._1DOSE2RESTORE);
		Ingredient SUPER_RESTORE_2 = new Ingredient(ItemID._2DOSE2RESTORE);
		Ingredient SUPER_RESTORE_3 = new Ingredient(ItemID._3DOSE2RESTORE);
		Ingredient SUPER_RESTORE_4 = new Ingredient(ItemID._4DOSE2RESTORE);
		Ingredient GUTHIX_BALANCE_UNF_1 = new Ingredient(ItemID.BURGH_UNFINISHED_GUTHIX_BALANCE_1);
		Ingredient GUTHIX_BALANCE_UNF_2 = new Ingredient(ItemID.BURGH_UNFINISHED_GUTHIX_BALANCE_2);
		Ingredient GUTHIX_BALANCE_UNF_3 = new Ingredient(ItemID.BURGH_UNFINISHED_GUTHIX_BALANCE_3);
		Ingredient GUTHIX_BALANCE_UNF_4 = new Ingredient(ItemID.BURGH_UNFINISHED_GUTHIX_BALANCE_4);
		Ingredient SUPER_ENERGY_1 = new Ingredient(ItemID._1DOSE2ENERGY);
		Ingredient SUPER_ENERGY_2 = new Ingredient(ItemID._2DOSE2ENERGY);
		Ingredient SUPER_ENERGY_3 = new Ingredient(ItemID._3DOSE2ENERGY);
		Ingredient SUPER_ENERGY_4 = new Ingredient(ItemID._4DOSE2ENERGY);
		Ingredient WEAPON_POISON_P_UNF = new Ingredient(ItemID.UNFINISHED_WEAPON_POISON_);
		Ingredient WEAPON_POISON_PP_UNF = new Ingredient(ItemID.UNFINISHED_WEAPON_POISON__);
		Ingredient ANTIDOTE_P_UNF = new Ingredient(ItemID.UNFINISHED_ANTIDOTE_);
		Ingredient ANTIDOTE_PP_UNF = new Ingredient(ItemID.UNFINISHED_ANTIDOTE__);
		Ingredient ANTIFIRE_1 = new Ingredient(ItemID._1DOSE1ANTIDRAGON);
		Ingredient ANTIFIRE_2 = new Ingredient(ItemID._2DOSE1ANTIDRAGON);
		Ingredient ANTIFIRE_3 = new Ingredient(ItemID._3DOSE1ANTIDRAGON);
		Ingredient ANTIFIRE_4 = new Ingredient(ItemID._4DOSE1ANTIDRAGON);
		Ingredient SUPER_ANTIFIRE_1 = new Ingredient(ItemID._1DOSE3ANTIDRAGON);
		Ingredient SUPER_ANTIFIRE_2 = new Ingredient(ItemID._2DOSE3ANTIDRAGON);
		Ingredient SUPER_ANTIFIRE_3 = new Ingredient(ItemID._3DOSE3ANTIDRAGON);
		Ingredient SUPER_ANTIFIRE_4 = new Ingredient(ItemID._4DOSE3ANTIDRAGON);
		Ingredient ANTIVENOM_1 = new Ingredient(ItemID.ANTIVENOM1);
		Ingredient ANTIVENOM_2 = new Ingredient(ItemID.ANTIVENOM2);
		Ingredient ANTIVENOM_3 = new Ingredient(ItemID.ANTIVENOM3);
		Ingredient ANTIVENOM_4 = new Ingredient(ItemID.ANTIVENOM4);
		Ingredient ANTIDOTE_PP_1 = new Ingredient(ItemID.ANTIDOTE__1);
		Ingredient ANTIDOTE_PP_2 = new Ingredient(ItemID.ANTIDOTE__2);
		Ingredient ANTIDOTE_PP_3 = new Ingredient(ItemID.ANTIDOTE__3);
		Ingredient ANTIDOTE_PP_4 = new Ingredient(ItemID.ANTIDOTE__4);
		Ingredient SUPER_ATTACK_1 = new Ingredient(ItemID._1DOSE2ATTACK);
		Ingredient SUPER_ATTACK_2 = new Ingredient(ItemID._2DOSE2ATTACK);
		Ingredient SUPER_ATTACK_3 = new Ingredient(ItemID._3DOSE2ATTACK);
		Ingredient SUPER_ATTACK_4 = new Ingredient(ItemID._4DOSE2ATTACK);
		Ingredient SUPER_STRENGTH_1 = new Ingredient(ItemID._1DOSE2STRENGTH);
		Ingredient SUPER_STRENGTH_2 = new Ingredient(ItemID._2DOSE2STRENGTH);
		Ingredient SUPER_STRENGTH_3 = new Ingredient(ItemID._3DOSE2STRENGTH);
		Ingredient SUPER_STRENGTH_4 = new Ingredient(ItemID._4DOSE2STRENGTH);
		Ingredient SUPER_DEFENCE_1 = new Ingredient(ItemID._1DOSE2DEFENSE);
		Ingredient SUPER_DEFENCE_2 = new Ingredient(ItemID._2DOSE2DEFENSE);
		Ingredient SUPER_DEFENCE_3 = new Ingredient(ItemID._3DOSE2DEFENSE);
		Ingredient SUPER_DEFENCE_4 = new Ingredient(ItemID._4DOSE2DEFENSE);
		Ingredient RANGING_1 = new Ingredient(ItemID._1DOSERANGERSPOTION);
		Ingredient RANGING_2 = new Ingredient(ItemID._2DOSERANGERSPOTION);
		Ingredient RANGING_3 = new Ingredient(ItemID._3DOSERANGERSPOTION);
		Ingredient RANGING_4 = new Ingredient(ItemID._4DOSERANGERSPOTION);
		Ingredient MAGIC_1 = new Ingredient(ItemID._1DOSE1MAGIC);
		Ingredient MAGIC_2 = new Ingredient(ItemID._2DOSE1MAGIC);
		Ingredient MAGIC_3 = new Ingredient(ItemID._3DOSE1MAGIC);
		Ingredient MAGIC_4 = new Ingredient(ItemID._4DOSE1MAGIC);
		Ingredient BASTION_1 = new Ingredient(ItemID._1DOSEBASTION);
		Ingredient BASTION_2 = new Ingredient(ItemID._2DOSEBASTION);
		Ingredient BASTION_3 = new Ingredient(ItemID._3DOSEBASTION);
		Ingredient BASTION_4 = new Ingredient(ItemID._4DOSEBASTION);
		Ingredient BATTLEMAGE_1 = new Ingredient(ItemID._1DOSEBATTLEMAGE);
		Ingredient BATTLEMAGE_2 = new Ingredient(ItemID._2DOSEBATTLEMAGE);
		Ingredient BATTLEMAGE_3 = new Ingredient(ItemID._3DOSEBATTLEMAGE);
		Ingredient BATTLEMAGE_4 = new Ingredient(ItemID._4DOSEBATTLEMAGE);
		Ingredient STAMINA_1 = new Ingredient(ItemID._1DOSESTAMINA);
		Ingredient STAMINA_2 = new Ingredient(ItemID._2DOSESTAMINA);
		Ingredient STAMINA_3 = new Ingredient(ItemID._3DOSESTAMINA);
		Ingredient STAMINA_4 = new Ingredient(ItemID._4DOSESTAMINA);
		Ingredient SUPER_COMBAT_1 = new Ingredient(ItemID._1DOSE2COMBAT);
		Ingredient SUPER_COMBAT_2 = new Ingredient(ItemID._2DOSE2COMBAT);
		Ingredient SUPER_COMBAT_3 = new Ingredient(ItemID._3DOSE2COMBAT);
		Ingredient SUPER_COMBAT_4 = new Ingredient(ItemID._4DOSE2COMBAT);
		// Sanfew serum mixtures
		Ingredient MIXTURE_STEP_1_1 = new Ingredient(ItemID.SANFEW_SALVE_STEP_1_1_DOSE);
		Ingredient MIXTURE_STEP_1_2 = new Ingredient(ItemID.SANFEW_SALVE_STEP_1_2_DOSE);
		Ingredient MIXTURE_STEP_1_3 = new Ingredient(ItemID.SANFEW_SALVE_STEP_1_3_DOSE);
		Ingredient MIXTURE_STEP_1_4 = new Ingredient(ItemID.SANFEW_SALVE_STEP_1_4_DOSE);
		Ingredient MIXTURE_STEP_2_1 = new Ingredient(ItemID.SANFEW_SALVE_STEP_2_1_DOSE);
		Ingredient MIXTURE_STEP_2_2 = new Ingredient(ItemID.SANFEW_SALVE_STEP_2_2_DOSE);
		Ingredient MIXTURE_STEP_2_3 = new Ingredient(ItemID.SANFEW_SALVE_STEP_2_3_DOSE);
		Ingredient MIXTURE_STEP_2_4 = new Ingredient(ItemID.SANFEW_SALVE_STEP_2_4_DOSE);
		// Guthix rest tea mixtures
		Ingredient HERB_TEA_MIX_H = new Ingredient(ItemID.FAVOUR_CUP_HARRALANDER);
		Ingredient HERB_TEA_MIX_G = new Ingredient(ItemID.FAVOUR_CUP_GUAM);
		Ingredient HERB_TEA_MIX_M = new Ingredient(ItemID.FAVOUR_CUP_MARRENTILL);
		Ingredient HERB_TEA_MIX_HM = new Ingredient(ItemID.FAVOUR_CUP_HARRALANDER_MARRENTILL);
		Ingredient HERB_TEA_MIX_HG = new Ingredient(ItemID.FAVOUR_CUP_HARRALANDER_GUAM);
		Ingredient HERB_TEA_MIX_GG = new Ingredient(ItemID.FAVOUR_CUP_GUAM_GUAM);
		Ingredient HERB_TEA_MIX_GM = new Ingredient(ItemID.FAVOUR_CUP_GUAM_MARRENTILL);
		Ingredient HERB_TEA_MIX_HMG = new Ingredient(ItemID.FAVOUR_CUP_HARRALANDER_MARRENTILL_GUAM);
		Ingredient HERB_TEA_MIX_GGM = new Ingredient(ItemID.FAVOUR_CUP_GUAM_GUAM_MARRENTILL);
		Ingredient HERB_TEA_MIX_GGH = new Ingredient(ItemID.FAVOUR_CUP_GUAM_GUAM_HARRALANDER);
		// Herbs
		Ingredient GUAM_LEAF = new Ingredient(ItemID.GUAM_LEAF);
		Ingredient ROGUES_PURSE = new Ingredient(ItemID.ROGUES_PURSE);
		Ingredient MARRENTILL = new Ingredient(ItemID.MARENTILL);
		Ingredient TARROMIN = new Ingredient(ItemID.TARROMIN);
		Ingredient HARRALANDER = new Ingredient(ItemID.HARRALANDER);
		Ingredient STAR_FLOWER = new Ingredient(ItemID.FAIRYTALE2_FLOWER_HERBS);
		Ingredient RANARR_WEED = new Ingredient(ItemID.RANARR_WEED);
		Ingredient TOADFLAX = new Ingredient(ItemID.TOADFLAX);
		Ingredient IRIT_LEAF = new Ingredient(ItemID.IRIT_LEAF);
		Ingredient AVANTOE = new Ingredient(ItemID.AVANTOE);
		Ingredient KWUARM = new Ingredient(ItemID.KWUARM);
		Ingredient SNAPDRAGON = new Ingredient(ItemID.SNAPDRAGON);
		Ingredient CADANTINE = new Ingredient(ItemID.CADANTINE);
		Ingredient LANTADYME = new Ingredient(ItemID.LANTADYME);
		Ingredient DWARF_WEED = new Ingredient(ItemID.DWARF_WEED);
		Ingredient TORSTOL = new Ingredient(ItemID.TORSTOL);
		// Secondaries
		Ingredient GARLIC = new Ingredient(ItemID.GARLIC);
		Ingredient EYE_OF_NEWT = new Ingredient(ItemID.EYE_OF_NEWT);
		Ingredient UNICORN_HORN_DUST = new Ingredient(ItemID.UNICORN_HORN_DUST);
		Ingredient SNAKE_WEED = new Ingredient(ItemID.SNAKE_WEED);
		Ingredient LIMPWURT_ROOT = new Ingredient(ItemID.LIMPWURT_ROOT);
		Ingredient ASHES = new Ingredient(ItemID.ASHES);
		Ingredient VOLCANIC_ASH = new Ingredient(ItemID.FOSSIL_VOLCANIC_ASH);
		Ingredient RED_SPIDERS_EGGS = new Ingredient(ItemID.RED_SPIDERS_EGGS);
		Ingredient SILVER_DUST = new Ingredient(ItemID.SILVER_DUST);
		Ingredient BLAMISH_SNAIL_SLIME = new Ingredient(ItemID.BLAMISH_SNAIL_SLIME);
		Ingredient CHOCOLATE_DUST = new Ingredient(ItemID.CHOCOLATE_DUST);
		Ingredient WHITE_BERRIES = new Ingredient(ItemID.WHITE_BERRIES);
		Ingredient TOADS_LEGS = new Ingredient(ItemID.TOADS_LEGS);
		Ingredient GOAT_HORN_DUST = new Ingredient(ItemID.GROUND_DESERT_GOAT_HORN);
		Ingredient SNAPE_GRASS = new Ingredient(ItemID.SNAPE_GRASS);
		Ingredient PHARMAKOS_BERRIES = new Ingredient(ItemID.LOTG_PHARMAKOS_BERRY);
		Ingredient MORT_MYRE_FUNGUS = new Ingredient(ItemID.MORTMYREMUSHROOM);
		Ingredient SHRUNK_OGLEROOT = new Ingredient(ItemID.GRIM_TURNIP);
		Ingredient KEBBIT_TEETH_DUST = new Ingredient(ItemID.HUNTINGBEAST_SABRETEETH_DUST);
		Ingredient ALDARIUM = new Ingredient(ItemID.ALDARIUM);
		Ingredient GORAK_CLAW_POWDER = new Ingredient(ItemID.FAIRYTALE2_GROUND_GORAK_CLAWS);
		Ingredient CRAB_PASTE = new Ingredient(ItemID.CRAB_PASTE);
		Ingredient SQUID_PASTE = new Ingredient(ItemID.SQUID_PASTE);
		Ingredient COTTON_YARN = new Ingredient(ItemID.COTTON_YARN);
		Ingredient HADDOCK_EYE = new Ingredient(ItemID.HADDOCK_EYE);
		Ingredient HAEMOSTATIC_POULTICE = new Ingredient(ItemID.HAEMOSTATIC_POULTICE);
		Ingredient DRAGON_SCALE_DUST = new Ingredient(ItemID.DRAGON_SCALE_DUST);
		Ingredient NAIL_BEAST_NAILS = new Ingredient(ItemID.NAIL_BEAST_NAIL);
		Ingredient YEW_ROOTS = new Ingredient(ItemID.YEW_ROOTS);
		Ingredient WINE_OF_ZAMORAK = new Ingredient(ItemID.WINE_OF_ZAMORAK);
		Ingredient POTATO_CACTUS = new Ingredient(ItemID.CACTUS_POTATO);
		Ingredient JANGERBERRIES = new Ingredient(ItemID.JANGERBERRIES);
		Ingredient MAGIC_ROOTS = new Ingredient(ItemID.MAGIC_ROOTS);
		Ingredient CRUSHED_NEST = new Ingredient(ItemID.CRUSHED_BIRD_NEST);
		Ingredient DEMONIC_TALLOW = new Ingredient(ItemID.DEMONIC_TALLOW);
		Ingredient POISON_IVY_BERRIES = new Ingredient(ItemID.POISONIVY_BERRIES);
		Ingredient NIHIL_DUST = new Ingredient(ItemID.NIHIL_DUST);
		Ingredient LAVA_SCALE_SHARD = new Ingredient(ItemID.LAVA_SHARD);
		Ingredient ARAXYTE_VENOM_SACK = new Ingredient(ItemID.ARAXYTE_VENOM_SACK);
		Ingredient CRYSTAL_DUST = new Ingredient(ItemID.SOTE_CRYSTAL_DUST);
		Ingredient CRUSHED_SUPERIOR_DRAGON_BONES = new Ingredient(ItemID.CRUSHED_DRAGON_BONES);
		Ingredient ZULRAHS_SCALES = new Ingredient(ItemID.SNAKEBOSS_SCALE);
		Ingredient CAVE_NIGHTSHADE = new Ingredient(ItemID.NIGHTSHADE);
		Ingredient AMYLASE_CRYSTAL = new Ingredient(ItemID.AMYLASE);
		Ingredient CACTUS_SPINE = new Ingredient(ItemID.CACTUS_SPINE);
		Ingredient ANCIENT_BREW_4 = new Ingredient(ItemID._4DOSEANCIENTBREW);
		Ingredient ANCIENT_ESSENCE = new Ingredient(ItemID.ANCIENT_ESSENCE);
		Ingredient MARLIN_SCALES = new Ingredient(ItemID.MARLIN_SCALES);
		Ingredient RAINBOW_CRAB_PASTE = new Ingredient(ItemID.RAINBOW_CRAB_PASTE);
	}

}
