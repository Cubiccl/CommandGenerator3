package fr.cubiccl.generator3.game.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

import fr.cubiccl.generator3.game.object.type.GameObjectType;
import fr.cubiccl.generator3.util.Logger;

public class GameObjectRegistry<T extends GameObjectType>
{

	private final HashMap<String, T> objects;

	public GameObjectRegistry()
	{
		this.objects = new HashMap<String, T>();
	}

	public T find(String id)
	{
		return this.objects.get(id);
	}

	public Collection<T> list()
	{
		ArrayList<T> list = new ArrayList<T>(this.objects.values());
		list.sort(Comparator.naturalOrder());
		return list;
	}

	public void register(T object)
	{
		if (this.objects.containsKey(object.idPrefixless())) Logger.log("Object " + object.idPrefixless() + " already exists !");
		else
		{
			this.objects.put(object.idPrefixless(), object);
			//System.out.println("Registered " + object.describe());
		}
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
