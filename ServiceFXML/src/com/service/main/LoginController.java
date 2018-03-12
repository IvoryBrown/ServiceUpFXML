package com.service.main;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	public static  String adminLogin = "123admin123";
	private  String userLogin = "kisker";
	public static String admin ;
	@FXML
	private PasswordField loginText;
	@FXML
	private Label errorLb;

	@FXML
	private void btnLogin() {
		if (loginText.getText().equals(adminLogin) || loginText.getText().equals(userLogin)) {
			admin = loginText.getText();
			
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/com/service/setting/fxmlsetting/ServiceFX.fxml"));
				Stage stage = new Stage();
				stage.setTitle("Bevételezve");
				stage.setScene(new Scene(root, 1300, 650));
				stage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} else {
			errorLb.setText("Nem megfelelő jelszó!");
		}
	}

	@FXML
	private void btnExit() {
		System.exit(0);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Automatikusan előállított metóduscsonk

	}

}
