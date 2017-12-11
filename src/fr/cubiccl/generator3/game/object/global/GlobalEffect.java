package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Effect;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalEffect extends GlobalObject
{
	public GlobalEffect(String id, int order)
	{
		super("effect." + id, order);
	}

	public GlobalEffect(String id, int order, Version introduced, Version removed)
	{
		super("effect." + id, order, introduced, removed);
	}

	public Effect value(Version version)
	{
		return VersionTranslator.translator(version).effects.get(this);
	}

}
