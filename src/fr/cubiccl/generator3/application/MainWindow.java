package fr.cubiccl.generator3.application;

import fr.cubiccl.generator3.CommandGenerator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application
{

	public static void initialize(String[] args)
	{
		launch(args);
	}

	public MainWindow()
	{
		CommandGenerator.window = this;
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}

}
