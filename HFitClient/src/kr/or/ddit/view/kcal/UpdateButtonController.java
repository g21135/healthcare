package kr.or.ddit.view.kcal;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.text.LabelView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.service.kcal.IKcalService;
import kr.or.ddit.vo.KcalVO;

public class UpdateButtonController implements Initializable {
	@FXML private Pane updatepana; // 메인 페인
	
	@FXML private TextField exName; // 전 이름
	@FXML private TextField exKcal; // 전 칼로리
	@FXML private TextField chName; // 바뀐 이름
	@FXML private TextField chKcal; // 바뀐 칼로리
	@FXML private TextField secretNum; // 숨긴 필드
	
	@FXML private Button updateBtn; // 수정 버튼
	@FXML private Button returnBack; // 뒤로가기
	
	private IKcalService kcalService;
	private Registry Reg;
	public static BorderPane mainView;
	public static String name;
	public static int calory;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("update넘어옴.");
		exName.setText(name);
		try {
			Reg = LocateRegistry.getRegistry(8888);
			kcalService = (IKcalService) Reg.lookup("kcal");
		}catch(RemoteException e) {
			e.printStackTrace();
		}catch(NotBoundException e) {
			e.printStackTrace();
		}
//		System.out.println(name);
		exKcal.setText(String.valueOf(calory));
		exName.setEditable(false);
		exKcal.setEditable(false);
//		secretNum.setVisible(false); // 숨기기
	}
	
	@FXML public void upDateBtn(ActionEvent event) { // 수정 버튼
		System.out.println("수정버튼 클릭");
		// 입력 받은 값을 가져옴.
		String x = chName.getText();
		int y = Integer.parseInt(chKcal.getText()); // int 형변환
//		int z = Integer.parseInt(secretNum.getText());
		
		if(x == null) {
			infoMsg("잘못된 방식입니다.", "수정할 이름을 확인해 주세요.");
			return;
		}else if(y == 0) {
			infoMsg("잘못된 방식입니다.", "칼로리 숫자를 확인해 주세요.");
			return;
		}
		
		KcalVO kv = new KcalVO();
		kv.setKcal_name(x);
		kv.setKcal_calory(y);
//		kv.setKcal_num(z);
		
		try {
			int cnt = kcalService.updateFoodKcal(kv);
			if(cnt > 0) {
				System.out.println("정상");
			}else {
				System.out.println("비정상");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		infoMsg("수정 결과",exName.getText() + "이(가) " + chName.getText() + "로 수정되었습니다.");
		
		//입력 완료했으니 돌아가야지?
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("FoodKcal.fxml"));
//		
//		Parent parent = null;
//		try {
//			parent = loader.load();					
//		}catch(IOException e){
//			e.printStackTrace();
//		}
		
//		Pane rootPane = ((Pane)(exName.getScene().getRoot()));
//		
//		rootPane.getChildren().clear();
//		rootPane.getChildren().addAll(((Pane)parent).getChildren());
		changeScene("FoodKcal.fxml");
	}
	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("수정 결과");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
	@FXML public void retrunBtn(ActionEvent event){ // 뒤로가기
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FoodKcal.fxml"));
		
		Parent parent = null;
		try {
			parent = loader.load();					
		}catch(IOException e){
			e.printStackTrace();
		}
		mainView.setCenter(null);
		mainView.setCenter(parent);			
	}
	@FXML public void KcalMenu() {}
	
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
