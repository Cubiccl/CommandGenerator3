package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Text;

public class Enchantment extends GameObjectType
{

	/** This Enchantment's numerical ID. */
	public final int idInt;
	/** This Enchantment's text ID. */
	public final String idString;
	/** This Enchantment's maximum Level in survival. */
	public final int maxLevel;

	public Enchantment(int idNum, String idStr, int maxLevel)
	{
		this.idInt = idNum;
		this.idString = "minecraft:" + idStr;
		this.maxLevel = maxLevel;
	}

	@Override
	public String id()
	{
		return this.idString;
	}

	public Text name()
	{
		return new Text("enchantment." + this.idString);
	}

}
