package fr.cubiccl.generator3.game.object.instance.nbt;

import fr.cubiccl.generator3.game.object.type.NBTTag;

public class TagFloat extends Tag
{
	/** This Tag's value. */
	public float value;

	public TagFloat(NBTTag tagType, float value)
	{
		super(tagType);
		this.value = value;
	}

	@Override
	protected String value()
	{
		return String.valueOf(this.value + "f");
	}

}
