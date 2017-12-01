package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.util.Text;

public class Particle extends GameObjectType
{

	/** This Particle's ID. */
	public final String id;

	public Particle(String id)
	{
		this.id = "minecraft:" + id;
	}

	@Override
	public String id()
	{
		return this.id;
	}

	@Override
	public Text name()
	{
		return new Text(this.id, false);
	}

}
