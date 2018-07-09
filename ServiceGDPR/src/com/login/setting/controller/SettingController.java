package com.login.setting.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.fxdialog.controller.Message;
import com.fxdialog.main.FXDialogMain;
import com.login.setting.main.SettingMain;
import com.setting.tooltip.Popup;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
	private Button exitButton,maxSizeButton;
	@FXML
	private HBox hBox;
	

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDBtextField();
		setTooltipButton();
		setMenuIttem();
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
				return;
			}
			if (name.equals(MENU_ADMINSITRATOR)) {
				settingDatabase.setVisible(false);
				settingAdministrator.setVisible(true);
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
			messageLbl.setStyle("-fx-text-fill: green;");
			messageLbl.setText("Sikeres beállítás!!");

		}
	}

	@FXML
	private void setExitButton() {
		getExitButton();
	}
	@FXML
	private void maxSizeButton() {
		SettingMain.primaryStageSettingMain.setMaximized(true);
		
	}
	@FXML
	private void minSizeButton() {
		SettingMain.primaryStageSettingMain.setMinHei //méret kicsi!!!
		
	}

	private void getExitButton() {
		Message.setMesssage("Biztos kilépsz?");
		FXDialogMain main = new FXDialogMain();
		main.start();
		Message.setStage(SettingMain.primaryStageSettingMain);
	}
}
