package com.service.main;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.service.device.fillteringdb.DeviceFillteringDB;
import com.service.setting.combobox.Combobox;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {
	public static String userLogin = "123kisker45";
	public static String adminLogin = "123admin123";
	public static String serviceLogin = "szerviz45";
	public static String exicomUserLogin = "userexi";
	public static String exicomServiceLogin = "exicszerviz";
	public static String admin;
	@FXML
	private PasswordField loginText;
	@FXML
	private Label errorLb;
	@FXML
	private ComboBox<String> loginService;
	public static String setLogin = null;
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
				if (loginService.getValue() != null) {
					if (loginText.getText().equals(adminLogin) || loginText.getText().equals(userLogin)
							|| loginText.getText().equals(serviceLogin)) {
						if (loginService.getValue().equals("Irisz")) {
							setLogin = null;
							setLogin = loginService.getSelectionModel().getSelectedItem();
							admin = loginText.getText();
							getParent();
						} else {
							errorLb.setText("Nincs hozzáférésed");
						}
					} else {
						errorLb.setText("Nem megfelelő jelszó!");
					}
					if (loginText.getText().equals(exicomUserLogin) || loginText.getText().equals(exicomServiceLogin)
							|| loginText.getText().equals(adminLogin)) {
						if (loginService.getValue().equals("Exicom")) {
							setLogin = null;
							setLogin = loginService.getSelectionModel().getSelectedItem();
							admin = loginText.getText();
							getParent();
						} else {
							errorLb.setText("Nincs hozzáférésed");
							return;
						}
					} else {
						errorLb.setText("Nem megfelelő jelszó!");
						return;
					}
				} else {
					errorLb.setText("Adatbázist válasszál!");
					return;
				}
			}
		} else {
			errorLb.setText("Hozzáférése lejárt");
		}
	}

	private void getParent() {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/com/service/setting/fxmlsetting/LoadApplication.fxml"));
			Stage stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			stage.setWidth(500);
			stage.setHeight(368);
			stage.setScene(scene);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/service/setting/desing/icon-it.png")));
			stage.show();
			Main.primaryStage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void btnExit() {
		System.exit(0);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin();
		loginService.getItems().addAll(Combobox.setLoginCombobox());

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
