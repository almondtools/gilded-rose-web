package com.gildedrose;


import org.junit.jupiter.api.Test;

import net.amygdalum.goldenmaster.Expectations;
import net.amygdalum.goldenmaster.GoldenMasterTest;

class GildedRoseTest {

    @GoldenMasterTest(store = "src/test/resources")
    @Test
    void testAgedBrie1(Expectations expectations) {
        Item item = new Item("Aged Brie", 2, 0);

        expectations.validate(age(item));
    }

    @GoldenMasterTest(store = "src/test/resources")
    @Test
    void testAgedBrie2(Expectations expectations) {
        Item item = new Item("Aged Brie", -1, 49);

        expectations.validate(age(item));
    }

    @GoldenMasterTest(store = "src/test/resources")
    @Test
    void testDexterityWest(Expectations expectations) {
        Item item = new Item("+5 Dexterity Vest", 10, 51);

        expectations.validate(age(item));
    }

    @GoldenMasterTest(store = "src/test/resources")
    @Test
    void testElixir(Expectations expectations) {
        Item item = new Item("Elixir of the Mongoose", 5, 7);

        expectations.validate(age(item));
    }

    @GoldenMasterTest(store = "src/test/resources")
    @Test
    void testHand1(Expectations expectations) {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 0, 80);

        expectations.validate(age(item));
    }

    @GoldenMasterTest(store = "src/test/resources")
    @Test
    void testHand2(Expectations expectations) {
        Item item = new Item("Sulfuras, Hand of Ragnaros", -1, 80);

        expectations.validate(age(item));
    }

    @GoldenMasterTest(store = "src/test/resources")
    @Test
    void testBackstagePass1(Expectations expectations) {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);

        expectations.validate(age(item));
    }

    @GoldenMasterTest(store = "src/test/resources")
    @Test
    void testBackstagePass2(Expectations expectations) {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);

        expectations.validate(age(item));
    }

    @GoldenMasterTest(store = "src/test/resources")
    @Test
    void testBackstagePass3(Expectations expectations) {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);

        expectations.validate(age(item));
    }

    private String age(Item item) {
        StringBuilder buffer = new StringBuilder();

        GildedRose gildedRose = new GildedRose(new Item[] { item });

        for (int i = 0; i < 20; i++) {
            gildedRose.updateQuality();
            buffer.append(item.toString()).append("\n");
        }

        return buffer.toString();
    }
}
