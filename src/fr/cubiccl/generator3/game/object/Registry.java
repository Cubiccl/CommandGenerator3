package fr.cubiccl.generator3.game.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import fr.cubiccl.generator3.game.object.global.*;
import fr.cubiccl.generator3.util.Persistance;
import fr.cubiccl.generator3.util.Settings.Version;

public class Registry<T extends GlobalObject>
{
	public static final Comparator<GlobalObject> _idComparator = new Comparator<GlobalObject>()
	{
		@Override
		public int compare(GlobalObject o1, GlobalObject o2)
		{
			return o1.id.toLowerCase().compareTo(o2.id.toLowerCase());
		}
	};

	public static final Registry<GlobalAchievement> achievements = new Registry<GlobalAchievement>();
	public static final Registry<GlobalAttribute> attributes = new Registry<GlobalAttribute>();
	public static final Registry<GlobalBlock> blocks = new Registry<GlobalBlock>();
	public static final Registry<GlobalEffect> effects = new Registry<GlobalEffect>();
	public static final Registry<GlobalEnchantment> enchantments = new Registry<GlobalEnchantment>();
	public static final Registry<GlobalEntity> entities = new Registry<GlobalEntity>();
	public static final Registry<GlobalItem> items = new Registry<GlobalItem>();
	public static final Registry<GlobalNBTTag> nbttags = new Registry<GlobalNBTTag>();
	public static final Registry<GlobalParticle> particles = new Registry<GlobalParticle>();
	public static final Registry<GlobalSound> sounds = new Registry<GlobalSound>();

	private final HashMap<String, T> objects;

	public Registry()
	{
		this.objects = new HashMap<String, T>();
	}

	public T find(String id)
	{
		return this.objects.get(id);
	}

	public Collection<T> list()
	{
		return this.list(Persistance.selectedVersion);
	}

	public Collection<T> list(Version version)
	{
		ArrayList<T> list = new ArrayList<T>(this.objects.values());
		list.sort(_idComparator);
		list.removeIf((T object) -> {
			return object.exists(version);
		});
		return list;
	}

	public void register(T object)
	{
		this.objects.put(object.id, object);
	}

}
