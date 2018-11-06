package com.log.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class LogController implements Initializable{
	@FXML
	private TreeView<String> treeViewLog;
	@FXML
	private TextArea areaLog;
	
	@SuppressWarnings("unchecked")
	private void setMenuTree() {
		TreeItem<String> treeItemRoot1 = new TreeItem<String>("Menü");
		treeViewLog.setRoot(treeItemRoot1);
		treeViewLog.setShowRoot(false);
		
		TreeItem<String> nodeItemA = new TreeItem<String>("sdfs");
		treeItemRoot1.getChildren().addAll(nodeItemA);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setMenuTree();
		
	}

}
