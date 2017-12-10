package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Entity;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalEntity extends GlobalObject
{
	public GlobalEntity(String id, double order)
	{
		super("entity." + id, order);
	}

	public GlobalEntity(String id, double order, Version introduced, Version removed)
	{
		super("entity." + id, order, introduced, removed);
	}

	public Entity value(Version version)
	{
		return VersionTranslator.translator(version).entities.get(this);
	}

}
