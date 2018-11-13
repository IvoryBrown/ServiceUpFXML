package com.main.normalsize;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.calendar.main.CalendarMain;
import com.client.main.ClientNewMain;
import com.clientcompany.table.main.ClientCompanyMain;
import com.company.main.CompanyMain;
import com.device.actual.main.DeviceActualMain;
import com.device.all.main.DeviceAllMain;
import com.device.finished.main.DeviceFinishedMain;
import com.device.information.main.DeviceInformationMain;
import com.device.newmain.DeviceNewMain;
import com.error.main.ErrorMain;
import com.log.filedelete.FileDelete;
import com.log.filewriter.FileWriterLog;
import com.log.main.LogMain;
import com.log.newlogdatabase.NewLogDatabase;
import com.login.database.LoginDataBase;
import com.login.filewrite.LoginFile;
import com.login.setting.setting.main.SettingMain;
import com.main.minsize.MainMin;
import com.statistics.main.StatisticsMain;
import com.stock.newmain.StockNewMain;
import com.stock.table.main.StockMain;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class MainController implements Initializable {

	@FXML
	private TreeView<String> treeView;
	@FXML
	private Label nameLabel;
	private TrayNotification tray;

	private final String MENU_HOME = "Kezdőlap";
	private final String MENU_STASTIC = "Statisztika";
	private final String MENU_ERROR_PAGE = "Hibajelentés";
	private final String MENU_CALENDAR = "Naptár";

	private final String MENU_CLIENT = "Ügyfelek";
	private final String MENU_NEW_CLIENT = "Új Ügyfél";
	private final String MENU_NEW_COMPANY = "Új Cég";
	private final String MENU_CLIENT_TABLE = "Ügyfelek Tábla";

	private final String MENU_DEVICE = "Eszköz";
	private final String MENU_DEVICE_NEW = "Új Felvétel";
	private final String MENU_DEVICE_TABLE_ACTUAL = "Folyamatba lévő";
	private final String MENU_DEVICE_AVAILABLE = "Befejezett";
	private final String MENU_DEVICE_TABLE_ALL = "Összes";
	private final String MENU_DEVICE_INFORMATION = "Eszköz Információk";

	private final String MENU_STOCK = "Raktár";
	private final String MENU_NEW_STOCK = "Új Eszköz";
	private final String MENU_TABLE_STOCK = "Raktáron";

	private final String MENU_SETTING = "Beállítás";

	private final String MENU_LOG = "Log";

	private final String MENU_MINIMAL_SIZE = "Minimál Méret";

	private final String MENU_EXIT = "Kilépés";

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
		TreeItem<String> nodeItemD2 = new TreeItem<String>(MENU_DEVICE_TABLE_ACTUAL);
		TreeItem<String> nodeItemD3 = new TreeItem<String>(MENU_DEVICE_AVAILABLE);
		TreeItem<String> nodeItemD4 = new TreeItem<String>(MENU_DEVICE_TABLE_ALL);
		TreeItem<String> nodeItemD5 = new TreeItem<String>(MENU_DEVICE_INFORMATION);
		nodeItemD.getChildren().addAll(nodeItemD1, nodeItemD2, nodeItemD3, nodeItemD4, nodeItemD5);

		TreeItem<String> nodeItemC = new TreeItem<String>(MENU_STOCK);
		nodeItemC.setExpanded(false);
		TreeItem<String> nodeItemC1 = new TreeItem<String>(MENU_NEW_STOCK);
		TreeItem<String> nodeItemC2 = new TreeItem<String>(MENU_TABLE_STOCK);
		nodeItemC.getChildren().addAll(nodeItemC1, nodeItemC2);
		TreeItem<String> nodeItemE = new TreeItem<String>(MENU_SETTING);

		TreeItem<String> nodeItemF = new TreeItem<String>(MENU_LOG);

		TreeItem<String> nodeItemG = new TreeItem<String>(MENU_MINIMAL_SIZE);

		TreeItem<String> nodeItemI = new TreeItem<String>(MENU_EXIT);

		treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB, nodeItemD, nodeItemC, nodeItemE, nodeItemF, nodeItemG,
				nodeItemI);

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

			if (name.equals(MENU_STASTIC)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Statisztika");
				StatisticsMain statistic = new StatisticsMain();
				statistic.start();
				return;
			}
			if (name.equals(MENU_ERROR_PAGE)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Hibajelentés");
				ErrorMain error = new ErrorMain();
				error.start();
				return;
			}
			if (name.equals(MENU_CALENDAR)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Naptár");
				CalendarMain calendar = new CalendarMain();
				calendar.startCalendar();
				return;
			}

			if (name.equals(MENU_NEW_CLIENT)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Új Ügyfél");
				ClientNewMain clientNewMain = new ClientNewMain();
				clientNewMain.newClientBtn();
				return;
			}
			if (name.equals(MENU_NEW_COMPANY)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Új Cég");
				CompanyMain companyMain = new CompanyMain();
				companyMain.start();
				return;
			}
			if (name.equals(MENU_CLIENT_TABLE)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Ügyfelek Tábla");
				ClientCompanyMain clientCompanyMain = new ClientCompanyMain();
				clientCompanyMain.start();
				return;
			}
			if (name.equals(MENU_DEVICE_NEW)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Új Felvétel Eszköz");
				DeviceNewMain newDevice = new DeviceNewMain();
				newDevice.start();
				return;
			}
			if (name.equals(MENU_DEVICE_TABLE_ACTUAL)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Folyamatba lévő Eszkőz");
				DeviceActualMain device = new DeviceActualMain();
				device.start();
				return;
			}
			if (name.equals(MENU_DEVICE_AVAILABLE)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Befejezett Eszkőz");
				DeviceFinishedMain finsihedDevice = new DeviceFinishedMain();
				finsihedDevice.start();
				return;
			}
			if (name.equals(MENU_DEVICE_TABLE_ALL)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Összes Eszkőz");
				DeviceAllMain allDevice = new DeviceAllMain();
				allDevice.start();
				return;
			}
			if (name.equals(MENU_DEVICE_INFORMATION)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Eszköz Információk");
				DeviceInformationMain infoDevice = new DeviceInformationMain();
				infoDevice.start();
				return;
			}
			if (name.equals(MENU_NEW_STOCK)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Új Eszköz");
				StockNewMain newStock = new StockNewMain();
				newStock.start();
				return;
			}
			if (name.equals(MENU_TABLE_STOCK)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Raktár");
				StockMain stockDevice = new StockMain();
				stockDevice.start();
				return;
			}
			if (name.equals(MENU_SETTING)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Beállítás");
				if (LoginDataBase.authority.equals("Admin")) {
					SettingMain main = new SettingMain();
					main.start();
				} else {
					tray = new TrayNotification("Butuska!", "Kérj hozzáférést", NotificationType.ERROR);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
				}

				return;
			}
			if (name.equals(MENU_LOG)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Log");
				if (LoginDataBase.authority.equals("Admin")) {
					LogMain log = new LogMain();
					log.start();
				} else {
					tray = new TrayNotification("Butuska!", "Kérj hozzáférést", NotificationType.ERROR);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
				}
				return;
			}
			if (name.equals(MENU_MINIMAL_SIZE)) {
				new FileWriterLog(LoginDataBase.name + " Főmenü Minimál méret");
				Main.primaryStage.close();
				MainMin mainMin = new MainMin();
				mainMin.start();
				return;
			}


			if (name.equals(MENU_EXIT)) {
				new FileWriterLog(LoginDataBase.name + " Program kilép");
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Kilépés");
				alert.setHeaderText("");
				alert.getDialogPane().getStylesheets().add("/com/setting/showinfo/ShowInfo.css");
				alert.initStyle(StageStyle.TRANSPARENT);
				String s = "Biztos kilépsz?";
				alert.setContentText(s);
				Optional<ButtonType> result = alert.showAndWait();
				if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
					new NewLogDatabase().newLog();
					new FileDelete();
					System.exit(0);
				}
				return;
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setMenuTree();
		LoginFile.setDataBaseOutput();
		nameLabel.setText(LoginDataBase.name);
		nameLabel.setStyle("-fx-text-fill: #00FF7F; -fx-font-weight: bold;");

	}
}
