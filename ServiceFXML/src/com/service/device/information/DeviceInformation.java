package com.service.device.information;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.service.device.DeviceInfo;
import com.service.device.fillteringdb.DeviceInfoFillteringDB;
import com.service.main.LoginController;
import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DeviceInformation {
	@FXML
	private WebView webView = new WebView();
	@FXML
	private TextField txtDeviceNumber;
	@FXML
	private TableView<DeviceInfo> deviceTableInfo;
	@FXML
	private Label fileText;
	private TableColumn<DeviceInfo, Integer> deviceTableId, deviceTableNumber;
	private TableColumn<DeviceInfo, String> removeCol;
	private FileChooser fileChooser;
	private File file;
	private WebEngine engine;
	private String HTMPath;
	private TrayNotification tray;
	private final ObservableList<DeviceInfo> dataDevice = FXCollections.observableArrayList();
	private DeviceInfoFillteringDB deviceDb = new DeviceInfoFillteringDB();
	private LoginController login = new LoginController();

	@SuppressWarnings("unchecked")
	@FXML
	public void initialize() {
		deviceTableId = new TableColumn<>("ID");
		deviceTableId.setMinWidth(50);
		deviceTableId.setCellValueFactory(new PropertyValueFactory<DeviceInfo, Integer>("deviceInfoID"));

		deviceTableNumber = new TableColumn<>("Azonosító");
		deviceTableNumber.setMinWidth(50);
		deviceTableNumber.setCellValueFactory(new PropertyValueFactory<DeviceInfo, Integer>("deviceNumber"));

		removeCol = new TableColumn<>("-");
		removeCol.setMinWidth(10);
		Callback<TableColumn<DeviceInfo, String>, TableCell<DeviceInfo, String>> cellFactory = new Callback<TableColumn<DeviceInfo, String>, TableCell<DeviceInfo, String>>() {
			@Override
			public TableCell<DeviceInfo, String> call(final TableColumn<DeviceInfo, String> param) {
				final TableCell<DeviceInfo, String> cell = new TableCell<DeviceInfo, String>() {
					final Button btn = new Button("-");

					@Override
					public void updateItem(String item, boolean empty) {

						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setOnAction((ActionEvent event) -> {
								DeviceInfo deviceInfo = getTableView().getItems().get(getIndex());
								dataDevice.remove(deviceInfo);
								deviceDb.removeContact(deviceInfo);
								tray = new TrayNotification("Törlés!", "Sikeres Törlés", NotificationType.SUCCESS);
								tray.showAndDismiss(Duration.seconds(1));
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		removeCol.setCellFactory(cellFactory);

		deviceTableInfo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DeviceInfo>() {
			@Override
			public void changed(ObservableValue<? extends DeviceInfo> observable, DeviceInfo oldValue,
					DeviceInfo newValue) {
				engine = webView.getEngine();
				engine.loadContent(resizeHtm(newValue.getHtml()));
			}
		});

		deviceTableInfo.setItems(dataDevice);
		deviceTableInfo.getColumns().addAll(deviceTableId, deviceTableNumber, removeCol);
		dataDevice.addAll(DeviceInfoFillteringDB.getAllDeviceCompanyFiltering());
	}

	private String resizeHtm(Blob s) {
		String g = null;
		try {
			byte[] bdata = s.getBytes(1, (int) s.length());
			g = new String(bdata, "iso-8859-1");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return g;
	}

	@FXML
	private void fileOpen() {
		fileText.setStyle("   -fx-font-size: 12.0;");
		fileChooser = new FileChooser();
		fileChooser.setTitle("Report beillesztés");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("HTM (*.htm)", "*.htm");
		fileChooser.getExtensionFilters().add(extFilter);
		file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			browers(file);
			HTMPath = file.getAbsolutePath();
			fileText.setText(file.getAbsolutePath());
		}
	}

	private void browers(File file) {
		engine = webView.getEngine();
		try {
			engine.load(file.toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private boolean check() {
		if (txtDeviceNumber.getText().trim().isEmpty() || file == null) {
			txtDeviceNumber.setStyle("-fx-prompt-text-fill: #CC0033");
			return false;
		} else {
			txtDeviceNumber.setStyle("-fx-prompt-text-fill: #61a2b1");
			return true;
		}
	}
	
	private void clearText() {
		txtDeviceNumber.clear();
		fileText.setText(null);
	}

	@FXML
	private void buttonDatabese() {
		if (check()) {
			try {
				Connection con = DataBaseConnect.getConnection();
				PreparedStatement insertDeviceInf = con
						.prepareStatement("INSERT INTO gepadatok_informacio(eszkoz_az, gep_info)" + "values(?,?) ");

				insertDeviceInf.setString(1, txtDeviceNumber.getText());
				InputStream htm = new FileInputStream(new File(HTMPath));
				System.out.println(HTMPath);
				insertDeviceInf.setBlob(2, htm);
				insertDeviceInf.executeUpdate();
				htm.close();
				clearText();
				tray = new TrayNotification("Remek!", "Sikeres Felvétel", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			} catch (SQLException e) {
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				ShowInfo.errorInfoMessengeException("Fájl Hiba", "Szerver válasza: ", e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				ShowInfo.errorInfoMessengeException("Fájl Hiba", "Szerver válasza: ", e.getMessage());
				e.printStackTrace();
			}
		} else {
			tray = new TrayNotification("HIBA", "Nincs minden mező kitöltve", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}
	}
}
