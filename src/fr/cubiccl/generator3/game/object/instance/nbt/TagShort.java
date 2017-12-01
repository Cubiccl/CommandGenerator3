package fr.cubiccl.generator3.game.object.instance.nbt;

import fr.cubiccl.generator3.game.object.type.NBTTag;

public class TagShort extends Tag
{
	/** This Tag's value. */
	public short value;

	public TagShort(NBTTag tagType, short value)
	{
		super(tagType);
		this.value = value;
	}

	@Override
	protected String value()
	{
		return String.valueOf(this.value + "s");
	}

}
