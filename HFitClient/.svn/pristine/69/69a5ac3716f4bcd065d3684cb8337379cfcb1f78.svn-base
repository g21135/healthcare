package kr.or.ddit.view.board.challenge;

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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.challApply.IChallApplyService;
import kr.or.ddit.service.challenge.IChallengeService;
import kr.or.ddit.service.check.ICheckService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.ChallApplyVO;
import kr.or.ddit.vo.ChallengeVO;
import kr.or.ddit.vo.CheckVO;

public class CheckBoardController implements Initializable {

	@FXML
	TableView<CheckVO> checkTable;
	@FXML
	TableColumn<CheckVO, Integer> num;
	@FXML
	TableColumn<CheckVO, String> photo;
	@FXML
	TableColumn<CheckVO, String> title;
	@FXML
	TableColumn<CheckVO, Date> date;
	@FXML
	TableColumn<CheckVO, Integer> check;

	@FXML
	TextField searchText;
	@FXML
	JFXButton searchBtn;
	@FXML
	ComboBox searchSort;

	Registry reg;
	ICheckService checkS;
	IChallApplyService challApply;
	IChallengeService chall;
	@FXML
	ComboBox<String> applyChallCombo;

	@FXML
	Pagination page;
	private int from, to, itemsForPage;
	ObservableList<CheckVO> allTableData;
	ObservableList<CheckVO> currentPageData;
	public static Integer applyChallComboNum = 0;
	@FXML JFXButton insert;
	@FXML JFXButton back;
	
	

	private void pageSet() {
		checkTable.refresh();
		ChallApplyVO cav = new ChallApplyVO();
		cav.setChall_no(applyChallComboNum);
		if(LoginSession.memSession.getMem_id().equals("root")) {
			cav.setMem_id(null);
		}
		else {
		cav.setMem_id(LoginSession.memSession.getMem_id());
		}
		try {
			allTableData = FXCollections.observableArrayList(FXCollections.observableArrayList(checkS.selectAll(cav)));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		itemsForPage = 3;

		int totItemCnt = allTableData.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		page.setPageCount(totPageCnt);

		page.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;

				ObservableList<CheckVO> data = getTableViewData(from, to);
				checkTable.setItems(data);
				return checkTable;
			}
		});
	}

	private ObservableList<CheckVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList(); // 현재 페이지 데이터 초기화
		int totSize = allTableData.size();
		for (int i = from; i <= to && i < totSize; i++) {
			currentPageData.add(allTableData.get(i));
		}
		return currentPageData;
	}

	@FXML
	public void insertBtnClick(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddCheckPost.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
			stage.setScene(scene);
			stage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
		pageSet();
	}

	@FXML
	public void searchBtnClick() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if(LoginSession.memSession.getMem_id().equals("root")) {
			insert.setVisible(false);
		}
		applyChallCombo.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				applyChallComboNum = Integer.parseInt(newValue.substring(0, 2).trim());
				pageSet();
			}
		});
		try {
			reg = LocateRegistry.getRegistry(8888);
			checkS = (ICheckService) reg.lookup("check");
			challApply = (IChallApplyService) reg.lookup("challApply");
			chall = (IChallengeService) reg.lookup("chall");
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		num.setCellValueFactory(new PropertyValueFactory<>("check_no"));
		photo.setCellValueFactory(new PropertyValueFactory<>("check_photo"));
		title.setCellValueFactory(new PropertyValueFactory<>("check_title"));
		date.setCellValueFactory(new PropertyValueFactory<>("check_date"));
		check.setCellValueFactory(new PropertyValueFactory<>("check_approve"));
		
		List<CheckVO> checkList = null;
		try {
			checkList = checkS.selectAll();
		} catch (RemoteException e2) {
			e2.printStackTrace();
		}
		
		if (LoginSession.memSession.getMem_id().equals("root")) {
			check.setCellFactory(new Callback<TableColumn<CheckVO, Integer>, TableCell<CheckVO, Integer>>() {
				@Override
				public TableCell<CheckVO, Integer> call(TableColumn<CheckVO, Integer> param) {
					TableCell<CheckVO, Integer> cell = new TableCell<CheckVO, Integer>() {

						public void updateItem(Integer item, boolean empty) {
							if (item != null) {
								
								Button btn = new Button("승인");
								
								if (item == 1) {
									btn.setDisable(true);
								} else {
									btn.setDisable(false);
								}
								btn.setOnAction(e -> {
									if (item == 0) {
										CheckVO cv = new CheckVO();
										cv.setCheck_approve(1);
										cv.setCheck_no(currentPageData.get(getIndex()).getCheck_no());
										try {
											checkS.update(cv);
										} catch (RemoteException e1) {
											e1.printStackTrace();
										}
										btn.setDisable(true);
										MethodUtil.alertShow("알림", "승인완료", "승인이 완료되었습니다.");
									}
								});
								setGraphic(btn);
							}
						}
					};
					return cell;
				}
			});
		}
		
		else {
			check.setCellFactory(new Callback<TableColumn<CheckVO, Integer>, TableCell<CheckVO, Integer>>() {
				@Override
				public TableCell<CheckVO, Integer> call(TableColumn<CheckVO, Integer> param) {
					TableCell<CheckVO, Integer> cell = new TableCell<CheckVO, Integer>() {

						public void updateItem(Integer item, boolean empty) {
							if (item != null) {
								
								Label label = new Label("승인 대기 중");
								if (item == 1) {
									label = new Label("승인 완료");
								} else {
									label.setDisable(false);
								}
								
								label.setFont(new Font(20));
								label.setAlignment(Pos.CENTER);
								label.setPadding(new Insets(5));
								setGraphic(label);
							}
						}
					};
					return cell;
				}
			});
		}
		
		photo.setCellFactory(new Callback<TableColumn<CheckVO, String>, TableCell<CheckVO, String>>() {
			@Override
			public TableCell<CheckVO, String> call(TableColumn<CheckVO, String> param) {
				TableCell<CheckVO, String> cell = new TableCell<CheckVO, String>() {

					public void updateItem(String item, boolean empty) {
						if (item != null) {
							ImageView imageview = null;
							try {
								imageview = new ImageView(
										new Image(new BufferedInputStream(new FileInputStream("img/" + item))));
								imageview.setFitWidth(70);
								imageview.setFitHeight(70);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							setGraphic(imageview);
						}
					}
				};
				return cell;
			}
		});

		List<ChallApplyVO> list = null;
		List<ChallengeVO> list2 = null;
		
		try {
			if (LoginSession.memSession.getMem_id().equals("root")) {
				list = challApply.selectAll();
			} else {
				list = challApply.selectId(LoginSession.memSession.getMem_id());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (list.isEmpty()) {
			return;
		}
		try {
			list2 = chall.selectAll();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		List<String> list3 = new ArrayList<String>();
		for(ChallengeVO cv :list2) {
			for(ChallApplyVO cav : list) {
				if(cv.getChall_no() == cav.getChall_no()) {
					list3.add(cav.getChall_no() + " : " + cv.getChall_name());
					break;
				}
			}
		}
			applyChallCombo.getItems().addAll(FXCollections.observableArrayList(list3));
	}

	@FXML
	public void backBtnClick(ActionEvent event) {
		BorderPane border = (BorderPane) ((Node) event.getSource()).getParent().getParent().getParent();
		
		border.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ChallengeMain.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		border.setCenter(parent);

	}
}
