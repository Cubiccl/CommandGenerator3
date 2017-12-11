package fr.cubiccl.generator3.game.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonObject.Member;

import fr.cubiccl.generator3.game.object.global.*;
import fr.cubiccl.generator3.util.FileUtils;
import fr.cubiccl.generator3.util.Settings.Version;

public class GlobalRegistry<T extends GlobalObject>
{
	public static final Comparator<GlobalObject> _idComparator = new Comparator<GlobalObject>()
	{
		@Override
		public int compare(GlobalObject o1, GlobalObject o2)
		{
			return o1.id.toLowerCase().compareTo(o2.id.toLowerCase());
		}
	};

	public static final GlobalRegistry<GlobalAttribute> attributes = new GlobalRegistry<GlobalAttribute>();
	public static final GlobalRegistry<GlobalBlock> blocks = new GlobalRegistry<GlobalBlock>();
	public static final GlobalRegistry<GlobalEffect> effects = new GlobalRegistry<GlobalEffect>();
	public static final GlobalRegistry<GlobalEnchantment> enchantments = new GlobalRegistry<GlobalEnchantment>();
	public static final GlobalRegistry<GlobalEntity> entities = new GlobalRegistry<GlobalEntity>();
	public static final GlobalRegistry<GlobalItem> items = new GlobalRegistry<GlobalItem>();
	public static final GlobalRegistry<GlobalNBTTag> nbttags = new GlobalRegistry<GlobalNBTTag>();
	public static final GlobalRegistry<GlobalParticle> particles = new GlobalRegistry<GlobalParticle>();
	public static final GlobalRegistry<GlobalSound> sounds = new GlobalRegistry<GlobalSound>();

	public static void loadObjects()
	{
		JsonObject root = Json.parse(FileUtils.readFile("/data/global_objects.json")).asObject();

		JsonObject object = root.get("attributes").asObject();
		for (Member m : object)
			attributes.register(new GlobalAttribute(m.getName(), m.getValue().asInt()));

		object = root.get("blocks").asObject();
		for (Member m : object)
			blocks.register(new GlobalBlock(m.getName(), m.getValue().asInt()));

		object = root.get("effects").asObject();
		for (Member m : object)
			effects.register(new GlobalEffect(m.getName(), m.getValue().asInt()));

		object = root.get("enchantments").asObject();
		for (Member m : object)
			enchantments.register(new GlobalEnchantment(m.getName(), m.getValue().asInt()));

		object = root.get("entities").asObject();
		for (Member m : object)
			entities.register(new GlobalEntity(m.getName(), m.getValue().asInt()));

		object = root.get("items").asObject();
		for (Member m : object)
			items.register(new GlobalItem(m.getName(), m.getValue().asInt()));

		object = root.get("nbttags").asObject();
		for (Member m : object)
			nbttags.register(new GlobalNBTTag(m.getName(), m.getValue().asInt()));

		object = root.get("particles").asObject();
		for (Member m : object)
			particles.register(new GlobalParticle(m.getName(), m.getValue().asInt()));

		object = root.get("sounds").asObject();
		for (Member m : object)
			sounds.register(new GlobalSound(m.getName(), m.getValue().asInt()));
	}

	private final HashMap<String, T> objects;

	public GlobalRegistry()
	{
		this.objects = new HashMap<String, T>();
	}

	public T find(String id)
	{
		return this.objects.get(id);
	}

	public Collection<T> list()
	{
		return this.list(null);
	}

	public Collection<T> list(Version version)
	{
		ArrayList<T> list = new ArrayList<T>(this.objects.values());
		list.sort(_idComparator);
		list.removeIf((T object) -> {
			return !object.exists(version);
		});
		return list;
	}

	public void register(T object)
	{
		this.objects.put(object.id, object);
	}

	public int size()
	{
		return this.objects.size();
	}

	public void unregister(T object)
	{
		this.objects.remove(object.id);
	}

}
