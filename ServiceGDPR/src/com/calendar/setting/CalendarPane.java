package com.calendar.setting;

import java.time.LocalDate;

import com.calendar.database.CalendarDataBase;
import com.device.main.DeviceMain;
import com.device.pojo.Device;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class CalendarPane extends AnchorPane {
	private LocalDate date;
	private Integer number;
	public static String sDate;
	public static boolean calander = false;

	public CalendarPane(Node... children) {
		super(children);
		this.setOnMouseClicked(e -> setCalendarController());

	}

	private void setCalendarController() {
		calander = true;
		sDate = String.valueOf(getDate());
		DeviceMain device = new DeviceMain();
		device.start();
		calander = false;
	}

	public ObservableList<Device> calendarNameList() {
		ObservableList<Device> calendarNameList = FXCollections.observableArrayList();
		CalendarDataBase data = new CalendarDataBase();
		calendarNameList.addAll(data.getDeviceNameCalendar(sDate));
		return calendarNameList;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
