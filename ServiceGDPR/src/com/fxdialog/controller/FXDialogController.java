package com.fxdialog.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.fxdialog.main.FXDialogMain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXDialogController implements Initializable {

	@FXML
	private Label message;
	@FXML
	private Button yesButton, noButton;

	private void noButton() {
		noButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				FXDialogMain.primaryStageFXDialogMain.close();
			}
		});
		yesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Message.getStage().close();
				FXDialogMain.primaryStageFXDialogMain.close();
			}
		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		message.setText(Message.getMesssage());
		noButton();
	}

}
