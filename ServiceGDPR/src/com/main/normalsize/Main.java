package com.main.normalsize;

import com.log.filedelete.FileDelete;
import com.log.newlogdatabase.NewLogDatabase;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main  {
	public static Stage primaryStage;

	public void startEnd() {
		try {
			Stage stage =  new Stage();
			primaryStage = stage;
			stage.setOnCloseRequest(evt -> {
		        // prevent window from closing
		        evt.consume();

		        // execute own shutdown procedure
		        shutdown(stage);
		    });
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
	
	private void shutdown(Stage mainWindow) {
	    Alert alert = new Alert(Alert.AlertType.NONE, "Biztos ki akarsz lépni?", ButtonType.YES, ButtonType.NO);
	    alert.getDialogPane().getStylesheets().add("/com/setting/showinfo/ShowInfo.css");
	    alert.initStyle(StageStyle.TRANSPARENT);
	    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
	    	new NewLogDatabase().newLog();
	    	new FileDelete();
	        mainWindow.close();
	        System.exit(0);
	    }
	}

}
