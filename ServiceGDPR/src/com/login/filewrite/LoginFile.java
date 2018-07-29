package com.login.filewrite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.setting.showinfo.ShowInfo;

public class LoginFile {
	
	

	public static void writeDB(String name) {
		
		try {
			
			BufferedWriter output = new BufferedWriter(
					new FileWriter(System.getProperty("user.home") + "\\PcVipService\\Name\\" + "name.txt"));
			String allSetting = name;
			output.write(allSetting);
			output.close();
		} catch (IOException ex) {
			new ShowInfo("Fálj hiba", "", ex.getMessage());
		}

	}

}
