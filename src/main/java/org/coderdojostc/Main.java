package org.coderdojostc;

import org.coderdojostc.common.block.CDBlock;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

@Mod(modid = "CoderDojoMod", version = "0")
public class Main {
	public static String MODID = "CoderDojoMod";
	public static String VERSION = "0";
	
	public static Block test;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {

		test = new CDBlock(Material.ground, 0.5F, Block.soundTypeGrass, "test");
		
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}
}