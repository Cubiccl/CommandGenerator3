package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Effect;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalEffect extends GlobalObject
{
	public GlobalEffect(String id)
	{
		super("effect." + id);
	}

	public GlobalEffect(String id, Version introduced, Version removed)
	{
		super("effect." + id, introduced, removed);
	}

	public Effect value(Version version)
	{
		return VersionTranslator.translator(version).effects.get(this);
	}

}
