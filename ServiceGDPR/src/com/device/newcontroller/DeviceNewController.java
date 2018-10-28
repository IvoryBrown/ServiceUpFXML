package com.device.newcontroller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class DeviceNewController implements Initializable{
	@FXML
	private DatePicker deviceSalesBuying, deviceAddDate, deviceEndDate, deviceDeliveryDate;
	@FXML
	private AnchorPane deviceAnchorPDate, deviceAncorPSoftver, deviceAncorPHardver;
	private Callback<DatePicker, DateCell> dayCellFactory;
	
	private void setDate() {
		deviceAddDate.setValue(LocalDate.now());
		dayCellFactory = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(deviceAddDate.getValue().plusDays(5))) {
							setDisable(true);
							setStyle("-fx-background-color: linear-gradient(#61a2b1, #2A5058);");
						}
					}
				};
			}
		};
		deviceEndDate.setDayCellFactory(dayCellFactory);
		deviceEndDate.setValue(deviceAddDate.getValue().plusDays(5));
	}
	
	@FXML
	private void setButtonPaneDate() {
		deviceAnchorPDate.setVisible(true);
		deviceAncorPSoftver.setVisible(false);
		deviceAncorPHardver.setVisible(false);
	}

	@FXML
	private void setButtonPaneSoftver() {
		deviceAnchorPDate.setVisible(false);
		deviceAncorPSoftver.setVisible(true);
		deviceAncorPHardver.setVisible(false);
	}

	@FXML
	private void setButtonPaneHardver() {
		deviceAnchorPDate.setVisible(false);
		deviceAncorPSoftver.setVisible(false);
		deviceAncorPHardver.setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDate();
		
	}

}
