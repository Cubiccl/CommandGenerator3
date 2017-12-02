package fr.cubiccl.generator3.game.object.instance.nbt;

import fr.cubiccl.generator3.game.object.type.NBTTag;

public class TagByte extends Tag
{
	/** This Tag's value. */
	public byte value;

	public TagByte(NBTTag tagType, byte value)
	{
		super(tagType);
		this.value = value;
	}

	@Override
	protected String value()
	{
		return String.valueOf(this.value + "b");
	}

}
