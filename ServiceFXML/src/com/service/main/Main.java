package com.service.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/service/setting/fxmlsetting/ServiceFX.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setWidth(1350);
			primaryStage.setHeight(710);
			//primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setTitle("Szerviz");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
