package kr.or.ddit.view.hfShop;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.clientMain.ClientMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class HFShopMainController implements Initializable {

	@FXML AnchorPane shopView;
	@FXML ImageView homeBtnClick;
	public static BorderPane topPane;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeBtnClick.setOnMouseClicked(e->{
			
			if(LoginSession.memSession != null) {
				if(LoginSession.memSession.getMem_id().equals("root")) {
					changeScene("../../clientMain/home.fxml");
					topChangeScene("../admin/AdminMain.fxml");
				}
				else {
					changeScene("../../clientMain/home.fxml");
					topChangeScene("../../clientMain/MainTopView.fxml");
				}
			}
			
			if(LoginSession.trainerSession != null) {
				changeScene("../../clientMain/home.fxml");
				topChangeScene("../trainer/TrainerMain.fxml");
			}
			
			
			
			
		});
		
		changeScene("HFShopHome.fxml");
	}

	@FXML
	public void homeBtnClick(ActionEvent event) {
		Parent parent = null;
		Stage stage = null;
		try {
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			parent = FXMLLoader.load(getClass().getResource("../../clientMain/mainView.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (LoginSession.memSession != null || LoginSession.trainerSession != null) {
			
			((BorderPane)parent.getChildrenUnmodifiable().get(1)).setTop(null);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../login/LoginSuccess.fxml"));
			Parent parent2 = null;
			try {
				parent2 = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			((BorderPane)parent.getChildrenUnmodifiable().get(1)).setTop(parent2);
		}
		Scene scene = new Scene(parent);
		stage.setScene(scene);
	}

	@FXML 
	public void AllProdBtnClick(ActionEvent event) {
		changeScene("../prod/ProdList.fxml");
	}

	@FXML public void FoodProdBtnClick() {
		changeScene("../prod/FoodProdView.fxml");
	}

	@FXML public void HealthProdBtnClick() {
		changeScene("../prod/HealthProdView.fxml");
	}

	@FXML public void DealHistroyBtnClick() {
		changeScene("../cart/OrderResultView.fxml");
	}

	@FXML public void CartListBtnClick() {
		changeScene("../cart/CartListView.fxml");
	}

	
	/**
	 * 파일의 경로를 넣으면 센터에 넣어주고 로더를 반환하는 메서드
	 * 
	 * @param fxmlURL
	 * @return loader
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		ClientMainController.pane.setCenter(null);
		Parent parent = null;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		((BorderPane)shopView.getParent()).setCenter(parent);
		ClientMainController.pane.setCenter(parent);
		return loader;
	}
	
	public FXMLLoader topChangeScene(String fxmlURL) {
		topPane.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		topPane.setCenter(parent);
		return loader;
	}

	
	
}
