package kr.or.ddit.view.board;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.post.IPostService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.view.myReport.diary.AlertShow;
import kr.or.ddit.vo.PostVO;


public class QnAPostWriteController implements Initializable{
	
	
	Parent parent;
	IPostService post;
	Registry reg;
	@FXML BorderPane postWriteView;
	@FXML JFXTextField txtTitle;
	@FXML JFXTextArea txtContent;
	int cnt = 0; 
	public static int boardNum;
	public static BorderPane MainView;

	public FXMLLoader changeScene(String fxmlURL) {
		MainView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainView.setCenter(parent);
		return loader;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			post = (IPostService) reg.lookup("post");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 등록버튼 클릭시 쓴 글이 등록되어진다.
	 * @param event
	 */
	@FXML public void okBtnClick(ActionEvent event) {
		PostVO pv = new PostVO();
		//pv.setPost_no(pv.getPost_no());
		pv.setBoard_no(boardNum);
		pv.setMem_id(LoginSession.memSession.getMem_id());
		//pv.setPost_title(txtTitle.getText());
		//pv.setPost_content(txtContent.getText());
		//pv.setPost_date(pv.getPost_date());
		pv.setPost_photo(null);
		 
		
		if(txtTitle.getText()!=null ) {
			pv.setPost_title(txtTitle.getText());
		}else {
			System.out.println("제목 삽입 오류");
			 MethodUtil.alertShow("INFORMATION", "등록실패", "빈 항목이 존재합니다.");
			return;
		}
		
		if(txtContent.getText()!=null) {
			pv.setPost_content(txtContent.getText());
		}else {
			System.out.println("내용 삽입 오류");
			MethodUtil.alertShow("INFORMATION", "등록실패", "빈 항목이 존재합니다.");
			return;
		}
		
		
		try {
			cnt = post.insertPost(pv);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //회원정보 등록 확인
		
		if(cnt > 0) {
			MethodUtil.alertShow("INFORMATION", "성공!", "게시글이 성공적으로 등록되었습니다!");
			((BorderPane)postWriteView.getParent()).setTop(null);
			if(boardNum == 2) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("FreeMain.fxml"));
				Parent parent = null;
				try {
					parent = loader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				((BorderPane)postWriteView.getParent()).setCenter(parent);
			}else if(boardNum == 3) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ReviewMain.fxml"));
				Parent parent = null;
				try {
					parent = loader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				((BorderPane)postWriteView.getParent()).setCenter(parent);
			}else if(boardNum == 4) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("QnAMain.fxml"));
				Parent parent = null;
				try {
					parent = loader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
				((BorderPane)postWriteView.getParent()).setCenter(parent);
			}
			
			
		}else {
			MethodUtil.alertShow("INFORMATION", "실패!", "다시 한번 더 클릭해주세요.");
			return;
		}
		
	
	}

	/**
	 * 취소 버튼 클릭시 원래 화면으로 돌아간다.
	 */
	@FXML public void cancelBtnClick() {
//		changeScene("FreeMain.fxml");
		
//		((BorderPane)postWriteView.getParent()).setTop(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("QnAMain.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommunityMainController.MainView.setCenter(parent);
		
	}
	
}
