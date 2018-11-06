package com.stock.table.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.log.filewriter.FileWriterLog;
import com.login.database.LoginDataBase;
import com.stock.pojo.Stock;
import com.stock.table.database.StockTableDB;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class StockTableController implements Initializable {
	@FXML
	private TableView<Stock> stockTable;
	@FXML
	private TextField stockDeviceNameFilteringTxt;
	private TableColumn<Stock, Integer> stockDeviceId, stockDeviceQuantity;
	private TableColumn<Stock, String> stockDeviceName, stockDeviceDate, stockDeviceDescription,
			stockDeviceAccountIdentity, stockDeviceInStock;
	private TableColumn<Stock, Date> stockDeviceSalesDate;
	private final ObservableList<Stock> dataStock = FXCollections.observableArrayList();
	private StockTableDB db = new StockTableDB();
	private TrayNotification tray;

	@SuppressWarnings("unchecked")
	private void setStockTableData() {
		stockTable.setStyle("-fx-text-background-color: whitesmoke;");
		Callback<TableColumn<Stock, Date>, TableCell<Stock, Date>> dateCellFactory = (
				TableColumn<Stock, Date> param) -> new DateEditingCell();

		stockDeviceId = new TableColumn<>("ID");
		stockDeviceId.setMinWidth(50);
		stockDeviceId.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("stockDeviceId"));

		stockDeviceName = new TableColumn<>("Termék");
		stockDeviceName.setMinWidth(200);
		stockDeviceName.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceName"));

		stockDeviceDate = new TableColumn<>("Kelte");
		stockDeviceDate.setMinWidth(80);
		stockDeviceDate.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceDate"));

		stockDeviceSalesDate = new TableColumn<>("Eladás*");
		stockDeviceSalesDate.setMinWidth(140);
		stockDeviceSalesDate.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
				|| LoginDataBase.authority.equals("User")) {
			stockDeviceSalesDate.setCellFactory(dateCellFactory);
			stockDeviceSalesDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stock, Date>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Stock, Date> d) {
					Stock actualStock = (Stock) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualStock.setStockDeviceSalesDate(d.getNewValue());
					db.updateStock(actualStock);
					tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
					tray.showAndDismiss(Duration.seconds(1));
				}
			});
		}
		stockDeviceQuantity = new TableColumn<>("Darab");
		stockDeviceQuantity.setMinWidth(50);
		stockDeviceQuantity.setEditable(false);
		stockDeviceQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		stockDeviceQuantity.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("stockDeviceQuantity"));

		stockDeviceInStock = new TableColumn<>("Raktáron*");
		stockDeviceInStock.setMinWidth(50);
		stockDeviceInStock.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceInStock"));
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
				|| LoginDataBase.authority.equals("User")) {
			stockDeviceInStock.setCellFactory(TextFieldTableCell.forTableColumn());
			stockDeviceInStock.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stock, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Stock, String> d) {
					try {
						if (Integer.valueOf(d.getNewValue()) >= 0) {
							Stock actualStock = (Stock) d.getTableView().getItems().get(d.getTablePosition().getRow());
							actualStock.setStockDeviceInStock(d.getNewValue());
							db.updateStock(actualStock);
							tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
							tray.showAndDismiss(Duration.seconds(1));
						} else {
							tray = new TrayNotification("HIBA", "Nem pozítiv Szám!", NotificationType.ERROR);
							tray.showAndDismiss(Duration.seconds(2));
						}
					} catch (NumberFormatException numberFormatException) {
						tray = new TrayNotification("HIBA", "Nem megfelelő Szám!", NotificationType.ERROR);
						tray.showAndDismiss(Duration.seconds(2));
					}
				}
			});
		}
		stockDeviceDescription = new TableColumn<>("Leírás");
		stockDeviceDescription.setMinWidth(650);
		stockDeviceDescription.setEditable(false);
		stockDeviceDescription.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceDescription.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceDescription"));

		stockDeviceAccountIdentity = new TableColumn<>("Számla azonosító*");
		stockDeviceAccountIdentity.setMinWidth(150);
		stockDeviceAccountIdentity.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceAccountIdentity
				.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceAccountIdentity"));
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
				|| LoginDataBase.authority.equals("User")) {
			stockDeviceAccountIdentity.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Stock, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Stock, String> d) {
					Stock actualStock = (Stock) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualStock.setStockDeviceAccountIdentity(d.getNewValue());
					db.updateStock(actualStock);
					tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
					tray.showAndDismiss(Duration.seconds(1));
				}
			});
		}

		stockTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stock>() {
			@Override
			public void changed(ObservableValue<? extends Stock> observable, Stock oldValue, Stock newValue) {
				if (oldValue == null || newValue != null) {
					new FileWriterLog(
							LoginDataBase.name + " Raktár kijelőlve: " + String.valueOf(newValue.getStockDeviceId()));
				}
			}
		});
		stockTable.setItems(dataStock);
		stockTable.getColumns().addAll(stockDeviceId, stockDeviceName, stockDeviceDate, stockDeviceSalesDate,
				stockDeviceQuantity, stockDeviceInStock, stockDeviceDescription, stockDeviceAccountIdentity);
		dataStock.addAll(StockTableDB.getAllStock(stockDeviceNameFilteringTxt.getText()));

	}

	@FXML
	private void updateClientTable() {
		dataStock.clear();
		stockTable.getItems().clear();
		dataStock.addAll(StockTableDB.getAllStock(stockDeviceNameFilteringTxt.getText()));

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setStockTableData();
		stockDeviceNameFilteringTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					updateClientTable();
					new FileWriterLog(LoginDataBase.name + " Raktárba keresés:" + stockDeviceNameFilteringTxt.getText());
				}
			}
		});
	}

}
