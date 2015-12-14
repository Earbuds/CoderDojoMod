package org.coderdojostc.common.block.item;

import org.coderdojostc.common.core.CreativeTabCD;

import net.minecraft.item.Item;

/**
 * 
 * @author Earbuds
 *
 */
public class CDItem extends Item {

	//Generic item for Coderdojo mod, put edits you want for all items in here
	public CDItem(int stackSize, String itemName) {
		this.setMaxStackSize(stackSize);
		this.setUnlocalizedName(itemName);
		this.setCreativeTab(CreativeTabCD.tabCD);
	}
	
}
