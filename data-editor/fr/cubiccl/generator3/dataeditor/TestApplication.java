package fr.cubiccl.generator3.dataeditor;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import fr.cubiccl.generator3.MainApplication;

public class TestApplication extends Application
{

	public static final byte GLOBAL_OBJECTS = 0, VERSION_OBJECTS = 1, BLOCK_STATES = 2;

	public static TestApplication instance;

	public static void initialize(String[] args)
	{
		launch(args);
	}

	public Stage primaryStage;

	public void setScene(byte sceneID)
	{
		String scenePath = null;
		if (sceneID == GLOBAL_OBJECTS) scenePath = "view/main-test.fxml";
		else if (sceneID == VERSION_OBJECTS) scenePath = "view/object-editor.fxml";
		else if (sceneID == BLOCK_STATES) scenePath = "view/states-editor.fxml";

		if (scenePath == null) return;
		try
		{
			Parent root = FXMLLoader.load(getClass().getResource(scenePath));
			Scene scene = new Scene(root, 700, 500);
			// scene.getStylesheets().add(getClass().getResource("view/generator.css").toExternalForm());
			this.primaryStage.setScene(scene);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		instance = this;
		this.primaryStage = stage;
		this.primaryStage.setOnCloseRequest(e -> {
			e.consume();
			Main.exit();
		});

		stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/textures/block/command_block.png")));
		stage.show();
		stage.titleProperty().setValue("Generator3 Testing");
		this.setScene(GLOBAL_OBJECTS);
	}

}
