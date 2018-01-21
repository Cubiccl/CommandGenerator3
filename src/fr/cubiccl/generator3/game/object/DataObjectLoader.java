package fr.cubiccl.generator3.game.object;

import fr.cubiccl.generator3.game.object.Versions.Version;
import fr.cubiccl.generator3.game.object.data.Recipe;
import fr.cubiccl.generator3.game.object.data.Tag;
import fr.cubiccl.generator3.util.FileUtils;
import fr.cubiccl.generator3.util.Logger;

public class DataObjectLoader
{

	static void loadDataObjects(Version version)
	{
		VersionRegistry registry = Versions.registry(version);

		Logger.log("Loading block tags.");
		for (String tag : FileUtils.getResourceFiles("/data/v" + version.id + "/tags/blocks"))
			registry.blockTags.register(
					new Tag("minecraft:" + tag.replace(".json", "")).readJson(FileUtils.readJsonFile("/data/v" + version.id + "/tags/blocks/" + tag)));

		Logger.log("Loading item tags.");
		for (String tag : FileUtils.getResourceFiles("/data/v" + version.id + "/tags/items"))
			registry.itemTags
					.register(new Tag("minecraft:" + tag.replace(".json", "")).readJson(FileUtils.readJsonFile("/data/v" + version.id + "/tags/items/" + tag)));

		Logger.log("Loading recipes.");
		for (String tag : FileUtils.getResourceFiles("/data/v" + version.id + "/recipes"))
			registry.recipes
					.register(new Recipe("minecraft:" + tag.replace(".json", "")).readJson(FileUtils.readJsonFile("/data/v" + version.id + "/recipes/" + tag)));
	}
}
