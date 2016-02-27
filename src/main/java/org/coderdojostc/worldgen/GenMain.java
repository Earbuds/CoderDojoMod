package org.coderdojostc.worldgen;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

/* Adds crops to the overworld
 * Current crops: Barley, Berry bushes
 */

public class GenMain implements IWorldGenerator {

	BushGen baseGen;
	
    public GenMain() {
    	baseGen = new BushGen(0, 64 + 64); // metadata, sealevel, spawn range
    }

    @Override
    public void generate (Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        final int xChunk = chunkX * 16 + 8, zChunk = chunkZ * 16 + 8;
        int xCh = chunkX * 16 + random.nextInt(16);
        int yCh = random.nextInt(128);
        int zCh = chunkZ * 16 + random.nextInt(16);

        BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(chunkX * 16, chunkZ * 16);

        //Berry bushes
        if (random.nextInt(30) == 0 && goodClimate(biome, 0.6f, 2.0f, 0.2f, 0.93f)) // Nextint is rarity
        {
            xCh = xChunk + random.nextInt(16);
            yCh = random.nextInt(64) + 64; // Spawn range + sea level
            zCh = zChunk + random.nextInt(16);
            baseGen.generate(world, random, xCh, yCh, zCh);
        }
    }

    public boolean goodClimate (BiomeGenBase biome, float minTemp, float maxTemp, float minRain, float maxRain)
    {
        float temp = biome.temperature;
        float rain = biome.rainfall;
        if (minTemp <= temp && temp <= maxTemp && minRain <= rain && rain <= maxRain)
            return true;

        return false;
    }
}