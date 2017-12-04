package fr.cubiccl.generator3.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Stack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import fr.cubiccl.generator3.CommandGenerator;

public class SceneController
{

	private static final HashSet<String> inPopup = new HashSet<String>();
	public static final String Main = "main", Preferences = "menu/preferences";
	private static final Stack<Stage> popups = new Stack<Stage>();

	static
	{
		inPopup.add(Preferences);
	}

	public static Stage closeTopStage()
	{
		Stage s = null;
		if (!popups.isEmpty())
		{
			s = popups.pop();
			s.titleProperty().unbind();
			s.close();
		}
		return s;
	}

	public static Stage loadScene(String sceneID)
	{
		try
		{
			Scene scene = new Scene(FXMLLoader.load(SceneController.class.getResource("/fr/cubiccl/generator3/view/" + sceneID + ".fxml")));
			if (inPopup.contains(sceneID))
			{
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initModality(Modality.APPLICATION_MODAL);
				popups.push(stage);
				stage.getIcons().add(CommandGenerator.window.primaryStage.getIcons().get(0));
				stage.show();
				return stage;
			} else
			{
				CommandGenerator.window.primaryStage.setScene(scene);
				return CommandGenerator.window.primaryStage;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static Stage topStage()
	{
		return popups.peek();
	}

}
