package com.fxdialog.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXDialogController implements Initializable{

	@FXML
	private static Label message;
	@FXML
	private Button yesButton, noButton;
	
	
	public static void setdf(String messag) {
		message.setText(messag);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Automatikusan előállított metóduscsonk
		
	}
}
