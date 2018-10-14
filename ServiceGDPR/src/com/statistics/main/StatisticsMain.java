package com.statistics.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StatisticsMain {
	public static Stage primaryStageStatisticsMain;

	@SuppressWarnings("static-access")
	public void start() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/com/statistics/view/FxmlStatistics.fxml"));
			Stage stage = new Stage();
			this.primaryStageStatisticsMain = stage;
			Scene scene = new Scene(root);
			stage.setWidth(1300);
			stage.setHeight(730);
			stage.setTitle("PcVipService");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
