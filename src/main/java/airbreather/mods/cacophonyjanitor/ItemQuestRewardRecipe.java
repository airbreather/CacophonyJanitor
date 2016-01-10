package airbreather.mods.cacophonyjanitor;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.*;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;

public abstract class ItemQuestRewardRecipe implements IRecipe
{
	private final String displayName;
	private final Item matchItem;
	private boolean lunchbag;

	protected ItemQuestRewardRecipe(String displayName, Item matchItem, boolean lunchbag)
	{
		this.displayName = displayName;
		this.matchItem = matchItem;
		this.lunchbag = lunchbag;
	}

	@Override
	public boolean matches(InventoryCrafting p_77569_1_, World p_77569_2_)
	{
		boolean found = false;
		for (int i = 0; i < p_77569_1_.getSizeInventory(); i++)
		{
			ItemStack stackInSlot = p_77569_1_.getStackInSlot(i);
			if (stackInSlot == null)
			{
				continue;
			}

			if (found || stackInSlot.getItem() != this.matchItem)
			{
				return false;
			}

			found = true;
		}

		return found;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
	{
		return this.getRecipeOutput();
	}

	@Override
	public int getRecipeSize() { return 1; }

	@Override
	public ItemStack getRecipeOutput()
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
		resultItemStack.setStackDisplayName(this.displayName);
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

	public static final class ViolenceLunch extends ItemQuestRewardRecipe
	{
		public ViolenceLunch()
		{
			super("VIOLENCE'S LUNCH!!!!!", ItemQuestRewardProxy.ViolenceLunch, true);
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

	public static final class IndolenceLunch extends ItemQuestRewardRecipe
	{
		public IndolenceLunch()
		{
			super("indolences lunch", ItemQuestRewardProxy.IndolenceLunch, true);
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

	public static final class PassionLunch extends ItemQuestRewardRecipe
	{
		public PassionLunch()
		{
			super("<3 Passion's Lunch XOXO", ItemQuestRewardProxy.PassionLunch, true);
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

	public static final class HungerLunch extends ItemQuestRewardRecipe
	{
		public HungerLunch()
		{
			super("Hunger's Light Snack", ItemQuestRewardProxy.HungerLunch, true);
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

	public static final class DoggieBag extends ItemQuestRewardRecipe
	{
		public DoggieBag()
		{
			super("Fancy Doggie Bag", ItemQuestRewardProxy.DoggieBag, true);
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

	public static final class RivalryLunch extends ItemQuestRewardRecipe
	{
		public RivalryLunch()
		{
			super("Rivalry's Lunch DO NOT STEAL", ItemQuestRewardProxy.RivalryLunch, true);
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

	public static final class SmokingBox extends ItemQuestRewardRecipe
	{
		public SmokingBox()
		{
			super("Smoking Box", ItemQuestRewardProxy.SmokingBox, false);
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
			this.appendItem(tagList, slot++, "TotemDefender", "ironDamageUpgrade", 0, 9);
			this.appendItem(tagList, slot++, "minecraft", "fire", 0, 1);
			this.appendItem(tagList, slot++, "minecraft", "fire", 0, 1);
			this.appendItem(tagList, slot++, "primitivemobs", "primitivemobs_TarBall", 0, 6);
			this.appendItem(tagList, slot++, "TConstruct", "materials", 12, 8);
			this.appendItem(tagList, slot++, "TotemDefender", "ironDamageUpgrade", 0, 9);
			this.appendItem(tagList, slot++, "BiomesOPlenty", "misc", 1, 15);
			this.appendItem(tagList, slot++, "minecraft", "bone", 0, 2);
			this.appendItem(tagList, slot++, "BiomesOPlenty", "misc", 3, 29);
			this.appendItem(tagList, slot++, "ThermalFoundation", "material", 514, 6);
			this.appendItem(tagList, slot++, "minecraft", "fire", 0, 1);
		}
	}
}