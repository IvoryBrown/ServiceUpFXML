package com.main.minsize;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainMin {
	public static Stage primaryStage;

	@SuppressWarnings("static-access")
	public void start() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainMin.fxml"));
			Stage stage = new Stage();
			this.primaryStage = stage;
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setWidth(69);
			stage.setHeight(54);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setScene(scene);


			// pozitcio
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			stage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 69);
			stage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 54);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
