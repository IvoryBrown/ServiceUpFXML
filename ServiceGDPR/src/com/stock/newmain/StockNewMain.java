package com.stock.newmain;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StockNewMain {
	public void start() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/stock/newview/NewStock.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Raktár");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setScene(new Scene(root, 1300, 700));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
