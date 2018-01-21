package fr.cubiccl.generator3.game.object.instance;

import fr.cubiccl.generator3.game.object.instance.nbt.TagCompound;
import fr.cubiccl.generator3.game.object.type.Item;

public class ItemStack implements GameObjectInstance
{

	/** The Item type. */
	public Item item;
	/** The NBT Tags. */
	public TagCompound nbtTags;
	/** The quantity of items. */
	public int quantity;
	/** The slot containing these items. -1 if no slot. */
	public int slot;

	public ItemStack(Item item)
	{
		this(item, -1, -1, null);
	}

	public ItemStack(Item item, int quantity, int slot, TagCompound nbtTags)
	{
		this.item = item;
		this.quantity = quantity;
		this.slot = slot;
		this.nbtTags = nbtTags;
	}

}
