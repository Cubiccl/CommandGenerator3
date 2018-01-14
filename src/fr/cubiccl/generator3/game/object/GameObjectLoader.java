package fr.cubiccl.generator3.game.object;

import java.util.ArrayList;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonObject.Member;
import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.game.object.type.*;
import fr.cubiccl.generator3.util.FileUtils;
import fr.cubiccl.generator3.util.Logger;
import fr.cubiccl.generator3.util.Settings.Version;

public class GameObjectLoader
{

	private static Attribute createAttribute(Version version, String name, JsonValue value)
	{
		return new Attribute(name, value.asInt());
	}

	private static Block createBlock(Version version, String name, JsonValue value)
	{
		JsonObject o = value.asObject();
		Block b = new Block(name, o.get("states").asArray().get(0).asObject().getInt("id", 0));
		if (o.get("properties") != null) for (Member state : o.get("properties").asObject())
		{
			ArrayList<String> values = new ArrayList<String>();
			for (JsonValue v : state.getValue().asArray())
				values.add(v.asString());
			b.addBlockState(new BlockState(state.getName(), values.toArray(new String[values.size()])));
		}
		return b;
	}

	private static Effect createEffect(Version version, String name, JsonValue value)
	{
		return new Effect(value.asObject().getInt("id", 0), name);
	}

	private static Enchantment createEnchantment(Version version, String name, JsonValue value)
	{
		JsonObject o = value.asObject();
		return new Enchantment(o.getInt("id", 0), name, o.getInt("max_level", 1));
	}

	private static Entity createEntity(Version version, String name, JsonValue value)
	{
		return new Entity(name, value.asInt());
	}

	private static Item createItem(Version version, String name, JsonValue value)
	{
		return new Item(name, value.asObject().getInt("protocol_id", 0));
	}

	private static Particle createParticle(Version version, JsonValue value)
	{
		return new Particle(value.asString());
	}

	private static Sound createSound(Version version, JsonValue value)
	{
		return new Sound(value.asString());
	}

	public static void loadObjects()
	{
		for (Version version : Version.getVersions())
			loadObjects(version);
	}

	private static void loadObjects(Version version)
	{
		Logger.log("---------------- Loading version " + version.name + "------------");
		GameObjects.create(version);

		Logger.log("Loading attributes.");
		JsonValue root = Json.parse(FileUtils.readFile("/data/v" + version.id + "/attributes.json"));
		for (Member m : root.asObject())
			GameObjects.registry(version).attributes.register(createAttribute(version, m.getName(), m.getValue()));

		Logger.log("Loading blocks.");
		root = Json.parse(FileUtils.readFile("/data/v" + version.id + "/blocks.json"));
		for (Member m : root.asObject())
			GameObjects.registry(version).blocks.register(createBlock(version, m.getName(), m.getValue()));

		Logger.log("Loading effects.");
		root = Json.parse(FileUtils.readFile("/data/v" + version.id + "/effects.json"));
		for (Member m : root.asObject())
			GameObjects.registry(version).effects.register(createEffect(version, m.getName(), m.getValue()));

		Logger.log("Loading enchantments.");
		root = Json.parse(FileUtils.readFile("/data/v" + version.id + "/enchantments.json"));
		for (Member m : root.asObject())
			GameObjects.registry(version).enchantments.register(createEnchantment(version, m.getName(), m.getValue()));

		Logger.log("Loading entities.");
		root = Json.parse(FileUtils.readFile("/data/v" + version.id + "/entities.json"));
		for (Member m : root.asObject())
			GameObjects.registry(version).entities.register(createEntity(version, m.getName(), m.getValue()));

		Logger.log("Loading items.");
		root = Json.parse(FileUtils.readFile("/data/v" + version.id + "/items.json"));
		for (Member m : root.asObject())
			GameObjects.registry(version).items.register(createItem(version, m.getName(), m.getValue()));

		Logger.log("Loading particles.");
		root = Json.parse(FileUtils.readFile("/data/v" + version.id + "/particles.json"));
		for (JsonValue m : root.asArray())
			GameObjects.registry(version).particles.register(createParticle(version, m));

		Logger.log("Loading sounds.");
		root = Json.parse(FileUtils.readFile("/data/v" + version.id + "/sounds.json"));
		for (JsonValue m : root.asArray())
			GameObjects.registry(version).sounds.register(createSound(version, m));
	}
}
