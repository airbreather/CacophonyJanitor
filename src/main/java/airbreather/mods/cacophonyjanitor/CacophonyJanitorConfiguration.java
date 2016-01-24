package airbreather.mods.cacophonyjanitor;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

final class CacophonyJanitorConfiguration
{
	public static int SKIPS_BEFORE_SQUID_SPAWN;

	public static void init(File configurationFile)
	{
		Configuration forgeConfiguration = new Configuration(configurationFile);
		forgeConfiguration.load();
		SKIPS_BEFORE_SQUID_SPAWN = getSkipsBeforeSquidSpawn(forgeConfiguration);
		if (forgeConfiguration.hasChanged())
		{
			forgeConfiguration.save();
		}
	}

	private static int getSkipsBeforeSquidSpawn(Configuration forgeConfiguration)
	{
		String skipsBeforeSquidSpawnPropertyName = "skipsBeforeSquidSpawn";
		int skipsBeforeSquidSpawnDefault = CacophonyJanitorConstants.defaultSkipsBeforeSquidSpawn;
		String skipsBeforeSquidSpawnComment = "Number of times to reject what would be a squid spawn before accepting one.  Min = 0, Max = 1000." + Configuration.NEW_LINE + 
		                                      "Default is " + skipsBeforeSquidSpawnDefault + ".  Vanilla is 0.  If this doesn't solve our problem, set it back to 0.";

		return forgeConfiguration.get(Configuration.CATEGORY_GENERAL, skipsBeforeSquidSpawnPropertyName, skipsBeforeSquidSpawnDefault, skipsBeforeSquidSpawnComment)
		                         .setMinValue(0)
		                         .setMaxValue(1000)
		                         .getInt(skipsBeforeSquidSpawnDefault);
	}
}
