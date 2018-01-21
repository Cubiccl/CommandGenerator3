package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.datapack.DataPacks.Version;
import fr.cubiccl.generator3.game.object.type.Particle;

public class GlobalParticle extends GlobalObject
{
	public GlobalParticle(String id, int order)
	{
		super("particle." + id, order);
	}

	@Override
	protected String prefix()
	{
		return "particle";
	}

	public Particle value(Version version)
	{
		return VersionTranslator.translator(version).particles.get(this);
	}

}
