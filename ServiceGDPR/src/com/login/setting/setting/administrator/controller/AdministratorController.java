package com.login.setting.setting.administrator.controller;

import com.fxdialog.main.FXDialogMain;
import com.login.setting.setting.administrator.database.AdministratorDataBase;
import com.login.setting.setting.administrator.pojo.Administrator;
import com.login.setting.setting.database.controller.DataBaseController;
import com.setting.combobox.ComboBoxSet;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class AdministratorController extends DataBaseController{
	
	@FXML
	private TextField administratorNameTxt, administratorEmailTxt;
	@FXML
	private ComboBox<String> administratorPostTxt;
	
	@FXML
	private TableView<Administrator> tableAndministrator;
	private TableColumn<Administrator, Integer> administratorId;
	private TableColumn<Administrator, String> administratorName, administratorEmail, administratorPost, removeCol;
	protected FXDialogMain main = new FXDialogMain();
	private final ObservableList<Administrator> dataAdministrator = FXCollections.observableArrayList();

	// admin. table
	@SuppressWarnings("unchecked")
	protected void setAdministratorTableData() {
		tableAndministrator.getColumns().clear();
		dataAdministrator.clear();

		administratorId = new TableColumn<>("ID");
		administratorId.setMinWidth(70);
		administratorId.setCellValueFactory(new PropertyValueFactory<Administrator, Integer>("administratorId"));

		administratorName = new TableColumn<>("Név");
		administratorName.setMinWidth(150);
		administratorName.setCellValueFactory(new PropertyValueFactory<Administrator, String>("administratorName"));

		administratorEmail = new TableColumn<>("Email");
		administratorEmail.setMinWidth(450);
		administratorEmail.setCellValueFactory(new PropertyValueFactory<Administrator, String>("administratorEmail"));
		administratorEmail.setCellFactory(TextFieldTableCell.forTableColumn());
		administratorEmail.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Administrator, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Administrator, String> t) {
				Administrator actualAdministrator = (Administrator) t.getTableView().getItems()
						.get(t.getTablePosition().getRow());
				if (t.getNewValue().length() > 3 && t.getNewValue().contains("@") && t.getNewValue().contains(".")) {
					actualAdministrator.setAdministratorEmail(t.getNewValue());
					AdministratorDataBase.updateAdministrator(actualAdministrator);
					messageLbl.setStyle("-fx-text-fill: #2A5058;");
					messageLbl.setText("Sikeres beállítás!!");
				} else {
					messageLbl.setStyle("-fx-text-fill: red;");
					messageLbl.setText("Sikertelen beállítás!! Email nem megfelelő!!");
				}
				setAdministratorTableData();
			}
		});

		administratorPost = new TableColumn<>("Beosztás");
		administratorPost.setMinWidth(150);
		administratorPost.setCellValueFactory(new PropertyValueFactory<Administrator, String>("administratorPost"));
		administratorPost.setCellValueFactory(i -> {
			final String value = i.getValue().getAdministratorPost();
			return Bindings.createObjectBinding(() -> value);
		});
		administratorPost.setCellFactory(ComboBoxTableCell.forTableColumn(ComboBoxSet.getAdministratorpost()));
		administratorPost.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Administrator, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Administrator, String> d) {
				Administrator actualAdministrator = (Administrator) d.getTableView().getItems()
						.get(d.getTablePosition().getRow());
				actualAdministrator.setAdministratorPost(d.getNewValue());
				AdministratorDataBase.updateAdministrator(actualAdministrator);
				messageLbl.setStyle("-fx-text-fill: #2A5058;");
				messageLbl.setText("Sikeres beállítás!!");
				setAdministratorTableData();

			}
		});

		removeCol = new TableColumn<>("Törlés");
		removeCol.setMinWidth(100);
		Callback<TableColumn<Administrator, String>, TableCell<Administrator, String>> cellFactory = new Callback<TableColumn<Administrator, String>, TableCell<Administrator, String>>() {
			@Override
			public TableCell<Administrator, String> call(final TableColumn<Administrator, String> param) {
				final TableCell<Administrator, String> cell = new TableCell<Administrator, String>() {
					final Button btn = new Button("Törlés");

					@Override
					public void updateItem(String item, boolean empty) {
						btn.setPrefWidth(90);
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setOnAction((ActionEvent event) -> {
								Administrator device = getTableView().getItems().get(getIndex());
								dataAdministrator.remove(device);
								AdministratorDataBase.removeAdministrator(device);
								messageLbl.setStyle("-fx-text-fill: #2A5058;");
								messageLbl.setText("Sikeres törlés!!");
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		removeCol.setCellFactory(cellFactory);

		tableAndministrator.setItems(dataAdministrator);
		tableAndministrator.getColumns().addAll(administratorId, administratorName, administratorEmail,
				administratorPost, removeCol);
		dataAdministrator.addAll(AdministratorDataBase.getAllAdministratorDataBase());
		setAdministartorCombobox();
	}

	@FXML
	private void saveAdministratorButton() {
		if (checkAdministratorTxField()) {
			String email = administratorEmailTxt.getText();
			if (email.length() > 3 && email.contains("@") && email.contains(".")) {
				Administrator newadministrator = new Administrator(administratorNameTxt.getText(), email,
						administratorPostTxt.getSelectionModel().getSelectedItem());
				AdministratorDataBase.addContact(newadministrator);
				messageLbl.setStyle("-fx-text-fill: #2A5058;");
				messageLbl.setText("Sikeres beállítás!!");
				setAdministratorClear();
				setAdministratorTableData();
			} else {
				messageLbl.setStyle("-fx-text-fill: red;");
				messageLbl.setText("Sikertelen beállítás!! Email nem megfelelő!!");
			}

		}

	}

	// administrator combobox
	private void setAdministartorCombobox() {
		administratorPostTxt.getItems().clear();
		administratorPostTxt.getItems().addAll(ComboBoxSet.getAdministratorpost());
	}

	// administrator texfield check
	private boolean checkAdministratorTxField() {
		if (administratorNameTxt.getText().trim().isEmpty() || administratorEmailTxt.getText().trim().isEmpty()
				|| administratorPostTxt.getValue() == null) {
			administratorNameTxt.setStyle("-fx-prompt-text-fill: #CC0033");
			administratorEmailTxt.setStyle("-fx-prompt-text-fill: #CC0033");
			administratorPostTxt.setPromptText("Kérlek válasz!");
			messageLbl.setStyle("-fx-text-fill: red;");
			messageLbl.setText("Sikertelen beállítás!! Üresek a mezők!!");
			return false;
		} else {
			return true;
		}

	}

	// adminsitrator textfield clear
	private void setAdministratorClear() {
		administratorNameTxt.clear();
		administratorEmailTxt.clear();
		administratorPostTxt.getSelectionModel().clearSelection();
	}
}
