package com.main.normalsize;

import java.net.URL;
import java.util.ResourceBundle;

import com.client.main.ClientNewMain;
import com.main.minsize.MainMin;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class MainController implements Initializable {

	@FXML
	private TreeView<String> treeView;

	private final String MENU_HOME = "Kezdőlap";
	private final String MENU_STASTIC = "Statisztika";
	private final String MENU_ERROR_PAGE = "Hibajelentés";
	private final String MENU_CALENDAR = "Naptár";

	private final String MENU_CLIENT = "Ügyfelek";
	private final String MENU_NEW_CLIENT = "Új Ügyfél";
	private final String MENU_NEW_COMPANY = "Új Cég";
	private final String MENU_CLIENT_TABLE = "Ügyfelek";

	private final String MENU_DEVICE = "Eszköz";
	private final String MENU_DEVICE_NEW = "Új Felvétel";
	private final String MENU_DEVICE_AVAILABLE = "Meglévő Felvétel";
	private final String MENU_DEVICE_TABLE_ACTUAL = "Aktuális";
	private final String MENU_DEVICE_TABLE_ALL = "Összes";
	private final String MENU_DEVICE_INFORMATION = "Eszköz Információk";

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
		nodeItemA.setExpanded(false);
		TreeItem<String> nodeItemA1 = new TreeItem<String>(MENU_STASTIC);
		TreeItem<String> nodeItemA2 = new TreeItem<String>(MENU_ERROR_PAGE);
		TreeItem<String> nodeItemA3 = new TreeItem<String>(MENU_CALENDAR);
		nodeItemA.getChildren().addAll(nodeItemA1, nodeItemA2, nodeItemA3);

		TreeItem<String> nodeItemB = new TreeItem<String>(MENU_CLIENT);
		nodeItemB.setExpanded(false);
		TreeItem<String> nodeItemB1 = new TreeItem<String>(MENU_NEW_CLIENT);
		TreeItem<String> nodeItemB2 = new TreeItem<String>(MENU_NEW_COMPANY);
		TreeItem<String> nodeItemB3 = new TreeItem<String>(MENU_CLIENT_TABLE);
		nodeItemB.getChildren().addAll(nodeItemB1, nodeItemB2, nodeItemB3);

		TreeItem<String> nodeItemD = new TreeItem<String>(MENU_DEVICE);
		nodeItemD.setExpanded(false);
		TreeItem<String> nodeItemD1 = new TreeItem<String>(MENU_DEVICE_NEW);
		TreeItem<String> nodeItemD2 = new TreeItem<String>(MENU_DEVICE_AVAILABLE);
		TreeItem<String> nodeItemD3 = new TreeItem<String>(MENU_DEVICE_TABLE_ACTUAL);
		TreeItem<String> nodeItemD4 = new TreeItem<String>(MENU_DEVICE_TABLE_ALL);
		TreeItem<String> nodeItemD5 = new TreeItem<String>(MENU_DEVICE_INFORMATION);
		nodeItemD.getChildren().addAll(nodeItemD1, nodeItemD2, nodeItemD3, nodeItemD4, nodeItemD5);

		TreeItem<String> nodeItemC = new TreeItem<String>(MENU_STOCK);

		TreeItem<String> nodeItemE = new TreeItem<String>(MENU_MINIMAL_SIZE);

		TreeItem<String> nodeItemF = new TreeItem<String>(MENU_EXIT);

		treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB, nodeItemD, nodeItemC, nodeItemE, nodeItemF);

		EventHandler<MouseEvent> mouseEventHandle = (MouseEvent event) -> {
			handleMouseClicked(event);
		};

		treeView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandle);
	}

	@SuppressWarnings("rawtypes")
	private void handleMouseClicked(MouseEvent event) {
		Node node = event.getPickResult().getIntersectedNode();
		if (node instanceof Text || (node instanceof TreeCell && ((TreeCell) node).getText() != null)) {
			String name = (String) ((TreeItem) treeView.getSelectionModel().getSelectedItem()).getValue();
			
			if (name.equals(MENU_NEW_CLIENT)) {
				ClientNewMain clientNewMain = new ClientNewMain();
				clientNewMain.newClientBtn();
				return;
			}
			
			if (name.equals(MENU_MINIMAL_SIZE)) {
				Main.primaryStage.close();
				MainMin mainMin = new MainMin();
				mainMin.start();
				return;
			}
			
			if (name.equals(MENU_EXIT)) {
				System.exit(0);
				return;
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setMenuTree();

	}
}
