package fr.cubiccl.generator3;

import fr.cubiccl.generator3.util.Lang;
import fr.cubiccl.generator3.util.Settings;

public class CommandGenerator
{

	public static MainApplication window;

	public static void exit()
	{
		window.primaryStage.close();
		Settings.save();
	}

	public static void main(String[] args)
	{
		Settings.loadSettings();
		Lang.updateLang();
		MainApplication.initialize(args);
	}
}
