package com.main.normalsize;

import java.net.URL;
import java.util.ResourceBundle;

import com.main.minsize.MainMin;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class MainController implements Initializable {

	@FXML
	private TreeView<String> treeView;

	private final String MENU_HOME = "Kezdőlap";
	private final String MENU_DEVICE = "Eszköz";
	private final String MENU_DEVICENEW = "Új Eszköz";
	private final String MENU_DEVICETABLE = "Eszköz Tábla";
	private final String MENU_CLIENT_TABLE = "Ügyfelek Tábla";
	private final String MENU_STOCK = "Raktár";
	private final String MENU_MINIMAL_SIZE = "Minimál Méret";
	private final String MENU_EXIT = "Kilépés";

	Main main;

	@SuppressWarnings("unchecked")
	private void setMenuTree() {
		TreeItem<String> treeItemRoot1 = new TreeItem<String>("Menü");
		treeView.setRoot(treeItemRoot1);
		treeView.setShowRoot(false);

		TreeItem<String> nodeItemA = new TreeItem<String>(MENU_HOME);
		TreeItem<String> nodeItemB2 = new TreeItem<String>(MENU_CLIENT_TABLE);

		TreeItem<String> nodeItemD = new TreeItem<String>(MENU_DEVICE);
		nodeItemD.setExpanded(false);
		TreeItem<String> nodeItemD1 = new TreeItem<String>(MENU_DEVICENEW);
		TreeItem<String> nodeItemD2 = new TreeItem<String>(MENU_DEVICETABLE);

		TreeItem<String> nodeItemC = new TreeItem<String>(MENU_STOCK);
		TreeItem<String> nodeItemE = new TreeItem<String>(MENU_MINIMAL_SIZE);
		TreeItem<String> nodeItemF = new TreeItem<String>(MENU_EXIT);
		nodeItemD.getChildren().addAll(nodeItemD1, nodeItemD2);
		treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB2, nodeItemD, nodeItemC, nodeItemE, nodeItemF);

		treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				TreeItem<String> selectedItem = (TreeItem<String>) newValue;
				String selectedMenu;
				selectedMenu = selectedItem.getValue();
				if (null != selectedMenu) {
					switch (selectedMenu) {
					case MENU_HOME:

						break;
					case MENU_DEVICENEW:

						break;
					case MENU_DEVICETABLE:

						break;
					case MENU_CLIENT_TABLE:

						break;
					case MENU_STOCK:

						break;
					case MENU_MINIMAL_SIZE:
						Main.primaryStage.close();
						MainMin mainMin = new MainMin();
						mainMin.start();
						break;
					case MENU_EXIT:
						System.exit(0);
						break;

					}
				}
			}
		});

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setMenuTree();

	}
}
