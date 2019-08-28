package kr.or.ddit.clientMain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.util.MethodUtil;
import javafx.scene.image.ImageView;

public class MainTopViewController implements Initializable {
	public static BorderPane topPane;
	public static BorderPane mainView;

	private Stage primaryStage;
	@FXML ImageView homeBtnClick;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public MainTopViewController getClientMainController() {
		return this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeBtnClick.setOnMouseClicked(e -> {
			changeScene("home.fxml");
		});
	}

	@FXML
	public void homeBtnClick(ActionEvent event) {
		changeScene("home.fxml");
	}

	@FXML
	public void myReportBtnClick() {
		if (LoginSession.memSession == null) {
			MethodUtil.alertShow("WARNING", "입장불가!", "로그인을 해주세요");
		} else if (LoginSession.memSession.getMem_grade().equals("골드")) {
			changeScene("../view/myReport/myReportMain.fxml");
		} else {
			MethodUtil.alertShow("INFORMATION", "입장불가!", "회원권을 구매해주세요");
		}
	}

	@FXML
	public void communityBtnClick() {
		changeScene("../view/board/CommunityMain.fxml");
	}

	@FXML
	public void memberShipBtnClick() {
		changeScene("../view/buyMembership/BuyMemMain.fxml");
	}

	@FXML
	public void applyTrainerBtnClick() {
		changeScene("../view/applyTrainer/ApplyTrainerMain.fxml");
	}

	@FXML
	public void homeTRBtnClick() {
		if (LoginSession.memSession == null) {
			MethodUtil.alertShow("로그인", "로그인", "로그인 하셔야합니다!!");
			changeScene("home.fxml");
		} else {
			changeScene("../view/homeTraining/HomeTrainingMain.fxml");
		}
	}

	@FXML
	public void magazineBtnClick() {
		changeScene("../view/magazine/recommend/recommendRecipe.fxml");
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

	/**
	 * 파일의 경로를 넣으면 탑에 넣어주고 로더를 반환하는 메서드
	 * 
	 * @param fxmlURL
	 * @return loader
	 */
	public FXMLLoader topChangeScene(String fxmlURL) {
		topPane.setTop(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		topPane.setTop(parent);
		return loader;
	}

	public FXMLLoader leftChangeScene(String fxmlURL) {
		mainView.setLeft(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainView.setLeft(parent);
		return loader;
	}

	/**
	 * dialog 창 띄우기
	 * 
	 * @param fxml
	 */
	public void modalDialog(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			 stage.initOwner(primaryStage);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void caloryBtnClick(ActionEvent event) {
		changeScene("../view/kcal/FoodKcal.fxml");
	}

}
