package org.coderdojostc.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CDTileEntityContainer extends BlockContainer {

	public CDTileEntityContainer(Material material_, float hardness, float resistance, String harvestTool, int harvestLevel){
		super(material_);
		// TODO Auto-generated constructor stub
		String placeholder = "";
		//this.setUnlocalizedName(placeholder);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setHarvestLevel(harvestTool, harvestLevel);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new CDTileEntity();
	}

}
