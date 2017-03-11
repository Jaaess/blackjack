package blackjack.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class BlackJackApp extends Application {
	
	/*
	 * Method to instantiate a Stage object and load the scene from the fxml file
	 * Also load style.css file which is the background image
	 * 
	 */

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/blackjack/view/game.fxml"));
		
		SplitPane root = (SplitPane)loader.load();
		root.setId("pane");
		
		Scene scene = new Scene(root);
		
		scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Blackjack");
		primaryStage.setResizable(true);  
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
