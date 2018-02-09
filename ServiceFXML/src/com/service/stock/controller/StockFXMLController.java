package com.service.stock.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.client.ClientFXMLController;
import com.service.stock.Stock;
import com.service.stock.filteringdb.StockFillteringDB;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class StockFXMLController extends ClientFXMLController implements Initializable {

	@FXML
	private TableView<Stock> stockTable;
	@FXML
	private TextField stockDeviceNameFilteringTxt;

	private final ObservableList<Stock> data = FXCollections.observableArrayList();
	StockFillteringDB db = new StockFillteringDB();

	public void setStockTableData() {
		TableColumn<Stock, Integer> stockDeviceId = new TableColumn<>("ID");
		stockDeviceId.setMinWidth(50);
		stockDeviceId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		stockDeviceId.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("stockDeviceId"));

		TableColumn<Stock, String> stockDeviceName = new TableColumn<>("Termék");
		stockDeviceName.setMinWidth(250);
		stockDeviceName.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceName.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceName"));
		stockDeviceName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stock, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Stock, String> d) {
				Stock actualStock = (Stock) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualStock.setStockDeviceName(d.getNewValue());
				db.updateStock(actualStock);
				System.out.println("ok");
			}
		});

		TableColumn<Stock, String> stockDeviceDate = new TableColumn<>("Kelte");
		stockDeviceDate.setMinWidth(80);
		stockDeviceDate.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceDate.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceDate"));

		TableColumn<Stock, String> stockDeviceSalesDate = new TableColumn<>("Eladás");
		stockDeviceSalesDate.setMinWidth(80);
		stockDeviceSalesDate.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceSalesDate.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceSalesDate"));
		stockDeviceSalesDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stock, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Stock, String> d) {
				Stock actualStock = (Stock) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualStock.setStockDeviceSalesDate(d.getNewValue());
				db.updateStock(actualStock);
				System.out.println("ok");
			}
		});

		TableColumn<Stock, String> stockDeviceQuantity = new TableColumn<>("Darab");
		stockDeviceQuantity.setMinWidth(80);
		stockDeviceQuantity.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceQuantity.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceQuantity"));
		stockDeviceQuantity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stock, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Stock, String> d) {
				Stock actualStock = (Stock) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualStock.setStockDeviceQuantity(d.getNewValue());
				db.updateStock(actualStock);
				System.out.println("ok");
			}
		});

		TableColumn<Stock, String> stockDeviceDescription = new TableColumn<>("Leírás");
		stockDeviceDescription.setMinWidth(750);
		stockDeviceDescription.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceDescription.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceDescription"));

		stockTable.setItems(data);
		stockTable.getColumns().addAll(stockDeviceId, stockDeviceName, stockDeviceDate, stockDeviceSalesDate,
				stockDeviceQuantity, stockDeviceDescription);
		data.addAll(StockFillteringDB.getAllStock());

	}

	@FXML
	private void filteringBtn(ActionEvent event) {
		if (stockDeviceNameFilteringTxt.getText() != null) {
			data.clear();
			data.addAll(StockFillteringDB.getStockNameFiltering(stockDeviceNameFilteringTxt.getText()));
		} else {
			data.clear();
			data.addAll(StockFillteringDB.getAllStock());
		}

	}

	@FXML
	private void filteringTxt(ActionEvent event) {

	}

	@FXML
	private void newDevieceBtn(ActionEvent event) {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/com/service/setting/fxmlnewstock/FxmlNewStock.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Eszköz");
			// stage.initStyle(StageStyle.TRANSPARENT);
			stage.setScene(new Scene(root, 1000, 650));
			stage.show();
			// stockPane.setOpacity(0.1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComponentAll();
		setStockTableData();
	}

}
