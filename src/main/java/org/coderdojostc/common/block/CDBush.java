package org.coderdojostc.common.block;

import java.util.List;
import java.util.Random;
import org.coderdojostc.Main;
import org.coderdojostc.client.BushRenderer;
import org.coderdojostc.common.core.CreativeTabCD;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CDBush extends BlockLeavesBase implements IPlantable {
    Random random;
    public IIcon[] fastIcons;
    public IIcon[] fancyIcons;
    public static String[] textureNames = new String[] { "bookbush", "bookbush_ripe" };
    
    private ItemStack[] drops;

    public CDBush(ItemStack[] drops) {
        super(Material.leaves, false);
        this.setTickRandomly(true);
        random = new Random();
        this.setHardness(0.3F);
        this.setStepSound(Block.soundTypeGrass);
        this.setBlockName("bush");
        this.setCreativeTab(CreativeTabCD.tabCD);
        
        this.drops = drops;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons (IIconRegister iconRegister) {
        this.fastIcons = new IIcon[textureNames.length];
        this.fancyIcons = new IIcon[textureNames.length];

        for (int i = 0; i < this.fastIcons.length; i++) {
            this.fastIcons[i] = iconRegister.registerIcon(Main.MODID.toLowerCase() + ":" + textureNames[i] + "_fast");
            this.fancyIcons[i] = iconRegister.registerIcon(Main.MODID.toLowerCase()+":" + textureNames[i] + "_fancy");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int metadata) {
        if (field_150121_P) { // Pretty sure this checks if fancy is enabled
            if (metadata < 12) {
                return fancyIcons[metadata % 4];
            }
            else {
                return fancyIcons[metadata % 4 + 4];
            }
        }
        else {
            if (metadata < 12) {
                return fastIcons[metadata % 4];
            }
            else {
                return fastIcons[metadata % 4 + 4];
            }
        }
    }

    /* Bushes are stored by size then type */
    @Override
    public int damageDropped (int metadata) {
        return metadata % 4;
    }

    /* The following methods define a berry bush's size depending on metadata */
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        
        if (l < 4) {
            return AxisAlignedBB.getBoundingBox(x + 0.25D, y, z + 0.25D, x + 0.75D, y + 0.5D, z + 0.75D);
        }
        else if (l < 8) {
            return AxisAlignedBB.getBoundingBox(x + 0.125D, y, z + 0.125D, x + 0.875D, y + 0.75D, z + 0.875D);
        }
        else {
            return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D);
        }
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool (World world, int x, int y, int z) {
        int l = world.getBlockMetadata(x, y, z);
        if (l < 4) {
            return AxisAlignedBB.getBoundingBox(x + 0.25D, y, z + 0.25D, x + 0.75D, y + 0.5D, z + 0.75D);
        }
        else if (l < 8) {
            return AxisAlignedBB.getBoundingBox(x + 0.125D, y, z + 0.125D, x + 0.875D, y + 0.75D, z + 0.875D);
        }
        else {
            return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D);
        }
    }

    @Override
    public void setBlockBoundsBasedOnState (IBlockAccess iblockaccess, int x, int y, int z) {
        int md = iblockaccess.getBlockMetadata(x, y, z);

        float minX;
        float minY = 0F;
        float minZ;
        float maxX;
        float maxY;
        float maxZ;

        if (md < 4) {
            minX = minZ = 0.25F;
            maxX = maxZ = 0.75F;
            maxY = 0.5F;
        }else if (md < 8)
        {
            minX = minZ = 0.125F;
            maxX = maxZ = 0.875F;
            maxY = 0.75F;
        }else {
            minX = minZ = 0.0F;
            maxX = maxZ = 1.0F;
            maxY = 1.0F;
        }
        
        setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }

    /* Left-click harvests berries */
    @Override
    public void onBlockClicked (World world, int x, int y, int z, EntityPlayer player) {
        if (!world.isRemote) {
        	
            int meta = world.getBlockMetadata(x, y, z);
            if (meta >= 12) {
                world.setBlock(x, y, z, this, meta - 4, 3);
                EntityItem entityitem = new EntityItem(world, player.posX, player.posY - 1.0D, player.posZ, getDrop());
                world.spawnEntityInWorld(entityitem);
                if (!(player instanceof FakePlayer))
                    entityitem.onCollideWithPlayer(player);
            }
        }
    }

    /* Right-click harvests berries */
    @Override
    public boolean onBlockActivated (World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        /*if (world.isRemote)
        	return false;*/

        int meta = world.getBlockMetadata(x, y, z);
        if (meta >= 12) {
            if (world.isRemote)
                return true;

            world.setBlock(x, y, z, this, meta - 4, 3);
            EntityItem entityitem = new EntityItem(world, player.posX, player.posY - 1.0D, player.posZ, getDrop());
            world.spawnEntityInWorld(entityitem);
            if (!(player instanceof FakePlayer))
                entityitem.onCollideWithPlayer(player);
            return true;
        }
        return false;
    }

    /* Render logic */

    @Override
    public boolean isOpaqueCube () {
        return false;
    }

    public void setGraphicsLevel (boolean flag) {
        field_150121_P = flag;
    }

    @Override
    public boolean renderAsNormalBlock () {
        return false;
    }

    @Override
    public int getRenderType () {
        return BushRenderer.berryModel;
    }

    @Override
    public boolean shouldSideBeRendered (IBlockAccess iblockaccess, int i, int j, int k, int l) {
        if (l > 7 || field_150121_P) {
            return super.shouldSideBeRendered(iblockaccess, i, j, k, l);
        }else {
            return true;
        }
    }

    /* Bush growth */

    @Override
    public void updateTick (World world, int x, int y, int z, Random random1) {
        if (world.isRemote) {
            return;
        }

        int height;

        for (height = 1; world.getBlock(x, y - height, z) == this; ++height) {
            ;
        }

        if (random1.nextInt(20) == 0 && world.getBlockLightValue(x, y, z) >= 8) {
            int md = world.getBlockMetadata(x, y, z);
            if (md < 12){
                world.setBlock(x, y, z, this, md + 4, 3);
            }
            if (random1.nextInt(3) == 0 && height < 3 && world.getBlock(x, y + 1, z) == Blocks.air && md >= 8) {
                world.setBlock(x, y + 1, z, this, md % 4, 3);
            }
        }
    }

    public boolean canSustainPlant (World world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
        if (plant instanceof CDBush)
            return (world.getBlockMetadata(x, y, z) > 7);
        return super.canSustainPlant(world, x, y, z, direction, plant);
    }

    /* Resistance to fire */

    @Override
    public int getFlammability (IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 25;
    }

    @Override
    public boolean isFlammable (IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return true;
    }

    @Override
    public int getFireSpreadSpeed (IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 4;
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks (Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 12; var4 < 16; ++var4) {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    @Override
    public EnumPlantType getPlantType (IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    @Override
    public Block getPlant (IBlockAccess world, int x, int y, int z) {
        return this;
    }

    @Override
    public int getPlantMetadata (IBlockAccess world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) - 4;
    }

    public boolean boneFertilize (World world, int x, int y, int z, Random random) {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta / 4 < 2) {
            int setMeta = random.nextInt(2) + 1 + meta / 4;
            if (setMeta > 2)
                setMeta = 2;
            world.setBlockMetadataWithNotify(x, y, z, meta % 4 + setMeta * 4, 4);
            return true;
        }

        Block block = world.getBlock(x, y + 1, z);
        if (block == null || world.isAirBlock(x, y + 1, z)) {
            if (random.nextInt(3) == 0)
                world.setBlock(x, y + 1, z, this, meta % 4, 3);

            return true;
        }

        return false;
    }
    
    private ItemStack getDrop() {
		return drops[random.nextInt()];
    }
}