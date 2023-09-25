//Copyright (c) 2023, Paul Moore
//All rights reserved.
//
//This source code is licensed under the BSD-style license found in the
//LICENSE file in the root directory of this source tree. 

const {Shop, Item} = require("../src/gilded_rose");

describe("Gilded Rose", function () {

    it("Generic item quality decreases twice as fast after sellIn date reached", () => {
        const sut = new Shop([new Item("generic item", 0, 10)]);

        const items = sut.updateQuality();

        expect(items[0].quality).toBe(8);
    });


    it("Aged Brie quality improves twice as fast after sellIn date reached", () => {
        const sut = new Shop([new Item("Aged Brie", 0, 30)]);

        const items = sut.updateQuality();

        expect(items[0].quality).toBe(32);
    });


    it("Backstage pass quality drops to zero when concert has passed", () => {
        const sut = new Shop([new Item("Backstage passes to a TAFKAL80ETC concert", 0, 23)]);

        const items = sut.updateQuality();

        expect(items[0].quality).toBe(0);
    });

});
