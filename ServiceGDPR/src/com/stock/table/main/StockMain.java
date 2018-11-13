package com.stock.table.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StockMain {
	public static Stage primaryStageStockMain;

	public void start() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/stock/table/view/StockTable.fxml"));
			Stage stage = new Stage();
			primaryStageStockMain = stage;
			stage.setTitle("Raktár");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setScene(new Scene(root, 1300, 700));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
