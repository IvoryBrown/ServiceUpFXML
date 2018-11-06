package com.login.controller;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.log.filewriter.FileWriterLog;
import com.login.database.LoginDataBase;
import com.login.filewrite.LoginFile;
import com.login.main.LoginMain;
import com.login.setting.setting.main.SettingMain;
import com.main.normalsize.Main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginController implements Initializable {
	private LocalDate localDate;
	private String localDateSumma;
	private Integer locadInteger;
	private Integer dbInteger;
	private String dbDate;
	@FXML
	private Label errorLb;
	@FXML
	private TextField userTxt;
	@FXML
	private PasswordField passwordTxt;
	@FXML
	private CheckBox saveChBox;

	private void getSaveUserName() {
		saveChBox.setVisible(false);
		userTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (newPropertyValue) {
					saveChBox.setVisible(true);

				} else {
					saveChBox.setVisible(false);
				}
			}
		});

	}

	private void setSaveUserName() {
		if (saveChBox.isSelected() != false) {
			LoginFile.writeDB(userTxt.getText().toString(), saveChBox.isSelected());
		} else {
			LoginFile.writeDB(null, saveChBox.isSelected());
		}

	}

	private boolean checkUserPassword() {
		boolean success = false;
		for (int i = 0; i < LoginDataBase.user.size(); i++) {
			for (int j = 0; j < LoginDataBase.password.size(); j++) {
				if (userTxt.getText().equals(LoginDataBase.user.get(i))
						&& passwordTxt.getText().equals(LoginDataBase.password.get(j))) {
					success = true;
				}
			}
		}
		return success;
	}

	@FXML
	private void btnLogin() {
		login();
	}

	private void login() {
		dateLinc();
		if (locadInteger < dbInteger) {
			if (checkLoginTxtField()) {
				LoginDataBase.getGetLogin(userTxt.getText());
				if (checkUserPassword()) {
					setSaveUserName();
					Main main = new Main();
					main.startEnd();
					LoginMain.primaryStageLoginMain.close();
					new FileWriterLog(LoginDataBase.name + " Belépet");
				} else {
					errorLb.setText("Ismeretlen User&Password!!");
				}
			}
		} else {
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
		localDateSumma = formattedString.substring(0, 4) + formattedString.substring(5, 7)
				+ formattedString.substring(8, 10);
		for (int i = 0; i < LoginDataBase.setGetDateLin().size(); i++) {
			dbDate = LoginDataBase.setGetDateLin().get(i);
		}
		dbDate = dbDate.substring(0, 4) + dbDate.substring(5, 7) + dbDate.substring(8, 10);
		locadInteger = Integer.parseInt(localDateSumma);
		dbInteger = Integer.parseInt(dbDate);
	}

	private boolean checkLoginTxtField() {
		if (userTxt.getText().trim().isEmpty() || passwordTxt.getText().trim().isEmpty()) {
			userTxt.setStyle("-fx-prompt-text-fill: #CC0033");
			passwordTxt.setStyle("-fx-prompt-text-fill: #CC0033");
			errorLb.setStyle("-fx-text-fill: red;");
			errorLb.setText("Sikertelen bejelenkezés!!");
			return false;
		} else {
			userTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
			passwordTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
			return true;
		}
	}

	private void checkFloder() {
		new File(System.getProperty("user.home") + "\\PcVipService\\Name").mkdirs();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		checkFloder();
		getSaveUserName();
		LoginFile.setDataBaseOutput();
		userTxt.setText(LoginFile.userName);
		if (LoginFile.booleanChBox.equals("true")) {
			saveChBox.setSelected(true);
		} else {
			userTxt.clear();
		}
		passwordTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					login();
				}
			}
		});
		userTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					login();
				}
			}
		});
	}
}
