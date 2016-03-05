package airbreather.mods.cacophonyjanitor;

import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

import cpw.mods.fml.common.registry.EntityRegistry;

import net.minecraftforge.event.entity.living.LivingSpawnEvent;

final class CompatibilityTweaks
{
	private CompatibilityTweaks()
	{
	}

	public static void runAll()
	{
		// Aquatic Abyss goes a little bit overboard on all the biomes its fish
		// can spawn in... Twilight Forest biomes prove to be problematic.
		removeAquaticAbyssSpawnsFromTwilightForestBiomes();

		// Adding in Squidless seems to have made nautilus really take over.
		// let's decrease these critters below squids.
		decreaseNautilusSpawnRate();
	}

	private static void removeAquaticAbyssSpawnsFromTwilightForestBiomes()
	{
		try
		{
			Class<?> clazzTFBiomeBase = Class.forName("twilightforest.biomes.TFBiomeBase");
			BiomeGenBase[] allBiomes = BiomeGenBase.getBiomeGenArray();
			ArrayList<BiomeGenBase> twilightForestBiomeList = new ArrayList<BiomeGenBase>();
			for (int i = 0; i < allBiomes.length; i++)
			{
				BiomeGenBase biome = allBiomes[i];
				if (biome != null && clazzTFBiomeBase.isInstance(biome))
				{
					twilightForestBiomeList.add(biome);
				}
			}

			if (twilightForestBiomeList.size() > 0)
			{
				BiomeGenBase[] twilightForestBiomes = new BiomeGenBase[twilightForestBiomeList.size()];
				twilightForestBiomes = twilightForestBiomeList.toArray(twilightForestBiomes);
				EntityRegistry.removeSpawn("aquaticabyss.fish", EnumCreatureType.waterCreature, twilightForestBiomes);
				EntityRegistry.removeSpawn("aquaticabyss.butterflyfish", EnumCreatureType.waterCreature, twilightForestBiomes);
			}
		}
		catch (ClassNotFoundException ex)
		{
			// means they removed Twilight Forest... I mean, good luck, but...
		}
	}

	private static void decreaseNautilusSpawnRate()
	{
		try
		{
			Class<? extends EntityLiving> nautilusClazz = (Class<? extends EntityLiving>)Class.forName("mods.fossil.entity.mob.EntityNautilus");
			BiomeGenBase[] spawnBiomes = new BiomeGenBase[]
			{
				BiomeGenBase.river,
				BiomeGenBase.ocean
			};

			EntityRegistry.removeSpawn(nautilusClazz, EnumCreatureType.waterCreature, spawnBiomes);

			// unadulterated spawn is 5, 4, 14
			// vanilla squid spawn is 10, 4, 4
			// squidless changes ^ to 10, 2, 2 (I think)
			// so let's make nautilus 2, 1, 2
			EntityRegistry.addSpawn(nautilusClazz, 2, 1, 2, EnumCreatureType.waterCreature, spawnBiomes);
		}
		catch (ClassNotFoundException ex)
		{
		}
	}
}
