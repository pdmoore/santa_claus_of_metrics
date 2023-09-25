#Copyright (c) 2023, Pauk Moore
#All rights reserved.
#
#This source code is licensed under the BSD-style license found in the
#LICENSE file in the root directory of this source tree. 

# -*- coding: utf-8 -*-
import unittest

from gilded_rose import Item, GildedRose


class GildedRoseTest(unittest.TestCase):

    def test_generic_item_quality_decreases_twice_as_fast_after_sell_by(self):
        items = [Item("generic item", 0, 10)]
        sut = GildedRose(items)

        sut.update_quality()

        self.assertEqual(8, items[0].quality)

    def test_brie_quality_increases_twice_as_fast_after_sell_by(self):
        items = [Item("Aged Brie", -1, 20)]
        sut = GildedRose(items)

        sut.update_quality()

        self.assertEqual(22, items[0].quality)

    def test_backstage_pass_quality_drops_to_zero_when_concert_has_passed(self):
        items = [Item("Backstage passes to a TAFKAL80ETC concert", 0, 40)]
        sut = GildedRose(items)

        sut.update_quality()

        self.assertEqual(0, items[0].quality)

if __name__ == '__main__':
    unittest.main()
