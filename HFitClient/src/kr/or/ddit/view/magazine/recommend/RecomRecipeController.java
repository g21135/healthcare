package kr.or.ddit.view.magazine.recommend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.recommend.IRecommendService;
import kr.or.ddit.vo.RecommendVO;

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
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

public class RecomRecipeController implements Initializable{

	@FXML BorderPane recomRecipeView;
	@FXML Pagination pagination;
	@FXML HBox hBox;
	@FXML JFXButton recomRecipe;
	@FXML JFXButton recomExer;
	@FXML JFXButton nearFit;
	@FXML JFXButton insert;
	
	ImageView imageView;
	ImageView imageView2;
	Registry reg;
	IRecommendService recomm;
	
	List<RecommendVO> list;
	ObservableList<RecommendVO> allBoxData;
	ObservableList<VBox> currentPageData;
	
	private int from, to, itemsForPage;
	
	public FXMLLoader changeScene(String fxmlURL) {
		recomRecipeView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		recomRecipeView.setCenter(parent);
		return loader;
	}
	
	public RecomRecipeController() {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		try {
			reg = LocateRegistry.getRegistry(8888);
			recomm = (IRecommendService) reg.lookup("recomm");

		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}		
		
		pageSet();
	
	}	
	
	
	private void pageSet() {
		
		try {
			list = recomm.getAllRecomDietList(1);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		allBoxData = FXCollections.observableArrayList(list);
		itemsForPage = 3;
		if(allBoxData == null) {
			System.out.println("비었당");
		}

		int totItemCnt = allBoxData.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		pagination.setPageCount(totPageCnt);

		pagination.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;

				ObservableList<VBox> data = getBoxViewData(from, to);
				hBox.getChildren().clear();
				hBox.getChildren().addAll(data);
				return hBox;
			}
		});
		
		/*if(LoginSession.memSession.getMem_id() != "root" || LoginSession.memSession.getMem_id() == null) {
			insert.setVisible(false);
		}*/
	}
	
	private ObservableList<VBox> getBoxViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		int totSize = allBoxData.size();

		for (int i = from; i <= to && i < totSize; i++) {
			
			RecommendVO rv = allBoxData.get(i);
			
			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			HBox.setMargin(vbox, new Insets(20));
				
			try {
				imageView = new ImageView(
					new Image(new BufferedInputStream(new FileInputStream("img/" + rv.getRecom_image()))));
			}catch (FileNotFoundException e) {
						e.printStackTrace();
			}
			imageView.setFitWidth(250);
			imageView.setFitHeight(300);
			
			vbox.setOnMouseClicked(e -> {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("recommendDetail.fxml"));

				Parent parent = null;
				try {
					parent = loader.load();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				Label title = (Label) parent.lookup("#title");
				ImageView imageView2 = (ImageView) parent.lookup("#imageView");
				ImageView imageView3 = (ImageView) parent.lookup("#imageView2");
				Label content = (Label) parent.lookup("#content");
				Button listBtn = (Button) parent.lookup("#listBtn");
				AnchorPane detailView = (AnchorPane) parent.lookup("#detailView");

//				title.setText(rv.getRecom_title());
//				content.setText(rv.getRecom_content());
				HBox hbox = new HBox();
				
				try {
					imageView2.setImage(
							new Image(new BufferedInputStream(new FileInputStream("img/" + rv.getRecom_image()))));
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				}
				imageView2.setFitWidth(400);
				imageView2.setFitHeight(400);
//				imageView2.setStyle("-fx-alignment : left");
				
				try {
					imageView3.setImage(
							new Image(new BufferedInputStream(new FileInputStream("img/" + rv.getRecom_content()))));
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				}
				imageView3.setFitWidth(600);
				imageView3.setFitHeight(400);
// 				imageView3.setStyle("-fx-alignment : right");
				
//				hbox.getChildren().addAll(imageView2, imageView3);
				
				

				listBtn.setOnAction(evt -> {
					FXMLLoader loader2 = new FXMLLoader(getClass().getResource("recommendRecipe.fxml"));
					Parent parent2 = null;
					try {
						parent2 = loader2.load();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					((BorderPane) detailView.getParent()).setCenter(parent2);
				});

				((BorderPane) recomRecipeView.getParent()).setCenter(parent);
			
			});

				
				
		Label name = new Label();
		name.setText(rv.getRecom_title());
		name.setFont(new Font(20));

		vbox.getChildren().addAll(imageView, name);

		currentPageData.add(vbox);
		}
		return currentPageData;	
		
		
	}	

	@FXML public void insertBtnClick() {
		if(LoginSession.memSession.getMem_id() != "root") {
			insert.setVisible(false);
		}
	}
	@FXML public void recomRecipeBtnClick() {
		recomExer.setStyle("");
		recomRecipeView.setTop(null);
		recomRecipe.setStyle("-fx-background-color: #FDF5E6;");
		changeScene("recommendRecipe.fxml");
	}
	@FXML public void recomExerBtnClick() {
		recomRecipe.setStyle("");
		recomExer.setStyle("-fx-background-color: #FDF5E6;");
		changeScene("recommendExer.fxml");
	}
	@FXML public void nearFitBtnClick() {
		recomRecipe.setStyle("");
		recomExer.setStyle("-fx-background-color: #FDF5E6;");
		changeScene("recommendMap.fxml");
	}
	
}












