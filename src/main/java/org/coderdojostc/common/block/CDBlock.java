package org.coderdojostc.common.block;

import org.coderdojostc.common.core.CreativeTabCD;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;

/**
 * @author Earbuds
 * @author dluxcode
 */
public class CDBlock extends Block {
	/**
	 * 
	 * @param material The material type of the block.
	 * @param sound The sound the block makes when walking on it, digging etc.
	 * @param blockname The name of the block in your inventory.
	 * @param hardness How long the block takes to break.
	 * @param resistance Blast resistance of block.
	 * @param harvestTool Tool used to harvest this block.
	 * @param harvestLevel The harvest level of the block.
	 */
	public CDBlock(Material material, SoundType sound, String blockName,
			float hardness, float resistance, String harvestTool, int harvestLevel) {
		super(material);
		this.setStepSound(sound);
		this.setBlockName(blockName);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setHarvestLevel(harvestTool, harvestLevel);
		this.setCreativeTab(CreativeTabCD.tabCD);
	}
	
}
