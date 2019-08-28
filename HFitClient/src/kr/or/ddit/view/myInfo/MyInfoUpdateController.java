package kr.or.ddit.view.myInfo;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.MemberVO;

public class MyInfoUpdateController implements Initializable{
	
	@FXML BorderPane infoUpdateView;
	@FXML TextField id;
	@FXML TextField pass;
	@FXML TextField pass2;
	@FXML TextField name;
	@FXML TextField birth;
	@FXML TextField tel;
	@FXML TextField email;
	@FXML TextField addr;
	@FXML FontAwesomeIconView check;
	
	Parent parent;
	Registry reg;
	IMemberService member;
	int cnt;
	
	MemberVO mv = LoginSession.memSession;
	String ss = mv.getMem_id();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			member = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		

//		mv.setMem_id(ss);
		
		id.setText(mv.getMem_id());
		pass.setText(mv.getMem_pass());
		pass2.setText(mv.getMem_pass());
		name.setText(mv.getMem_name());
		birth.setText(mv.getMem_bir());
		tel.setText(mv.getMem_tel());
		email.setText(mv.getMem_email());
		addr.setText(mv.getMem_addr());

		
		id.setDisable(true);
		
	}
	
	@FXML public void okBtnClick() {
		//int cnt = 0;
		
		MemberVO mv = new MemberVO();
		mv.setMem_id(ss);
		mv.setMem_pass(pass.getText());
		mv.setMem_name(name.getText());
		mv.setMem_bir(birth.getText());
		mv.setMem_tel(tel.getText());
		mv.setMem_email(email.getText());
		mv.setMem_addr(addr.getText());
		
		if (pass.getText().length() < 1 || pass2.getText().length() < 1
				|| name.getText().length() < 1 || birth.getText().length() < 1
				|| tel.getText().length() < 1 || email.getText().length() < 1 || addr.getText().length() < 1) {
			MethodUtil.alertShow("INFORMATION", "등록실패", "빈 항목이 존재합니다.");
		} else if (!pass.getText().equals(pass2.getText())) {
			MethodUtil.alertShow("INFORMATION", "등록실패", "비밀번호가 일치하지 않습니다.");
		} 
		
		try {
			cnt = member.updateMember(mv);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		if (cnt > 0) {
			MethodUtil.alertShow("INFORMATION", "성공", "내 정보가 수정 되었습니다.");
			id.setText(mv.getMem_id());
			pass.setText(mv.getMem_pass());
			pass2.setText(mv.getMem_pass());
			name.setText(mv.getMem_name());
			birth.setText(mv.getMem_bir());
			tel.setText(mv.getMem_tel());
			email.setText(mv.getMem_email());
			addr.setText(mv.getMem_addr());
			
			

			/*id.setDisable(true);*/
//			infoUpdateView.setTop(null);
//			infoUpdateView.setCenter(null);
		}else {
			MethodUtil.alertShow("INFORMATION", "등록실패", "오류로 인하여 수정이 되지 않았습니다.");
			return;
		}
	}
	
	@FXML public void cancelBtnClick() {

		changeScene("MyInfo.fxml");
	}
	
	
	public FXMLLoader changeScene(String fxmlURL) {
		infoUpdateView.setTop(null);
		infoUpdateView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		infoUpdateView.setCenter(parent);
		return loader;
	}
	
	
}

















