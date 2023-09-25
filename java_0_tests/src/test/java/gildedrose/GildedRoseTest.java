//Copyright (c) 2023, Paul Moore
//All rights reserved.
//
//This source code is licensed under the BSD-style license found in the
//LICENSE file in the root directory of this source tree.

package gildedrose;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTest {

    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    @Disabled
    void AgedBrie_QualityIncreases_EvenAfterSellInDate() {
        GildedRose sut = new GildedRose(createItemArray("Aged Brie", -1, 20));

        sut.updateQuality();

        assertEquals(22, sut.items[0].quality, "Aged Brie improves twice as fast after sellIn date (BUG?)");
    }

    @Test
    @Disabled
    void BackStagePass_QualityDropsToZeroWhenConcertPasses() {
        GildedRose sut = new GildedRose(createItemArray(BACKSTAGE_PASS, 0, 40));

        sut.updateQuality();

        assertEquals(0, sut.items[0].quality, "Backstage Pass is worthless when concert has passed");
    }

    @Test
    @Disabled
    void GenericItem_QualityDecreasesTwiceAsFastAfterSellInDate() {
        GildedRose sut = new GildedRose(createItemArray("generic item", 0, 10));

        sut.updateQuality();

        assertEquals(8, sut.items[0].quality, "When sellIn date is 0 then quality decreases twice as fast");
    }

    private Item[] createItemArray(String itemName, int sellIn, int quality) {
        return new Item[] { new Item(itemName, sellIn, quality) };
    }
}
