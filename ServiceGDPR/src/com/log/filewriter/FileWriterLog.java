package com.log.filewriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.setting.showinfo.ShowInfo;

public class FileWriterLog {

	public FileWriterLog(String writer) {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		String timeStamp = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(Calendar.getInstance().getTime());
		String fileName = "temp.txt";

		try {
			final File homeDirectory = new File(System.getProperty("user.home") + "\\PcVipService\\Log\\");
			if (!homeDirectory.exists()) {
				boolean result = homeDirectory.mkdir();
				if (result) {
					System.out.println("Mappa kész");
				}
			}

			fileWriter = new FileWriter(System.getProperty("user.home") + "\\PcVipService\\Log\\" + fileName, true);

			bufferedWriter = new BufferedWriter(fileWriter);

			bufferedWriter.write(timeStamp + "; " + writer + "\r\n");

		} catch (IOException ex) {
			new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
		} finally {

			try {

				if (bufferedWriter != null)
					bufferedWriter.close();

				if (fileWriter != null)
					fileWriter.close();

			} catch (IOException ex) {
				new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());

			}
		}
	}

}
