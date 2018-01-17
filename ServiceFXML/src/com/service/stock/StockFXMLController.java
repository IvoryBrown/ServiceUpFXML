package com.service.stock;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.client.ClientFXMLController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class StockFXMLController extends ClientFXMLController implements Initializable {
	@FXML
	private TableView stockTable;

	protected void setStockTableData() {

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComponentAll();
		setStockTableData();
	}

}
