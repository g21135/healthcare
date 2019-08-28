package kr.or.ddit.view.login;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.MemberVO;

public class IdFindController implements Initializable{
	
	@FXML AnchorPane idFindView;
	@FXML JFXTextField email;
	@FXML JFXTextField name;
	
	Registry reg;
	IMemberService member;

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
	
	@FXML
	public void okBtnClick() {
		 if (name.getText().equals("") || email.getText().equals("")
				 || name.getText() == null || email.getText() == null) {
			 MethodUtil.alertShow("INFORMATION", "알림", "입력하지 않은 정보가 있습니다.");
             return;
		 }
		 
		ArrayList<MemberVO> list = new ArrayList<>();
		MemberVO mv = new MemberVO();
		mv.setMem_name(name.getText());
		mv.setMem_email(email.getText());
		
		try {
			list = (ArrayList<MemberVO>) member.searchId(mv);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(list.size() > 0) {
			MethodUtil.alertShow("INFORMATION", "입력하신 정보에 일치하는 아이디입니다.", "아이디 : " + list.get(0).getMem_id());
			Stage primaryStage = (Stage) idFindView.getScene().getWindow();
			primaryStage.close();
		}else {
			MethodUtil.alertShow("INFORMATION", "알림", "해당하는 ID가 존재하지 않습니다.");
		}
			
	}
	
	
	@FXML
	public void cancelBtnClick() {
		Stage primaryStage = (Stage) idFindView.getScene().getWindow();
		primaryStage.close();
	}

}









