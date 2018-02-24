package com.service.client.table;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.client.Client;
import com.service.client.fillteringdb.ClientFillteringDB;
import com.service.stock.controller.StockFXMLController;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ClientTable extends StockFXMLController implements Initializable {
	@FXML
	private TableView<Client> clientTable;
	@FXML
	private TextField clientNameFilteringTxt;

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

		clientCompanyName = new TableColumn<>("Cégnév*");
		clientCompanyName.setMinWidth(200);
		clientCompanyName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCompanyName"));
		clientCompanyName.setCellFactory(TextFieldTableCell.forTableColumn());
		clientCompanyName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualClient.setClientCompanyName(d.getNewValue());
				clientDB.updateClient(actualClient);
				tray = new TrayNotification("Cégnév!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		clientName = new TableColumn<>("Ügyfél név*");
		clientName.setMinWidth(150);
		clientName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
		clientName.setCellFactory(TextFieldTableCell.forTableColumn());
		clientName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualClient.setClientName(d.getNewValue());
				clientDB.updateClient(actualClient);
				tray = new TrayNotification("Cégnév!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		clientCounty = new TableColumn<>("Megye*");
		clientCounty.setMinWidth(160);
		clientCounty.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCounty"));
		clientCounty.setCellFactory(TextFieldTableCell.forTableColumn());
		clientCounty.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualClient.setClientCounty(d.getNewValue());
				clientDB.updateClient(actualClient);
				tray = new TrayNotification("Megye!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		clientSettlement = new TableColumn<>("Település*");
		clientSettlement.setMinWidth(100);
		clientSettlement.setCellValueFactory(new PropertyValueFactory<Client, String>("clientSettlement"));
		clientSettlement.setCellFactory(TextFieldTableCell.forTableColumn());
		clientSettlement.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualClient.setClientSettlement(d.getNewValue());
				clientDB.updateClient(actualClient);
				tray = new TrayNotification("Település!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		clientZipCode = new TableColumn<>("Irányítószám*");
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
						tray = new TrayNotification("Irányítószám!", "Sikeres Frissítése", NotificationType.SUCCESS);
						tray.showAndDismiss(Duration.seconds(1));
					} else {
						tray = new TrayNotification("HIBA", "Nem megfelelő Irányítószám!", NotificationType.ERROR);
						tray.showAndDismiss(Duration.seconds(2));
					}
				} catch (NumberFormatException numberFormatException) {
					tray = new TrayNotification("HIBA", "Nem megfelelő Szám!", NotificationType.ERROR);
					tray.showAndDismiss(Duration.seconds(2));
				}
			}
		});

		clientAddress = new TableColumn<>("Cím*");
		clientAddress.setMinWidth(200);
		clientAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("clientAddress"));
		clientAddress.setCellFactory(TextFieldTableCell.forTableColumn());
		clientAddress.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualClient.setClientAddress(d.getNewValue());
				clientDB.updateClient(actualClient);
				tray = new TrayNotification("Cím!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		clientCompanyPhone = new TableColumn<>("Cég telefon*");
		clientCompanyPhone.setMinWidth(200);
		clientCompanyPhone.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCompanyPhone"));
		clientCompanyPhone.setCellFactory(TextFieldTableCell.forTableColumn());
		clientCompanyPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualClient.setClientCompanyPhone(d.getNewValue());
				clientDB.updateClient(actualClient);
				tray = new TrayNotification("Cég telefon!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		clientCompanyEmail = new TableColumn<>("Cég email*");
		clientCompanyEmail.setMinWidth(200);
		clientCompanyEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCompanyEmail"));
		clientCompanyEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		clientCompanyEmail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualClient.setClientCompanyEmail(d.getNewValue());
				clientDB.updateClient(actualClient);
				tray = new TrayNotification("Cég email!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		clientPhone = new TableColumn<>("Telefon*");
		clientPhone.setMinWidth(140);
		clientPhone.setCellValueFactory(new PropertyValueFactory<Client, String>("clientPhone"));
		clientPhone.setCellFactory(TextFieldTableCell.forTableColumn());
		clientPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualClient.setClientPhone(d.getNewValue());
				clientDB.updateClient(actualClient);
				tray = new TrayNotification("Telefon!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		clientEmail = new TableColumn<>("Email*");
		clientEmail.setMinWidth(150);
		clientEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("clientEmail"));
		clientEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		clientEmail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualClient.setClientEmail(d.getNewValue());
				clientDB.updateClient(actualClient);
				tray = new TrayNotification("Email!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		clientPackage = new TableColumn<>("Csomag*");
		clientPackage.setMinWidth(100);
		clientPackage.setCellValueFactory(new PropertyValueFactory<Client, String>("clientPackage"));
		clientPackage.setCellFactory(TextFieldTableCell.forTableColumn());
		clientPackage.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualClient.setClientPackage(d.getNewValue());
				clientDB.updateClient(actualClient);
				tray = new TrayNotification("Csomag!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		clientComment = new TableColumn<>("Megjegyzés*");
		clientComment.setMinWidth(300);
		clientComment.setCellValueFactory(new PropertyValueFactory<Client, String>("clientComment"));
		clientComment.setCellFactory(TextFieldTableCell.forTableColumn());
		clientComment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Client, String> d) {
				Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualClient.setClientComment(d.getNewValue());
				clientDB.updateClient(actualClient);
				tray = new TrayNotification("Megjegyzés!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		TableColumn<Client, Boolean> col_action = new TableColumn<>("+");
		col_action.setSortable(false);
		col_action.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Client, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Client, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});
		col_action.setCellFactory(new Callback<TableColumn<Client, Boolean>, TableCell<Client, Boolean>>() {
			@Override
			public TableCell<Client, Boolean> call(TableColumn<Client, Boolean> p) {
				return new ButtonCell(clientTable);
			}
		});

		clientTable.setItems(dataClient);
		clientTable.getColumns().addAll(col_action, clientId, clientNumber, clientCompanyName, clientName, clientCounty,
				clientZipCode, clientSettlement, clientAddress, clientCompanyPhone, clientCompanyEmail, clientPhone,
				clientEmail, clientPackage, clientComment);
		dataClient.addAll(ClientFillteringDB.getAllClient());
	}

	@FXML
	private void filteringClientBtn(ActionEvent event) {
		if (setClientCheckTxt()) {
			dataClient.clear();
			dataClient.addAll(ClientFillteringDB.getClientNameFilltering(clientNameFilteringTxt.getText()));
			clientNameFilteringTxt.clear();
			clientNameFilteringTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
			tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
			tray.showAndDismiss(Duration.seconds(1));
		} else {

		}
	}

	@FXML
	private void updateClientBtn(ActionEvent event) {
		dataClient.clear();
		dataClient.addAll(ClientFillteringDB.getAllClient());
		tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
		tray.showAndDismiss(Duration.seconds(1));
		clientNameFilteringTxt.setStyle("-fx-prompt-text-fill: #61a2b1");

	}

	@FXML
	private void newClientBtn() {
		// TODO Automatikusan előállított metóduscsonk

	}

	private boolean setClientCheckTxt() {
		if (clientNameFilteringTxt.getText().trim().isEmpty()) {
			clientNameFilteringTxt.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (clientNameFilteringTxt.getText().trim().isEmpty()) {
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
		setClientTableData();
	}
}
