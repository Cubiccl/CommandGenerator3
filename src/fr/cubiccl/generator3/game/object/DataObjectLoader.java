package fr.cubiccl.generator3.game.object;

import fr.cubiccl.generator3.game.object.Versions.Version;
import fr.cubiccl.generator3.util.Logger;

public class DataObjectLoader
{

	static void loadDataObjects(Version version)
	{
		Logger.log("Loading block tags.");
		/* root = Json.parse(FileUtils.readFile("/data/v" + version.id + "/sounds.json")); for (JsonValue m : root.asArray()) Versions.registry(version).sounds.register(createSound(version, m)); */
	}
}
