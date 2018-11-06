package com.log.filedelete;

import java.io.File;

public class FileDelete {

	public FileDelete() {
		try {
			File file = new File(System.getProperty("user.home") + "\\PcVipService\\Log\\" + "temp.txt");
			file.delete();

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
