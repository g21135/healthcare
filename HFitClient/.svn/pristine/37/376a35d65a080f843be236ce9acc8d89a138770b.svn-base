package kr.or.ddit.view.login;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

/*import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/

import com.jfoenix.controls.JFXTextField;

import api.Mail;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.MemberVO;

public class PassFindController implements Initializable{

	@FXML AnchorPane passFindView;
	@FXML JFXTextField email;
	@FXML JFXTextField name;
	@FXML JFXTextField id;
	
	Registry reg;
	IMemberService member;
	Mail mail = new Mail();
	
	private Stage primaryStage;
	public static String newpass;
	 public static String pw;

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
	
	@FXML
	public void okBtnClick() {
		 if (id.getText().equals("")||name.getText().equals("") || email.getText().equals("")
				 || name.getText() == null || email.getText() == null) {
			 MethodUtil.alertShow("INFORMATION", "알림", "입력하지 않은 정보가 있습니다.");
             return;
		 }
		 
		ArrayList<MemberVO> list = new ArrayList<>();
		MemberVO mv = new MemberVO();
		mv.setMem_id(id.getText());
		mv.setMem_name(name.getText());
		mv.setMem_email(email.getText());
		
		try {
			list = (ArrayList<MemberVO>) member.searchPass(mv);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(list.size() > 0) {
			MethodUtil.alertShow("INFORMATION", "알림", "입력하신 메일로 임시 비밀번호를 전달하였습니다.");
			
			for (int i = 0; i < 12; i++) {
				   pw += (char) ((Math.random() * 26) + 97);
			}
			
			/**
			 * mem_id : 받는사람 메일주소
			 * code : 아무거나 
			 * type : 
			 */
			mail.sendMail(mv.getMem_email(), pw,"", "forgotPW");
			
//			System.out.println(pw);
				
				mv.setMem_id(id.getText());
				mv.setMem_pass(pw);
				try {
					int cnt = member.updatePass(mv);
					System.out.println(cnt);
					System.out.println(mv.getMem_pass());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("passFind2.fxml"));
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
			
		}else {
			MethodUtil.alertShow("INFORMATION", "알림", "해당하는 ID가 존재하지 않습니다.");
		}
		
			Stage primaryStage = (Stage) passFindView.getScene().getWindow();
			primaryStage.close();
		
			
	}
	
	
	@FXML
	public void cancelBtnClick() {
		Stage primaryStage = (Stage) passFindView.getScene().getWindow();
		primaryStage.close();
	}
}
