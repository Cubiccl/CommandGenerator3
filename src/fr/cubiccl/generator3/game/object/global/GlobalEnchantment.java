package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.Versions.Version;
import fr.cubiccl.generator3.game.object.type.Enchantment;

public class GlobalEnchantment extends GlobalObject
{
	public GlobalEnchantment(String id, int order)
	{
		super("enchantment." + id, order);
	}

	@Override
	protected String prefix()
	{
		return "enchantment";
	}

	public Enchantment value(Version version)
	{
		return VersionTranslator.translator(version).enchantments.get(this);
	}

}
