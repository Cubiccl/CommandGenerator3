package fr.cubiccl.generator3.controller.main;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import fr.cubiccl.generator3.game.map.MapTreeItem;

public class MapExplorerController
{

	public final TreeView<MapTreeItem> mapExplorer;

	public MapExplorerController(TreeView<MapTreeItem> mapExplorer)
	{
		this.mapExplorer = mapExplorer;
		this.mapExplorer.setOnMouseClicked(e -> {
			onMouseClicked(e);
		});
		this.mapExplorer.setOnMouseDragged(e -> {
			onMouseDragged(e);
		});
		this.mapExplorer.setOnMouseDragOver(e -> {
			onMouseDragOver(e);
		});
	}

	private void onMouseClicked(MouseEvent e)
	{
		if (e.getClickCount() == 2)
		{
			TreeItem<MapTreeItem> item = this.selectedItem();
			if (item.getValue().isDirectory && !item.isExpanded()) item.setExpanded(true);
			if (!item.getValue().isDirectory) System.out.println("Opening " + item.getValue().toString());
		}
	}

	private void onMouseDragged(MouseEvent e)
	{}

	private void onMouseDragOver(MouseEvent e)
	{}

	public TreeItem<MapTreeItem> selectedItem()
	{
		return this.mapExplorer.getSelectionModel().getSelectedItem();
	}

}
