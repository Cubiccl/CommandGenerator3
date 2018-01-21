package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Text;

public class Particle extends GameObjectType
{

	public Particle(String id)
	{
		super(id);
	}

	@Override
	public int compareTo(GameObjectType o)
	{
		if ((o instanceof Particle)) return 0;
		return this.id().compareTo(((Particle) o).id());
	}

	@Override
	protected Text createName()
	{
		return new Text("particle." + this.idWithoutNamespace());
	}

	@Override
	public String type()
	{
		return "Particle";
	}

}
