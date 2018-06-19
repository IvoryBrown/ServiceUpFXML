package com.login.setting.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.login.setting.main.SettingMain;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class SettingController implements Initializable {
	@FXML
	private PasswordField loginTxt;
	@FXML
	private AnchorPane loginAPane;
	@FXML
	private SplitPane loginSPane;

	@FXML
	private void loginTxt() {
		if (loginTxt.getText().equals("123")) {
			loginAPane.setVisible(false);
			loginSPane.setVisible(true);
		} else {
			SettingMain.primaryStageSettingMain.close();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Automatikusan előállított metóduscsonk

	}

}
