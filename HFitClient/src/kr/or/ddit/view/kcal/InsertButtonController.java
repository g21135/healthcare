package kr.or.ddit.view.kcal;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import kr.or.ddit.service.kcal.IKcalService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.KcalVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class InsertButtonController implements Initializable {
	
	@FXML private BorderPane InsertPane;
	
	@FXML private ComboBox<String> KcalMenu;//
	@FXML private TextField MenuName; // 추가되는 음식, 운동 이름
	@FXML private TextField KcalName; // 추가되는 음식, 운동 칼로리
	
	
	@FXML private JFXButton InsertButton; // 추가 버튼
	@FXML private JFXButton BreakButton; // 취소 버튼
	
	private IKcalService kcalService;
	private Registry Reg; // 레지를 사용해야-
	public static BorderPane mainView;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("추가로 넘어왔다.");
		try {
			Reg = LocateRegistry.getRegistry(8888);
			kcalService = (IKcalService) Reg.lookup("kcal");
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		ObservableList<String> list = FXCollections.observableArrayList(
				"음식", "운동"
				);
		KcalMenu.setItems(list);		
	}
	
	
	/**
	 * 추가 버튼
	 * @param event
	 */
	@FXML public void InsertButton(ActionEvent event) {
		//System.out.println("1. 추가버튼 클릭");
		int cnt = 0;
		
		String ser = KcalMenu.getValue();
		System.out.println("ser = "+ser);//================================================================
		//MenuName 의 값이 null 일때.
		
		int DCode = 0;
		if(ser == null) {
			infoMsg("잘못된 입력입니다.", "운동, 음식을 선택해 주세요.");
			return;
		}else if (ser.equals("음식")) {
			DCode = 1;
		} else if (ser.equals("운동")) {
			DCode = 2;
		}
		
		String regExp1 = "^[a-zA-Z]*$";
		String regExp2 = "^[0-9]*$";
		
		//String name = MenuName.getText();
		
		if(MenuName.getText().equals("") || MenuName.getText() == regExp2) {
			infoMsg("잘못된 입력입니다.", ser + " 이름칸을 확인해 주세요!");
			return;
		}else if(KcalName.getText().matches(regExp1)) {
			System.out.println(MenuName.getText());
			infoMsg("잘못된 입력입니다.", "칼로리 값은 숫자만 허용 됩니다.");
		}
		//=======================================================
		String KName = MenuName.getText(); // 입력된 박스 안의 값을  가져옴.
		int KCalory = Integer.parseInt(KcalName.getText()); // int값 가져오기(칼로리 값)
		
		
		
		KcalVO kv = new KcalVO();
		kv.setDiv_code(DCode);
		kv.setKcal_name(KName);
		kv.setKcal_calory(KCalory);
		
		try {			
			cnt = kcalService.insertFoodKcal(kv);
		} catch (RemoteException e) {
			System.out.println("InsertButtonController에서 에러!");
			e.printStackTrace();
		}
		
		if(cnt>0) {
			if(DCode == 1) {
				System.out.println("음식 입력 성공");
			}else if(DCode == 2) {
				System.out.println("운동 입력 성공");
			}else {
				System.out.println("음식, 운동 입력 실패!!!");			
			}
			
			if (MenuName.getText() != null) {
				//kv.setKcal_name(MenuName.getText());
				System.out.println("칼로리 이름 입력 성공.");
			} else {
				MethodUtil.alertShow("INFORMATION", "등록실패", "이름 항목이 존재하지 않습니다.");
				return;
			}
	
			if (KcalName.getText() != null) {
				//kv.setKcal_num(Integer.parseInt(KcalName.getText()));
				System.out.println("칼로리 량 입력 성공");
			} else {
				MethodUtil.alertShow("INFORMATION", "등록실패", "칼로리 항목이 존재하지 않습니다.");
				return;
			}
			
			infoMsg("작업결과",MenuName.getText() + "의 " + KcalName.getText() + "을(를) 추가하였습니다.");
			
			//끝났으니 뒤로 돌아가야지
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FoodKcal.fxml"));
			
			Parent parent = null;
			try {
				parent = loader.load();
			} catch(IOException e) {
				e.printStackTrace();
			}
			System.out.println("parent1 ==> " + InsertPane.getParent());
			System.out.println("parent2 ==> " + parent);
			
			Pane rootPane = ((Pane)(KcalName.getScene().getRoot()));
			
			rootPane.getChildren().clear();
			rootPane.getChildren().addAll(((Pane)parent).getChildren());
		}
	}	
	
	public void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("정보 결과");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
	
	
	
	/**
	 * 뒤로가기 버튼
	 * @param event
	 */
	@FXML public void BreakButton(ActionEvent event) {
		//System.out.println("뒤로가기 버튼 클릭");
		
		/*FXMLLoader loader = new FXMLLoader(getClass().getResource("FoodKcal.fxml"));
		
		Parent parent = null;
		try {
			parent = loader.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("parent1 ==> " + InsertPane.getParent());
		System.out.println("parent2 ==> " + parent);
		
		Pane rootPane = ((Pane)(KcalName.getScene().getRoot()));
		
		rootPane.getChildren().clear();
		rootPane.getChildren().addAll(((Pane)parent).getChildren());	*/
		changeScene("FoodKcal.fxml");
	}
	
	
	@FXML public void KcalMenu() {} // xml과의 연동
	
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
