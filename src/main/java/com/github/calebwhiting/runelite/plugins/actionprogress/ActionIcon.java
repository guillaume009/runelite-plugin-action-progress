package com.github.calebwhiting.runelite.plugins.actionprogress;

import com.github.calebwhiting.runelite.api.ui.IconSource;
import com.github.calebwhiting.runelite.api.ui.ItemIconSource;
import com.github.calebwhiting.runelite.api.ui.SpriteIconSource;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.SpriteID;

public interface ActionIcon
{
	IconSource SPRITE_TOTAL = new SpriteIconSource(SpriteID.Staticons2.TOTAL, 0);
	IconSource SPRITE_CRAFTING = new SpriteIconSource(SpriteID.Staticons.CRAFTING, 0);
	IconSource SPRITE_FISHING = new SpriteIconSource(SpriteID.Staticons.FISHING, 0);
	IconSource SPRITE_WOODCUTTING = new SpriteIconSource(SpriteID.Staticons.WOODCUTTING, 0);
	IconSource SPRITE_COOKING = new SpriteIconSource(SpriteID.Staticons.COOKING, 0);
	IconSource SPRITE_PRAYER = new SpriteIconSource(SpriteID.Staticons.PRAYER, 0);
	IconSource SPRITE_FLETCHING = new SpriteIconSource(SpriteID.Staticons.FLETCHING, 0);
	IconSource SPRITE_FIREMAKING = new SpriteIconSource(SpriteID.Staticons.FIREMAKING, 0);
	IconSource SPRITE_SMITHING = new SpriteIconSource(SpriteID.Staticons.SMITHING, 0);
	IconSource SPRITE_MAGIC = new SpriteIconSource(SpriteID.Staticons.MAGIC, 0);
	IconSource SPRITE_HERBLORE = new SpriteIconSource(SpriteID.Staticons.HERBLORE, 0);
	IconSource SPRITE_GUARDIAN_OF_THE_RIFT_REWARD = new ItemIconSource(ItemID.ABYSSALPET);
	IconSource SPRITE_FARMING = new SpriteIconSource(SpriteID.Staticons2.FARMING, 0);
	IconSource SPRITE_BUCKET = new ItemIconSource(ItemID.BUCKET_EMPTY);//1925
	IconSource SPRITE_SALVAGE = new ItemIconSource(ItemID.SAILING_LARGE_SHIPWRECK_SALVAGE);
	IconSource SPRITE_CONSTRUCTION = new ItemIconSource(SpriteID.Staticons2.CONSTRUCTION);
	IconSource SPRITE_INCENDIARY = new ItemIconSource(ItemID.RUNE_INCENDIARY_CANNONBALL);
	IconSource SPRITE_GOLEM_CRAFTING = new ItemIconSource(ItemID.SUNSTONE_CORE);

}
