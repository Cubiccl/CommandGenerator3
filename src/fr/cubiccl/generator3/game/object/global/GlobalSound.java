package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Sound;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalSound extends GlobalObject
{
	public GlobalSound(String id, double order)
	{
		super("sound." + id, order);
	}

	public GlobalSound(String id, double order, Version introduced, Version removed)
	{
		super("sound." + id, order, introduced, removed);
	}

	public Sound value(Version version)
	{
		return VersionTranslator.translator(version).sounds.get(this);
	}

}
