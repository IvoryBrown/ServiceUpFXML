package com.error.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.error.database.ErrorFillteringDB;
import com.error.pojo.Error;
import com.login.database.LoginDataBase;
import com.setting.combobox.ComboBoxSet;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ErrorController implements Initializable {

	@FXML
	private Label errorName;
	@FXML
	private TextArea errorComment;
	@FXML
	private TableView<Error> errorTable;
	private TableColumn<Error, String> errorTableName, errorTableComment, errorTableStatus;
	private TableColumn<Error, Integer> errorTableId;
	private final ObservableList<Error> dataError = FXCollections.observableArrayList();
	private TrayNotification tray;
	private ErrorFillteringDB errorDb = new ErrorFillteringDB();

	private boolean textName() {
		if (errorName.getText().trim().isEmpty() || errorComment.getText().trim().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	private void errorTable() {
		errorTable.setStyle("-fx-text-background-color: white;");
		errorTableId = new TableColumn<Error, Integer>("ID");
		errorTableId.setMinWidth(50);
		errorTableId.setCellValueFactory(new PropertyValueFactory<Error, Integer>("errorId"));

		errorTableName = new TableColumn<Error, String>("Név");
		errorTableName.setMinWidth(160);
		errorTableName.setCellValueFactory(new PropertyValueFactory<Error, String>("errorName"));

		errorTableComment = new TableColumn<Error, String>("Hiba leírás");
		errorTableComment.setMinWidth(900);
		errorTableComment.setCellValueFactory(new PropertyValueFactory<Error, String>("errorComment"));

		errorTableStatus = new TableColumn<Error, String>("Állapot");
		errorTableStatus.setMinWidth(90);
		errorTableStatus.setCellValueFactory(new PropertyValueFactory<Error, String>("errorStatus"));
		if (LoginDataBase.authority.equals("Admin")) {
			errorTableStatus.setCellValueFactory(i -> {
				final String value = i.getValue().getErrorStatus();
				return Bindings.createObjectBinding(() -> value);
			});
			errorTableStatus.setCellFactory(ComboBoxTableCell.forTableColumn(ComboBoxSet.setErrorStatusCombobox()));
			errorTableStatus.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Error, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Error, String> d) {
					Error actualDevice = (Error) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualDevice.setErrorStatus(d.getNewValue());
					errorDb.updateError(actualDevice);
					tray = new TrayNotification("Állapot!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.showAndDismiss(Duration.seconds(1));
				}
			});
		}
		errorTable.setItems(dataError);
		errorTable.getColumns().addAll(errorTableId, errorTableName, errorTableComment, errorTableStatus);
		dataError.addAll(ErrorFillteringDB.getAllError());
	}

	@FXML
	private void btnNewError() {
		if (textName()) {
			try {
				Connection con = DataBaseConnect.getConnection();
				PreparedStatement insertError = con
						.prepareStatement("INSERT INTO error(error_name, error_comment)" + "values(?,?) ");
				insertError.setString(1, errorName.getText());
				insertError.setString(2, errorComment.getText());
				insertError.executeUpdate();
				tray = new TrayNotification("Remek!", "Sikeres Felvétel", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
				dataError.clear();
				dataError.addAll(ErrorFillteringDB.getAllError());
				textFieldClear();
			} catch (SQLException ex) {
				new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
			}
		} else {
			tray = new TrayNotification("HIBA", "Nincs minden mező kitöltve", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}

	}

	private void textFieldClear() {
		errorComment.clear();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		errorTable();
		errorName.setText(LoginDataBase.name);
		errorName.setStyle("-fx-text-fill: #00FF7F; -fx-font-weight: bold;  -fx-font-size: 22.0px;");

	}

}
