package com.sdabyd2.programowanie;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainPane.fxml"));
		Pane mainPain = loader.load();
		Scene scene = new Scene(mainPain);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Wirtualny notatnik czytelnika");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
