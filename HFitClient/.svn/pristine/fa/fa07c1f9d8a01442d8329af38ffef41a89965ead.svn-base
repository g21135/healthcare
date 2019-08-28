package kr.or.ddit.view.board.groupTalk;

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

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.openTalk.IOpenTalkService;
import kr.or.ddit.service.talkAdmin.ITalkAdminService;
import kr.or.ddit.service.trainerTalk.ChatServer;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.OpenTalkVO;
import kr.or.ddit.vo.TalkAdminVO;

public class groupTalkController implements Initializable {

	public groupTalkController getGroupTalkController() {
		return this;

	}

	@FXML
	TextArea textArea;
	@FXML
	ListView<String> memberArea;
	@FXML
	Label title;
	@FXML
	TextField textInput;
	@FXML
	JFXButton submit;
	@FXML
	JFXButton exit;

	private Registry res;
	private ChatServer server;
	private IOpenTalkService openTalk;
	private ITalkAdminService talkAdmin;
	private List<OpenTalkVO> otv;

	public void initialize(URL location, ResourceBundle resources) {
		try {
			Registry reg = LocateRegistry.getRegistry("localhost", 8888);
			server = (ChatServer) reg.lookup("server");
			openTalk = (IOpenTalkService) reg.lookup("openTalk");
			talkAdmin = (ITalkAdminService) reg.lookup("talkAdmin");
			List<OpenTalkVO> otv = openTalk.selectAll();
			textArea.clear();
			
		} catch (AccessException e3) {
			e3.printStackTrace();
		} catch (RemoteException e3) {
			e3.printStackTrace();
		} catch (NotBoundException e3) {
			e3.printStackTrace();
		}
		title.setText(groupTalkMainController.groupName);
		
		textInput.setOnKeyPressed(e2 -> {
			if (e2.getCode().equals(KeyCode.ENTER)) {
				send();
			}
		});

		submit.setOnAction(e1 -> {
			send();
		});

		exit.setOnAction(e -> {
			ButtonType buttonType = MethodUtil.alertConfirmShow("알림", "그룹 나가기", "정말로 그룹에서 나가시겠습니까?");
			if (buttonType == ButtonType.OK) {
				/*try {
					server.groupExit(groupTalkMainController.groupName, LoginSession.memSession);
				} catch (RemoteException e2) {
					e2.printStackTrace();
				}*/
				TalkAdminVO tav = new TalkAdminVO(0, LoginSession.memSession.getMem_id(),
						groupTalkMainController.groupNum, null);

				try {
					talkAdmin.delete(tav);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			Stage stage2 = (Stage) ((Node) e.getSource()).getScene().getWindow();
			stage2.close();
		});

		List<TalkAdminVO> list = new ArrayList<TalkAdminVO>();
		List<String> nameList = new ArrayList<String>();
		List<OpenTalkVO> list2 = null;

		try {
			list = talkAdmin.selectId(LoginSession.memSession.getMem_id());
			list2 = openTalk.selectAll();
		} catch (RemoteException e3) {
			e3.printStackTrace();
		}

		for (OpenTalkVO otv : list2) {
			if (otv.getOpentalk_name().equals(groupTalkMainController.groupName)) {
				for (TalkAdminVO tav : list) {
					if (tav.getOpentalk_no() == otv.getOpentalk_no()) {
						nameList.add(tav.getMem_id());
					}
				}
			}
		}
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				memberArea.setItems(FXCollections.observableArrayList(nameList));
			}
		});
	}

	public void send() {
		String message = textInput.getText();
		try {
			if (LoginSession.memSession != null)
				server.GroupSay(groupTalkMainController.groupName,
						" [" + LoginSession.memSession.getMem_name() + " 회원]\n > " + message);
			else if (LoginSession.trainerSession != null) {
				server.GroupSay(groupTalkMainController.groupName,
						" [" + LoginSession.trainerSession.getTrainer_name() + " 트레이너]\n > " + message);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		textInput.clear();
		textInput.isFocused();
	}

	public void showMsg(String msg) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				if (LoginSession.memSession != null) {
					textArea.appendText(msg + "\n");
				} else if (LoginSession.trainerSession != null) {
					textArea.appendText("\t\t" + msg + "\n");
				}
			}
		});
	}

	public void textAreaClear() {
		textArea.clear();
	}
}
