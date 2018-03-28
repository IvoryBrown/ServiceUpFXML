package com.service.client.table;

import com.service.client.Client;
import com.service.client.SubRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ButtonCell extends TableCell<Client, Boolean> {
	final Button cellButton = new Button("+");
	@FXML
	private TableView<SubRecord> subTableView;

	ButtonCell(final TableView<Client> tblView) {

		cellButton.setOnAction(new EventHandler<ActionEvent>() {

			@SuppressWarnings("unchecked")
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

				@SuppressWarnings("rawtypes")
				TableColumn columnfield = new TableColumn<>(" ");
				columnfield.setCellValueFactory(new PropertyValueFactory<Client, String>("fieldSubRecordName"));
				columnfield.setStyle("-fx-background-color: #2A5058 ");
				columnfield.setMinWidth(100);
				columnfield.setMaxWidth(100);

				@SuppressWarnings("rawtypes")
				TableColumn columnValue = new TableColumn<>("Ügyfél");
				columnValue.setCellValueFactory(new PropertyValueFactory<SubRecord, String>("fieldSubRecordValue"));
				columnValue.setMinWidth(845);

				subTableView = new TableView<>();
				subTableView.setStyle("-fx-text-background-color: whitesmoke;");
				subTableView.setItems(subDataList);
				subTableView.getColumns().addAll(columnfield, columnValue);

				try {
					StackPane root = new StackPane();
					root.getChildren().add(subTableView);
					Stage stage = new Stage();
					stage.setResizable(false);
					root.getStylesheets().add(getClass().getClassLoader()
							.getResource("com/service/setting/desing/desing.css").toExternalForm());
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("Ügyfél");
					stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/service/setting/desing/icon-it.png")));
					root.prefWidthProperty().bind(stage.widthProperty());
					stage.setScene(new Scene(root, 950, 430));
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
		}else {
			 setGraphic( null );
             setText( null );
		}
	}
}
