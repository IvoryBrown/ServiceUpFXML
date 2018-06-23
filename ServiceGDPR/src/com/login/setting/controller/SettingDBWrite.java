package com.login.setting.controller;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class SettingDBWrite {
	private static String PER_R_N = "\r\n";
	static Cipher desCipher;
	static SecretKey myDesKey;
	static byte[] textEncrypted;
	

	public static void writeDB(String url, String name, String password) {
		try {
			BufferedWriter output = new BufferedWriter(
					new FileWriter(System.getProperty("user.home") + "\\PcVipService\\" + "output.txt"));
			// output.write(url + PER_R_N);

			KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
			myDesKey = keygenerator.generateKey();

			desCipher = Cipher.getInstance("DES");

			byte[] text = url.getBytes("UTF8");
			desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
			textEncrypted = desCipher.doFinal(text);

			String s = new String(textEncrypted);
			output.write(s + PER_R_N);
			output.close();

		} catch (IOException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		}

	}

	public static void output() {
		try {
			FileReader fr = new FileReader(System.getProperty("user.home") + "\\PcVipService\\" + "output.txt");
			int c;
			String g;
			while ((c = fr.read()) != -1) {
				g = String.valueOf(c);
			}
				desCipher.init(Cipher.DECRYPT_MODE, myDesKey);

				byte[] textDecrypted = desCipher.doFinal(textEncrypted);

				g = new String(textDecrypted);
				System.out.println(g);
			
			fr.close();

		} catch (FileNotFoundException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		}
	}
}
