package fr.cubiccl.generator3.controller.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import fr.cubiccl.generator3.CommandGenerator;
import fr.cubiccl.generator3.controller.SceneController;
import fr.cubiccl.generator3.game.map.Map;
import fr.cubiccl.generator3.game.map.MapContent;
import fr.cubiccl.generator3.game.map.MapExplorerTreeCell;
import fr.cubiccl.generator3.game.map.Maps;
import fr.cubiccl.generator3.util.Text;

public class MainController implements Initializable
{
	private static final Text explorerLabelT = new Text("treemap.title");

	public static MainController instance;

	private static final Text menuEditT = new Text("menu.edit");

	private static final Text menuFileExitT = new Text("menu.file.exit");
	private static final Text menuFileNewT = new Text("menu.file.new");
	private static final Text menuFileOpenT = new Text("menu.file.open");
	private static final Text menuFilePreferencesT = new Text("menu.file.preferences");
	private static final Text menuFileT = new Text("menu.file");

	private static final Text menuHelpT = new Text("menu.help");

	public Label explorerLabel;
	public TreeView<MapContent> mapExplorer;
	public Menu menuFile, menuEdit, menuHelp;
	public MenuItem menuFileNew, menuFileOpen, menuFilePreferences, menuFileExit;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		instance = this;

		this.menuFile.textProperty().bind(menuFileT.value);
		this.menuFileNew.textProperty().bind(menuFileNewT.value.concat("..."));
		this.menuFileOpen.textProperty().bind(menuFileOpenT.value.concat("..."));
		this.menuFilePreferences.textProperty().bind(menuFilePreferencesT.value.concat("..."));
		this.menuFileExit.textProperty().bind(menuFileExitT.value);

		this.menuEdit.textProperty().bind(menuEditT.value);

		this.menuHelp.textProperty().bind(menuHelpT.value);

		this.explorerLabel.textProperty().bind(explorerLabelT.value);

		List<Map> maps = Maps.list();
		TreeItem<MapContent> root = new TreeItem<MapContent>(null);
		for (Map map : maps)
			root.getChildren().add(map.createTree());

		this.mapExplorer.rootProperty().set(root);
		this.mapExplorer.setCellFactory(param -> {
			return new MapExplorerTreeCell();
		});
	}

	public void onMenuNew()
	{
		Maps.register(new Map("Map " + System.currentTimeMillis() % 1000));
	}

	public void onMenuPreferences()
	{
		SceneController.loadScene(SceneController.Preferences).titleProperty().bind(menuFilePreferencesT.value);
	}

	public void onMenuQuit()
	{
		CommandGenerator.exit();
	}

}
