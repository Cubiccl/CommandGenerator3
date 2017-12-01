package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.instance.AppliedEnchantment;
import fr.cubiccl.generator3.game.object.type.Enchantment;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalEnchantment extends GlobalObject<Enchantment, AppliedEnchantment>
{
	public GlobalEnchantment(String id)
	{
		super("enchantment." + id);
	}

	public GlobalEnchantment(String id, Version introduced, Version removed)
	{
		super("enchantment." + id, introduced, removed);
	}
}
