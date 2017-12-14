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

	private final String COUNTRYCOUNTIES[] = { "Bács-Kiskun", "Baranya", "Békés", "Borsod-Abaúj-Zemplén", "Csongrád",
			"Fejér", "Gyõr-Moson-Sopron", "Hajdú-Bihar", "Heves", "Jász-Nagykun-Szolnok", "Komárom-Esztergom", "Nógrád",
			"Pest", "Somogy", "Szabolcs-Szatmár-Bereg", "Tolna", "Vas", "Veszprém", "Zala" };

	private void setComboxAll() {
		cmbClientInputCounty.getItems().addAll(COUNTRYCOUNTIES);
		btnClientNewClient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				btnClientNewclient();

			}
		});
	}

	private boolean clientInput() {
		if (cmbClientInputCounty.getValue() == null) {
			return false;
		}
		return true;
	}

	private void btnClientNewclient() {
		if (clientInput()) {
			try {
				Connection con = DataBaseConnect.getConnection();
				PreparedStatement insertClient = con
						.prepareStatement("INSERT INTO megrendelo(ugyfel_azonosito, ugyfel_nev, megye,"
								+ "telepules, iranyitoszam, cim,ugyfel_email,ugyfel_telefon,ugyintezo,ugyfel_megjegyzes)"
								+ "values(?,?,?,?,?,?,?,?,?,?) ");
				txtClientInputNumber.setText(ClientIdentficationGenerator.random());
				insertClient.setString(1, txtClientInputNumber.getText());
				insertClient.setString(2, txtClientInputClientName.getText());
				insertClient.setString(3, cmbClientInputCounty.getSelectionModel().getSelectedItem());
				insertClient.setString(4, txtClientInputZipCode.getText());
				insertClient.setString(5, txtClientInputSettlement.getText());
				insertClient.setString(6, txtClientInputAddress.getText());
				insertClient.setString(7, txtClientInputEmail.getText());
				insertClient.setString(8, txtClientInputMobil.getText());
				insertClient.setString(9, txtClientInputAdministrator.getText());
				insertClient.setString(10, txtClientInputComment.getText());
				insertClient.executeUpdate();
			} catch (SQLException ex) {
				// TODO: handle exception
			}

		} else {
			ShowInfo.showInfo("Nem megfelelõ", "Bakker");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComboxAll();
	}
}