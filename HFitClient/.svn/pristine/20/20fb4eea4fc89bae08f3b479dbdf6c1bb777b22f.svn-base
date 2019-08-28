package kr.or.ddit.view.buyMembership;

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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.hope.IHopeService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.view.card.CardMainController;
import kr.or.ddit.view.card.PaymentResultController;
import kr.or.ddit.view.cart.CardController;
import javafx.scene.control.Label;

public class BuyMemMainController implements Initializable {

	LoginSession session;
	Registry reg;
	IHopeService hope;
	@FXML
	JFXButton onebtn;
	@FXML
	JFXButton threebtn;
	@FXML
	JFXButton sixbtn;
	@FXML
	Label oneMonth;
	@FXML
	Label oneMonthPrice;
	@FXML
	Label threeMonth;
	@FXML
	Label threeMonthPrice;
	@FXML
	Label sixMonth;
	@FXML
	Label sixMonthPrice;

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

	}

	@FXML
	public void onebtn(ActionEvent event) {
		if (session.memSession == null) {
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("오류");
			alertError.setHeaderText("로그인 오류");
			alertError.setContentText("로그인을 하셔야 합니다");
			alertError.showAndWait();
		} else {
			CardMainController.month = oneMonth.getText();
			CardMainController.price = oneMonthPrice.getText();
			PaymentResultController.month = oneMonth.getText();
			MethodUtil.alertShow("결제창", "결제창이동", "결제하러 가볼까요?");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../card/CardMain.fxml"));
				Parent parent = loader.load();
				BorderPane mainView = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
				mainView.setCenter(null);
				mainView.setCenter(parent);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@FXML
	public void threebtn(ActionEvent event) {
		if (session.memSession == null) {
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("오류");
			alertError.setHeaderText("로그인 오류");
			alertError.setContentText("로그인을 하셔야 합니다");
			alertError.showAndWait();
		} else {
			CardMainController.month = threeMonth.getText();
			CardMainController.price = threeMonthPrice.getText();
			PaymentResultController.month = threeMonth.getText();
			MethodUtil.alertShow("결제창", "결제창이동", "결제하러 가볼까요?");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../card/CardMain.fxml"));
				Parent parent = loader.load();
				BorderPane mainView = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
				mainView.setCenter(null);
				mainView.setCenter(parent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@FXML
	public void sixbtn(ActionEvent event) {
		if (session.memSession == null) {
			Alert alertError = new Alert(AlertType.ERROR);
			alertError.setTitle("오류");
			alertError.setHeaderText("로그인 오류");
			alertError.setContentText("로그인을 하셔야 합니다");
			alertError.showAndWait();
		} else {
			CardMainController.month = sixMonth.getText();
			CardMainController.price = sixMonthPrice.getText();
			PaymentResultController.month = sixMonth.getText();
			MethodUtil.alertShow("결제창", "결제창이동", "결제하러 가볼까요?");
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../card/CardMain.fxml"));
				Parent parent = loader.load();
				BorderPane mainView = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
				mainView.setCenter(null);
				mainView.setCenter(parent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
