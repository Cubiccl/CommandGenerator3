package fr.cubiccl.generator3.game.datapack;

import java.util.ArrayList;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonObject.Member;
import com.eclipsesource.json.JsonValue;

import fr.cubiccl.generator3.game.datapack.DataPack.VanillaDataPack;
import fr.cubiccl.generator3.game.object.type.Attribute;
import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.BlockState;
import fr.cubiccl.generator3.game.object.type.Effect;
import fr.cubiccl.generator3.game.object.type.Enchantment;
import fr.cubiccl.generator3.game.object.type.Entity;
import fr.cubiccl.generator3.game.object.type.Item;
import fr.cubiccl.generator3.game.object.type.Particle;
import fr.cubiccl.generator3.game.object.type.Sound;
import fr.cubiccl.generator3.util.FileUtils;
import fr.cubiccl.generator3.util.Logger;

public class GameObjectLoader
{

	private static Attribute createAttribute(VanillaDataPack VanillaDataPack, String name, JsonValue value)
	{
		return new Attribute(name, value.asInt());
	}

	private static Block createBlock(VanillaDataPack VanillaDataPack, String name, JsonValue value)
	{
		JsonObject o = value.asObject();
		Block b = new Block(name.replaceAll("minecraft:", ""), o.get("states").asArray().get(0).asObject().getInt("id", 0));
		if (o.get("properties") != null) for (Member state : o.get("properties").asObject())
		{
			ArrayList<String> values = new ArrayList<String>();
			for (JsonValue v : state.getValue().asArray())
				values.add(v.asString());
			b.addBlockState(new BlockState(state.getName(), values.toArray(new String[values.size()])));
		}
		return b;
	}

	private static Effect createEffect(VanillaDataPack VanillaDataPack, String name, JsonValue value)
	{
		return new Effect(value.asObject().getInt("id", 0), name);
	}

	private static Enchantment createEnchantment(VanillaDataPack VanillaDataPack, String name, JsonValue value)
	{
		JsonObject o = value.asObject();
		return new Enchantment(o.getInt("id", 0), name, o.getInt("max_level", 1));
	}

	private static Entity createEntity(VanillaDataPack VanillaDataPack, String name, JsonValue value)
	{
		return new Entity(name, value.asInt());
	}

	private static Item createItem(VanillaDataPack VanillaDataPack, String name, JsonValue value)
	{
		return new Item(name.replaceAll("minecraft:", ""), value.asObject().getInt("protocol_id", 0));
	}

	private static Particle createParticle(VanillaDataPack VanillaDataPack, JsonValue value)
	{
		return new Particle(value.asString());
	}

	private static Sound createSound(VanillaDataPack VanillaDataPack, JsonValue value)
	{
		return new Sound(value.asString());
	}

	static void loadObjects(VanillaDataPack VanillaDataPack)
	{
		Logger.log("---------------- Loading version " + VanillaDataPack.getName() + "------------");

		Logger.log("Loading attributes.");
		JsonValue root = Json.parse(FileUtils.readFile("/data/" + VanillaDataPack.getName() + "/attributes.json"));
		for (Member m : root.asObject())
			VanillaDataPack.attributes.register(createAttribute(VanillaDataPack, m.getName(), m.getValue()));

		Logger.log("Loading blocks.");
		root = Json.parse(FileUtils.readFile("/data/" + VanillaDataPack.getName() + "/blocks.json"));
		for (Member m : root.asObject())
			VanillaDataPack.blocks.register(createBlock(VanillaDataPack, m.getName(), m.getValue()));

		Logger.log("Loading effects.");
		root = Json.parse(FileUtils.readFile("/data/" + VanillaDataPack.getName() + "/effects.json"));
		for (Member m : root.asObject())
			VanillaDataPack.effects.register(createEffect(VanillaDataPack, m.getName(), m.getValue()));

		Logger.log("Loading enchantments.");
		root = Json.parse(FileUtils.readFile("/data/" + VanillaDataPack.getName() + "/enchantments.json"));
		for (Member m : root.asObject())
			VanillaDataPack.enchantments.register(createEnchantment(VanillaDataPack, m.getName(), m.getValue()));

		Logger.log("Loading entities.");
		root = Json.parse(FileUtils.readFile("/data/" + VanillaDataPack.getName() + "/entities.json"));
		for (Member m : root.asObject())
			VanillaDataPack.entities.register(createEntity(VanillaDataPack, m.getName(), m.getValue()));

		Logger.log("Loading items.");
		root = Json.parse(FileUtils.readFile("/data/" + VanillaDataPack.getName() + "/items.json"));
		for (Member m : root.asObject())
			VanillaDataPack.items.register(createItem(VanillaDataPack, m.getName(), m.getValue()));

		Logger.log("Loading particles.");
		root = Json.parse(FileUtils.readFile("/data/" + VanillaDataPack.getName() + "/particles.json"));
		for (JsonValue m : root.asArray())
			VanillaDataPack.particles.register(createParticle(VanillaDataPack, m));

		Logger.log("Loading sounds.");
		root = Json.parse(FileUtils.readFile("/data/" + VanillaDataPack.getName() + "/sounds.json"));
		for (JsonValue m : root.asArray())
			VanillaDataPack.sounds.register(createSound(VanillaDataPack, m));
	}
}
