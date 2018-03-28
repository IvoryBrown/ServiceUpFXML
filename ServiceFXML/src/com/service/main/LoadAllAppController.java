package com.service.main;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.device.calendar.CalendarPane;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadAllAppController implements Initializable {
	@FXML
	private ProgressBar progresBar;
	@FXML
	private AnchorPane anchorPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadApp();

	}

	@SuppressWarnings("unchecked")
	protected void loadApp() {
		String s = CalendarPane.sDate;
		@SuppressWarnings("rawtypes")
		Task task = taskWorker(10);
		progresBar.progressProperty().unbind();
		progresBar.progressProperty().bind(task.progressProperty());
		task.setOnSucceeded(e_ -> {
			Stage stage1 = (Stage) anchorPane.getScene().getWindow();
			stage1.close();
			try {
				Parent root = FXMLLoader
						.load(getClass().getResource("/com/service/setting/fxmlmean/NewDeviceCalendar.fxml"));
				Stage stage = new Stage();
				stage.setWidth(1300);
				stage.setHeight(650);
				stage.setTitle(s);
				stage.getIcons()
						.add(new Image(getClass().getResourceAsStream("/com/service/setting/desing/icon-it.png")));
				stage.setScene(new Scene(root));
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Thread thread = new Thread(task);
		thread.start();
	}

	@SuppressWarnings("rawtypes")
	private Task taskWorker(int secounds) {

		return new Task() {
			@Override
			protected Object call() throws Exception {
				for (int i = 0; i < secounds; i++) {
					updateProgress(i + 1, secounds);
					Thread.sleep(200);
				}
				return true;
			};

		};

	}
}
