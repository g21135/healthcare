package kr.or.ddit.view.hope;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.clientMain.ClientMainController;
import kr.or.ddit.service.hope.IHopeService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.HopeVO;

public class HopeMainController implements Initializable{

	Registry reg;
	IHopeService hope;
	@FXML JFXCheckBox Increased_muscle;
	@FXML JFXCheckBox Diet;
	@FXML JFXCheckBox Rehabilitation;
	@FXML JFXCheckBox Strength_enhancement;
	@FXML JFXCheckBox Weight_increase;
	@FXML JFXCheckBox Body_correction;
	@FXML JFXCheckBox Increase_flexibility;
	@FXML JFXCheckBox Monday;
	@FXML JFXCheckBox Tuesday;
	@FXML JFXCheckBox Saturday;
	@FXML JFXCheckBox Friday;
	@FXML JFXCheckBox Wednesday;
	@FXML JFXCheckBox Sunday;
	@FXML JFXCheckBox Thursday;
	@FXML JFXCheckBox Early_Morning;
	@FXML JFXCheckBox Morning;
	@FXML JFXCheckBox Night;
	@FXML JFXCheckBox After_Dinner;
	@FXML JFXCheckBox Lunch;
	@FXML JFXCheckBox Early_Dinner;
	@FXML JFXCheckBox teenager;
	@FXML JFXCheckBox twenteen;
	@FXML JFXCheckBox fifty;
	@FXML JFXCheckBox thirty;
	@FXML JFXCheckBox forty;
	@FXML JFXButton cancel;
	@FXML JFXButton register;
	@FXML JFXRadioButton boyRadio;
	@FXML JFXRadioButton girlRadio;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			hope = (IHopeService) reg.lookup("hope");
			System.out.println("성공");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		CheckBox[] purposeBox = new CheckBox[] {Increased_muscle, Diet, Rehabilitation, Strength_enhancement, Weight_increase, Body_correction, Increase_flexibility};
		CheckBox[] dayBox = new CheckBox[] {Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday};
		CheckBox[] chatTimeBox = new CheckBox[] {Early_Morning, Morning, Lunch, Early_Dinner, After_Dinner, Night};
		CheckBox[] ageBox = new CheckBox[] {teenager, twenteen, thirty, forty, fifty};
		ToggleGroup group = new ToggleGroup();
		boyRadio.setToggleGroup(group);
		boyRadio.setUserData("남자");
		girlRadio.setToggleGroup(group);
		girlRadio.setUserData("여자");
		
		cancel.setOnAction(e->{
			Stage stage = (Stage) cancel.getScene().getWindow();
			stage.close();
		});
		
		register.setOnAction(e->{
			
			
//			System.out.println(Arrays.toString(purposeBox));
			String purpose = "";
			for(int i = 0; i < purposeBox.length; i++) {
				if(purposeBox[i].isSelected()) {
					purpose += purposeBox[i].getText() + ",";
//					System.out.println(purposeBox[i].getText());
				}
			}
			String day = "";
			for(int i = 0; i < dayBox.length; i++) {
				if(dayBox[i].isSelected()) {
					day += dayBox[i].getText() + ",";
				}
			}
			
			String chatTime = "";
			for(int i = 0; i < chatTimeBox.length; i++) {
				if(chatTimeBox[i].isSelected()) {
					chatTime += chatTimeBox[i].getText() + ",";
				}
			}
			
			String age = "";
			for(int i = 0; i < ageBox.length; i++) {
				if(ageBox[i].isSelected()) {
					age += ageBox[i].getText() + ",";
				}
			}
			
			String gender = "";
			if(group.getSelectedToggle().getUserData() != null) {
				gender = group.getSelectedToggle().getUserData().toString();
			}
			
			Object obj = null;
			HopeVO vo = new HopeVO();
			vo.setMem_id(LoginSession.memSession.getMem_id());
			vo.setHope_purpose(purpose);
			vo.setHope_day(day);
			vo.setHope_time(chatTime);
			vo.setHope_age(age);
			vo.setHope_gender(gender);
			
			obj = hope.insertHope(vo);
			
			MethodUtil.alertShow("등록", "등록완료", "등록이 완료되었습니다!!!!");
			Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
			stage.close();
			
			Parent parent = null;
			try {
				parent = FXMLLoader.load(getClass().getResource("../../clientMain/home.fxml"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ClientMainController.pane.setCenter(null);
			ClientMainController.pane.setCenter(parent);
			
			
			
			
			/*if(obj == null) {
				System.out.println("삽입 성공");
			}*/
			
			
			
			
			
			
			
			
			
			
			
		});
	}
	

}
