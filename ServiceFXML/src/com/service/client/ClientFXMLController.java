package com.service.client;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.service.setting.database.DataBaseConnect;
import com.service.setting.identification.ClientIdentficationGenerator;
import com.service.setting.menutreeitem.MenuTreeItemController;
import com.service.setting.showinfo.ShowInfo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientFXMLController extends MenuTreeItemController implements Initializable {
	ShowInfo showInfo = new ShowInfo();
	@FXML
	private ComboBox<String> cmbClientInputCounty;
	@FXML
	private TextField txtClientInputClientName, txtClientInputZipCode, txtClientInputSettlement, txtClientInputAddress,
			txtClientInputEmail, txtClientInputMobil, txtClientInputAdministrator;
	@FXML
	private Label txtClientInputID, txtClientInputNumber;
	@FXML
	private TextArea txtClientInputComment;
	@FXML
	private Button btnClientNewClient;

	private final String COUNTRYCOUNTIES[] = { "B�cs-Kiskun", "Baranya", "B�k�s", "Borsod-Aba�j-Zempl�n", "Csongr�d",
			"Fej�r", "Gy�r-Moson-Sopron", "Hajd�-Bihar", "Heves", "J�sz-Nagykun-Szolnok", "Kom�rom-Esztergom", "N�gr�d",
			"Pest", "Somogy", "Szabolcs-Szatm�r-Bereg", "Tolna", "Vas", "Veszpr�m", "Zala" };

	private void setComponentAll() {
		txtClientInputComment.setWrapText(true);
		cmbClientInputCounty.getItems().addAll(COUNTRYCOUNTIES);
		btnClientNewClient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				btnClientNewclient();

			}
		});
	}

	private boolean clientInput() {
		if (txtClientInputClientName.getText().trim().isEmpty()) {
			txtClientInputClientName.getStyleClass().add("errorTextField");
		}
		if (cmbClientInputCounty.getValue() == null) {
			cmbClientInputCounty.setPromptText("K�rlek v�lasz!");
		}
		if (txtClientInputSettlement.getText().trim().isEmpty()) {
			txtClientInputSettlement.getStyleClass().add("errorTextField");
		}
		if (txtClientInputZipCode.getText().trim().isEmpty()) {
			txtClientInputZipCode.getStyleClass().add("errorTextField");
		}
		if (txtClientInputMobil.getText().trim().isEmpty()) {
			txtClientInputMobil.getStyleClass().add("errorTextField");
		}
		if (txtClientInputAdministrator.getText().trim().isEmpty()) {
			txtClientInputAdministrator.getStyleClass().add("errorTextField");
		}
		if (txtClientInputAddress.getText().trim().isEmpty()) {
			txtClientInputAddress.getStyleClass().add("errorTextField");
		}
		if (cmbClientInputCounty.getValue() == null || txtClientInputClientName.getText().trim().isEmpty()
				|| txtClientInputSettlement.getText().trim().isEmpty()
				|| txtClientInputZipCode.getText().trim().isEmpty() || txtClientInputAddress.getText().trim().isEmpty()
				|| txtClientInputMobil.getText().trim().isEmpty()
				|| txtClientInputAdministrator.getText().trim().isEmpty()) {
			return false;
		}
		return true;
	}

	private void btnClientNewclient() {
		if (clientInput()) {
			try {
				Connection con = DataBaseConnect.getConnection();
				PreparedStatement insertClient = con
						.prepareStatement("INSERT INTO ugyfel_adatok(ugyfel_azonosito, ugyfel_nev, megye,"
								+ "telepules, iranyitoszam, cim,ugyfel_email,ugyfel_telefon,ugyintezo,ugyfel_megjegyzes)"
								+ "values(?,?,?,?,?,?,?,?,?,?) ");
				txtClientInputNumber.setText(ClientIdentficationGenerator.random());
				insertClient.setString(1, txtClientInputNumber.getText());
				insertClient.setString(2, txtClientInputClientName.getText());
				insertClient.setString(3, cmbClientInputCounty.getSelectionModel().getSelectedItem());
				insertClient.setString(4, txtClientInputSettlement.getText());
				try {
					insertClient.setInt(5, Integer.parseInt(txtClientInputZipCode.getText()));
				} catch (NumberFormatException e) {
					clientPane.setOpacity(0.1);
					ShowInfo.errorInfoMessengeException("HIBA", "Nem megfelel� �r�ny�t�sz�m!", e.getMessage());
				}
				insertClient.setString(6, txtClientInputAddress.getText());
				insertClient.setString(7, txtClientInputEmail.getText());
				insertClient.setString(8, txtClientInputMobil.getText());
				insertClient.setString(9, txtClientInputAdministrator.getText());
				insertClient.setString(10, txtClientInputComment.getText());
				insertClient.executeUpdate();
				clientPane.setOpacity(0.1);
				showInfo.showInfoMessenge("Sikeres Friss�t�s ", "Remek! ");
			} catch (SQLException ex) {
				clientPane.setOpacity(0.1);
				ShowInfo.errorInfoMessengeException("Adatb�zis Hiba", "Szerver v�lasza: ", ex.getMessage());
			}
		} else {
			clientPane.setOpacity(0.1);
			ShowInfo.errorInfoMessenge("HIBA", "Nincs minden mez� kit�ltve");

		}
		clientPane.setOpacity(1);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComponentAll();
	}
}