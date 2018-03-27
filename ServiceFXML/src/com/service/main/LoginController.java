package com.service.main;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.service.device.fillteringdb.DeviceFillteringDB;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {
	private String userLogin = "kisker";
	public static String adminLogin = "123admin123";
	public static String serviceLogin = "szerviz";
	public static String admin;
	@FXML
	private PasswordField loginText;
	@FXML
	private Label errorLb;
	private DeviceFillteringDB deviceDb = new DeviceFillteringDB();
	private LocalDate localDate;
	private String localDateSub;
	private String dbDate;
	private Integer locadInteger;
	private Integer dbInteger; 

	private void dateLinc() {
		localDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedString = localDate.format(formatter);
		localDateSub = formattedString.substring(0, 4) + formattedString.substring(5, 7)
				+ formattedString.substring(8, 10);

		for (int i = 0; i < deviceDb.dateLincList.size(); i++) {
			dbDate = deviceDb.dateLincList.get(i);
		}
		dbDate = dbDate.substring(0, 4) + dbDate.substring(5, 7) + dbDate.substring(8, 10);
		locadInteger = Integer.parseInt(localDateSub);
		dbInteger = Integer.parseInt(dbDate);
	}

	@FXML
	private void btnLogin() {
		dateLinc();
		if (locadInteger < dbInteger) {
			if (!loginText.getText().trim().isEmpty()) {
				if (loginText.getText().equals(adminLogin) || loginText.getText().equals(userLogin)
						|| loginText.getText().equals(serviceLogin)) {
					admin = loginText.getText();
					try {
						Parent root = FXMLLoader
								.load(getClass().getResource("/com/service/setting/fxmlsetting/LoadApplication.fxml"));
						Stage stage = new Stage();
						stage.setTitle("PcVipService");
						stage.initStyle(StageStyle.TRANSPARENT);
						stage.setScene(new Scene(root, 500, 368));
						stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/service/setting/desing/icon-it.png")));
						stage.show();
						Main.primaryStage.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				
				} else {
					errorLb.setText("Nem megfelelő jelszó!");
				}
			}
		} else {
			errorLb.setText("Hozzáférése lejárt");
		}
	}

	@FXML
	private void btnExit() {
		System.exit(0);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin();
		loginText.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					btnLogin();
				}
			}
		});
	}

}
