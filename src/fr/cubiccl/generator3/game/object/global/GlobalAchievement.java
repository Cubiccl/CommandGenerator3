package fr.cubiccl.generator3.game.object.global;

import fr.cubiccl.generator3.game.object.type.Achievement;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalAchievement extends GlobalObject<Achievement, Achievement>
{
	public GlobalAchievement(String id)
	{
		super("achievement." + id);
	}

	public GlobalAchievement(String id, Version introduced, Version removed)
	{
		super("achievement." + id, introduced, removed);
	}
}
