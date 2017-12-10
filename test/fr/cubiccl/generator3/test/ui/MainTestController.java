package fr.cubiccl.generator3.test.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import fr.cubiccl.generator3.game.object.GlobalRegistry;
import fr.cubiccl.generator3.game.object.global.GlobalObject;

public class MainTestController implements Initializable
{
	@SuppressWarnings("rawtypes")
	private GlobalRegistry currentRegistry;

	public TextField id, order, searchbox;
	public ListView<String> modeSelection;
	public Label nameLabel, modeLabel;
	public ListView<GlobalObject> objectSelection;
	public Button ok, cancel;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.modeSelection.getItems().addAll("Blocks", "Items", "Entities", "Attributes", "Effects", "Enchantments", "NBT Tags", "Particles", "Sounds");
		this.modeSelection.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<String>()
		{
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> c)
			{
				onModeSelected(c.getList().get(0));
			}
		});

		this.objectSelection.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<GlobalObject>()
		{
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends GlobalObject> c)
			{
				reloadObject();
			}
		});
		this.searchbox.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				reloadObjects();
			}
		});
	}

	public void onCancel()
	{
		this.reloadObject();
	}

	private void onModeSelected(String mode)
	{
		if (mode == null) return;

		switch (mode)
		{
			case "Items":
				currentRegistry = GlobalRegistry.items;
				break;
			case "Entities":
				currentRegistry = GlobalRegistry.entities;
				break;
			case "Attributes":
				currentRegistry = GlobalRegistry.attributes;
				break;
			case "Effects":
				currentRegistry = GlobalRegistry.effects;
				break;
			case "Enchantments":
				currentRegistry = GlobalRegistry.enchantments;
				break;
			case "NBT Tags":
				currentRegistry = GlobalRegistry.nbttags;
				break;
			case "Particles":
				currentRegistry = GlobalRegistry.particles;
				break;
			case "Sounds":
				currentRegistry = GlobalRegistry.sounds;
				break;
			default:
				currentRegistry = GlobalRegistry.blocks;
				break;
		}

		this.modeLabel.textProperty().setValue(mode);
		this.reloadObjects();
	}

	public void onOK()
	{
		GlobalObject object = this.objectSelection.getSelectionModel().getSelectedItem();
		if (object == null) return;

		object.order = Double.parseDouble(this.order.getText());
		this.reloadObjects();
	}

	private void reloadObject()
	{
		GlobalObject object = this.objectSelection.getSelectionModel().getSelectedItem();
		if (object == null) return;

		this.nameLabel.textProperty().setValue(object.id);
		this.id.setText(object.id);
		this.order.setText(String.valueOf(object.order));

	}

	@SuppressWarnings("unchecked")
	private void reloadObjects()
	{
		ArrayList<GlobalObject> objects = new ArrayList<>();
		objects.addAll(currentRegistry.list());
		objects.sort(Comparator.naturalOrder());
		String search = this.searchbox.getText();
		objects.removeIf(o -> {
			return !o.id.contains(search);
		});
		this.objectSelection.getItems().setAll(objects);
	}

}
