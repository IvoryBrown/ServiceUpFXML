package com.login.setting.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.fxdialog.controller.FXDialogController;
import com.fxdialog.main.FXDialogMain;
import com.login.setting.main.SettingMain;
import com.setting.tooltip.Popup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class SettingController implements Initializable {
	@FXML
	private PasswordField loginTxt;
	@FXML
	private StackPane settingTrheePane;
	@FXML
	private AnchorPane loginAPane, settingMenuPane, settingDatabase;
	@FXML
	private SplitPane loginSPane;
	@FXML
	private TextField urlTxt, nameTxt, passwordTxt;
	@FXML
	private Label messageLbl;
	@FXML
	private Button exitButton;

	private final String MENU_DATABASE = "Adatbázis";
	private final String MENU_DEVICE = "Eszköz";
	private final String MENU_DEVICENEW = "Új Eszköz";
	private final String MENU_DEVICETABLE = "Eszköz Tábla";
	private final String MENU_CLIENT_TABLE = "Ügyfelek Tábla";
	private final String MENU_STOCK = "Raktár";
	private final String MENU_EXIT = "Kilépés";
	private TreeView<String> treeView;

	@FXML
	private void loginTxt() {
		if (loginTxt.getText().equals("123")) {
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

	}

	// Menu
	@SuppressWarnings("unchecked")
	private void setMenuIttem() {
		TreeItem<String> treeItemRoot1 = new TreeItem<>("Menü");
		treeView = new TreeView<>(treeItemRoot1);
		treeView.setShowRoot(false);

		TreeItem<String> nodeItemA = new TreeItem<>(MENU_DATABASE);
		TreeItem<String> nodeItemB2 = new TreeItem<>(MENU_CLIENT_TABLE);

		TreeItem<String> nodeItemD = new TreeItem<>(MENU_DEVICE);
		nodeItemD.setExpanded(false);
		TreeItem<String> nodeItemD1 = new TreeItem<>(MENU_DEVICENEW);
		TreeItem<String> nodeItemD2 = new TreeItem<>(MENU_DEVICETABLE);

		TreeItem<String> nodeItemC = new TreeItem<>(MENU_STOCK);
		TreeItem<String> nodeItemE = new TreeItem<>(MENU_EXIT);
		nodeItemD.getChildren().addAll(nodeItemD1, nodeItemD2);
		treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB2, nodeItemD, nodeItemC, nodeItemE);

		settingTrheePane.getChildren().add(treeView);
		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {

			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				TreeItem<String> selectedItem = (TreeItem<String>) newValue;
				String selectedMenu;
				selectedMenu = selectedItem.getValue();
				if (null != selectedMenu) {
					switch (selectedMenu) {
					case MENU_DATABASE:
						settingDatabase.setVisible(true);
						break;
					case MENU_DEVICENEW:

						break;
					case MENU_DEVICETABLE:

						break;
					case MENU_CLIENT_TABLE:

						break;
					case MENU_STOCK:

						break;
					case MENU_EXIT:
						SettingMain.primaryStageSettingMain.close();
						break;

					}
				}
			}
		});
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
		FXDialogMain main = new FXDialogMain();
		main.start();
		FXDialogController.setdf("exit");
		SettingMain.primaryStageSettingMain.close();
	}
}
