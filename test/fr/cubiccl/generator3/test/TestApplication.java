package fr.cubiccl.generator3.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import fr.cubiccl.generator3.MainApplication;

public class TestApplication extends Application
{

	public static TestApplication instance;
	public Stage primaryStage;

	public static void initialize(String[] args)
	{
		launch(args);
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
		Parent root = FXMLLoader.load(getClass().getResource("view/main-test.fxml"));
		Scene scene = new Scene(root, 300, 300);
		//scene.getStylesheets().add(getClass().getResource("view/generator.css").toExternalForm());
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/textures/blocks/command_block.png")));
		stage.show();
		stage.titleProperty().setValue("Generator3 Testing");

	}

}
