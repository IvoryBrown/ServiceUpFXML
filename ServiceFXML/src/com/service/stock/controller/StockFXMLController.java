package com.service.stock.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.service.client.ClientFXMLController;
import com.service.setting.database.DataBaseConnect;
import com.service.stock.Stock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class StockFXMLController extends ClientFXMLController implements Initializable {

	@SuppressWarnings("rawtypes")
	@FXML
	private TableView stockTable;
	@FXML
	private DatePicker stockDate;
	@FXML
	private Button stockDeviceFilteringBtn;
	@FXML
	private TextField stockDeviceNameFilteringTxt;

	private final ObservableList<Stock> data = FXCollections.observableArrayList();

	public ArrayList<Stock> getAllStock() {
		// String sql = "SELECT * FROM `raktar`";
		String sql = "SELECT * FROM `raktar` WHERE CONCAT (`"
				+ "eszkoznev" + "`) LIKE '%"
				+ stockDeviceNameFilteringTxt.getText() + "%'";
		ArrayList<Stock> device = null;

		Statement createStatement = null;
		ResultSet rs = null;
		Connection con = DataBaseConnect.getConnection();
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			device = new ArrayList<>();
			while (rs.next()) {
				Stock actualStock = new Stock(rs.getInt("category_id"), rs.getString("eszkoznev"),
						rs.getString("kelte"), rs.getString("eladas_kelte"), rs.getInt("mennyiseg"),
						rs.getString("leiras"));
				device.add(actualStock);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (createStatement != null) {
					createStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
		return device;

	}

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
		stockDeviceDate.setMinWidth(80);
		stockDeviceDate.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceDate.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceDate"));

		TableColumn stockDeviceSalesDate = new TableColumn("Eladás");
		stockDeviceSalesDate.setMinWidth(80);
		stockDeviceSalesDate.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceSalesDate.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceSalesDate"));

		TableColumn stockDeviceQuantity = new TableColumn("Darab");
		stockDeviceQuantity.setMinWidth(80);
		stockDeviceQuantity.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceQuantity.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceQuantity"));

		TableColumn stockDeviceDescription = new TableColumn("Leírás");
		stockDeviceDescription.setMinWidth(750);
		stockDeviceDescription.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceDescription.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceDescription"));

		stockTable.getColumns().addAll(stockDeviceId, stockDeviceName, stockDeviceDate, stockDeviceSalesDate,
				stockDeviceQuantity, stockDeviceDescription);
		data.addAll(getAllStock());
		stockTable.setItems(data);

	}

	@FXML
	private void searchAdd(ActionEvent event) {
		data.clear();
		setStockTableData();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComponentAll();
		setStockTableData();
	}

}
