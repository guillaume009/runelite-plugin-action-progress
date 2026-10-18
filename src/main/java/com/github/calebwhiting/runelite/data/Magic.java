package com.github.calebwhiting.runelite.data;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.Client;
import net.runelite.api.EnumComposition;
import net.runelite.api.EnumID;
import net.runelite.api.ItemContainer;
import net.runelite.api.annotations.Varbit;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.VarbitID;
import org.apache.commons.lang3.ArrayUtils;

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
		MINITGAME_TELEPORT(
				"Minigame Teleport",
				1,
				0
		),
		WIND_STRIKE(
				"Wind Strike",
				5,
				1,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.MIND, 1)
		),
		CONFUSE(
				"Confuse",
				6,
				3,
				new RuneRequirement(Rune.BODY, 1),
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.WATER, 3)
		),
		ENCHANT_CROSSBOW_BOLT(
				"Crossbow Bolt Enchantments",
				7,
				4
		),
		WATER_STRIKE(
				"Water Strike",
				8,
				5,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.MIND, 1),
				new RuneRequirement(Rune.WATER, 1)
		),
		LVL_1_ENCHANT(
				"Lvl-1 Enchant",
				10,
				7,
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.WATER, 1)
		),
		EARTH_STRIKE(
				"Earth Strike",
				11,
				9,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.MIND, 1)
		),
		WEAKEN(
				"Weaken",
				12,
				11,
				new RuneRequirement(Rune.BODY, 1),
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.WATER, 3)
		),
		FIRE_STRIKE(
				"Fire Strike",
				13,
				13,
				new RuneRequirement(Rune.AIR, 2),
				new RuneRequirement(Rune.FIRE, 3),
				new RuneRequirement(Rune.MIND, 1)
		),
		BONES_TO_BANANAS(
				"Bones to Bananas",
				14,
				15,
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.NATURE, 1),
				new RuneRequirement(Rune.WATER, 2)
		),
		WIND_BOLT(
				"Wind Bolt",
				15,
				17,
				new RuneRequirement(Rune.AIR, 2),
				new RuneRequirement(Rune.CHAOS, 1)
		),
		CURSE(
				"Curse",
				16,
				19,
				new RuneRequirement(Rune.BODY, 1),
				new RuneRequirement(Rune.EARTH, 3),
				new RuneRequirement(Rune.WATER, 2)
		),
		BIND(
				"Bind",
				17,
				20,
				new RuneRequirement(Rune.EARTH, 3),
				new RuneRequirement(Rune.NATURE, 2),
				new RuneRequirement(Rune.WATER, 3)
		),
		LOW_LEVEL_ALCHEMY(
				"Low Level Alchemy",
				18,
				21,
				new RuneRequirement(Rune.FIRE, 3),
				new RuneRequirement(Rune.NATURE, 1)
		),
		WATER_BOLT(
				"Water Bolt",
				19,
				23,
				new RuneRequirement(Rune.AIR, 2),
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.WATER, 2)
		),
		VARROCK_TELEPORT(
				"Varrock Teleport",
				20,
				25,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.FIRE, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		LVL_2_ENCHANT(
				"Lvl-2 Enchant",
				21,
				27,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.COSMIC, 1)
		),
		EARTH_BOLT(
				"Earth Bolt",
				22,
				29,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.EARTH, 1)
		),
		LUMBRIDGE_TELEPORT(
				"Lumbridge Teleport",
				23,
				31,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.EARTH, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		TELEKINETIC_GRAB(
				"Telekinetic Grab",
				24,
				33,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		FIRE_BOLT(
				"Fire Bolt",
				25,
				35,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.FIRE, 4)
		),
		FALADOR_TELEPORT(
				"Falador Teleport",
				26,
				37,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.LAW, 1),
				new RuneRequirement(Rune.WATER, 1)
		),
		CRUMBLE_UNDEAD(
				"Crumble Undead",
				27,
				39,
				new RuneRequirement(Rune.AIR, 2),
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.EARTH, 2)
		),
		TELEPORT_TO_HOUSE(
				"Teleport to House",
				28,
				40,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.EARTH, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		WIND_BLAST(
				"Wind Blast",
				29,
				41,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.DEATH, 1)
		),
		SUPERHEAT_ITEM(
				"Superheat Item",
				30,
				43,
				new RuneRequirement(Rune.FIRE, 4),
				new RuneRequirement(Rune.NATURE, 1)
		),
		CAMELOT_TELEPORT(
				"Camelot Teleport",
				31,
				45,
				new RuneRequirement(Rune.AIR, 5),
				new RuneRequirement(Rune.LAW, 1)
		),
		WATER_BLAST(
				"Water Blast",
				32,
				47,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.WATER, 3)
		),
		KOUREND_CASTLE_TELEPORT(
				"Kourend Castle Teleport",
				33,
				48,
				new RuneRequirement(Rune.FIRE, 5),
				new RuneRequirement(Rune.LAW, 2),
				new RuneRequirement(Rune.SOUL, 2),
				new RuneRequirement(Rune.WATER, 4)
		),
		LVL_3_ENCHANT(
				"Lvl-3 Enchant",
				34,
				49,
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.FIRE, 5)
		),
		IBAN_BLAST(
				"Iban Blast",
				35,
				50,
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.FIRE, 5)
		),
		SNARE(
				"Snare",
				36,
				50,
				new RuneRequirement(Rune.EARTH, 4),
				new RuneRequirement(Rune.NATURE, 3),
				new RuneRequirement(Rune.WATER, 4)
		),
		MAGIC_DART(
				"Magic Dart",
				37,
				50,
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.MIND, 4)
		),
		ARDOUGNE_TELEPORT(
				"Ardougne Teleport",
				38,
				51,
				new RuneRequirement(Rune.LAW, 2),
				new RuneRequirement(Rune.WATER, 2)
		),
		EARTH_BLAST(
				"Earth Blast",
				39,
				53,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.EARTH, 4)
		),
		CIVITAS_ILLA_FORTIS_TELEPORT(
				"Civitas illa Fortis Teleport",
				40,
				51,
				new RuneRequirement(Rune.LAW, 2),
				new RuneRequirement(Rune.EARTH, 1),
				new RuneRequirement(Rune.FIRE, 1)
		),
		HIGH_LEVEL_ALCHEMY(
				"High Level Alchemy",
				41,
				55,
				new RuneRequirement(Rune.FIRE, 5),
				new RuneRequirement(Rune.NATURE, 1)
		),
		CHARGE_WATER_ORB(
				"Charge Water Orb",
				42,
				56,
				new RuneRequirement(Rune.COSMIC, 3),
				new RuneRequirement(Rune.WATER, 30)
		),
		LVL_4_ENCHANT(
				"Lvl-4 Enchant",
				43,
				57,
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.EARTH, 10)
		),
		WATCHTOWER_TELEPORT(
				"Watchtower Teleport",
				44,
				58,
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.LAW, 2)
		),
		FIRE_BLAST(
				"Fire Blast",
				45,
				59,
				new RuneRequirement(Rune.AIR, 4),
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.FIRE, 5)
		),
		CHARGE_EARTH_ORB(
				"Charge Earth Orb",
				46,
				60,
				new RuneRequirement(Rune.COSMIC, 3),
				new RuneRequirement(Rune.EARTH, 30)
		),
		BONES_TO_PEACHES(
				"Bones to Peaches",
				47,
				60,
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.NATURE, 2),
				new RuneRequirement(Rune.WATER, 4)
		),
		SARADOMIN_STRIKE(
				"Saradomin Strike",
				48,
				60,
				new RuneRequirement(Rune.AIR, 4),
				new RuneRequirement(Rune.BLOOD, 2),
				new RuneRequirement(Rune.FIRE, 2)
		),
		CLAWS_OF_GUTHIX(
				"Claws of Guthix",
				49,
				60,
				new RuneRequirement(Rune.AIR, 4),
				new RuneRequirement(Rune.BLOOD, 2),
				new RuneRequirement(Rune.FIRE, 1)
		),
		FLAMES_OF_ZAMORAK(
				"Flames of Zamorak",
				50,
				60,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.BLOOD, 2),
				new RuneRequirement(Rune.FIRE, 4)
		),
		TROLLHEIM_TELEPORT(
				"Trollheim Teleport",
				51,
				61,
				new RuneRequirement(Rune.FIRE, 2),
				new RuneRequirement(Rune.LAW, 2)
		),
		WIND_WAVE(
				"Wind Wave",
				52,
				62,
				new RuneRequirement(Rune.AIR, 5),
				new RuneRequirement(Rune.BLOOD, 1)
		),
		CHARGE_FIRE_ORB(
				"Charge Fire Orb",
				53,
				63,
				new RuneRequirement(Rune.COSMIC, 3),
				new RuneRequirement(Rune.FIRE, 30)
		),
		APE_ATOLL_TELEPORT(
				"Ape Atoll Teleport",
				54,
				64,
				new RuneRequirement(Rune.FIRE, 2),
				new RuneRequirement(Rune.LAW, 2),
				new RuneRequirement(Rune.WATER, 2)
		),
		WATER_WAVE(
				"Water Wave",
				55,
				65,
				new RuneRequirement(Rune.AIR, 5),
				new RuneRequirement(Rune.BLOOD, 1),
				new RuneRequirement(Rune.WATER, 7)
		),
		CHARGE_AIR_ORB(
				"Charge Air Orb",
				56,
				66,
				new RuneRequirement(Rune.AIR, 30),
				new RuneRequirement(Rune.COSMIC, 3)
		),
		VULNERABILITY(
				"Vulnerability",
				57,
				66,
				new RuneRequirement(Rune.EARTH, 5),
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.WATER, 5)
		),
		LVL_5_ENCHANT(
				"Lvl-5 Enchant",
				58,
				68,
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.EARTH, 15),
				new RuneRequirement(Rune.WATER, 15)
		),
		EARTH_WAVE(
				"Earth Wave",
				59,
				70,
				new RuneRequirement(Rune.AIR, 5),
				new RuneRequirement(Rune.BLOOD, 1),
				new RuneRequirement(Rune.EARTH, 7)
		),
		ENFEEBLE(
				"Enfeeble",
				60,
				73,
				new RuneRequirement(Rune.EARTH, 8),
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.WATER, 8)
		),
		TELEOTHER_LUMBRIDGE(
				"Teleother Lumbridge",
				61,
				74,
				new RuneRequirement(Rune.EARTH, 1),
				new RuneRequirement(Rune.LAW, 1),
				new RuneRequirement(Rune.SOUL, 1)
		),
		FIRE_WAVE(
				"Fire Wave",
				62,
				75,
				new RuneRequirement(Rune.AIR, 5),
				new RuneRequirement(Rune.BLOOD, 1),
				new RuneRequirement(Rune.FIRE, 7)
		),
		ENTANGLE(
				"Entangle",
				63,
				79,
				new RuneRequirement(Rune.EARTH, 5),
				new RuneRequirement(Rune.NATURE, 4),
				new RuneRequirement(Rune.WATER, 5)
		),
		STUN(
				"Stun",
				64,
				80,
				new RuneRequirement(Rune.EARTH, 12),
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.WATER, 12)
		),
		CHARGE(
				"Charge",
				65,
				80,
				new RuneRequirement(Rune.AIR, 3),
				new RuneRequirement(Rune.BLOOD, 3),
				new RuneRequirement(Rune.FIRE, 3)
		),
		WIND_SURGE(
				"Wind Surge",
				66,
				81,
				new RuneRequirement(Rune.AIR, 7),
				new RuneRequirement(Rune.WRATH, 1)
		),
		TELEOTHER_FALADOR(
				"Teleother Falador",
				67,
				82,
				new RuneRequirement(Rune.LAW, 1),
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.WATER, 1)
		),
		WATER_SURGE(
				"Water Surge",
				68,
				85,
				new RuneRequirement(Rune.AIR, 7),
				new RuneRequirement(Rune.WATER, 10),
				new RuneRequirement(Rune.WRATH, 1)
		),
		TELE_BLOCK(
				"Tele Block",
				69,
				85,
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		TELEPORT_TO_BOUNTY_TARGET(
				"Teleport to Bounty Target",
				70,
				85,
				new RuneRequirement(Rune.CHAOS, 1),
				new RuneRequirement(Rune.DEATH, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		LVL_6_ENCHANT(
				"Lvl-6 Enchant",
				71,
				87,
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.EARTH, 20),
				new RuneRequirement(Rune.FIRE, 20)
		),
		TELEOTHER_CAMELOT(
				"Teleother Camelot",
				72,
				90,
				new RuneRequirement(Rune.LAW, 1),
				new RuneRequirement(Rune.SOUL, 2)
		),
		EARTH_SURGE(
				"Earth Surge",
				73,
				90,
				new RuneRequirement(Rune.AIR, 7),
				new RuneRequirement(Rune.EARTH, 10),
				new RuneRequirement(Rune.WRATH, 1)
		),
		LVL_7_ENCHANT(
				"Lvl-7 Enchant",
				74,
				93,
				new RuneRequirement(Rune.BLOOD, 20),
				new RuneRequirement(Rune.COSMIC, 1),
				new RuneRequirement(Rune.SOUL, 20)
		),
		FIRE_SURGE(
				"Fire Surge",
				75,
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
		),
		BAKE_PIE(
				"Bake Pie",
				1,
				65,
				new RuneRequirement(Rune.FIRE, 4),
				new RuneRequirement(Rune.ASTRAL, 1),
				new RuneRequirement(Rune.WATER, 4)

		),
		MOONCLAN_TELEPORT(
				"Moonclan Teleport",
				1,
				69,
				new RuneRequirement(Rune.EARTH, 2),
				new RuneRequirement(Rune.ASTRAL, 2),
				new RuneRequirement(Rune.LAW, 1)
		),
		OURANIA_TELEPORT(
				"Ourania Teleport",
				2,
				71,
				new RuneRequirement(Rune.EARTH, 6),
				new RuneRequirement(Rune.ASTRAL, 2),
				new RuneRequirement(Rune.LAW, 1)
		),
		WATERBIRTH_TELEPORT(
				"Waterbirth Teleport",
				3,
				72,
				new RuneRequirement(Rune.WATER, 1),
				new RuneRequirement(Rune.ASTRAL, 2),
				new RuneRequirement(Rune.LAW, 1)
		),
		BARBARIAN_TELEPORT(
				"Barbarian Teleport",
				4,
				75,
				new RuneRequirement(Rune.FIRE, 3),
				new RuneRequirement(Rune.ASTRAL, 2),
				new RuneRequirement(Rune.LAW, 2)
		),
		KHAZARD_TELEPORT(
				"Khazard Teleport",
				5,
				78,
				new RuneRequirement(Rune.WATER, 4),
				new RuneRequirement(Rune.ASTRAL, 2),
				new RuneRequirement(Rune.LAW, 2)
		),
		FISHING_GUILD_TELEPORT(
				"Fishing Guild Teleport",
				6,
				85,
				new RuneRequirement(Rune.WATER, 10),
				new RuneRequirement(Rune.ASTRAL, 3),
				new RuneRequirement(Rune.LAW, 3)
		),
		CATHERBY_TELEPORT(
				"Catherby Teleport",
				7,
				87,
				new RuneRequirement(Rune.WATER, 10),
				new RuneRequirement(Rune.ASTRAL, 3),
				new RuneRequirement(Rune.LAW, 3)
		),
		ICE_PLATEAU_TELEPORT(
				"Ice Plateau Teleport",
				8,
				89,
				new RuneRequirement(Rune.WATER, 8),
				new RuneRequirement(Rune.ASTRAL, 3),
				new RuneRequirement(Rune.LAW, 3)
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
	enum AncientSpell implements Spell
	{
		PADDEWWA_TELEPORT(
				"Paddewwa Teleport",
				16,
				54,
				new RuneRequirement(Rune.AIR, 1),
				new RuneRequirement(Rune.FIRE, 1),
				new RuneRequirement(Rune.LAW, 2)
		),
		SENNTISTEN_TELEPORT(
				"Senntisen Teleport",
				17,
				60,
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		KHARYRLL_TELEPORT(
				"Kharyrll Teleport",
				18,
				66,
				new RuneRequirement(Rune.BLOOD, 1),
				new RuneRequirement(Rune.LAW, 2)
		),
		LASSAR_TELEPORT(
				"Lassar Teleport",
				19,
				72,
				new RuneRequirement(Rune.WATER, 4),
				new RuneRequirement(Rune.LAW, 2)
		),
		DAREEYAK_TELEPORT(
				"Dareeyak Teleport",
				20,
				78,
				new RuneRequirement(Rune.AIR, 2),
				new RuneRequirement(Rune.FIRE, 3),
				new RuneRequirement(Rune.LAW, 2)
		),
		CARRALLANGER_TELEPORT(
				"Carrallanger Teleport",
				21, 84,
				new RuneRequirement(Rune.LAW, 2),
				new RuneRequirement(Rune.SOUL, 2)
		),
		ANNAKARL_TELEPORT(
				"Annakarl Teleport",
				22,
				90,
				new RuneRequirement(Rune.BLOOD, 2),
				new RuneRequirement(Rune.LAW, 2)
		),
		GHORROCK_TELEPORT(
				"Ghorrock Teleport",
				23,
				96,
				new RuneRequirement(Rune.WATER, 2),
				new RuneRequirement(Rune.LAW, 2)
		),;

		private static final int ANCIENT_SPELLBOOK_GROUP_ID = 218;

		private static final int ANCIENT_SPELLBOOK_FIRST_SPELL_INDEX = 85;

		private final String name;

		private final int index;

		private final int levelRequirement;

		private final RuneRequirement[] runeRequirements;

		AncientSpell(String name, int index, int levelRequirement, RuneRequirement... runeRequirements)
		{
			this.name = name;
			this.index = index;
			this.levelRequirement = levelRequirement;
			this.runeRequirements = runeRequirements;
		}

		@Override
		public int getGroupId()
		{
			return ANCIENT_SPELLBOOK_GROUP_ID;
		}

		public int getChildId()
		{
			return ANCIENT_SPELLBOOK_FIRST_SPELL_INDEX + this.getIndex();
		}
	}

	@Getter
	enum ArceuusSpell implements Spell
	{
		ARCEUUS_LIBRARY_TELEPORT(
				"Arceuus Library Teleport",
				1,
				6,
				new RuneRequirement(Rune.EARTH, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		DRAYNOR_MANOR_TELEPORT(
				"Draynor Teleport",
				2,
				17,
				new RuneRequirement(Rune.EARTH, 1),
				new RuneRequirement(Rune.WATER, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		BATTLEFRONT_TELEPORT(
				"Battlefront Teleport",
				1,
				23,
				new RuneRequirement(Rune.EARTH, 1),
				new RuneRequirement(Rune.FIRE, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		MIND_ALTAR_TELEPORT(
				"Mind Alter Teleport",
				1,
				28,
				new RuneRequirement(Rune.MIND, 2),
				new RuneRequirement(Rune.LAW, 1)
		),
		SALVE_GRAVEYARD_TELEPORT(
				"Salve Graveyard Teleport",
				1,
				40,
				new RuneRequirement(Rune.SOUL, 2),
				new RuneRequirement(Rune.LAW, 1)
		),
		FENKENSTRAINS_CASTLE_TELEPORT(
				"Fenkenstrain's Castle Teleport",
				1,
				48,
				new RuneRequirement(Rune.EARTH, 1),
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		WEST_ARDOUGNE_TELEPORT(
				"West Ardounge Teleport",
				1,
				61,
				new RuneRequirement(Rune.SOUL, 2),
				new RuneRequirement(Rune.LAW, 2)
		),
		HARMONY_ISLAND_TELEPORT(
				"Harmony Island Teleport",
				1,
				65,
				new RuneRequirement(Rune.NATURE, 1),
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		CEMETERY_TELEPORT(
				"Cemetery Teleport",
				1,
				71,
				new RuneRequirement(Rune.BLOOD, 1),
				new RuneRequirement(Rune.SOUL, 1),
				new RuneRequirement(Rune.LAW, 1)
		),
		BARROWS_TELEPORT(
				"Barrows Teleport",
				1,
				83,
				new RuneRequirement(Rune.BLOOD, 1),
				new RuneRequirement(Rune.SOUL, 2),
				new RuneRequirement(Rune.LAW, 2)
		),
		APE_ATOLL_TELEPORT(
				"Ape Atoll Teleport",
				1,
				90,
				new RuneRequirement(Rune.BLOOD, 2),
				new RuneRequirement(Rune.SOUL, 2),
				new RuneRequirement(Rune.LAW, 2)
		),;

		private static final int ARCEUUS_SPELLBOOK_GROUP_ID = 218;

		private static final int ARCEUUS_SPELLBOOK_FIRST_SPELL_INDEX = 140;

		private final String name;

		private final int index;

		private final int levelRequirement;

		private final RuneRequirement[] runeRequirements;

		ArceuusSpell(String name, int index, int levelRequirement, RuneRequirement... runeRequirements)
		{
			this.name = name;
			this.index = index;
			this.levelRequirement = levelRequirement;
			this.runeRequirements = runeRequirements;
		}

		@Override
		public int getGroupId()
		{
			return ARCEUUS_SPELLBOOK_GROUP_ID;
		}

		public int getChildId()
		{
			return ARCEUUS_SPELLBOOK_FIRST_SPELL_INDEX + this.getIndex();
		}
	}

	@Getter
	enum BakePieSpell
	{
		BAKE_PIE_REDBERRY(LunarSpell.BAKE_PIE,ItemID.UNCOOKED_REDBERRY_PIE, 10),
		BAKE_PIE_MEAT(LunarSpell.BAKE_PIE, ItemID.UNCOOKED_MEAT_PIE, 20),
		BAKE_PIE_MUD(LunarSpell.BAKE_PIE, ItemID.UNCOOKED_MUD_PIE, 29),
		BAKE_PIE_APPLE(LunarSpell.BAKE_PIE, ItemID.UNCOOKED_APPLE_PIE, 30),
		BAKE_PIE_GARDEN(LunarSpell.BAKE_PIE, ItemID.UNCOOKED_GARDEN_PIE, 34),
		BAKE_PIE_FISH(LunarSpell.BAKE_PIE, ItemID.UNCOOKED_FISH_PIE, 47),
		BAKE_PIE_BOTANICAL(LunarSpell.BAKE_PIE, ItemID.UNCOOKED_BOTANICAL_PIE, 52),
		BAKE_PIE_MUSHROOM(LunarSpell.BAKE_PIE, ItemID.UNCOOKED_MUSHROOM_PIE, 60),
		BAKE_PIE_ADMIRAL(LunarSpell.BAKE_PIE, ItemID.UNCOOKED_ADMIRAL_PIE, 70),
		BAKE_PIE_DRAGONFRUIT(LunarSpell.BAKE_PIE, ItemID.UNCOOKED_DRAGONFRUIT_PIE, 73),
		BAKE_PIE_WILD(LunarSpell.BAKE_PIE, ItemID.UNCOOKED_WILD_PIE, 85),
		BAKE_PIE_SUMMER(LunarSpell.BAKE_PIE, ItemID.UNCOOKED_SUMMER_PIE, 95);

		private final Spell spell;

		private final int pieId;

		private final int level;


		BakePieSpell(Spell spell, int pieId, int level)
		{
			this.spell = spell;
			this.pieId = pieId;
			this.level = level;
		}
	}

	@Getter
	enum PlankMakeSpell
	{
		PLANK_MAKE(LunarSpell.PLANK_MAKE, ItemID.LOGS, 70),
		PLANK_MAKE_OAK(LunarSpell.PLANK_MAKE, ItemID.OAK_LOGS, 175),
		PLANK_MAKE_TEAK(LunarSpell.PLANK_MAKE, ItemID.TEAK_LOGS, 350),
		PLANK_MAKE_MAHOGANY(LunarSpell.PLANK_MAKE, ItemID.MAHOGANY_LOGS, 1050),
		PLANK_MAKE_CAMPHOR(LunarSpell.PLANK_MAKE, ItemID.CAMPHOR_LOGS, 1750),
		PLANK_MAKE_IRONWOOD(LunarSpell.PLANK_MAKE, ItemID.IRONWOOD_LOGS, 3500),
		PLANK_MAKE_ROSEWOOD(LunarSpell.PLANK_MAKE, ItemID.ROSEWOOD_LOGS, 5250);

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
		STRING_STRUNG_RABBIT_FOOT(LunarSpell.STRING_JEWELLERY, ItemID.HUNTING_RABBIT_FOOT),
		STRING_HOLY_SYMBOL(LunarSpell.STRING_JEWELLERY, ItemID.NOSTRINGSTAR),
		STRING_UNHOLY_SYMBOL(LunarSpell.STRING_JEWELLERY, ItemID.NOSTRINGSNAKE),
		STRING_OPAL_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_OPAL_AMULET),
		STRING_JADE_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_JADE_AMULET),
		STRING_SAPPHIRE_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_SAPPHIRE_AMULET),
		STRING_TOPAZ_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_TOPAZ_AMULET),
		STRING_EMERALD_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_EMERALD_AMULET),
		STRING_RUBY_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_RUBY_AMULET),
		STRING_GOLD_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_GOLD_AMULET),
		STRING_DIAMOND_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_DIAMOND_AMULET),
		STRING_DRAGONSTONE_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_DRAGONSTONE_AMULET),
		STRING_ONYX_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_ONYX_AMULET),
		STRING_ZENYTE_AMULET(LunarSpell.STRING_JEWELLERY, ItemID.UNSTRUNG_ZENYTE_AMULET);

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
		NATURE("Nature", RuneIDs.NATURE.build(), new IDs(StaveIDs.NATURE).build()),
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
			VarbitID.RUNE_POUCH_QUANTITY_1, VarbitID.RUNE_POUCH_QUANTITY_2, VarbitID.RUNE_POUCH_QUANTITY_3, VarbitID.RUNE_POUCH_QUANTITY_4
		};

		private static final int[] RUNE_VARBITS =
		{
			VarbitID.RUNE_POUCH_TYPE_1, VarbitID.RUNE_POUCH_TYPE_2, VarbitID.RUNE_POUCH_TYPE_3, VarbitID.RUNE_POUCH_TYPE_4
		};

		Rune(String name, int... runeId)
		{
			this(name, runeId, ArrayUtils.EMPTY_INT_ARRAY);
		}

		public int countAvailable(Client client)
		{
			for (int itemId : this.unlimitedSources) {
				ItemContainer equipment = client.getItemContainer(InventoryID.WORN);
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
				
				ItemContainer inventory = client.getItemContainer(InventoryID.INV);
				count += inventory == null ? 0 : inventory.count(itemId);
			}
			return count;
		}
	}

	@Getter
	@RequiredArgsConstructor
	enum LecternSpell
	{
		ENCHANT_ONYX(ItemID.POH_TABLET_ENCHANTONYX, "Enchant onyx", StandardSpell.LVL_6_ENCHANT),
		LUMBRIDGE_TELEPORT(ItemID.POH_TABLET_LUMBRIDGETELEPORT, "Lumbridge teleport", StandardSpell.LUMBRIDGE_TELEPORT),
		ENCHANT_DIAMOND(ItemID.POH_TABLET_ENCHANTDIAMOND, "Enchant diamond", StandardSpell.LVL_4_ENCHANT),
		WATCHTOWER_TELEPORT(ItemID.POH_TABLET_WATCHTOWERTELEPORT, "Watchtower teleport", StandardSpell.WATCHTOWER_TELEPORT),
		HOUSE_TELEPORT(ItemID.POH_TABLET_TELEPORTTOHOUSE, "Teleport to house", StandardSpell.TELEPORT_TO_HOUSE),
		ENCHANT_EMERALD(ItemID.POH_TABLET_ENCHANTEMERALD, "Enchant emerald", StandardSpell.LVL_2_ENCHANT),
		ENCHANT_SAPPHIRE(ItemID.POH_TABLET_ENCHANTSAPPHIRE, "Enchant sapphire", StandardSpell.LVL_1_ENCHANT),
		FALADOR_TELEPORT(ItemID.POH_TABLET_FALADORTELEPORT, "Falador teleport", StandardSpell.FALADOR_TELEPORT),
		ARDOUGNE_TELEPORT(ItemID.POH_TABLET_ARDOUGNETELEPORT, "Ardougne teleport", StandardSpell.ARDOUGNE_TELEPORT),
		BONES_TO_BANANAS(ItemID.POH_TABLET_BONESTOBANANAS, "Bones to bananas", StandardSpell.BONES_TO_BANANAS),
		ENCHANT_DRAGONSTONE(ItemID.POH_TABLET_ENCHANTDRAGONSTONE, "Enchant dragonstone", StandardSpell.LVL_5_ENCHANT),
		ENCHANT_RUBY(ItemID.POH_TABLET_ENCHANTRUBY, "Enchant ruby", StandardSpell.LVL_3_ENCHANT),
		VARROCK_TELEPORT(ItemID.POH_TABLET_VARROCKTELEPORT, "Varrock teleport", StandardSpell.VARROCK_TELEPORT),
		CAMELOT_TELEPORT(ItemID.POH_TABLET_CAMELOTTELEPORT, "Camelot teleport", StandardSpell.CAMELOT_TELEPORT),
		BONES_TO_PEACHES(ItemID.MAGICTRAINING_PEACHSPELL, "Bones to peaches", StandardSpell.BONES_TO_PEACHES),
		//Lunar Teleports
		MOONCLAN_TELEPORT(ItemID.LUNAR_TABLET_MOONCLAN_TELEPORT, "Moonclan", LunarSpell.MOONCLAN_TELEPORT),
		OURANIA_TELEPORT(ItemID.LUNAR_TABLET_OURANIA_TELEPORT, "Ourania", LunarSpell.OURANIA_TELEPORT),
		WATERBIRTH_TELEPORT(ItemID.LUNAR_TABLET_WATERBIRTH_TELEPORT, "Waterbirth", LunarSpell.WATERBIRTH_TELEPORT),
		BARBARIAN_TELEPORT(ItemID.LUNAR_TABLET_BARBARIAN_TELEPORT, "Barbarian", LunarSpell.BARBARIAN_TELEPORT),
		KHAZARD_TELEPORT(ItemID.LUNAR_TABLET_KHAZARD_TELEPORT, "Khazard", LunarSpell.KHAZARD_TELEPORT),
		FISHING_GUILD_TELEPORT(ItemID.LUNAR_TABLET_FISHING_GUILD_TELEPORT, "Fishing guild", LunarSpell.FISHING_GUILD_TELEPORT),
		CATHERBY_TELEPORT(ItemID.LUNAR_TABLET_CATHERBY_TELEPORT, "Catherby", LunarSpell.CATHERBY_TELEPORT),
		ICE_PLATEAU_TELEPORT(ItemID.LUNAR_TABLET_ICE_PLATEAU_TELEPORT, "Ice Plateau", LunarSpell.ICE_PLATEAU_TELEPORT),
		//Ancient Teleports
		PADDEWWA_TELEPORT(ItemID.TABLET_PADDEWA, "Paddewa", AncientSpell.PADDEWWA_TELEPORT), //typo in game menu, if jagex item name needs update
		SENNTISTEN_TELEPORT(ItemID.TABLET_SENNTISTEN, "Senntisten", AncientSpell.SENNTISTEN_TELEPORT),
		KHARYRLL_TELEPORT(ItemID.TABLET_KHARYLL, "Kharyrll", AncientSpell.KHARYRLL_TELEPORT),
		LASSAR_TELEPORT(ItemID.TABLET_LASSAR, "Lassar", AncientSpell.LASSAR_TELEPORT),
		DAREEYAK_TELEPORT(ItemID.TABLET_DAREEYAK, "Dareeyak", AncientSpell.DAREEYAK_TELEPORT),
		CARRALLANGER_TELEPORT(ItemID.TABLET_CARRALLANGAR, "Carrallanger", AncientSpell.CARRALLANGER_TELEPORT),
		ANNAKARL_TELEPORT(ItemID.TABLET_ANNAKARL, "Annakarl", AncientSpell.ANNAKARL_TELEPORT),
		GHORROCK_TELEPORT(ItemID.TABLET_GHORROCK, "Ghorrock", AncientSpell.GHORROCK_TELEPORT),
		//Arceuus Teleports
		// net.runelite.api.gameval.ItemID.TELETAB_LUMBRIDGE is in fact the Arceuus Library teleport tablet
		ARCEUUS_LIBRARY_TELEPORT(ItemID.TELETAB_LUMBRIDGE, "Arceuus Library", ArceuusSpell.ARCEUUS_LIBRARY_TELEPORT),
		DRAYNOR_MANOR_TELEPORT(ItemID.TELETAB_DRAYNOR, "Draynor Manor", ArceuusSpell.DRAYNOR_MANOR_TELEPORT),
		BATTLEFRONT_TELEPORT(ItemID.TELETAB_BATTLEFRONT, "Battlefront", ArceuusSpell.BATTLEFRONT_TELEPORT),
		MIND_ALTAR_TELEPORT(ItemID.TELETAB_MIND_ALTAR, "Mind Altar", ArceuusSpell.MIND_ALTAR_TELEPORT),
		SALVE_GRAVEYARD_TELEPORT(ItemID.TELETAB_SALVE, "Salve Graveyard", ArceuusSpell.SALVE_GRAVEYARD_TELEPORT),
		FENKENSTRAINS_CASTLE_TELEPORT(ItemID.TELETAB_FENK, "Fenkenstrain's Castle", ArceuusSpell.FENKENSTRAINS_CASTLE_TELEPORT),
		WEST_ARDOUGNE_TELEPORT(ItemID.TELETAB_WESTARDY, "West Ardougne", ArceuusSpell.WEST_ARDOUGNE_TELEPORT),
		HARMONY_ISLAND_TELEPORT(ItemID.TELETAB_HARMONY, "Harmony Island", ArceuusSpell.HARMONY_ISLAND_TELEPORT),
		CEMETERY_TELEPORT(ItemID.TELETAB_CEMETERY, "Cemetery", ArceuusSpell.CEMETERY_TELEPORT),
		BARROWS_TELEPORT(ItemID.TELETAB_BARROWS, "Barrows", ArceuusSpell.BARROWS_TELEPORT),
		APE_ATOLL_TELEPORT(ItemID.TELETAB_APE, "Ape Atoll", ArceuusSpell.APE_ATOLL_TELEPORT),
		;


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
		ENCHANT_OPAL_BOLT(ItemID.XBOWS_CROSSBOW_BOLTS_BRONZE_TIPPED_OPAL_ENCHANTED, ItemID.OPAL_BOLT, new RuneRequirement(Rune.AIR, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_OPAL_DRAGON_BOLT(ItemID.DRAGON_BOLTS_ENCHANTED_OPAL, ItemID.DRAGON_BOLTS_UNENCHANTED_OPAL, new RuneRequirement(Rune.AIR, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_JADE_BOLT(ItemID.XBOWS_CROSSBOW_BOLTS_BLURITE_TIPPED_JADE_ENCHANTED, ItemID.XBOWS_CROSSBOW_BOLTS_BLURITE_TIPPED_JADE, new RuneRequirement(Rune.EARTH, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_JADE_DRAGON_BOLT(ItemID.DRAGON_BOLTS_ENCHANTED_JADE, ItemID.DRAGON_BOLTS_UNENCHANTED_JADE, new RuneRequirement(Rune.EARTH, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_PEARL_BOLT(ItemID.XBOWS_CROSSBOW_BOLTS_IRON_TIPPED_PEARL_ENCHANTED, ItemID.PEARL_BOLT, new RuneRequirement(Rune.WATER, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_PEARL_DRAGON_BOLT(ItemID.DRAGON_BOLTS_ENCHANTED_PEARL, ItemID.DRAGON_BOLTS_UNENCHANTED_PEARL, new RuneRequirement(Rune.WATER, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_TOPAZ_BOLT(ItemID.XBOWS_CROSSBOW_BOLTS_STEEL_TIPPED_REDTOPAZ_ENCHANTED, ItemID.XBOWS_CROSSBOW_BOLTS_STEEL_TIPPED_REDTOPAZ, new RuneRequirement(Rune.FIRE, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_TOPAZ_DRAGON_BOLT(ItemID.DRAGON_BOLTS_ENCHANTED_TOPAZ, ItemID.DRAGON_BOLTS_UNENCHANTED_TOPAZ, new RuneRequirement(Rune.FIRE, 2),new RuneRequirement(Rune.COSMIC, 1)),
		ENCHANT_SAPPHIRE_BOLT(ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL_TIPPED_SAPPHIRE_ENCHANTED, ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL_TIPPED_SAPPHIRE, new RuneRequirement(Rune.WATER, 1),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.MIND, 1)),
		ENCHANT_SAPPHIRE_DRAGON_BOLT(ItemID.DRAGON_BOLTS_ENCHANTED_SAPPHIRE, ItemID.DRAGON_BOLTS_UNENCHANTED_SAPPHIRE, new RuneRequirement(Rune.WATER, 1),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.MIND, 1)),
		ENCHANT_EMERALD_BOLT(ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL_TIPPED_EMERALD_ENCHANTED, ItemID.XBOWS_CROSSBOW_BOLTS_MITHRIL_TIPPED_EMERALD, new RuneRequirement(Rune.AIR, 3),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.NATURE, 1)),
		ENCHANT_EMERALD_DRAGON_BOLT(ItemID.DRAGON_BOLTS_ENCHANTED_EMERALD, ItemID.DRAGON_BOLTS_UNENCHANTED_EMERALD, new RuneRequirement(Rune.AIR, 3),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.NATURE, 1)),
		ENCHANT_RUBY_BOLT(ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_TIPPED_RUBY_ENCHANTED, ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_TIPPED_RUBY, new RuneRequirement(Rune.FIRE, 5),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.BLOOD, 1)),
		ENCHANT_RUBY_DRAGON_BOLT(ItemID.DRAGON_BOLTS_ENCHANTED_RUBY, ItemID.DRAGON_BOLTS_UNENCHANTED_RUBY, new RuneRequirement(Rune.FIRE, 5),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.BLOOD, 1)),
		ENCHANT_DIAMOND_BOLT(ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_TIPPED_DIAMOND_ENCHANTED, ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_TIPPED_DIAMOND, new RuneRequirement(Rune.EARTH, 10),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.LAW, 2)),
		ENCHANT_DIAMOND_BOLT2(ItemID.BR_DSTONE_BOLTS_E, ItemID.XBOWS_CROSSBOW_BOLTS_ADAMANTITE_TIPPED_DIAMOND, new RuneRequirement(Rune.EARTH, 10),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.LAW, 2)),
		ENCHANT_DIAMOND_DRAGON_BOLT(ItemID.DRAGON_BOLTS_ENCHANTED_DIAMOND, ItemID.DRAGON_BOLTS_UNENCHANTED_DIAMOND, new RuneRequirement(Rune.EARTH, 10),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.LAW, 2)),
		ENCHANT_DRAGONSTONE_BOLT(ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE_TIPPED_DRAGONSTONE_ENCHANTED, ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE_TIPPED_DRAGONSTONE, new RuneRequirement(Rune.EARTH, 15),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.SOUL, 1)),
		ENCHANT_DRAGONSTONE_DRAGON_BOLT(ItemID.DRAGON_BOLTS_ENCHANTED_DRAGONSTONE, ItemID.DRAGON_BOLTS_UNENCHANTED_DRAGONSTONE, new RuneRequirement(Rune.EARTH, 15),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.SOUL, 1)),
		ENCHANT_ONYX_BOLT(ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE_TIPPED_ONYX_ENCHANTED, ItemID.XBOWS_CROSSBOW_BOLTS_RUNITE_TIPPED_ONYX, new RuneRequirement(Rune.FIRE, 20),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.DEATH, 1)),
		ENCHANT_ONYX_DRAGON_BOLT(ItemID.DRAGON_BOLTS_ENCHANTED_ONYX, ItemID.DRAGON_BOLTS_UNENCHANTED_ONYX, new RuneRequirement(Rune.FIRE, 20),new RuneRequirement(Rune.COSMIC, 1), new RuneRequirement(Rune.DEATH, 1));

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

			ItemContainer inventory = client.getItemContainer(InventoryID.INV);
			return min = Math.min(min, inventory.count(spell.getSource()) / 10);
		}
	}

	interface StaveIDs
	{
		IDs NATURE = new IDs(
				net.runelite.api.gameval.ItemID.NATURE_STAFF_CHARGED
		);
		IDs SMOKE = new IDs(
				ItemID.SMOKE_BATTLESTAFF,
				ItemID.MYSTIC_SMOKE_BATTLESTAFF
		);
		IDs DUST = new IDs(
				ItemID.DUST_BATTLESTAFF,
				ItemID.MYSTIC_DUST_BATTLESTAFF
		);
		IDs MIST = new IDs(
				ItemID.MIST_BATTLESTAFF,
				ItemID.MYSTIC_MIST_BATTLESTAFF
		);
		IDs MUD = new IDs(
				ItemID.MUD_BATTLESTAFF,
				ItemID.MYSTIC_MUD_STAFF
		);
		IDs LAVA = new IDs(
				ItemID.LAVA_BATTLESTAFF,
				ItemID.LAVA_BATTLESTAFF_PRETTY,
				ItemID.MYSTIC_LAVA_STAFF,
				ItemID.MYSTIC_LAVA_STAFF_PRETTY
		);
		IDs STEAM = new IDs(
				ItemID.STEAM_BATTLESTAFF,
				ItemID.STEAM_BATTLESTAFF_PRETTY,
				ItemID.MYSTIC_STEAM_BATTLESTAFF,
				ItemID.MYSTIC_STEAM_BATTLESTAFF_PRETTY
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
				ItemID.MYSTIC_WATER_STAFF,
				ItemID.TWINFLAME_STAFF
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
				ItemID.MYSTIC_FIRE_STAFF,
				ItemID.TWINFLAME_STAFF
		);

	}

	interface RuneIDs
	{

		IDs SMOKE = new IDs(ItemID.SMOKERUNE);
		IDs DUST = new IDs(ItemID.DUSTRUNE);
		IDs LAVA = new IDs(ItemID.LAVARUNE);
		IDs MIST = new IDs(ItemID.MISTRUNE);
		IDs MUD = new IDs(ItemID.MUDRUNE);
		IDs STEAM = new IDs(ItemID.STEAMRUNE);
		IDs AIR = new IDs(
				SMOKE,
				DUST,
				MIST,
				ItemID.AIRRUNE,
				ItemID.NZONE_AIRRUNE,
				ItemID.ROGUETRADER_AIRRUNE,
				ItemID._100GUIDE_AIRRUNE_DUM,
				ItemID.SLUG2_RUNE_AIR,
				ItemID.FAKE_AIRRUNE
		);
		IDs WATER = new IDs(
				MIST,
				MUD,
				STEAM,
				ItemID.WATERRUNE,
				ItemID.NZONE_WATERRUNE,
				ItemID.ROGUETRADER_WATERRUNE,
				ItemID._100GUIDE_WATERRUNE_DUM,
				ItemID.SLUG2_RUNE_WATER,
				ItemID.FAKE_WATERRUNE
		);
		IDs EARTH = new IDs(
				DUST,
				LAVA,
				MUD,
				ItemID.EARTHRUNE,
				ItemID.NZONE_EARTHRUNE,
				ItemID.ROGUETRADER_EARTHRUNE,
				ItemID.SLUG2_RUNE_EARTH,
				ItemID.FAKE_EARTHRUNE
		);
		IDs FIRE = new IDs(
				SMOKE,
				LAVA,
				STEAM,
				ItemID.FIRERUNE,
				ItemID.NZONE_FIRERUNE,
				ItemID.ROGUETRADER_FIRERUNE,
				ItemID._100GUIDE_FIRERUNE_DUM,
				ItemID.SLUG2_RUNE_FIRE,
				ItemID.FAKE_FIRERUNE
		);
		IDs MIND = new IDs(
				ItemID.MINDRUNE,
				ItemID.ROGUETRADER_MINDRUNE,
				ItemID.SLUG2_RUNE_MIND,
				ItemID.FAKE_MINDRUNE
		);
		IDs BODY = new IDs(
				ItemID.BODYRUNE,
				ItemID.ROGUETRADER_BODYRUNE,
				ItemID.FAKE_BODYRUNE
		);
		IDs COSMIC = new IDs(
				ItemID.COSMICRUNE,
				ItemID.FAKE_COSMICRUNE
		);
		IDs CHAOS = new IDs(
				ItemID.CHAOSRUNE,
				ItemID.NZONE_CHAOSRUNE,
				ItemID.ROGUETRADER_CHAOSRUNE,
				ItemID._100GUIDE_CHAOSRUNE_DUM,
				ItemID.FAKE_CHAOSRUNE
		);
		IDs ASTRAL = new IDs(
				ItemID.ASTRALRUNE,
				ItemID.FAKE_ASTRALRUNE
		);
		IDs NATURE = new IDs(
				ItemID.NATURERUNE,
				ItemID.FAKE_NATURERUNE
		);
		IDs LAW = new IDs(
				ItemID.LAWRUNE,
				ItemID.ROGUETRADER_LAWRUNE,
				ItemID.FAKE_LAWRUNE
		);
		IDs DEATH = new IDs(
				ItemID.DEATHRUNE,
				ItemID.NZONE_DEATHRUNE,
				ItemID.ROGUETRADER_DEATHRUNE,
				ItemID.FAKE_DEATHRUNE
		);
		IDs BLOOD = new IDs(
				ItemID.BLOODRUNE,
				ItemID.NZONE_BLOODRUNE,
				ItemID.FAKE_BLOODRUNE
		);
		IDs SOUL = new IDs(
				ItemID.SOULRUNE,
				ItemID.FAKE_SOULRUNE
		);
		IDs WRATH = new IDs(
				ItemID.WRATHRUNE,
				ItemID.FAKE_WRATHRUNE
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