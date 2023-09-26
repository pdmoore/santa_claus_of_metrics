//Copyright (c) 2023, Paul Moore
//All rights reserved.
//
//This source code is licensed under the BSD-style license found in the
//LICENSE file in the root directory of this source tree.

package gildedrose;

import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.reporters.UseReporter;
import org.approvaltests.reporters.macosx.DiffMergeReporter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the following line to use DiffMerge when Received/Approved differ
//@UseReporter(DiffMergeReporter.class)
public class GildedRoseApprovalTests {

    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    public static final String LEGENDARY = "Sulfuras, Hand of Ragnaros";

    @Test
    public void test_UpdateQuality_SingleItem_AtSellin_NoQuality() {
        // Arrange
        Item[] items = new Item[]{new Item("normal", 0, 0)};
        GildedRose sut = new GildedRose(items);

        // Act
        sut.updateQuality();

        // Assert
        // The Verify call creates a file named GildedRoseApprovalTests.OneItemApproval.received.txt
        // and compares it against the Approved result file, GildedRoseApprovalTests.OneItemApproval.approved.txt
        // If the contents of the two files match, the test passes
        // otherwise the test fails and the diff of the two files is shown
        Approvals.verify(sut.items[0].toString());
    }

    @Test
    public void test_UpdateQuality_SingleItem() {
        CombinationApprovals.verifyAllCombinations(
                this::doUpdateQuality,
                new String[] {"foo", "Aged Brie", BACKSTAGE_PASS, LEGENDARY},
                new Integer[] {-1, 0, 1, 2, 3, 4, 5, 6, 9, 10, 11},  // SellIn
                new Integer[] { 0, 1, 5, 10, 49, 50 }                // Quality
        );
    }

    private String doUpdateQuality(String name, int sellIn, int quality) {
        Item[] items = new Item[]{new Item(name, sellIn, quality)};
        GildedRose sut = new GildedRose(items);
        sut.updateQuality();
        return sut.items[0].toString();
    }
}
