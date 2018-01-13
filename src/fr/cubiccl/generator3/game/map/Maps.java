package fr.cubiccl.generator3.game.map;

import java.util.Comparator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import fr.cubiccl.generator3.controller.main.MainController;

public class Maps
{

	private static final ObservableList<Map> maps = FXCollections.observableArrayList();

	static
	{
		maps.add(new Map("Map 1"));
		maps.add(new Map("Map 2"));
	}

	public static int indexOf(Map map)
	{
		return maps.indexOf(map);
	}

	public static List<Map> list()
	{
		return maps.subList(0, maps.size());
	}

	public static void register(Map map)
	{
		maps.add(map);
		maps.sort(Comparator.naturalOrder());
		MainController.instance.mapExplorer.getRoot().getChildren().add(map.createTree());
		MainController.instance.mapExplorer.getRoot().getChildren().sort((TreeItem<MapTreeItem> o1, TreeItem<MapTreeItem> o2) -> {
			return o1.getValue().toString().toLowerCase().compareTo(o2.getValue().toString().toLowerCase());
		});
	}

	public static void unregister(Map map)
	{
		maps.remove(map);
		MainController.instance.mapExplorer.getRoot().getChildren().remove(map.tree().root);
	}

}
