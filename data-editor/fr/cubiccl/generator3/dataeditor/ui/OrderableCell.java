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

		setOnDragDetected(event -> {
			if (getItem() == null) { return; }

			Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
			ClipboardContent content = new ClipboardContent();
			content.putString(Integer.toString(getIndex()));
			dragboard.setContent(content);

			event.consume();
		});

		setOnDragOver(event -> {
			if (event.getGestureSource() != thisCell && event.getDragboard().hasString())
			{
				event.acceptTransferModes(TransferMode.MOVE);
			}

			event.consume();
		});

		setOnDragEntered(event -> {
			if (event.getGestureSource() != thisCell && event.getDragboard().hasString())
			{
				setOpacity(0.3);
			}
		});

		setOnDragExited(event -> {
			if (event.getGestureSource() != thisCell && event.getDragboard().hasString())
			{
				setOpacity(1);
			}
		});

		setOnDragDropped(event -> {
			if (getItem() == null) { return; }

			Dragboard db = event.getDragboard();
			boolean success = false;

			if (db.hasString())
			{
				ObservableList<GlobalObject> items = getListView().getItems();
				int draggedIdx = Integer.parseInt(db.getString());
				int thisIdx = items.indexOf(getItem());
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

		if (empty || item == null)
		{
			setText("");
		} else
		{
			setText(item.toString());
		}
	}
}
