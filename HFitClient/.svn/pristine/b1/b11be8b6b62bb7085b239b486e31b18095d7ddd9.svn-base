package kr.or.ddit.view.cart;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.card.ICardService;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.vo.MemberVO;

public class CardController implements Initializable {

	public static String cardName;
	public static int price;
			
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	private void infoMsg(String headerText, String msg) {
	      Alert infoAlert = new Alert(AlertType.INFORMATION);
	      infoAlert.setTitle("알림");
	      infoAlert.setHeaderText(headerText);
	      infoAlert.setContentText(msg);
	      infoAlert.showAndWait();
	   }

	   private void errMsg(String headerText, String msg) {
	      Alert errAlert = new Alert(AlertType.ERROR);
	      errAlert.setTitle("오류");
	      errAlert.setHeaderText(headerText);
	      errAlert.setContentText(msg);
	      errAlert.showAndWait();

	   }


	
	

}
