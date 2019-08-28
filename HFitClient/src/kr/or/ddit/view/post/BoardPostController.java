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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.clientMain.ClientMainController;
import kr.or.ddit.service.post.IPostService;
import kr.or.ddit.service.reply.IReplyService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.view.board.CommunityMainController;
import kr.or.ddit.view.board.FreeMainController;
import kr.or.ddit.vo.PostVO;
import kr.or.ddit.vo.ReplyVO;

public class BoardPostController implements Initializable{

	@FXML BorderPane boardPostView;
	@FXML JFXTextField title;
	@FXML JFXTextField name;
	@FXML JFXTextField date;
	@FXML JFXTextArea txtContent;
	@FXML JFXButton update;
	@FXML JFXButton delete;
	@FXML JFXButton ok;
	@FXML JFXButton replyInsert;
	@FXML JFXButton list;
	@FXML TableView<ReplyVO> replyTable;
	@FXML TableColumn<ReplyVO, String> content;
	@FXML TableColumn<ReplyVO, String> id;
	@FXML TableColumn<ReplyVO, Date> replyDate;
	@FXML TextArea replyTxt;
	@FXML Label count;
	public static BorderPane mainView;
	
	SimpleDateFormat smf = new SimpleDateFormat("YYYY년 MM월 dd일");
	
	Parent parent;
	IPostService post;
	IReplyService reply;
	Registry reg;
	int cnt;
	private ObservableList<ReplyVO> data2;
	int a = FreeMainController.postNum;
	
