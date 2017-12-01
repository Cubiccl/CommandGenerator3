package fr.cubiccl.generator3.game.object.instance.nbt;

import java.util.ArrayList;
import java.util.Arrays;

import fr.cubiccl.generator3.game.object.type.NBTTag;

public class TagCompound extends Tag
{
	/** This Tag's value. */
	public final ArrayList<Tag> value;

	public TagCompound(NBTTag tagType, Tag... value)
	{
		super(tagType);
		this.value = new ArrayList<Tag>(Arrays.asList(value));
	}

	@Override
	public TagCompound duplicate()
	{
		TagCompound t = new TagCompound(this.tagType);
		for (Tag tag : this.value)
			t.value.add((Tag) tag.duplicate());
		return t;
	}

	@Override
	protected String value()
	{
		StringBuilder value = new StringBuilder("{");
		for (int i = 0; i < this.value.size(); ++i)
		{
			if (i != 0) value.append(",");
			value.append(this.value.get(i).toString());
		}
		return value.append("}").toString();
	}

}
