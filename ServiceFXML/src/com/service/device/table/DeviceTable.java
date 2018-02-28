package com.service.device.table;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.device.Device;
import com.service.device.controller.DeviceNewController;
import com.service.device.fillteringdb.DeviceFillteringDB;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DeviceTable extends DeviceNewController implements Initializable {
	@FXML
	private TableView<Device> deviceAllTable;
	@FXML
	private TextField deviceClientNameFilteringTxt;
	private TableColumn<Device, Boolean> deviceTableLaptop;
	private TableColumn<Device, Integer> deviceTableId;
	private TableColumn<Device, String> deviceTableAdministrator;
	private final ObservableList<Device> dataDevice = FXCollections.observableArrayList();
	DeviceFillteringDB deviceDb = new DeviceFillteringDB();

	@SuppressWarnings("unchecked")
	protected void setDeviceTableData() {
		deviceTableId = new TableColumn<>("ID");
		deviceTableId.setMinWidth(50);
		deviceTableId.setCellValueFactory(new PropertyValueFactory<Device, Integer>("deviceID"));

		deviceTableLaptop = new TableColumn<>("Laptop");
		deviceTableLaptop.setEditable(false);
		deviceTableLaptop.setMinWidth(48);
		deviceTableLaptop.setCellFactory(new Callback<TableColumn<Device, Boolean>, //
				TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		deviceTableLaptop.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
				Device device = param.getValue();
				SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceLaptop());
				booleanProp.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
							Boolean newValue) {
						device.setDeviceLaptop(newValue);
					}
				});
				return booleanProp;
			}
		});
		
		
		deviceTableAdministrator = new TableColumn<>("Ügy");
		deviceTableAdministrator.setMinWidth(100);
		deviceTableAdministrator.setCellValueFactory(i -> {
	        final String value = i.getValue().getDeviceAdministrator();
	        return Bindings.createObjectBinding(() -> value);
	    });
		deviceTableAdministrator.setCellFactory( ComboBoxTableCell.forTableColumn(deviceDb.administratorList));
		deviceTableAdministrator.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDevice.setDeviceAdministrator(d.getNewValue());
				deviceDb.updateDevice(actualDevice);
				tray = new TrayNotification("Cégnév!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		deviceAllTable.setItems(dataDevice);
		deviceAllTable.getColumns().addAll(deviceTableId, deviceTableLaptop,deviceTableAdministrator);
		dataDevice.addAll(DeviceFillteringDB.getAllDevice());
	}

	@FXML
	private void updateDeviceBtn(ActionEvent event) {
		dataDevice.clear();
		dataDevice.addAll(DeviceFillteringDB.getAllDevice());
		tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
		tray.showAndDismiss(Duration.seconds(1));
		deviceClientNameFilteringTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDeviceTableData();
		setComboxAll();
		setClientTableData();
		setStockTableData();
		setComponentAll();
		setMenuData();

	}

}
