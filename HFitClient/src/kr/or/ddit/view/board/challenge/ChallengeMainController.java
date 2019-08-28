package kr.or.ddit.view.board.challenge;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.challApply.IChallApplyService;
import kr.or.ddit.service.challenge.IChallengeService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.ChallApplyVO;
import kr.or.ddit.vo.ChallengeVO;
import com.jfoenix.controls.JFXButton;

public class ChallengeMainController implements Initializable {

	@FXML
	AnchorPane anchorPane;
	@FXML
	HBox hbox;

	ImageView imageView;
	Registry reg;
	IChallengeService chall;

	List<ChallengeVO> list;

	@FXML
	Pagination page;
	private int from, to, itemsForPage;
	ObservableList<ChallengeVO> allBoxData;
	ObservableList<VBox> currentPageData;
	IChallApplyService challApply;
	public static BorderPane mainView;
	public static BorderPane communityView;

	@FXML
	BorderPane challengeView;
	SimpleDateFormat smf = new SimpleDateFormat("YY년 MM월 dd일");

	@FXML
	JFXButton check;
	@FXML
	JFXButton insertChall;

	public ChallengeMainController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		insertChall.setVisible(false);
		check.setVisible(false);

		if (LoginSession.memSession == null) {
			MethodUtil.alertShow("로그인", "로그인", "로그인 하셔야합니다!!");
			changeScene("../../../clientMain/home.fxml");
		}
		else if (LoginSession.memSession != null) {
			if (LoginSession.memSession.getMem_id().equals("root")) {
				insertChall.setVisible(true);
			}
		}
		try {
			reg = LocateRegistry.getRegistry(8888);
			chall = (IChallengeService) reg.lookup("chall");
			challApply = (IChallApplyService) reg.lookup("challApply");
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		pageSet();
	}

	private void pageSet() {
		try {
			list = chall.selectAll();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		allBoxData = FXCollections.observableArrayList(list);
		itemsForPage = 3;

		int totItemCnt = allBoxData.size();
		int totPageCnt = totItemCnt % itemsForPage == 0 ? totItemCnt / itemsForPage : totItemCnt / itemsForPage + 1;
		page.setPageCount(totPageCnt);

		page.setPageFactory(new Callback<Integer, Node>() {
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;

				ObservableList<VBox> data = getBoxViewData(from, to);
				hbox.getChildren().clear();
				hbox.getChildren().addAll(data);
				return hbox;
			}
		});
	}

	private ObservableList<VBox> getBoxViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		int totSize = allBoxData.size();

		for (int i = from; i <= to && i < totSize; i++) {
			ChallengeVO cv = allBoxData.get(i);

			VBox vbox = new VBox();
			vbox.setAlignment(Pos.CENTER);
			HBox.setMargin(vbox, new Insets(20));

			try {
				imageView = new ImageView(
						new Image(new BufferedInputStream(new FileInputStream("img/" + cv.getChall_photo()))));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			imageView.setFitWidth(150);
			imageView.setFitHeight(150);

			vbox.setOnMouseClicked(e -> {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("ChallengeDetail.fxml"));

				Parent parent = null;
				try {
					parent = loader.load();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				Label title = (Label) parent.lookup("#title");
				ImageView imageView2 = (ImageView) parent.lookup("#imageView");
				Label content = (Label) parent.lookup("#content");
				Label date = (Label) parent.lookup("#date");
				Button applyBtn = (Button) parent.lookup("#applyBtn");
				Button listBtn = (Button) parent.lookup("#listBtn");
				AnchorPane detailView = (AnchorPane) parent.lookup("#detailView");

				title.setText(cv.getChall_name());
				content.setText(cv.getChall_content());
				try {
					imageView2.setImage(
							new Image(new BufferedInputStream(new FileInputStream("img/" + cv.getChall_photo()))));
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				}
				imageView2.setFitWidth(250);
				imageView2.setFitHeight(200);
				imageView2.setStyle("-fx-alignment : center");
				date.setText(smf.format(cv.getChall_start()) + " ~ " + smf.format(cv.getChall_end()));

				applyBtn.setOnAction(ev -> {
					List<ChallApplyVO> list = null;
					try {
						list = challApply.selectAll();
					} catch (RemoteException e2) {
						e2.printStackTrace();
					}
					for (ChallApplyVO cav : list) {
						if (cav.getChall_no() == cv.getChall_no()) {
							if (cav.getMem_id().equals(LoginSession.memSession.getMem_id())) {
								MethodUtil.alertShow("안내", "첼린지 중복 신청!", "해당 첼린지가 이미 신청되어있습니다.");
								return;
							}
						}
					}

					ButtonType confirmResult = MethodUtil.alertConfirmShow("안내", "첼린지 신청", "해당 첼린지를 신청하시겠습니까?");
					if (confirmResult == ButtonType.OK) {
						try {
							reg = LocateRegistry.getRegistry(8888);
							challApply = (IChallApplyService) reg.lookup("challApply");

						} catch (AccessException e1) {
							e1.printStackTrace();
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (NotBoundException e1) {
							e1.printStackTrace();
						}

						ChallApplyVO cav = new ChallApplyVO();
						cav.setChall_no(cv.getChall_no());
						cav.setMem_id(LoginSession.memSession.getMem_id());

						try {
							challApply.insert(cav);
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
						MethodUtil.alertShow("안내", "첼린지 신청 완료", "해당 첼린지가 신청되었습니다.");

					} else if (confirmResult == ButtonType.CANCEL) {
					}
				});

				listBtn.setOnAction(evt -> {
					FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ChallengeMain.fxml"));
					Parent parent2 = null;
					try {
						parent2 = loader2.load();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					((BorderPane) detailView.getParent()).setCenter(parent2);
				});

				((BorderPane) challengeView.getParent()).setCenter(parent);
			});
			Label name = new Label();
			name.setText(cv.getChall_no() + " : " + cv.getChall_name());
			name.setFont(new Font(20));

			Label content = new Label();
			content.setText(cv.getChall_name());
			content.setFont(new Font(20));

			Label date = new Label();

			date.setText(smf.format(cv.getChall_start()) + " ~ " + smf.format(cv.getChall_end()));

			vbox.getChildren().addAll(imageView, name, content, date);

			currentPageData.add(vbox);
		}
		return currentPageData;
	}

	@FXML
	public void challengeSearchBtnClick() {

	}

	@FXML
	public void challengeAdd(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddChallenge.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			list = chall.selectAll();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		pageSet();
	}

	@FXML
	public void checkBoardBtnClick() {
		challengeView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("checkBoard.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		check.setVisible(true);
		challengeView.setCenter(parent);

	}

	@FXML
	public void checkBtnClick() {
		if (LoginSession.memSession.getMem_id().equals("root")) {
			return;
		}

		if (CheckBoardController.applyChallComboNum == 0) {
			MethodUtil.alertShow("안내", "첼린지 미선택", "콤보박스에서 첼린지를 선택해 주세요");
			return;
		}
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("check.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public FXMLLoader changeScene(String fxmlURL) {
		mainView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainView.setCenter(parent);
		return loader;
	}
}
