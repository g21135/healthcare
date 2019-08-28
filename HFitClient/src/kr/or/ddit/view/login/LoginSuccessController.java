package kr.or.ddit.view.login;

import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.ddit.LoginSession.IClientList;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.clientMain.ClientMainController;

import com.jfoenix.controls.JFXButton;

public class LoginSuccessController implements Initializable {

	@FXML JFXButton logout;
	public static BorderPane topPane;
	
	IClientList icl; 
	Registry reg;
	@FXML JFXButton user_Id;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(LoginSession.memSession != null) {
			user_Id.setText(LoginSession.memSession.getMem_id());
			if(LoginSession.memSession.getMem_id().equals("root")) {
				user_Id.setText("관리자");
			}
		}
		if(LoginSession.trainerSession != null) {
			user_Id.setText(LoginSession.trainerSession.getTrainer_id());
		}
		
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
	public void logoutBtnClick(ActionEvent event) {
		
		try {
			if (LoginSession.memSession != null && LoginSession.trainerSession == null) {
				icl.removeMemberSession(LoginSession.memSession);
			} else if (LoginSession.memSession == null && LoginSession.trainerSession != null) {
				icl.removeTrainerSession(LoginSession.trainerSession);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			
			Parent parent = FXMLLoader.load(getClass().getResource("../../clientMain/mainView.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);

		} catch (IOException e) {
			e.printStackTrace();
		}
		LoginSession.memSession = null;
		LoginSession.trainerSession = null;
		
		
		

	}

	@FXML
	public void infoBtnClick(ActionEvent event) {
		
		BorderPane border =  (BorderPane) ((Node)event.getSource()).getScene().getRoot(); 
		border.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../myInfo/MyInfo.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		border.setCenter(parent);
		
		
	}

	@FXML public void noticeBtnClick(ActionEvent event) {
		
		BorderPane border =  (BorderPane) ((Node)event.getSource()).getScene().getRoot(); 
		border.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../board/NoticeMain.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		border.setCenter(parent);
		
	}

	@FXML public void hpShopBtnClick(ActionEvent event) {
		
		topPane.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../hfShop/HFShopMain.fxml"));
		Parent parent = null;
		
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		topPane.setCenter(parent);
	}
	
}
