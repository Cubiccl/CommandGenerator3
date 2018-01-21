package fr.cubiccl.generator3.game.datapack;

import fr.cubiccl.generator3.game.object.data.Recipe;
import fr.cubiccl.generator3.game.object.data.Tag;
import fr.cubiccl.generator3.game.object.data.loottable.LootTable;
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
			datapack.recipes.register(
					new Recipe(tag.replace(".json", ""), datapack.id).readJson(FileUtils.readJsonFile("datapacks/" + datapack.getName() + "/recipes/" + tag)));

		Logger.log("Loading loot tables.");
		for (String tag : FileUtils.getSubFiles("datapacks/" + datapack.getName() + "/loot_tables"))
			datapack.lootTables.register(new LootTable(tag.replace(".json", ""), datapack.id)
					.readJson(FileUtils.readJsonFile("datapacks/" + datapack.getName() + "/loot_tables/" + tag)));
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
			datapack.recipes.register(
					new Recipe(tag.replace(".json", ""), datapack.id).readJson(FileUtils.readJsonFile("/data/" + datapack.getName() + "/recipes/" + tag)));

		Logger.log("Loading loot tables.");
		for (String tag : FileUtils.getResourceSubFiles("/data/" + datapack.getName() + "/loot_tables"))
		{
			datapack.lootTables.register(new LootTable(tag.replace(".json", ""), datapack.id)
					.readJson(FileUtils.readJsonFile("/data/" + datapack.getName() + "/loot_tables/" + tag)));
		}
	}
}
