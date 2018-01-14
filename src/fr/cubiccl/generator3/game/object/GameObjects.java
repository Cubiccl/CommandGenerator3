package fr.cubiccl.generator3.game.object;

import java.util.HashMap;

import fr.cubiccl.generator3.util.Settings.Version;

public class GameObjects
{

	private static final HashMap<Version, VersionRegistry> registries = new HashMap<Version, VersionRegistry>();

	static void create(Version version)
	{
		registries.put(version, new VersionRegistry(version));

	}

	public static final VersionRegistry registry(Version version)
	{
		return registries.get(version);
	}

}
