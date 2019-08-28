package kr.or.ddit.view.board;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import kr.or.ddit.LoginSession.LoginSession;
import javafx.scene.image.ImageView;
import com.jfoenix.controls.JFXButton;

public class EventMainController implements Initializable{

	@FXML BorderPane eventMainView;
	@FXML HBox hbox;
	@FXML ImageView img2;
	@FXML ImageView img1;
	@FXML JFXButton insert;
	
	
	Parent parent;
	Registry reg;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			/*post = (IPostService) reg.lookup("post");*/

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		if(LoginSession.memSession.getMem_id().equals("root")) {
			insert.setVisible(false);
			
		}
		
		
	}

	@FXML public void insertBtnClick() {}
}
