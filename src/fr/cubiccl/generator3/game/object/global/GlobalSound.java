package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Sound;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalSound extends GlobalObject
{
	public GlobalSound(String id)
	{
		super("sound." + id);
	}

	public GlobalSound(String id, Version introduced, Version removed)
	{
		super("sound." + id, introduced, removed);
	}

	public Sound value(Version version)
	{
		return VersionTranslator.translator(version).sounds.get(this);
	}

}
