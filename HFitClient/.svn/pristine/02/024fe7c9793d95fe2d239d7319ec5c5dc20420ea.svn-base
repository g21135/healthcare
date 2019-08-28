package kr.or.ddit.view.admin;

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
import com.jfoenix.controls.JFXComboBox;

import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kr.or.ddit.service.cart.ICartService;
import kr.or.ddit.service.prod.IProdService;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.ProdVO;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProdManegermentController implements Initializable {

	@FXML TableView<ProdVO> prod_tableview;
	
	@FXML TableColumn<ProdVO, String> prod_name;
	@FXML TableColumn<ProdVO, Integer> prod_price;
	@FXML TableColumn<ProdVO, Integer> prod_qty;
	@FXML TableColumn<ProdVO, Integer> prod_ramain;
	@FXML TableColumn<ProdVO, Integer> prod_totalprice;
	@FXML TableColumn<ProdVO,Integer> prod_sleect;
	@FXML TableColumn <ProdVO , Integer>prod_no;
	@FXML TableColumn<ProdVO, String> prod_content;
	
	
	@FXML JFXComboBox<String> prod_div;
	ICartService cart;
	
	
	@FXML JFXButton prod_add;
	@FXML JFXButton prod_delete;
	
	@FXML Pagination pag_prod;
	
	int total =0;
	Registry reg;
	IProdService prod;
	public int check = 0;
	List<ProdVO> list;
	int Prod_no =0;
	ProdVO pv = new ProdVO();
	// 페이징
		private int from, to, itemsForPage;
		ObservableList<ProdVO> allBoxData, currentPageData;

		@FXML BorderPane mainView;
		
		
		
		public void pageset() {
			
			try {
				list = prod.ProdMagerment();
				
			} catch (RemoteException e) {
				e.printStackTrace();
			}

			allBoxData.setAll(list);
			
			itemsForPage = 100;
			System.out.println(allBoxData.size());

			int totItemCnt = allBoxData.size();
			int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
			pag_prod.setPageCount(totPageCnt);
			
			pag_prod.setPageFactory(new Callback<Integer, Node>() {
				
				@Override
				public Node call(Integer pageIndex) {
					from = pageIndex * itemsForPage;
					to = from + itemsForPage - 1;
					prod_tableview.setItems(getTableViewData(from, to));
					return prod_tableview;
				}
			});
		}
		private ObservableList<ProdVO> getTableViewData(int from, int to) {
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
			prod = (IProdService) reg.lookup("prod");
			
			System.out.println("rmI 성공");
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		prod_div.getItems().addAll("전체보기","식품","운동용품");
		prod_div.setValue("전체보기");
		allBoxData = FXCollections.observableArrayList();
		
		pageset();
		
	
		prod_no.setCellValueFactory(new PropertyValueFactory<>("prod_no"));
		prod_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		prod_content.setCellValueFactory(new PropertyValueFactory<>("prod_content"));
		prod_price.setCellValueFactory(new PropertyValueFactory<>("prod_price"));
		prod_qty.setCellValueFactory(new PropertyValueFactory<>("remain_outstock"));
		prod_ramain.setCellValueFactory(new PropertyValueFactory<>("remain_curstock"));
		prod_totalprice.setCellValueFactory(new PropertyValueFactory<>("prod_price"));
		prod_sleect.setCellValueFactory(new PropertyValueFactory<>("prod_check"));
			
		prod_add.setOnAction(e->{
			try {
				FXMLLoader loader = new FXMLLoader((getClass().getResource("prod_Add.fxml")));
				Parent parent = loader.load();
				Stage stage = new Stage(StageStyle.UTILITY);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("추가 정보 입력");
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.showAndWait();
				
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		prod_delete.setOnAction(e->{
			
			int cnt = 0;
			try {
				
				
				cnt = prod.DeleteProd();
				System.out.println(cnt);
			} catch (Exception e1) {
			}
			if (cnt <= 0) {
				errMsg("삭제에러", "삭제할 물품을 선택해주세요");
				return;
			} else {
				infoMsg("삭제성공", "해당물품이 삭제되었습니다");
				
				allBoxData.clear();
				prod_tableview.refresh();
				System.out.println(allBoxData);
				pageset();
			}
		});
		
		prod_sleect.setCellFactory(new Callback<TableColumn<ProdVO, Integer>, TableCell<ProdVO, Integer>>() {
			@Override
			public TableCell<ProdVO, Integer> call(TableColumn<ProdVO, Integer> param) {
				TableCell<ProdVO, Integer> cell = new TableCell<ProdVO, Integer>() {
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
										check = 1;
										ProdVO produp = new ProdVO();
										int prodNum = list.get(getIndex()).getProd_no();
										Prod_no = list.get(getIndex()).getProd_no();
										System.out.println("no:"+Prod_no);
										System.out.println(prodNum);
										produp.setProd_no(prodNum);
										try {
											prod.ProdSelectUpdate(produp);
										} catch (RemoteException e1) {
											e1.printStackTrace();
										}
										
								
									} else {
										
										check = 0;
										ProdVO produp = new ProdVO();
										int prodNum = list.get(getIndex()).getProd_no();
										produp.setProd_no(prodNum);
										try {
											prod.ProdCheckUpdate(produp);
										} catch (RemoteException e1) {
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
		
		
		prod_totalprice.setCellFactory(new Callback<TableColumn<ProdVO, Integer>, TableCell<ProdVO, Integer>>() {

			@Override
			public TableCell<ProdVO, Integer> call(TableColumn<ProdVO, Integer> param) {
				TableCell<ProdVO, Integer> cell = new TableCell<ProdVO, Integer>() {
					@Override
					public void updateItem(Integer item, boolean empty) {
						if (item != null) {
							Label price = new Label();
							int avg = total * item;
							price.setText(avg+"원");
							setGraphic(price);
						}
					}
				};
				return cell;
			}

		});
		
		
	
		prod_ramain.setCellFactory(new Callback<TableColumn<ProdVO, Integer>, TableCell<ProdVO, Integer>>() {

			@Override
			public TableCell<ProdVO, Integer> call(TableColumn<ProdVO, Integer> param) {
				TableCell<ProdVO, Integer> cell = new TableCell<ProdVO, Integer>() {
					@Override
					public void updateItem(Integer item, boolean empty) {
						if (item != null) {
							Label price = new Label();
							price.setText(item+"개");
							setGraphic(price);
						}
					}
				};
				return cell;
			}

		});

		
		
		prod_qty.setCellFactory(new Callback<TableColumn<ProdVO, Integer>, TableCell<ProdVO, Integer>>() {

			@Override
			public TableCell<ProdVO, Integer> call(TableColumn<ProdVO, Integer> param) {
				TableCell<ProdVO, Integer> cell = new TableCell<ProdVO, Integer>() {
					@Override
					public void updateItem(Integer item, boolean empty) {
						if (item != null) {
							Label qty = new Label();
							qty.setText(item+"개");
							setGraphic(qty);
							total = item;
						}
					}
				};
				return cell;
			}

		});
		
		
		prod_no.setCellFactory(new Callback<TableColumn<ProdVO, Integer>, TableCell<ProdVO, Integer>>() {

			@Override
			public TableCell<ProdVO, Integer> call(TableColumn<ProdVO, Integer> param) {
				TableCell<ProdVO, Integer> cell = new TableCell<ProdVO, Integer>() {
					@Override
					public void updateItem(Integer item, boolean empty) {
						if (item != null) {
							Label price = new Label();
							price.setText("HF00"+item);
							setGraphic(price);
						}
					}
				};
				return cell;
			}

		});

		
		
		prod_price.setCellFactory(new Callback<TableColumn<ProdVO, Integer>, TableCell<ProdVO, Integer>>() {

			@Override
			public TableCell<ProdVO, Integer> call(TableColumn<ProdVO, Integer> param) {
				TableCell<ProdVO, Integer> cell = new TableCell<ProdVO, Integer>() {
					@Override
					public void updateItem(Integer item, boolean empty) {
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

		
		prod_tableview.setItems(allBoxData);
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
