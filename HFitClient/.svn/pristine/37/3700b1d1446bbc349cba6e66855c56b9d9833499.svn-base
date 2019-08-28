package kr.or.ddit.view.board.challenge;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.challApply.IChallApplyService;
import kr.or.ddit.service.check.ICheckService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.ChallApplyVO;
import kr.or.ddit.vo.ChallengeVO;
import kr.or.ddit.vo.CheckVO;

public class AddCheckPostController implements Initializable {

	@FXML
	JFXButton file;
	@FXML
	TextField textTitle;
	@FXML
	TextArea textContent;
	@FXML
	ImageView imageView;

	Registry reg;
	ICheckService checkS;
	IChallApplyService challApply;
	boolean check = false;
	BufferedInputStream bis = null;
	BufferedOutputStream bos = null;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			checkS = (ICheckService) reg.lookup("check");
			challApply = (IChallApplyService) reg.lookup("challApply");

		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void okBtnClick() {
		if (!check) {
			MethodUtil.alertShow("WARNNING", "사진첨부 에러!", "사진을 첨부해주세요");
			return;
		}
		CheckVO cv = new CheckVO();
		
		cv = new CheckVO();
		try {
			ChallApplyVO cav = new ChallApplyVO();
			cav.setChall_no(CheckBoardController.applyChallComboNum);
			cav.setMem_id(LoginSession.memSession.getMem_id());
			cv.setChallapply_no(challApply.selectCav(cav).getChallapply_no());
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}

		cv.setCheck_title(textTitle.getText());
		cv.setCheck_content(textContent.getText());
		cv.setCheck_photo("check/" + textTitle.getText() + ".jpg"); 
		cv.setCheck_date(new Date());
		cv.setCheck_approve(0);

		

		int c;
		try {
			bos = new BufferedOutputStream(new FileOutputStream("img/" + cv.getCheck_photo()));
			while ((c = bis.read()) != -1) {
				bos.write(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
				bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			checkS.insert(cv);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		Stage primaryStage = (Stage) imageView.getScene().getWindow();
		primaryStage.close();

	}

	@FXML
	public void fileBtnClick(ActionEvent event) {
		// 파일 열기 창
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif"));
		File selectFile = fileChooser.showOpenDialog(null);
		if (selectFile != null) {
			try {
				bis = new BufferedInputStream(new FileInputStream(selectFile));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		try {
			imageView.setImage(new Image(new BufferedInputStream(new FileInputStream(selectFile))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		imageView.setFitWidth(360);
		imageView.setFitHeight(198);
		imageView.setStyle("-fx-alignment : center");

		check = true;
	}

	@FXML
	public void cancleBtnClick(ActionEvent event) {
		Stage primaryStage = (Stage) imageView.getScene().getWindow();
		primaryStage.close();
	}
}
