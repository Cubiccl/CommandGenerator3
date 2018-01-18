package fr.cubiccl.generator3.game.map;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import fr.cubiccl.generator3.util.Text;

public class MapTree extends MapTreeItem
{
	public static class AdvancementsFolder extends MapTreeItem
	{
		public AdvancementsFolder()
		{
			super(true);
		}

		@Override
		public Image icon()
		{
			return new Image(Map.class.getResourceAsStream("/textures/ui/advancements.png"));
		}

		@Override
		public String toString()
		{
			return new Text("treemap.advancements").toString();
		}
	}

	public static class CommandsFolder extends MapTreeItem
	{

		public CommandsFolder()
		{
			super(true);
		}

		@Override
		public Image icon()
		{
			return new Image(Map.class.getResourceAsStream("/textures/ui/commands.png"));
		}

		@Override
		public String toString()
		{
			return new Text("treemap.commands").toString();
		}
	}

	public static class FunctionsFolder extends MapTreeItem
	{

		public FunctionsFolder()
		{
			super(true);
		}

		@Override
		public Image icon()
		{
			return new Image(Map.class.getResourceAsStream("/textures/ui/functions.png"));
		}

		@Override
		public String toString()
		{
			return new Text("treemap.functions").toString();
		}
	}

	public static class LootTablesFolder extends MapTreeItem
	{

		public LootTablesFolder()
		{
			super(true);
		}

		@Override
		public Image icon()
		{
			return new Image(Map.class.getResourceAsStream("/textures/ui/loottables.png"));
		}

		@Override
		public String toString()
		{
			return new Text("treemap.loottables").toString();
		}
	}

	public static class RecipesFolder extends MapTreeItem
	{

		public RecipesFolder()
		{
			super(true);
		}

		@Override
		public Image icon()
		{
			return new Image(Map.class.getResourceAsStream("/textures/ui/recipes.png"));
		}

		@Override
		public String toString()
		{
			return new Text("treemap.recipes").toString();
		}
	}

	public final TreeItem<MapTreeItem> advancements, commands, functions, loottables, recipes;
	public final Map map;
	public final TreeItem<MapTreeItem> root;

	@SuppressWarnings("unchecked")
	public MapTree(Map map)
	{
		super(true);
		this.map = map;
		this.root = this.getTreeItem();
		this.advancements = new AdvancementsFolder().getTreeItem();
		this.commands = new CommandsFolder().getTreeItem();
		this.functions = new FunctionsFolder().getTreeItem();
		this.loottables = new LootTablesFolder().getTreeItem();
		this.recipes = new RecipesFolder().getTreeItem();

		this.root.getChildren().addAll(this.advancements, this.functions, this.commands, this.loottables, this.recipes);
	}

	@Override
	public Image icon()
	{
		return new Image(Map.class.getResourceAsStream("/textures/ui/map.png"));
	}

	@Override
	public String toString()
	{
		return this.map.name;
	}

}
