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

	private final String COUNTRYCOUNTIES[] = { "B�cs-Kiskun", "Baranya", "B�k�s", "Borsod-Aba�j-Zempl�n", "Csongr�d",
			"Fej�r", "Gy�r-Moson-Sopron", "Hajd�-Bihar", "Heves", "J�sz-Nagykun-Szolnok", "Kom�rom-Esztergom", "N�gr�d",
			"Pest", "Somogy", "Szabolcs-Szatm�r-Bereg", "Tolna", "Vas", "Veszpr�m", "Zala" };

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
			ShowInfo.showInfo("Nem megfelel�", "Bakker");
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setMenuData();
		setComboxAll();
	}
}