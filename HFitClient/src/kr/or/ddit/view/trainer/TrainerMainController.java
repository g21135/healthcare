package kr.or.ddit.view.trainer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;

public class TrainerMainController implements Initializable {

	@FXML AnchorPane trainerView;
	@FXML ImageView homeBtnClick;
	public static BorderPane mainView;

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeBtnClick.setOnMouseClicked(e->{
			changeScene("../../clientMain/home.fxml");
		});
		
		
	}

	
	
	/*@FXML public void TrainerReport(ActionEvent event) {
		
	}*/

	/**
	 * 파일의 경로를 넣으면 센터에 넣어주고 로더를 반환하는 메서드
	 * 
	 * @param fxmlURL
	 * @return loader
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		Parent parent = null;
//		((BorderPane) trainerView.getParent()).setCenter(null);
		mainView.setCenter(null);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		((BorderPane)trainerView.getParent()).setCenter(parent);
		mainView.setCenter(parent);
		return loader;
	}

	@FXML public void trainerReportBtnClick(ActionEvent event) {
		changeScene("TrainerReport.fxml");
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



	@FXML public void caloryBtnClick(ActionEvent event) {
		changeScene("../kcal/FoodKcal.fxml");
	}
}
