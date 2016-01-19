package airbreather.mods.cacophonyjanitor;

import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.*;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class ItemQuestRewardProxy extends Item
{
	private final String displayNameKey;
	private boolean lunchbag;

	protected ItemQuestRewardProxy(String displayNameKey, boolean lunchbag)
	{
		this.setMaxStackSize(1);
		this.setUnlocalizedName(displayNameKey);
		this.setTextureName(lunchbag ? "SpiceOfLife:lunchbag" : "minecraft:blaze_powder");
		this.displayNameKey = "item." + displayNameKey + ".name";
		this.lunchbag = lunchbag;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced)
	{
		tooltip.add("Right-click to claim.");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player)
	{
		Item resultItem = this.lunchbag
			? GameRegistry.findItem("SpiceOfLife", "lunchbag")
			: GameRegistry.findItem("ThermalExpansion", "Strongbox");

		NBTTagList itemsTag = new NBTTagList();
		this.appendItems(itemsTag);

		NBTBase inventoryTag = itemsTag;
		if (this.lunchbag)
		{
			NBTTagCompound inventoryTagCompound = new NBTTagCompound();
			inventoryTagCompound.setTag("Items", itemsTag);
			inventoryTag = inventoryTagCompound;
		}

		ItemStack resultItemStack = new ItemStack(resultItem);
		resultItemStack.stackSize = 1;
		resultItemStack.setItemDamage(this.lunchbag ? 0 : 1);

		NBTTagCompound finalTag = new NBTTagCompound();
		finalTag.setTag("Inventory", inventoryTag);
		if (this.lunchbag)
		{
			finalTag.setInteger("RepairCost", 2);
			finalTag.setString("UUID", "5d7c5abf-8c4f-41e7-beaf-42cc0836f47b");
			finalTag.setByte("Open", (byte)0);
		}

		resultItemStack.setTagCompound(finalTag);
		resultItemStack.setStackDisplayName(LanguageRegistry.instance().getStringLocalization(this.displayNameKey));
		return resultItemStack;
	}

	protected abstract void appendItems(NBTTagList tag);

	protected void appendItem(NBTTagList tagList, int slot, String modID, String itemID, int damage, int count)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("Slot", slot);
		tag.setShort("id", (short)Item.getIdFromItem(GameRegistry.findItem(modID, itemID)));
		tag.setByte("Count", (byte)count);
		tag.setShort("Damage", (short)damage);
		tagList.appendTag(tag);
	}

	public static final class ViolenceLunchProxy extends ItemQuestRewardProxy
	{
		public ViolenceLunchProxy()
		{
			super("lunchViolence", true);
		}

		@Override
		protected void appendItems(NBTTagList tagList)
		{
			int slot = 0;
			this.appendItem(tagList, slot++, "minecraft", "beef", 0, 2);
			this.appendItem(tagList, slot++, "BinnieCore", "containerGlass", 516, 2);
			this.appendItem(tagList, slot++, "harvestcraft", "muttonrawItem", 0, 2);
		}
	}

	public static final class IndolenceLunchProxy extends ItemQuestRewardProxy
	{
		public IndolenceLunchProxy()
		{
			super("lunchIndolence", true);
		}

		@Override
		protected void appendItems(NBTTagList tagList)
		{
			int slot = 0;
			this.appendItem(tagList, slot++, "harvestcraft", "lemonlimesodaItem", 0, 2);
			this.appendItem(tagList, slot++, "harvestcraft", "lemonlimesodaItem", 0, 2);
			this.appendItem(tagList, slot++, "harvestcraft", "jaffaItem", 0, 2);
		}
	}

	public static final class PassionLunchProxy extends ItemQuestRewardProxy
	{
		public PassionLunchProxy()
		{
			super("lunchPassion", true);
		}

		@Override
		protected void appendItems(NBTTagList tagList)
		{
			int slot = 0;
			this.appendItem(tagList, slot++, "harvestcraft", "honeycombchocolatebarItem", 0, 2);
			this.appendItem(tagList, slot++, "harvestcraft", "strawberryicecreamItem", 0, 1);
			this.appendItem(tagList, slot++, "Forestry", "honeyPot", 0, 2);
		}
	}

	public static final class HungerLunchProxy extends ItemQuestRewardProxy
	{
		public HungerLunchProxy()
		{
			super("lunchHunger", true);
		}

		@Override
		protected void appendItems(NBTTagList tagList)
		{
			int slot = 0;
			this.appendItem(tagList, slot++, "harvestcraft", "supremepizzaItem", 0, 1);
			this.appendItem(tagList, slot++, "harvestcraft", "delightedmealItem", 0, 1);
			this.appendItem(tagList, slot++, "harvestcraft", "heartybreakfastItem", 0, 1);
		}
	}

	public static final class RivalryLunchProxy extends ItemQuestRewardProxy
	{
		public RivalryLunchProxy()
		{
			super("lunchRivalry", true);
		}

		@Override
		protected void appendItems(NBTTagList tagList)
		{
			int slot = 0;
			this.appendItem(tagList, slot++, "harvestcraft", "chaiteaItem", 0, 1);
			this.appendItem(tagList, slot++, "harvestcraft", "deluxechickencurryItem", 0, 1);
			this.appendItem(tagList, slot++, "harvestcraft", "peppermintItem", 0, 1);
		}
	}

	public static final class DoggieBagProxy extends ItemQuestRewardProxy
	{
		public DoggieBagProxy()
		{
			super("doggieBag", true);
		}

		@Override
		protected void appendItems(NBTTagList tagList)
		{
			int slot = 0;
			this.appendItem(tagList, slot++, "harvestcraft", "lambwithmintsauceItem", 0, 1);
			this.appendItem(tagList, slot++, "harvestcraft", "baconwrappeddatesItem", 0, 2);
			this.appendItem(tagList, slot++, "BinnieCore", "containerGlass", 394, 1);
		}
	}

	public static final class SmokingBoxProxy extends ItemQuestRewardProxy
	{
		public SmokingBoxProxy()
		{
			super("smokingBox", false);
		}

		@Override
		protected void appendItems(NBTTagList tagList)
		{
			int slot = 0;
			this.appendItem(tagList, slot++, "minecraft", "fire", 0, 1);
			this.appendItem(tagList, slot++, "BiomesOPlenty", "misc", 1, 47);
			this.appendItem(tagList, slot++, "fossil", "skullBlock", 0, 1);
			this.appendItem(tagList, slot++, "minecraft", "gunpowder", 0, 4);
			this.appendItem(tagList, slot++, "TConstruct", "materials", 12, 10);
			this.appendItem(tagList, slot++, "BiomesOPlenty", "misc", 10, 1);
			this.appendItem(tagList, slot++, "ThermalFoundation", "material", 131, 2);
			this.appendItem(tagList, slot++, "TMechworks", "LengthWire", 0, 9);
			this.appendItem(tagList, slot++, "minecraft", "fire", 0, 1);
			this.appendItem(tagList, slot++, "minecraft", "fire", 0, 1);
			this.appendItem(tagList, slot++, "primitivemobs", "primitivemobs_TarBall", 0, 6);
			this.appendItem(tagList, slot++, "TConstruct", "materials", 12, 8);
			this.appendItem(tagList, slot++, "TMechworks", "LengthWire", 0, 9);
			this.appendItem(tagList, slot++, "BiomesOPlenty", "misc", 1, 15);
			this.appendItem(tagList, slot++, "minecraft", "bone", 0, 2);
			this.appendItem(tagList, slot++, "BiomesOPlenty", "misc", 3, 29);
			this.appendItem(tagList, slot++, "ThermalExpansion", "material", 514, 6);
			this.appendItem(tagList, slot++, "minecraft", "fire", 0, 1);
		}
	}

	public static final Item ViolenceLunch = new ItemQuestRewardProxy.ViolenceLunchProxy();
	public static final Item IndolenceLunch = new ItemQuestRewardProxy.IndolenceLunchProxy();
	public static final Item PassionLunch = new ItemQuestRewardProxy.PassionLunchProxy();
	public static final Item HungerLunch = new ItemQuestRewardProxy.HungerLunchProxy();
	public static final Item DoggieBag = new ItemQuestRewardProxy.DoggieBagProxy();
	public static final Item RivalryLunch = new ItemQuestRewardProxy.RivalryLunchProxy();
	public static final Item SmokingBox = new ItemQuestRewardProxy.SmokingBoxProxy();
}
