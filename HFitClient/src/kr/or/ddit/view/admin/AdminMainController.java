package kr.or.ddit.view.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import kr.or.ddit.clientMain.ClientMainController;

public class AdminMainController implements Initializable {

	@FXML ImageView homeBtnClick;
	public static BorderPane mainView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeBtnClick.setOnMouseClicked(e->{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminMain.fxml"));
			Parent parent = null;
			try {
				parent = loader.load();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ClientMainController.topPane.setCenter(parent);
			
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../../clientMain/home.fxml"));
			Parent parent2 = null;
			try {
				parent2 = loader2.load();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ClientMainController.pane.setCenter(parent2);
			
		});
	}

	@FXML public void operationBtnClick(ActionEvent event) {
		changeScene("topView.fxml");
	}

	@FXML public void homeTRBtnClick(ActionEvent event) {
		changeScene("../homeTraining/HomeTrainingMain.fxml");
	}

	@FXML public void magazineBtnClick(ActionEvent event) {
		changeScene("../magazine/recommend/recommendRecipe.fxml");
	}

	@FXML public void communityBtnClick(ActionEvent event) {
		changeScene("../board/CommunityMain.fxml");
	}
	
	public FXMLLoader changeScene(String fxmlURL) {
		ClientMainController.pane.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClientMainController.pane.setCenter(parent);
		return loader;
	}

	@FXML public void homeBtnClick(ActionEvent event) {}

	@FXML public void caloryBtnClick(ActionEvent event) {
		changeScene("../kcal/FoodKcal.fxml");
	}
}
