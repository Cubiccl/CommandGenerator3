package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Enchantment;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalEnchantment extends GlobalObject
{
	public GlobalEnchantment(String id, double order)
	{
		super("enchantment." + id, order);
	}

	public GlobalEnchantment(String id, double order, Version introduced, Version removed)
	{
		super("enchantment." + id, order, introduced, removed);
	}

	public Enchantment value(Version version)
	{
		return VersionTranslator.translator(version).enchantments.get(this);
	}
	
}
