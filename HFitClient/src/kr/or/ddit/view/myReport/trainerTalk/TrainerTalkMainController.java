package kr.or.ddit.view.myReport.trainerTalk;

import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.openTalk.IOpenTalkService;
import kr.or.ddit.service.trainerTalk.ChatClient;
import kr.or.ddit.service.trainerTalk.ChatServer;

public class TrainerTalkMainController extends UnicastRemoteObject implements ChatClient, Initializable {
	private ChatServer server = null;
	protected String name = null;
	
	@FXML
	TextField textInput;
	@FXML
	TextArea textArea;
	@FXML 
	JFXButton submit;
	
	public TrainerTalkMainController() throws RemoteException {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Registry reg = LocateRegistry.getRegistry("localhost", 8888);
			server = (ChatServer) reg.lookup("server");
			if(LoginSession.memSession == null && LoginSession.trainerSession != null) {
				server.addTrainerSession(LoginSession.trainerSession, this);
			}
			else {
				server.addMemberSession(LoginSession.memSession, this);
			}
			
		} catch (AccessException e2) {
			e2.printStackTrace();
		} catch (RemoteException e2) {
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			e2.printStackTrace();
		}
		
		textInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        if(event.getCode().equals(KeyCode.ENTER)) {
		        	send();
		        }
		    }
		});
		
		
	}

	@FXML
	public void submitBtnClick() {
		send();
	}

	@Override
	public void printMessage(String msg) throws RemoteException {
		if(LoginSession.memSession != null) {
			textArea.appendText(msg +"\n");
		}else if(LoginSession.trainerSession != null){
			textArea.appendText(msg + "\n");
		}
	}
	
	public void send() {
		String message = textInput.getText();
		try {
			if(LoginSession.memSession != null)
				server.say(" [" + LoginSession.memSession.getMem_name() + " 회원]\n > " + message);
			else if(LoginSession.trainerSession != null){
				server.say(" [" + LoginSession.trainerSession.getTrainer_name() + " 트레이너]\n > " + message);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		textInput.clear();
		textInput.isFocused();
	}

	@Override
	public void printGroupMessage(String msg) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
