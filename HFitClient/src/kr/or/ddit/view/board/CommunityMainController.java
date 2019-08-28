package kr.or.ddit.view.board;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import kr.or.ddit.view.board.challenge.ChallengeMainController;

public class CommunityMainController implements Initializable {
	@FXML
	JFXButton challenge;
	@FXML
	JFXButton qnaBoard;
	@FXML
	JFXButton freeBoard;
	@FXML
	JFXButton reviewBoard;
	@FXML
	JFXButton openTalk;
	@FXML
	BorderPane freeMainView;
	@FXML BorderPane CommunityMainView;
	public static BorderPane MainView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MainView = CommunityMainView;
		ChallengeMainController.communityView = CommunityMainView;
//		ReviewPostWriteController.
		changeScene("FreeMain.fxml");
	}

	@FXML
	public void openTalkBtnClick() {
		challenge.setStyle("");
		qnaBoard.setStyle("");
		reviewBoard.setStyle("");
		freeBoard.setStyle("");
		openTalk.setStyle("-fx-background-color: #FDF5E6;");
//		changeScene("groupTalk/groupTalkMain.fxml");
	}

	@FXML
	public void challengeBtnClick() {

		openTalk.setStyle("");
		qnaBoard.setStyle("");
		reviewBoard.setStyle("");
		freeBoard.setStyle("");
		challenge.setStyle("-fx-background-color: #FDF5E6;");
		changeScene("challenge/ChallengeMain.fxml");
	}

	@FXML
	public void qnaBtnClick(ActionEvent event) {
		challenge.setStyle("");
		reviewBoard.setStyle("");
		freeBoard.setStyle("");
		openTalk.setStyle("");
		qnaBoard.setStyle("-fx-background-color: #FDF5E6;");
		changeScene("QnAMain.fxml");
	}

	@FXML
	public void reviewBtnClick(ActionEvent event) {
		challenge.setStyle("");
		qnaBoard.setStyle("");
		freeBoard.setStyle("");
		openTalk.setStyle("");
		reviewBoard.setStyle("-fx-background-color: #FDF5E6;");
		changeScene("ReviewMain.fxml");
	}

	@FXML
	public void freeBtnClick(ActionEvent event) {
		challenge.setStyle("");
		qnaBoard.setStyle("");
		openTalk.setStyle("");
		reviewBoard.setStyle("");
		freeBoard.setStyle("-fx-background-color: #FDF5E6;");
//		freeMainView.setTop(null);
		changeScene("FreeMain.fxml");

	}

	public FXMLLoader changeScene(String fxmlURL) {
		CommunityMainView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommunityMainView.setCenter(parent);
		return loader;
	}

}
