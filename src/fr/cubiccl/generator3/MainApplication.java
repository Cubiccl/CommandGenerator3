package fr.cubiccl.generator3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import fr.cubiccl.generator3.util.Text;

public class MainApplication extends Application
{
	public static final Text applicationName = new Text("general.title");

	public static void initialize(String[] args)
	{
		launch(args);
	}

	public Stage primaryStage;

	public MainApplication()
	{
		CommandGenerator.window = this;
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		this.primaryStage = stage;
		this.primaryStage.setOnCloseRequest(e -> {
			e.consume();
			CommandGenerator.exit();
		});
		Parent root = FXMLLoader.load(getClass().getResource("view/main.fxml"));
		Scene scene = new Scene(root, 300, 300);
		scene.getStylesheets().add(getClass().getResource("view/generator.css").toExternalForm());
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.getIcons().add(new Image(MainApplication.class.getResourceAsStream("/textures/blocks/command_block.png")));
		stage.show();
		stage.titleProperty().bind(applicationName.value);
	}

}
