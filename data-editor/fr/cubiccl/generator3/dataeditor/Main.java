package fr.cubiccl.generator3.dataeditor;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.PrettyPrint;

import fr.cubiccl.generator3.game.object.GlobalRegistry;
import fr.cubiccl.generator3.game.object.VersionObjects;
import fr.cubiccl.generator3.game.object.global.*;
import fr.cubiccl.generator3.game.object.type.Effect;
import fr.cubiccl.generator3.game.object.type.Enchantment;
import fr.cubiccl.generator3.game.object.type.GameObjectType;
import fr.cubiccl.generator3.util.*;
import fr.cubiccl.generator3.util.Settings.Version;

public class Main
{
	private static final Comparator<GlobalObject> idComparator = new Comparator<GlobalObject>()
	{
		@Override
		public int compare(GlobalObject o1, GlobalObject o2)
		{
			return o1.id.compareTo(o2.id);
		}
	};

	public static void exit()
	{
		TestApplication.instance.primaryStage.close();

		saveGlobal();
		for (Version v : Version.getVersions())
			saveVersion(v);
	}

	public static void main(String[] args)
	{
		Settings.loadSettings();
		Lang.fullReload();
		Textures.createTextures();
		GlobalRegistry.loadObjects();
		VersionObjects.loadObjects();
		TestApplication.initialize(args);
	}

	private static void saveGlobal()
	{
		JsonObject root = Json.object();

		JsonObject object = Json.object();
		ArrayList<GlobalObject> objects = new ArrayList<GlobalObject>();
		objects.addAll(GlobalRegistry.blocks.list());
		objects.sort(idComparator);
		for (GlobalObject o : objects)
			object.add(o.id.substring("block.".length()), o.order);
		root.add("blocks", object);

		object = Json.object();
		objects = new ArrayList<GlobalObject>();
		objects.clear();
		objects.addAll(GlobalRegistry.items.list());
		objects.sort(idComparator);
		for (GlobalObject o : objects)
			object.add(o.id.substring("item.".length()), o.order);
		root.add("items", object);

		object = Json.object();
		objects = new ArrayList<GlobalObject>();
		objects.clear();
		objects.addAll(GlobalRegistry.entities.list());
		objects.sort(idComparator);
		for (GlobalObject o : objects)
			object.add(o.id.substring("entity.".length()), o.order);
		root.add("entities", object);

		object = Json.object();
		objects = new ArrayList<GlobalObject>();
		objects.clear();
		objects.addAll(GlobalRegistry.attributes.list());
		objects.sort(idComparator);
		for (GlobalObject o : objects)
			object.add(o.id.substring("attribute.".length()), o.order);
		root.add("attributes", object);

		object = Json.object();
		objects = new ArrayList<GlobalObject>();
		objects.clear();
		objects.addAll(GlobalRegistry.effects.list());
		objects.sort(idComparator);
		for (GlobalObject o : objects)
			object.add(o.id.substring("effect.".length()), o.order);
		root.add("effects", object);

		object = Json.object();
		objects = new ArrayList<GlobalObject>();
		objects.clear();
		objects.addAll(GlobalRegistry.enchantments.list());
		objects.sort(idComparator);
		for (GlobalObject o : objects)
			object.add(o.id.substring("enchantment.".length()), o.order);
		root.add("enchantments", object);

		object = Json.object();
		objects = new ArrayList<GlobalObject>();
		objects.clear();
		objects.addAll(GlobalRegistry.nbttags.list());
		objects.sort(idComparator);
		for (GlobalObject o : objects)
			object.add(o.id.substring("nbttag.".length()), o.order);
		root.add("nbttags", object);

		object = Json.object();
		objects = new ArrayList<GlobalObject>();
		objects.clear();
		objects.addAll(GlobalRegistry.particles.list());
		objects.sort(idComparator);
		for (GlobalObject o : objects)
			object.add(o.id.substring("particle.".length()), o.order);
		root.add("particles", object);

		object = Json.object();
		objects = new ArrayList<GlobalObject>();
		objects.clear();
		objects.addAll(GlobalRegistry.sounds.list());
		objects.sort(idComparator);
		for (GlobalObject o : objects)
			object.add(o.id.substring("sound.".length()), o.order);
		root.add("sounds", object);

		FileUtils.writeToFile(root.toString(PrettyPrint.indentWithTabs()), new File("resources/data/global_objects.json"));
	}

	private static void saveVersion(Version version)
	{
		JsonObject root = Json.object();

		JsonObject object = Json.object();
		ArrayList<GameObjectType> objects = new ArrayList<GameObjectType>();
		for (GlobalObject o : GlobalRegistry.attributes.list())
			if (o.value(version) != null) objects.add(o.value(version));
		objects.sort(Comparator.naturalOrder());
		for (GameObjectType o : objects)
			object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("attributes", object);

		object = Json.object();
		objects.clear();
		for (GlobalObject o : GlobalRegistry.blocks.list())
			if (o.value(version) != null) objects.add(o.value(version));
		objects.sort(Comparator.naturalOrder());
		for (GameObjectType o : objects)
			object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("blocks", object);

		object = Json.object();
		objects.clear();
		for (GlobalObject o : GlobalRegistry.effects.list())
			if (o.value(version) != null) objects.add(o.value(version));
		objects.sort(Comparator.naturalOrder());
		for (GameObjectType o : objects)
		{
			JsonObject e = Json.object();
			e.add("global", o.globalValue().idPrefixless());
			e.add("id", ((Effect) o).idInt);
			object.add(o.idPrefixless(), e);
		}
		root.add("effects", object);

		object = Json.object();
		objects.clear();
		for (GlobalObject o : GlobalRegistry.enchantments.list())
			if (o.value(version) != null) objects.add(o.value(version));
		objects.sort(Comparator.naturalOrder());
		for (GameObjectType o : objects)
		{
			JsonObject e = Json.object();
			e.add("global", o.globalValue().idPrefixless());
			e.add("id", ((Enchantment) o).idInt);
			if (((Enchantment) o).maxLevel != 1) e.add("max_level", ((Enchantment) o).maxLevel);
			object.add(o.idPrefixless(), e);
		}
		root.add("enchantments", object);

		object = Json.object();
		objects.clear();
		for (GlobalObject o : GlobalRegistry.entities.list())
			if (o.value(version) != null) objects.add(o.value(version));
		objects.sort(Comparator.naturalOrder());
		for (GameObjectType o : objects)
			object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("entities", object);

		object = Json.object();
		objects.clear();
		for (GlobalObject o : GlobalRegistry.items.list())
			if (o.value(version) != null) objects.add(o.value(version));
		objects.sort(Comparator.naturalOrder());
		for (GameObjectType o : objects)
			object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("items", object);

		object = Json.object();
		objects.clear();
		for (GlobalObject o : GlobalRegistry.particles.list())
			if (o.value(version) != null) objects.add(o.value(version));
		objects.sort(Comparator.naturalOrder());
		for (GameObjectType o : objects)
			object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("particles", object);

		object = Json.object();
		objects.clear();
		for (GlobalObject o : GlobalRegistry.sounds.list())
			if (o.value(version) != null) objects.add(o.value(version));
		objects.sort(Comparator.naturalOrder());
		for (GameObjectType o : objects)
			object.add(o.idPrefixless(), o.globalValue().idPrefixless());
		root.add("sounds", object);

		FileUtils.writeToFile(root.toString(PrettyPrint.indentWithTabs()), new File("resources/data/v" + version.id + "/data.json"));
	}

}
