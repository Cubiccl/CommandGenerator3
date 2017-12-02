package fr.cubiccl.generator3.game.object.instance;

import fr.cubiccl.generator3.game.object.global.GlobalItem;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.game.object.instance.nbt.TagCompound;
import fr.cubiccl.generator3.game.object.type.Item;

public class ItemStack implements GameObjectInstance
{

	/** The Damage value. */
	public int damageValue;
	/** The Item type. */
	public Item item;
	/** The NBT Tags. */
	public TagCompound nbtTags;
	/** The quantity of items. */
	public int quantity;
	/** The slot containing these items. -1 if no slot. */
	public int slot;

	public ItemStack(Item item, int damageValue, int quantity, int slot, TagCompound nbtTags)
	{
		this.item = item;
		this.damageValue = damageValue;
		this.quantity = quantity;
		this.slot = slot;
		this.nbtTags = nbtTags;
	}

	public GlobalItem globalValue()
	{
		return VersionTranslator.translator(this.item.version).items.inverse().get(new ItemStack(this.item, this.damageValue, 0, 0, null));
	}

}
