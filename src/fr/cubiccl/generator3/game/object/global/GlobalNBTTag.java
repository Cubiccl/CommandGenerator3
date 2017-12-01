package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.instance.nbt.Tag;
import fr.cubiccl.generator3.game.object.type.NBTTag;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalNBTTag extends GlobalObject<NBTTag, Tag>
{
	public GlobalNBTTag(String id)
	{
		super("achievement." + id);
	}

	public GlobalNBTTag(String id, Version introduced, Version removed)
	{
		super("achievement." + id, introduced, removed);
	}
}
