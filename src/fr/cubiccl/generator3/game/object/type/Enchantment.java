package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.global.GlobalEnchantment;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;

public class Enchantment extends GameObjectType
{

	/** This Enchantment's numerical ID. */
	public int idInt;
	/** This Enchantment's maximum Level in survival. */
	public int maxLevel;

	public Enchantment(int idNum, String idStr, int maxLevel)
	{
		super("minecraft:" + idStr, Persistance.selectedVersion);
		this.idInt = idNum;
		this.maxLevel = maxLevel;
	}

	public GlobalEnchantment globalValue()
	{
		return VersionTranslator.translator(this.version).enchantments.inverse().get(this);
	}

}
