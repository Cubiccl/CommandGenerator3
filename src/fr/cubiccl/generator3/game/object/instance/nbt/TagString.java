package fr.cubiccl.generator3.game.object.instance.nbt;

import fr.cubiccl.generator3.game.object.type.NBTTag;

public class TagString extends Tag
{
	/** This Tag's value. */
	public String value;

	public TagString(NBTTag tagType, String value)
	{
		super(tagType);
		this.value = value;
	}

	@Override
	protected String value()
	{
		return "\"" + this.value + "\"";
	}

}
