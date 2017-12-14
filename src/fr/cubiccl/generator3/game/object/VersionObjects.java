package fr.cubiccl.generator3.game.object;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonObject.Member;
import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.game.object.global.*;
import fr.cubiccl.generator3.game.object.type.*;
import fr.cubiccl.generator3.util.FileUtils;
import fr.cubiccl.generator3.util.Logger;
import fr.cubiccl.generator3.util.Settings.Version;

public class VersionObjects
{

	private static void createAttribute(Version version, String name, JsonValue value)
	{
		Attribute attribute = new Attribute(name);
		GlobalAttribute global = GlobalRegistry.attributes.find(value.asString());
		if (global == null) Logger.log("No global attribute found for id \"" + value.asString() + "\". Could not register attribute \"" + name + "\" ("
				+ version.name + ").");
		else if (global.value(version) != null) Logger.log("Global attribute \"" + value.asString()
				+ "\" is already translated. Could not register attribute \"" + name + "\" (" + version.name + ").");
		else VersionTranslator.translator(version).attributes.put(global, attribute);
	}

	private static void createBlock(Version version, String name, JsonValue value)
	{
		Block block = new Block(name);
		GlobalBlock global = GlobalRegistry.blocks.find(value.asString());
		if (global == null) Logger.log("No global block found for id \"" + value.asString() + "\". Could not register block \"" + name + "\" (" + version.name
				+ ").");
		else if (global.value(version) != null) Logger.log("Global block \"" + value.asString() + "\" is already translated. Could not register block \""
				+ name + "\" (" + version.name + ").");
		else VersionTranslator.translator(version).blocks.put(global, block);
	}

	private static void createEffect(Version version, String name, JsonValue value)
	{
		JsonObject o = value.asObject();
		String g = o.getString("global", "");

		Effect effect = new Effect(o.getInt("id", 0), name);
		GlobalEffect global = GlobalRegistry.effects.find(g);
		if (global == null) Logger.log("No global effect found for id \"" + g + "\". Could not register effect \"" + name + "\" (" + version.name + ").");
		else if (global.value(version) != null) Logger.log("Global effect \"" + g + "\" is already translated. Could not register effect \"" + name + "\" ("
				+ version.name + ").");
		else VersionTranslator.translator(version).effects.put(global, effect);
	}

	private static void createEnchantment(Version version, String name, JsonValue value)
	{
		JsonObject o = value.asObject();
		String g = o.getString("global", "");

		Enchantment enchantment = new Enchantment(o.getInt("id", 0), name, o.getInt("max_level", 1));
		GlobalEnchantment global = GlobalRegistry.enchantments.find(g);
		if (global == null) Logger.log("No global enchantment found for id \"" + g + "\". Could not register enchantment \"" + name + "\" (" + version.name
				+ ").");
		else if (global.value(version) != null) Logger.log("Global enchantment \"" + g + "\" is already translated. Could not register enchantment \"" + name
				+ "\" (" + version.name + ").");
		else VersionTranslator.translator(version).enchantments.put(global, enchantment);
	}

	private static void createEntity(Version version, String name, JsonValue value)
	{
		Entity entity = new Entity(name);
		GlobalEntity global = GlobalRegistry.entities.find(value.asString());
		if (global == null) Logger.log("No global entity found for id \"" + value.asString() + "\". Could not register entity \"" + name + "\" ("
				+ version.name + ").");
		else if (global.value(version) != null) Logger.log("Global entity \"" + value.asString() + "\" is already translated. Could not register entity \""
				+ name + "\" (" + version.name + ").");
		else VersionTranslator.translator(version).entities.put(global, entity);
	}

	private static void createItem(Version version, String name, JsonValue value)
	{
		Item item = new Item(name);
		GlobalItem global = GlobalRegistry.items.find(value.asString());
		if (global == null) Logger.log("No global item found for id \"" + value.asString() + "\". Could not register item \"" + name + "\" (" + version.name
				+ ").");
		else if (global.value(version) != null) Logger.log("Global item \"" + value.asString() + "\" is already translated. Could not register item \"" + name
				+ "\" (" + version.name + ").");
		else VersionTranslator.translator(version).items.put(global, item);
	}

	private static void createParticle(Version version, String name, JsonValue value)
	{
		Particle particle = new Particle(name);
		GlobalParticle global = GlobalRegistry.particles.find(value.asString());
		if (global == null) Logger.log("No global particle found for id \"" + value.asString() + "\". Could not register particle \"" + name + "\" ("
				+ version.name + ").");
		else if (global.value(version) != null) Logger.log("Global particle \"" + value.asString() + "\" is already translated. Could not register particle \""
				+ name + "\" (" + version.name + ").");
		else VersionTranslator.translator(version).particles.put(global, particle);
	}

	private static void createSound(Version version, String name, JsonValue value)
	{
		Sound sound = new Sound(name);
		GlobalSound global = GlobalRegistry.sounds.find(name);
		if (global == null) Logger.log("No global sound found for id \"" + value.asString() + "\". Could not register sound \"" + name + "\" (" + version.name
				+ ").");
		else if (global.value(version) != null) Logger.log("Global sound \"" + value.asString() + "\" is already translated. Could not register sound \""
				+ name + "\" (" + version.name + ").");
		else VersionTranslator.translator(version).sounds.put(global, sound);
	}

	public static void loadObjects()
	{
		for (Version version : Version.getVersions())
			loadObjects(version);
	}

	private static void loadObjects(Version version)
	{
		JsonObject root = Json.parse(FileUtils.readFile("/data/v" + version.id + "/data.json")).asObject();

		if (root.get("attributes") != null) for (Member m : root.get("attributes").asObject())
			createAttribute(version, m.getName(), m.getValue());

		if (root.get("blocks") != null) for (Member m : root.get("blocks").asObject())
			createBlock(version, m.getName(), m.getValue());

		if (root.get("effects") != null) for (Member m : root.get("effects").asObject())
			createEffect(version, m.getName(), m.getValue());

		if (root.get("enchantments") != null) for (Member m : root.get("enchantments").asObject())
			createEnchantment(version, m.getName(), m.getValue());

		if (root.get("entities") != null) for (Member m : root.get("entities").asObject())
			createEntity(version, m.getName(), m.getValue());

		if (root.get("items") != null) for (Member m : root.get("items").asObject())
			createItem(version, m.getName(), m.getValue());

		if (root.get("particles") != null) for (Member m : root.get("particles").asObject())
			createParticle(version, m.getName(), m.getValue());

		if (root.get("sounds") != null) for (Member m : root.get("sounds").asObject())
			createSound(version, m.getName(), m.getValue());
	}
}
