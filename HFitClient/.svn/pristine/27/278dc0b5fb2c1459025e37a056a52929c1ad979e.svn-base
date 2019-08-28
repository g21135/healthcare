package kr.or.ddit.view.myReport.mySub;

import java.io.ByteArrayInputStream;
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
import com.jfoenix.controls.JFXListView;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
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

public class MySubController implements Initializable {

	@FXML
	JFXListView<HomeTrainingVO> listView;
	@FXML
	JFXButton myReportBtn;
	@FXML
	StackPane stackPane;
	Registry reg;
	Registry reg2;
	IMdeiaUtil media;
	IHomeTrainingService home;
	IVideoService video;
	IHomeSubService sub;

	public static int homeTrNum;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
		listSet();

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

							for (HomeSubVO hsv : suvList) {
								if (hsv.getMem_id().equals(LoginSession.memSession.getMem_id())) {
									if (hsv.getHome_no() == item.getHome_no()) {
										button.setText("구독중");
										button.setPrefWidth(70);
										button.setStyle("-fx-background-color : deeppink");
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

											listSet();
										}
										return;
									}

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
														SimpleMediaPlayer player = SimpleMediaPlayer
																.newInstance(
																		getClass()
																				.getResource("../../homeTraining/"
																						+ item.getVideo_url())
																				.toString());
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

	public void listSet() {
		try {
			List<HomeTrainingVO> list = new ArrayList<>();
			List<HomeTrainingVO> htvList = home.selectAll();

			List<HomeSubVO> hsvList = sub.selectAll();
			for (HomeTrainingVO htv : htvList) {
				for (HomeSubVO hsv : hsvList) {
					if (hsv.getMem_id().equals(LoginSession.memSession.getMem_id())) {

						if (hsv.getHome_no() == htv.getHome_no()) {
							list.add(htv);
						}
					}
				}
			}
			listView.setItems(FXCollections.observableArrayList(list));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void myReportBtnClick(ActionEvent event) {
		try {
			ObservableList<Node> list = FXCollections
					.observableArrayList(myReportBtn.getScene().getRoot().getChildrenUnmodifiable());

			stackPane.setTranslateX(0);

			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(stackPane.translateXProperty(), 350);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					((StackPane) list.get(1)).getChildren().remove(stackPane);
				}
			}, keyValue);
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
