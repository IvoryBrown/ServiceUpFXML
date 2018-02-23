package com.service.client.table;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.client.Client;
import com.service.client.fillteringdb.ClientFillteringDB;
import com.service.stock.controller.StockFXMLController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ClientTable extends StockFXMLController implements Initializable {
	@FXML
	private TableView<Client> clientTable;

	private TableColumn<Client, Integer> clientId;
	private TableColumn<Client, String> clientNumber, clientCompanyName, clientName, clientCounty, clientSettlement,
			clientAddress, clientCompanyPhone, clientCompanyEmail, clientPhone, clientZipCode, clientEmail,
			clientPackage, clientComment;

	private final ObservableList<Client> dataClient = FXCollections.observableArrayList();
	private ClientFillteringDB clientDB = new ClientFillteringDB();
	private TrayNotification tray;

	@SuppressWarnings("unchecked")
	protected void setClientTableData() {
		clientId = new TableColumn<>("ID");
		clientId.setMinWidth(50);
		clientId.setCellValueFactory(new PropertyValueFactory<Client, Integer>("clientId"));

		clientNumber = new TableColumn<>("Azonosító");
		clientNumber.setMinWidth(90);
		clientNumber.setCellValueFactory(new PropertyValueFactory<Client, String>("clientNumber"));

		clientCompanyName = new TableColumn<>("Cégnév");
		clientCompanyName.setMinWidth(200);
		clientCompanyName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCompanyName"));

		clientName = new TableColumn<>("Ügyfél név");
		clientName.setMinWidth(150);
		clientName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));

		clientCounty = new TableColumn<>("Megye");
		clientCounty.setMinWidth(160);
		clientCounty.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCounty"));

		clientSettlement = new TableColumn<>("Település");
		clientSettlement.setMinWidth(100);
		clientSettlement.setCellValueFactory(new PropertyValueFactory<Client, String>("clientSettlement"));

		clientZipCode = new TableColumn<>("Irányítószám");
		clientZipCode.setMinWidth(40);
		clientZipCode.setEditable(true);
		clientZipCode.setCellFactory(TextFieldTableCell.forTableColumn());
		clientZipCode.setCellValueFactory(new PropertyValueFactory<Client, String>("clientZipCode"));
		clientZipCode.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				try {
					if (Integer.valueOf(d.getNewValue()) >= 2000 && Integer.valueOf(d.getNewValue()) <= 9985) {
						Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
						actualClient.setClientZipCode(d.getNewValue());
						clientDB.updateClient(actualClient);
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

		clientAddress = new TableColumn<>("Település");
		clientAddress.setMinWidth(200);
		clientAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("clientAddress"));

		clientCompanyPhone = new TableColumn<>("Cég telefon");
		clientCompanyPhone.setMinWidth(200);
		clientCompanyPhone.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCompanyPhone"));

		clientCompanyEmail = new TableColumn<>("Cég email");
		clientCompanyEmail.setMinWidth(200);
		clientCompanyEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCompanyEmail"));

		clientPhone = new TableColumn<>("Telefon");
		clientPhone.setMinWidth(140);
		clientPhone.setCellValueFactory(new PropertyValueFactory<Client, String>("clientPhone"));

		clientEmail = new TableColumn<>("Email");
		clientEmail.setMinWidth(150);
		clientEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("clientEmail"));

		clientPackage = new TableColumn<>("Csomag");
		clientPackage.setMinWidth(100);
		clientPackage.setCellValueFactory(new PropertyValueFactory<Client, String>("clientPackage"));

		clientComment = new TableColumn<>("Megjegyzés");
		clientComment.setMinWidth(300);
		clientComment.setCellValueFactory(new PropertyValueFactory<Client, String>("clientComment"));

		clientTable.setItems(dataClient);
		clientTable.getColumns().addAll(clientId, clientNumber, clientCompanyName, clientName, clientCounty,
				clientZipCode, clientSettlement, clientAddress, clientCompanyPhone, clientCompanyEmail, clientPhone,
				clientEmail, clientPackage, clientComment);
		dataClient.addAll(ClientFillteringDB.getAllClient());
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComponentAll();
		setStockTableData();
		setClientTableData();
	}
}
