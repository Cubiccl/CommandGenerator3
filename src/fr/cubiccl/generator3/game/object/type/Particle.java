package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.global.GlobalParticle;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Text;

public class Particle extends GameObjectType
{

	/** This Particle's ID. */
	public final String id;

	public Particle(String id)
	{
		super(Persistance.selectedVersion);
		this.id = "minecraft:" + id;
	}

	public GlobalParticle globalValue()
	{
		return VersionTranslator.translator(this.version).particles.inverse().get(this);
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
