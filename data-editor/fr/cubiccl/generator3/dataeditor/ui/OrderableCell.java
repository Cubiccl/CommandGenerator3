package fr.cubiccl.generator3.dataeditor.ui;

import java.util.Comparator;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import fr.cubiccl.generator3.game.object.global.GlobalObject;
import fr.cubiccl.generator3.game.object.global.TexturedObject;

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
			if (getItem() instanceof TexturedObject)
			{
				Image image = ((TexturedObject) getItem()).texture();
				if (image != null) dragboard.setDragView(image);
			}
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
			if (event.getGestureSource() != thisCell && event.getDragboard().hasString())
			{
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

					int draggedOrder = dragged.order;
					int thisOrder = thisObject.order;
					dragged.order = thisOrder;
					thisObject.order = draggedOrder;

					getListView().getItems().sort(Comparator.naturalOrder());
					getListView().getSelectionModel().select(thisIdx);

				} else MainTestController.instance.move(draggedIdx, thisIdx - (draggedIdx > thisIdx ? 0 : 1));
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
		this.setText(empty || item == null ? "" : item.toString());
		if (item instanceof TexturedObject)
		{
			Image image = ((TexturedObject) item).texture();
			if (image != null)
			{
				ImageView iv = new ImageView(image);
				iv.setFitHeight(24);
				iv.setFitWidth(24);
				this.setGraphic(iv);
			} else this.setGraphic(null);
		} else this.setGraphic(null);
	}
}
