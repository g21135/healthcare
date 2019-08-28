package kr.or.ddit.view.cart;


import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.dealHistory.IDealHistoryService;
import kr.or.ddit.view.card.CardInfoController;
import kr.or.ddit.vo.DealHistoryVO;
import javafx.scene.layout.AnchorPane;

public class OrderController implements Initializable {

	@FXML
	private Label Order_Name;
	
	@FXML
	private Label Order_Tel;
	
	@FXML
	private Label Order_TotalPrice;
	@FXML
	TableView<DealHistoryVO> Order_tableview;
	
	@FXML
	TableColumn<DealHistoryVO, Integer> Order_prodNo;

	@FXML
	TableColumn<DealHistoryVO, String> Order_ProdName;
	
	@FXML
	TableColumn<DealHistoryVO, String> Order_ProdQty;
	
	@FXML
	TableColumn<DealHistoryVO, String> Order_ProdPrice;
	
	@FXML
	TableColumn<DealHistoryVO, String> Order_ProdShip;
	
	@FXML
	TableColumn<DealHistoryVO, Integer> Order_ProdShipPirce;
	
	@FXML
	TableColumn<DealHistoryVO, String> Order_AllPirce;
	
	
	@FXML
	Pagination Order_page;
	
	@FXML
	ComboBox<String> Order_Card;
	
	@FXML
	ComboBox<String> Order_HowBuy;
	
	@FXML
	JFXButton Order_PayButton;
	
	@FXML
	JFXButton Order_BackButton;
	
	LoginSession session;
	
	String reset ="0";
	// 페이징
	private int from, to, itemsForPage;
	ObservableList<DealHistoryVO> allBoxData, currentPageData;
		
	List<DealHistoryVO> list;	
	DealHistoryVO dv = new DealHistoryVO();
	private Registry reg;
	private IDealHistoryService deal;
	

