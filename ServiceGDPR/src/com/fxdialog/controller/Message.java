package com.fxdialog.controller;

import javafx.stage.Stage;

public class Message {

	private static String messsage;
	private static Stage stage;

	public static String getMesssage() {
		return messsage;
	}

	public static void setMesssage(String messsage) {
		Message.messsage = messsage;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Message.stage = stage;
	}



	


}
