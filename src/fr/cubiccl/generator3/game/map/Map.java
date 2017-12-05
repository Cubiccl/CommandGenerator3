package fr.cubiccl.generator3.game.map;

import javafx.scene.control.TreeItem;

public class Map implements Comparable<Map>
{

	// Version
	// Functions
	// Structures
	// Saved Commands
	// Advancements
	// Recipes
	// Loot tables
	// Scoreboard context

	public final String name;
	private MapTree tree;

	public Map(String name)
	{
		this.name = name;
	}

	@Override
	public int compareTo(Map o)
	{
		return this.name.compareTo(o.name);
	}

	public TreeItem<MapTreeItem> createTree()
	{
		return (this.tree = new MapTree(this)).root;
	}

	@Override
	public String toString()
	{
		return this.name;
	}

	public MapTree tree()
	{
		return this.tree;
	}

}
