package com.gildedrose;

public class WrappedItem {

	private String name;
	private int quality;
	private int sellIn;

	public WrappedItem(String name, int quality, int sellIn) {
		this.name = name;
		this.quality = quality;
		this.sellIn = sellIn;
	}

	public String getName() {
		return name;
	}

	public int getQuality() {
		return quality;
	}

	public int getSellIn() {
		return sellIn;
	}

}
