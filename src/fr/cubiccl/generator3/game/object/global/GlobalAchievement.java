package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Achievement;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalAchievement extends GlobalObject
{
	public GlobalAchievement(String id, int order)
	{
		super("achievement." + id, order);
	}

	public GlobalAchievement(String id, int order, Version introduced, Version removed)
	{
		super("achievement." + id, order, introduced, removed);
	}

	public Achievement value(Version version)
	{
		return VersionTranslator.translator(version).achievements.get(this);
	}

}
