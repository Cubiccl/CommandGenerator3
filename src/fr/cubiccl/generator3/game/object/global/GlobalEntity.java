package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.instance.LivingEntity;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalEntity extends GlobalObject
{
	public GlobalEntity(String id, int order)
	{
		super("entity." + id, order);
	}

	public GlobalEntity(String id, int order, Version introduced, Version removed)
	{
		super("entity." + id, order, introduced, removed);
	}

	public LivingEntity value(Version version)
	{
		return VersionTranslator.translator(version).entities.get(this);
	}

}
