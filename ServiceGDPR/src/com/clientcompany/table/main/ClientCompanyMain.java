package com.clientcompany.table.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ClientCompanyMain {
	public static Stage primaryStageClientCompanyMain;

	public void start() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/clientcompany/table/view/ClientCompany.fxml"));
			Stage stage = new Stage();
			primaryStageClientCompanyMain = stage;
			stage.setTitle("Ügyfél Tábla");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setScene(new Scene(root, 1300, 650));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
