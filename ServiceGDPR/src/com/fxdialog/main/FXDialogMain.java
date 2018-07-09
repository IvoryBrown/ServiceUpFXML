package com.fxdialog.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
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
			scene.setFill(Color.TRANSPARENT);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setWidth(550);
			stage.setHeight(170);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
