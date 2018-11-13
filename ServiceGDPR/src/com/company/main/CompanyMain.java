package com.company.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CompanyMain {
	public static Stage primaryStageCompanyMain;

	public void start() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/company/view/Company.fxml"));
			Stage stage = new Stage();
			primaryStageCompanyMain = stage;
			stage.setTitle("Cég");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setScene(new Scene(root, 1300, 650));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
