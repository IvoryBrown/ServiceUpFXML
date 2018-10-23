package com.clientcompany.table.controller;



import java.net.URL;
import java.util.ResourceBundle;

import com.client.pojo.Client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClientCompanyController implements Initializable{
	@FXML
	private TableView<Client> clientConpanyTable;
	@FXML
	private TextField clientNameFilteringTxt;
	private TableColumn<Client, Integer> clientId;
	private TableColumn<Client, String> clientNumber, clientCompanyName, clientName, clientCounty, clientSettlement,
			clientAddress, clientCompanyPhone, clientCompanyEmail, clientPhone, clientZipCode, clientEmail,
			clientPackage, clientComment;
	
	private void setClientTableData() {
		
	}
	@FXML
	private void filterCompanytBtn() {

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setClientTableData();
		
	}

}
