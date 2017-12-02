package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.instance.LivingEntity;
import fr.cubiccl.generator3.game.object.type.Entity;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalEntity extends GlobalObject
{
	public GlobalEntity(String id)
	{
		super("entity." + id);
	}

	public GlobalEntity(String id, Version introduced, Version removed)
	{
		super("entity." + id, introduced, removed);
	}

	public LivingEntity value(Version version)
	{
		return VersionTranslator.translator(version).entities.get(this);
	}

	public Entity valueAsGroup(Version version)
	{
		return VersionTranslator.translator(version).entityGroups.get(this);
	}

}
