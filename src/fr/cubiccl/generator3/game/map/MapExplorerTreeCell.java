package fr.cubiccl.generator3.game.map;

import java.util.Optional;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import fr.cubiccl.generator3.controller.main.MainController;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Text.Replacement;

public class MapExplorerTreeCell extends TreeCell<MapTreeItem>
{
	private static final Text deleteT = new Text("treemap.menu.delete");
	private static final Text newT = new Text("menu.file.new");

	private ContextMenu menu(MapTreeItem item)
	{
		ContextMenu menu = new ContextMenu();

		if (item == null)
		{
			MenuItem newmap = new MenuItem(newT.toString());
			newmap.setOnAction(e -> {
				MainController.instance.onMenuNew();
			});

			menu.getItems().add(newmap);
		} else if (item instanceof MapTree)
		{
			MenuItem remove = new MenuItem(deleteT.toString());
			remove.setOnAction(e -> {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText(deleteT.toString());
				alert.setContentText(new Text("treemap.menu.delete.confirm", new Replacement("<map>", item.toString())).toString());
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) Maps.unregister(((MapTree) item).map);
			});

			menu.getItems().add(remove);
		}

		return menu;
	}

	@Override
	protected void updateItem(MapTreeItem item, boolean empty)
	{
		super.updateItem(item, empty);
		this.setText(empty ? null : item.toString());
		this.setGraphic(empty ? null : getTreeItem().getGraphic());

		this.setContextMenu(this.menu(item));
	}

}
