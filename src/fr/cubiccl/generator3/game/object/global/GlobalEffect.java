package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Effect;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalEffect extends GlobalObject
{
	public GlobalEffect(String id, int order)
	{
		super("effect." + id, order);
	}

	@Override
	protected String prefix()
	{
		return "effect";
	}

	public Effect value(Version version)
	{
		return VersionTranslator.translator(version).effects.get(this);
	}

}
