package com.github.calebwhiting.runelite.api;

import com.github.calebwhiting.runelite.api.event.ItemSelectionChanged;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.runelite.api.*;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;

import java.util.Arrays;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.stream.Stream;

@Singleton
public class InventoryManager
{

	@Inject private Client client;

	@Inject private EventBus eventBus;

	// The plank sack (like the rune pouch) supplies its contents to actions without the planks
	// ever entering the inventory, so item-count lookups need to add its contents in as well.
	private static final int[] PLANK_SACK_ITEM_IDS = {
			ItemID.PLANK_SACK,
			ItemID.PLANK_SACK_25629
	};

	private static final Map<Integer, Integer> PLANK_SACK_VARBITS_BY_PLANK_ID = Map.of(
			ItemID.PLANK, VarbitID.PLANK_SACK_PLAIN,
			ItemID.OAK_PLANK, VarbitID.PLANK_SACK_OAK,
			ItemID.TEAK_PLANK, VarbitID.PLANK_SACK_TEAK,
			ItemID.MAHOGANY_PLANK, VarbitID.PLANK_SACK_MAHOGANY,
			ItemID.CAMPHOR_PLANK, VarbitID.PLANK_SACK_CAMPHOR,
			ItemID.IRONWOOD_PLANK, VarbitID.PLANK_SACK_IRONWOOD,
			ItemID.ROSEWOOD_PLANK, VarbitID.PLANK_SACK_ROSEWOOD
	);

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked evt)
	{
		if (evt.getMenuAction() != MenuAction.WIDGET_TARGET) {
			return;
		}
		ItemContainer inventory = this.client.getItemContainer(InventoryID.INVENTORY);
		if (inventory == null) {
			return;
		}
		Item item = inventory.getItem(evt.getParam0());
		this.eventBus.post(new ItemSelectionChanged(item));
	}

	public Stream<Item> getItems()
	{
		ItemContainer inventory = this.client.getItemContainer(InventoryID.INVENTORY);
		if (inventory == null) {
			return Stream.empty();
		}
		return Stream.of(inventory.getItems());
	}

	public int getFreeSpaces()
	{
		ItemContainer container = this.client.getItemContainer(InventoryID.INVENTORY);
		if (container == null) {
			return 0;
		}
		int free = 28;
		for (Item item : container.getItems()) {
			if (item.getId() >= 0) {
				free--;
			}
		}
		return free;
	}

	public int getItemCount(IntPredicate idPredicate)
	{
		return this.getItems().filter(it -> idPredicate.test(it.getId())).mapToInt(Item::getQuantity).sum();
	}

	public int getItemCountById(int... ids)
	{
		if (ids.length == 0) {
			throw new IllegalArgumentException("Must specify at least one item ID");
		}
		int[] copy = ids.clone();
		Arrays.sort(copy);
		int count = this.getItemCount(id -> Arrays.binarySearch(copy, id) >= 0);
		for (int id : Arrays.stream(copy).distinct().toArray()) {
			count += this.getPlankSackCount(id);
		}
		return count;
	}

	private int getPlankSackCount(int plankItemId)
	{
		Integer varbitId = PLANK_SACK_VARBITS_BY_PLANK_ID.get(plankItemId);
		if (varbitId == null) {
			return 0;
		}
		boolean carryingSack = this.getItems()
				.mapToInt(Item::getId)
				.anyMatch(id -> Arrays.stream(PLANK_SACK_ITEM_IDS).anyMatch(sackId -> sackId == id));
		if (!carryingSack) {
			return 0;
		}
		return this.client.getVarbitValue(varbitId);
	}

}
