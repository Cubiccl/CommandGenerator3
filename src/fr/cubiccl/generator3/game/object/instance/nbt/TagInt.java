package fr.cubiccl.generator3.game.object.instance.nbt;

import fr.cubiccl.generator3.game.object.type.NBTTag;

public class TagInt extends Tag
{
	/** This Tag's value. */
	public int value;

	public TagInt(NBTTag tagType, int value)
	{
		super(tagType);
		this.value = value;
	}

	@Override
	protected String value()
	{
		return String.valueOf(this.value);
	}

}
