package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Particle;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalParticle extends GlobalObject
{
	public GlobalParticle(String id)
	{
		super("particle." + id);
	}

	public GlobalParticle(String id, Version introduced, Version removed)
	{
		super("particle." + id, introduced, removed);
	}

	public Particle value(Version version)
	{
		return VersionTranslator.translator(version).particles.get(this);
	}

}
