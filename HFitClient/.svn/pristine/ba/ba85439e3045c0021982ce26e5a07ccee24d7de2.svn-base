package kr.or.ddit.clientMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ClientMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent parent = FXMLLoader.load(getClass().getResource("mainView.fxml"));
		Scene scene = new Scene(parent);
		
//		String css = ClientMain.class.getResource("style.css").toExternalForm();
//		scene.getStylesheets().add(getClass().getResource("style.css").toString());
		
		LoginViewController loginView = new LoginViewController();
		loginView.setPrimaryStage(primaryStage);

		MainTopViewController mainTopView = new MainTopViewController();
		mainTopView.setPrimaryStage(primaryStage);
		
		primaryStage.setTitle("H&F - HEALTH AND FIT");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
