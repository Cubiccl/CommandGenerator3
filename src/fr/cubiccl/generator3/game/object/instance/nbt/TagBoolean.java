package fr.cubiccl.generator3.game.object.instance.nbt;

import fr.cubiccl.generator3.game.object.type.NBTTag;

public class TagBoolean extends Tag
{
	/** This Tag's value. */
	public boolean value;

	public TagBoolean(NBTTag tagType, boolean value)
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
