package kr.or.ddit.view.cart;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.dealHistory.IDealHistoryService;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.DealHistoryVO;

public class OrderResultController implements Initializable {

	
	
	  @FXML
	  TableView<DealHistoryVO> Orderreult_tableview;
      @FXML
      TableColumn<DealHistoryVO, Integer> Deal_no;
      @FXML
      TableColumn<DealHistoryVO, String> Order_Img;
      @FXML
      TableColumn<DealHistoryVO, String> prod_name;
      @FXML
      TableColumn<DealHistoryVO, String> prod_qty;
      @FXML
      TableColumn<DealHistoryVO, String> prod_price;
      @FXML
      TableColumn<DealHistoryVO, String> prod_ship;
      @FXML
      TableColumn<DealHistoryVO, Integer> prod_shipprice;
      @FXML
      TableColumn<DealHistoryVO, String> total_pirce;
      @FXML
      TableColumn<DealHistoryVO, String> order_date;
      
      @FXML
  	  Pagination Order_page;
      
      LoginSession session;
         
      
   // 페이징
  	private int from, to, itemsForPage;
  	ObservableList<DealHistoryVO> allBoxData, currentPageData;
  		
  	List<DealHistoryVO> list;	
  	DealHistoryVO dv = new DealHistoryVO();
  	private Registry reg;
  	private IDealHistoryService deal;
      
      public void pageset() {
  		try {
  			list = deal.SelectOrderListget(dv);
  		} catch (RemoteException e) {
  			e.printStackTrace();
  		}
  		
  		allBoxData.setAll(list);
  		Orderreult_tableview.setItems(allBoxData);
  		System.out.println(allBoxData);
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
  				Orderreult_tableview.setItems(getTableViewData(from, to));
  				return Orderreult_tableview;
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
		pageset();
	
		   Deal_no.setCellValueFactory(new PropertyValueFactory<>("deal_no"));
		   Order_Img.setCellValueFactory(new PropertyValueFactory<>("order_img"));
		   prod_name.setCellValueFactory(new PropertyValueFactory<>("order_prodname"));
		   prod_qty.setCellValueFactory(new PropertyValueFactory<>("order_prodqty"));
		   prod_price.setCellValueFactory(new PropertyValueFactory<>("order_prodprice"));
		   prod_ship.setCellValueFactory(new PropertyValueFactory<>("order_prodship"));
		   prod_shipprice.setCellValueFactory(new PropertyValueFactory<>("order_prodshipprice"));
		   total_pirce.setCellValueFactory(new PropertyValueFactory<>("order_allprice"));
		   order_date.setCellValueFactory(new PropertyValueFactory<>("deal_date"));
		
		   
		   Deal_no.setCellFactory(new Callback<TableColumn<DealHistoryVO,Integer>, TableCell<DealHistoryVO,Integer>>() {
				
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
			
		   Order_Img.setCellFactory(new Callback<TableColumn<DealHistoryVO, String>, TableCell<DealHistoryVO, String>>() {

				@Override
				public TableCell<DealHistoryVO, String> call(TableColumn<DealHistoryVO, String> param) {
					TableCell<DealHistoryVO, String> cell = new TableCell<DealHistoryVO, String>() {
						@Override
						public void updateItem(String item, boolean empty) {
							if (item != null) {
								ImageView imgview = new ImageView();
								
								try {
									imgview = new ImageView(new Image(
											new BufferedInputStream(new FileInputStream("img/prod/" + item + ".jpg"))));
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
			

		   prod_name.setCellFactory(new Callback<TableColumn<DealHistoryVO,String>, TableCell<DealHistoryVO,String>>() {
				
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
			

		   prod_qty.setCellFactory(new Callback<TableColumn<DealHistoryVO,String>, TableCell<DealHistoryVO,String>>() {
				
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
			
		   prod_price.setCellFactory(new Callback<TableColumn<DealHistoryVO,String>, TableCell<DealHistoryVO,String>>() {
				
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
			
		   prod_shipprice.setCellFactory(new Callback<TableColumn<DealHistoryVO,Integer>, TableCell<DealHistoryVO,Integer>>() {
				
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
			
			

		   total_pirce.setCellFactory(new Callback<TableColumn<DealHistoryVO,String>, TableCell<DealHistoryVO,String>>() {
				
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
			
			Orderreult_tableview.setItems(allBoxData); 
	}

}
