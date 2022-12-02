package com.arlania.world.content.itemcombiner;

import com.ruse.model.Item;

public enum CombinerData {

	// first item is crafted item, and the array is 4 requirements
	
	/*ITEM_1(new Item(4568, 1), new Item[] {new Item(1959, 261), new Item(17831, 6138), new Item(19640, 126), new Item(18189, 12)}),
	ITEM_2(new Item(20083, 1), new Item[] {new Item(1959, 361), new Item(17831, 7659), new Item(14505, 85), new Item(20422, 9)}),
	ITEM_3(new Item(22032, 1), new Item[] {new Item(1959, 563), new Item(17831, 7128), new Item(18761, 104), new Item(2396, 11)}),
	ITEM_4(new Item(6183, 1), new Item[] {new Item(1959, 565), new Item(17831, 8174), new Item(588, 89), new Item(15222, 10)});
	*/
	ITEM_1(new Item(3737, 1), new Item[] {new Item(12855, 1000000), new Item(8087, 2), new Item(4367, 1), new Item(20488, 1)}),
	ITEM_2(new Item(3720, 1), new Item[] {new Item(12855, 1000000), new Item(11320, 2), new Item(8329, 1), new Item(8335, 1)}),
	ITEM_3(new Item(3721, 1), new Item[] {new Item(12855, 1000000), new Item(11321, 2), new Item(16137, 1), new Item(11140, 1)}),
	ITEM_4(new Item(3722, 1), new Item[] {new Item(12855, 1000000), new Item(11322, 2), new Item(8334, 1), new Item(19892, 1)});
	
	CombinerData(Item craftedItem, Item[] requirements) {
		this.craftedItem = craftedItem;
		this.requirements = requirements;
	}
	
	public Item craftedItem;
	public Item[] requirements;
	
}
