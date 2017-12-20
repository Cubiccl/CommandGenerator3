package fr.cubiccl.generator3.dataeditor.ui;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import fr.cubiccl.generator3.game.object.global.GlobalObject;

public class OrderableCell extends ListCell<GlobalObject>
{
	@SuppressWarnings("rawtypes")
	public OrderableCell()
	{
		ListCell thisCell = this;
		String defaultStyle = getStyle();

		setOnDragDetected(event -> {
			if (getItem() == null) return;

			Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			content.putString(event.getButton().name().charAt(0) + Integer.toString(getIndex()));
			dragboard.setContent(content);

			event.consume();
		});

		setOnDragOver(event -> {
			if (event.getGestureSource() != thisCell && event.getDragboard().hasString()) event.acceptTransferModes(TransferMode.MOVE);
			event.consume();
		});

		setOnDragEntered(event -> {
			if (event.getGestureSource() != thisCell && event.getDragboard().hasString())
			{
				if (event.getDragboard().getString().charAt(0) == 'S') setOpacity(0.3);
				else setStyle("-fx-border-width: 5 1 1 1; -fx-border-color: #0096C9 transparent transparent transparent;");
			}
		});

		setOnDragExited(event -> {
			if (event.getGestureSource() != thisCell && event.getDragboard().hasString()) {
				if (event.getDragboard().getString().charAt(0) == 'S') setOpacity(1);
				else setStyle(defaultStyle);
			}
		});

		setOnDragDropped(event -> {
			if (getItem() == null) return;

			Dragboard db = event.getDragboard();
			boolean success = false;

			if (db.hasString())
			{
				ObservableList<GlobalObject> items = getListView().getItems();
				int draggedIdx = Integer.parseInt(db.getString().substring(1));
				int thisIdx = items.indexOf(getItem());
				if (db.getString().charAt(0) == 'S')
				{
					GlobalObject dragged = items.get(draggedIdx);
					GlobalObject thisObject = items.get(thisIdx);

					items.set(draggedIdx, thisObject);
					items.set(thisIdx, dragged);

					int draggedOrder = dragged.order;
					int thisOrder = thisObject.order;
					dragged.order = thisOrder;
					thisObject.order = draggedOrder;

					getListView().getSelectionModel().select(thisIdx);

					success = true;
				} else MainTestController.instance.move(draggedIdx, thisIdx - (draggedIdx > thisIdx ? 0 : 1));
			}
			event.setDropCompleted(success);

			event.consume();
		});

		setOnDragDone(DragEvent::consume);
	}

	@Override
	protected void updateItem(GlobalObject item, boolean empty)
	{
		super.updateItem(item, empty);
		this.setText(empty || item == null ? "" : item.toString());
	}
}
