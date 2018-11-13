package com.log.controller;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.log.database.LogDatabase;
import com.log.filewriter.FileWriterLog;
import com.log.pojo.Log;
import com.login.database.LoginDataBase;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class LogController implements Initializable {
	@FXML
	private TableView<Log> tableViewLog;
	private TableColumn<Log, Integer> logId;
	private TableColumn<Log, Date> logDate;
	private TableColumn<Log, String> logAdmistratorName;
	@FXML
	private TextArea areaLog;
	@FXML
	private ComboBox<String> logAdministrator;
	private final ObservableList<Log> dataLog = FXCollections.observableArrayList();
	private LogDatabase logDB = new LogDatabase();

	@SuppressWarnings("unchecked")
	private void setMenuTree() {

		logId = new TableColumn<>("ID");
		logId.setMinWidth(50);
		logId.setCellValueFactory(new PropertyValueFactory<Log, Integer>("logId"));

		logAdmistratorName = new TableColumn<>("Azonosító");
		logAdmistratorName.setMinWidth(50);
		logAdmistratorName.setCellValueFactory(new PropertyValueFactory<Log, String>("logAdmistratorName"));

		logDate = new TableColumn<>("Dátum");
		logDate.setMinWidth(50);
		logDate.setCellValueFactory(new PropertyValueFactory<Log, Date>("logDate"));

		tableViewLog.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Log>() {
			@Override
			public void changed(ObservableValue<? extends Log> observable, Log oldValue, Log newValue) {
				if (oldValue == null || newValue != null) {
					areaLog.setText(logDB.getLogTextArea(newValue.getLogId()));
					new FileWriterLog(LoginDataBase.name + " Log kiválsztva: " + newValue.getLogId());
				}
			}
		});

		tableViewLog.setItems(dataLog);

		tableViewLog.getColumns().addAll(logId, logAdmistratorName, logDate);
		dataLog.addAll(logDB.getAllLog());
	}

	private void updateLogTable() {
		dataLog.clear();
		tableViewLog.getItems().clear();
		dataLog.addAll(logDB.getAllLog());
	}

	private void setComBobox() {
		logAdministrator.getItems().addAll("Összes");
		logAdministrator.getItems().addAll(logDB.setGetAllAdministrator());
		logAdministrator.setOnAction((e) -> {
			dataLog.clear();
			tableViewLog.getItems().clear();
			dataLog.addAll(logDB.getAllLogFillter(logAdministrator.getSelectionModel().getSelectedItem()));
			if (logAdministrator.getSelectionModel().getSelectedItem().equals("Összes")) {
				updateLogTable();
			}
		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setMenuTree();
		setComBobox();
	}

}
