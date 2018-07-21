package com.login.setting.setting.operatingsystem.controller;

import com.login.setting.setting.operatingsystem.database.OperatingSystemDataBase;
import com.login.setting.setting.operatingsystem.pojo.OperatingSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class OperatingSystemController {
	@FXML
	protected Label messageLbl;
	@FXML
	private TableView<OperatingSystem> tableOperatingSystemSetting;
	private TableColumn<OperatingSystem, Integer> operatingSystemId;
	private TableColumn<OperatingSystem, String> operatingSystem, removeColOperatingSystem;
	@FXML
	private TextField operatingSystemSettingTxt;
	@FXML
	private ComboBox<String> operatingSystemSettingCmb;
	private final ObservableList<OperatingSystem> dataOperationSystem = FXCollections.observableArrayList();

	@FXML
	private void operatingSystemSave() {
		if (checkOperatingSystemTxField()) {
			OperatingSystem operatingSystem = new OperatingSystem(operatingSystemSettingTxt.getText());
			OperatingSystemDataBase.addOperatingSystem(operatingSystem);
			messageLbl.setStyle("-fx-text-fill: #2A5058;");
			messageLbl.setText("Sikeres beállítás!!");
			setOperatingSystemTableData();
			setOperatingSystemClear();
		}
	}

	@SuppressWarnings("unchecked")
	protected void setOperatingSystemTableData() {
		tableOperatingSystemSetting.getColumns().clear();
		dataOperationSystem.clear();
		operatingSystemId = new TableColumn<>("ID");
		operatingSystemId.setMinWidth(70);
		operatingSystemId.setCellValueFactory(new PropertyValueFactory<OperatingSystem, Integer>("operatingSystemId"));

		operatingSystem = new TableColumn<>("Operációs Rendszer");
		operatingSystem.setMinWidth(550);
		operatingSystem.setCellValueFactory(new PropertyValueFactory<OperatingSystem, String>("operatingSystem"));
		operatingSystem.setCellFactory(TextFieldTableCell.forTableColumn());
		operatingSystem.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<OperatingSystem, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<OperatingSystem, String> t) {
				OperatingSystem actualDeviceName = (OperatingSystem) t.getTableView().getItems()
						.get(t.getTablePosition().getRow());
				if (t.getNewValue().length() > 2) {
					actualDeviceName.setOperatingSystem(t.getNewValue());
					OperatingSystemDataBase.updateOperatingSystem(actualDeviceName);
					messageLbl.setStyle("-fx-text-fill: #2A5058;");
					messageLbl.setText("Sikeres beállítás!!");
					setOperatingSystemTableData();
				} else {
					messageLbl.setStyle("-fx-text-fill: red;");
					messageLbl.setText("Sikertelen beállítás!! Min. 3 karakter!");
					setOperatingSystemTableData();
				}
			}
		});

		removeColOperatingSystem = new TableColumn<>("Törlés");
		removeColOperatingSystem.setMinWidth(100);
		Callback<TableColumn<OperatingSystem, String>, TableCell<OperatingSystem, String>> cellFactory = new Callback<TableColumn<OperatingSystem, String>, TableCell<OperatingSystem, String>>() {
			@Override
			public TableCell<OperatingSystem, String> call(final TableColumn<OperatingSystem, String> param) {
				final TableCell<OperatingSystem, String> cell = new TableCell<OperatingSystem, String>() {
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
								OperatingSystem device = getTableView().getItems().get(getIndex());
								dataOperationSystem.remove(device);
								OperatingSystemDataBase.removeOperatingSystem(device);
								messageLbl.setStyle("-fx-text-fill: #2A5058;");
								messageLbl.setText("Sikeres törlés!!");
								setOperatingSystemTableData();
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		removeColOperatingSystem.setCellFactory(cellFactory);

		tableOperatingSystemSetting.setItems(dataOperationSystem);
		tableOperatingSystemSetting.getColumns().addAll(operatingSystemId, operatingSystem, removeColOperatingSystem);
		dataOperationSystem.addAll(OperatingSystemDataBase.getAllOperatingSystem());
		setOperatingSystemCombobox();
	}

	private void setOperatingSystemCombobox() {
		operatingSystemSettingCmb.getItems().clear();
		operatingSystemSettingCmb.getItems().addAll(OperatingSystemDataBase.operatingSystemListComboBox);
		OperatingSystemDataBase.operatingSystemListComboBox.clear();

	}

	private boolean checkOperatingSystemTxField() {
		if (operatingSystemSettingTxt.getText().trim().isEmpty()) {
			operatingSystemSettingTxt.setStyle("-fx-prompt-text-fill: #CC0033");
			messageLbl.setStyle("-fx-text-fill: red;");
			messageLbl.setText("Sikertelen beállítás!! Üresek a mezők!!");
			return false;
		} else {
			operatingSystemSettingTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
		}
		return true;
	}

	private void setOperatingSystemClear() {
		operatingSystemSettingTxt.clear();
	}

}
