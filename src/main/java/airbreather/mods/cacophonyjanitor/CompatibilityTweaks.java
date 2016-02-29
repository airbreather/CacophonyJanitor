package airbreather.mods.cacophonyjanitor;

import java.util.ArrayList;

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
}
