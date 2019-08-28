package kr.or.ddit.view.board;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import kr.or.ddit.service.post.IPostService;
import kr.or.ddit.vo.PostVO;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;

public class FreeMainController implements Initializable {

	public static int postNum = 0;

	@FXML
	ComboBox<String> searchSort;
	@FXML
	TableView<PostVO> freeTable;
	@FXML
	TableColumn<PostVO, Integer> num;
	@FXML
	TableColumn<PostVO, String> title;
	@FXML
	TableColumn<PostVO, String> name;
	@FXML
	TableColumn<PostVO, Date> date;
	@FXML
	TextField searchText;
	@FXML
	JFXButton searchBtn;
	@FXML
	Pagination pagination;

	SimpleDateFormat smf = new SimpleDateFormat("YYYY년 MM월 dd일");
	
	public static BorderPane mainView;
	Parent parent;
	IPostService post;
	Registry reg;

	private int from, to, itemsForPage;
	private ObservableList<PostVO> currentPageData;
	private ObservableList<PostVO> data;

	@FXML JFXButton openTalk;

	public FXMLLoader changeScene(String fxmlURL) {
		CommunityMainController.MainView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommunityMainController.MainView.setCenter(parent);
		return loader;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
//		PostWriteController.MainView = freeMainView;
		
		searchSort.getItems().addAll("제목+내용", "제목", "내용");
		searchSort.setValue("제목+내용");

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			post = (IPostService) reg.lookup("post");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		try {
			data = FXCollections.observableArrayList(post.allPostList(2));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		tableSet(data);

		/* 게시판 글 목록 클릭시 상세 게시물 확인 */
		freeTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (freeTable.getSelectionModel().isEmpty()) {
					return;
				} else {
					PostVO pv = freeTable.getSelectionModel().getSelectedItem();
					postNum = freeTable.getSelectionModel().getSelectedItem().getPost_no();
					changeScene("../post/BoardPost.fxml");
				}
			}
		});
	}

	public void tableSet(ObservableList<PostVO> data) {

		num.setCellValueFactory(new PropertyValueFactory<>("post_no"));
		num.setStyle("-fx-alignment:center;");
		name.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		name.setStyle("-fx-alignment:center;");
		title.setCellValueFactory(new PropertyValueFactory<>("post_title"));
		title.setStyle("-fx-alignment:center;");
		date.setCellValueFactory(new PropertyValueFactory<>("post_date"));
		date.setStyle("-fx-alignment:center;");
		
		//날짜 형식 변경------------------------------------------------------------
		date.setCellFactory(new Callback<TableColumn<PostVO, Date>, TableCell<PostVO, Date>>() {
			@Override
			public TableCell<PostVO, Date> call(TableColumn<PostVO, Date> param) {
				TableCell<PostVO, Date> cell = new TableCell<PostVO, Date>() {
					@Override
					protected void updateItem(Date item, boolean empty) {
						if (item != null) {
							Label label = new Label(smf.format(item));	
							setGraphic(label);
						}
					}
				};
				return cell;
			}
		});
		//--------------------------------------------------------------------------
		
		freeTable.setItems(data);

		// 페이지네이션--------------------------------------------------------------
		itemsForPage = 7; // 한페이지에 보여줄 항목 수 설정
		int totItemCnt = data.size();
		int totoPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		pagination.setPageCount(totoPageCnt);

		pagination.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				freeTable.setItems(getTableViewData(from, to));
				return freeTable;
			}
		});
	}

	@FXML
	public void searchBtnClick() {
		PostVO pv = new PostVO();
		if (searchSort.getValue() == "제목+내용") {
			pv.setBoard_no(2);
			pv.setPost_content(searchText.getText());

			try {
				data = FXCollections.observableArrayList(post.searchPost(pv));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tableSet(data);

		} else if (searchSort.getValue() == "제목") {

			pv.setBoard_no(2);
			pv.setPost_content(searchText.getText());
			try {
				data = FXCollections.observableArrayList(post.searchTitle(pv));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tableSet(data);

		} else if (searchSort.getValue() == "내용") {

			pv.setBoard_no(2);
			pv.setPost_content(searchText.getText());

			try {
				data = FXCollections.observableArrayList(post.searchContent(pv));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tableSet(data);
		}

	}


	@FXML
	public void insertBtnClick(ActionEvent event) {
		FreePostWriteController.boardNum = 2;
		changeScene("FreePostWriteMain.fxml");
	}

	/**
	 * 페이지네이션에 필요한 메서드
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	private ObservableList<PostVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = data.size();
		for (int i = from; i <= to && i < totSize; i++) {
			currentPageData.add(data.get(i));
		}
		return currentPageData;
	}

}
