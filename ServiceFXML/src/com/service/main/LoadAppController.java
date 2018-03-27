package com.service.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoadAppController implements Initializable {

	@FXML
	private ProgressIndicator pindicator;
	@FXML
	private AnchorPane loadAncjopsne;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadApp();
	}

	@SuppressWarnings("unchecked")
	protected  void loadApp() {
		@SuppressWarnings("rawtypes")
		Task task = taskWorker(10);
		pindicator.progressProperty().unbind();
		pindicator.progressProperty().bind(task.progressProperty());
		task.setOnSucceeded(e_->{
			Stage stage1 = (Stage) loadAncjopsne.getScene().getWindow();
			stage1.close();
			try {
				Parent root = FXMLLoader
						.load(getClass().getResource("/com/service/setting/fxmlsetting/ServiceFX.fxml"));
				Stage stage = new Stage();
				stage.setTitle("PcVipService");
				stage.setScene(new Scene(root, 1350, 700));
				stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/service/setting/desing/icon-it.png")));
				stage.show();
				Main.primaryStage.close();
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
					updateProgress(i+1, secounds);
					Thread.sleep(500);
				}
				return true;
			};

		};

	}

}
