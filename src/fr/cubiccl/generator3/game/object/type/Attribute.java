package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Persistance;

public class Attribute extends GameObjectType
{

	public final int order;

	public Attribute(String id, int order)
	{
		super(id, Persistance.selectedVersion);
		this.order = order;
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		if (!(o instanceof Attribute)) return 0;
		return Integer.compare(this.order, ((Attribute) o).order);
	}

}
