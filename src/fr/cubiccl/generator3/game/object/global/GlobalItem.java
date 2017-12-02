package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.instance.ItemStack;
import fr.cubiccl.generator3.game.object.type.Item;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalItem extends GlobalObject
{
	public GlobalItem(String id)
	{
		super("item." + id);
	}

	public GlobalItem(String id, Version introduced, Version removed)
	{
		super("item." + id, introduced, removed);
	}

	public ItemStack value(Version version)
	{
		return VersionTranslator.translator(version).items.get(this);
	}

	public Item valueAsGroup(Version version)
	{
		return VersionTranslator.translator(version).itemGroups.get(this);
	}

}
