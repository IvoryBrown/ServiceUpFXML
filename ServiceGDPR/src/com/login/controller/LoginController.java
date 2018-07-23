package com.login.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.login.database.LoginDatabsae;
import com.login.main.LoginMain;
import com.login.setting.setting.main.SettingMain;
import com.main.normalsize.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class LoginController implements Initializable {
	private LocalDate localDate;
	private String localDateSub;
	private Integer locadInteger;
	private Integer dbInteger;
	private String dbDate;
	@FXML
	private Label errorLb;

	@FXML
	private void btnLogin() {
		dateLinc();
		 if (locadInteger < dbInteger) {
			 Main main = new Main();
				main.startEnd();
				LoginMain.primaryStageLoginMain.close();
		 }else {
		 errorLb.setText("Hozzáférése lejárt");
		 }
		
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

	private void dateLinc() {
		localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString = localDate.format(formatter);
		localDateSub = formattedString.substring(0, 4) + formattedString.substring(5, 7)
				+ formattedString.substring(8, 10);
		System.out.println(localDateSub);
		for (int i = 0; i < LoginDatabsae.setGetDateLin().size(); i++) {
			dbDate = LoginDatabsae.setGetDateLin().get(i);
		}
		dbDate = dbDate.substring(0, 4) + dbDate.substring(5, 7) + dbDate.substring(8, 10);
		locadInteger = Integer.parseInt(localDateSub);
		dbInteger = Integer.parseInt(dbDate);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin();
	}
}
