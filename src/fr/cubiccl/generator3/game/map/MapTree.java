package fr.cubiccl.generator3.game.map;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import fr.cubiccl.generator3.util.Text;

public class MapTree
{
	public static class AdvancementsFolder implements MapContent
	{
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

	public static class CommandsFolder implements MapContent
	{
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

	public static class FunctionsFolder implements MapContent
	{
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

	public static class LootTablesFolder implements MapContent
	{
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

	public static class RecipesFolder implements MapContent
	{
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

	public final TreeItem<MapContent> advancements, commands, functions, loottables, recipes;
	public final Map map;
	public final TreeItem<MapContent> root;

	@SuppressWarnings("unchecked")
	public MapTree(Map map)
	{
		this.map = map;
		this.root = MapContent.create(this.map);
		this.advancements = MapContent.create(new AdvancementsFolder());
		this.commands = MapContent.create(new CommandsFolder());
		this.functions = MapContent.create(new FunctionsFolder());
		this.loottables = MapContent.create(new LootTablesFolder());
		this.recipes = MapContent.create(new RecipesFolder());

		this.root.getChildren().addAll(this.advancements, this.commands, this.functions, this.loottables, this.recipes);
	}

}
