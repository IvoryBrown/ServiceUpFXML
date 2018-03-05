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

public class CompanyFXMLController implements Initializable {
	@FXML
	private ComboBox<String> cmbClientInputCounty;
	@FXML
	private TextField txtClientInputClientName, txtClientInputZipCode, txtClientInputSettlement, txtClientInputAddress,
			txtClientInputEmail, txtClientInputMobil, txtClientCompany, txtClientCompanyMobil, txtClientCompanyEmail,
			txtClientCompanyPackard;
	@FXML
	private Label txtClientInputNumber;
	@FXML
	private TextArea txtClientInputComment;
	@FXML
	private TableView<Client> companyCheckTable;
	private TableColumn<Client, String> companyName, companyAddress;
	private final String COUNTRYCOUNTIES[] = { "Bács-Kiskun", "Baranya", "Békés", " Borsod-Abaúj-Zemplén", "Csongrád",
			" Fejér", " Győr-Moson-Sopron", "Hajdú-Bihar", "Heves", "Jász-Nagykun-Szolnok", " Komárom-Esztergom ",
			"Nógrád", "Pest", "Somogy", "Szabolcs-Szatmár-Bereg", "Tolna", "Vas", " Veszprém", "Zala" };

	private final ObservableList<Client> dataCompany = FXCollections.observableArrayList();
	private TrayNotification tray;

	private void setComponentAll() {
		txtClientInputComment.setWrapText(true);
		cmbClientInputCounty.getItems().addAll(COUNTRYCOUNTIES);
	}

	private boolean companyInputBoolen() {
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
		if (txtClientCompany.getText().trim().isEmpty()) {
			txtClientCompany.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (txtClientCompanyMobil.getText().trim().isEmpty()) {
			txtClientCompanyMobil.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (cmbClientInputCounty.getValue() == null || txtClientInputClientName.getText().trim().isEmpty()
				|| txtClientInputSettlement.getText().trim().isEmpty()
				|| txtClientInputZipCode.getText().trim().isEmpty() || txtClientInputAddress.getText().trim().isEmpty()
				|| txtClientInputMobil.getText().trim().isEmpty() || txtClientCompany.getText().trim().isEmpty()
				|| txtClientCompanyMobil.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean companyZipCodedBoolen() {
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

	@FXML
	private void btnClientNewclient() {
		companyCheckTable.setVisible(false);
		if (companyInputBoolen()) {
			if (companyZipCodedBoolen()) {
				try {
					Connection con = DataBaseConnect.getConnection();
					PreparedStatement insertCompany = con
							.prepareStatement("INSERT INTO ugyfel_adatok(ugyfel_azonosito, cegnev, ugyfel_nev, megye,"
									+ "telepules, iranyitoszam, cim, ceg_telefon, ceg_email, ugyfel_email, ugyfel_telefon, csomag_tipus, ugyfel_megjegyzes)"
									+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?) ");
					txtClientInputNumber.setText(ClientIdentficationGenerator.random());
					insertCompany.setString(1, txtClientInputNumber.getText());
					insertCompany.setString(2, txtClientCompany.getText());
					insertCompany.setString(3, txtClientInputClientName.getText());
					insertCompany.setString(4, cmbClientInputCounty.getSelectionModel().getSelectedItem());
					insertCompany.setString(5, txtClientInputSettlement.getText());
					insertCompany.setInt(6, Integer.parseInt(txtClientInputZipCode.getText()));
					insertCompany.setString(7, txtClientInputAddress.getText());
					insertCompany.setString(8, txtClientCompanyMobil.getText());
					insertCompany.setString(9, txtClientCompanyEmail.getText());
					insertCompany.setString(10, txtClientInputEmail.getText());
					insertCompany.setString(11, txtClientInputMobil.getText());
					insertCompany.setString(12, txtClientCompanyPackard.getText());
					insertCompany.setString(13, txtClientInputComment.getText());
					insertCompany.executeUpdate();
					tray = new TrayNotification("Remek!", "Sikeres Felvétel", NotificationType.SUCCESS);
					tray.showAndDismiss(Duration.seconds(1));
					setCompanyInputText();
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

	private void setCompanyInputText() {
		txtClientInputNumber.setText(null);
		txtClientInputClientName.clear();
		cmbClientInputCounty.getSelectionModel().clearSelection();
		txtClientInputSettlement.clear();
		txtClientInputZipCode.clear();
		txtClientInputAddress.clear();
		txtClientInputEmail.clear();
		txtClientInputMobil.clear();
		txtClientInputComment.clear();
		txtClientCompany.clear();
		txtClientCompanyMobil.clear();
		txtClientCompanyEmail.clear();
		txtClientCompanyPackard.clear();
	}

	@SuppressWarnings("unchecked")
	private void companyCheckTable() {
		companyCheckTable.setVisible(true);
		companyName = new TableColumn<>("Cég");
		companyName.setMinWidth(150);
		companyName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientCompanyName"));

		companyAddress = new TableColumn<>("Cím");
		companyAddress.setMinWidth(200);
		companyAddress.setCellValueFactory(new PropertyValueFactory<Client, String>("clientAddress"));
		companyCheckTable.setItems(dataCompany);
		companyCheckTable.getColumns().addAll(companyName, companyAddress);
		dataCompany.addAll(ClientFillteringDB.getAllClient());
	}

	private boolean setClientCheckTxt() {
		if (txtClientCompany.getText().trim().isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

	@FXML
	private void btnCompanyCheck() {
		if (setClientCheckTxt()) {
			companyCheckTable.getColumns().clear();
			companyCheckTable();
			dataCompany.clear();
			dataCompany.addAll(ClientFillteringDB.getCompanyNameFilltering(txtClientCompany.getText()));
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
