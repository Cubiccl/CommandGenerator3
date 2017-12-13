package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Item;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalItem extends GlobalObject
{
	public GlobalItem(String id, int order)
	{
		super("item." + id, order);
	}

	@Override
	protected String prefix()
	{
		return "nbttag";
	}

	public Item value(Version version)
	{
		return VersionTranslator.translator(version).items.get(this);
	}

}
