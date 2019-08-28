package kr.or.ddit.view.cart;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.ddit.clientMain.ClientMainController;
import kr.or.ddit.service.card.ICardService;
import kr.or.ddit.service.dealHistory.IDealHistoryService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.CardVO;
import javafx.scene.text.Text;

public class CardInController implements Initializable {

	public static String cardName;
	public static int price;
	@FXML
	Label card;
	Registry reg;
	ICardService cardService;
	@FXML
	TextField card_Num1;
	@FXML
	TextField card_Num2;
	@FXML
	TextField card_Num3;
	@FXML
	TextField card_Num4;
	@FXML
	TextField cardAlidity_month;
	@FXML
	TextField cardAlidity_year;
	@FXML
	TextField cvc_Num;
	@FXML
	TextField card_Pass;
	@FXML
	TextField mem_Bir1;
	@FXML
	TextField mem_Bir2;
	@FXML
	TextField mem_Tel;
	@FXML
	TextField mem_Email1;
	@FXML
	TextField mem_Email2;
	kr.or.ddit.LoginSession.LoginSession session;
	@FXML Text Order_price;
	
	IDealHistoryService deal;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		card.setText(cardName);

		Order_price.setText(price+"원");
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			cardService = (ICardService) reg.lookup("card");
			deal = (IDealHistoryService) reg.lookup("deal");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		Pattern pattern = Pattern.compile(".{0,4}");
		TextFormatter formatter1 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return pattern.matcher(change.getControlNewText()).matches() ? change : null;
		});
		TextFormatter formatter2 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return pattern.matcher(change.getControlNewText()).matches() ? change : null;
		});
		TextFormatter formatter3 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return pattern.matcher(change.getControlNewText()).matches() ? change : null;
		});
		TextFormatter formatter4 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return pattern.matcher(change.getControlNewText()).matches() ? change : null;
		});
		TextFormatter formatter5 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return pattern.matcher(change.getControlNewText()).matches() ? change : null;
		});

		Pattern pattern2 = Pattern.compile(".{0,2}");
		TextFormatter a1 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return pattern2.matcher(change.getControlNewText()).matches() ? change : null;
		});
		TextFormatter a2 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return pattern2.matcher(change.getControlNewText()).matches() ? change : null;
		});

		Pattern pattern3 = Pattern.compile(".{0,3}");
		TextFormatter b1 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return pattern3.matcher(change.getControlNewText()).matches() ? change : null;
		});

		Pattern pattern4 = Pattern.compile(".{0,6}");
		TextFormatter c1 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return pattern4.matcher(change.getControlNewText()).matches() ? change : null;
		});

		Pattern pattern5 = Pattern.compile(".{0,7}");
		TextFormatter d1 = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
			return pattern5.matcher(change.getControlNewText()).matches() ? change : null;
		});

		card_Num1.setTextFormatter(formatter1);
		card_Num2.setTextFormatter(formatter2);
		card_Num3.setTextFormatter(formatter3);
		card_Num4.setTextFormatter(formatter4);
		cardAlidity_month.setTextFormatter(a1);
		cardAlidity_year.setTextFormatter(a2);
		cvc_Num.setTextFormatter(b1);
		card_Pass.setTextFormatter(formatter5);
		mem_Bir1.setTextFormatter(c1);
		mem_Bir2.setTextFormatter(d1);

		/*
		 * cardAlidity_month.setTextFormatter(formatter2);
		 * cardAlidity_year.setTextFormatter(formatter2);
		 */

		card_Num1.requestFocus();
	}

	@FXML
	public void okBtn(ActionEvent event) {
		List<CardVO> cardList = null;

		try {
			cardList = (List<CardVO>) cardService.getCardList();
			for (CardVO cvo : cardList) {
				System.out.println(cvo.getCard_name() + cvo.getCard_number());
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			cardService = (ICardService) reg.lookup("card");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		boolean result_Bir = true;
		boolean result_card_Num = true;
		boolean result_card_Alidity = true;
		boolean result_card_Cvc = true;
		boolean result_card_Pass = true;
		boolean result_card_Name = true;

		String mem_bir = "";
		mem_bir = mem_Bir1.getText() + mem_Bir2.getText();
		System.out.println(mem_bir);
		long card_Num;
		card_Num = Long
				.parseLong(card_Num1.getText() + card_Num2.getText() + card_Num3.getText() + card_Num4.getText());
		int card_Alidity;
		card_Alidity = Integer.parseInt(cardAlidity_month.getText() + cardAlidity_year.getText());
		int card_Cvc_Num;
		card_Cvc_Num = Integer.parseInt(cvc_Num.getText());
		int card_Pass_Num;
		card_Pass_Num = Integer.parseInt(card_Pass.getText());
		// System.out.println(card_Num);

		for (int i = 0; i < cardList.size(); i++) {
			if (cardList.get(i).getCard_name().equals(card.getText())) {
				if (cardList.get(i).getMem_bir().equals(mem_bir) && session.memSession.getMem_bir().equals(cardList.get(i).getMem_bir())) {
					if (cardList.get(i).getCard_number() == card_Num) {
						if (cardList.get(i).getCard_alidity() == card_Alidity) {
							if (cardList.get(i).getCard_cvc() == card_Cvc_Num) {
								if (cardList.get(i).getCard_pass() == card_Pass_Num) {
									ButtonType button = MethodUtil.alertConfirmShow("결제진행", "결제", "결제를 진행하시겠습니까?");
									if (button == ButtonType.OK) {

//										int memPrice = 0;
										CardVO vo = new CardVO();
										System.out.println(card.getText() + mem_bir);
										try {
											vo = cardService.getCardBalance(card.getText(), mem_bir);
										} catch (RemoteException e) {
											e.printStackTrace();
										}
										System.out.println(vo.getCard_balance());

										if (vo.getCard_balance() < price) {
											Parent parent3 = null;
											try {
												parent3 = FXMLLoader
														.load(getClass().getResource("PaymentResult_False.fxml"));
												deal.DeleteOrder();
											} catch (IOException e) {
												e.printStackTrace();
											}
											Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
											stage.close();
											ClientMainController.pane.setCenter(parent3);
											result_Bir = true;

										}
										if (vo.getCard_balance() > price) {
											Parent parent2 = null;
											try {
												parent2 = FXMLLoader
														.load(getClass().getResource("PaymentResult_True.fxml"));
											} catch (IOException e) {
												e.printStackTrace();
											}
											Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
											stage.close();
											ClientMainController.pane.setCenter(parent2);
											result_Bir = true;
										}

									} else if (button == ButtonType.CANCEL) {
										Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
										stage.close();
										return;
									}

								} else
									result_card_Pass = false;

								if (!result_card_Pass) {
									MethodUtil.alertShow("잘못된 정보 입력", "비밀번호", "비밀번호를 확인하세요");
								}
								break;
							} else
								result_card_Cvc = false;
							if (!result_card_Cvc) {
								MethodUtil.alertShow("잘못된 정보 입력", "CVC번호", "CVC번호를 확인하세요");
							}
							break;
						} else
							result_card_Alidity = false;
						if (!result_card_Alidity) {
							MethodUtil.alertShow("잘못된 정보 입력", "유효기간", "유효기간를 확인하세요");
						}
						break;
					} else
						result_card_Num = false;
					if (!result_card_Num) {
						MethodUtil.alertShow("잘못된 정보 입력", "카드번호", "카드번호를 확인하세요");
					}
					result_Bir = true;
					break;
				} else
					result_Bir = false;
			}
			// result_card_Name = false;
		}
		
		if (!result_Bir) {
			MethodUtil.alertShow("잘못된 정보 입력", "주민등록번호", "주민등록번호를 확인하세요");
		}
		result_Bir = true;
		
		
		
		

	}
}
