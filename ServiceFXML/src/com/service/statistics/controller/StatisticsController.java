package com.service.statistics.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;
import com.service.statistics.Statistics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StatisticsController implements Initializable {
	@FXML
	private BarChart<String, Number> barChar;
	@FXML
	private DatePicker startDate, endDate;
	@FXML
	private TableView<Statistics> tableStatistics;
	TableColumn<Statistics, Integer> columnValue;
	TableColumn<Statistics, String> columnField, columnPercent;
	private XYChart.Series<String, Number> pcSumXY, notebookSumXY, printerSumXY, monitorSumXY, projektorSumXY,
			pendriverSumXY, upsBatterySumXY, otherSumXY, sumXY;
	private ObservableList<String> dataStatistics = FXCollections.observableArrayList();
	private ObservableList<Statistics> subDataList;

	private Integer sum = 0, pcSum = 0, notebookSum = 0, printerSum = 0, monitorSum = 0, projektorSum = 0,
			pendriverSum = 0, upsBatterySum = 0, otherSum = 0, newMachineSum = 0;

	private double pcSumPercent = 0, notebookSumPercent = 0, printerSumPercent = 0, monitorSumPercent = 0,
			projektorSumPercent = 0, pendriverSumPercent = 0, upsBatterySumPercent = 0, otherSumPercent = 0;

	private boolean checkDate() {
		if (startDate.getValue() == null || endDate.getValue() == null) {
			return false;
		} else {
			return true;
		}
	}

	private void setGetAllTechnikal() {
		String sql = "SELECT  eszkoz, uj_gep FROM gepadatok WHERE hatarido_datuma >= '" + startDate.getValue()
				+ "' and hatarido_datuma <= '" + endDate.getValue() + "'";
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement pstStn = null;
		ResultSet stnRS = null;
		try {
			pstStn = con.prepareStatement(sql);
			stnRS = pstStn.executeQuery(sql);
			while (stnRS.next()) {
				dataStatistics.addAll(stnRS.getString("eszkoz"), stnRS.getString("uj_gep"));
			}
		} catch (SQLException ex) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
		}
	}

	private void setStatistics() {
		if (startDate.getValue() == null) {
			startDate.setValue(LocalDate.of(2011, 1, 1));
			endDate.setValue(LocalDate.now());
		}
		if (checkDate()) {
			setGetAllTechnikal();
			for (int i = 0; i < dataStatistics.size(); i++) {
				if (dataStatistics.get(i).equals("Monitor")) {
					monitorSum += 1;
				} else if (dataStatistics.get(i).equals("Asztali PC")) {
					pcSum += 1;
				} else if (dataStatistics.get(i).equals("Notebook")) {
					notebookSum += 1;
				} else if (dataStatistics.get(i).equals("Nyomtató")) {
					printerSum += 1;
				} else if (dataStatistics.get(i).equals("Projektor")) {
					projektorSum += 1;
				} else if (dataStatistics.get(i).equals("Pendrive")) {
					pendriverSum += 1;
				} else if (dataStatistics.get(i).equals("Szünetmentes tápegység")) {
					upsBatterySum += 1;
				} else if (dataStatistics.get(i).equals("Egyéb")) {
					otherSum += 1;
				} else if (dataStatistics.get(i).equals("Igen")) {
					newMachineSum += 1;
				}
			}

		}
		sum += pcSum;
		sum += notebookSum;
		sum += printerSum;
		sum += monitorSum;
		sum += projektorSum;
		sum += pendriverSum;
		sum += upsBatterySum;
		sum += otherSum;
		double v1 = pcSum;
		double v2 = notebookSum;
		double v3 = printerSum;
		double v4 = monitorSum;
		double v5 = projektorSum;
		double v6 = pendriverSum;
		double v7 = upsBatterySum;
		double v8 = otherSum;
		pcSumPercent = (v1 * 100) / sum;
		notebookSumPercent = (v2 * 100) / sum;
		printerSumPercent = (v3 * 100) / sum;
		monitorSumPercent = (v4 * 100) / sum;
		projektorSumPercent = (v5 * 100) / sum;
		pendriverSumPercent = (v6 * 100) / sum;
		upsBatterySumPercent = (v7 * 100) / sum;
		otherSumPercent = (v8 * 100) / sum;

	}

	@SuppressWarnings("unchecked")
	private void setStatisticsTable() {
		subDataList = FXCollections.observableArrayList(
				new Statistics("Asztali gép", pcSum, String.format("%.1f", pcSumPercent)),
				new Statistics("Notebook", notebookSum, String.format("%.1f", notebookSumPercent)),
				new Statistics("Nyomtató", printerSum, String.format("%.1f", printerSumPercent)),
				new Statistics("Monitor", monitorSum, String.format("%.1f", monitorSumPercent)),
				new Statistics("Projektor", projektorSum, String.format("%.1f", projektorSumPercent)),
				new Statistics("Pendrive", pendriverSum, String.format("%.1f", pendriverSumPercent)),
				new Statistics("Szünetmentes ", upsBatterySum, String.format("%.1f", upsBatterySumPercent)),
				new Statistics("Egyéb", otherSum, String.format("%.1f", otherSumPercent)),
				new Statistics("Összesen", sum, "100"), new Statistics("Új gép", newMachineSum, ""));

		columnField = new TableColumn<>("Eszköz");
		columnField.setCellValueFactory(new PropertyValueFactory<Statistics, String>("deviceName"));
		columnField.setStyle("-fx-background-color: #2A5058 ");
		columnField.setMinWidth(160);
		columnField.setMaxWidth(160);

		columnValue = new TableColumn<>("DB");
		columnValue.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("deviceNumber"));
		columnValue.setMinWidth(40);

		columnPercent = new TableColumn<>("%");
		columnPercent.setCellValueFactory(new PropertyValueFactory<Statistics, String>("devicePercent"));
		columnPercent.setMinWidth(40);

		tableStatistics.getColumns().addAll(columnField, columnValue, columnPercent);
		tableStatistics.setItems(subDataList);
	}

	@SuppressWarnings("unchecked")
	private void getStatistics() {
		setStatistics();
		setStatisticsTable();
		pcSumXY = new XYChart.Series<String, Number>();
		pcSumXY.setName("Asztali PC");
		pcSumXY.getData().add(new XYChart.Data<>("Asztali PC", pcSum));

		notebookSumXY = new XYChart.Series<String, Number>();
		notebookSumXY.setName("Notebook");
		notebookSumXY.getData().add(new XYChart.Data<>("Notebook", notebookSum));

		printerSumXY = new XYChart.Series<String, Number>();
		printerSumXY.setName("Nyomtató");
		printerSumXY.getData().add(new XYChart.Data<>("Nyomtató", printerSum));

		monitorSumXY = new XYChart.Series<String, Number>();
		monitorSumXY.setName("Monitor");
		monitorSumXY.getData().add(new XYChart.Data<>("Monitor", monitorSum));

		projektorSumXY = new XYChart.Series<String, Number>();
		projektorSumXY.setName("Projektor");
		projektorSumXY.getData().add(new XYChart.Data<>("Projektor", projektorSum));

		pendriverSumXY = new XYChart.Series<String, Number>();
		pendriverSumXY.setName("Pendriver");
		pendriverSumXY.getData().add(new XYChart.Data<>("Pendrive", pendriverSum));

		upsBatterySumXY = new XYChart.Series<String, Number>();
		upsBatterySumXY.setName("Szünetmentes");
		upsBatterySumXY.getData().add(new XYChart.Data<>("Szünetmentes", upsBatterySum));

		otherSumXY = new XYChart.Series<String, Number>();
		otherSumXY.setName("Egyéb");
		otherSumXY.getData().add(new XYChart.Data<>("Egyéb", otherSum));

		sumXY = new XYChart.Series<String, Number>();
		sumXY.setName("Összes");
		sumXY.getData().add(new XYChart.Data<>("Összes", sum));

		barChar.getData().addAll(pcSumXY, notebookSumXY, printerSumXY, monitorSumXY, projektorSumXY, pendriverSumXY,
				upsBatterySumXY, otherSumXY, sumXY);
		sum = 0;
		pcSum = 0;
		notebookSum = 0;
		printerSum = 0;
		monitorSum = 0;
		projektorSum = 0;
		pendriverSum = 0;
		upsBatterySum = 0;
		otherSum = 0;
		newMachineSum = 0;
	}

	@FXML
	private void btnStatistics() {
		dataStatistics.clear();
		subDataList.clear();
		tableStatistics.getColumns().clear();
		barChar.setAnimated(false);
		barChar.getData().clear();
		barChar.setAnimated(true);
		getStatistics();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getStatistics();
	}

}
