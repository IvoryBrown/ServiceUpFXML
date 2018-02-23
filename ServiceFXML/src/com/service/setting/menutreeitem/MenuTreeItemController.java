package com.service.setting.menutreeitem;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MenuTreeItemController {
	@FXML
	protected StackPane menuPane;
	
	@FXML
	protected Pane homePane, clientPane, companyPane;
	@FXML
	protected AnchorPane stockPane,clientTablePane;
	@FXML
	protected ToggleButton fullSctene;

	private final String MENU_HOME = "Kezdőlap";
	private final String MENU_CONTACTS = "Ügyfelek";
	private final String MENU_CLIENT = "Magánszemély";
	private final String MENU_COMPANY = "Cég";
	private final String MENU_CLIENT_TABLE = "Ügyfelek Tábla";
	private final String MENU_STOCK = "Raktár";
	private final String MENU_EXIT = "Kilépés";

	@SuppressWarnings("unchecked")
	protected void setMenuData() {

		TreeItem<String> treeItemRoot1 = new TreeItem<>("Menü");
		TreeView<String> treeView = new TreeView<>(treeItemRoot1);
		treeView.setShowRoot(false);

		TreeItem<String> nodeItemA = new TreeItem<>(MENU_HOME);
		TreeItem<String> nodeItemB = new TreeItem<>(MENU_CONTACTS);
		nodeItemB.setExpanded(false);
		TreeItem<String> nodeItemB1 = new TreeItem<>(MENU_CLIENT);
		TreeItem<String> nodeItemB2 = new TreeItem<>(MENU_COMPANY);
		TreeItem<String> nodeItemB3 = new TreeItem<>(MENU_CLIENT_TABLE);

		TreeItem<String> nodeItemC = new TreeItem<>(MENU_STOCK);
		TreeItem<String> nodeItemE = new TreeItem<>(MENU_EXIT);
		nodeItemB.getChildren().addAll(nodeItemB1, nodeItemB2, nodeItemB3);
		treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB, nodeItemC, nodeItemE);

		menuPane.getChildren().add(treeView);

		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				TreeItem<String> selectedItem = (TreeItem<String>) newValue;
				String selectedMenu;
				selectedMenu = selectedItem.getValue();

				if (null != selectedMenu) {
					switch (selectedMenu) {
					case MENU_HOME:
						homePane.setVisible(true);
						clientPane.setVisible(false);
						companyPane.setVisible(false);
						clientTablePane.setVisible(false);
						stockPane.setVisible(false);
						break;
					case MENU_CLIENT:
						homePane.setVisible(false);
						clientPane.setVisible(true);
						companyPane.setVisible(false);
						clientTablePane.setVisible(false);
						stockPane.setVisible(false);
						break;
					case MENU_COMPANY:
						homePane.setVisible(false);
						clientPane.setVisible(false);
						companyPane.setVisible(true);
						clientTablePane.setVisible(false);
						stockPane.setVisible(false);
						break;
					case MENU_CLIENT_TABLE:
						homePane.setVisible(false);
						clientPane.setVisible(false);
						companyPane.setVisible(false);
						clientTablePane.setVisible(true);
						stockPane.setVisible(false);
						break;
					case MENU_STOCK:
						homePane.setVisible(false);
						clientPane.setVisible(false);
						companyPane.setVisible(false);
						clientTablePane.setVisible(false);
						stockPane.setVisible(true);
						break;
					case MENU_EXIT:
						System.exit(0);
						break;
					}
				}
			}
		});

	}

	@FXML
	private void addContact(ActionEvent event) {
		fullSctene.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

			}
		});
	}
}
