package com.main.normalsize;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main  {
	public static Stage primaryStage;

	@SuppressWarnings("static-access")
//	@Override
//	public void start(Stage primaryStage) {
//		this.primaryStage = primaryStage;
//		try {
//			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
//			Scene scene = new Scene(root, 300, 600);
//			primaryStage.setScene(scene);
//			primaryStage.initStyle(StageStyle.TRANSPARENT);
//			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
//			// pozitcio
//			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//			primaryStage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 300);
//			primaryStage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 600);
//			primaryStage.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		launch(args);
//	}

	public void startEnd() {
		try {
			Stage stage =  new Stage();
			primaryStage = stage;
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root, 300, 600);
			stage.setScene(scene);
			stage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			// pozitcio
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 300);
			primaryStage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 600);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