	@FXML AnchorPane shopView;
	
	
	public void pageset() {
		
		try {
			list = deal.AllOrderListget(dv);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		allBoxData.setAll(list);
		Order_tableview.setItems(allBoxData);
		
		itemsForPage = 10;
		System.out.println(allBoxData.size());

		int totItemCnt = allBoxData.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		Order_page.setPageCount(totPageCnt);
		
		Order_page.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				Order_tableview.setItems(getTableViewData(from, to));
				return Order_tableview;
			}

		
		});
	}
	private ObservableList<DealHistoryVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		int totsize = allBoxData.size();
		for (int i = from; i <= to && i < totsize; i++) {
			currentPageData.add(allBoxData.get(i));
		}
		return currentPageData;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
			try {
				reg = LocateRegistry.getRegistry(8888);
				deal = (IDealHistoryService) reg.lookup("deal");
				System.out.println("rmI 성공");
			} catch (AccessException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
			
			String loginId = LoginSession.memSession.getMem_id();
			dv.setMem_id(loginId);
			allBoxData = FXCollections.observableArrayList();
			Order_Name.setText(session.memSession.getMem_name());
			Order_Tel.setText(session.memSession.getMem_tel());
			pageset();
			
			Order_Card.getItems().addAll("신한카드","비씨카드","국민카드");
			Order_Card.setValue("신한카드");
			
			Order_HowBuy.setValue("일시불");
			Order_HowBuy.setDisable(true);
			
			int prod_order = 0;
			for (int i = 0; i < list.size(); i++) {
				prod_order += Integer.parseInt(list.get(i).getOrder_allprice());
			}
			
			if(prod_order >50000 || prod_order == 0) {
				Order_TotalPrice.setText(Integer.toString(prod_order));
			
			}else {
				Order_TotalPrice.setText(Integer.toString(prod_order+2500));
			}
			
			//총구매가 50000만원넘으면 공짜
			
			
		
			Order_prodNo.setCellValueFactory(new PropertyValueFactory<>("deal_no"));
			Order_ProdName.setCellValueFactory(new PropertyValueFactory<>("order_prodname"));
			Order_ProdQty.setCellValueFactory(new PropertyValueFactory<>("order_prodqty"));
			Order_ProdShip.setCellValueFactory(new PropertyValueFactory<>("order_prodship"));
			Order_ProdPrice.setCellValueFactory(new PropertyValueFactory<>("order_prodprice"));
			Order_ProdShipPirce.setCellValueFactory(new PropertyValueFactory<>("order_prodshipprice"));
			Order_AllPirce.setCellValueFactory(new PropertyValueFactory<>("order_allprice"));
			
			Order_PayButton.setOnAction(ev->{
				int cnt = Integer.parseInt(Order_TotalPrice.getText());
				if(cnt == 0) {
					errMsg("구매에러", "구매한 물품이 없습니다");
				}
				CardInController.cardName = Order_Card.getSelectionModel().getSelectedItem();
				CardInController.price = Integer.parseInt(Order_TotalPrice.getText());
				try {
					FXMLLoader loader = new FXMLLoader((getClass().getResource("CardIn.fxml")));
					Parent parent = loader.load();
					Stage stage = new Stage(StageStyle.UTILITY);
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("추가 정보 입력");
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.showAndWait();
					
				}catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			Order_BackButton.setOnAction(dv ->{
				int cnt= 0;
				try {
					cnt = deal.DeleteOrder();
				} catch (Exception e) {
					
				}
				if (cnt <= 0) {
					errMsg("삭제에러", "삭제할 물품을 선택해주세요");
					return;
				} else {
					infoMsg("삭제성공", "해당물품이 삭제되었습니다");
					
					 try {
						 
								FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/view/cart/OrderView.fxml"));
								Parent parent = loader.load();
								BorderPane mainView = (BorderPane) ((Node) dv.getSource()).getScene().getRoot();
								mainView.setCenter(null);
								mainView.setCenter(parent);
							
							
						} catch (Exception e) {
						}
				}
			});
			
	
			Order_prodNo.setCellFactory(new Callback<TableColumn<DealHistoryVO,Integer>, TableCell<DealHistoryVO,Integer>>() {
				
				@Override
				public TableCell<DealHistoryVO, Integer> call(TableColumn<DealHistoryVO, Integer> param) {
					TableCell<DealHistoryVO, Integer> cell = new TableCell<DealHistoryVO, Integer>() {
						
						@Override
						public void updateItem(Integer item, boolean empty) {
							if (item != null) {
								Label order_no = new Label();
								order_no.setText(item +"번");
								setGraphic(order_no);
							}
						}
					};
					return cell;
				}
			});
			
			

			Order_ProdName.setCellFactory(new Callback<TableColumn<DealHistoryVO,String>, TableCell<DealHistoryVO,String>>() {
				
				@Override
				public TableCell<DealHistoryVO, String> call(TableColumn<DealHistoryVO, String> param) {
						TableCell<DealHistoryVO, String> cell = new TableCell<DealHistoryVO, String>() {
						
						@Override
						public void updateItem(String item, boolean empty) {
							if (item != null) {
								Label order_no = new Label();
								order_no.setText(item);
								setGraphic(order_no);
							}
						}
					};
					return cell;
				}
			});
			

			Order_ProdQty.setCellFactory(new Callback<TableColumn<DealHistoryVO,String>, TableCell<DealHistoryVO,String>>() {
				
				@Override
				public TableCell<DealHistoryVO, String> call(TableColumn<DealHistoryVO, String> param) {
						TableCell<DealHistoryVO, String> cell = new TableCell<DealHistoryVO, String>() {
						
						@Override
						public void updateItem(String item, boolean empty) {
							if (item != null) {
								Label order_qty = new Label();
								order_qty.setText(item+"개");
								setGraphic(order_qty);
							}
						}
					};
					return cell;
				}
			});
			
			Order_ProdPrice.setCellFactory(new Callback<TableColumn<DealHistoryVO,String>, TableCell<DealHistoryVO,String>>() {
				
				@Override
				public TableCell<DealHistoryVO, String> call(TableColumn<DealHistoryVO, String> param) {
						TableCell<DealHistoryVO, String> cell = new TableCell<DealHistoryVO, String>() {
						
						@Override
						public void updateItem(String item, boolean empty) {
							if (item != null) {
								Label order_price = new Label();
								order_price.setText(item+"원");
								setGraphic(order_price);
							}
						}
					};
					return cell;
				}
			});
			
			Order_ProdShipPirce.setCellFactory(new Callback<TableColumn<DealHistoryVO,Integer>, TableCell<DealHistoryVO,Integer>>() {
				
				@Override
				public TableCell<DealHistoryVO, Integer> call(TableColumn<DealHistoryVO, Integer> param) {
						TableCell<DealHistoryVO, Integer> cell = new TableCell<DealHistoryVO, Integer>() {
						
						@Override
						public void updateItem(Integer item, boolean empty) {
							if (item != null) {
								Label order_shipprice = new Label();
								order_shipprice.setText(item+"원");
								setGraphic(order_shipprice);
							}
						}
					};
					return cell;
				}
			});
			
			

			Order_AllPirce.setCellFactory(new Callback<TableColumn<DealHistoryVO,String>, TableCell<DealHistoryVO,String>>() {
				
				@Override
				public TableCell<DealHistoryVO, String> call(TableColumn<DealHistoryVO, String> param) {
						TableCell<DealHistoryVO, String> cell = new TableCell<DealHistoryVO, String>() {
						
						@Override
						public void updateItem(String item, boolean empty) {
							if (item != null) {
								Label order_allprice = new Label();
								int order_price = Integer.parseInt(item);
								int order_sum=0;
								if(order_price > 50000) {
									order_sum = order_price +0;
								}
								else if (order_price < 50000) {
									order_sum = order_price +2500;
								}
								System.out.println(order_sum);
								
								order_allprice.setText(order_sum + "원");
								
								setGraphic(order_allprice);
								
						
							}
						}
					};
					return cell;
				}
			});
			
			Order_tableview.setItems(allBoxData);
	}


	
	
	
	private void errMsg(String headerText, String msg) {
		Alert errAlert = new Alert(AlertType.ERROR);
		errAlert.setTitle("알림");
		errAlert.setHeaderText(headerText);
		errAlert.setContentText(msg);
		errAlert.showAndWait();

	}

	private void infoMsg(String headerText, String msg) {
		Alert infoAlert = new Alert(AlertType.INFORMATION);
		infoAlert.setTitle("알림");
		infoAlert.setHeaderText(headerText);
		infoAlert.setContentText(msg);
		infoAlert.showAndWait();
	}
}
