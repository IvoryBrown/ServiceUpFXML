package com.login.setting.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SettingMain  { 
	public static Stage primaryStageSettingMain;
	@SuppressWarnings("static-access")

	public void start() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/login/setting/view/SettingMain.fxml"));
			Stage stage = new Stage();
			this.primaryStageSettingMain = stage;
			stage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setWidth(900);
			stage.setHeight(700);
			stage.setScene(scene);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
