package fr.cubiccl.generator3.dataeditor.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import fr.cubiccl.generator3.dataeditor.TestApplication;
import fr.cubiccl.generator3.game.object.GameObjectRegistry;
import fr.cubiccl.generator3.game.object.global.*;
import fr.cubiccl.generator3.util.Lang;

public class MainTestController implements Initializable
{
	public static MainTestController instance;

	@SuppressWarnings("rawtypes")
	private GameObjectRegistry currentRegistry;

	public Label modeLabel, nameLabel;
	public ListView<String> modeSelection;
	public ListView<GlobalObject> objectSelection;
	public TextField searchbox;
	public Button top, up, down, bottom;

	public void goToVersion()
	{
		TestApplication.instance.setScene(TestApplication.VERSION_OBJECTS);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		instance = this;

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
		this.objectSelection.setCellFactory(param -> new OrderableCell());
		this.searchbox.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				select(newValue);
			}
		});
	}

	public void move(int from, int to)
	{
		if (this.selected() == -1) return;

		boolean negative = to < from;
		for (int i = negative ? from - 1 : from + 1; negative ? i >= to : i <= to;)
		{
			this.objectSelection.getItems().get(i).order += negative ? 1 : -1;
			if (negative) --i;
			else ++i;
		}
		this.objectSelection.getItems().get(from).order += to - from;
		//if (!negative) this.objectSelection.getItems().get(from).order += 1;
		this.reloadObjects();
		this.objectSelection.getSelectionModel().select(to);
		// this.objectSelection.scrollTo(Math.max(0, to - 10));

		this.objectSelection.requestFocus();
	}

	public void onAbove()
	{
		this.move(this.selected(), this.selected() - 10);
	}

	public void onBelow()
	{
		this.move(this.selected(), this.selected() + 10);
	}

	public void onBottom()
	{
		this.move(this.selected(), this.objectSelection.getItems().size() - 1);
	}

	public void onDelete()
	{
		GlobalObject object = this.objectSelection.getSelectionModel().getSelectedItem();
		if (object == null) return;

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete");
		alert.setHeaderText("");
		alert.setContentText("Are you sure you want to delete " + object.name + " !?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			this.onBottom();
			//this.currentRegistry.unregister(object);
			this.reloadObjects();
		}
	}

	public void onDown()
	{
		this.move(this.selected(), this.selected() + 1);
	}

	private void onModeSelected(String mode)
	{
		if (mode == null) return;

		/*switch (mode)
		{
			case "Items":
				currentRegistry = GameObjectRegistry.items;
				break;
			case "Entities":
				currentRegistry = GameObjectRegistry.entities;
				break;
			case "Attributes":
				currentRegistry = GameObjectRegistry.attributes;
				break;
			case "Effects":
				currentRegistry = GameObjectRegistry.effects;
				break;
			case "Enchantments":
				currentRegistry = GameObjectRegistry.enchantments;
				break;
			case "NBT Tags":
				currentRegistry = GameObjectRegistry.nbttags;
				break;
			case "Particles":
				currentRegistry = GameObjectRegistry.particles;
				break;
			case "Sounds":
				currentRegistry = GameObjectRegistry.sounds;
				break;
			default:
				currentRegistry = GameObjectRegistry.blocks;
				break;
		}*/

		this.modeLabel.textProperty().setValue(mode);
		this.reloadObjects();
	}

	@SuppressWarnings("unused")
	public void onNew()
	{
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("New object");
		dialog.setHeaderText("");
		dialog.setContentText("Type in the id of the new object:");

		Optional<String> result = dialog.showAndWait();
		if (!result.isPresent()) return;

		String id = result.get();
		GlobalObject newObject = null;
		String mode = this.modeSelection.getSelectionModel().getSelectedItem();
		int order = this.currentRegistry.size();
		switch (mode)
		{
			case "Blocks":
				newObject = new GlobalBlock(id, order);
				break;
			case "Items":
				newObject = new GlobalItem(id, order);
				break;
			case "Entities":
				newObject = new GlobalEntity(id, order);
				break;
			case "Attributes":
				newObject = new GlobalAttribute(id, order);
				break;
			case "Effects":
				newObject = new GlobalEffect(id, order);
				break;
			case "Enchantments":
				newObject = new GlobalEnchantment(id, order);
				break;
			case "NBT Tags":
				newObject = new GlobalNBTTag(id, order);
				break;
			case "Particles":
				newObject = new GlobalParticle(id, order);
				break;
			case "Sounds":
				newObject = new GlobalSound(id, order);
				break;
			default:
				break;
		}

		//if (newObject != null) currentRegistry.register(newObject);
		this.reloadObjects();
	}

	public void onTop()
	{
		this.move(this.selected(), 0);
	}

	public void onUp()
	{
		this.move(this.selected(), this.selected() - 1);
	}

	public void reloadLanguage()
	{
		Lang.fullReload();
	}

	protected void reloadObject()
	{
		GlobalObject object = this.objectSelection.getSelectionModel().getSelectedItem();
		this.nameLabel.setText(object == null ? "(No selection)" : object.order + " : " + object.id);
	}

	@SuppressWarnings("unchecked")
	private void reloadObjects()
	{
		ArrayList<GlobalObject> objects = new ArrayList<>();
		objects.addAll(currentRegistry.list());
		objects.sort(Comparator.naturalOrder());
		this.objectSelection.getItems().setAll(objects);
	}

	@SuppressWarnings("unchecked")
	protected void select(String id)
	{
		ArrayList<GlobalObject> objects = new ArrayList<>();
		objects.addAll(currentRegistry.list());
		objects.sort(Comparator.naturalOrder());
		String search = this.searchbox.getText();
		int index = -1;
		for (int i = 0; i < objects.size(); ++i)
			if (objects.get(i).name.toString().contains(search))
			{
				index = i;
				break;
			}
		if (index != -1)
		{
			this.objectSelection.getSelectionModel().select(index);
			this.objectSelection.scrollTo(Math.max(0, index - 10));
		}
	}

	private int selected()
	{
		return this.objectSelection.getSelectionModel().getSelectedIndex();
	}

}
