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
import com.jfoenix.controls.JFXDatePicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import kr.or.ddit.service.challenge.IChallengeService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.ChallengeVO;

public class AddChallengeController implements Initializable {

	@FXML
	JFXButton file;
	@FXML
	TextField textTitle;
	@FXML
	TextArea textContent;
	@FXML
	ImageView imageView;
	@FXML
	JFXDatePicker dateStart;
	@FXML
	JFXDatePicker dateEnd;
	Registry reg;
	IChallengeService chall;

	boolean check = false;
	BufferedInputStream bis = null;
	BufferedOutputStream bos = null;
	ChallengeVO cv;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			chall = (IChallengeService) reg.lookup("chall");

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
		cv = new ChallengeVO();
		cv.setChall_name(textTitle.getText());
		cv.setChall_content(textContent.getText());
		cv.setChall_photo("challenge/" + textTitle.getText() + ".jpg"); // 파일 저장 다이얼로그를 써야할듯
		cv.setChall_start(new Date());
		cv.setChall_end(new Date());

		try {
			chall.insert(cv);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		int c;

		try {
			bos = new BufferedOutputStream(new FileOutputStream("img/challenge/" + cv.getChall_name() + ".jpg"));
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
