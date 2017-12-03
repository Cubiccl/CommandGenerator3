package fr.cubiccl.generator3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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

	public void onApply()
	{
		System.out.println("Language: " + this.language.getValue().name);
		System.out.println("Location: " + this.location.getText());
	}

	public void onCancel()
	{
		this.exit();
	}

	public void onLocationBrowse()
	{}

	public void onOk()
	{
		this.onApply();
		this.exit();
	}

}
