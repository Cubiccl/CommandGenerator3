package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Persistance;

public class Particle extends GameObjectType
{

	public Particle(String id)
	{
		super("minecraft:" + id, Persistance.selectedVersion);
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		if ((o instanceof Particle)) return 0;
		return this.id.compareTo(((Particle) o).id);
	}

}
