package org.coderdojostc.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabCD {

	public static CreativeTabs tabCD = new CreativeTabs("Coder Dojo") {
		@Override
		@SideOnly(Side.CLIENT)
		
		public Item getTabIconItem() {
			return Items.ender_eye;
		}
	};
	
}
