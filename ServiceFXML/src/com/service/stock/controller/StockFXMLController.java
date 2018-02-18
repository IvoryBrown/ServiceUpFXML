package com.service.stock.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.service.client.ClientFXMLController;
import com.service.setting.showinfo.ShowInfo;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

public class StockFXMLController extends ClientFXMLController implements Initializable {

	@FXML
	private TableView<Stock> stockTable;
	@FXML
	private TextField stockDeviceNameFilteringTxt;
	TableColumn<Stock, Integer> stockDeviceId, stockDeviceQuantity;
	TableColumn<Stock, String> stockDeviceName, stockDeviceDate, stockDeviceDescription, stockDeviceAccountIdentity,
			stockDeviceInStock;
	TableColumn<Stock, Date> stockDeviceSalesDate;
	private final ObservableList<Stock> data = FXCollections.observableArrayList();
	StockFillteringDB db = new StockFillteringDB();

	@SuppressWarnings("unchecked")
	public void setStockTableData() {

		stockDeviceId = new TableColumn<>("ID");
		stockDeviceId.setMinWidth(50);
		stockDeviceId.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("stockDeviceId"));

		stockDeviceName = new TableColumn<>("Termék");
		stockDeviceName.setMinWidth(250);
		stockDeviceName.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceName"));

		stockDeviceDate = new TableColumn<>("Kelte");
		stockDeviceDate.setMinWidth(80);
		stockDeviceDate.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceDate"));

		Callback<TableColumn<Stock, Date>, TableCell<Stock, Date>> dateCellFactory = (
				TableColumn<Stock, Date> param) -> new DateEditingCell();
				
				

		stockDeviceSalesDate = new TableColumn<>("Eladás");
		stockDeviceSalesDate.setMinWidth(120);

		// stockDeviceSalesDate.setCellValueFactory(cellData ->
		// cellData.getValue().birthdayProperty());
		 stockDeviceSalesDate.setCellFactory(dateCellFactory);
		
		
		stockDeviceSalesDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stock, Date>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Stock, Date> d) {
				Stock actualStock = (Stock) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualStock.setStockDeviceSalesDate(d.getNewValue());
				db.updateStock(actualStock);
				System.out.println("ok");

			}
		});

		stockDeviceQuantity = new TableColumn<>("Darab");
		stockDeviceQuantity.setMinWidth(50);
		stockDeviceQuantity.setEditable(false);
		stockDeviceQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		stockDeviceQuantity.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("stockDeviceQuantity"));

		stockDeviceInStock = new TableColumn<>("Raktáron");
		stockDeviceInStock.setMinWidth(50);
		stockDeviceInStock.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceInStock.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceInStock"));
		stockDeviceInStock.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stock, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Stock, String> d) {
				try {
					Stock actualStock = (Stock) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualStock.setStockDeviceInStock(d.getNewValue());
					db.updateStock(actualStock);
					System.out.println("ok");
				} catch (NumberFormatException numberFormatException) {
					ShowInfo.errorInfoMessengeException("HIBA", "Szám legyen!", numberFormatException.getMessage());
				}
			}
		});

		stockDeviceDescription = new TableColumn<>("Leírás");
		stockDeviceDescription.setMinWidth(750);
		stockDeviceDescription.setEditable(false);
		stockDeviceDescription.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceDescription.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceDescription"));

		stockDeviceAccountIdentity = new TableColumn<>("Számla azonosító");
		stockDeviceAccountIdentity.setMinWidth(150);
		stockDeviceAccountIdentity.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceAccountIdentity
				.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceAccountIdentity"));
		stockDeviceAccountIdentity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stock, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Stock, String> d) {
				Stock actualStock = (Stock) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualStock.setStockDeviceAccountIdentity(d.getNewValue());
				db.updateStock(actualStock);
				System.out.println("ok");
			}
		});

		stockTable.setItems(data);
		stockTable.getColumns().addAll(stockDeviceId, stockDeviceName, stockDeviceDate, stockDeviceSalesDate,
				stockDeviceQuantity, stockDeviceInStock, stockDeviceDescription, stockDeviceAccountIdentity);
		data.addAll(StockFillteringDB.getAllStock());

	}

	@FXML
	private void filteringBtn(ActionEvent event) {
		data.clear();
		data.addAll(StockFillteringDB.getStockNameFiltering(stockDeviceNameFilteringTxt.getText()));
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
