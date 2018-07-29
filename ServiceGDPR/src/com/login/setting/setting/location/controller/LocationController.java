package com.login.setting.setting.location.controller;

import com.login.setting.setting.location.database.LocationDataBase;
import com.login.setting.setting.location.pojo.Location;
import com.login.setting.setting.operatingsystem.controller.OperatingSystemController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class LocationController extends OperatingSystemController{
	@FXML
	private TableView<Location> tableLocationSetting;
	private TableColumn<Location, Integer> locationId;
	private TableColumn<Location, String> location, removeColLocation;
	@FXML
	private TextField locationSettingTxt;
	@FXML
	private ComboBox<String> locationSettingCmb;
	private final ObservableList<Location> dataLocation = FXCollections.observableArrayList();

	@FXML
	private void locationSave() {
		if (checkLocationTxField()) {
			Location location = new Location(locationSettingTxt.getText());
			LocationDataBase.addLocation(location);
			messageLbl.setStyle("-fx-text-fill: #2A5058;");
			messageLbl.setText("Sikeres beállítás!!");
			setLocationTableData();
			setLocationClear();
		}
	}

	@SuppressWarnings("unchecked")
	protected void setLocationTableData() {
		tableLocationSetting.getColumns().clear();
		dataLocation.clear();
		locationId = new TableColumn<>("ID");
		locationId.setMinWidth(70);
		locationId.setCellValueFactory(new PropertyValueFactory<Location, Integer>("locationId"));

		location = new TableColumn<>("Helyszín");
		location.setMinWidth(350);
		location.setCellValueFactory(new PropertyValueFactory<Location, String>("location"));
		location.setCellFactory(TextFieldTableCell.forTableColumn());
		location.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Location, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Location, String> t) {
				Location actualDeviceName = (Location) t.getTableView().getItems().get(t.getTablePosition().getRow());
				if (t.getNewValue().length() > 2) {
					actualDeviceName.setLocation(t.getNewValue());
					LocationDataBase.updateLocation(actualDeviceName);
					messageLbl.setStyle("-fx-text-fill: #2A5058;");
					messageLbl.setText("Sikeres beállítás!!");
					setLocationTableData();
				} else {
					messageLbl.setStyle("-fx-text-fill: red;");
					messageLbl.setText("Sikertelen beállítás!! Min. 3 karakter!");
					setLocationTableData();
				}
			}
		});

		removeColLocation = new TableColumn<>("Törlés");
		removeColLocation.setMinWidth(100);
		Callback<TableColumn<Location, String>, TableCell<Location, String>> cellFactory = new Callback<TableColumn<Location, String>, TableCell<Location, String>>() {
			@Override
			public TableCell<Location, String> call(final TableColumn<Location, String> param) {
				final TableCell<Location, String> cell = new TableCell<Location, String>() {
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
								Location device = getTableView().getItems().get(getIndex());
								dataLocation.remove(device);
								LocationDataBase.removeLocation(device);
								messageLbl.setStyle("-fx-text-fill: #2A5058;");
								messageLbl.setText("Sikeres törlés!!");
								setLocationTableData();
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		removeColLocation.setCellFactory(cellFactory);

		tableLocationSetting.setItems(dataLocation);
		tableLocationSetting.getColumns().addAll(locationId, location, removeColLocation);
		dataLocation.addAll(LocationDataBase.getAllLocationDataBase());
		setLoctionCombobox();
	}
	
	private void setLoctionCombobox() {
		locationSettingCmb.getItems().clear();
		locationSettingCmb.getItems().addAll(LocationDataBase.locationListComboBox);
		LocationDataBase.locationListComboBox.clear();

	}

	private boolean checkLocationTxField() {
		if (locationSettingTxt.getText().trim().isEmpty()) {
			locationSettingTxt.setStyle("-fx-prompt-text-fill: #CC0033");
			messageLbl.setStyle("-fx-text-fill: red;");
			messageLbl.setText("Sikertelen beállítás!! Üresek a mezők!!");
			return false;
		} else {
			locationSettingTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
			return true;
		}
	}

	private void setLocationClear() {
		locationSettingTxt.clear();
	}
}
