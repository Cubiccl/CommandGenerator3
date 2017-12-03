package fr.cubiccl.generator3.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import fr.cubiccl.generator3.util.Settings;
import fr.cubiccl.generator3.util.Settings.Language;

public class PreferencesController implements Initializable
{
	public ChoiceBox<Language> language;
	public TextField location;

	private void exit()
	{
		SceneController.closeTopStage();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		this.language.getItems().addAll(Language.values());
		this.language.setValue(Settings.language());
		this.location.setText(Settings.getSetting(Settings.MINECRAFT_LOCATION));
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

		Settings.setLanguage(this.language.getValue());
		Settings.setSetting(Settings.MINECRAFT_LOCATION, this.location.getText());
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
		chooser.setTitle("Location ?");

		File chosen = chooser.showDialog(SceneController.topStage());
		if (chosen != null) this.location.setText(chosen.getAbsolutePath());
	}

	public void onOk()
	{
		if (this.onApply()) this.exit();
	}

}
