package fr.cubiccl.generator3.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.PrettyPrint;

import fr.cubiccl.generator3.game.object.GlobalRegistry;
import fr.cubiccl.generator3.game.object.global.GlobalObject;
import fr.cubiccl.generator3.util.FileUtils;
import fr.cubiccl.generator3.util.Lang;
import fr.cubiccl.generator3.util.Settings;

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

		FileUtils.writeToFile(root.toString(PrettyPrint.indentWithTabs()), new File("test/global_objects.json"));
	}

	public static void main(String[] args)
	{
		Settings.loadSettings();
		Lang.fullReload();
		GlobalRegistry.loadObjects();
		TestApplication.initialize(args);
	}

}
