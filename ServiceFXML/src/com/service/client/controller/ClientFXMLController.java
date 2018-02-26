package com.service.client.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.service.setting.database.DataBaseConnect;
import com.service.setting.identification.ClientIdentficationGenerator;
import com.service.setting.menutreeitem.MenuTreeItemController;
import com.service.setting.showinfo.ShowInfo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ClientFXMLController extends MenuTreeItemController {

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

	private TrayNotification tray;

	private final String COUNTRYCOUNTIES[] = { "Bács-Kiskun", "Baranya", "Békés", " Borsod-Abaúj-Zemplén", "Csongrád",
			" Fejér", " Győr-Moson-Sopron", "Hajdú-Bihar", "Heves", "Jász-Nagykun-Szolnok", " Komárom-Esztergom ",
			"Nógrád", "Pest", "Somogy", "Szabolcs-Szatmár-Bereg", "Tolna", "Vas", " Veszprém", "Zala" };

	protected void setComponentAll() {
		txtClientInputComment.setWrapText(true);
		cmbClientInputCounty.getItems().addAll(COUNTRYCOUNTIES);
		btnClientNewClient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				btnClientNewclient();
			}
		});
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

	private void btnClientNewclient() {
		if (clientInputBoolen()) {
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
				try {
					if (Integer.valueOf(txtClientInputZipCode.getText()) >= 1000
							&& Integer.valueOf(txtClientInputZipCode.getText()) <= 9985) {
						insertClient.setInt(5, Integer.parseInt(txtClientInputZipCode.getText()));
					} else {
						tray = new TrayNotification("HIBA", "Nem megfelelő Irányítószám!", NotificationType.ERROR);
						tray.showAndDismiss(Duration.seconds(2));
					}
				} catch (NumberFormatException e) {
					tray = new TrayNotification("HIBA", "Nem megfelelő szám!", NotificationType.ERROR);
					tray.showAndDismiss(Duration.seconds(2));
				}
				insertClient.setString(6, txtClientInputAddress.getText());
				insertClient.setString(7, txtClientInputEmail.getText());
				insertClient.setString(8, txtClientInputMobil.getText());
				insertClient.setString(9, txtClientInputComment.getText());
				insertClient.executeUpdate();
				setClientInputText();
				tray = new TrayNotification("Remek!", "Sikeres Felvétel", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			} catch (SQLException ex) {
				clientPane.setOpacity(0.1);
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
			}
		} else {
			tray = new TrayNotification("HIBA", "Nincs minden mező kitöltve", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}
		clientPane.setOpacity(1);
	}

}