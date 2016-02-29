package airbreather.mods.cacophonyjanitor;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public final class TwilightForestAquaticAbyssGate
{
	private boolean initialized = false;
	private Class<?> clazzCommonFish;
	private Class<?> clazzButterflyfish;

	@SubscribeEvent
	public void onCheckEntitySpawn(LivingSpawnEvent.CheckSpawn event)
	{
		// most impactful short-circuit first: overworld is unaffected.
		if (event.world.provider.dimensionId == 0)
		{
			return;
		}

		// we're not in the overworld, so deny Aquatic Abyss entity spawns.
		if (this.clazzCommonFish == null)
		{
			if (this.initialized)
			{
				// someone removed Aquatic Abyss from the modpack.
				return;
			}

			try
			{
				// assumption: we're only called after all mods are loaded.
				this.clazzCommonFish = Class.forName("thehippomaster.aquaticabyss.CommonFish");
				this.clazzButterflyfish = Class.forName("thehippomaster.aquaticabyss.Butterflyfish");
				this.initialized = true;
			}
			catch (ClassNotFoundException ex)
			{
				// someone removed Aquatic Abyss from the modpack.
				this.clazzCommonFish = null;
				this.clazzButterflyfish = null;
				this.initialized = true;
				return;
			}
		}

		if (this.clazzCommonFish.isInstance(event.entity) ||
		    this.clazzButterflyfish.isInstance(event.entity))
		{
			event.setResult(Result.DENY);
		}
	}
}
