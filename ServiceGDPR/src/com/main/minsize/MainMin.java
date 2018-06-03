package com.main.minsize;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainMin {
	public static Stage primaryStage;

	@SuppressWarnings("static-access")
	public void start() {

		try {
			System.out.println("3");
			Parent root = FXMLLoader.load(getClass().getResource("MainMin.fxml"));
			Stage stage = new Stage();
			this.primaryStage = stage;
			stage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root);
			stage.setWidth(69);
			stage.setHeight(54);
			stage.setScene(scene);
			stage.show();

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
