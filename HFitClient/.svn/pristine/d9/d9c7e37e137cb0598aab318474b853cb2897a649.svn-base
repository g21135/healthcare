package kr.or.ddit.view.dealHistroy;

import java.awt.Label;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import kr.or.ddit.service.card.ICardService;
import kr.or.ddit.service.dealHistory.IDealHistoryService;
import kr.or.ddit.vo.ProdVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXButton;

public class TableViewController extends TableView implements Initializable {

	
	@FXML
	TableView DealHistroyList;
	@FXML 
	TableColumn Deal_Select;
	@FXML 
	TableColumn<ProdVO, Integer> Deal_No;
	@FXML 
	TableColumn<ProdVO, String> Deal_Content;
	@FXML 
	TableColumn Deal_Qty;
	@FXML 
	TableColumn Deal_ProdPrice;
	@FXML 
	TableColumn Deal_Ship;
	@FXML 
	TableColumn Deal_OrderPrice;
	@FXML 
	TableColumn Deal_OrderDate;
	
	private Registry Reg;
	private IDealHistoryService DealHistoryService;
	@FXML TableView Cart_TableView;
	@FXML TableColumn Tv_Select;
	@FXML TableColumn Tv_Prod;
	@FXML TableColumn Tv_Price;
	@FXML TableColumn Tv_Qty;
	@FXML TableColumn Tv_Order;
	@FXML Text Prod_Price;
	@FXML Text Order_Price; 
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			Reg = LocateRegistry.getRegistry("localhost", 8888);
			DealHistoryService = (IDealHistoryService) Reg.lookup("DealHistroy");
			System.out.println("rmi성공");
			

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		Deal_No.setCellFactory(new PropertyValueFactory("ProdNo"));
		
		Deal_Content.setCellFactory(new PropertyValueFactory("Content"));
		
		Deal_Qty.setCellFactory(new PropertyValueFactory("Qty"));
		
		Deal_ProdPrice.setCellFactory(new PropertyValueFactory("ProdPrice"));
		
		Deal_Ship.setCellFactory(new PropertyValueFactory("Ship"));
		
		Deal_OrderPrice.setCellFactory(new PropertyValueFactory("OrderPrice"));
		
		Deal_OrderDate.setCellFactory(new PropertyValueFactory("OrderDate"));
		
		
		Deal_No.setCellFactory(new Callback<TableColumn<ProdVO,Integer>, TableCell<ProdVO,Integer>>() {
			
			@Override
			public TableCell<ProdVO, Integer> call(TableColumn<ProdVO, Integer> param) {
				TableCell<ProdVO, Integer> cell = new TableCell<ProdVO,Integer>(){
					
					@Override
					public void updateItem(Integer item,boolean empty) {
						if(item!=null) {
							HBox hbox = new HBox();
							hbox.setSpacing(10);
							CheckBox cbox = new CheckBox();
							
							Text text = new  Text();
							text.setText("444433331113333");
							
							hbox.getChildren().addAll(cbox,text);
							setGraphic(hbox);
						}
					}
				};
				
				return cell;
			}
		});
		
		Deal_Content.setCellFactory(new Callback<TableColumn<ProdVO,String>, TableCell<ProdVO,String>>() {
			
			@Override
			public TableCell<ProdVO, String> call(TableColumn<ProdVO, String> param) {
				TableCell<ProdVO, String> cell = new TableCell<ProdVO,String>(){
					
					@Override
					public void updateItem(String item,boolean empty) {
						if(item!=null) {
							
							HBox hbox = new HBox();
							hbox.setSpacing(10);
							VBox vbox = new VBox();
							
							
							ImageView imageView = new ImageView();
							imageView.setFitHeight(50);
							imageView.setFitWidth(50);
							
							
							hbox.getChildren().addAll(imageView);
							setGraphic(hbox);
						}
						}
					};
					
					return cell;
				}
			});
						
		
	}

}
