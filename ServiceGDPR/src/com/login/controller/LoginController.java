package com.login.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.login.main.LoginMain;
import com.login.setting.setting.main.SettingMain;
import com.main.normalsize.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LoginController implements Initializable {

	@FXML
	private void btnLogin() {
		Main main = new Main();
		main.startEnd();
		LoginMain.primaryStageLoginMain.close();
	}

	@FXML
	private void exitLoginBtn() {
		System.exit(0);

	}

	@FXML
	private void loginSetting() {
		SettingMain main = new SettingMain();
		main.start();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
