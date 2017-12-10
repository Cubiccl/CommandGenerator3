package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.NBTTag;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalNBTTag extends GlobalObject
{
	public GlobalNBTTag(String id, double order)
	{
		super("nbttag." + id, order);
	}

	public GlobalNBTTag(String id, double order, Version introduced, Version removed)
	{
		super("nbttag." + id, order, introduced, removed);
	}

	public NBTTag value(Version version)
	{
		return VersionTranslator.translator(version).nbtTags.get(this);
	}

}
