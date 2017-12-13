package fr.cubiccl.generator3.game.object.type;

import fr.cubiccl.generator3.game.object.global.GlobalParticle;
import fr.cubiccl.generator3.game.object.global.VersionTranslator;
import fr.cubiccl.generator3.util.Persistance;

public class Particle extends GameObjectType
{

	public Particle(String id)
	{
		super("minecraft:" + id, Persistance.selectedVersion);
	}

	public GlobalParticle globalValue()
	{
		return VersionTranslator.translator(this.version).particles.inverse().get(this);
	}

}
