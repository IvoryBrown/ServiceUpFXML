package com.clientcompany.table.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.client.pojo.Client;
import com.clientcompany.table.database.ClientCompanyDB;
import com.login.database.LoginDataBase;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ClientCompanyController implements Initializable {
	@FXML
	private TableView<Client> clientConpanyTable;
	@FXML
	private TextField clientNameFilteringTxt;
	private TableColumn<Client, Integer> clientId;
	private TableColumn<Client, String> clientDate, removeCol;
	private TableColumn<Client, String> clientNumber, clientCompanyName, clientName, clientCounty, clientSettlement,
			clientAddress, clientCompanyPhone, clientCompanyEmail, clientPhone, clientZipCode, clientEmail,
			clientPackage, clientComment;
	private ClientCompanyDB clientCompanyDB = new ClientCompanyDB();
	private TrayNotification tray = new TrayNotification();
	private final ObservableList<Client> dataClient = FXCollections.observableArrayList();

	@SuppressWarnings("unchecked")
	private void setClientTableData() {
		clientId = new TableColumn<>("ID");
		clientId.setMinWidth(50);
		clientId.setCellValueFactory(new PropertyValueFactory<Client, Integer>("clientId"));

		clientNumber = new TableColumn<>("Azonosító");
		clientNumber.setMinWidth(90);
		clientNumber.setCellValueFactory(new PropertyValueFactory<Client, String>("clientNumber"));

		clientCompanyName = new TableColumn<>("Cég*");
		clientCompanyName.setMinWidth(200);
		clientCompanyName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCompanyName"));
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
				|| LoginDataBase.authority.equals("User")) {
			clientCompanyName.setCellFactory(TextFieldTableCell.forTableColumn());
			clientCompanyName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Client, String> d) {
					Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualClient.setClientCompanyName(d.getNewValue());
					clientCompanyDB.updateClient(actualClient);
					tray = new TrayNotification("Cégnév!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					updateClientTable();
				}
			});

			clientName = new TableColumn<>("Ügyfél*");
			clientName.setMinWidth(150);
			clientName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
			if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
					|| LoginDataBase.authority.equals("User")) {
				clientName.setCellFactory(TextFieldTableCell.forTableColumn());
				clientName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<Client, String> d) {
						Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
						actualClient.setClientName(d.getNewValue());
						clientCompanyDB.updateClient(actualClient);
						tray = new TrayNotification("Ügyfél!", "Sikeres Frissítése", NotificationType.SUCCESS);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
						updateClientTable();
					}
				});
			}

			clientCounty = new TableColumn<>("Megye*");
			clientCounty.setMinWidth(160);
			clientCounty.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCounty"));
			if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
					|| LoginDataBase.authority.equals("User")) {
				clientCounty.setCellFactory(TextFieldTableCell.forTableColumn());
				clientCounty.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<Client, String> d) {
						Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
						actualClient.setClientCounty(d.getNewValue());
						clientCompanyDB.updateClient(actualClient);
						tray = new TrayNotification("Megye!", "Sikeres Frissítése", NotificationType.SUCCESS);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
						updateClientTable();
					}
				});
			}

			clientSettlement = new TableColumn<>("Település*");
			clientSettlement.setMinWidth(100);
			clientSettlement.setCellValueFactory(new PropertyValueFactory<Client, String>("clientSettlement"));
			if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
					|| LoginDataBase.authority.equals("User")) {
				clientSettlement.setCellFactory(TextFieldTableCell.forTableColumn());
				clientSettlement.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<Client, String> d) {
						Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
						actualClient.setClientSettlement(d.getNewValue());
						clientCompanyDB.updateClient(actualClient);
						tray = new TrayNotification("Település!", "Sikeres Frissítése", NotificationType.SUCCESS);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
						updateClientTable();
					}
				});
			}

			clientZipCode = new TableColumn<>("Irányítószám*");
			clientZipCode.setMinWidth(40);
			clientZipCode.setCellValueFactory(new PropertyValueFactory<Client, String>("clientZipCode"));
			if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
					|| LoginDataBase.authority.equals("User")) {
				clientZipCode.setCellFactory(TextFieldTableCell.forTableColumn());
				clientZipCode.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<Client, String> d) {
						try {
							if (Integer.valueOf(d.getNewValue()) >= 1000 && Integer.valueOf(d.getNewValue()) <= 9985) {
								Client actualClient = (Client) d.getTableView().getItems()
										.get(d.getTablePosition().getRow());
								actualClient.setClientZipCode(d.getNewValue());
								clientCompanyDB.updateClient(actualClient);
								tray = new TrayNotification("Irányítószám!", "Sikeres Frissítése",
										NotificationType.SUCCESS);
								tray.setAnimationType(AnimationType.POPUP);
								tray.showAndDismiss(Duration.seconds(2));
								updateClientTable();
							} else {
								tray = new TrayNotification("HIBA", "Nem megfelelő Irányítószám!",
										NotificationType.ERROR);
								tray.showAndDismiss(Duration.seconds(2));
								updateClientTable();
							}
						} catch (NumberFormatException numberFormatException) {
							tray = new TrayNotification("HIBA", "Nem megfelelő Szám!", NotificationType.ERROR);
							tray.showAndDismiss(Duration.seconds(2));
							updateClientTable();
						}
					}
				});
			}

			clientAddress = new TableColumn<>("Cím*");
			clientAddress.setMinWidth(200);
			clientAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("clientAddress"));
			if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
					|| LoginDataBase.authority.equals("User")) {
				clientAddress.setCellFactory(TextFieldTableCell.forTableColumn());
				clientAddress.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<Client, String> d) {
						Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
						actualClient.setClientAddress(d.getNewValue());
						clientCompanyDB.updateClient(actualClient);
						tray = new TrayNotification("Cím!", "Sikeres Frissítése", NotificationType.SUCCESS);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
						updateClientTable();
					}
				});
			}

			clientCompanyPhone = new TableColumn<>("Cég telefon*");
			clientCompanyPhone.setMinWidth(200);
			clientCompanyPhone.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCompanyPhone"));
			if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
					|| LoginDataBase.authority.equals("User")) {
				clientCompanyPhone.setCellFactory(TextFieldTableCell.forTableColumn());
				clientCompanyPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<Client, String> d) {
						Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
						actualClient.setClientCompanyPhone(d.getNewValue());
						clientCompanyDB.updateClient(actualClient);
						tray = new TrayNotification("Cég telefon!", "Sikeres Frissítése", NotificationType.SUCCESS);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
						updateClientTable();
					}
				});
			}
			clientCompanyEmail = new TableColumn<>("Cég email*");
			clientCompanyEmail.setMinWidth(200);
			clientCompanyEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCompanyEmail"));
			if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
					|| LoginDataBase.authority.equals("User")) {
				clientCompanyEmail.setCellFactory(TextFieldTableCell.forTableColumn());
				clientCompanyEmail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<Client, String> d) {
						Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
						actualClient.setClientCompanyEmail(d.getNewValue());
						clientCompanyDB.updateClient(actualClient);
						tray = new TrayNotification("Cég email!", "Sikeres Frissítése", NotificationType.SUCCESS);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
						updateClientTable();
					}
				});
			}
			clientPhone = new TableColumn<>("Telefon*");
			clientPhone.setMinWidth(140);
			clientPhone.setCellValueFactory(new PropertyValueFactory<Client, String>("clientPhone"));
			if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
					|| LoginDataBase.authority.equals("User")) {
				clientPhone.setCellFactory(TextFieldTableCell.forTableColumn());
				clientPhone.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<Client, String> d) {
						Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
						actualClient.setClientPhone(d.getNewValue());
						clientCompanyDB.updateClient(actualClient);
						tray = new TrayNotification("Telefon!", "Sikeres Frissítése", NotificationType.SUCCESS);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
						updateClientTable();
					}
				});
			}
			clientEmail = new TableColumn<>("Email*");
			clientEmail.setMinWidth(150);
			clientEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("clientEmail"));
			if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
					|| LoginDataBase.authority.equals("User")) {
				clientEmail.setCellFactory(TextFieldTableCell.forTableColumn());
				clientEmail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<Client, String> d) {
						Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
						actualClient.setClientEmail(d.getNewValue());
						clientCompanyDB.updateClient(actualClient);
						tray = new TrayNotification("Email!", "Sikeres Frissítése", NotificationType.SUCCESS);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
						updateClientTable();
					}
				});
			}
			clientPackage = new TableColumn<>("Csomag*");
			clientPackage.setMinWidth(100);
			clientPackage.setCellValueFactory(new PropertyValueFactory<Client, String>("clientPackage"));
			if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
					|| LoginDataBase.authority.equals("User")) {
				clientPackage.setCellFactory(TextFieldTableCell.forTableColumn());
				clientPackage.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<Client, String> d) {
						Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
						actualClient.setClientPackage(d.getNewValue());
						clientCompanyDB.updateClient(actualClient);
						tray = new TrayNotification("Csomag!", "Sikeres Frissítése", NotificationType.SUCCESS);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
						updateClientTable();
					}
				});
			}
			clientComment = new TableColumn<>("Megjegyzés*");
			clientComment.setMinWidth(300);
			clientComment.setCellValueFactory(new PropertyValueFactory<Client, String>("clientComment"));
			if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
					|| LoginDataBase.authority.equals("User")) {
				clientComment.setCellFactory(TextFieldTableCell.forTableColumn());
				clientComment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Client, String>>() {
					@Override
					public void handle(TableColumn.CellEditEvent<Client, String> d) {
						Client actualClient = (Client) d.getTableView().getItems().get(d.getTablePosition().getRow());
						actualClient.setClientComment(d.getNewValue());
						clientCompanyDB.updateClient(actualClient);
						tray = new TrayNotification("Megjegyzés!", "Sikeres Frissítése", NotificationType.SUCCESS);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
						updateClientTable();
					}
				});
			}

			clientDate = new TableColumn<>("Felvétel Dátuma");
			clientDate.setMinWidth(100);
			clientDate.setCellValueFactory(new PropertyValueFactory<Client, String>("clientDate"));

			removeCol = new TableColumn<>("Törlés");
			removeCol.setMinWidth(100);
			Callback<TableColumn<Client, String>, TableCell<Client, String>> cellFactory = new Callback<TableColumn<Client, String>, TableCell<Client, String>>() {
				@Override
				public TableCell<Client, String> call(final TableColumn<Client, String> param) {
					final TableCell<Client, String> cell = new TableCell<Client, String>() {
						final Button btn = new Button("Törlés");

						@Override
						public void updateItem(String item, boolean empty) {
							btn.setDisable(true);
							if (LoginDataBase.authority.equals("Admin")) {
								btn.setDisable(false);
							}
							super.updateItem(item, empty);
							if (empty) {
								setGraphic(null);
								setText(null);
							} else {
								btn.setOnAction((ActionEvent event) -> {
									Client device = getTableView().getItems().get(getIndex());
									clientCompanyDB.removeContact(device);
									tray = new TrayNotification("Törlés!", "Sikeres Törlés", NotificationType.SUCCESS);
									tray.showAndDismiss(Duration.seconds(1));
									updateClientTable();
								});
								setGraphic(btn);
								setText(null);
							}
						}
					};
					return cell;
				}
			};
			removeCol.setCellFactory(cellFactory);
			
			clientConpanyTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Client>() {
				@Override
				public void changed(ObservableValue<? extends Client> observable, Client oldValue, Client newValue) {
					if (oldValue == null || newValue != null) {
						Device.(newValue.getClientId());
						deviceClientName.setText(newValue.getClientName());
						deviceCompanyName.setText(newValue.getClientCompanyName());
					}
				}
			});

			clientConpanyTable.setItems(dataClient);

			clientConpanyTable.getColumns().addAll(clientId, clientNumber, clientName, clientCompanyName, clientCounty,
					clientZipCode, clientSettlement, clientAddress, clientCompanyPhone, clientCompanyEmail, clientPhone,
					clientEmail, clientPackage, clientComment, clientDate, removeCol);
			dataClient.addAll(clientCompanyDB.getAllClient());
		}
	}

	private void updateClientTable() {
		dataClient.clear();
		clientConpanyTable.getItems().clear();
		dataClient.addAll(clientCompanyDB.getAllClient());

	}

	private void checkClient() {
		dataClient.clear();
		clientConpanyTable.getItems().clear();
		dataClient.addAll(clientCompanyDB.getClientNameFilltering(clientNameFilteringTxt.getText()));
	}

	@FXML
	private void filterCompanytBtn() {
		updateClientTable();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setClientTableData();
		clientNameFilteringTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					checkClient();
				}
			}
		});
	}

}
