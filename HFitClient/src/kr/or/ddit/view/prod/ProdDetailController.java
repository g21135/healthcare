package kr.or.ddit.view.prod;



import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.cart.ICartService;
import kr.or.ddit.service.dealHistory.IDealHistoryService;
import kr.or.ddit.service.prod.IProdService;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.DealHistoryVO;
import kr.or.ddit.vo.ProdVO;
import javafx.scene.layout.BorderPane;

public class ProdDetailController implements Initializable {

	public static int prod_no=0;
	@FXML
	ImageView Prod_Img;
	@FXML
	Text Prod_Name;
	@FXML
	Text Prod_Pirce;
	@FXML
	Text Prod_Content;
	@FXML
	Text Prod_Sum;
	@FXML 
	JFXComboBox<String> Prod_Qty;
	@FXML
	JFXButton Prod_Cart;
	@FXML
	JFXButton Prod_Order;
	
	private Registry reg;
	private IProdService prod;
	private ICartService cart;
	private IDealHistoryService deal;
	List<ProdVO> ProdList;
	@FXML BorderPane MainView;

	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			prod = (IProdService) reg.lookup("prod");
			cart = (ICartService) reg.lookup("cart");
			deal = (IDealHistoryService) reg.lookup("deal");
			
			System.out.println( " prod  " + prod);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println(prod_no);
			ProdList = prod.ProdSelectList(prod_no);
			System.out.println(ProdList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		String prod_img = ProdList.get(0).getProd_photo();
		System.out.println(prod_img);
		String prod_name = ProdList.get(0).getProd_name();
		String prod_content  = ProdList.get(0).getProd_content();
		int prod_Price = ProdList.get(0).getProd_price();
		System.out.println(prod_name);
		
		Prod_Name.setText(prod_name);
		Prod_Pirce.setText(Integer.toString(prod_Price));
		Prod_Content.setText(prod_content);
		
		Image image = null;
		try {
			image = new Image(new BufferedInputStream(new FileInputStream("img/prod/"+prod_img+".jpg")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Prod_Img.setImage(image);
		
		
		ObservableList<String> list = FXCollections.observableArrayList();
		
		for(int i=1;i<=50;i++) {
			String str_i = Integer.toString(i);
			list.add(str_i);
		}
		
		Prod_Qty.setItems(list);

	
	}
	
	public void comboChanged(ActionEvent event){
		CartVO cartvo = new CartVO();
        System.out.println("chose the " + Prod_Qty.getValue().toString());
        int get = Integer.parseInt(Prod_Qty.getValue().toString());
        System.out.println(get);
        int price = Integer.parseInt(Prod_Pirce.getText());
        System.out.println(price);
        
        int Mult = get * price;
        Prod_Sum.setText(Integer.toString(Mult));
    }
	
	
	private String Money() {
		String Qty_Select = Prod_Qty.getSelectionModel().getSelectedItem();
		
		String Prod_price = Prod_Pirce.getText();
		
		
		int All_Prod_Price = Integer.parseInt(Qty_Select) * Integer.parseInt(Prod_price) ;
		return Integer.toString(All_Prod_Price);
	}
	
	@FXML
	public void AddCart(ActionEvent event) throws RemoteException{
		if(Prod_Qty.getSelectionModel().getSelectedItem() ==null) {
			errMsg("경고!", "수량을 선택하세요!");
			return;
		}
		
		String cart_order = Money();
		String prod_img = ProdList.get(0).getProd_photo();
		
		CartVO cartvo = new CartVO();
		cartvo.setProd_no(prod_no);
		cartvo.setCart_name(Prod_Name.getText());
		System.out.println(Prod_Name.getText());
		cartvo.setCart_qty(Prod_Qty.getSelectionModel().getSelectedItem());
		cartvo.setCart_photo(prod_img);
		cartvo.setCart_content(Prod_Content.getText());
		cartvo.setCart_price(Prod_Pirce.getText());
		cartvo.setCart_order(cart_order);
		cartvo.setMem_id(LoginSession.memSession.getMem_id());
		
		int cart_sum = Integer.parseInt(cart_order);
		
		if(cart_sum > 50000) {
			cartvo.setCart_ship("무료배송");
			cartvo.setCart_shipprice(0);
		}
		else if (cart_sum < 50000) {
			cartvo.setCart_ship("유료배송");
			cartvo.setCart_shipprice(2500);
		}
		
		int cnt =0;
		cnt = cart.InsertCart(cartvo);
		if(cnt == 0 ) {
			errMsg("장바구니에러", "장바구니에 들어가지 않았습니다.");
		}else {
			infoMsg("장바구니성공", "물품이 장바구니에 들어갔습니다.");
		}
			
//		changeScene("/kr/or/ddit/view/cart/CartView.fxml");
	}
	
	@FXML
	public void NowOrder(ActionEvent event) throws RemoteException{
		if(Prod_Qty.getSelectionModel().getSelectedItem() ==null) {
			errMsg("경고!", "수량을 선택하세요!");
			return;
}
		String cart_order = Money();
		
		String prod_img = ProdList.get(0).getProd_photo();
		DealHistoryVO dealhistroyvo = new DealHistoryVO();
		dealhistroyvo.setMem_id(LoginSession.memSession.getMem_id());
		dealhistroyvo.setOrder_prodname(Prod_Name.getText());
		dealhistroyvo.setOrder_prodqty(Prod_Qty.getSelectionModel().getSelectedItem());
		dealhistroyvo.setOrder_prodprice(Prod_Pirce.getText());
		dealhistroyvo.setOrder_img(prod_img);
		
		int cart_sum = Integer.parseInt(cart_order);
		if(cart_sum > 50000) {
			dealhistroyvo.setOrder_prodship("무료배송");
			dealhistroyvo.setOrder_prodshipprice(0);
		}
		else if (cart_sum < 50000) {
			dealhistroyvo.setOrder_prodship("유료배송");
			dealhistroyvo.setOrder_prodshipprice(2500);
		}
		dealhistroyvo.setOrder_allprice(cart_order);
		
		deal.InsertOrder(dealhistroyvo);
		
		changeScene("/kr/or/ddit/view/cart/OrderView.fxml");
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

}
