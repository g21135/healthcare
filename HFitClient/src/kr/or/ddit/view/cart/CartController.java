package kr.or.ddit.view.cart;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.cart.ICartService;
import kr.or.ddit.service.dealHistory.IDealHistoryService;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.DealHistoryVO;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.AnchorPane;

public class CartController implements Initializable {

	

	private Registry reg;
	private ICartService cart;
	private IDealHistoryService deal;

	List<CartVO> list;
	List<CartVO> checklist;
	CartVO cv = new CartVO();
	DealHistoryVO dv = new DealHistoryVO();

	public int check = 0;
	
	
	// 페이징
	private int from, to, itemsForPage;
	ObservableList<CartVO> allBoxData, currentPageData;
	@FXML
	Pagination Cart_Page;
	
	@FXML
	TableView<CartVO> Cart_TableView;
	@FXML
	TableColumn<CartVO, Integer> Tv_Select;
	@FXML
	TableColumn<CartVO, String> Tv_Image;

	@FXML
	TableColumn<CartVO, String> Tv_Name;
	@FXML
	TableColumn<CartVO, String> Tv_Price;
	@FXML
	TableColumn<CartVO, String> Tv_Qty;

	@FXML
	TableColumn<CartVO, String> Tv_ship;
	@FXML
	TableColumn<CartVO, Integer> Tv_ShipPrice;
	@FXML
	TableColumn<CartVO, String> Tv_Order;
	@FXML
	Text Prod_Price;
	@FXML
	Text Order_Price;

	@FXML
	Text Prod_ship;

	@FXML
	JFXButton Cart_Delete;

