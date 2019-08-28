package kr.or.ddit.view.kcal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kr.or.ddit.clientMain.ClientMainController;

public class KcalMain extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent parent = FXMLLoader.load(getClass().getResource("FoodKcal.fxml"));
		Scene scene = new Scene(parent);
		
	primaryStage.setTitle("KcalDictionary");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {		
		launch(args);
	}
}
