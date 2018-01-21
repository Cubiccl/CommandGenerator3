package fr.cubiccl.generator3.game.datapack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import fr.cubiccl.generator3.game.object.type.GameObjectType;
import fr.cubiccl.generator3.util.Logger;

public class GameObjectRegistry<T extends GameObjectType>
{

	private final DataPack datapack;
	private final HashMap<String, T> objects;

	public GameObjectRegistry(DataPack datapack)
	{
		this.datapack = datapack;
		this.objects = new HashMap<String, T>();
	}

	public T find(String id)
	{
		return this.objects.get(id.replaceAll("minecraft:", ""));
	}

	public Collection<T> list()
	{
		ArrayList<T> list = new ArrayList<T>(this.objects.values());
		list.sort(Comparator.naturalOrder());
		return list;
	}

	public void register(T object)
	{
		if (this.objects.containsKey(object.idWithoutNamespace())) Logger.log("Object " + object.idWithoutNamespace() + " already exists !");
		else
		{
			this.objects.put(object.idWithoutNamespace(), object);
			// System.out.println("Registered " + object.describe());
		}
		object.setDatapack(this.datapack.id);
	}

	public int size()
	{
		return this.objects.size();
	}

	public void unregister(T object)
	{
		this.objects.remove(object.idWithoutNamespace());
		object.setDatapack(-1);
	}

}
