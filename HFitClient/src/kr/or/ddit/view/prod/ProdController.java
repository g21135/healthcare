package kr.or.ddit.view.prod;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.prod.IProdService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ProdVO;
import javafx.scene.layout.BorderPane;

public class ProdController implements Initializable{


	//이미지
	@FXML
	ImageView ImageView1;
	@FXML
	ImageView ImageView2;
	@FXML
	ImageView ImageView3;
	@FXML
	ImageView ImageView4;
	@FXML
	ImageView ImageView5;
	@FXML
	ImageView ImageView6;
	
	
	//button
	@FXML
	JFXButton Button1;
	@FXML
	JFXButton Button2;
	@FXML
	JFXButton Button3;
	@FXML
	JFXButton Button4;
	@FXML
	JFXButton Button5;
	@FXML
	JFXButton Button6;
	
	//text
	@FXML
	Text Text1;
	@FXML
	Text Text2;
	@FXML
	Text Text3;
	@FXML
	Text Text4;
	@FXML
	Text Text5;
	@FXML
	Text Text6;
	
	@FXML
	AnchorPane ProdMain;
	
	
	private Registry reg;
	private IProdService prod;
	
	ArrayList<ProdVO> newlist;
	ArrayList<ProdVO> qtylist;
//	MemberVO mv = LoginSession.memSession;
//	String mem_id = mv.getMem_id();
	@FXML BorderPane MainView;
	private int prod_no;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
		
			reg = LocateRegistry.getRegistry("localhost", 8888);
			prod = (IProdService) reg.lookup("prod");
			
			
			
			
			
			
			newlist = (ArrayList<ProdVO>) prod.ProdList();
			
			
			System.out.println("rmi성공");
			System.out.println(newlist.size());
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		ImageView[] arr_Prod_Img = {
				ImageView1,ImageView2,ImageView3,ImageView4,ImageView5,ImageView6
		};
		
		JFXButton[] arr_Prod_Name = {
				Button1,Button2, Button3, Button4,Button5,Button6
		};
		
		Text[] arr_Prod_Price = {
				Text1,Text2,Text3,Text4,Text5,Text6
		};

		setting(arr_Prod_Img,arr_Prod_Name,arr_Prod_Price,newlist);
		
		
		
		clickbutton(arr_Prod_Name,newlist);
}

	private void clickbutton(JFXButton[] arr_Prod_Name, ArrayList<ProdVO> list) {
		for(int i = 0; i< arr_Prod_Name.length;i++) {
			if(i<list.size()) {
				int j=i;
				arr_Prod_Name[i].setOnAction(e->ProdDetail(list.get(j).getProd_no()));
			}
		}
	}
	
	@FXML
	public void ProdDetail(int prod_no) {
		
		ProdDetailController.prod_no = prod_no;
		System.out.println(ProdDetailController.prod_no);
		changeScene("/kr/or/ddit/view/prod/ProdDetail.fxml");
	}

/*	public void  ProdDetail(int prod_no) {
		
		ProdDetailController.get_prod_no = prod_no;
		changeScene("/kr/or/ddit/view/prod/ProdDetail.fxml");
	}*/

	/**
	 * 정렬 메서드
	 * @param arr_Prod_Img
	 * @param arr_Prod_Name
	 * @param arr_Prod_Price
	 * @param list
	 */
	private void setting(ImageView[] arr_Prod_Img, JFXButton[] arr_Prod_Name, Text[] arr_Prod_Price, ArrayList<ProdVO> list) {

		
		for(int i=0; i< arr_Prod_Img.length;i++) {
			arr_Prod_Img[i].setImage(null);
			arr_Prod_Name[i].setText(null);
			arr_Prod_Price[i].setText(null);
		}
		System.out.println(list.size());
		int int_list =list.size();
		if(int_list>6) {
			int_list =6;
		}
		
		String Img = "";
		String Name = "";
		File ImgFile =null;
		Image image =null;
		String url = "/kr/or/ddit/view/prod/";
		int Price = 0;
		
		for(int i =0; i< int_list;i++) {
			Name = list.get(i).getProd_name();
			Price = list.get(i).getProd_price();
			
			arr_Prod_Name[i].setText(Name);
			arr_Prod_Price[i].setText(Price+"원");
			if(!Img.equals("")||Img !=null) {
				Img = list.get(i).getProd_photo();
				image = new Image(url+Img+".jpg");
				/*ImgFile = new File(Img);
				System.out.println(ImgFile.toURI().toString());*/
				//image = new Image(Img.toString()+".jpg");
			}
			arr_Prod_Img[i].setImage(image);
		}
		
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
