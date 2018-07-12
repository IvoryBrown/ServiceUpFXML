package com.login.setting.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.administrator.database.AdministratorDataBase;
import com.administrator.pojo.Administrator;
import com.fxdialog.controller.Message;
import com.fxdialog.main.FXDialogMain;
import com.login.setting.main.SettingMain;
import com.setting.combobox.ComboBoxSet;
import com.setting.tooltip.Popup;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

public class SettingController implements Initializable {
	@FXML
	private PasswordField loginTxt;
	@FXML
	private StackPane settingTrheePane;
	@FXML
	private AnchorPane loginAPane, settingMenuPane, settingDatabase, settingAdministrator;
	@FXML
	private SplitPane loginSPane;
	@FXML
	private TextField urlTxt, nameTxt, passwordTxt;
	@FXML
	private TextField administratorNameTxt, administratorEmailTxt;
	@FXML
	private ComboBox<String> administratorPostTxt;
	@FXML
	private Label messageLbl;
	@FXML
	private Button exitButton, maxSizeButton, minSizeButton;
	@FXML
	private HBox hBox;
	@FXML
	private TableView<Administrator> tableAndministrator;
	private TableColumn<Administrator, Integer> administratorId;
	private TableColumn<Administrator, String> administratorName, administratorEmail, administratorPost,removeCol;
	private FXDialogMain main = new FXDialogMain();
	private final ObservableList<Administrator> dataAdministrator = FXCollections.observableArrayList();

	private final String MENU_DATABASE = "Adatbázis";
	private final String MENU_WORKERS = "Dolgozok";
	private final String MENU_STOCK = "Raktár";
	private final String MENU_EXIT = "Kilépés";
	private TreeView<String> treeView;

	@FXML
	private void loginTxt() {
		if (loginTxt.getText().equals("123admin123")) {
			loginAPane.setVisible(false);
			loginSPane.setVisible(true);
		} else {
			SettingMain.primaryStageSettingMain.close();
		}
	}

	// SettingDBFile
	private void setDBtextField() {
		SettingDBFile.setDataBaseOutput();
		urlTxt.setText(SettingDBFile.getDBOutput());
		nameTxt.setText(SettingDBFile.getNameOutput());
		passwordTxt.setText(SettingDBFile.getPasswordOutput());
	}

	// set button popup
	private void setTooltipButton() {
		Popup tt = new Popup("Kilépés");
		exitButton.setTooltip(tt);
		Popup tz = new Popup("Teljes Méret");
		maxSizeButton.setTooltip(tz);
		Popup tr = new Popup("Normál Méret");
		minSizeButton.setTooltip(tr);
	}

	// Menu
	@SuppressWarnings("unchecked")
	private void setMenuIttem() {
		TreeItem<String> treeItemRoot1 = new TreeItem<>("Menü");
		treeView = new TreeView<>(treeItemRoot1);
		treeView.setShowRoot(false);

		TreeItem<String> nodeItemA = new TreeItem<>(MENU_DATABASE);

		TreeItem<String> nodeItemB = new TreeItem<>(MENU_WORKERS);

		TreeItem<String> nodeItemC = new TreeItem<>(MENU_STOCK);
		TreeItem<String> nodeItemE = new TreeItem<>(MENU_EXIT);
		treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB, nodeItemC, nodeItemE);

		EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
			handleMouseClicked(event);
		};

		treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);
		settingTrheePane.getChildren().add(treeView);

	}

	@SuppressWarnings("rawtypes")
	private void handleMouseClicked(MouseEvent event) {
		Node node = event.getPickResult().getIntersectedNode();
		if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
			String name = (String) ((TreeItem) treeView.getSelectionModel().getSelectedItem()).getValue();

			if (name.equals(MENU_DATABASE)) {
				settingDatabase.setVisible(true);
				settingAdministrator.setVisible(false);
				setDBtextField();
				return;
			}
			if (name.equals(MENU_WORKERS)) {
				settingDatabase.setVisible(false);
				settingAdministrator.setVisible(true);
				setAdministratorTableData();
				return;
			}

			if (name.equals(MENU_EXIT)) {
				getExitButton();
				return;
			}
		}
	}

	@FXML
	private void saveDBButton() {
		if (urlTxt.getText().trim().isEmpty() || nameTxt.getText().trim().isEmpty()) {
			messageLbl.setStyle("-fx-text-fill: red;");
			messageLbl.setText("Sikertelen beállítás!!");
		} else {
			SettingDBFile.writeDB(urlTxt.getText(), nameTxt.getText(), passwordTxt.getText());
			messageLbl.setStyle("-fx-text-fill: #2A5058;");
			messageLbl.setText("Sikeres beállítás!!");
			setDBtextField();
		}
	}

	@FXML
	private void setExitButton() {
		getExitButton();
	}

	// full size
	@FXML
	private void maxSizeButton() {
		SettingMain.primaryStageSettingMain.setMaximized(true);
	}

	// resize
	@FXML
	private void minSizeButton() {
		SettingMain.primaryStageSettingMain.setMaximized(false);
		SettingMain.primaryStageSettingMain.sizeToScene();
	}

	private void getExitButton() {
		Message.setMesssage("Biztos kilépsz?");
		main.start();
		Message.setStage(SettingMain.primaryStageSettingMain);
	}

	// admin. table
	@SuppressWarnings("unchecked")
	private void setAdministratorTableData() {
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
				administratorPost,removeCol);
		dataAdministrator.addAll(AdministratorDataBase.getAllAdministratorDataBase());
		setCombobox();
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
	private void setCombobox() {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDBtextField();
		setTooltipButton();
		setMenuIttem();
	}
}
