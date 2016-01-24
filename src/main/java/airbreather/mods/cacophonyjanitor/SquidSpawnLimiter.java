package airbreather.mods.cacophonyjanitor;

import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.world.World;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public final class SquidSpawnLimiter
{
	private int skipCounter = CacophonyJanitorConfiguration.SKIPS_BEFORE_SQUID_SPAWN + 1;

	@SubscribeEvent
	public void onCheckEntitySpawn(LivingSpawnEvent.CheckSpawn event)
	{
		if (!(event.entity instanceof EntitySquid) || CacophonyJanitorConfiguration.SKIPS_BEFORE_SQUID_SPAWN == 0)
		{
			return;
		}

		// not completely thread-safe, but probably won't explode.
		if (--this.skipCounter > 0)
		{
			event.setResult(Result.DENY);
		}
		else
		{
			this.skipCounter = CacophonyJanitorConfiguration.SKIPS_BEFORE_SQUID_SPAWN + 1;
		}
	}
}
