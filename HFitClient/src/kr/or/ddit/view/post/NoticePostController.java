package kr.or.ddit.view.post;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.post.IPostService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.view.board.FreeMainController;
import kr.or.ddit.view.board.NoticeMainController;
import kr.or.ddit.view.login.loginMainController;
import kr.or.ddit.vo.PostVO;

public class NoticePostController implements Initializable{

	@FXML BorderPane noticePostView;
	@FXML JFXTextField title;
	@FXML JFXTextField name;
	@FXML JFXTextField date;
	@FXML JFXTextArea txtContent;
	@FXML JFXButton update;
	@FXML JFXButton delete;
	@FXML JFXButton ok;
	public static BorderPane mainView;
	
	SimpleDateFormat smf = new SimpleDateFormat("YYYY년 MM월 dd일");
	
	Parent parent;
	IPostService post;
	Registry reg;
	int cnt;
	
	public FXMLLoader changeScene(String fxmlURL) {
		noticePostView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		noticePostView.setCenter(parent);
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
		
		/*Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(date);*/
		
		try {
			ObservableList<PostVO> data = FXCollections.observableArrayList(post.allPostList(1));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int a = NoticeMainController.postNum;
		
		PostVO pv = new PostVO();
		pv.setBoard_no(1);
		pv.setPost_no(a);
		
		try {
			pv = post.selectPostNum(pv);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		title.setText(pv.getPost_title());
		name.setText(pv.getMem_id());
		txtContent.setText(pv.getPost_content());
		date.setText(smf.format(pv.getPost_date()));
		
		
		title.setEditable(false);
		txtContent.setEditable(false);
		name.setEditable(false);
		date.setEditable(false);
		
		ok.setDisable(true);
	
	
	}
	
	@FXML public void listBtnClick(ActionEvent event) {
//		((BorderPane)noticePostView.getParent()).setTop(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../board/NoticeMain.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainView.setCenter(parent);
	}
	@FXML public void updateBtnClick() {
		if(LoginSession.memSession == null) {
			MethodUtil.alertShow("INFORMATION", "실패!", "로그인정보가 없습니다.");
		}
		
		update.setDisable(true);
		delete.setDisable(true);
		ok.setDisable(false);
		
		title.setEditable(true);
		txtContent.setEditable(true);
	
	}
	@FXML public void deleteBtnClick() {
//		pv.setBoard_no(2);
//		pv.setPost_no(FreeMainController.postNum);
		
		if(LoginSession.memSession == null) {
			MethodUtil.alertShow("INFORMATION", "실패!", "로그인정보가 없습니다.");
		}

		if(LoginSession.memSession.getMem_id().equals(name.getText())) {
			try {
				cnt = post.deletePost(NoticeMainController.postNum);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MethodUtil.alertShow("INFORMATION", "성공!", "게시글이 성공적으로 삭제되었습니다!");
		}else {
			MethodUtil.alertShow("INFORMATION", "실패!", "다시 한번 더 클릭해주세요.");
		}
		
		
		/*if(cnt > 0) {
			MethodUtil.alertShow("INFORMATION", "성공!", "게시글이 성공적으로 삭제되었습니다!");
			*/
			((BorderPane)noticePostView.getParent()).setTop(null);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../board/NoticeMain.fxml"));
			Parent parent = null;
			try {
				parent = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			((BorderPane)noticePostView.getParent()).setCenter(parent);
			
				
	/*	}else {
			MethodUtil.alertShow("INFORMATION", "실패!", "다시 한번 더 클릭해주세요.");
			return;
		}*/
		
//		ButtonType buttontype = MethodUtil.alertConfirmShow("INFORMATION", "안내", "정말 삭제하시겠습니까?");
//		if(buttontype == ButtonType.OK) {

//		}
		
	}
	
	@FXML public void okBtnClick() {
	
		/*txtContent.setEditable(false);
		
		PostVO pv = new PostVO();
		
		pv.setBoard_no(2);
		pv.setPost_no(FreeMainController.postNum);
		pv.setMem_id(LoginSession.memSession.getMem_id());
		
		if(title.getText()!=null ) {
			pv.setPost_title(title.getText());
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
		
		
		cnt = post.updatePost(pv); 
		
		if(cnt > 0) {
			MethodUtil.alertShow("INFORMATION", "성공!", "게시글이 성공적으로 등록되었습니다!");
			
			((BorderPane)noticePostView.getParent()).setTop(null);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../board/NoticeMain.fxml"));
			Parent parent = null;
			try {
				parent = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			((BorderPane)noticePostView.getParent()).setCenter(parent);
			
				
		}else {
			MethodUtil.alertShow("INFORMATION", "실패!", "다시 한번 더 클릭해주세요.");
			return;
		}
		*/
		
	}

}












