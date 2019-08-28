package kr.or.ddit.view.prod;

import java.io.BufferedInputStream;
import java.io.File;
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

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import kr.or.ddit.service.prod.IProdService;
import kr.or.ddit.vo.ProdVO;

public class FoodProdController implements Initializable {

	@FXML Pagination page;
	@FXML HBox hbox;
	
	ImageView imageView;
	Registry reg;
	
	IProdService prod;
	List<ProdVO> list;

	
	private int from , to , itemsForPage;
	ObservableList<ProdVO> allBoxData;
	ObservableList<VBox> currentPageData;
	@FXML BorderPane MainView;
	@FXML ImageView Popups;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry(8888);
			prod = (IProdService) reg.lookup("prod");
			list = prod.FoodProdList();
			allBoxData = FXCollections.observableArrayList(list);

		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	
		
		
		pageSet();
		
		Image image = null;
		try {
			image = new Image(new BufferedInputStream(new FileInputStream("img/prod/popup.jpg")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Popups.setImage(image);
		
		
	}


	
	private void pageSet() {
		itemsForPage = 3;
		System.out.println(allBoxData.size());

		int totItemCnt = allBoxData.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		page.setPageCount(totPageCnt);

		page.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;

				ObservableList<VBox> data = getBoxViewData(from, to);
				hbox.getChildren().clear();
				hbox.getChildren().addAll(data);
				return hbox;
			}
		});
	}
	
	private ObservableList<VBox> getBoxViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		int totSize = allBoxData.size();

		for (int i = from; i <= to && i < totSize; i++) {
			ProdVO cv = allBoxData.get(i);
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			HBox.setMargin(vbox, new Insets(20));
			
			try {
				imageView = new ImageView(new Image(new BufferedInputStream(new FileInputStream("img/prod/"+cv.getProd_photo()+".jpg"))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			imageView.setFitWidth(200);
			imageView.setFitHeight(200);

			Label name = new Label();
			name.setText(cv.getProd_name());
			name.setFont(new Font(20));
			

			Label price = new Label();
			price.setText((cv.getProd_price()+"원"));
			price.setFont(new Font(15));

			

			

			vbox.getChildren().addAll(imageView, name, price);
			vbox.setOnMouseClicked(e->ProdDetail(cv.getProd_no()));
			currentPageData.add(vbox);
		}
		return currentPageData;
	}


	private void ProdDetail(int prod_no) {

		ProdDetailController.prod_no = prod_no;
		System.out.println(ProdDetailController.prod_no);
		changeScene("/kr/or/ddit/view/prod/ProdDetail.fxml");
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
