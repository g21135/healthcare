package kr.or.ddit.view.myInfo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import kr.or.ddit.LoginSession.LoginSession;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import kr.or.ddit.service.match.IMatchService;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.vo.MatchVO;
import javafx.scene.layout.BorderPane;

public class MyInfoController implements Initializable {

	@FXML
	JFXTextField id;
	@FXML
	JFXTextField grade;
	@FXML
	JFXTextField trainer;
	@FXML
	BorderPane myInfoView;
	@FXML
	ImageView imageView;

	Parent parent;
	IMemberService member;
	IMatchService match;
	Registry reg;

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

		/*
		 * try { ObservableList<MatchVO> data =
		 * FXCollections.observableArrayList(match.selectTrainer("lovelysh24")); } catch
		 * (RemoteException e) { // TODO Auto-generated catch block e.printStackTrace();
		 * }
		 */

		// MemberVO mv = new MemberVO();
		MatchVO mtv = new MatchVO();
		
		id.setText(LoginSession.memSession.getMem_id());
		grade.setText(LoginSession.memSession.getMem_grade());
//		trainer.setText(null);

	}

	@FXML
	public void updateBtnClick() {
		changeScene("MyInfoUpdate.fxml");
	}

	@FXML
	public void dropBtnClick() {
	}

	@FXML
	public void msgBtnClick() {
	}

	@FXML
	public void dhBtnClick() {
		changeScene("../dealHistory/DealHistroy.fxml");
	}

	@FXML
	public void cartBtnClick() {
		changeScene("../cart/CartView.fxml");
	}

	public FXMLLoader changeScene(String fxmlURL) {
		myInfoView.setTop(null);
		myInfoView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		myInfoView.setCenter(parent);
		return loader;
	}

}
