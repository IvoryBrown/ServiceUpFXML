package com.fxdialog.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXDialogMain  { 
	public static Stage primaryStageFXDialogMain;
	@SuppressWarnings("static-access")
	public void start() {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/com/fxdialog/view/FXDialog.fxml"));
			Stage stage = new Stage();
			this.primaryStageFXDialogMain = stage;
			stage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setWidth(650);
			stage.setHeight(220);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
