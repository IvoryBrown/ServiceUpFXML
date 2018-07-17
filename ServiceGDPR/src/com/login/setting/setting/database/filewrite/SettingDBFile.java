package com.login.setting.setting.database.filewrite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class SettingDBFile {
	private static String PER_R_N = "\r\n";
	static Cipher desCipher;
	static SecretKey myDesKey;
	static byte[] textEncrypted;
	static String url;
	static String name;
	static String password;

	// file write SettingController class
	public static void writeDB(String url, String name, String password) {
		try {
			BufferedWriter output = new BufferedWriter(
					new FileWriter(System.getProperty("user.home") + "\\PcVipService\\" + "output.txt"));
			if (password != null) {
				String allSetting = url + PER_R_N + name + PER_R_N + password;
				output.write(allSetting);
			}
			if (password == null) {
				String allSetting = url + PER_R_N + name;
				output.write(allSetting);
			}
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// file read SettingController class
	protected static ArrayList<String> outputDB() {
		ArrayList<String> d = new ArrayList<String>();
		try {
			FileReader fr = new FileReader(System.getProperty("user.home") + "\\PcVipService\\" + "output.txt");
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

	// file outputdb data
	public static void setDataBaseOutput() {
		url = null;
		name = null;
		password = null;
		StringBuilder sb = new StringBuilder();
		if (SettingDBFile.outputDB().size() != 0) {
			sb.append(SettingDBFile.outputDB());
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
			url = u;
			if (p == null) {
				name = n.substring(1, n.length() - 1);
			}
			if (p != null) {
				name = n.substring(1);
				password = p.substring(1, p.length() - 1);
			}
		}

	}

	// set textfield SettingController class
	public static String getDBOutput() {
		return url;
	}

	// set textfield SettingController class
	public static String getNameOutput() {
		return name;
	}

	// set textfield SettingController class
	public static String getPasswordOutput() {
		return password;
	}

}
