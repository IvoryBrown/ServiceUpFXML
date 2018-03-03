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
	TableColumn<Statistics, String> columnfield;
	private XYChart.Series<String, Number> pcSumXY, notebookSumXY, printerSumXY, monitorSumXY, projektorSumXY,
			pendriverSumXY, upsBatterySumXY, otherSumXY, sumXY;
	private ObservableList<String> dataStatistics = FXCollections.observableArrayList();
	
	private Integer sum = 0;
	private Integer pcSum = 0;
	private Integer notebookSum = 0;
	private Integer printerSum = 0;
	private Integer monitorSum = 0;
	private Integer projektorSum = 0;
	private Integer pendriverSum = 0;
	private Integer upsBatterySum = 0;
	private Integer otherSum = 0;

	private boolean checkDate() {
		if (startDate.getValue() == null || endDate.getValue() == null) {
			return false;
		} else {
			return true;
		}
	}

	public void setGetAllTechnikal() {
		String sql = "SELECT  eszkoz FROM gepadatok WHERE hatarido_datuma >= '" + startDate.getValue()
				+ "' and hatarido_datuma <= '" + endDate.getValue() + "'";
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement pstStn = null;
		ResultSet stnRS = null;
		try {
			pstStn = con.prepareStatement(sql);
			stnRS = pstStn.executeQuery(sql);
			while (stnRS.next()) {
				dataStatistics.add(stnRS.getString("eszkoz"));
			}
		} catch (SQLException ex) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
		}
	}

	private void setStatistics() {
		dataStatistics.clear();
		if (startDate.getValue() == null) {
			startDate.setValue(LocalDate.of(2011, 7, 25));
			endDate.setValue(LocalDate.now());
		}
		if (checkDate()) {
			setGetAllTechnikal();
			for (int i = 0; i < dataStatistics.size(); i++) {
				if (dataStatistics.get(i).equals("Monitor")) {
					monitorSum += 1;
					System.out.println("monitor" + monitorSum);
				} else if (dataStatistics.get(i).equals("Asztali PC")) {
					pcSum += 1;
					System.out.println("Asztali" + pcSum);
				} else if (dataStatistics.get(i).equals("Notebook")) {
					notebookSum += 1;
					System.out.println("Notebook" + notebookSum);
				} else if (dataStatistics.get(i).equals("Nyomtató")) {
					printerSum += 1;
					System.out.println("Nyomtató" + printerSum);
				} else if (dataStatistics.get(i).equals("Projektor")) {
					projektorSum += 1;
					System.out.println("Projektor" + projektorSum);
				} else if (dataStatistics.get(i).equals("Pendrive")) {
					pendriverSum += 1;
					System.out.println("Pendrive" + pendriverSum);
				} else if (dataStatistics.get(i).equals("Szünetmentes tápegység")) {
					upsBatterySum += 1;
					System.out.println("Szünetmentes" + upsBatterySum);
				} else if (dataStatistics.get(i).equals("Egyéb")) {
					otherSum += 1;
					System.out.println("Egyéb" + otherSum);
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
		}
	}

	private void setStatisticsTable() {
		ObservableList<Statistics> subDataList = FXCollections.observableArrayList(
				new Statistics("Asztali gép", sum),
				new Statistics("Asztali gép", 2),
				new Statistics("Asztali gép", 2),
				new Statistics("Asztali gép", 2),
				new Statistics("Asztali gép", 2)
				
				
				);
		
		
		columnfield = new TableColumn<>(" ");
		columnfield.setCellValueFactory(new PropertyValueFactory<Statistics, String>("deviceName"));
		columnfield.setStyle("-fx-background-color: #2A5058 ");
		columnfield.setMinWidth(160);
		columnfield.setMaxWidth(160);

		columnValue = new TableColumn<>("Eszköz");
		columnValue.setCellValueFactory(new PropertyValueFactory<Statistics, Integer>("deviceNumber"));
		columnValue.setMinWidth(40);

		tableStatistics.getColumns().addAll(columnfield, columnValue);
		tableStatistics.setItems(subDataList);
	}

	@SuppressWarnings("unchecked")
	@FXML
	private void getStatistics() {
		setStatistics();
		setStatisticsTable();
		barChar.setAnimated(false);
		barChar.getData().clear();
		barChar.setAnimated(true);
		System.out.println("sum" + sum);
		System.out.println(monitorSum);
		pcSumXY = new XYChart.Series<String, Number>();
		pcSumXY.setName("Asztali PC");
		pcSumXY.getData().add(new XYChart.Data<>("1 ", pcSum));

		notebookSumXY = new XYChart.Series<String, Number>();
		notebookSumXY.setName("Notebook");
		notebookSumXY.getData().add(new XYChart.Data<>(" 2", notebookSum));

		printerSumXY = new XYChart.Series<String, Number>();
		printerSumXY.setName("Nyomtató");
		printerSumXY.getData().add(new XYChart.Data<>(" 3", printerSum));

		monitorSumXY = new XYChart.Series<String, Number>();
		monitorSumXY.setName("Monitor");
		monitorSumXY.getData().add(new XYChart.Data<>(" 4", monitorSum));

		projektorSumXY = new XYChart.Series<String, Number>();
		projektorSumXY.setName("Projektor");
		projektorSumXY.getData().add(new XYChart.Data<>(" 5", projektorSum));

		pendriverSumXY = new XYChart.Series<String, Number>();
		pendriverSumXY.setName("Pendriver");
		pendriverSumXY.getData().add(new XYChart.Data<>(" 6", pendriverSum));

		upsBatterySumXY = new XYChart.Series<String, Number>();
		upsBatterySumXY.setName("Szünetmentes");
		upsBatterySumXY.getData().add(new XYChart.Data<>("7 ", upsBatterySum));

		otherSumXY = new XYChart.Series<String, Number>();
		otherSumXY.setName("Egyéb");
		otherSumXY.getData().add(new XYChart.Data<>("8 ", otherSum));

		sumXY = new XYChart.Series<String, Number>();
		sumXY.setName("Összes");
		sumXY.getData().add(new XYChart.Data<>("9 ", sum));

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

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getStatistics();
	}

}
