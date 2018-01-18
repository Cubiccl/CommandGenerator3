package fr.cubiccl.generator3.dataeditor.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

import com.google.common.collect.BiMap;

import fr.cubiccl.generator3.dataeditor.TestApplication;
import fr.cubiccl.generator3.dataeditor.TestPersistance;
import fr.cubiccl.generator3.game.object.GameObjectRegistry;
import fr.cubiccl.generator3.game.object.Versions.Version;
import fr.cubiccl.generator3.game.object.global.GlobalObject;
import fr.cubiccl.generator3.game.object.type.Block;
import fr.cubiccl.generator3.game.object.type.Effect;
import fr.cubiccl.generator3.game.object.type.Enchantment;
import fr.cubiccl.generator3.game.object.type.GameObjectType;
import fr.cubiccl.generator3.util.Lang;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ObjectEditorController implements Initializable
{
	public Button buttonNumID, buttonMaxLevel, buttonBlockStates;
	@SuppressWarnings("rawtypes")
	private GameObjectRegistry currentRegistry;
	@SuppressWarnings("rawtypes")
	private BiMap currentTranslator;
	public Label modeLabel, nameLabel;
	public ListView<String> modeSelection;
	public ListView<GameObjectType> objectSelection;
	public TextField searchbox;

	public void changeVersion()
	{
		ArrayList<String> versions = new ArrayList<>();
		for (Version version : Version.getVersions())
			versions.add(version.id);

		ChoiceDialog<String> dialog = new ChoiceDialog<>("b", versions);
		dialog.setTitle("Version selection");
		dialog.setHeaderText("Version selection");
		dialog.setContentText("Choose the new version :");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
		{
			Version v = Version.get(result.get());
			TestPersistance.version = v;
			this.reloadObjects();
		}
	}

	public void editBlockStates()
	{
		TestPersistance.editedBlock = (Block) this.objectSelection.getSelectionModel().getSelectedItem();
		TestApplication.instance.setScene(TestApplication.BLOCK_STATES);
	}

	public void editGlobalLink()
	{
		GameObjectType object = this.objectSelection.getSelectionModel().getSelectedItem();
		if (object == null) return;

		/*Dialog<GlobalObject> dialog = this.globalObjectSelector(object.globalValue());

		Optional<GlobalObject> result = dialog.showAndWait();
		if (result.isPresent())
		{
			this.currentTranslator.remove(object.globalValue());
			this.currentTranslator.put(result.get(), object);
		}*/
	}

	public void editID()
	{
		GameObjectType object = this.objectSelection.getSelectionModel().getSelectedItem();
		if (object == null) return;

		TextInputDialog dialog = new TextInputDialog(object.idPrefixless());
		dialog.setTitle("Set object ID");
		dialog.setHeaderText("Set object ID");
		dialog.setContentText("Type in the new ID for this object, without \"minecraft:\" :");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) object.id = result.get();
	}

	public void editMaxLevel()
	{
		GameObjectType object = this.objectSelection.getSelectionModel().getSelectedItem();
		if (object == null || !(object instanceof Enchantment)) return;

		TextInputDialog dialog = new TextInputDialog(String.valueOf(((Enchantment) object).maxLevel));
		dialog.setTitle("Set enchantment max level");
		dialog.setHeaderText("Set enchantment max level");
		dialog.setContentText("Type in the new maximum level for this enchantment :");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
		{
			try
			{
				((Enchantment) object).maxLevel = Integer.parseInt(result.get());
			} catch (Exception e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erorr");
				alert.setContentText("Wrong integer!!");
			}
		}
	}

	public void editNumID()
	{
		GameObjectType object = this.objectSelection.getSelectionModel().getSelectedItem();
		if (object == null || !(object instanceof Effect || object instanceof Enchantment)) return;

		TextInputDialog dialog = new TextInputDialog(String.valueOf(object instanceof Effect ? ((Effect) object).idInt : ((Enchantment) object).idInt));
		dialog.setTitle("Set object num ID");
		dialog.setHeaderText("Set object num ID");
		dialog.setContentText("Type in the new num ID for this object :");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
		{
			try
			{
				if (object instanceof Effect) ((Effect) object).idInt = Integer.parseInt(result.get());
				else ((Enchantment) object).idInt = Integer.parseInt(result.get());
			} catch (Exception e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erorr");
				alert.setContentText("Wrong integer!!");
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Dialog<GlobalObject> globalObjectSelector(GlobalObject defaultValue)
	{
		Dialog<GlobalObject> dialog = new Dialog<>();
		dialog.setTitle("Set Global link");
		dialog.setHeaderText("Set the Global link for this object.");

		ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

		VBox box = new VBox(2);

		TextField sb = new TextField();
		sb.setPromptText("Search...");
		ListView<GlobalObject> list = new ListView<GlobalObject>();
		list.getItems().addAll(this.currentRegistry.list());
		if (defaultValue != null) list.getSelectionModel().select(defaultValue);

		box.getChildren().addAll(sb, list);
		VBox.setVgrow(list, Priority.ALWAYS);

		Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
		okButton.setDisable(true);
		list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			okButton.setDisable(newValue == null);
		});

		sb.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				ObservableList<GlobalObject> objects = list.getItems();
				int index = -1;
				for (int i = 0; i < objects.size(); ++i)
					if (objects.get(i).name.toString().contains(newValue))
					{
						index = i;
						break;
					}
				if (index != -1)
				{
					list.getSelectionModel().select(index);
					list.scrollTo(Math.max(0, index - 10));
				}
			}
		});

		dialog.getDialogPane().setContent(box);

		Platform.runLater(() -> sb.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == okButtonType) return list.getSelectionModel().getSelectedItem();
			return null;
		});
		return dialog;
	}

	public void goToGlobal()
	{
		TestApplication.instance.setScene(TestApplication.GLOBAL_OBJECTS);
	}

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
		this.objectSelection.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<GameObjectType>()
		{
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends GameObjectType> c)
			{
				reloadObject();
			}
		});
		this.searchbox.textProperty().addListener(new ChangeListener<String>()
		{
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				select(newValue);
			}
		});

		this.buttonNumID.setVisible(false);
		this.buttonMaxLevel.setVisible(false);
		this.buttonBlockStates.setVisible(false);
	}

	@SuppressWarnings("unchecked")
	private ArrayList<GameObjectType> listObjects()
	{
		ArrayList<GameObjectType> objects = new ArrayList<>();
		ArrayList<GlobalObject> globals = new ArrayList<>();
		globals.addAll(this.currentRegistry.list());
		globals.sort(Comparator.naturalOrder());
		if (this.currentRegistry == null) return objects;
		for (GlobalObject o : globals)
			if (o.value(TestPersistance.version) != null) objects.add(o.value(TestPersistance.version));
		return objects;
	}

	public void onDelete()
	{
		GameObjectType object = this.objectSelection.getSelectionModel().getSelectedItem();
		if (object == null) return;

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete");
		alert.setHeaderText("");
		alert.setContentText("Are you sure you want to delete " + object.name + " !?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{
			//this.currentTranslator.remove(object.globalValue());
			this.reloadObjects();
		}
	}

	private void onModeSelected(String mode)
	{
		if (mode == null) return;

		/*switch (mode)
		{
			case "Items":
				currentRegistry = GameObjectRegistry.items;
				currentTranslator = VersionTranslator.translator(TestPersistance.version).items;
				break;
			case "Entities":
				currentRegistry = GameObjectRegistry.entities;
				currentTranslator = VersionTranslator.translator(TestPersistance.version).entities;
				break;
			case "Attributes":
				currentRegistry = GameObjectRegistry.attributes;
				currentTranslator = VersionTranslator.translator(TestPersistance.version).attributes;
				break;
			case "Effects":
				currentRegistry = GameObjectRegistry.effects;
				currentTranslator = VersionTranslator.translator(TestPersistance.version).effects;
				break;
			case "Enchantments":
				currentRegistry = GameObjectRegistry.enchantments;
				currentTranslator = VersionTranslator.translator(TestPersistance.version).enchantments;
				break;
			case "NBT Tags":
				currentRegistry = GameObjectRegistry.nbttags;
				currentTranslator = VersionTranslator.translator(TestPersistance.version).nbtTags;
				break;
			case "Particles":
				currentRegistry = GameObjectRegistry.particles;
				currentTranslator = VersionTranslator.translator(TestPersistance.version).particles;
				break;
			case "Sounds":
				currentRegistry = GameObjectRegistry.sounds;
				currentTranslator = VersionTranslator.translator(TestPersistance.version).sounds;
				break;
			default:
				currentRegistry = GameObjectRegistry.blocks;
				currentTranslator = VersionTranslator.translator(TestPersistance.version).blocks;
				break;
		}*/

		this.buttonNumID.setVisible(mode.equals("Effects") || mode.equals("Enchantments"));
		this.buttonMaxLevel.setVisible(mode.equals("Enchantments"));
		this.buttonBlockStates.setVisible(mode.equals("Blocks"));

		this.modeLabel.textProperty().setValue(mode);
		this.reloadObjects();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public void onNew()
	{
		String mode = this.modeSelection.getSelectionModel().getSelectedItem();
		if (mode.equals("NBT Tags")) return;

		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("New object");
		dialog.setHeaderText("");
		dialog.setContentText("Type in the id of the new object:");

		Optional<String> result = dialog.showAndWait();
		if (!result.isPresent()) return;

		Dialog<GlobalObject> dialog2 = this.globalObjectSelector(null);
		Optional<GlobalObject> result2 = dialog2.showAndWait();
		if (!result2.isPresent()) return;

		String id = result.get();
		GameObjectType newObject = null;
		/*switch (mode)
		{
			case "Blocks":
				newObject = new Block(id);
				break;
			case "Items":
				newObject = new Item(id);
				break;
			case "Entities":
				newObject = new Entity(id);
				break;
			case "Attributes":
				newObject = new Attribute(id);
				break;
			case "Effects":
				newObject = new Effect(-1, id);
				break;
			case "Enchantments":
				newObject = new Enchantment(-1, id, 1);
				break;
			case "NBT Tags":
				// newObject = new NBTTag(id);
				break;
			case "Particles":
				newObject = new Particle(id);
				break;
			case "Sounds":
				newObject = new Sound(id);
				break;
			default:
				break;
		}*/

		if (newObject != null) this.currentTranslator.put(result2.get(), newObject);
		this.reloadObjects();
	}

	public void reloadLanguage()
	{
		Lang.fullReload();
	}

	protected void reloadObject()
	{
		GameObjectType object = this.objectSelection.getSelectionModel().getSelectedItem();
		this.nameLabel.setText(/*object == null ? "(No selection)" : object.globalValue().name + " -> " +*/ object.id);
	}

	private void reloadObjects()
	{
		this.objectSelection.getItems().setAll(this.listObjects());
	}

	protected void select(String id)
	{
		ArrayList<GameObjectType> objects = this.listObjects();
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

	public int selected()
	{
		return this.objectSelection.getSelectionModel().getSelectedIndex();
	}

}
