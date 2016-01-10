package airbreather.mods.cacophonyjanitor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.oredict.RecipeSorter;

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
	private void init(FMLInitializationEvent event)
	{
		GameRegistry.addRecipe(new ItemQuestRewardRecipe.ViolenceLunch());
		GameRegistry.addRecipe(new ItemQuestRewardRecipe.IndolenceLunch());
		GameRegistry.addRecipe(new ItemQuestRewardRecipe.PassionLunch());
		GameRegistry.addRecipe(new ItemQuestRewardRecipe.HungerLunch());
		GameRegistry.addRecipe(new ItemQuestRewardRecipe.DoggieBag());
		GameRegistry.addRecipe(new ItemQuestRewardRecipe.RivalryLunch());
		GameRegistry.addRecipe(new ItemQuestRewardRecipe.SmokingBox());
		RecipeSorter.register("cacophonyjanitor:questrewardproxy", ItemQuestRewardRecipe.class, RecipeSorter.Category.SHAPELESS, "after:minecraft:shapeless");
	}
}
