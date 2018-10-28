package xxx;

import com.device.actual.main.DeviceActualMain;
import com.device.newmain.DeviceNewMain;

import javafx.application.Application;
import javafx.stage.Stage;

public class Test extends Application { 
	public static Stage primaryStageStatisticsMain;
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage primaryStage) {
		this.primaryStageStatisticsMain = primaryStage;
		try {

		
			DeviceNewMain device =new DeviceNewMain();
			device.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
