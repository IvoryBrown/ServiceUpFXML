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
	@FXML
	private TableView stockTable;
	private final ObservableList<Stock> data = FXCollections.observableArrayList(
			
			);
	

	protected void setStockTableData() {
		TableColumn deviceId = new TableColumn("ID");
		deviceId.setMinWidth(50);
		deviceId.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceId.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockId"));
		stockTable.getColumns().addAll(deviceId);
		stockTable.setItems(data);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComponentAll();
		setStockTableData();
	}

}
