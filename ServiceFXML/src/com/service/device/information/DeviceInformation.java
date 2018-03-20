package com.service.device.information;

import java.io.File;
import java.net.MalformedURLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DeviceInformation {
	@FXML
	private WebView webView = new WebView();
	@FXML
	private TextField actionStatus;
	private FileChooser fileChooser;
	private File file;

	@FXML
	public void initialize() {

	}

	@FXML
	private void fileOpen() {
		fileChooser = new FileChooser();
		fileChooser.setTitle("Report beillesztés");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("HTM (*.htm)", "*.htm");
		fileChooser.getExtensionFilters().add(extFilter);
		file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			browers(file);
			actionStatus.setText(file.getAbsolutePath());
		}
	}

	private void browers(File file) {
		WebEngine engine = webView.getEngine();
		try {
			engine.load(file.toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
