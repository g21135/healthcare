package kr.or.ddit.view.login;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import api.Mail;
import javafx.fxml.Initializable;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.MemberVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PassFind2Controller implements Initializable{

	@FXML AnchorPane passFind2View;
	@FXML JFXTextField id;
	@FXML JFXTextField pass;
	@FXML JFXTextField pass2;
	
	Registry reg;
	IMemberService member;
	
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(8888);
			member = (IMemberService) reg.lookup("member");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML public void okBtnClick() {
		MemberVO mv = new MemberVO();
//		if(id.getText() == mv.getMem_id() && pass.getText() == mv.getMem_pass()) {
			mv.setMem_id(id.getText());
			mv.setMem_pass(pass2.getText());
//		}
		
		try {
			int cnt = member.updatePass(mv);
			
			if(cnt > 0) {
				MethodUtil.alertShow("INFORMATION", "알림", "비밀번호가 성공적으로 변경되었습니다.");
			}else {
				MethodUtil.alertShow("INFORMATION", "알림", "비밀번호 변경해 실패하였습니다.");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		Stage primaryStage = (Stage) passFind2View.getScene().getWindow();
		primaryStage.close();
		
	}
	
	@FXML public void cancelBtnClick() {
		Stage primaryStage = (Stage) passFind2View.getScene().getWindow();
		primaryStage.close();
	}
	

}
