package kr.or.ddit.view.card;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.LoginSession.LoginSession;

public class CardMainController implements Initializable {

	@FXML
	Label tel;
	@FXML
	Label name;
	@FXML
	Label email;
	LoginSession session;
	@FXML
	Label Whatmonth;

	@FXML
	Label saleApplyPrice;
	@FXML
	Label totalPrice;

	public static String month;
	public static String price;
	public static String tPrice;
	@FXML
	ComboBox sortCard;
	@FXML
	ComboBox howBuy;
	@FXML
	JFXButton cancel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		name.setText(session.memSession.getMem_name());
		email.setText(session.memSession.getMem_email());
		tel.setText(session.memSession.getMem_tel());
		
		Whatmonth.setText(month + "일");
		saleApplyPrice.setText(price);
		totalPrice.setText(price);
		String updatePrice;
		
		updatePrice = totalPrice.getText().replaceAll(",", "");
		tPrice = updatePrice;
		
		CardInfoController.price = Integer.parseInt(updatePrice);
		
		sortCard.getItems().addAll("신한카드", "비씨카드", "국민카드");
		sortCard.setValue("신한카드");

		howBuy.setValue("일시불");
		howBuy.setDisable(true);
		
		
	}
	
	@FXML
	public void paymentBtn(ActionEvent event) {
		CardInfoController.cardName = (String) sortCard.getValue();
		try {
			FXMLLoader loader = new FXMLLoader((getClass().getResource("CardInfo.fxml")));
			Parent parent = loader.load();
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.initModality(Modality.APPLICATION_MODAL);
			// stage.initOwner(stage);
			stage.setTitle("추가 정보 입력");
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.showAndWait();
			/*Parent parent2 = FXMLLoader.load(getClass().getResource("CardInfo.fxml"));
			BorderPane pane = (BorderPane) ((Node)event.getSource()).getScene().getRoot();
			pane.setCenter(null);
			pane.setCenter(parent2);*/
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		

	}

}
