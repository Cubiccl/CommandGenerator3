package fr.cubiccl.generator3.game.object.instance.nbt;

import fr.cubiccl.generator3.game.object.instance.GameObjectInstance;
import fr.cubiccl.generator3.game.object.type.NBTTag;

public abstract class Tag implements GameObjectInstance
{

	/** The NBT Tag type. */
	public final NBTTag tagType;

	public Tag(NBTTag tagType)
	{
		this.tagType = tagType;
	}

	/** @return The String to use as key when generating this NBT Tag. */
	protected String key()
	{
		return this.tagType.id();
	}

	public String toString()
	{
		return "\"" + this.key() + "\":" + String.valueOf(this.value());
	}

	/** @return The String to use as value when generating this NBT Tag. */
	protected abstract String value();

}
