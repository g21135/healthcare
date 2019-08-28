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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.ddit.service.hope.IHopeService;
import kr.or.ddit.vo.HopeVO;

public class UserHopeMain implements Initializable {

	public static String name;
	public static String mem_id;
	@FXML
	Label mem_name;
	@FXML
	JFXCheckBox Increased_muscle;
	@FXML
	JFXCheckBox Diet;
	@FXML
	JFXCheckBox Rehabilitation;
	@FXML
	JFXCheckBox Strength_enhancement;
	@FXML
	JFXCheckBox Weight_increase;
	@FXML
	JFXCheckBox Body_correction;
	@FXML
	JFXCheckBox Increase_flexibility;
	@FXML
	JFXCheckBox Monday;
	@FXML
	JFXCheckBox Tuesday;
	@FXML
	JFXCheckBox Saturday;
	@FXML
	JFXCheckBox Friday;
	@FXML
	JFXCheckBox Wednesday;
	@FXML
	JFXCheckBox Sunday;
	@FXML
	JFXCheckBox Thursday;
	@FXML
	JFXCheckBox Early_Morning;
	@FXML
	JFXCheckBox Morning;
	@FXML
	JFXCheckBox Night;
	@FXML
	JFXCheckBox After_Dinner;
	@FXML
	JFXCheckBox Lunch;
	@FXML
	JFXCheckBox Early_Dinner;
	@FXML
	JFXCheckBox teenager;
	@FXML
	JFXCheckBox twenteen;
	@FXML
	JFXCheckBox fifty;
	@FXML
	JFXCheckBox thirty;
	@FXML
	JFXCheckBox forty;
	@FXML
	JFXButton cancel;
	@FXML
	JFXButton register;
	@FXML
	JFXRadioButton boyRadio;
	@FXML
	JFXRadioButton girlRadio;

	Registry reg;
	IHopeService hopeService;
	@FXML
	JFXButton trainerList;
	public static BorderPane mainView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mem_name.setText(name);

		CheckBox[] purposeBox = new CheckBox[] { Increased_muscle, Diet, Rehabilitation, Strength_enhancement,
				Weight_increase, Body_correction, Increase_flexibility };
		CheckBox[] dayBox = new CheckBox[] { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday };
		CheckBox[] chatTimeBox = new CheckBox[] { Early_Morning, Morning, Lunch, Early_Dinner, After_Dinner, Night };
		CheckBox[] ageBox = new CheckBox[] { teenager, twenteen, thirty, forty, fifty };
		ToggleGroup group = new ToggleGroup();
		boyRadio.setToggleGroup(group);
		boyRadio.setUserData("남자");
		girlRadio.setToggleGroup(group);
		girlRadio.setUserData("여자");

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			hopeService = (IHopeService) reg.lookup("hope");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		HopeVO vo = new HopeVO();
		try {
			vo = hopeService.getMemberHope(mem_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		// System.out.println(".*"+vo.getHope_purpose()+"*.");
		for (int i = 0; i < purposeBox.length; i++) {
			if (vo.getHope_purpose().matches(".*" + purposeBox[i].getText() + "*.")) {
				System.out.println(purposeBox[i].getText());
				purposeBox[i].setSelected(true);
				purposeBox[i].setDisable(true);
			}
		}
		for (int i = 0; i < dayBox.length; i++) {
			if (vo.getHope_day().matches(".*" + dayBox[i].getText() + "*.")) {
				System.out.println(dayBox[i].getText());
				dayBox[i].setSelected(true);
				dayBox[i].setDisable(true);
			}
		}
		for (int i = 0; i < chatTimeBox.length; i++) {
			if (vo.getHope_time().matches(".*" + chatTimeBox[i].getText() + "*.")) {
				System.out.println(chatTimeBox[i].getText());
				chatTimeBox[i].setSelected(true);
				chatTimeBox[i].setDisable(true);
			}
		}
		for (int i = 0; i < ageBox.length; i++) {
			if (vo.getHope_age().matches(".*" + ageBox[i].getText() + "*.")) {
				System.out.println(ageBox[i].getText());
				ageBox[i].setSelected(true);
				ageBox[i].setDisable(true);
			}
		}
		
//		System.out.println(group.getToggles().get(0).getUserData());
		for (int i = 0; i < group.getToggles().size(); i++) {
			if (vo.getHope_gender().equals(group.getToggles().get(i).getUserData())) {
				System.out.println(group.getToggles().get(i).getUserData());
				group.getToggles().get(i).setSelected(true);
			}
		}

	}

	@FXML public void cancel(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
	}

	@FXML public void trainerList(ActionEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
		mainView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../admin/memberListMain.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainView.setCenter(parent);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
