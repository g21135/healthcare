package kr.or.ddit.view.cart;

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
import kr.or.ddit.service.dealHistory.IDealHistoryService;
import kr.or.ddit.service.membership.IMemberShipService;
import kr.or.ddit.vo.CardVO;
import kr.or.ddit.vo.DealHistoryVO;
import kr.or.ddit.vo.MembershipVO;

public class PayResultController implements Initializable{
	 Registry reg;
	 LoginSession session;
	 IDealHistoryService deal;
	 ICardService cardService;
	 IMemberShipService memberShipService;
	 DealHistoryVO dv = new DealHistoryVO();
	 public static String month;
	@FXML ImageView addInfo;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		int cardBalance = CardInController.price;
		String cardName = CardInController.cardName;
		String memBir = session.memSession.getMem_bir();
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			cardService = (ICardService) reg.lookup("card");
			deal =(IDealHistoryService) reg.lookup("deal");
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
			try {
				deal.PayUpdate();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		}else
			System.out.println("업데이트 실패");
		
		
		
		
		
			
	}	

}
