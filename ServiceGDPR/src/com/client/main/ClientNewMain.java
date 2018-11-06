package com.client.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ClientNewMain {
	public static Stage primaryStageLoginMain;
	

	public void newClientBtn() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/client/view/NewClient.fxml"));
			Stage stage = new Stage();
			ClientNewMain.primaryStageLoginMain = stage;
			stage.setTitle("Ügyfél");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setScene(new Scene(root, 1300, 650));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
