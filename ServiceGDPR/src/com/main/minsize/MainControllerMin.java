package com.main.minsize;

import com.main.normalsize.Main;

import javafx.fxml.FXML;

public class MainControllerMin {
	MainMin mainMin;
	

	@FXML
	private void exitButton() {
		MainMin.primaryStage.close();
		Main main = new Main();
		main.startEnd();
	}

}
