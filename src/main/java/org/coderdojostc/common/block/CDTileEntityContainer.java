package org.coderdojostc.common.block;

import org.coderdojostc.common.core.CreativeTabCD;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * @author DLuxCru
 *The CDTileEntityContainer class functions
 * as something of a link between blocks and their associated tile entities.
 */
public class CDTileEntityContainer extends BlockContainer {
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
	public CDTileEntityContainer(Material material, SoundType sound, String blockname,
			float hardness, float resistance, String harvestTool, int harvestLevel){
		super(material);
		// TODO Auto-generated constructor stub
		this.setStepSound(sound);
		this.setBlockName(blockname);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setHarvestLevel(harvestTool, harvestLevel);
		this.setCreativeTab(CreativeTabCD.tabCD);
	}
	/**
	 * @return returns a tile entity of the CDTileEntity type.
	 */
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		// TODO Auto-generated method stub
		return new CDTileEntity();
	}

}
