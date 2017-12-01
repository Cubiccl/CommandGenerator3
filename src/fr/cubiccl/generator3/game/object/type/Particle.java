package fr.cubiccl.generator3.game.object.type;

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

}
