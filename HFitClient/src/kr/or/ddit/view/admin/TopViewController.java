package kr.or.ddit.view.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import kr.or.ddit.view.hope.UserHopeMain;

public class TopViewController implements Initializable{

	@FXML BorderPane mainView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		UserHopeMain.mainView = mainView;
		memberListMainController.mainView = mainView;
		trainerListMainController.mainView = mainView;
		trainerMainController.mainView = mainView;
		
		
		mainView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("memberMain.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainView.setCenter(parent);
		
		
	}
	
	
	@FXML
	public void memberBtnClick(ActionEvent event) {
		changeScene("memberMain.fxml");
	}

	@FXML
	public void trainerBtnClick(ActionEvent event) {
		changeScene("trainerMain.fxml");
	}

	@FXML
	public void matchingBtnClick(ActionEvent event) {
		changeScene("matchingMain.fxml");
	}

	@FXML
	public void saleBtnClick(ActionEvent event) {
		
		changeScene("Paymagerment.fxml");
	}

	@FXML
	public void prodBtnClick(ActionEvent event) {
		changeScene("Prodmanegerment.fxml");
	}
	
	public FXMLLoader changeScene(String fxmlURL) {
		mainView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainView.setCenter(parent);
		return loader;
	}
}
