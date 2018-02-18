package com.service.stock.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

import com.service.stock.Stock;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;

class DateEditingCell extends TableCell<Stock, Date> {

	private DatePicker datePicker;

	DateEditingCell() {
	}

	@Override
	public void startEdit() {
		if (!isEmpty()) {
			super.startEdit();
			createDatePicker();
			setText(null);
			setGraphic(datePicker);
		}
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setText(getDate().toString());
		setGraphic(null);
	}

	@Override
	public void updateItem(Date item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			if (isEditing()) {
				if (datePicker != null) {
					datePicker.setValue(getDate());
				
				}
				setText(null);
				setGraphic(this.datePicker);
				
			} else {
				setGraphic(null);
				setText(getDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
				System.out.println("datePicker != null");
			}
		}
	}

	private void createDatePicker() {
		datePicker = new DatePicker(getDate());
		datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		datePicker.setOnAction((e) -> {
			System.out.println("Committed: " + datePicker.getValue().toString());
			commitEdit((Date) Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		});
		// datePicker.focusedProperty().addListener((ObservableValue<? extends Boolean>
		// observable, Boolean oldValue, Boolean newValue) -> {
		// if (!newValue) {
		// commitEdit(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		// }
		// });
	}

	private LocalDate getDate() {
		if (getText() == null) {
			System.out.println("getItem() == null");
			return LocalDate.now();
		} else {
			System.out.println("return getItem().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();");
			return getItem().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
		}

	}

}
