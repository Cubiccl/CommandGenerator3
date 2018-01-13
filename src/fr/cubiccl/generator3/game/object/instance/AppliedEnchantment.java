package fr.cubiccl.generator3.game.object.instance;

import fr.cubiccl.generator3.game.object.type.Enchantment;

public class AppliedEnchantment implements GameObjectInstance
{

	/** The Enchantment type. */
	public final Enchantment enchantment;
	/** The Enchantment level. */
	public final int level;

	public AppliedEnchantment(Enchantment enchantment, int level)
	{
		this.enchantment = enchantment;
		this.level = level;
	}

}
