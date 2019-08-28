package kr.or.ddit.clientMain;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.service.post.IPostService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.view.admin.applyListController;
import kr.or.ddit.view.board.FreeMainController;
import kr.or.ddit.view.board.FreePostWriteController;
import kr.or.ddit.view.board.NoticeMainController;
import kr.or.ddit.view.board.NoticePostWriteController;
import kr.or.ddit.view.board.challenge.ChallengeMainController;
import kr.or.ddit.view.hfShop.HFShopMainController;
import kr.or.ddit.view.homeTraining.HomeTrainingMainController;
import kr.or.ddit.view.kcal.InsertButtonController;
import kr.or.ddit.view.kcal.KcalController;
import kr.or.ddit.view.kcal.UpdateButtonController;
import kr.or.ddit.view.login.LoginSuccessController;
import kr.or.ddit.view.login.loginMainController;
import kr.or.ddit.view.post.BoardPostController;
import kr.or.ddit.view.post.NoticePostController;
import kr.or.ddit.view.trainer.TrainerMainController;
import kr.or.ddit.vo.PostVO;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;

public class ClientMainController implements Initializable {
	
	/*public static TableView<PostVO> table;
	public static TableColumn<PostVO, Integer> number;
	public static TableColumn<PostVO, String> tit;
	public static TableColumn<PostVO, String> nm;
	public static TableColumn<PostVO, Date> dt;*/

	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/*public ClientMainController getClientMainController() {
		return this;
	}*/

	public static BorderPane pane;
	public static BorderPane topPane;
	@FXML
	JFXButton loginBtn;
	@FXML
	JFXButton join;
	@FXML
	BorderPane mainView;
	@FXML
	BorderPane topMenu;
	@FXML ImageView homeBtnClick;
	
	Registry reg;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pane = mainView;
		topPane = topMenu;
		
		HFShopMainController.topPane = topMenu;
		LoginSuccessController.topPane = topMenu;
		
		MainTopViewController.topPane = topMenu;
		MainTopViewController.mainView = mainView;
		LoginViewController.mainView = mainView;
		LoginViewController.topPane = topMenu;
		applyListController.mainView = mainView;
		TrainerMainController.mainView = mainView;
		KcalController.mainView = mainView;
		InsertButtonController.mainView = mainView;
		UpdateButtonController.mainView = mainView;
		loginMainController.mainView = mainView;
		FreeMainController.mainView = mainView;
		ChallengeMainController.mainView = mainView;
		BoardPostController.mainView = mainView;
		HomeTrainingMainController.mainView = mainView;
		NoticePostWriteController.mainView = mainView;
		NoticeMainController.mainView = mainView;
		NoticePostController.mainView = mainView;
		FreePostWriteController.mainView = mainView;
		
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("LoginView.fxml"));
		Parent parent2 = null;
		try {
			parent2 = loader2.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		topMenu.setTop(parent2);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainTopView.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		topMenu.setCenter(parent);
		
		FXMLLoader loader3 = new FXMLLoader(getClass().getResource("home.fxml"));
		Parent parent3 = null;
		try {
			parent3 = loader3.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		mainView.setCenter(parent3);
			
		
		
		
		
		
		
		
	}
		
	@FXML public void hfBtnClick(ActionEvent event) {}

	@FXML public void noticeBtnClick(ActionEvent event) {}
	
	
}
		
		
		
		/*topMenu.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainTopView.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		topMenu.setCenter(parent);*/


	/**
	 * 파일의 경로를 넣으면 센터에 넣어주고 로더를 반환하는 메서드
	 * 
	 * @param fxmlURL
	 * @return loader
	 */
	/*public FXMLLoader changeScene(String fxmlURL) {
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

	*//**
	 * 파일의 경로를 넣으면 탑에 넣어주고 로더를 반환하는 메서드
	 * 
	 * @param fxmlURL
	 * @return loader
	 *//*
	public FXMLLoader topChangeScene(String fxmlURL) {
		topMenu.setTop(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		topMenu.setTop(parent);
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

	*//**
	 * dialog 창 띄우기
	 * 
	 * @param fxml
	 *//*
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
	}*/

