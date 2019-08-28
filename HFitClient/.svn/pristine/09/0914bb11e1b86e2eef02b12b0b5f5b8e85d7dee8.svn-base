package kr.or.ddit.view.homeTraining;

import java.io.ByteArrayInputStream;
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
import com.jfoenix.controls.JFXListView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.homeSub.IHomeSubService;
import kr.or.ddit.service.homeTraining.IHomeTrainingService;
import kr.or.ddit.service.mediaUtil.IMdeiaUtil;
import kr.or.ddit.service.video.IVideoService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.view.homeTraining.player.SimpleMediaPlayer;
import kr.or.ddit.vo.HomeSubVO;
import kr.or.ddit.vo.HomeTrainingVO;
import kr.or.ddit.vo.VideoVO;

public class HomeTrainingMainController implements Initializable {

	@FXML
	JFXListView<HomeTrainingVO> listView;

	Registry reg;
	Registry reg2;
	IMdeiaUtil media;
	IHomeTrainingService home;
	IVideoService video;
	IHomeSubService sub;
	public static BorderPane mainView;

	@FXML
	AnchorPane homeTrainingView;

	public static int homeTrNum;

	@FXML
	JFXButton insert;

	@FXML
	JFXButton delete;

	@FXML
	JFXButton update;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		insert.setVisible(false);
		delete.setVisible(false);
		update.setVisible(false);

		if (LoginSession.memSession != null) {
			if (LoginSession.memSession.getMem_id().equals("root")) {
				insert.setVisible(true);
				delete.setVisible(true);
				update.setVisible(true);
			}
		}