	@FXML
	JFXButton Cart_Order;
	@FXML AnchorPane cartview;
	@FXML BorderPane MainView;

	
	public void pageset() {
	
		try {
			list = cart.AllCartListget(cv);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if(list.size() == 0 ) {
			errMsg("장바구니오류", "장바구니에 들어있는게없습니다.");
			
			changeScene("/kr/or/ddit/view/prod/ProdList.fxml");
		
		}
		allBoxData.setAll(list);
		
		itemsForPage = 10;
		System.out.println(allBoxData.size());

		int totItemCnt = allBoxData.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		Cart_Page.setPageCount(totPageCnt);
		
		Cart_Page.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				Cart_TableView.setItems(getTableViewData(from, to));
				return Cart_TableView;
			}
		});
	}
	private ObservableList<CartVO> getTableViewData(int from, int to) {
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
			cart = (ICartService) reg.lookup("cart");
			deal = (IDealHistoryService) reg.lookup("deal");

			System.out.println("rmI 성공");
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		Tv_Select.setCellValueFactory(new PropertyValueFactory<>("cart_check"));
		Tv_Image.setCellValueFactory(new PropertyValueFactory<>("cart_photo"));
		Tv_Name.setCellValueFactory(new PropertyValueFactory<>("cart_name"));
		Tv_Price.setCellValueFactory(new PropertyValueFactory<>("cart_price"));
		Tv_Qty.setCellValueFactory(new PropertyValueFactory<>("cart_qty"));
		Tv_ship.setCellValueFactory(new PropertyValueFactory<>("cart_ship"));
		Tv_ShipPrice.setCellValueFactory(new PropertyValueFactory<>("cart_shipprice"));
		Tv_Order.setCellValueFactory(new PropertyValueFactory<>("cart_order"));

		String loginId = LoginSession.memSession.getMem_id();
		cv.setMem_id(loginId);
		
		allBoxData = FXCollections.observableArrayList();
		
		pageset();
		
		System.out.println(list.size());
		
		Cart_Delete.setOnAction(dl -> {
			int cnt = 0;
			try {
				cnt = cart.DeleteCart(cv);
			} catch (Exception e) {
			}
			if (cnt <= 0) {
				errMsg("삭제에러", "삭제할 물품을 선택해주세요");
				return;
			} else {
				infoMsg("삭제성공", "해당물품이 삭제되었습니다");
				
				allBoxData.clear();
				Cart_TableView.refresh();
				System.out.println(allBoxData);
				pageset();
			}

		});

		Cart_Order.setOnAction(e -> {
			if(check == 0) {
				errMsg("주문에러", "주문할 상품이 없습니다");
				return;
			}else {
				infoMsg("주문성공","선택한 상품을 주문합니다.");
			}
			try {
				checklist = cart.CheckCartListget(cv);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			System.out.println("체크:"+checklist.size());
			DealHistoryVO dealhistroyvo =new DealHistoryVO();
			for(int i=0; i<checklist.size();i++) {
				
					String cart_name = checklist.get(i).getCart_name();
					String cart_qty = checklist.get(i).getCart_qty();
					String cart_price = checklist.get(i).getCart_price();
					String cart_ship = checklist.get(i).getCart_ship();
					int cart_shipprice = checklist.get(i).getCart_shipprice();
					String cart_order = checklist.get(i).getCart_order();
					String cart_img = checklist.get(i).getCart_photo();
					
					 dealhistroyvo.setMem_id(loginId);
					 dealhistroyvo.setOrder_prodname(cart_name);
					 dealhistroyvo.setOrder_prodqty(cart_qty);
					 dealhistroyvo.setOrder_prodprice(cart_price);
					 dealhistroyvo.setOrder_prodship(cart_ship);
					 dealhistroyvo.setOrder_prodshipprice(cart_shipprice);
					 dealhistroyvo.setOrder_allprice(cart_order);
					 dealhistroyvo.setOrder_img(cart_img);
					 
					 try {
						 deal.InsertOrder(dealhistroyvo);
						 cart.DeleteCart(cv);
					
							
							
						} catch (Exception e2) {
						}
				
				
				
			}
			 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/kr/or/ddit/view/cart/OrderView.fxml"));
			Parent parent = null;
			try {
				parent = loader.load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			BorderPane mainView = (BorderPane) ((Node) e.getSource()).getScene().getRoot();
			mainView.setCenter(null);
			mainView.setCenter(parent);
			 
			 
			
		});

		Tv_Select.setCellFactory(new Callback<TableColumn<CartVO, Integer>, TableCell<CartVO, Integer>>() {
			@Override
			public TableCell<CartVO, Integer> call(TableColumn<CartVO, Integer> param) {
				TableCell<CartVO, Integer> cell = new TableCell<CartVO, Integer>() {
					@Override
					public void updateItem(Integer item, boolean empty) {

						if (item != null) {
							CheckBox ckbox = new CheckBox();
							ckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
								@Override
								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
										Boolean newValue) {
									System.out.println(getIndex());
									if (newValue.booleanValue() == true) {
										// Cart_TableView.getSelectionModel().getSelectedItem().setCart_check(1);
										// Cart_TableView.getItems().get(getIndex()).setCart_check(0);
										check = 1;
										CartVO cartup = new CartVO();
										int cartNum = list.get(getIndex()).getCart_no();
										cartup.setCart_no(cartNum);
										/* cartup.setCart_check(1); */

										// 삭제할때 no가 몇번인지 인식을못해서 어떤걸 업데이트 하는지 모른다.

										try {
											cart.CartSelectUpdate(cartup);
										} catch (RemoteException e1) {
											e1.printStackTrace();
										}
										System.out.println(cv.getCart_check());
										System.out.println();
										// try {
										// list = cart.AllCartListget(cv);
										// } catch (RemoteException e) {
										// e.printStackTrace();
										// }
										// System.out.println(list.size());
										// allBoxData.setAll(list);
									} else {
										// Cart_TableView.getItems().get(getIndex()).setCart_check(1);
										check = 0;
										CartVO cartup = new CartVO();
										int cartNum = list.get(getIndex()).getCart_no();
										cartup.setCart_no(cartNum);
										try {
											cart.CartCheckUpdate(cartup);
										} catch (RemoteException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}

									}
								}
							});
							
							setGraphic(ckbox);
						}
					}
				};
				return cell;
			}
		});

		Tv_Image.setCellFactory(new Callback<TableColumn<CartVO, String>, TableCell<CartVO, String>>() {

			@Override
			public TableCell<CartVO, String> call(TableColumn<CartVO, String> param) {
				TableCell<CartVO, String> cell = new TableCell<CartVO, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						if (item != null) {
							ImageView imgview = new ImageView();
							

							try {
								imgview = new ImageView(new Image(new BufferedInputStream(new FileInputStream("img/prod/"+item+".jpg"))));
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							
							imgview.setFitHeight(70);
							imgview.setFitWidth(50);
							setGraphic(imgview);
						}
					}
				};
				return cell;
			}
		});

		/*
		 * Tv_Name.setCellFactory(new Callback<TableColumn<CartVO,String>,
		 * TableCell<CartVO,String>>() {
		 * 
		 * @Override public TableCell<CartVO, String> call(TableColumn<CartVO, String>
		 * param) { TableCell<CartVO, String> cell = new TableCell<CartVO,String>(){
		 * 
		 * @Override public void updateItem(String item , boolean empty) { if(item !=
		 * null) {
		 * 
		 * Label lab = new Label(); lab.setText(item);
		 * 
		 * setGraphic(lab); } } }; return cell; } });
		 */

		Tv_Price.setCellFactory(new Callback<TableColumn<CartVO, String>, TableCell<CartVO, String>>() {

			@Override
			public TableCell<CartVO, String> call(TableColumn<CartVO, String> param) {
				TableCell<CartVO, String> cell = new TableCell<CartVO, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						if (item != null) {
							Label price = new Label();
							price.setText(item + "원");
							setGraphic(price);
						}
					}
				};
				return cell;
			}

		});

		Tv_Qty.setCellFactory(new Callback<TableColumn<CartVO, String>, TableCell<CartVO, String>>() {

			@Override
			public TableCell<CartVO, String> call(TableColumn<CartVO, String> param) {
				TableCell<CartVO, String> cell = new TableCell<CartVO, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						if (item != null) {
							Label price = new Label();
							price.setText(item + "개");
							setGraphic(price);
						}
					}
				};
				return cell;
			}

		});

		Tv_ShipPrice.setCellFactory(new Callback<TableColumn<CartVO, Integer>, TableCell<CartVO, Integer>>() {

			@Override
			public TableCell<CartVO, Integer> call(TableColumn<CartVO, Integer> param) {
				TableCell<CartVO, Integer> cell = new TableCell<CartVO, Integer>() {
					@Override
					public void updateItem(Integer item, boolean empty) {
						if (item != null) {
							Label ship = new Label();
							ship.setText(item + "원");
							setGraphic(ship);
						}
					}
				};
				return cell;
			}

		});

		Tv_Order.setCellFactory(new Callback<TableColumn<CartVO, String>, TableCell<CartVO, String>>() {

			@Override
			public TableCell<CartVO, String> call(TableColumn<CartVO, String> param) {
				TableCell<CartVO, String> cell = new TableCell<CartVO, String>() {
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

		
		
		
		Cart_TableView.setItems(allBoxData);
		
		int prod_order = 0;
		for (int i = 0; i < list.size(); i++) {
			prod_order += Integer.parseInt(list.get(i).getCart_order());
		}

		System.out.println(prod_order);
		String Prod_Ship = Integer.toString(list.get(0).getCart_shipprice());
		Prod_ship.setText(Prod_Ship + "원");

		Prod_Price.setText(prod_order + "원");

		int Order_Sum = prod_order + Integer.parseInt(Prod_Ship);

		Order_Price.setText(Order_Sum + "원");

		
		
		
		
		/*
		 * for(CartVO cartvo : list) {
		 * 
		 * int All_Price += }
		 * 
		 * 
		 * Prod_Price.setText(prod_Price+"원");
		 * 
		 * 
		 * int prod_shipprice = list.get(0).getCart_shipprice(); int order_Price =
		 * prod_shipprice + Integer.parseInt(prod_Price);
		 * Order_Price.setText(order_Price+"원");
		 */

	}

	 /**
	 * 파일의 경로를 넣으면 센터에 넣어주고 로더를 반환하는 메서드
	 * 
	 * @param fxmlURL
	 * @return loader
	 */
	public FXMLLoader changeScene(String fxmlURL) {
		MainView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainView.setCenter(parent);
		return loader;
	}


	/*
	 * @FXML public void Cart_Delete(ActionEvent event) { if(check==0) {
	 * errMsg("작업오류","삭제할 자료를 선택해주세요"); return; } CartVO delcv = }
	 *
	 */

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
