package com.service.client;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;

public class ClientFXMLController extends MenuTreeItemController implements Initializable {

	@FXML
	private ComboBox<String> cmbClientInputCounty; 
	@FXML
	private TextField txtClientInputClientName;
	@FXML
	private Label txtClientInputID, txtClientInputNumber; 
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
		if(txtClientInputClientName.getText().trim().isEmpty()) {
			return false; 
		}
		return true;
	}
	
	
	private void btnClientNewclient(){
		if (clientInput()) {
			txtClientInputNumber.setText(ClientIdentficationGenerator.random());
		}else {
			ShowInfo.showInfo("Nem megfelelõ", "Bakker");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComboxAll();
	}
}