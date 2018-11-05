package com.stock.table.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.setting.showinfo.ShowInfo;
import com.stock.pojo.Stock;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;

class DateEditingCell extends TableCell<Stock, Date> {

	private DatePicker datePicker;

	DateEditingCell() {
		if (datePicker == null) {
			createDatePicker();
		}
		datePicker.setDayCellFactory(picker -> {
			DateCell cell = new DateCell();
			cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
				datePicker.setValue(cell.getItem());
				if (event.getClickCount() == 2) {
					datePicker.hide();
					commitEdit(Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				}
				event.consume();
			});
			return cell;
		});
	}

	@Override
	public void startEdit() {
		if (!isEmpty()) {
			super.startEdit();
			setText(null);
			setGraphic(datePicker);
		}
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		setText(null);
		setGraphic(datePicker);
	}

	@Override
	public void updateItem(Date item, boolean empty) {
		super.updateItem(item, empty);
		SimpleDateFormat smp = new SimpleDateFormat("dd/MM/yyyy");
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
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			} else {
				if (getItem() != null) {
					setDatepikerDate(smp.format(item));
					setText(smp.format(item));
					setGraphic(datePicker);
					setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				} else {
					setText(null);
					setGraphic(null);
				}

			}
		}
	}

	private void setDatepikerDate(String dateAsStr) {
		LocalDate ld = null;
		int jour, mois, annee;
		jour = mois = annee = 0;
		try {
			jour = Integer.parseInt(dateAsStr.substring(0, 2));
			mois = Integer.parseInt(dateAsStr.substring(3, 5));
			annee = Integer.parseInt(dateAsStr.substring(6, dateAsStr.length()));
		} catch (NumberFormatException e) {
			new ShowInfo("Nem megfelelő dátum", "Szerver válasza: ", e.getMessage());
		}
		ld = LocalDate.of(annee, mois, jour);
		datePicker.setValue(ld);
	}

	private void createDatePicker() {
		datePicker = new DatePicker(getDate());
		datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
		datePicker.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					getTableView().edit(getIndex(), getTableColumn());
				}
			}
		});
	}

	private LocalDate getDate() {
		return getItem() == null ? LocalDate.now() : getItem().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(DatePicker datePicker) {
		this.datePicker = datePicker;
	}

}
