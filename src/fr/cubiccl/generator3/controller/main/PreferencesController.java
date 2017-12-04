package fr.cubiccl.generator3.controller.main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import fr.cubiccl.generator3.controller.SceneController;
import fr.cubiccl.generator3.util.Settings;
import fr.cubiccl.generator3.util.Settings.Language;
import fr.cubiccl.generator3.util.Text;
import fr.cubiccl.generator3.util.Texts;

public class PreferencesController implements Initializable
{
	private static final Text browseT = new Text("preferences.location.browse");
	private static final Text languageT = new Text("preferences.language");
	private static final Text locationT = new Text("preferences.location");

	public ChoiceBox<Language> language;
	public TextField location;
	public Label locationLabel, languageLabel;
	public Button ok, apply, cancel, browse;

	private void exit()
	{
		SceneController.closeTopStage();
		this.languageLabel.textProperty().unbind();
		this.locationLabel.textProperty().unbind();
		this.ok.textProperty().unbind();
		this.apply.textProperty().unbind();
		this.cancel.textProperty().unbind();
		this.browse.textProperty().unbind();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.language.getItems().addAll(Language.values());
		this.language.setValue(Settings.language());
		this.location.setText(Settings.getSetting(Settings.MINECRAFT_LOCATION));

		this.languageLabel.textProperty().bind(languageT.value.concat(" :"));
		this.locationLabel.textProperty().bind(locationT.value.concat(" :"));
		this.ok.textProperty().bind(Texts.OK.value);
		this.apply.textProperty().bind(Texts.Apply.value);
		this.cancel.textProperty().bind(Texts.Cancel.value);
		this.browse.textProperty().bind(browseT.value);
	}

	public boolean onApply()
	{
		File location = new File(this.location.getText());
		if (!location.exists() || !location.isDirectory())
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);

			alert.setContentText("The .minecraft location is not a valid directory.");
			alert.showAndWait();
			return false;
		}

		Settings.setSetting(Settings.LANG, this.language.getValue().id);
		Settings.setSetting(Settings.MINECRAFT_LOCATION, this.location.getText());
		SceneController.topStage().sizeToScene();
		return true;
	}

	public void onCancel()
	{
		this.exit();
	}

	public void onLocationBrowse()
	{
		File current = new File(this.location.getText());

		DirectoryChooser chooser = new DirectoryChooser();
		if (current.exists() && current.getParentFile().isDirectory()) chooser.setInitialDirectory(current.getParentFile());
		chooser.setTitle(locationT.toString());

		File chosen = chooser.showDialog(SceneController.topStage());
		if (chosen != null) this.location.setText(chosen.getAbsolutePath());
	}

	public void onOk()
	{
		if (this.onApply()) this.exit();
	}

}
