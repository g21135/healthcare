package kr.or.ddit.view.card;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.card.ICardService;
import kr.or.ddit.service.membership.IMemberShipService;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.MembershipVO;

public class PaymentResultController implements Initializable{
	 Registry reg;
	 LoginSession session;
	 ICardService cardService;
	 IMemberShipService memberShipService;
	 public static String month;
	@FXML ImageView addInfo;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		int cardBalance = Integer.parseInt(CardMainController.tPrice);
		String cardName = CardInfoController.cardName;
		String memBir = session.memSession.getMem_bir();
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			cardService = (ICardService) reg.lookup("card");
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		int a = 0;
		try {
			CardVO cv = new CardVO();
			cv.setCard_balance(cardBalance);
			cv.setCard_name(cardName);
			cv.setMem_bir(memBir);
			a = cardService.updateBalance(cv);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		if(a > 0) {
			System.out.println("업데이트 성공");
		}else
			System.out.println("업데이트 실패");
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			memberShipService = (IMemberShipService) reg.lookup("memberShip");
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			memberShipService.insertMemberShip(session.memSession.getMem_id(), month);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		addInfo.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Parent parent = null;
				try {
					parent = FXMLLoader.load(getClass().getResource("../hope/HopeMain.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Stage stage = new Stage();
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.showAndWait();
				
			}
		});
		
		
		
//		ButtonType button = MethodUtil.alertConfirmShow("추가정보", "추가정보입력", "추가정보 입력하러 가볼까요?");
//		if (button == ButtonType.OK) {
			
//		}
		
			
	}	

}