		try {
			reg = LocateRegistry.getRegistry(8888);
			media = (IMdeiaUtil) reg.lookup("media");

			home = (IHomeTrainingService) reg.lookup("home");
			video = (IVideoService) reg.lookup("video");
			sub = (IHomeSubService) reg.lookup("sub");

		} catch (AccessException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
		try {
			List<HomeTrainingVO> list = home.selectAll();
			listView.setItems(FXCollections.observableArrayList(list));
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		listView.setCellFactory(new Callback<ListView<HomeTrainingVO>, ListCell<HomeTrainingVO>>() {
			@Override
			public ListCell<HomeTrainingVO> call(ListView<HomeTrainingVO> param) {
				return new ListCell<HomeTrainingVO>() {
					@Override
					public void updateItem(HomeTrainingVO item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null && !empty) {

							HBox container = new HBox();
							container.setAlignment(Pos.CENTER_LEFT);

							Label label = new Label(String.valueOf(item.getHome_no()));
							label.setFont(new Font(30));
							label.setPadding(new Insets(5));

							container.getChildren().add(label);
							try {
								byte[] byte2 = media.imgLoad(item.getHome_thumbnail()).getFileData();
								ImageView imgView = new ImageView(new Image(new ByteArrayInputStream(byte2)));
								imgView.setFitWidth(150);
								imgView.setFitHeight(100);

								container.getChildren().add(imgView);
							} catch (RemoteException e) {
								e.printStackTrace();
							}

							HBox hbox = new HBox();
							hbox.setPrefWidth(420);
							VBox vbox = new VBox();

							Label label1 = new Label(item.getHome_name());
							label1.setFont(new Font(30));
							label1.setPadding(new Insets(10));

							Label label2 = new Label(item.getHome_content());
							label2.setPadding(new Insets(10));
							label2.setFont(new Font(20));

							vbox.getChildren().addAll(label1, label2);

							JFXButton button = new JFXButton("구독");
							button.setStyle("-fx-background-color : pink");
							button.setAlignment(Pos.CENTER);
							button.setPrefSize(60, 50);
							hbox.getChildren().addAll(vbox, button);
							container.getChildren().add(hbox);

							List<HomeSubVO> suvList = null;
							try {
								suvList = sub.selectAll();
							} catch (RemoteException e5) {
								e5.printStackTrace();
							}

							if (LoginSession.memSession != null) {
								for (HomeSubVO hsv : suvList) {
									if (hsv.getMem_id().equals(LoginSession.memSession.getMem_id())) {
										if (hsv.getHome_no() == item.getHome_no()) {
											button.setText("구독중");
											button.setPrefWidth(70);
											button.setStyle("-fx-background-color : deeppink");
										}
									}
								}
							}

							button.setOnAction(e -> {
								List<HomeSubVO> list = null;
								try {
									list = sub.selectAll();
								} catch (RemoteException e2) {
									e2.printStackTrace();
								}
								
								for (HomeSubVO hsv : list) {
									if (hsv.getMem_id().equals(LoginSession.memSession.getMem_id())
											&& hsv.getHome_no() == item.getHome_no()) {
										ButtonType bt = MethodUtil.alertConfirmShow("알림", "구독취소", "정말로 구독을 취소하시 겠습니까?");
										if (bt == ButtonType.OK) {
											HomeSubVO hsv2 = new HomeSubVO();
											hsv2.setHome_no(item.getHome_no());
											hsv2.setMem_id(LoginSession.memSession.getMem_id());
											try {
												sub.delete(hsv);
											} catch (RemoteException e1) {
												e1.printStackTrace();
											}
											MethodUtil.alertShow("알림", "구독 취소!", "구독이 취소되었습니다");
											button.setText("구독");
											button.setPrefWidth(50);
											button.setStyle("-fx-background-color : pink");
										}
										return;
									}

								}

								ButtonType bt = MethodUtil.alertConfirmShow("알림", "구독하기", "정말로 구독하시 겠습니까?");
								if (bt == ButtonType.OK) {
									HomeSubVO hsv = new HomeSubVO();
									hsv.setHome_no(item.getHome_no());
									hsv.setMem_id(LoginSession.memSession.getMem_id());
									try {
										sub.insert(hsv);
									} catch (RemoteException e1) {
										e1.printStackTrace();
									}
									MethodUtil.alertShow("알림", "구독 완료!", "구독이 완료되었습니다\n내 구독 목록에서 확인 가능합니다.");
									button.setText("구독중");
									button.setPrefWidth(70);
									button.setStyle("-fx-background-color : deeppink");
								}
							});

							container.getChildren().add(button);

							container.setOnMouseClicked(e -> {
								BorderPane pane = new BorderPane();

								pane.setPrefWidth(USE_COMPUTED_SIZE);
								pane.setPrefHeight(USE_COMPUTED_SIZE);

								byte[] byte2 = null;
								try {
									byte2 = media.imgLoad(item.getHome_thumbnail()).getFileData();
								} catch (RemoteException e4) {
									e4.printStackTrace();
								}

								ImageView imgView = new ImageView(new Image(new ByteArrayInputStream(byte2)));
								imgView.setFitWidth(600);
								imgView.setFitHeight(400);
								pane.setCenter(imgView);
								pane.setAlignment(imgView, Pos.CENTER);

								JFXListView<VideoVO> videoList = new JFXListView<VideoVO>();
								List<VideoVO> list = null;
								videoList.setPrefWidth(600);
								videoList.setPrefHeight(400);

								try {
									list = video.selectAll(item.getHome_no());
								} catch (RemoteException e3) {
									e3.printStackTrace();
								}
								videoList.setItems(FXCollections.observableArrayList(list));

								videoList.getSelectionModel().selectedItemProperty()
										.addListener(new ChangeListener<VideoVO>() {
											@Override
											public void changed(ObservableValue<? extends VideoVO> observable,
													VideoVO oldValue, VideoVO newValue) {
												try {
													SimpleMediaPlayer.simpleMediaPlayer.getController().destroy();
												} catch (Exception e) {
												}
											}
										});
								videoList.setCellFactory(new Callback<ListView<VideoVO>, ListCell<VideoVO>>() {
									@Override
									public ListCell<VideoVO> call(ListView<VideoVO> param) {
										return new ListCell<VideoVO>() {
											@Override
											public void updateItem(VideoVO item, boolean empty) {
												super.updateItem(item, empty);
												if (item != null && !empty) {

													HBox container = new HBox();
													container.setAlignment(Pos.CENTER_LEFT);
													Label label = new Label(String.valueOf(item.getVideo_no()));
													label.setFont(new Font(20));
													label.setPadding(new Insets(10));
													container.getChildren().add(label);

													try {
														byte[] byte2 = media.imgLoad(item.getVideo_thumbnail())
																.getFileData();
														ImageView imgView = new ImageView(
																new Image(new ByteArrayInputStream(byte2)));
														imgView.setFitWidth(100);
														imgView.setFitHeight(50);
														container.getChildren().add(imgView);

													} catch (RemoteException e) {
														e.printStackTrace();
													}
													VBox vbox = new VBox();

													Label label1 = new Label(item.getVideo_name());
													label1.setFont(new Font(20));
													label1.setPadding(new Insets(10));
													container.getChildren().add(label1);
													Label label2 = new Label(item.getVideo_content());
													label2.setPadding(new Insets(10));
													label2.setFont(new Font(10));
													container.getChildren().add(label2);
													vbox.getChildren().addAll(label1, label2);
													container.getChildren().add(vbox);

													container.setOnMouseClicked(e -> {
														SimpleMediaPlayer player = SimpleMediaPlayer.newInstance(
																getClass().getResource(item.getVideo_url()).toString());

														pane.setCenter(player);
														pane.setAlignment(player, Pos.CENTER);
													});
													setGraphic(container);
												}
											}
										};
									}
								});
								pane.setRight(videoList);
								Stage stage = new Stage();
								stage.setScene(new Scene(pane, 1100, 400));
								stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

									@Override
									public void handle(WindowEvent event) {
										if (SimpleMediaPlayer.simpleMediaPlayer != null) {
											try {
												SimpleMediaPlayer.simpleMediaPlayer.getController().destroy();
											} catch (Exception e) {
											}
										} else {
											stage.close();
										}
									}
								});
								stage.show();

							});
							setGraphic(container);
						}
					}
				};
			}
		});
	}

	@FXML
	public void insertBtnClick() {

	}

	@FXML
	public void deleteBtnClick() {

	}

	@FXML
	public void updateBtnClick() {

	}

	public FXMLLoader changeScene(String fxmlURL) {
		mainView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainView.setCenter(parent);
		return loader;
	}

}
