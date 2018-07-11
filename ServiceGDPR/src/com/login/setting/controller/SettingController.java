package com.login.setting.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.administrator.database.AdministratorDataBase;
import com.administrator.pojo.Administrator;
import com.fxdialog.controller.Message;
import com.fxdialog.main.FXDialogMain;
import com.login.setting.main.SettingMain;
import com.setting.tooltip.Popup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

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
	private Label messageLbl;
	@FXML
	private Button exitButton, maxSizeButton, minSizeButton;
	@FXML
	private HBox hBox;
	@FXML
	private TableView<Administrator> tableAndministrator;
	private TableColumn<Administrator, Integer> administratorId;
	private TableColumn<Administrator, String> administratorName, administratorEmail, administratorPost;
	private FXDialogMain main = new FXDialogMain();
	private final ObservableList<Administrator> dataAdministrator = FXCollections.observableArrayList();

	private final String MENU_DATABASE = "Adatbázis";
	private final String MENU_WORKERS = "Dolgozok";
	private final String MENU_ADMINSITRATOR = "Ügyintéző";
	private final String MENU_DEVICETABLE = "Szervizes";
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
		nodeItemB.setExpanded(false);
		TreeItem<String> nodeItemB1 = new TreeItem<>(MENU_ADMINSITRATOR);
		TreeItem<String> nodeItemB2 = new TreeItem<>(MENU_DEVICETABLE);

		TreeItem<String> nodeItemC = new TreeItem<>(MENU_STOCK);
		TreeItem<String> nodeItemE = new TreeItem<>(MENU_EXIT);
		nodeItemB.getChildren().addAll(nodeItemB1, nodeItemB2);
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
			if (name.equals(MENU_ADMINSITRATOR)) {
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
		administratorEmail.setMinWidth(350);
		administratorEmail.setCellValueFactory(new PropertyValueFactory<Administrator, String>("administratorEmail"));

		administratorPost = new TableColumn<>("Beosztás");
		administratorPost.setMinWidth(150);
		administratorPost.setCellValueFactory(new PropertyValueFactory<Administrator, String>("administratorPost"));

		tableAndministrator.setItems(dataAdministrator);
		tableAndministrator.getColumns().addAll(administratorId, administratorName, administratorEmail,
				administratorPost);
		dataAdministrator.addAll(AdministratorDataBase.getAllAdministratorDataBase());

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDBtextField();
		setTooltipButton();
		setMenuIttem();
	}
}
