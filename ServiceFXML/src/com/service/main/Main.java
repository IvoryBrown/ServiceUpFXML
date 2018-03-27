package com.service.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application { 
	public static Stage primaryStage;
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/com/service/setting/fxmllogin/LoginFxml.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setWidth(764);
			primaryStage.setHeight(513);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setTitle("PcVipService");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/service/setting/desing/icon-it.png")));
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
