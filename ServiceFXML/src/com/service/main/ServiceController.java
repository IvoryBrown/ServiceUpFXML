package com.service.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ServiceController {
	@FXML
	StackPane menuPane;
	@FXML
	Pane homePane, clientPane, companyPane, devicePane;

	private final String MENU_HOME = "Kezdõlap";
	private final String MENU_CONTACTS = "Ügyfelek";
	private final String MENU_CLIENT = "Magánszemély";
	private final String MENU_COMPANY = "Cég";
	private final String MENU_DEVICE = "Eszköz";
	private final String MENU_EXIT = "Kilépés";
	

	@SuppressWarnings("unchecked")
	protected void setMenuData() {

		TreeItem<String> treeItemRoot1 = new TreeItem<>("Menü");
		TreeView<String> treeView = new TreeView<>(treeItemRoot1);
		treeView.setShowRoot(false);

		TreeItem<String> nodeItemA = new TreeItem<>(MENU_HOME);
		TreeItem<String> nodeItemB = new TreeItem<>(MENU_CONTACTS);
		nodeItemB.setExpanded(false);
		TreeItem<String> nodeItemC = new TreeItem<>(MENU_DEVICE);
		TreeItem<String> nodeItemD = new TreeItem<>(MENU_EXIT);

		TreeItem<String> nodeItemB1 = new TreeItem<>(MENU_CLIENT);
		TreeItem<String> nodeItemB2 = new TreeItem<>(MENU_COMPANY);

		nodeItemB.getChildren().addAll(nodeItemB1, nodeItemB2);
		treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB, nodeItemC, nodeItemD);

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
						devicePane.setVisible(false);
						break;
					case MENU_CLIENT:
						homePane.setVisible(false);
						clientPane.setVisible(true);
						companyPane.setVisible(false);
						devicePane.setVisible(false);
						break;
					case MENU_COMPANY:
						homePane.setVisible(false);
						clientPane.setVisible(false);
						companyPane.setVisible(true);
						devicePane.setVisible(false);
						break;
					case MENU_DEVICE:
						homePane.setVisible(false);
						clientPane.setVisible(false);
						companyPane.setVisible(false);
						devicePane.setVisible(true);
						break;
					case MENU_EXIT:
						System.exit(0);
						break;
					}
				}
			}
		});
	}

}
