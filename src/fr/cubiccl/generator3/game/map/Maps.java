package fr.cubiccl.generator3.game.map;

import java.util.ArrayList;
import java.util.Comparator;

public class Maps
{

	private static final ArrayList<Map> maps = new ArrayList<Map>();

	static
	{
		maps.add(new Map("Map 1"));
		maps.add(new Map("Map 2"));
	}

	public static int indexOf(Map map)
	{
		return maps.indexOf(map);
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Map> list()
	{
		return (ArrayList<Map>) maps.clone();
	}

	public static void register(Map map)
	{
		maps.add(map);
		maps.sort(Comparator.naturalOrder());
	}

	public static void unregister(Map map)
	{
		maps.remove(map);
	}

}
