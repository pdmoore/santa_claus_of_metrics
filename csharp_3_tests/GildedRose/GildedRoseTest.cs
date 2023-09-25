#Copyright (c) 2023, Paul Moore
#All rights reserved.
#
#This source code is licensed under the BSD-style license found in the
#LICENSE file in the root directory of this source tree. 

using NUnit.Framework;
using System.Collections.Generic;

namespace GildedRose
{
    [TestFixture]
    public class GildedRoseTest
    {
        private const string BackstagePass = "Backstage passes to a TAFKAL80ETC concert";

        private List<Item> createItemList(string itemName, int sellIn, int quality) {
            return new List<Item> { new Item { Name = itemName, SellIn = sellIn, Quality = quality } };
        }        

        [Test]
        public void AgedBrie_QualityIncreases_EvenAfterSellInDate() {
            GildedRose sut = new GildedRose(createItemList("Aged Brie", -1, 20));
            sut.UpdateQuality();
            Assert.AreEqual(22, sut.Items[0].Quality, "Aged Brie improves twice as fast after sellin date (BUG?)");
        }

        [Test]
        public void BackStagePass_QualityDropsToZeroWhenConcertPasses() {
            GildedRose sut = new GildedRose(createItemList(BackstagePass, 0, 40));
            sut.UpdateQuality();
            Assert.AreEqual(0, sut.Items[0].Quality, "Backstage Pass is worthless when concert has passed");
        }
        
        [Test]
        public void GenericItem_QualityDecreasesTwiceAsFastAfterSellinDate() {
            GildedRose sut = new GildedRose(createItemList("generic item", 0, 10));
            sut.UpdateQuality();
            Assert.AreEqual(8, sut.Items[0].Quality, "When sellin date is 0 then quality decreases twice as fast");
        }
    
    }
}
