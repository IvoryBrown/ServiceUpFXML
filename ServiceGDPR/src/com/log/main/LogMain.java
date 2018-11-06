package com.log.main;

import com.company.main.CompanyMain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LogMain {
	public static Stage primaryStageLoginMain;

	public void start() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/log/view/Log.fxml"));
			Stage stage = new Stage();
			CompanyMain.primaryStageLoginMain = stage;
			stage.setTitle("Cég");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setScene(new Scene(root, 1300, 650));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
