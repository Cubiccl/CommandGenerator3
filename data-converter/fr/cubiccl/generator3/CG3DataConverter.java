package fr.cubiccl.generator3;

import java.io.File;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonObject.Member;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.PrettyPrint;

import fr.cubiccl.generator3.game.datapack.DataPacks.Version;
import fr.cubiccl.generator3.util.FileUtils;

public class CG3DataConverter
{

	public static void main(String[] args)
	{
		System.out.println("Started.");
		for (Version version : Version.getVersions())
		{
			System.out.println("Version: " + version.name);
			String data = FileUtils.readFile("/data/v" + version.id + "/blocks.json");
			System.out.println("File read.");
			JsonValue root = Json.parse(data);
			System.out.println("Data parsed.");

			for (Member m : root.asObject())
			{
				JsonObject block = m.getValue().asObject();
				if (block.get("states") != null)
				{
					block.set("id", block.get("states").asArray().get(0).asObject().getInt("id", 0));
					block.remove("states");
				}
				if (block.get("properties") != null)
				{
					block.set("states", block.get("properties"));
					block.remove("properties");
				}
			}
			System.out.println("Data converted.");

			FileUtils.writeToFile(root.toString(PrettyPrint.indentWithTabs()), new File("resources/data/v" + version.id + "/blocks.json"));
			System.out.println("File saved.");
		}
		System.out.println("Done.");
	}

}
