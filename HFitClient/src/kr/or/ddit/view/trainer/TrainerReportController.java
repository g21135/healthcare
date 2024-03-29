package kr.or.ddit.view.trainer;

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

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import kr.or.ddit.LoginSession.IClientList;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.clientMain.ClientMainController;
import kr.or.ddit.service.match.IMatchService;
import kr.or.ddit.service.membership.IMemberShipService;
import kr.or.ddit.service.trainer.ITrainerService;
import kr.or.ddit.vo.CartVO;
import kr.or.ddit.vo.MatchVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MembershipVO;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class TrainerReportController implements Initializable {

	@FXML
	TableColumn<MatchVO, Integer> Mem_no;
	@FXML
	TableColumn<MatchVO, String> Mem_stay;
	@FXML
	TableColumn<MatchVO, String> Mem_id;
	@FXML
	TableColumn<MatchVO, String> Mem_Date;
	@FXML
	TableColumn<MatchVO, String> Trainer_chat;
	@FXML
	TableColumn<MatchVO, String> Mem_name;
	@FXML
	TableColumn<MatchVO, String> Trainer_sczuler;
	@FXML
	Pagination trainer_page;

	private Registry reg;
	private IMatchService match;

	// 페이징
	private int from, to, itemsForPage;
	ObservableList<MatchVO> allBoxData, currentPageData;
	MatchVO mc = new MatchVO();
	List<MatchVO> list;
	@FXML
	TableView<MatchVO> match_tableview;
	@FXML
	AnchorPane MainView;
	@FXML
	JFXButton reflash;

	IClientList icl;

	public static String mem_id; 
	public void pageset() {

		try {
			list = match.matchTrainer(mc);
			System.out.println(list.size());

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		allBoxData.setAll(list);

		itemsForPage = 10;
		System.out.println(allBoxData.size());

		int totItemCnt = allBoxData.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		trainer_page.setPageCount(totPageCnt);

		trainer_page.setPageFactory(new Callback<Integer, Node>() {

			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				match_tableview.setItems(getTableViewData(from, to));
				return match_tableview;
			}
		});
	}

	private ObservableList<MatchVO> getTableViewData(int from, int to) {
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
			match = (IMatchService) reg.lookup("match");
			icl = (IClientList) reg.lookup("host");

			System.out.println("rmI 성공");
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();

		}

		reflash(null);

		match_tableview.setItems(allBoxData);

	}

	@FXML
	public void reflash(ActionEvent event) {
		String loginid = LoginSession.trainerSession.getTrainer_id();

		mc.setTrainer_id(loginid);
		allBoxData = FXCollections.observableArrayList();
		Mem_no.setCellValueFactory(new PropertyValueFactory<>("membership_no"));
		Mem_no.setStyle("-fx-alignment:center;");
		Mem_stay.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		Mem_stay.setStyle("-fx-alignment:center;");
		Mem_id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		Mem_id.setStyle("-fx-alignment:center;");
		Mem_Date.setCellValueFactory(new PropertyValueFactory<>("membership_startdate"));
		Mem_Date.setStyle("-fx-alignment:center;");
		Mem_name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		Mem_name.setStyle("-fx-alignment:center;");
		Trainer_chat.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		Trainer_chat.setStyle("-fx-alignment:center;");
		Trainer_sczuler.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		Trainer_sczuler.setStyle("-fx-alignment:center;");

		pageset();

		Mem_stay.setCellFactory(new Callback<TableColumn<MatchVO, String>, TableCell<MatchVO, String>>() {

			@Override
			public TableCell<MatchVO, String> call(TableColumn<MatchVO, String> param) {
				TableCell<MatchVO, String> cell = new TableCell<MatchVO, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						if (item != null) {

							ImageView img = null;
							// host.getMemsession //검사 후 일치하는 아이디 있으면

							List<MemberVO> list = null;
							try {
								list = (List<MemberVO>) icl.getMemberSession();
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}

							try {
								img = new ImageView(
										new Image(new BufferedInputStream(new FileInputStream("img/main/gray.jpg"))));
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}

							for (MemberVO mv : list) {
								if (item.equals(mv.getMem_id()))
									try {
										img = new ImageView(new Image(
												new BufferedInputStream(new FileInputStream("img/main/green.jpg"))));
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
							}

							img.setFitWidth(20);
							img.setFitHeight(20);

							setGraphic(img);
						}
					}
				};
				return cell;
			}
		});

		Mem_id.setCellFactory(new Callback<TableColumn<MatchVO, String>, TableCell<MatchVO, String>>() {

			@Override
			public TableCell<MatchVO, String> call(TableColumn<MatchVO, String> param) {
				TableCell<MatchVO, String> cell = new TableCell<MatchVO, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						if (item != null) {
							Label label = new Label(item);
							
							setGraphic(label);
						}
					}
				};
				return cell;
			}

		});

		Trainer_chat.setCellFactory(new Callback<TableColumn<MatchVO, String>, TableCell<MatchVO, String>>() {

			@Override
			public TableCell<MatchVO, String> call(TableColumn<MatchVO, String> param) {
				TableCell<MatchVO, String> cell = new TableCell<MatchVO, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						if (item != null) {

							ImageView img = null;

							try {
								img = new ImageView(new Image(
										new BufferedInputStream(new FileInputStream("img/main/oneandonecaht.jpg"))));
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							img.setFitWidth(20);
							img.setFitHeight(20);

							JFXButton btn = new JFXButton();
							btn.setGraphic(img);

							btn.setOnAction(e -> {
								try {
									FXMLLoader loader = new FXMLLoader(
											(getClass().getResource("../myReport/trainerTalk/trainerTalkMain.fxml")));
									Parent parent = loader.load();
									Stage stage = new Stage(StageStyle.UTILITY);
									stage.initModality(Modality.APPLICATION_MODAL);
									stage.setTitle("트레이너 채팅");
									Scene scene = new Scene(parent);
									stage.setScene(scene);
									stage.showAndWait();

								} catch (IOException e1) {
									e1.printStackTrace();
								}

							});

							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		});

		Trainer_sczuler.setCellFactory(new Callback<TableColumn<MatchVO, String>, TableCell<MatchVO, String>>() {

			@Override
			public TableCell<MatchVO, String> call(TableColumn<MatchVO, String> param) {
				TableCell<MatchVO, String> cell = new TableCell<MatchVO, String>() {
					@Override
					public void updateItem(String item, boolean empty) {
						if (item != null) {

							ImageView img = null;

							try {
								img = new ImageView(new Image(
										new BufferedInputStream(new FileInputStream("img/main/sczuler.jpg"))));
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
							img.setFitWidth(20);
							img.setFitHeight(20);

							JFXButton btn = new JFXButton();
							btn.setGraphic(img);

							btn.setOnAction(e -> {
								
								
								try {
									
									String name = allBoxData.get(getIndex()).getMem_name();
									
									mem_id = name;
									FXMLLoader loader = new FXMLLoader(
											(getClass().getResource("../myReport/diary/DiaryMain.fxml")));
									Parent parent = loader.load();
									Stage stage = new Stage(StageStyle.UTILITY);
									stage.initModality(Modality.APPLICATION_MODAL);
									stage.setTitle("스케줄러");
									Scene scene = new Scene(parent);
									stage.setScene(scene);
									stage.showAndWait();

								} catch (IOException e1) {
									e1.printStackTrace();
								}

							});

							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		});
		match_tableview.refresh();
	}

	
}
