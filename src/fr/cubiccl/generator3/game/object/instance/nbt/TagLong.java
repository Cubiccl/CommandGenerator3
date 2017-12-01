package fr.cubiccl.generator3.game.object.instance.nbt;

import fr.cubiccl.generator3.game.object.type.NBTTag;

public class TagLong extends Tag
{
	/** This Tag's value. */
	public long value;

	public TagLong(NBTTag tagType, long value)
	{
		super(tagType);
		this.value = value;
	}

	@Override
	public TagLong duplicate()
	{
		return new TagLong(this.tagType, this.value);
	}

	@Override
	protected String value()
	{
		return String.valueOf(this.value + "l");
	}

}
