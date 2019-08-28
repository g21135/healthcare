package kr.or.ddit.view.board.groupTalk;

import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.service.openTalk.IOpenTalkService;
import kr.or.ddit.service.talkAdmin.ITalkAdminService;
import kr.or.ddit.service.trainerTalk.ChatClient;
import kr.or.ddit.service.trainerTalk.ChatServer;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.OpenTalkVO;
import kr.or.ddit.vo.TalkAdminVO;
import kr.or.ddit.vo.User;

public class groupTalkMainController extends UnicastRemoteObject implements Initializable, ChatClient {
	@FXML
	JFXListView<OpenTalkVO> listView;

	// public groupTalkMainController(groupTalkController controller) throws
	// RemoteException, Exception {
	// this.controller = controller;
	//
	// }

	private Registry res;
	private ChatServer server;
	private IOpenTalkService openTalk;
	private ITalkAdminService talkAdmin;
	private IMemberService member;
	public static String groupName;
	public static int groupNum;

	public groupTalkController controller = null;
	public groupTalkMainController gtmc = null;

	Stage stage = null;

	public groupTalkMainController() throws RemoteException {
	}

	@Override
	public void printMessage(String msg) throws RemoteException {

	}

	@Override
	public void printGroupMessage(String msg) throws RemoteException {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.showMsg(msg); // 컨트롤러객체를 이용하여 대화창에 메시지 출력하기
			}
		});
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("groupTalk.fxml"));
			Parent parent = loader.load();
			controller = loader.getController();

			Scene scene = new Scene(parent);
			stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setOnCloseRequest(e -> {
				stage.close();
			});

			gtmc = this;
			Registry reg = LocateRegistry.getRegistry("localhost", 8888);
			server = (ChatServer) reg.lookup("server");
			openTalk = (IOpenTalkService) reg.lookup("openTalk");
			talkAdmin = (ITalkAdminService) reg.lookup("talkAdmin");
			member = (IMemberService) reg.lookup("member");

		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, List<Map<User, ChatClient>>> group = null;
		/*try {
			List<TalkAdminVO> list = talkAdmin.selectAll();
			group = server.getGroup();

			for (OpenTalkVO otv : openTalk.selectAll()) {
				List<Map<User, ChatClient>> groupList = new ArrayList<>();
				for (TalkAdminVO tav : list) {
					if (tav.getOpentalk_no() == otv.getOpentalk_no()) {
						Map<User, ChatClient> userInfo = new HashMap<>();
						MemberVO mv = member.selectId(tav.getMem_id());
						if (mv.getMem_id().equals(LoginSession.memSession.getMem_id())) {
							System.out.println(tav.getOpentalk_no() + "," + otv.getOpentalk_name() + "실행");
							userInfo.put(mv, gtmc);
							groupList.add(userInfo);
						}
					}
				}
				group.put(otv.getOpentalk_name(), groupList);
			}
			server.setGroup(group);

		} catch (RemoteException e3) {
			e3.printStackTrace();
		}*/

		try {
			listView.setItems(FXCollections.observableArrayList(openTalk.selectAll()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		listView.setCellFactory(new Callback<ListView<OpenTalkVO>, ListCell<OpenTalkVO>>() {
			@Override
			public ListCell<OpenTalkVO> call(ListView<OpenTalkVO> param) {
				return new ListCell<OpenTalkVO>() {
					@Override
					public void updateItem(OpenTalkVO item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null && !empty) {
							VBox vbox = new VBox();

							Label label = new Label(item.getOpentalk_name());
							label.setFont(new Font(30));
							label.setPadding(new Insets(10));

							Label label2 = new Label();

							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									int size = 0;
									try {
										for (TalkAdminVO name : talkAdmin.selectAll()) {
											if (item.getOpentalk_no() == name.getOpentalk_no()) {
												size++;
											}
										}
									} catch (RemoteException e) {
										e.printStackTrace();
									}
									label2.setText("현재 '" + String.valueOf(size) + "'명 참여 중");
								}
							});

							label2.setFont(new Font(20));
							vbox.getChildren().addAll(label, label2);

							vbox.setOnMouseClicked(new EventHandler<Event>() {
								@Override
								public void handle(Event event) {
									try {

										List<TalkAdminVO> list = new ArrayList<TalkAdminVO>();
										list = talkAdmin.selectId(LoginSession.memSession.getMem_id());
										boolean sw = false;

										for (TalkAdminVO tav : list) {
											if (tav.getOpentalk_no() == item.getOpentalk_no()) {
												groupName = item.getOpentalk_name();
												groupNum = item.getOpentalk_no();
												controller.textAreaClear();
												stage.showAndWait();
												return;
											}
										}
										ButtonType buttonType = MethodUtil.alertConfirmShow("알림", "그룹 참여",
												item.getOpentalk_name() + " 그룹에 참여 하시 겠습니까?");
										if (buttonType == buttonType.OK) {
											TalkAdminVO tav2 = null;
											java.util.Date utilDate = new java.util.Date();
											java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
											if (LoginSession.memSession != null
													&& LoginSession.trainerSession == null) {
//												server.joinGroup(LoginSession.memSession, item.getOpentalk_name(),gtmc);
												tav2 = new TalkAdminVO(0, LoginSession.memSession.getMem_id(),
														item.getOpentalk_no(), sqlDate);
											} else if (LoginSession.memSession != null
													&& LoginSession.trainerSession == null) {
//												server.joinGroup(LoginSession.trainerSession, item.getOpentalk_name(),gtmc);
												tav2 = new TalkAdminVO(0, LoginSession.trainerSession.getTrainer_id(),
														item.getOpentalk_no(), sqlDate);
											}

											MethodUtil.alertShow("알림", "참여 완료",
													item.getOpentalk_name() + "방에 참여하셨습니다.");
											talkAdmin.insert(tav2);
											groupNum = item.getOpentalk_no();
											groupName = item.getOpentalk_name();
											controller.textAreaClear();
											stage.showAndWait();
										}
									} catch (RemoteException e2) {
										e2.printStackTrace();
									}
								}
							});
							setGraphic(vbox);
						}
					}
				};
			}
		});
	}
}