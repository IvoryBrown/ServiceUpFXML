package com.log.newlogdatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.log.filereader.FileReaderLog;
import com.login.database.LoginDataBase;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

public class NewLogDatabase {
	private LocalDate localDate;

	public void newLog() {
		String readers = FileReaderLog.setReader();
		try {
			localDate = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String formattedString = localDate.format(formatter);
			Connection con = DataBaseConnect.getConnection();
			PreparedStatement insertStock = con
					.prepareStatement("INSERT INTO logger(belepo_nev, belepet_datum, log_leiras)" + "values(?,?,?) ");
			insertStock.setString(1, LoginDataBase.name);
			insertStock.setString(2, formattedString);
			insertStock.setString(3, readers);
			insertStock.executeUpdate();
		} catch (SQLException e) {
			new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
		}
	}

}
