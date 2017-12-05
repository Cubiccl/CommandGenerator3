package fr.cubiccl.generator3.game.map;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class MapTreeItem
{

	public static class MapFolder extends MapTreeItem
	{
		private String name;

		public MapFolder(String name)
		{
			super(true);
			this.name = name;
		}

		@Override
		public Image icon()
		{
			return new Image(Map.class.getResourceAsStream("/textures/ui/folder.png"));
		}

		public String name()
		{
			return this.name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

	}

	public final boolean isDirectory;
	private TreeItem<MapTreeItem> treeItem;

	public MapTreeItem(boolean isDirectory)
	{
		this.isDirectory = isDirectory;
	}

	private void createTreeItem()
	{
		this.treeItem = new TreeItem<MapTreeItem>(this, new ImageView(this.icon()));
	}

	public TreeItem<MapTreeItem> getTreeItem()
	{
		if (this.treeItem == null) this.createTreeItem();
		return this.treeItem;
	}

	public abstract Image icon();

}
