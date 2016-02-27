package org.coderdojostc.common.block;

import org.coderdojostc.common.BushDropTables;
import org.coderdojostc.common.item.block.CDBushItem;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * 
 * @author Earbuds
 *
 */
public class CDBlocks {
	
	public static CDBush blockBush;
	// Used for initializing blocks, put them here
	public static void init() {
		//block = new CDBlock(Material.ground, Block.soundTypeGrass, "testB", 1.0F, 1.0F, "pickaxe", 1);
		blockBush = new CDBush(BushDropTables.drops);
	}
	
	// Used for registering blocks, put them here
	public static void registerBlocks() {
		//GameRegistry.registerBlock(block, "block");
		GameRegistry.registerBlock(blockBush, CDBushItem.class, "blockBushBase");
	}
	
}
