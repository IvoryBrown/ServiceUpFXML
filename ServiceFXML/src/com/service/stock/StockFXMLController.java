package com.service.stock;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.client.ClientFXMLController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class StockFXMLController extends ClientFXMLController implements Initializable {
	@SuppressWarnings("rawtypes")
	@FXML
	private TableView stockTable;
	private final ObservableList<Stock> data = FXCollections.observableArrayList(
			);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void setStockTableData() {
		TableColumn deviceId = new TableColumn("ID");
		deviceId.setMinWidth(50);
		deviceId.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceId.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceId"));

		TableColumn deviceName = new TableColumn("Termék");
		deviceName.setMinWidth(250);
		deviceName.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceName.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceName"));

		TableColumn deviceQuantity = new TableColumn("Mennyiség");
		deviceQuantity.setMinWidth(150);
		deviceQuantity.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceQuantity.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceQuantity"));

		TableColumn deviceDescription = new TableColumn("Leírás");
		deviceDescription.setMinWidth(750);
		deviceDescription.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceDescription.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceDescription"));

		stockTable.getColumns().addAll(deviceId, deviceName, deviceQuantity, deviceDescription);
		stockTable.setItems(data);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComponentAll();
		setStockTableData();
	}

}
