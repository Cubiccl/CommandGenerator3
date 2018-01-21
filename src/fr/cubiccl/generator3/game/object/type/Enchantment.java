package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Text;

public class Enchantment extends GameObjectType
{

	/** This Enchantment's numerical ID. */
	public int idInt;
	/** This Enchantment's maximum Level in survival. */
	public int maxLevel;

	public Enchantment(int idNum, String idStr, int maxLevel)
	{
		super(idStr);
		this.idInt = idNum;
		this.maxLevel = maxLevel;
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		if (!(o instanceof Enchantment)) return 0;
		return Integer.compare(this.idInt, ((Enchantment) o).idInt);
	}

	@Override
	protected Text createName()
	{
		return new Text("enchantment." + this.idWithoutNamespace());
	}

	@Override
	public String describe()
	{
		return super.describe() + " (" + this.idInt + ", max_level=" + this.maxLevel + ")";
	}

	@Override
	public String type()
	{
		return "Enchantment";
	}

}
