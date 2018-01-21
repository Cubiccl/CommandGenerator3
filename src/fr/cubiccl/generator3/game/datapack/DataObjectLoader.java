package fr.cubiccl.generator3.game.datapack;

import fr.cubiccl.generator3.game.object.data.Recipe;
import fr.cubiccl.generator3.game.object.data.Tag;
import fr.cubiccl.generator3.util.FileUtils;
import fr.cubiccl.generator3.util.Logger;

public class DataObjectLoader
{

	static void loadObjects(DataPack datapack)
	{
		if (datapack.isVanillaPack()) loadVanillaObjects(datapack);
		else loadPackObjects(datapack);
	}

	private static void loadPackObjects(DataPack datapack)
	{
		Logger.log("Loading block tags.");
		for (String tag : FileUtils.getFiles("datapacks/" + datapack.getName() + "/tags/blocks"))
			datapack.blockTags
					.register(new Tag(tag.replace(".json", "")).readJson(FileUtils.readJsonFile("datapacks/" + datapack.getName() + "/tags/blocks/" + tag)));

		Logger.log("Loading item tags.");
		for (String tag : FileUtils.getFiles("datapacks/" + datapack.getName() + "/tags/items"))
			datapack.itemTags
					.register(new Tag(tag.replace(".json", "")).readJson(FileUtils.readJsonFile("datapacks/" + datapack.getName() + "/tags/items/" + tag)));

		Logger.log("Loading recipes.");
		for (String tag : FileUtils.getFiles("datapacks/" + datapack.getName() + "/recipes"))
			datapack.recipes
					.register(new Recipe(tag.replace(".json", "")).readJson(FileUtils.readJsonFile("datapacks/" + datapack.getName() + "/recipes/" + tag)));
	}

	private static void loadVanillaObjects(DataPack datapack)
	{
		Logger.log("Loading block tags.");
		for (String tag : FileUtils.getResourceFiles("/data/" + datapack.getName() + "/tags/blocks"))
			datapack.blockTags
					.register(new Tag(tag.replace(".json", "")).readJson(FileUtils.readJsonFile("/data/" + datapack.getName() + "/tags/blocks/" + tag)));

		Logger.log("Loading item tags.");
		for (String tag : FileUtils.getResourceFiles("/data/" + datapack.getName() + "/tags/items"))
			datapack.itemTags
					.register(new Tag(tag.replace(".json", "")).readJson(FileUtils.readJsonFile("/data/" + datapack.getName() + "/tags/items/" + tag)));

		Logger.log("Loading recipes.");
		for (String tag : FileUtils.getResourceFiles("/data/" + datapack.getName() + "/recipes"))
			datapack.recipes.register(new Recipe(tag.replace(".json", "")).readJson(FileUtils.readJsonFile("/data/" + datapack.getName() + "/recipes/" + tag)));
	}
}
