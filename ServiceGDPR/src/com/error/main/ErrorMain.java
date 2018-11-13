package com.error.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ErrorMain {
	public static Stage primaryStageErrorMain;
	public void start() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/error/view/FxmlError.fxml"));
			Stage stage = new Stage();
			primaryStageErrorMain = stage;
			stage.setResizable(false);
			stage.setTitle("Hibabejelentés");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setScene(new Scene(root, 1200, 650));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}
