package fr.cubiccl.generator3.game.map;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;

public class Map implements Comparable<Map>, MapContent
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

	public TreeItem<MapContent> createTree()
	{
		this.tree = new MapTree(this);
		return this.tree.root;
	}

	@Override
	public Image icon()
	{
		return new Image(Map.class.getResourceAsStream("/textures/ui/map.png"));
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
