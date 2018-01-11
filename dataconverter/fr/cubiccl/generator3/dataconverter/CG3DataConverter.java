package fr.cubiccl.generator3.dataconverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonObject.Member;

import fr.cubiccl.generator3.util.FileUtils;

/** Takes in Minecraft's data files and convert them to be used by the Generator. */
public class CG3DataConverter
{

	public static void main(String[] args)
	{
		String folder = "";
		if (args.length >= 1) folder = args[0] + "/";

		Properties data = new Properties();
		try
		{
			data.load(new FileInputStream(new File("infiles/" + folder + "conversiondata.properties")));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		System.out.println("Started converting.");
		System.out.println("Converting blocks.");
		JsonObject root = FileUtils.readJsonFile(new File("infiles/" + folder + "blocks.json"));
		for (Member b : root)
		{
			JsonObject block = b.getValue().asObject();
			if (block.get("states") != null) block.remove("states");
			if (block.get("properties") != null)
			{
				block.add("states", block.get("properties"));
				block.remove("properties");
			}

			/* String id = b.getName().substring("minecraft:".length()); block.add("global", data.containsKey(id) ? data.getProperty(id) : id); */
		}
		FileUtils.writeJsonToFile(root, new File("infiles/" + folder + "blocks_done.json"));

		System.out.println("Converting items.");
		ArrayList<String> newItems = new ArrayList<String>();
		root = FileUtils.readJsonFile(new File("infiles/" + folder + "items.json"));
		for (Member item : root)
		{
			String id = item.getName().substring("minecraft:".length());
			newItems.add(id/* , data.containsKey(id) ? data.getProperty(id) : id */);
		}
		FileUtils.writeJsonToFile(Json.array(newItems.toArray(new String[newItems.size()])), new File("infiles/" + folder + "items_done.json"));

		System.out.println("Conversion finished!");
	}

}
