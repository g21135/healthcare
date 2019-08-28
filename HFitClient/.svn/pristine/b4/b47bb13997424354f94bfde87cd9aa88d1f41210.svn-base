package kr.or.ddit.view.applyTrainer;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.tree.AbstractLayoutCache;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import kr.or.ddit.clientMain.ClientMainController;
import kr.or.ddit.service.apply.IApplyService;
import kr.or.ddit.vo.ApplyVO;

import com.jfoenix.controls.JFXRadioButton;

import api.Mail;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TextField;

public class ApplyTrainerMainController implements Initializable {

	@FXML
	JFXRadioButton apply_gender_men;
	@FXML
	JFXRadioButton apply_gender_women;
	@FXML
	JFXButton addTrainer;
	@FXML
	JFXButton cancel;

	Registry reg;
	IApplyService apply;
	@FXML TextField apply_name;
	@FXML TextField apply_bir;
	@FXML TextField apply_addr;
	@FXML TextField apply_tel;
	@FXML TextField apply_career;
	@FXML TextField apply_email;
	ToggleGroup group;
	@FXML JFXButton addBtn;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			apply = (IApplyService) reg.lookup("apply");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		ToggleGroup group = new ToggleGroup();
		
		apply_gender_men.setToggleGroup(group);
		apply_gender_men.setUserData("men");
		apply_gender_women.setToggleGroup(group);
		apply_gender_women.setUserData("women");
		
		addBtn.setOnAction(e->{
			String trainer_Name = apply_name.getText().trim();
			int trainer_Bir = Integer.parseInt(apply_bir.getText().trim());
			String trainer_Addr = apply_addr.getText().trim();
			String trainer_Tel = apply_tel.getText().trim();
			String trainer_Career = apply_career.getText().trim();
			String trainer_email = apply_email.getText().trim();
			
			
			String gender = "";
			if(group.getSelectedToggle().getUserData() != null) {
				gender = group.getSelectedToggle().getUserData().toString();
			}
			
			ApplyVO vo = new ApplyVO();
			vo.setApply_name(trainer_Name);
			vo.setApply_bir(trainer_Bir);
			vo.setApply_addr(trainer_Addr);
			vo.setApply_tel(trainer_Tel);
			vo.setApply_careeri(trainer_Career);
			vo.setApply_gender(gender);
			vo.setApply_email(trainer_email);
			
			Object obj = apply.insertTrainer(vo);
			Alert alertInformation = null; 
			alertInformation = new Alert(AlertType.INFORMATION);
//			Alert alertError = new Alert(AlertType.ERROR);
			
			if (obj == null) {
				alertInformation.setTitle("INFORMATION");
				alertInformation.setHeaderText(vo.getApply_name() + "님 등록 신청되셨습니다!!");
				alertInformation.showAndWait();
				
				//mail.sendMail(vo.getApply_email(), code, type);
				
			}
			
			apply_name.setText("");
			apply_bir.setText("");
			apply_addr.setText("");
			apply_tel.setText("");
			apply_career.setText("");
			apply_name.requestFocus();
		});
		
		cancel.setOnAction(e->{
			changeScene("../../clientMain/home.fxml");
		});
	}
	
	public FXMLLoader changeScene(String fxmlURL) {
		ClientMainController.pane.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ClientMainController.pane.setCenter(parent);
		return loader;
	}


}
