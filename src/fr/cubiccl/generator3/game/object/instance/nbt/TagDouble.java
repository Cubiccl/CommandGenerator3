package fr.cubiccl.generator3.game.object.instance.nbt;

import fr.cubiccl.generator3.game.object.type.NBTTag;

public class TagDouble extends Tag
{
	/** This Tag's value. */
	public double value;

	public TagDouble(NBTTag tagType, double value)
	{
		super(tagType);
		this.value = value;
	}

	@Override
	protected String value()
	{
		return String.valueOf(this.value + "d");
	}

}
