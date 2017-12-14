package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Entity;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalEntity extends GlobalObject
{
	public GlobalEntity(String id, int order)
	{
		super("entity." + id, order);
	}

	@Override
	protected String prefix()
	{
		return "entity";
	}

	public Entity value(Version version)
	{
		return VersionTranslator.translator(version).entities.get(this);
	}

}
