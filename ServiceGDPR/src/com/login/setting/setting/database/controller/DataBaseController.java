package com.login.setting.setting.database.controller;

import com.login.setting.setting.controller.SettingController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DataBaseController extends SettingController{
	@FXML
	private TextField urlTxt, nameTxt, passwordTxt;
	@FXML
	protected Label messageLbl;
	
	// SettingDBFile
	protected void setDBtextField() {
		SettingDBFile.setDataBaseOutput();
		urlTxt.setText(SettingDBFile.getDBOutput());
		nameTxt.setText(SettingDBFile.getNameOutput());
		passwordTxt.setText(SettingDBFile.getPasswordOutput());
	}
	@FXML
	private void saveDBButton() {
		if (urlTxt.getText().trim().isEmpty() || nameTxt.getText().trim().isEmpty()) {
			messageLbl.setStyle("-fx-text-fill: red;");
			messageLbl.setText("Sikertelen beállítás!!");
		} else {
			SettingDBFile.writeDB(urlTxt.getText(), nameTxt.getText(), passwordTxt.getText());
			messageLbl.setStyle("-fx-text-fill: #2A5058;");
			messageLbl.setText("Sikeres beállítás!!");
			setDBtextField();
		}
	}
	
}
