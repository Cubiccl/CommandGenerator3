package fr.cubiccl.generator3;

import fr.cubiccl.generator3.game.object.GameObjectLoader;
import fr.cubiccl.generator3.util.Lang;
import fr.cubiccl.generator3.util.Settings;
import fr.cubiccl.generator3.util.Textures;

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
		Lang.fullReload();
		Textures.createTextures();
		GameObjectLoader.loadObjects();
		MainApplication.initialize(args);
	}
}
