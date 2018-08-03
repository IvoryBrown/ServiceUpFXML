package com.login.filewrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.setting.showinfo.ShowInfo;

public class LoginFile {
	private static String PER_R_N = "\r\n";
	static public String userName;
	static public String booleanChBox;

	public static void writeDB(String name, boolean checkBox) {

		try {

			BufferedWriter output = new BufferedWriter(
					new FileWriter(System.getProperty("user.home") + "\\PcVipService\\Name\\" + "name.txt"));
			String allSetting = name + PER_R_N + checkBox;
			output.write(allSetting);
			output.close();
		} catch (IOException ex) {
			new ShowInfo("Fálj hiba", "", ex.getMessage());
		}

	}

	protected static ArrayList<String> outputDB() {
		ArrayList<String> d = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(System.getProperty("user.home") + "\\PcVipService\\Name\\" + "name.txt");
			BufferedReader bf = new BufferedReader(fr);
			String a;
			while ((a = bf.readLine()) != null) {
				d.add(a);
			}
			fr.close();
			bf.close();

		} catch (FileNotFoundException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		}
		return d;
	}

	public static void setDataBaseOutput() {
		userName = null;
		booleanChBox = null;
		StringBuilder sb = new StringBuilder();
		if (LoginFile.outputDB().size() != 0) {
			sb.append(LoginFile.outputDB());
			sb.deleteCharAt(0);
			String s1 = sb.toString();
			String[] s = s1.split(",");
			String u = null;
			String n = null;
			String p = null;
			for (int i = 0; i < s.length; i++) {
				u = s[0];
				n = s[1];
				if (s.length == 3) {
					p = s[2];
				}
			}
			userName = u;
			if (p == null) {
				booleanChBox = n.substring(1, n.length() - 1);
			}
		}
		
	}
}
