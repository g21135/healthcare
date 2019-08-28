package kr.or.ddit.view.dealHistroy;


import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class DealHistroyController extends ListView<String> implements Initializable {

	@FXML
	private JFXListView<String> ListView;
	
	ObservableList<String> ObList = FXCollections.observableArrayList("상품명1","상품명2","상품명3");
	
	static ObservableList<Integer> ComList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10);
	
	static class Cell extends ListCell<String>
	{
		HBox hbox = new HBox();
		CheckBox check = new CheckBox();
		Label label =new Label("");
		Label label2 =new Label("가격1");
		Label label3 =new Label("가격2");
		Label label4 =new Label("구매일자");
		
		ComboBox<Integer> combo = new ComboBox<Integer>(ComList);
		TextField textfield = new TextField();
		
		Pane pane = new Pane();
		Image ListImage = new Image("/kr/or/ddit/view/dealHistroy/Health1.jpg");
		ImageView img = new ImageView(ListImage);
	
	
	public Cell() {
		super();
		
		hbox.getChildren().addAll(check,img,label,combo,label2,label3,label4);
		
		hbox.setHgrow(pane, Priority.ALWAYS);
	}
	
	public void updateItem(String name, boolean empty) {
		super.updateItem(name, empty);
		setText(null);
		setGraphic(null);
		
		if(name != null && !empty) {
			label.setText(name);
			setGraphic(hbox);
		}
	}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ListView.setItems(ObList);
		
		
		


		
		ListView.setCellFactory(param -> new Cell());
		
			
		
	}
		
	
}
	/*
	
	private Registry reg;
	private IBuylistService ibuy;
	private int from, to, itemsForPage;
	private ObservableList<RequestVO> allTableData ,currentPageData;
	
	private RequestVO rvo = new RequestVO();
	ArrayList<RequestVO> buylist = new ArrayList<>();
	
	String backEat_name = "";
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tb_allbuylist.getStylesheets().add(getClass().getResource("/tableview.css").toString());
		try {
			// 1. 등록된 서버를 찾기 위해 Registry객체를 생성한 후, 사용할 객체를 불러온다.
			reg = LocateRegistry.getRegistry("localhost", 8429);
			// 설정한 서버를 찾는 메소드 lookup
			
			ibuy = (IBuylistService) reg.lookup("buylist");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		String loginid = LoginSession.session.getMem_no(); // 로그인 세션에 담긴 정보 
		rvo.setMem_no(loginid);
		
		col_no.setCellValueFactory(new PropertyValueFactory<>("rownum"));
		col_name.setCellValueFactory(new PropertyValueFactory<>("eat_name"));
		col_cnt.setCellValueFactory(new PropertyValueFactory<>("order_qty"));
		col_price.setCellValueFactory(new PropertyValueFactory<>("order_price"));
		col_buydate.setCellValueFactory(new PropertyValueFactory<>("order_date"));
		col_st.setCellValueFactory(new PropertyValueFactory<>("order_st"));
		//-----------------------------------------------------
		
		// 전체 정보 가져오기
		allTableData = FXCollections.observableArrayList();
		
		try {
			buylist = (ArrayList<RequestVO>) ibuy.getAllBuyList(rvo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
				
 		allTableData.setAll(buylist);
		tb_allbuylist.setItems(allTableData);
		
		pagenation();
		
		
		btn_return.setOnAction(e-> {
			if(tb_allbuylist.getSelectionModel().isEmpty()) {
	           errMsg("작업 오류", "환불 할 내역을 선택한 후 클릭하세요.");
	           return;
		    }
			if(tb_allbuylist.getSelectionModel().getSelectedItem().getOrder_st().equals("취소")) {
				errMsg("작업 오류", "이미 취소 된 내역입니다.");
		        return;
			}
			
			Stage dialog = new Stage(StageStyle.UTILITY);
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.initOwner(btn_return.getScene().getWindow());
			
			Parent parent = null;

			try {
	           parent = FXMLLoader.load(getClass().getResource("payback.fxml"));
	        }catch(IOException ex) {
	           ex.printStackTrace();
	        }
			Button btn_payback = (Button) parent.lookup("#btn_payback"); // 환불 버튼
			Button btn_back = (Button) parent.lookup("#btn_back"); // 돌아가기 버튼
			
			btn_payback.setOnAction(e1 -> {
				
				backEat_name = tb_allbuylist.getSelectionModel().getSelectedItem().getEat_name();
				int tb_index = page_buylist.getCurrentPageIndex() * itemsForPage + 
						tb_allbuylist.getSelectionModel().getSelectedIndex();
				RequestVO rvo2 = new RequestVO();
				rvo2 = buylist.get(tb_index);
				rvo2.getOrder_no();
				rvo.setOrder_no(rvo2.getOrder_no());
				
				int cnt = 0;
				try {
					cnt = ibuy.updateOrder_st(rvo);
				} catch (RemoteException e2) {
					e2.printStackTrace();
				}
				if(cnt > 0) {
					infoMsg("환불 완료", "환불이 완료되셨습니다. 문자를 확인해주세요");
					SendSMS();
					dialog.close();
				} else {
					errMsg("환불안됫어 ㅠㅠ", "이상하다 뭔가");
					return;
				}
				
				try {
					buylist = (ArrayList<RequestVO>) ibuy.getAllBuyList(rvo);
				} catch (RemoteException e5) {
					e5.printStackTrace();
				}
						
				allTableData.setAll(buylist);
				tb_allbuylist.setItems(allTableData);
				
				pagenation();
				
			});
			
			
			btn_back.setOnAction(event -> dialog.close()); //돌아가기버튼구현
			
			Scene scene = new Scene(parent);
            
            dialog.setScene(scene);
            dialog.setResizable(false);
            dialog.show();
		});
		
		
	}

	   private void errMsg(String headerText, String msg) {
		  Alert errAlert = new Alert(AlertType.ERROR);
		  errAlert.setTitle("오류");
	      errAlert.setHeaderText(headerText);
	      errAlert.setContentText(msg);
	      errAlert.showAndWait();
	   }
	   
	   private void infoMsg(String headerText, String msg) {
	      Alert errAlert = new Alert(AlertType.INFORMATION);
	      errAlert.setTitle("정보 확인");
	      errAlert.setHeaderText(headerText);
	      errAlert.setContentText(msg);
	      errAlert.showAndWait();
	   }

	   private ObservableList<RequestVO> getTableViewData(int from, int to){
			
			currentPageData = FXCollections.observableArrayList(); 
			int totSize = allTableData.size();
			for (int i = from; i<= to && i < totSize; i++) {
				currentPageData.add(allTableData.get(i));
			}
			
			return currentPageData;
		}
		private Node createPage(int pageIndex) {
			
			from = pageIndex * itemsForPage;
			to = from + itemsForPage -1;
			tb_allbuylist.setItems(getTableViewData(from, to));
			
			return tb_allbuylist;
		}
	
		private void SendSMS() {

	        SMS sms = new SMS();

	        sms.appversion("TEST/1.0");
	        sms.charset("utf8");
	        sms.setuser("phy5687", "ddit201!");				// coolsms 계정 입력해주시면되요

	        String number[] = new String[1];            // 받을 사람 폰번호
	        number[0] = "01056518002";

	       
	        for( int i = 0 ; i < number.length ; i ++ ) {
	        	
		        SmsMessagePdu pdu = new SmsMessagePdu();
		        pdu.type = "SMS";
		        pdu.destinationAddress = number[i];
		        pdu.scAddress = "01056518002"	;       // 발신자 번호          
		        pdu.text = 
		        		" MelonPlate 결제 취소\n"
		    			+ "● 잇딜이름  : " + backEat_name + "결제가 취소 되었습니다.\n";			// 보낼 메세지 내용

		        sms.add(pdu);

		        try {
		        	
		            sms.connect();
		            sms.send();
		            sms.disconnect();

		        } catch (IOException e) {

		            System.out.println(e.toString());
		        }

		        sms.printr();
		        sms.emptyall();
		    }
	       
	    }
		
		private void pagenation() {
			itemsForPage = 10;
			
			int totPageCount = allTableData.size()%itemsForPage == 0 
					? allTableData.size()/itemsForPage  
					: allTableData.size()/itemsForPage + 1; 
					
			page_buylist.setPageCount(totPageCount);	
			page_buylist.setPageFactory(this::createPage);
		}
*/
