package kr.or.ddit.clientMain;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kr.or.ddit.service.post.IPostService;
import kr.or.ddit.vo.PostVO;

public class homeController implements Initializable{
	
	@FXML TableView noticeTable;
	@FXML TableColumn date;
	@FXML TableColumn title;
	private ObservableList<PostVO> data;
	IPostService post;
	SimpleDateFormat smf = new SimpleDateFormat("YYYY년 MM월 dd일");

	Registry reg;

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
		
		try {
			data = FXCollections.observableArrayList(post.allPostList(1));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tableSet(data);
	}
	
	public void tableSet(ObservableList<PostVO> data) {
		date.setCellValueFactory(new PropertyValueFactory<>("post_date"));
		date.setStyle("-fx-alignment:center;");
		title.setCellValueFactory(new PropertyValueFactory<>("post_title"));
		title.setStyle("-fx-alignment:center;");
		
		//날짜 형식 변경------------------------------------------------------------
				date.setCellFactory(new Callback<TableColumn<PostVO, Date>, TableCell<PostVO, Date>>() {
					@Override
					public TableCell<PostVO, Date> call(TableColumn<PostVO, Date> param) {
						TableCell<PostVO, Date> cell = new TableCell<PostVO, Date>() {
							@Override
							protected void updateItem(Date item, boolean empty) {
								if (item != null) {
									Label label = new Label(smf.format(item));	
									setGraphic(label);
								}
							}
						};
						return cell;
					}
				});
				//--------------------------------------------------------------------------
				
				
		noticeTable.setItems(data);
			
	}

}
