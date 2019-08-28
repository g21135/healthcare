package kr.or.ddit.clientMain;

import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.plaf.synth.SynthSpinnerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.ddit.LoginSession.IClientList;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.vo.MemberVO;

public class LoginViewController implements Initializable {
	public static BorderPane topPane;
	public static BorderPane mainView;
	
	private Stage primaryStage;
	@FXML ImageView homeBtnClick;
	
	IClientList icl; 
	Registry reg;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public LoginViewController getClientMainController() {
		return this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost",8888);
			icl = (IClientList) reg.lookup("host");
			
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void loginBtnClick() {
		modalDialog("../view/login/LoginMain.fxml");
		
		try {
			if (LoginSession.memSession != null && LoginSession.trainerSession == null) {
				if(LoginSession.memSession.getMem_id().equals("root")) {
					centerChangeScene("../view/admin/adminMain.fxml");
					topChangeScene("../view/login/LoginSuccess.fxml");
					icl.addMemberSession(LoginSession.memSession);
				}else {
					topChangeScene("../view/login/LoginSuccess.fxml");
					icl.addMemberSession(LoginSession.memSession);
				}	
			}
			else if (LoginSession.memSession == null && LoginSession.trainerSession != null) {
					centerChangeScene("../view/trainer/TrainerMain.fxml");
					topChangeScene("../view/login/LoginSuccess.fxml");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void joinBtnClick() {
		changeScene("../view/join/JoinMain.fxml");
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
	
	public FXMLLoader centerChangeScene(String fxmlURL) {
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
}
