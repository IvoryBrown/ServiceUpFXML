package com.device.table;

import com.device.pojo.Device;
import com.device.pojo.DeviceSubRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeviceButtonCell extends TableCell<Device, Boolean> {
	final Button cellButton = new Button("+");
	@FXML
	private TableView<DeviceSubRecord> subTableView;

	public DeviceButtonCell(final TableView<Device> tblView) {

		cellButton.setOnAction(new EventHandler<ActionEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent t) {
				int selectdIndex = getTableRow().getIndex();
				Device selectedRecord = (Device) tblView.getItems().get(selectdIndex);
				ObservableList<DeviceSubRecord> subDataList = FXCollections.observableArrayList(
						new DeviceSubRecord("ID", selectedRecord.getDeviceID()),
						new DeviceSubRecord("Azonosító", selectedRecord.getDeviceNumber()),
						new DeviceSubRecord("Cég", selectedRecord.getDeviceCompanyName()),
						new DeviceSubRecord("Ügyfél", selectedRecord.getDeviceClientName()),
						new DeviceSubRecord("Eszköz", selectedRecord.getDeviceName()),
						new DeviceSubRecord("Gyártó", selectedRecord.getDeviceManufacturer()),
						new DeviceSubRecord("Serial no.", selectedRecord.getDeviceSerialNumber()),
						new DeviceSubRecord("Garancia", selectedRecord.getDeviceGuaranteeConverter()),
						new DeviceSubRecord("Felvétel helye", selectedRecord.getDeviceAdministratorLocation()),
						new DeviceSubRecord("Javítás helye", selectedRecord.getDeviceRepairLocation()),
						new DeviceSubRecord("Állapot", selectedRecord.getDeviceStatus()),
						new DeviceSubRecord("Státusz", selectedRecord.getDeviceStatusz()),
						new DeviceSubRecord("Új gép", selectedRecord.getDeviceNewMachine()),
						new DeviceSubRecord("Ügyintéző", selectedRecord.getDeviceAdministrator()),
						new DeviceSubRecord("Technikus", selectedRecord.getDeviceTechnicalPerson()),
						new DeviceSubRecord("Prioritás", selectedRecord.getDevicePriorit()),
						new DeviceSubRecord("Jelszó", selectedRecord.getDevicePassword()),
						new DeviceSubRecord("Hivatkozási szám", selectedRecord.getDeviceReferences()),
						new DeviceSubRecord("Tartozékok", selectedRecord.getDeviceAccesssory()),
						new DeviceSubRecord("Sérülés", selectedRecord.getDeviceInjury()),
						new DeviceSubRecord("Hiba leírás", selectedRecord.getDeviceErrorDescription()),
						new DeviceSubRecord("Valós hiba", selectedRecord.getDeviceErrorCorrection()),
						new DeviceSubRecord("Eszközről megjegyzés", selectedRecord.getDeviceComment()),
						new DeviceSubRecord("Vásárlás", selectedRecord.getDeviceSalesBuyingConverter()),
						new DeviceSubRecord("Bejelentés", selectedRecord.getDeviceAddDateConverter()),
						new DeviceSubRecord("Határidő", selectedRecord.getDeviceEndDateConverter()),
						new DeviceSubRecord("Kiszállás", selectedRecord.getDeviceDeliveryDateConverter()),
						new DeviceSubRecord("Adatmentés", selectedRecord.getDeviceDataRecovery()),
						new DeviceSubRecord("Szoftver", selectedRecord.getDeviceSoftver()),
						new DeviceSubRecord("Operációs rendszer", selectedRecord.getDeviceOperatingSystem()),
						new DeviceSubRecord("Szoftver megjegyzés", selectedRecord.getDeviceSoftverComment()),
						new DeviceSubRecord("Ház", selectedRecord.getDeviceNewHouseConverter()),
						new DeviceSubRecord("Tápegység", selectedRecord.getDevicePowerSupplyConverter()),
						new DeviceSubRecord("Processzor", selectedRecord.getDeviceProcessorConverter()),
						new DeviceSubRecord("Alaplap", selectedRecord.getDeviceBaseBoardConverter()),
						new DeviceSubRecord("Memória", selectedRecord.getDeviceMemoryConverter()),
						new DeviceSubRecord("Videokártya", selectedRecord.getDeviceVideoCardConverter()),
						new DeviceSubRecord("SSD", selectedRecord.getDeviceSSDDriveConverter()),
						new DeviceSubRecord("Merevlemez", selectedRecord.getDeviceHardDriveConverter()),
						new DeviceSubRecord("Hűtőventillátor", selectedRecord.getDeviceCoolingFanConverter()),
						new DeviceSubRecord("Optikai meghajtó", selectedRecord.getDeviceOpticalDriveConvertel()),
						new DeviceSubRecord("Bővítőkártya", selectedRecord.getDeviceExpansionCardConverter()),
						new DeviceSubRecord("Laptop", selectedRecord.getDeviceLaptopConverter())

				);

				@SuppressWarnings("rawtypes")
				TableColumn columnfield = new TableColumn<>(" ");
				columnfield.setCellValueFactory(new PropertyValueFactory<Device, String>("fieldSubRecordName"));
				columnfield.setStyle("-fx-background-color: #2A5058 ");
				columnfield.setMinWidth(160);
				columnfield.setMaxWidth(160);

				@SuppressWarnings("rawtypes")
				TableColumn columnValue = new TableColumn<>("Eszköz");
				columnValue
						.setCellValueFactory(new PropertyValueFactory<DeviceSubRecord, String>("fieldSubRecordValue"));

				subTableView = new TableView<>();
				subTableView.setStyle("-fx-text-background-color: white;");
				subTableView.setItems(subDataList);
				subTableView.getColumns().addAll(columnfield, columnValue);

				try {
					StackPane root = new StackPane();
					root.getChildren().add(subTableView);
					Stage stage = new Stage();
					root.getStylesheets().add(getClass().getClassLoader().getResource("com/device/view/DeviceTable.css")
							.toExternalForm());
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("Eszköz");
					stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/setting/icon/icon-it.png")));
					root.prefWidthProperty().bind(stage.widthProperty());
					stage.setScene(new Scene(root, 850, 750));
					stage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void updateItem(Boolean t, boolean empty) {
		super.updateItem(t, empty);
		if (!empty) {
			setGraphic(cellButton);
		} else {
			setGraphic(null);
			setText(null);
		}
	}
}