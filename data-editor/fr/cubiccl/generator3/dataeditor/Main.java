package fr.cubiccl.generator3.dataeditor;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.PrettyPrint;

import fr.cubiccl.generator3.game.object.GameObjectLoader;
import fr.cubiccl.generator3.game.object.Versions;
import fr.cubiccl.generator3.game.object.Versions.Version;
import fr.cubiccl.generator3.game.object.type.Effect;
import fr.cubiccl.generator3.game.object.type.Enchantment;
import fr.cubiccl.generator3.game.object.type.GameObjectType;
import fr.cubiccl.generator3.util.FileUtils;
import fr.cubiccl.generator3.util.Lang;
import fr.cubiccl.generator3.util.Settings;
import fr.cubiccl.generator3.util.Textures;

public class Main
{

	public static void exit()
	{
		TestApplication.instance.primaryStage.close();

		for (Version v : Version.getVersions())
			saveVersion(v);
	}

	public static void main(String[] args)
	{
		Settings.loadSettings();
		Lang.fullReload();
		Textures.createTextures();
		GameObjectLoader.loadObjects();
		TestApplication.initialize(args);
	}

	private static void saveVersion(Version version)
	{
		JsonObject root = Json.object();

		JsonObject object = Json.object();
		ArrayList<GameObjectType> objects = new ArrayList<GameObjectType>();
		objects.addAll(Versions.registry(version).attributes.list());
		objects.sort(Comparator.naturalOrder());
		// for (GameObjectType o : objects) object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("attributes", object);

		object = Json.object();
		objects.clear();
		objects.addAll(Versions.registry(version).blocks.list());
		objects.sort(Comparator.naturalOrder());
		// for (GameObjectType o : objects) object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("blocks", object);

		object = Json.object();
		objects.clear();
		objects.addAll(Versions.registry(version).effects.list());
		objects.sort(Comparator.naturalOrder());
		for (GameObjectType o : objects)
		{
			JsonObject e = Json.object();
			// e.add("global", o.globalValue().idPrefixless());
			e.add("id", ((Effect) o).idInt);
			object.add(o.idPrefixless(), e);
		}
		root.add("effects", object);

		object = Json.object();
		objects.clear();
		objects.addAll(Versions.registry(version).attributes.list());
		objects.sort(Comparator.naturalOrder());
		for (GameObjectType o : objects)
		{
			JsonObject e = Json.object();
			// e.add("global", o.globalValue().idPrefixless());
			e.add("id", ((Enchantment) o).idInt);
			if (((Enchantment) o).maxLevel != 1) e.add("max_level", ((Enchantment) o).maxLevel);
			object.add(o.idPrefixless(), e);
		}
		root.add("enchantments", object);

		object = Json.object();
		objects.clear();
		objects.addAll(Versions.registry(version).attributes.list());
		objects.sort(Comparator.naturalOrder());
		// for (GameObjectType o : objects) object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("entities", object);

		object = Json.object();
		objects.clear();
		objects.addAll(Versions.registry(version).attributes.list());
		objects.sort(Comparator.naturalOrder());
		// for (GameObjectType o : objects) object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("items", object);

		object = Json.object();
		objects.clear();
		objects.addAll(Versions.registry(version).attributes.list());
		objects.sort(Comparator.naturalOrder());
		// for (GameObjectType o : objects) object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("particles", object);

		object = Json.object();
		objects.clear();
		objects.addAll(Versions.registry(version).attributes.list());
		objects.sort(Comparator.naturalOrder());
		// for (GameObjectType o : objects) object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("sounds", object);

		FileUtils.writeToFile(root.toString(PrettyPrint.indentWithTabs()), new File("resources/data/v" + version.id + "/data.json"));
	}

}
