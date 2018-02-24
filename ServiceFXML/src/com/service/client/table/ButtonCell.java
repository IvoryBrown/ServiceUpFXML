package com.service.client.table;

import com.service.client.Client;
import com.service.client.SubRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ButtonCell extends TableCell<Client, Boolean> {
	final Button cellButton = new Button("+");
	final Button exitButton = new Button("-");
	TableView<SubRecord> subTableView;

	ButtonCell(final TableView<Client> tblView) {

		cellButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				int selectdIndex = getTableRow().getIndex();

				Client selectedRecord = (Client) tblView.getItems().get(selectdIndex);
				ObservableList<SubRecord> subDataList = FXCollections.observableArrayList(
						new SubRecord("ID", selectedRecord.getClientId()),
						new SubRecord("Azonosító", selectedRecord.getClientNumber()),
						new SubRecord("Cégnév", selectedRecord.getClientCompanyName()),
						new SubRecord("Ügyfél név", selectedRecord.getClientName()),
						new SubRecord("Megye", selectedRecord.getClientCounty()),
						new SubRecord("Irányítószám", selectedRecord.getClientZipCode()),
						new SubRecord("Település", selectedRecord.getClientSettlement()),
						new SubRecord("Cím", selectedRecord.getClientAddress()),
						new SubRecord("Cég telefon", selectedRecord.getClientCompanyPhone()),
						new SubRecord("Cég email", selectedRecord.getClientCompanyEmail()),
						new SubRecord("Telefon", selectedRecord.getClientPhone()),
						new SubRecord("Email", selectedRecord.getClientEmail()),
						new SubRecord("Csomag", selectedRecord.getClientPackage()),
						new SubRecord("Megjegyzés", selectedRecord.getClientComment()));

				TableColumn columnfield = new TableColumn<>("Field");
				columnfield.setCellValueFactory(new PropertyValueFactory<Client, String>("fieldSubRecordName"));
				columnfield.setPrefWidth(100);

				TableColumn columnValue = new TableColumn<>("Value");
				columnValue.setCellValueFactory(new PropertyValueFactory<SubRecord, Integer>("fieldSubRecordValue"));
				columnValue.setPrefWidth(850);
				
				subTableView = new TableView<>();
				subTableView.setItems(subDataList);
				subTableView.getColumns().addAll(columnfield, columnValue);

				try {
					StackPane root = new StackPane();
					root.getChildren().add(subTableView);
					root.getChildren().add(exitButton);
					Stage stage = new Stage();
					root.getStylesheets().add(getClass().getClassLoader()
							.getResource("com/service/setting/desing/desing.css").toExternalForm());
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("Eszköz");
					stage.setScene(new Scene(root, 950, 450));
					stage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void updateItem(Boolean t, boolean empty) {
		super.updateItem(t, empty);
		if (!empty) {
			setGraphic(cellButton);
		}
	}
}
