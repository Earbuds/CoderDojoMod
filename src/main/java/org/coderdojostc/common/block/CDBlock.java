package org.coderdojostc.common.block;

import org.coderdojostc.common.core.CreativeTabCD;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CDBlock extends Block {

	public CDBlock(Material material, float hardness, SoundType stepsound, String blockname) {
		super(material);
		setHardness(hardness);
		setStepSound(stepsound);
		setBlockName(blockname);
		setCreativeTab(CreativeTabCD.tabCD);
	}
	
}
