package kr.or.ddit.view.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.mediaUtil.IMdeiaUtil;
import kr.or.ddit.service.prod.IProdService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.view.board.challenge.CheckBoardController;
import kr.or.ddit.view.cart.CardInController;
import kr.or.ddit.vo.ChallApplyVO;
import kr.or.ddit.vo.CheckVO;
import kr.or.ddit.vo.FileVO;
import kr.or.ddit.vo.ProdVO;

public class Prod_AddController implements Initializable {

	@FXML JFXComboBox<Integer> prod_div;
	@FXML JFXTextField prod_name;
	@FXML JFXTextField prod_price;
	@FXML JFXTextField prod_img;
	@FXML JFXTextArea prod_area;
	@FXML JFXButton prod_addbtn;
	@FXML JFXButton fileClick;
	@FXML ImageView fileimage;
	boolean check = false;
	IProdService prod;
	Registry reg;
	BufferedInputStream bis = null;
	BufferedOutputStream bos = null;
	IMdeiaUtil media = null;
			
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			reg = LocateRegistry.getRegistry(8888);
			prod = (IProdService) reg.lookup("prod");
			media = (IMdeiaUtil) reg.lookup("media");
			
			System.out.println("rmI 성공");
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		
		
		prod_div.getItems().addAll(1,2);
		prod_div.setValue(1);
		
		
		
		prod_addbtn.setOnAction(e->{
			int Select_div= prod_div.getSelectionModel().getSelectedItem();
			System.out.println(Select_div);
			if (!check) {
				MethodUtil.alertShow("WARNNING", "사진첨부 에러!", "사진을 첨부해주세요");
				return;
			}
			
			
			ProdVO pv = new ProdVO();
			pv.setDiv_code(Select_div);
			pv.setProd_name(prod_name.getText());
			pv.setProd_content(prod_area.getText());
			pv.setProd_price(Integer.parseInt(prod_price.getText()));
			pv.setProd_photo(pv.getProd_name());

			int c;
			try {
				bos = new BufferedOutputStream(new FileOutputStream("img/prod/" + pv.getProd_name() + ".jpg"));
				while ((c = bis.read()) != -1) {
					bos.write(c);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					bos.close();
					bis.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			try {
				//;
				int cnt = prod.InsertProd(pv);
				if(cnt == 0) {
					System.out.println("안되");
				}else {
					System.out.println("되");
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			Stage primaryStage = (Stage) prod_div.getScene().getWindow();
			primaryStage.close();

			
			
			
		});
		
	}
	@FXML
 	public void fileBtnClick(ActionEvent event) {
		// 파일 열기 창
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image files", "*.png", "*.jpg", "*.gif"));
		File selectFile = fileChooser.showOpenDialog(null);
		if (selectFile != null) {
			try {
				bis = new BufferedInputStream(new FileInputStream(selectFile));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		try {
			fileimage.setImage(new Image(new BufferedInputStream(new FileInputStream(selectFile))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		fileimage.setFitWidth(151);
		fileimage.setFitHeight(111);
		fileimage.setStyle("-fx-alignment : center");
		
		check = true;
	}

}
