package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.datapack.DataPacks.Version;
import fr.cubiccl.generator3.game.object.type.NBTTag;

public class GlobalNBTTag extends GlobalObject
{
	public GlobalNBTTag(String id, int order)
	{
		super("nbttag." + id, order);
	}

	@Override
	protected String prefix()
	{
		return "nbttag";
	}

	public NBTTag value(Version version)
	{
		return VersionTranslator.translator(version).nbtTags.get(this);
	}

}
