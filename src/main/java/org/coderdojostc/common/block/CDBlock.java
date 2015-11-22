package org.coderdojostc.common.block;

import org.coderdojostc.common.core.CreativeTabCD;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author earbuds
 * @author dluxcode
 */
public class CDBlock extends Block {
	/**
	 * 
	 * @param material Material for the block
	 * @param hardness hardness of the material
	 * @param stepsound sound when walking
	 * @param blockname block name in game
	 */
	public CDBlock(Material material, float hardness, SoundType stepsound, String blockname) {
		super(material);
		setHardness(hardness);
		setStepSound(stepsound);
		setBlockName(blockname);
		setCreativeTab(CreativeTabCD.tabCD);
	}
	
}
