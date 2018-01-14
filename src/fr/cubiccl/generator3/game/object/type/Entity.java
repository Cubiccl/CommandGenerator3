package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Persistance;

public class Entity extends GameObjectType
{

	public final int order;

	public Entity(String id, int order)
	{
		super("minecraft:" + id, Persistance.selectedVersion);
		this.order = order;
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		if (!(o instanceof Entity)) return 0;
		return Integer.compare(this.order, ((Entity) o).order);
	}

}
