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

	public CalendarPane(Node... children) {
		super(children);
		this.setOnMouseClicked(e -> setCalendarController());

	}

	private void setCalendarController() {
		sDate = String.valueOf(getDate());
		calendarNameList();
		DeviceMain statistic =new DeviceMain();
		statistic.start();
	}

	public ObservableList<Device> calendarNameList(){
		ObservableList<Device> calendarNameList = FXCollections.observableArrayList();
		CalendarDataBase data = new CalendarDataBase();
		calendarNameList.addAll(data.getDeviceNameCalendar(sDate));
		System.out.println(calendarNameList.size());
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
