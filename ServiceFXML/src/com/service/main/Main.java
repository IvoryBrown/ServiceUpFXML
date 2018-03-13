package com.service.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/service/setting/fxmllogin/LoginFxml.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setWidth(764);
			primaryStage.setHeight(513);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setTitle("PcVipService");
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
