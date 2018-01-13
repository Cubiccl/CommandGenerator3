package fr.cubiccl.generator3.game.object.instance.nbt;

import java.util.ArrayList;
import java.util.Arrays;

import fr.cubiccl.generator3.game.object.type.NBTTag;

public class TagList extends Tag
{
	/** This Tag's value. */
	public final ArrayList<Tag> value;

	public TagList(NBTTag tagType, Tag... value)
	{
		super(tagType);
		this.value = new ArrayList<Tag>(Arrays.asList(value));
	}

	@Override
	protected String value()
	{
		StringBuilder value = new StringBuilder("[");
		for (int i = 0; i < this.value.size(); ++i)
		{
			if (i != 0) value.append(",");
			value.append(this.value.get(i).value());
		}
		return value.append("]").toString();
	}

}
