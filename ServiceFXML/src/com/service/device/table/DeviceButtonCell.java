package com.service.device.table;

import com.service.device.Device;
import com.service.device.DeviceSubRecord;

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
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeviceButtonCell extends TableCell<Device, Boolean> {
	final Button cellButton = new Button("+");
	@FXML
	private TableView<DeviceSubRecord> subTableView;

	DeviceButtonCell(final TableView<Device> tblView) {

		cellButton.setOnAction(new EventHandler<ActionEvent>() {

			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent t) {
				int selectdIndex = getTableRow().getIndex();
				Device selectedRecord = (Device) tblView.getItems().get(selectdIndex);
				ObservableList<DeviceSubRecord> subDataList = FXCollections.observableArrayList(
						new DeviceSubRecord("Email", selectedRecord.getDeviceAdministrator()),
						new DeviceSubRecord("Csomag", selectedRecord.getDeviceComment()),
						new DeviceSubRecord("Megjegyzés", selectedRecord.getDeviceSalesBuyings()));

				@SuppressWarnings("rawtypes")
				TableColumn columnfield = new TableColumn<>(" ");
				columnfield.setCellValueFactory(new PropertyValueFactory<Device, String>("fieldSubRecordName"));
				columnfield.setMinWidth(100);
				columnfield.setMaxWidth(100);

				@SuppressWarnings("rawtypes")
				TableColumn columnValue = new TableColumn<>("Eszköz");
				columnValue.setCellValueFactory(new PropertyValueFactory<DeviceSubRecord, String>("fieldSubRecordValue"));
				columnValue.setMinWidth(845);

				subTableView = new TableView<>();
				subTableView.setItems(subDataList);
				subTableView.getColumns().addAll(columnfield, columnValue);

				try {
					StackPane root = new StackPane();
					root.getChildren().add(subTableView);
					Stage stage = new Stage();
					stage.setResizable(false);
					root.getStylesheets().add(getClass().getClassLoader()
							.getResource("com/service/setting/desing/desing.css").toExternalForm());
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("Ügyfél");
					root.prefWidthProperty().bind(stage.widthProperty());
					stage.setScene(new Scene(root, 950, 430));
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
		}else {
			 setGraphic( null );
             setText( null );
		}
	}
}
