package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Persistance;

public class Item extends GameObjectType
{

	/** <code>true</code> if this Item has durability. */
	public final int durability;
	public final int idNum;

	public Item(String id, int idNum)
	{
		this(id, idNum, 0);
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		if (!(o instanceof Item)) return 0;
		return Integer.compare(this.idNum, ((Item) o).idNum);
	}

	public Item(String id, int idNum, int durability)
	{
		super("minecraft:" + id, Persistance.selectedVersion);
		this.idNum = idNum;
		this.durability = durability;
	}

}
