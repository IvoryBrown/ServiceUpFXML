package com.service.client.table;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.client.Client;
import com.service.stock.controller.StockFXMLController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClientTable extends StockFXMLController implements Initializable {
	@FXML
	private TableView<Client> clientTable;
	
	private TableColumn<Client, Integer> stockDeviceId, stockDeviceQuantity;
	
	protected void setClientTableData() {
	

	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComponentAll();
		setStockTableData();
		setClientTableData();
	}
}