	public FXMLLoader changeScene(String fxmlURL) {
		boardPostView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boardPostView.setCenter(parent);
		return loader;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			post = (IPostService) reg.lookup("post");
			reply = (IReplyService) reg.lookup("reply");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		/*Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(date);*/
		
		try {
			ObservableList<PostVO> data = FXCollections.observableArrayList(post.allPostList(2));
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//int a = FreeMainController.postNum;
		//FreeMainController.postNum = freeTable.getSelectionModel().getSelectedItem().getPost_no();
		
		
		PostVO pv = new PostVO();
		pv.setBoard_no(2);
		pv.setPost_no(a);
		
		try {
			pv = post.selectPostNum(pv);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		title.setText(pv.getPost_title());
		name.setText(pv.getMem_id());
		txtContent.setText(pv.getPost_content());
		date.setText(smf.format(pv.getPost_date()));
		
		//댓글-------------------------------------------------------------------
		
		try {
			data2 = FXCollections.observableArrayList(reply.allReplyList(a));
		} catch (RemoteException e) {
			System.out.println("리플에러네");
			e.printStackTrace();
		}
		tableSet(data2);
	
		//-----------------------------------------------------------------------
		
		title.setEditable(false);
		txtContent.setEditable(false);
		name.setEditable(false);
		date.setEditable(false);
		
		ok.setDisable(true);
			
	}
	
	private void tableSet(ObservableList<ReplyVO> data2) {
		id.setCellValueFactory(new PropertyValueFactory<>("reply_writer"));
		content.setCellValueFactory(new PropertyValueFactory<>("reply_content"));
		replyDate.setCellValueFactory(new PropertyValueFactory<>("reply_date"));
		replyTable.setItems(data2);
		
	}

	@FXML public void listBtnClick(ActionEvent event) {
		((BorderPane)boardPostView.getParent()).setTop(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../board/CommunityMain.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainView.setCenter(parent);
	}
	@FXML public void updateBtnClick() {
		System.out.println(name.getText());
		System.out.println("/////");
		System.out.println(LoginSession.memSession.getMem_id());
		if(LoginSession.memSession == null) {
			MethodUtil.alertShow("INFORMATION", "실패!", "로그인정보가 없습니다.");
		}else if(LoginSession.memSession.getMem_id().equals(name.getText())) {
			//로그인확인하고 로그인한 정보가 일치하면은 
			update.setDisable(true);
			delete.setDisable(true);
			ok.setDisable(false);
			
			title.setEditable(true);
			txtContent.setEditable(true);
		}else {
			MethodUtil.alertShow("INFORMATION", "수정 불가!", "다른 사람의 글을 수정 할 수 없습니다.");
			
		}
		
	}
	@FXML public void deleteBtnClick() {

		PostVO pv = new PostVO();
//		pv.setBoard_no(2);
//		pv.setPost_no(a);
////		pv.setBoard_no(2);
		pv.setPost_no(FreeMainController.postNum);
		
		if(LoginSession.memSession == null) {
			MethodUtil.alertShow("INFORMATION", "실패!", "로그인정보가 없습니다.");
			delete.setDisable(false);
		}else if(LoginSession.memSession.getMem_id().equals(name.getText())) {
			try {
				cnt = post.deletePost(FreeMainController.postNum);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(cnt);
			if(cnt > 0) {
				MethodUtil.alertShow("INFORMATION", "성공!", "게시글이 성공적으로 삭제되었습니다!");				
			}else {
				MethodUtil.alertShow("INFORMATION", "실패!", "다시 한번 더 클릭해주세요.");
			}
		}else {
			MethodUtil.alertShow("INFORMATION", "수정 불가!", "다른 사람의 글을 삭제할 수 없습니다.");
		}
			
		/*}else if(LoginSession.memSession.getMem_id() != name.getText()) {
			MethodUtil.alertShow("INFORMATION", "수정 불가!", "다른 사람의 글을 삭제할 수 없습니다.");
		}

		if(LoginSession.memSession.getMem_id().equals(name.getText())) {
			try {
				cnt = post.deletePost(FreeMainController.postNum);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MethodUtil.alertShow("INFORMATION", "성공!", "게시글이 성공적으로 삭제되었습니다!");
		}else {
			MethodUtil.alertShow("INFORMATION", "실패!", "다시 한번 더 클릭해주세요.");
		}*/
		
		
		/*if(cnt > 0) {
			MethodUtil.alertShow("INFORMATION", "성공!", "게시글이 성공적으로 삭제되었습니다!");
			*/
			((BorderPane)boardPostView.getParent()).setTop(null);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../board/FreeMain.fxml"));
			Parent parent = null;
			try {
				parent = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			((BorderPane)boardPostView.getParent()).setCenter(parent);
			
				
	/*	}else {
			MethodUtil.alertShow("INFORMATION", "실패!", "다시 한번 더 클릭해주세요.");
			return;
		}*/
		
//		ButtonType buttontype = MethodUtil.alertConfirmShow("INFORMATION", "안내", "정말 삭제하시겠습니까?");
//		if(buttontype == ButtonType.OK) {

//		}
		
	}
	
	@FXML public void okBtnClick() {
		title.setEditable(false);
		txtContent.setEditable(false);
		
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
		
		
		try {
			cnt = post.updatePost(pv);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		if(cnt > 0) {
			MethodUtil.alertShow("INFORMATION", "성공!", "게시글이 성공적으로 등록되었습니다!");
			
			((BorderPane)boardPostView.getParent()).setTop(null);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../board/FreeMain.fxml"));
			Parent parent = null;
			try {
				parent = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			((BorderPane)boardPostView.getParent()).setCenter(parent);
			
				
		}else {
			MethodUtil.alertShow("INFORMATION", "실패!", "다시 한번 더 클릭해주세요.");
			return;
		}
	}
	
	@FXML public void replyBtnClick(){
		
		ReplyVO rv = new ReplyVO();
		rv.setReply_writer(LoginSession.memSession.getMem_id());
		rv.setReply_content(replyTxt.getText());
		rv.setPost_no(a);
		
		try {
			reply.insertReply(rv);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		((BorderPane)boardPostView.getParent()).setTop(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BoardPost.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		((BorderPane)boardPostView.getParent()).setCenter(parent);
		


	}

}












