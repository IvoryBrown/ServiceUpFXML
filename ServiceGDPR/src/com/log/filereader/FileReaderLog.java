package com.log.filereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.setting.showinfo.ShowInfo;

public class FileReaderLog {
	
	public static String setReader() {
		String sCurrentLine = null;
		String everything = null;
		BufferedReader br = null;
		FileReader fr = null;
		try {
			StringBuilder sbString = new StringBuilder();
			fr = new FileReader(System.getProperty("user.home") + "\\PcVipService\\Log\\" + "temp.txt");
			br = new BufferedReader(fr);
			while ((sCurrentLine = br.readLine()) != null) {
				sbString.append(sCurrentLine+"\r\n") ;
			}
			everything = sbString.toString();
		} catch (IOException e) {
			new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());

			}
		}
		
		return everything;
		
	}
}
