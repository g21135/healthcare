package kr.or.ddit.view.login;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.service.trainer.ITrainerService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.TrainerVO;

public class loginMainController implements Initializable {

	IMemberService mem;
	ITrainerService trainer;
	Registry reg;

	@FXML
	JFXTextField textId;
	@FXML
	JFXPasswordField textPass;
	@FXML 
	JFXButton loginBtn;
	public static  BorderPane mainView;
	
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			mem = (IMemberService) reg.lookup("member");
			trainer = (ITrainerService) reg.lookup("trainer");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		textId.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					loginBtnClick();
				}
			}
		});
		textPass.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        if(event.getCode().equals(KeyCode.ENTER)) {
		        	loginBtnClick();
		        }
		    }
		});
	}
	
	@FXML
	public void searchIdBtnClick() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("IdFind.fxml"));
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
	
	@FXML
	public void searchPassBtnClick() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("passFind.fxml"));
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

	@FXML
	public void joinBtnClick() {
		
	}

	@FXML
	public void loginBtnClick() {
		MemberVO member;
		TrainerVO tr;
		try {
			member = mem.login(textId.getText(), textPass.getText());
			tr = trainer.login(textId.getText(), textPass.getText());
			
			Alert alertInformation = null;
			alertInformation = new Alert(AlertType.INFORMATION); // 아이콘 지정
			
			if (member == null && tr == null) {
				MethodUtil.alertShow("INFORMATION","로그인 실패!", "아이디 또는 비밀번호가 맞지 않습니다.");
			}
			else if(member != null && tr == null){
				LoginSession.memSession = member;
				
				if(member.getMem_id().equals("root")) {
					MethodUtil.alertShow("INFORMATION","관리자 로그인 성공!","환영합니다");
				}else{
					MethodUtil.alertShow("INFORMATION","일반회원 로그인 성공!","환영합니다");
				}
			}
			else if(member == null && tr != null) {
				LoginSession.trainerSession= tr;
				MethodUtil.alertShow("INFORMATION","트레이너회원 로그인 성공!","환영합니다");
			}
			
			Stage primaryStage = (Stage) loginBtn.getScene().getWindow(); 
			primaryStage.close();
			
			changeScene("../../clientMain/home.fxml");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
