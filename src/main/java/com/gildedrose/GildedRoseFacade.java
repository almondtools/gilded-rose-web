package com.gildedrose;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class GildedRoseFacade extends GildedRose {

	public GildedRoseFacade(Item[] items) {
		super(items);
	}

	public GildedRoseFacade() {
		this(new Item[0]);
	}

	public void setItems(Item[] newItems) {
		items = newItems;
	}

	public void addItem(Item item) {
		Item[] newItems = new Item[items.length + 1];
		System.arraycopy(items, 0, newItems, 0, items.length);
		newItems[items.length] = item;

		items = newItems;
	}

	public Item createItem(String name, int sellIn, int quality) {
		return new Item(name, sellIn, quality);
	}

	public List<WrappedItem> getItems() {
		return Arrays.stream(items)
			.map(item -> new WrappedItem(item.name, item.quality, item.sellIn))
			.collect(toList());
	}

	public void removeItem(int index) {
		if (items.length > index) {
			Item[] newItems = new Item[items.length - 1];
			System.arraycopy(items, 0, newItems, 0, index);
			if (index + 1 < items.length) {
				System.arraycopy(items, index + 1, newItems, index, newItems.length - index);
			}

			items = newItems;
		} else {
			throw new RuntimeException("cannot remove item index " + index + " because there are only " + items.length + " items.");
		}
	}

}
