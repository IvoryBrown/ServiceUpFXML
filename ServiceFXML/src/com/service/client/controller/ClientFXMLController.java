package com.service.client.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.service.client.Client;
import com.service.client.fillteringdb.ClientFillteringDB;
import com.service.setting.database.DataBaseConnect;
import com.service.setting.identification.ClientIdentficationGenerator;
import com.service.setting.showinfo.ShowInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ClientFXMLController implements Initializable {

	@FXML
	private ComboBox<String> cmbClientInputCounty;
	@FXML
	private TextField txtClientInputClientName, txtClientInputZipCode, txtClientInputSettlement, txtClientInputAddress,
			txtClientInputEmail, txtClientInputMobil;
	@FXML
	private Label txtClientInputID, txtClientInputNumber;
	@FXML
	private TextArea txtClientInputComment;
	@FXML
	private Button btnClientNewClient;
	@FXML
	private TableView<Client> clientCheckTable;
	private TableColumn<Client, String> clientNumber, clientAddress ;

	private final ObservableList<Client> dataClient = FXCollections.observableArrayList();
	private TrayNotification tray;

	private final String COUNTRYCOUNTIES[] = { "Bács-Kiskun", "Baranya", "Békés", " Borsod-Abaúj-Zemplén", "Csongrád",
			" Fejér", " Győr-Moson-Sopron", "Hajdú-Bihar", "Heves", "Jász-Nagykun-Szolnok", " Komárom-Esztergom ",
			"Nógrád", "Pest", "Somogy", "Szabolcs-Szatmár-Bereg", "Tolna", "Vas", " Veszprém", "Zala" };

	protected void setComponentAll() {
		txtClientInputComment.setWrapText(true);
		cmbClientInputCounty.getItems().addAll(COUNTRYCOUNTIES);
	}

	private boolean clientInputBoolen() {
		if (txtClientInputClientName.getText().trim().isEmpty()) {
			txtClientInputClientName.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (cmbClientInputCounty.getValue() == null) {
			cmbClientInputCounty.setPromptText("Kérlek válasz!");
		}
		if (txtClientInputSettlement.getText().trim().isEmpty()) {
			txtClientInputSettlement.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (txtClientInputZipCode.getText().trim().isEmpty()) {
			txtClientInputZipCode.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (txtClientInputMobil.getText().trim().isEmpty()) {
			txtClientInputMobil.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (txtClientInputAddress.getText().trim().isEmpty()) {
			txtClientInputAddress.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (cmbClientInputCounty.getValue() == null || txtClientInputClientName.getText().trim().isEmpty()
				|| txtClientInputSettlement.getText().trim().isEmpty()
				|| txtClientInputZipCode.getText().trim().isEmpty() || txtClientInputAddress.getText().trim().isEmpty()
				|| txtClientInputMobil.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean clientZipCodedBoolen() {
		try {
			if (Integer.valueOf(txtClientInputZipCode.getText()) >= 1000
					&& Integer.valueOf(txtClientInputZipCode.getText()) <= 9985) {
				return true;
			} else {
				return false;
			}
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void setClientInputText() {
		txtClientInputNumber.setText(null);
		txtClientInputClientName.clear();
		cmbClientInputCounty.getSelectionModel().clearSelection();
		txtClientInputSettlement.clear();
		txtClientInputZipCode.clear();
		txtClientInputAddress.clear();
		txtClientInputEmail.clear();
		txtClientInputMobil.clear();
		txtClientInputComment.clear();
	}

	@FXML
	private void btnClientNewclient() {
		clientCheckTable.setVisible(true);
		if (clientInputBoolen()) {
			if (clientZipCodedBoolen()) {
				try {
					Connection con = DataBaseConnect.getConnection();
					PreparedStatement insertClient = con
							.prepareStatement("INSERT INTO ugyfel_adatok(ugyfel_azonosito, ugyfel_nev, megye,"
									+ "telepules, iranyitoszam, cim, ugyfel_email, ugyfel_telefon, ugyfel_megjegyzes)"
									+ "values(?,?,?,?,?,?,?,?,?) ");
					txtClientInputNumber.setText(ClientIdentficationGenerator.random());
					insertClient.setString(1, txtClientInputNumber.getText());
					insertClient.setString(2, txtClientInputClientName.getText());
					insertClient.setString(3, cmbClientInputCounty.getSelectionModel().getSelectedItem());
					insertClient.setString(4, txtClientInputSettlement.getText());
					insertClient.setInt(5, Integer.parseInt(txtClientInputZipCode.getText()));
					insertClient.setString(6, txtClientInputAddress.getText());
					insertClient.setString(7, txtClientInputEmail.getText());
					insertClient.setString(8, txtClientInputMobil.getText());
					insertClient.setString(9, txtClientInputComment.getText());
					insertClient.executeUpdate();
					setClientInputText();
					tray = new TrayNotification("Remek!", "Sikeres Felvétel", NotificationType.SUCCESS);
					tray.showAndDismiss(Duration.seconds(1));
				} catch (SQLException ex) {
					ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
				}
			} else {
				tray = new TrayNotification("HIBA", "Nem megfelelő Irányítószám!", NotificationType.ERROR);
				tray.showAndDismiss(Duration.seconds(2));
			}
		} else {
			tray = new TrayNotification("HIBA", "Nincs minden mező kitöltve", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}

	}

	@SuppressWarnings("unchecked")
	private void clientCheckTable() {
		clientCheckTable.setVisible(true);
		clientNumber= new TableColumn<>("Ügyfél");
		clientNumber.setMinWidth(150);
		clientNumber.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
		
		clientAddress = new TableColumn<>("Cím");
		clientAddress.setMinWidth(200);
		clientAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("clientAddress"));
		clientCheckTable.setItems(dataClient);
		clientCheckTable.getColumns().addAll(clientNumber,clientAddress);
		dataClient.addAll(ClientFillteringDB.getAllClient());
	}
	
	private boolean setClientCheckTxt() {
		if (txtClientInputClientName.getText().trim().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@FXML
	private void btnClientCheck() {
		if (setClientCheckTxt()) {
			clientCheckTable.getColumns().clear();
			clientCheckTable();
			dataClient.clear();
			dataClient.addAll(ClientFillteringDB.getClientNameFilltering(txtClientInputClientName.getText()));
			tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
			tray.showAndDismiss(Duration.seconds(1));
		} else {
			tray = new TrayNotification("HIBA", "Üres a név mező", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setComponentAll();

	}

}