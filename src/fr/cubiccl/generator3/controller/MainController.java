package fr.cubiccl.generator3.controller;

import fr.cubiccl.generator3.CommandGenerator;

public class MainController
{

	public void onMenuPreferences()
	{
		SceneController.loadScene(SceneController.Preferences);
	}

	public void onMenuQuit()
	{
		CommandGenerator.exit();
	}

}
