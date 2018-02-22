package com.service.stock.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.service.client.controller.ClientFXMLController;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class StockFXMLController extends ClientFXMLController implements Initializable {

	@FXML
	private TableView<Stock> stockTable;
	@FXML
	private TextField stockDeviceNameFilteringTxt;
	@FXML
	Button butten;
	private TableColumn<Stock, Integer> stockDeviceId, stockDeviceQuantity;
	private TableColumn<Stock, String> stockDeviceName, stockDeviceDate, stockDeviceDescription,
			stockDeviceAccountIdentity, stockDeviceInStock;
	private TableColumn<Stock, Date> stockDeviceSalesDate;
	private final ObservableList<Stock> data = FXCollections.observableArrayList();
	private StockFillteringDB db = new StockFillteringDB();
	private TrayNotification tray;

	@SuppressWarnings("unchecked")
	public void setStockTableData() {
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

		stockDeviceQuantity = new TableColumn<>("Darab");
		stockDeviceQuantity.setMinWidth(50);
		stockDeviceQuantity.setEditable(false);
		stockDeviceQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		stockDeviceQuantity.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("stockDeviceQuantity"));

		stockDeviceInStock = new TableColumn<>("Raktáron*");
		stockDeviceInStock.setMinWidth(50);
		stockDeviceInStock.setCellFactory(TextFieldTableCell.forTableColumn());
		stockDeviceInStock.setCellValueFactory(new PropertyValueFactory<Stock, String>("stockDeviceInStock"));
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

		stockTable.setItems(data);
		stockTable.getColumns().addAll(stockDeviceId, stockDeviceName, stockDeviceDate, stockDeviceSalesDate,
				stockDeviceQuantity, stockDeviceInStock, stockDeviceDescription, stockDeviceAccountIdentity);
		data.addAll(StockFillteringDB.getAllStock());

	}
	
	
	@FXML
	private void print(ActionEvent e) {
		
	}

	@FXML
	private void filteringBtn(ActionEvent event) {
		if (setStockCheckTxt()) {
			data.clear();
			data.addAll(StockFillteringDB.getStockNameFiltering(stockDeviceNameFilteringTxt.getText()));
			stockDeviceNameFilteringTxt.clear();
			stockDeviceNameFilteringTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
			tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
			tray.showAndDismiss(Duration.seconds(1));
		} else {
			tray = new TrayNotification("HIBA", "Üres a kereső mező", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}
	}

	@FXML
	private void updateBtn(ActionEvent event) {
		data.clear();
		data.addAll(StockFillteringDB.getAllStock());
		tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
		tray.showAndDismiss(Duration.seconds(1));
		stockDeviceNameFilteringTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
	}

	@FXML
	private void newDevieceBtn(ActionEvent event) {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/com/service/setting/fxmlnewstock/FxmlNewStock.fxml"));
			Stage stage = new Stage();
			stage.setTitle("Eszköz");
			stage.setScene(new Scene(root, 1000, 650));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean setStockCheckTxt() {
		if (stockDeviceNameFilteringTxt.getText().trim().isEmpty()) {
			stockDeviceNameFilteringTxt.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (stockDeviceNameFilteringTxt.getText().trim().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComponentAll();
		setStockTableData();
	}

}
