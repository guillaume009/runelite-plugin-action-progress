package com.github.calebwhiting.runelite.data;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.Client;
import net.runelite.api.EnumComposition;
import net.runelite.api.EnumID;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.ItemID;
import org.apache.commons.lang3.ArrayUtils;
import net.runelite.api.Varbits;
import net.runelite.api.annotations.Varbit;

import java.util.Arrays;
import java.util.Objects;

public interface Magic
{

	@Getter
	enum StandardSpell implements Spell
	{
		LUMBRIDGE_HOME_TELEPORT(
				"Lumbridge Home Teleport",
				0,
				0
		),
		WIND_STRIKE(
				"Wind Strike",
				1,
				1,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.MIND, 1)
		),
		CONFUSE(
				"Confuse",
				2,
				3,
				new RuneRequirement(Rune.BODY, 1),
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.WATER, 3)
		),
		ENCHANT_CROSSBOW_BOLT(
				"Crossbow Bolt Enchantments",
				3,
				4
		),
		WATER_STRIKE(
				"Water Strike",
				4,
				5,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.MIND, 1),
				new RuneRequirement(Rune.WATER, 1)
		),
		LVL_1_ENCHANT(
				"Lvl-1 Enchant",
				6,
				7,
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.WATER, 1)
		),
		EARTH_STRIKE(
				"Earth Strike",
				7,
				9,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.MIND, 1)
		),
		WEAKEN(
				"Weaken",
				8,
				11,
				new RuneRequirement(Rune.BODY, 1),
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.WATER, 3)
		),
		FIRE_STRIKE(
				"Fire Strike",
				9,
				13,
				new RuneRequirement(Rune.AIR, 2),
				new RuneRequirement(Rune.FIRE, 3),
				new RuneRequirement(Rune.MIND, 1)
		),
		BONES_TO_BANANAS(
				"Bones to Bananas",
				10,
				15,
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.NATURE, 1),
				new RuneRequirement(Rune.WATER, 2)
		),
		WIND_BOLT(
				"Wind Bolt",
				11,
				17,
				new RuneRequirement(Rune.AIR, 2),
				new RuneRequirement(Rune.CHAOS, 1)
		),
		CURSE(
				"Curse",
				12,
				19,
				new RuneRequirement(Rune.BODY, 1),
				new RuneRequirement(Rune.EARTH, 3),
				new RuneRequirement(Rune.WATER, 2)
		),
		BIND(
				"Bind",
				13,
				20,
				new RuneRequirement(Rune.EARTH, 3),
				new RuneRequirement(Rune.NATURE, 2),
				new RuneRequirement(Rune.WATER, 3)
		),
		LOW_LEVEL_ALCHEMY(
				"Low Level Alchemy",
				14,
				21,
				new RuneRequirement(Rune.FIRE, 3),
				new RuneRequirement(Rune.NATURE, 1)
		),
		WATER_BOLT(
				"Water Bolt",
				15,
				23,
				new RuneRequirement(Rune.AIR, 2),
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.WATER, 2)
		),
		VARROCK_TELEPORT(
				"Varrock Teleport",
				16,
				25,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.FIRE, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		LVL_2_ENCHANT(
				"Lvl-2 Enchant",
				17,
				27,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.COSMIC, 1)
		),
		EARTH_BOLT(
				"Earth Bolt",
				18,
				29,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.EARTH, 1)
		),
		LUMBRIDGE_TELEPORT(
				"Lumbridge Teleport",
				19,
				31,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.EARTH, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		TELEKINETIC_GRAB(
				"Telekinetic Grab",
				20,
				33,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		FIRE_BOLT(
				"Fire Bolt",
				21,
				35,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.FIRE, 4)
		),
		FALADOR_TELEPORT(
				"Falador Teleport",
				22,
				37,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.LAW, 1),
				new RuneRequirement(Rune.WATER, 1)
		),
		CRUMBLE_UNDEAD(
				"Crumble Undead",
				23,
				39,
				new RuneRequirement(Rune.AIR, 2),
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.EARTH, 2)
		),
		TELEPORT_TO_HOUSE(
				"Teleport to House",
				24,
				40,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.EARTH, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		WIND_BLAST(
				"Wind Blast",
				25,
				41,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.DEATH, 1)
		),
		SUPERHEAT_ITEM(
				"Superheat Item",
				26,
				43,
				new RuneRequirement(Rune.FIRE, 4),
				new RuneRequirement(Rune.NATURE, 1)
		),
		CAMELOT_TELEPORT(
				"Camelot Teleport",
				27,
				45,
				new RuneRequirement(Rune.AIR, 5),
				new RuneRequirement(Rune.LAW, 1)
		),
		WATER_BLAST(
				"Water Blast",
				28,
				47,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.WATER, 3)
		),
		KOUREND_CASTLE_TELEPORT(
				"Kourend Castle Teleport",
				29,
				48,
				new RuneRequirement(Rune.FIRE, 5),
				new RuneRequirement(Rune.LAW, 2),
				new RuneRequirement(Rune.SOUL, 2),
				new RuneRequirement(Rune.WATER, 4)
		),
		LVL_3_ENCHANT(
				"Lvl-3 Enchant",
				30,
				49,
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.FIRE, 5)
		),
		IBAN_BLAST(
				"Iban Blast",
				31,
				50,
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.FIRE, 5)
		),
		SNARE(
				"Snare",
				32,
				50,
				new RuneRequirement(Rune.EARTH, 4),
				new RuneRequirement(Rune.NATURE, 3),
				new RuneRequirement(Rune.WATER, 4)
		),
		MAGIC_DART(
				"Magic Dart",
				33,
				50,
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.MIND, 4)
		),
		ARDOUGNE_TELEPORT(
				"Ardougne Teleport",
				34,
				51,
				new RuneRequirement(Rune.LAW, 2),
				new RuneRequirement(Rune.WATER, 2)
		),
		EARTH_BLAST(
				"Earth Blast",
				35,
				53,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.EARTH, 4)
		),
		HIGH_LEVEL_ALCHEMY(
				"High Level Alchemy",
				34,
				55,
				new RuneRequirement(Rune.FIRE, 5),
				new RuneRequirement(Rune.NATURE, 1)
		),
		CHARGE_WATER_ORB(
				"Charge Water Orb",
				38,
				56,
				new RuneRequirement(Rune.COSMIC, 3),
				new RuneRequirement(Rune.WATER, 30)
		),
		LVL_4_ENCHANT(
				"Lvl-4 Enchant",
				39,
				57,
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.EARTH, 10)
		),
		WATCHTOWER_TELEPORT(
				"Watchtower Teleport",
				40,
				58,
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.LAW, 2)
		),
		FIRE_BLAST(
				"Fire Blast",
				41,
				59,
				new RuneRequirement(Rune.AIR, 4),
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.FIRE, 5)
		),
		CHARGE_EARTH_ORB(
				"Charge Earth Orb",
				42,
				60,
				new RuneRequirement(Rune.COSMIC, 3),
				new RuneRequirement(Rune.EARTH, 30)
		),
		BONES_TO_PEACHES(
				"Bones to Peaches",
				43,
				60,
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.NATURE, 2),
				new RuneRequirement(Rune.WATER, 4)
		),
		SARADOMIN_STRIKE(
				"Saradomin Strike",
				44,
				60,
				new RuneRequirement(Rune.AIR, 4),
				new RuneRequirement(Rune.BLOOD, 2),
				new RuneRequirement(Rune.FIRE, 2)
		),
		CLAWS_OF_GUTHIX(
				"Claws of Guthix",
				45,
				60,
				new RuneRequirement(Rune.AIR, 4),
				new RuneRequirement(Rune.BLOOD, 2),
				new RuneRequirement(Rune.FIRE, 1)
		),
		FLAMES_OF_ZAMORAK(
				"Flames of Zamorak",
				46,
				60,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.BLOOD, 2),
				new RuneRequirement(Rune.FIRE, 4)
		),
		TROLLHEIM_TELEPORT(
				"Trollheim Teleport",
				47,
				61,
				new RuneRequirement(Rune.FIRE, 2),
				new RuneRequirement(Rune.LAW, 2)
		),
		WIND_WAVE(
				"Wind Wave",
				48,
				62,
				new RuneRequirement(Rune.AIR, 5),
				new RuneRequirement(Rune.BLOOD, 1)
		),
		CHARGE_FIRE_ORB(
				"Charge Fire Orb",
				49,
				63,
				new RuneRequirement(Rune.COSMIC, 3),
				new RuneRequirement(Rune.FIRE, 30)
		),
		APE_ATOLL_TELEPORT(
				"Ape Atoll Teleport",
				50,
				64,
				new RuneRequirement(Rune.FIRE, 2),
				new RuneRequirement(Rune.LAW, 2),
				new RuneRequirement(Rune.WATER, 2)
		),
		WATER_WAVE(
				"Water Wave",
				51,
				65,
				new RuneRequirement(Rune.AIR, 5),
				new RuneRequirement(Rune.BLOOD, 1),
				new RuneRequirement(Rune.WATER, 7)
		),
		CHARGE_AIR_ORB(
				"Charge Air Orb",
				52,
				66,
				new RuneRequirement(Rune.AIR, 30),
				new RuneRequirement(Rune.COSMIC, 3)
		),
		VULNERABILITY(
				"Vulnerability",
				53,
				66,
				new RuneRequirement(Rune.EARTH, 5),
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.WATER, 5)
		),
		LVL_5_ENCHANT(
				"Lvl-5 Enchant",
				54,
				68,
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.EARTH, 15),
				new RuneRequirement(Rune.WATER, 15)
		),
		EARTH_WAVE(
				"Earth Wave",
				55,
				70,
				new RuneRequirement(Rune.AIR, 5),
				new RuneRequirement(Rune.BLOOD, 1),
				new RuneRequirement(Rune.EARTH, 7)
		),
		ENFEEBLE(
				"Enfeeble",
				56,
				73,
				new RuneRequirement(Rune.EARTH, 8),
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.WATER, 8)
		),
		TELEOTHER_LUMBRIDGE(
				"Teleother Lumbridge",
				57,
				74,
				new RuneRequirement(Rune.EARTH, 1),
				new RuneRequirement(Rune.LAW, 1),
				new RuneRequirement(Rune.SOUL, 1)
		),
		FIRE_WAVE(
				"Fire Wave",
				58,
				75,
				new RuneRequirement(Rune.AIR, 5),
				new RuneRequirement(Rune.BLOOD, 1),
				new RuneRequirement(Rune.FIRE, 7)
		),
		ENTANGLE(
				"Entangle",
				59,
				79,
				new RuneRequirement(Rune.EARTH, 5),
				new RuneRequirement(Rune.NATURE, 4),
				new RuneRequirement(Rune.WATER, 5)
		),
		STUN(
				"Stun",
				60,
				80,
				new RuneRequirement(Rune.EARTH, 12),
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.WATER, 12)
		),
		CHARGE(
				"Charge",
				61,
				80,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.BLOOD, 3),
				new RuneRequirement(Rune.FIRE, 3)
		),
		WIND_SURGE(
				"Wind Surge",
				62,
				81,
				new RuneRequirement(Rune.AIR, 7),
				new RuneRequirement(Rune.WRATH, 1)
		),
		TELEOTHER_FALADOR(
				"Teleother Falador",
				63,
				82,
				new RuneRequirement(Rune.LAW, 1),
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.WATER, 1)
		),
		WATER_SURGE(
				"Water Surge",
				64,
				85,
				new RuneRequirement(Rune.AIR, 7),
				new RuneRequirement(Rune.WATER, 10),
				new RuneRequirement(Rune.WRATH, 1)
		),
		TELE_BLOCK(
				"Tele Block",
				65,
				85,
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		TELEPORT_TO_BOUNTY_TARGET(
				"Teleport to Bounty Target",
				66,
				85,
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		LVL_6_ENCHANT(
				"Lvl-6 Enchant",
				67,
				87,
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.EARTH, 20),
				new RuneRequirement(Rune.FIRE, 20)
		),
		TELEOTHER_CAMELOT(
				"Teleother Camelot",
				68,
				90,
				new RuneRequirement(Rune.LAW, 1),
				new RuneRequirement(Rune.SOUL, 2)
		),
		EARTH_SURGE(
				"Earth Surge",
				69,
				90,
				new RuneRequirement(Rune.AIR, 7),
				new RuneRequirement(Rune.EARTH, 10),
				new RuneRequirement(Rune.WRATH, 1)
		),
		LVL_7_ENCHANT(
				"Lvl-7 Enchant",
				70,
				93,
				new RuneRequirement(Rune.BLOOD, 20),
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.SOUL, 20)
		),
		FIRE_SURGE(
				"Fire Surge",
				71,
				95,
				new RuneRequirement(Rune.AIR, 7),
				new RuneRequirement(Rune.FIRE, 10),
				new RuneRequirement(Rune.WRATH, 1)
		);

		private static final int STANDARD_SPELLBOOK_GROUP_ID = 218;

		private static final int STANDARD_SPELLBOOK_FIRST_SPELL_INDEX = 7;

		private final String name;

		private final int index;

		private final int levelRequirement;

		private final RuneRequirement[] runeRequirements;

		StandardSpell(String name, int index, int levelRequirement, RuneRequirement... runeRequirements)
		{
			this.name = name;
			this.index = index;
			this.levelRequirement = levelRequirement;
			this.runeRequirements = runeRequirements;
		}

		@Override
		public int getGroupId()
		{
			return STANDARD_SPELLBOOK_GROUP_ID;
		}

		public int getChildId()
		{
			return STANDARD_SPELLBOOK_FIRST_SPELL_INDEX + this.getIndex();
		}
	}

	@Getter
	enum LunarSpell implements Spell
	{
		STRING_JEWELLERY(
				"String Jewellery",
				22,
				80,
				new RuneRequirement(Rune.EARTH, 10),
				new RuneRequirement(Rune.ASTRAL, 2),
				new RuneRequirement(Rune.WATER, 5)
		),
		PLANK_MAKE(
				"Plank Make",
				29,
				86,
				new RuneRequirement(Rune.EARTH, 15),
				new RuneRequirement(Rune.ASTRAL, 2),
				new RuneRequirement(Rune.NATURE, 1)
		);

		private static final int LUNAR_SPELLBOOK_GROUP_ID = 218;

		private static final int LUNAR_SPELLBOOK_FIRST_SPELL_INDEX = 104;

		private final String name;

		private final int index;

		private final int levelRequirement;

		private final RuneRequirement[] runeRequirements;

		LunarSpell(String name, int index, int levelRequirement, RuneRequirement... runeRequirements)
		{
			this.name = name;
			this.index = index;
			this.levelRequirement = levelRequirement;
			this.runeRequirements = runeRequirements;
		}

		@Override
		public int getGroupId()
		{
			return LUNAR_SPELLBOOK_GROUP_ID;
		}

		public int getChildId()
		{
			return LUNAR_SPELLBOOK_FIRST_SPELL_INDEX + this.getIndex();
		}
	}

	@Getter
	enum PlankMakeSpell
	{
		PLANK_MAKE(LunarSpell.PLANK_MAKE, ItemID.LOGS, 70),
		PLANK_MAKE_OAK(LunarSpell.PLANK_MAKE, ItemID.OAK_LOGS, 175),
		PLANK_MAKE_TEAK(LunarSpell.PLANK_MAKE, ItemID.TEAK_LOGS, 350),
		PLANK_MAKE_MAHOGANY(LunarSpell.PLANK_MAKE, ItemID.MAHOGANY_LOGS, 1050);

		private final Spell spell;

		private final int plank;

		private final int cost;

		PlankMakeSpell(Spell spell, int plank, int cost)
		{
			this.spell = spell;
			this.plank = plank;
			this.cost = cost;
		}
	}

	@Getter
	enum StringJewellerySpell
	{
		STRING_STRUNG_RABBIT_FOOT(LunarSpell.STRING_JEWELLERY, ItemID.RABBIT_FOOT),
		STRING_HOLY_SYMBOL(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_SYMBOL),
		STRING_UNHOLY_SYMBOL(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_EMBLEM),
		STRING_OPAL_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.OPAL_AMULET_U),
		STRING_JADE_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.JADE_AMULET_U),
		STRING_SAPPHIRE_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.SAPPHIRE_AMULET_U),
		STRING_TOPAZ_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.TOPAZ_AMULET_U),
		STRING_EMERALD_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.EMERALD_AMULET_U),
		STRING_RUBY_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.RUBY_AMULET_U),
		STRING_GOLD_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.GOLD_AMULET_U),
		STRING_DIAMOND_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.DIAMOND_AMULET_U),
		STRING_DRAGONSTONE_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.DRAGONSTONE_AMULET_U),
		STRING_ONYX_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.ONYX_AMULET_U),
		STRING_ZENYTE_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.ZENYTE_AMULET_U);

		private final Spell spell;

		private final int jewelleryItemId;

		StringJewellerySpell(Spell spell, int jewelleryItemId)
		{
			this.spell = spell;
			this.jewelleryItemId = jewelleryItemId;
		}
	}

	@Getter
	enum EnchantSpell
	{
		ENCHANT_SAPPHIRE(StandardSpell.LVL_1_ENCHANT, Crafting.SAPPHIRE_AND_OPAL_JEWELLERY),
		ENCHANT_EMERALD(StandardSpell.LVL_2_ENCHANT, Crafting.EMERALD_JEWELLERY),
		ENCHANT_RUBY(StandardSpell.LVL_3_ENCHANT, Crafting.TOPAZ_AND_RUBY_JEWELLERY),
		ENCHANT_DIAMOND(StandardSpell.LVL_4_ENCHANT, Crafting.DIAMOND_JEWELLERY),
		ENCHANT_DRAGONSTONE(StandardSpell.LVL_5_ENCHANT, Crafting.DRAGONSTONE_JEWELLERY),
		ENCHANT_ONYX(StandardSpell.LVL_6_ENCHANT, Crafting.ONYX_JEWELLERY),
		ENCHANT_ZENYTE(StandardSpell.LVL_7_ENCHANT, Crafting.ZENYTE_JEWELLERY);

		private final Spell spell;

		private final int[] jewellery;

		EnchantSpell(Spell spell, int[] jewellery)
		{
			this.spell = spell;
			this.jewellery = jewellery;
			Arrays.sort(jewellery);
		}
	}

	@RequiredArgsConstructor
	@Getter
	enum Rune
	{
		AIR("Air", RuneIDs.AIR.build(), StaveIDs.AIR.build()),
		WATER("Water", RuneIDs.WATER.build(), new IDs(StaveIDs.WATER, ItemID.TOME_OF_WATER).build()),
		EARTH("Earth", RuneIDs.EARTH.build(), new IDs(StaveIDs.EARTH, ItemID.TOME_OF_EARTH).build()),
		FIRE("Fire", RuneIDs.FIRE.build(), new IDs(StaveIDs.FIRE, ItemID.TOME_OF_FIRE).build()),
		MIND("Mind", RuneIDs.MIND.build()),
		BODY("Body", RuneIDs.BODY.build()),
		COSMIC("Cosmic", RuneIDs.COSMIC.build()),
		CHAOS("Chaos", RuneIDs.CHAOS.build()),
		ASTRAL("Astral", RuneIDs.ASTRAL.build()),
		NATURE("Nature", RuneIDs.NATURE.build()),
		LAW("Law", RuneIDs.LAW.build()),
		DEATH("Death", RuneIDs.DEATH.build()),
		BLOOD("Blood", RuneIDs.BLOOD.build()),
		SOUL("Soul", RuneIDs.SOUL.build()),
		WRATH("Wrath", RuneIDs.WRATH.build());

		private final String name;

		private final int[] runeIds;

		private final int[] unlimitedSources;

		private static final int RUNE_POUCH_NUM_SLOTS = 4;

		private static final int[] AMOUNT_VARBITS = {
			Varbits.RUNE_POUCH_AMOUNT1, Varbits.RUNE_POUCH_AMOUNT2, Varbits.RUNE_POUCH_AMOUNT3, Varbits.RUNE_POUCH_AMOUNT4
		};

		private static final int[] RUNE_VARBITS =
		{
			Varbits.RUNE_POUCH_RUNE1, Varbits.RUNE_POUCH_RUNE2, Varbits.RUNE_POUCH_RUNE3, Varbits.RUNE_POUCH_RUNE4
		};

		Rune(String name, int... runeId)
		{
			this(name, runeId, ArrayUtils.EMPTY_INT_ARRAY);
		}

		public int countAvailable(Client client)
		{
			for (int itemId : this.unlimitedSources) {
				ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);
				if (equipment != null && equipment.contains(itemId)) {
					return Integer.MAX_VALUE;
				}
			}

			EnumComposition runepouchEnum = client.getEnum(EnumID.RUNEPOUCH_RUNE);
			int count = 0;
			
			for (int itemId : this.runeIds) {				
				for (int i = 0; i < RUNE_POUCH_NUM_SLOTS; i++){
					@Varbit int runeVarbit = RUNE_VARBITS[i];
					int runepouchRuneId = client.getVarbitValue(runeVarbit);
					int runeId = runepouchEnum.getIntValue(runepouchRuneId);
					if (runeId == itemId){
						@Varbit int amountVarbit = AMOUNT_VARBITS[i];
						int amount = client.getVarbitValue(amountVarbit);
						count += amount;
					}
				}
				
				ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
				count += inventory == null ? 0 : inventory.count(itemId);
			}
			return count;
		}
	}

	@Getter
	@RequiredArgsConstructor
	enum LecternSpell
	{
		ENCHANT_ONYX(ItemID.ENCHANT_ONYX, "Enchant onyx", StandardSpell.LVL_6_ENCHANT),
		LUMBRIDGE_TELEPORT(ItemID.LUMBRIDGE_TELEPORT, "Lumbridge teleport", StandardSpell.LUMBRIDGE_TELEPORT),
		ENCHANT_DIAMOND(ItemID.ENCHANT_DIAMOND, "Enchant diamond", StandardSpell.LVL_4_ENCHANT),
		WATCHTOWER_TELEPORT(ItemID.WATCHTOWER_TELEPORT, "Watchtower teleport", StandardSpell.WATCHTOWER_TELEPORT),
		HOUSE_TELEPORT(ItemID.TELEPORT_TO_HOUSE, "Teleport to house", StandardSpell.TELEPORT_TO_HOUSE),
		ENCHANT_EMERALD(ItemID.ENCHANT_EMERALD_OR_JADE, "Enchant emerald", StandardSpell.LVL_2_ENCHANT),
		ENCHANT_SAPPHIRE(ItemID.ENCHANT_SAPPHIRE_OR_OPAL, "Enchant sapphire", StandardSpell.LVL_1_ENCHANT),
		FALADOR_TELEPORT(ItemID.FALADOR_TELEPORT, "Falador teleport", StandardSpell.FALADOR_TELEPORT),
		ARDOUGNE_TELEPORT(ItemID.ARDOUGNE_TELEPORT, "Ardougne teleport", StandardSpell.ARDOUGNE_TELEPORT),
		BONES_TO_BANANAS(ItemID.BONES_TO_BANANAS, "Bones to bananas", StandardSpell.BONES_TO_BANANAS),
		ENCHANT_DRAGONSTONE(ItemID.ENCHANT_DRAGONSTONE, "Enchant dragonstone", StandardSpell.LVL_5_ENCHANT),
		ENCHANT_RUBY(ItemID.ENCHANT_RUBY_OR_TOPAZ, "Enchant ruby", StandardSpell.LVL_3_ENCHANT),
		VARROCK_TELEPORT(ItemID.VARROCK_TELEPORT, "Varrock teleport", StandardSpell.VARROCK_TELEPORT),
		CAMELOT_TELEPORT(ItemID.CAMELOT_TELEPORT, "Camelot teleport", StandardSpell.CAMELOT_TELEPORT),
		BONES_TO_PEACHES(ItemID.BONES_TO_PEACHES, "Bones to peaches", StandardSpell.BONES_TO_PEACHES);

		private final int product;

		private final String itemText;

		private final Spell spell;
	}

	@Getter
	@RequiredArgsConstructor
	enum ChargeOrbSpell
	{
		CHARGE_AIR_ORB(ItemID.AIR_ORB, StandardSpell.CHARGE_AIR_ORB),
		CHARGE_EARTH_ORB(ItemID.EARTH_ORB, StandardSpell.CHARGE_EARTH_ORB),
		CHARGE_FIRE_ORB(ItemID.FIRE_ORB, StandardSpell.CHARGE_FIRE_ORB),
		CHARGE_WATER_ORB(ItemID.WATER_ORB, StandardSpell.CHARGE_WATER_ORB);

		private final int product;

		private final Spell spell;

		public static ChargeOrbSpell byProduct(int productId)
		{
			return Arrays.stream(values()).filter(x -> x.product == productId).findFirst().orElse(null);
		}
	}

	@Getter
	enum EnchantCrossbowBoltSpell
	{
		ENCHANT_OPAL_BOLT(ItemID.OPAL_BOLTS_E, ItemID.OPAL_BOLTS, new RuneRequirement(Rune.AIR, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_OPAL_DRAGON_BOLT(ItemID.OPAL_DRAGON_BOLTS_E, ItemID.OPAL_DRAGON_BOLTS, new RuneRequirement(Rune.AIR, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_JADE_BOLT(ItemID.JADE_BOLTS_E, ItemID.JADE_BOLTS, new RuneRequirement(Rune.EARTH, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_JADE_DRAGON_BOLT(ItemID.JADE_DRAGON_BOLTS_E, ItemID.JADE_DRAGON_BOLTS, new RuneRequirement(Rune.EARTH, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_PEARL_BOLT(ItemID.PEARL_BOLTS_E, ItemID.PEARL_BOLTS, new RuneRequirement(Rune.WATER, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_PEARL_DRAGON_BOLT(ItemID.PEARL_DRAGON_BOLTS_E, ItemID.PEARL_DRAGON_BOLTS, new RuneRequirement(Rune.WATER, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_TOPAZ_BOLT(ItemID.TOPAZ_BOLTS_E, ItemID.TOPAZ_BOLTS, new RuneRequirement(Rune.FIRE, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_TOPAZ_DRAGON_BOLT(ItemID.TOPAZ_DRAGON_BOLTS_E, ItemID.TOPAZ_DRAGON_BOLTS, new RuneRequirement(Rune.FIRE, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_SAPPHIRE_BOLT(ItemID.SAPPHIRE_BOLTS_E, ItemID.SAPPHIRE_BOLTS, new RuneRequirement(Rune.WATER, 1),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.MIND, 1)),
		ENCHANT_SAPPHIRE_DRAGON_BOLT(ItemID.SAPPHIRE_DRAGON_BOLTS_E, ItemID.SAPPHIRE_DRAGON_BOLTS, new RuneRequirement(Rune.WATER, 1),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.MIND, 1)),
		ENCHANT_EMERALD_BOLT(ItemID.EMERALD_BOLTS_E, ItemID.EMERALD_BOLTS, new RuneRequirement(Rune.AIR, 3),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.NATURE, 1)),
		ENCHANT_EMERALD_DRAGON_BOLT(ItemID.EMERALD_DRAGON_BOLTS_E, ItemID.EMERALD_DRAGON_BOLTS, new RuneRequirement(Rune.AIR, 3),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.NATURE, 1)),
		ENCHANT_RUBY_BOLT(ItemID.RUBY_BOLTS_E, ItemID.RUBY_BOLTS, new RuneRequirement(Rune.FIRE, 5),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.BLOOD, 1)),
		ENCHANT_RUBY_DRAGON_BOLT(ItemID.RUBY_DRAGON_BOLTS_E, ItemID.RUBY_DRAGON_BOLTS, new RuneRequirement(Rune.FIRE, 5),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.BLOOD, 1)),
		ENCHANT_DIAMOND_BOLT(ItemID.DIAMOND_BOLTS_E, ItemID.DIAMOND_BOLTS, new RuneRequirement(Rune.EARTH, 10),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.LAW, 2)),
		ENCHANT_DIAMOND_BOLT2(ItemID.DIAMOND_BOLTS_E_23649, ItemID.DIAMOND_BOLTS, new RuneRequirement(Rune.EARTH, 10),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.LAW, 2)),
		ENCHANT_DIAMOND_DRAGON_BOLT(ItemID.DIAMOND_DRAGON_BOLTS_E, ItemID.DIAMOND_DRAGON_BOLTS, new RuneRequirement(Rune.EARTH, 10),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.LAW, 2)),
		ENCHANT_DRAGONSTONE_BOLT(ItemID.DRAGONSTONE_BOLTS_E, ItemID.DRAGONSTONE_BOLTS, new RuneRequirement(Rune.EARTH, 15),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.SOUL, 1)),
		ENCHANT_DRAGONSTONE_DRAGON_BOLT(ItemID.DRAGONSTONE_DRAGON_BOLTS_E, ItemID.DRAGONSTONE_DRAGON_BOLTS, new RuneRequirement(Rune.EARTH, 15),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.SOUL, 1)),
		ENCHANT_ONYX_BOLT(ItemID.ONYX_BOLTS_E, ItemID.ONYX_BOLTS, new RuneRequirement(Rune.FIRE, 20),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.DEATH, 1)),
		ENCHANT_ONYX_DRAGON_BOLT(ItemID.ONYX_DRAGON_BOLTS_E, ItemID.ONYX_DRAGON_BOLTS, new RuneRequirement(Rune.FIRE, 20),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.DEATH, 1));

		private final int product;

		private final int source;

		private final RuneRequirement[] runeRequirements;

		EnchantCrossbowBoltSpell(int productId, int sourceId, RuneRequirement... runeRequirements)
		{
			this.product = productId;
			this.source = sourceId;
			this.runeRequirements = runeRequirements;
		}

		public static int getAvailableCasts(Client client, int productId)
		{
			EnchantCrossbowBoltSpell spell = Arrays.stream(values()).filter(x -> x.product == productId).findFirst().orElse(null);
			Objects.requireNonNull(spell, "No enchant crossbow bolt spell found for product: " + productId);

			int min = Integer.MAX_VALUE;

			for (RuneRequirement requirement : spell.getRuneRequirements()) {
				int amount = requirement.getRune().countAvailable(client);
				min = Math.min(min, amount / requirement.getAmount());
			}

			ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
			return min = Math.min(min, inventory.count(spell.getSource()) / 10);
		}
	}

	interface StaveIDs
	{

		IDs SMOKE = new IDs(
				ItemID.SMOKE_BATTLESTAFF,
				ItemID.MYSTIC_SMOKE_STAFF
		);
		IDs DUST = new IDs(
				ItemID.DUST_BATTLESTAFF,
				ItemID.MYSTIC_DUST_STAFF
		);
		IDs MIST = new IDs(
				ItemID.MIST_BATTLESTAFF,
				ItemID.MYSTIC_MIST_STAFF
		);
		IDs MUD = new IDs(
				ItemID.MUD_BATTLESTAFF,
				ItemID.MYSTIC_MUD_STAFF
		);
		IDs LAVA = new IDs(
				ItemID.LAVA_BATTLESTAFF,
				ItemID.LAVA_BATTLESTAFF_21198,
				ItemID.MYSTIC_LAVA_STAFF,
				ItemID.MYSTIC_LAVA_STAFF_21200
		);
		IDs STEAM = new IDs(
				ItemID.STEAM_BATTLESTAFF,
				ItemID.STEAM_BATTLESTAFF_12795,
				ItemID.MYSTIC_STEAM_STAFF,
				ItemID.MYSTIC_STEAM_STAFF_12796
		);
		IDs AIR = new IDs(
				SMOKE,
				DUST,
				MIST,
				STEAM,
				ItemID.STAFF_OF_AIR,
				ItemID.AIR_BATTLESTAFF,
				ItemID.MYSTIC_AIR_STAFF
		);
		IDs WATER = new IDs(
				MUD,
				MIST,
				STEAM,
				ItemID.STAFF_OF_WATER,
				ItemID.WATER_BATTLESTAFF,
				ItemID.MYSTIC_WATER_STAFF
		);
		IDs EARTH = new IDs(
				DUST,
				MUD,
				LAVA,
				ItemID.STAFF_OF_EARTH,
				ItemID.EARTH_BATTLESTAFF,
				ItemID.MYSTIC_EARTH_STAFF
		);
		IDs FIRE = new IDs(
				SMOKE, 
				STEAM, 
				LAVA, 
				ItemID.STAFF_OF_FIRE, 
				ItemID.FIRE_BATTLESTAFF,
				ItemID.MYSTIC_FIRE_STAFF
		);

	}

	interface RuneIDs
	{

		IDs SMOKE = new IDs(ItemID.SMOKE_RUNE);
		IDs DUST = new IDs(ItemID.DUST_RUNE);
		IDs LAVA = new IDs(ItemID.LAVA_RUNE);
		IDs MIST = new IDs(ItemID.MIST_RUNE);
		IDs MUD = new IDs(ItemID.MUD_RUNE);
		IDs STEAM = new IDs(ItemID.STEAM_RUNE);
		IDs AIR = new IDs(
				SMOKE,
				DUST,
				MIST,
				ItemID.AIR_RUNE,
				ItemID.AIR_RUNE_NZ,
				ItemID.AIR_RUNE_6422,
				ItemID.AIR_RUNE_7558,
				ItemID.AIR_RUNE_9693,
				ItemID.AIR_RUNE_11688
		);
		IDs WATER = new IDs(
				MIST,
				MUD,
				STEAM,
				ItemID.WATER_RUNE,
				ItemID.WATER_RUNE_NZ,
				ItemID.WATER_RUNE_6424,
				ItemID.WATER_RUNE_7556,
				ItemID.WATER_RUNE_9691,
				ItemID.WATER_RUNE_11687
		);
		IDs EARTH = new IDs(
				DUST,
				LAVA,
				MUD,
				ItemID.EARTH_RUNE,
				ItemID.EARTH_RUNE_NZ,
				ItemID.EARTH_RUNE_6426,
				ItemID.EARTH_RUNE_9695,
				ItemID.EARTH_RUNE_11689
		);
		IDs FIRE = new IDs(
				SMOKE,
				LAVA,
				STEAM,
				ItemID.FIRE_RUNE,
				ItemID.FIRE_RUNE_NZ,
				ItemID.FIRE_RUNE_6428,
				ItemID.FIRE_RUNE_7554,
				ItemID.FIRE_RUNE_9699,
				ItemID.FIRE_RUNE_11686
		);
		IDs MIND = new IDs(
				ItemID.MIND_RUNE,
				ItemID.MIND_RUNE_6436,
				ItemID.MIND_RUNE_9697,
				ItemID.MIND_RUNE_11690
		);
		IDs BODY = new IDs(
				ItemID.BODY_RUNE,
				ItemID.BODY_RUNE_6438,
				ItemID.BODY_RUNE_11691
		);
		IDs COSMIC = new IDs(
				ItemID.COSMIC_RUNE,
				ItemID.COSMIC_RUNE_11696
		);
		IDs CHAOS = new IDs(
				ItemID.CHAOS_RUNE,
				ItemID.CHAOS_RUNE_NZ,
				ItemID.CHAOS_RUNE_6430,
				ItemID.CHAOS_RUNE_7560,
				ItemID.CHAOS_RUNE_11694
		);
		IDs ASTRAL = new IDs(
				ItemID.ASTRAL_RUNE,
				ItemID.ASTRAL_RUNE_11699
		);
		IDs NATURE = new IDs(
				ItemID.NATURE_RUNE,
				ItemID.NATURE_RUNE_11693
		);
		IDs LAW = new IDs(
				ItemID.LAW_RUNE,
				ItemID.LAW_RUNE_6434,
				ItemID.LAW_RUNE_11695
		);
		IDs DEATH = new IDs(
				ItemID.DEATH_RUNE,
				ItemID.DEATH_RUNE_NZ,
				ItemID.DEATH_RUNE_6432,
				ItemID.DEATH_RUNE_11692
		);
		IDs BLOOD = new IDs(
				ItemID.BLOOD_RUNE,
				ItemID.BLOOD_RUNE_NZ,
				ItemID.BLOOD_RUNE_11697
		);
		IDs SOUL = new IDs(
				ItemID.SOUL_RUNE,
				ItemID.SOUL_RUNE_11698
		);
		IDs WRATH = new IDs(
				ItemID.WRATH_RUNE,
				ItemID.WRATH_RUNE_22208
		);

	}

	interface Spell
	{

		String getName();

		int getGroupId();

		int getChildId();

		int getLevelRequirement();

		RuneRequirement[] getRuneRequirements();

		default int getWidgetId()
		{
			return this.getGroupId() << 16 | this.getChildId();
		}

		default int getAvailableCasts(Client client)
		{
			int min = Integer.MAX_VALUE;
			for (RuneRequirement requirement : this.getRuneRequirements()) {
				int amount = requirement.getRune().countAvailable(client);
				min = Math.min(min, amount / requirement.getAmount());
			}
			return min;
		}

	}

	@Data
	class RuneRequirement
	{

		private final Rune rune;

		private final int amount;

	}

}