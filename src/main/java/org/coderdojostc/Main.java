package org.coderdojostc;

import org.coderdojostc.common.CommonProxy;
import org.coderdojostc.common.block.CDBlock;
import org.coderdojostc.common.block.CDBlocks;
import org.coderdojostc.common.block.tileentity.CDTileEntities;
import org.coderdojostc.common.item.CDItems;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

@Mod(modid = "CoderDojoMod", version = "0")
public class Main {
	public static String MODID = "CoderDojoMod";
	public static String VERSION = "0";
	
	@SidedProxy(clientSide="org.coderdojostc.client.ClientProxy", serverSide="org.coderdojostc.common.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		
		
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

		CDItems.init();
		CDBlocks.init();
		CDTileEntities.init();
		
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

		CDItems.registerItems();
		CDBlocks.registerBlocks();
		
	}
}