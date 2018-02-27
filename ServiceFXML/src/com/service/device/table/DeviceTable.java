package com.service.device.table;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.device.Device;
import com.service.device.controller.DeviceNewController;
import com.service.device.fillteringdb.DeviceFillteringDB;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class DeviceTable extends DeviceNewController implements Initializable {
	@FXML
	private TableView<Device> deviceAllTable;
	private TableColumn<Device, Boolean> deviceLaptop;
	private TableColumn<Device, Integer> deviceId;
	private final ObservableList<Device> dataDevice = FXCollections.observableArrayList();
	DeviceFillteringDB deviceDb = new DeviceFillteringDB();

	@SuppressWarnings("unchecked")
	protected void setDeviceTableData() {
		deviceId = new TableColumn<>("ID");
		deviceId.setMinWidth(50);
		deviceId.setCellValueFactory(new PropertyValueFactory<Device, Integer>("deviceID"));

		deviceLaptop = new TableColumn<>("Laptop");
		deviceLaptop.setEditable(false);
		deviceLaptop.setMinWidth(40);
		deviceLaptop.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
			@Override
			public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
				Device device = param.getValue();
				SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.setDeviceLaptop());
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
		deviceLaptop.setCellFactory(new Callback<TableColumn<Device, Boolean>, //
				TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});

		deviceAllTable.setItems(dataDevice);
		deviceAllTable.getColumns().addAll(deviceId, deviceLaptop);
		dataDevice.addAll(DeviceFillteringDB.getAllDevice());
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
