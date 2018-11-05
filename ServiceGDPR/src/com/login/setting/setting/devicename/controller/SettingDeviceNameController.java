package com.login.setting.setting.devicename.controller;

import java.util.Optional;

import com.login.setting.setting.devicename.database.DeviceNameDataBase;
import com.login.setting.setting.devicename.pojo.DeviceName;
import com.login.setting.setting.location.controller.LocationController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class SettingDeviceNameController extends LocationController {
	@FXML
	private TableView<DeviceName> tableDeviceSetting;
	private TableColumn<DeviceName, Integer> deviceNameId;
	private TableColumn<DeviceName, String> deviceName, removeColDeviceName;
	@FXML
	private TextField deviceNameSettingTxt;
	@FXML
	private ComboBox<String> deviceNameSettingCmb;
	private final ObservableList<DeviceName> dataDeviceName = FXCollections.observableArrayList();

	@FXML
	private void deviceNameSave() {
		if (checkDeviceNameTxField()) {
			DeviceName deviceName = new DeviceName(deviceNameSettingTxt.getText());
			DeviceNameDataBase.addDeviceName(deviceName);
			messageLbl.setStyle("-fx-text-fill: #2A5058;");
			messageLbl.setText("Sikeres beállítás!!");
			setDeviceNameTableData();
			setDeviceNameClear();
		}
	}

	@SuppressWarnings("unchecked")
	protected void setDeviceNameTableData() {
		tableDeviceSetting.getColumns().clear();
		dataDeviceName.clear();
		deviceNameId = new TableColumn<>("ID");
		deviceNameId.setMinWidth(70);
		deviceNameId.setCellValueFactory(new PropertyValueFactory<DeviceName, Integer>("deviceNameId"));

		deviceName = new TableColumn<>("Név");
		deviceName.setMinWidth(350);
		deviceName.setCellValueFactory(new PropertyValueFactory<DeviceName, String>("deviceName"));
		deviceName.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<DeviceName, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<DeviceName, String> t) {
				DeviceName actualDeviceName = (DeviceName) t.getTableView().getItems()
						.get(t.getTablePosition().getRow());
				if (t.getNewValue().length() > 2) {
					actualDeviceName.setDeviceName(t.getNewValue());
					DeviceNameDataBase.updateDeviceName(actualDeviceName);
					messageLbl.setStyle("-fx-text-fill: #2A5058;");
					messageLbl.setText("Sikeres beállítás!!");
					setDeviceNameTableData();
				} else {
					messageLbl.setStyle("-fx-text-fill: red;");
					messageLbl.setText("Sikertelen beállítás!! Min. 3 karakter!");
					setLocationTableData();
				}
			}
		});

		removeColDeviceName = new TableColumn<>("Törlés");
		removeColDeviceName.setMinWidth(100);
		Callback<TableColumn<DeviceName, String>, TableCell<DeviceName, String>> cellFactory = new Callback<TableColumn<DeviceName, String>, TableCell<DeviceName, String>>() {
			@Override
			public TableCell<DeviceName, String> call(final TableColumn<DeviceName, String> param) {
				final TableCell<DeviceName, String> cell = new TableCell<DeviceName, String>() {
					final Button btn = new Button("Törlés");

					@Override
					public void updateItem(String item, boolean empty) {
						btn.setPrefWidth(90);
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setOnAction((ActionEvent event) -> {
								Alert alert = new Alert(AlertType.CONFIRMATION);
								alert.setTitle("Törlés");
								alert.setHeaderText("");
								alert.getDialogPane().getStylesheets().add("/com/setting/showinfo/ShowInfo.css");
								alert.initStyle(StageStyle.TRANSPARENT);
								String s = "Biztos törölni szeretnéd ?";
								alert.setContentText(s);
								Optional<ButtonType> result = alert.showAndWait();
								if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
									DeviceName device = getTableView().getItems().get(getIndex());
									dataDeviceName.remove(device);
									DeviceNameDataBase.removeDeviceName(device);
									messageLbl.setStyle("-fx-text-fill: #2A5058;");
									messageLbl.setText("Sikeres törlés!!");
									setDeviceNameTableData();
								}
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		removeColDeviceName.setCellFactory(cellFactory);

		tableDeviceSetting.setItems(dataDeviceName);
		tableDeviceSetting.getColumns().addAll(deviceNameId, deviceName, removeColDeviceName);
		dataDeviceName.addAll(DeviceNameDataBase.getAllDeviceNameDataBase());
		setDeviceNameCombobox();

	}

	private void setDeviceNameCombobox() {
		deviceNameSettingCmb.getItems().clear();
		deviceNameSettingCmb.getItems().addAll(DeviceNameDataBase.deviceNameList);
		DeviceNameDataBase.deviceNameList.clear();

	}

	private boolean checkDeviceNameTxField() {
		if (deviceNameSettingTxt.getText().trim().isEmpty()) {
			deviceNameSettingTxt.setStyle("-fx-prompt-text-fill: #CC0033");
			messageLbl.setStyle("-fx-text-fill: red;");
			messageLbl.setText("Sikertelen beállítás!! Üresek a mezők!!");
			return false;
		} else {
			deviceNameSettingTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
		}
		return true;
	}

	private void setDeviceNameClear() {
		deviceNameSettingTxt.clear();
	}
}
