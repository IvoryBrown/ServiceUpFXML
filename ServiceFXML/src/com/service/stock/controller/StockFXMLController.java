package com.service.stock.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.client.ClientFXMLController;
import com.service.stock.Stock;
import com.service.stock.db.StockDataBase;

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
	private final ObservableList<Stock> data = FXCollections.observableArrayList();
	StockDataBase sDataBase = new StockDataBase();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void setStockTableData() {

		TableColumn stockDeviceId = new TableColumn("ID");
		stockDeviceId.setMinWidth(50);
		stockDeviceId.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceId.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceId"));

		TableColumn stockDeviceName = new TableColumn("Termék");
		stockDeviceName.setMinWidth(250);
		stockDeviceName.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceName.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceName"));

		TableColumn stockDeviceDate = new TableColumn("Kelte");
		stockDeviceDate.setMinWidth(250);
		stockDeviceDate.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceDate.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceDate"));

		TableColumn stockDeviceSalesDate = new TableColumn("Eladás");
		stockDeviceSalesDate.setMinWidth(250);
		stockDeviceSalesDate.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceSalesDate.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceSalesDate"));

		TableColumn stockDeviceQuantity = new TableColumn("Mennyiség");
		stockDeviceQuantity.setMinWidth(150);
		stockDeviceQuantity.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceQuantity.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceQuantity"));

		TableColumn stockDeviceDescription = new TableColumn("Leírás");
		stockDeviceDescription.setMinWidth(750);
		stockDeviceDescription.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceDescription.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceDescription"));

		stockTable.getColumns().addAll(stockDeviceId, stockDeviceName, stockDeviceDate, stockDeviceSalesDate,
				stockDeviceQuantity, stockDeviceDescription);
		data.addAll(sDataBase.getAllStock());
		stockTable.setItems(data);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComponentAll();
		setStockTableData();
	}

}
