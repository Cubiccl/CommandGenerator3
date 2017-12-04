package fr.cubiccl.generator3.game.map;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface MapContent
{

	public static TreeItem<MapContent> create(MapContent item)
	{
		TreeItem<MapContent> i = new TreeItem<MapContent>(item, new ImageView(item.icon()));
		return i;
	}

	public Image icon();

}
