package airbreather.mods.cacophonyjanitor;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = CacophonyJanitorConstants.modID, name = CacophonyJanitorConstants.modName)
public final class CacophonyJanitorMod
{
	@Mod.EventHandler
	private void preInit(FMLPreInitializationEvent event)
	{
		GameRegistry.registerItem(ItemQuestRewardProxy.ViolenceLunch, CacophonyJanitorConstants.violenceLunchProxyItemID, CacophonyJanitorConstants.modID);
		GameRegistry.registerItem(ItemQuestRewardProxy.IndolenceLunch, CacophonyJanitorConstants.indolenceLunchProxyItemID, CacophonyJanitorConstants.modID);
		GameRegistry.registerItem(ItemQuestRewardProxy.PassionLunch, CacophonyJanitorConstants.passionLunchProxyItemID, CacophonyJanitorConstants.modID);
		GameRegistry.registerItem(ItemQuestRewardProxy.HungerLunch, CacophonyJanitorConstants.hungerLunchProxyItemID, CacophonyJanitorConstants.modID);
		GameRegistry.registerItem(ItemQuestRewardProxy.DoggieBag, CacophonyJanitorConstants.doggieBagProxyItemID, CacophonyJanitorConstants.modID);
		GameRegistry.registerItem(ItemQuestRewardProxy.RivalryLunch, CacophonyJanitorConstants.rivalryLunchProxyItemID, CacophonyJanitorConstants.modID);
		GameRegistry.registerItem(ItemQuestRewardProxy.SmokingBox, CacophonyJanitorConstants.smokingBoxProxyItemID, CacophonyJanitorConstants.modID);
	}

	@Mod.EventHandler
	private void postInit(FMLPostInitializationEvent event)
	{
		CompatibilityTweaks.runAll();
	}
}
