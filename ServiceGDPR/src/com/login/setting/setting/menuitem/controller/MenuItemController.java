package com.login.setting.setting.menuitem.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.fxdialog.controller.Message;
import com.login.setting.setting.administrator.controller.AdministratorController;
import com.login.setting.setting.main.SettingMain;
import com.setting.tooltip.Popup;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class MenuItemController extends AdministratorController implements Initializable {
	@FXML
	private StackPane settingTrheePane;
	@FXML
	private AnchorPane loginAPane, settingMenuPane, settingDatabase, settingAdministrator, settingDeviceName,
			settingLocation;
	@FXML
	private SplitPane loginSPane;
	@FXML
	private PasswordField loginTxt;
	@FXML
	private Button exitButton, maxSizeButton, minSizeButton;

	private final String MENU_DATABASE = "Adatbázis";
	private final String MENU_WORKERS = "Dolgozok";
	private final String MENU_SETTING = "Beállítások";
	private final String MENU_DEVICE_NAME = "Eszközök";
	private final String MENU_LOCATION = "Helyszínek";
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

	// set button popup
	protected void setTooltipButton() {
		Popup tt = new Popup("Kilépés");
		exitButton.setTooltip(tt);
		Popup tz = new Popup("Teljes Méret");
		maxSizeButton.setTooltip(tz);
		Popup tr = new Popup("Normál Méret");
		minSizeButton.setTooltip(tr);
	}

	// Menu
	@SuppressWarnings("unchecked")
	protected void setMenuIttem() {
		TreeItem<String> treeItemRoot1 = new TreeItem<>("Menü");
		treeView = new TreeView<>(treeItemRoot1);
		treeView.setShowRoot(false);

		TreeItem<String> nodeItemA = new TreeItem<>(MENU_DATABASE);

		TreeItem<String> nodeItemB = new TreeItem<>(MENU_WORKERS);

		TreeItem<String> nodeItemC = new TreeItem<>(MENU_SETTING);
		TreeItem<String> nodeItemC1 = new TreeItem<>(MENU_DEVICE_NAME);
		TreeItem<String> nodeItemC2 = new TreeItem<>(MENU_LOCATION);
		nodeItemC.getChildren().addAll(nodeItemC1, nodeItemC2);
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
				settingDeviceName.setVisible(false);
				settingAdministrator.setVisible(false);
				settingLocation.setVisible(false);
				setDBtextField();
				return;
			}
			if (name.equals(MENU_WORKERS)) {
				settingDatabase.setVisible(false);
				settingDeviceName.setVisible(false);
				settingAdministrator.setVisible(true);
				settingLocation.setVisible(false);
				setAdministratorTableData();
				return;
			}
			if (name.equals(MENU_DEVICE_NAME)) {
				settingDatabase.setVisible(false);
				settingAdministrator.setVisible(false);
				settingDeviceName.setVisible(true);
				settingLocation.setVisible(false);
				setDeviceNameTableData();
				return;
			}
			if (name.equals(MENU_LOCATION)) {
				settingDatabase.setVisible(false);
				settingAdministrator.setVisible(false);
				settingDeviceName.setVisible(false);
				settingLocation.setVisible(true);
				setLocationTableData();
				return;
			}

			if (name.equals(MENU_EXIT)) {
				getExitButton();
				return;
			}
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

	protected void getExitButton() {
		Message.setMesssage("Biztos kilépsz?");
		main.start();
		Message.setStage(SettingMain.primaryStageSettingMain);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDBtextField();
		setTooltipButton();
		setMenuIttem();
	}
}
