package kr.or.ddit.view.admin;

import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kr.or.ddit.service.prod.IProdService;
import kr.or.ddit.vo.ProdVO;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public class PaymagermentController implements Initializable {

	
	
	
	@FXML TableView<ProdVO> pay_tableview;
	@FXML TableColumn<ProdVO, Integer> deal_no;
	@FXML TableColumn<ProdVO, Integer> prod_no;
	@FXML TableColumn<ProdVO, String> prod_name;
	@FXML TableColumn<ProdVO, String> mem_name;
	@FXML TableColumn<ProdVO, String> deal_date;
	@FXML TableColumn<ProdVO, String> mem_tel;
	IProdService prod;
	Registry reg;
	
	@FXML Pagination pag_prod;
	@FXML JFXComboBox<String> prod_div;
	
	// 페이징
	private int from, to, itemsForPage;
	ObservableList<ProdVO> allBoxData, currentPageData;
	List<ProdVO> list;
	ProdVO pv = new ProdVO();
	
	
	public void pageset() {
		
		try {
			list = prod.PayMagerment();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		allBoxData.setAll(list);
		
		itemsForPage = 10;
		System.out.println(allBoxData.size());

		int totItemCnt = allBoxData.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		pag_prod.setPageCount(totPageCnt);
		
		pag_prod.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				pay_tableview.setItems(getTableViewData(from, to));
				return pay_tableview;
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
		
		
		allBoxData = FXCollections.observableArrayList();
		prod_div.getItems().addAll("전체보기","운동","식품");
		prod_div.setValue("전체보기");
		pageset();
		
		
		
		
		deal_no.setCellValueFactory(new PropertyValueFactory<>("deal_no"));
		prod_no.setCellValueFactory(new PropertyValueFactory<>("prod_no"));
		prod_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
		mem_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		deal_date.setCellValueFactory(new PropertyValueFactory<>("deal_date"));
		mem_tel.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
		
		
		
		
		
		
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
		pay_tableview.setItems(allBoxData);
	}

}
