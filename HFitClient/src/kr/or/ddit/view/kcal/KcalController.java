package kr.or.ddit.view.kcal;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.kcal.IKcalService;
import kr.or.ddit.vo.KcalVO;

public class KcalController implements Initializable {
	@FXML
	private Pane mainPane;
	@FXML
	private BorderPane InsertMainPane;
	@FXML
	private Button foodButton;
	@FXML
	private Button helsButton;
	@FXML
	private Text setName;
	@FXML
	private TableView<KcalVO> selList; // 테이블 뷰에 KcalVO(상태)를 넣을 거야. 이름은 selList.
	@FXML
	private TableColumn<KcalVO, String> kcal_name; // <상태, 속성>
	@FXML
	private TableColumn<KcalVO, String> kcal_calory;
	// 추수삭

	@FXML
	private JFXButton insert; // 추가
	@FXML
	private Button update; // 수정
	@FXML
	private TextField exName;
	@FXML
	private TextField exKcal;
	@FXML
	private TextField chName;
	@FXML
	private TextField chKcal;

	@FXML
	private Button delete; // 삭제

	// 검색
	@FXML
	private JFXButton SearchButton;
	@FXML
	private TextField ContentText;
	@FXML
	private ComboBox<String> FoodOrHels;

	private Registry Reg; // 레지를 사용해야-
	private IKcalService KcalService;
	public static BorderPane mainView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Reg = LocateRegistry.getRegistry(8888); // -서버에 연결할 수 있다.
			KcalService = (IKcalService) Reg.lookup("kcal");

			if (LoginSession.memSession != null) {
				if(LoginSession.memSession.getMem_id().equals("root")) {
					insert.setVisible(true);
					delete.setVisible(true);
					update.setVisible(true);
				}else {
					insert.setVisible(false);
					delete.setVisible(false);
					update.setVisible(false);
				}
			}
			
			if(LoginSession.memSession == null) {
				insert.setVisible(false);
				delete.setVisible(false);
				update.setVisible(false);
			}

			try {
				// 칼로리_이름. 설정 셀 값 공장 새로운 속성 값 공장 <> (칼로리 이름 <- 셀 이름)
				kcal_name.setCellValueFactory(new PropertyValueFactory<>("kcal_name"));
				kcal_name.setStyle("-fx-alignment:center;");
				kcal_calory.setCellValueFactory(new PropertyValueFactory<>("kcal_calory"));
				kcal_calory.setStyle("-fx-alignment:center;");

				// 셀리스트에 아이템 설정.
				selList.setItems(FXCollections.observableArrayList(KcalService.getAllFoodKcalList()));
				setName.setText("칼로리 사전");
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		// ============================================================================================
		/*
		 * new EventHandler<ActionEvent>() 가 e -> 로 축약된다.
		 */
		foodButton.setOnAction(e -> {
			setName.setText("음식 칼로리");
			try {
				selList.setItems(FXCollections.observableArrayList(KcalService.getAllFoodKcalList()));
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		});

		helsButton.setOnAction(e -> {
			setName.setText("운동 칼로리");
			try {
				selList.setItems(FXCollections.observableArrayList(KcalService.getAllExerKcalList())); // getAllExerKcalList가
																										// 2이니 운동 리스트를
																										// 가져온다.
			} catch (RemoteException e2) {
				e2.printStackTrace();
			}
		});

		// 검색 기능 구현 중... getSearchExerKcal여기서 2를 선택함.
		// 운동 선택시 getSearchExerKcal로 넘어가야됨.
		// 콤보박스의 매뉴 넣기
		ObservableList<String> list = FXCollections.observableArrayList("음식", "운동");
		FoodOrHels.setItems(list); // 콤보박스에 세팅 완료.

		SearchButton.setOnAction(e -> { // 버튼 클릭
			setName.setText("검색 결과"); // test변화.(기능에 필요 없음)

			KcalVO kcalvo = new KcalVO();

			String ser = FoodOrHels.getValue(); // 위 콤보박스에 입력된 값 가져오기(음식, 운동)
			if (ser == null) {
				try {
					selList.setItems(FXCollections.observableArrayList(KcalService.getAllFoodKcalList()));
					setName.setText("칼로리 사전");
					// 창 띄워야 해!
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			} else if (ser.equals("운동")) {
				try {
					kcalvo.setKcal_name(ContentText.getText());
					selList.setItems(FXCollections.observableArrayList(KcalService.getSearchExerKcal(kcalvo)));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			} else if (ser.equals("음식")) {
				try {
					kcalvo.setKcal_name(ContentText.getText());
					selList.setItems(FXCollections.observableArrayList(KcalService.getSearchFoodKcal(kcalvo)));
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});

		if (selList.getSelectionModel().getSelectedItems().isEmpty()) {

			return;
		}

	}

	@FXML
	public void insertBtn(ActionEvent event) { // '추가' 버튼 클릭

		/*
		 * FXMLLoader loader = new
		 * FXMLLoader(getClass().getResource("InsertButton.fxml"));
		 * 
		 * Parent parent = null;
		 * try { 
		 * 	// System.out.println("넘겨보자"); parent =
		 * 	loader.load();
		 * 	// System.out.println("넘겨보자2");
		 * } catch (IOException e) {
		 * 	e.printStackTrace(); }
		 */

		changeScene("InsertButton.fxml");
		/*
		 * Pane rootPane = ((Pane) (setName.getScene().getRoot())); // 루트페인 = 패인 @FXML에
		 * 선언된 아무거나.찾는다Scene.찾는다Root rootPane.getChildren().clear();
		 * rootPane.getChildren().addAll(parent);
		 */
	}

	@FXML
	public void updateButtonClick(ActionEvent event) {

		if (selList.getSelectionModel().getSelectedItems().isEmpty()) {
			return;
		}

		FXMLLoader loader = new FXMLLoader(getClass().getResource("updateKcalButton.fxml"));

		Parent parent = null;
		try {
			parent = loader.load();
			/*
			 * TextField tf1 = (TextField) parent.lookup("#exName"); TextField tf2 =
			 * (TextField) parent.lookup("#exKcal"); TextField tf3 = (TextField)
			 * parent.lookup("#secretNum");
			 */

			// tf1.setText(selList.getSelectionModel().getSelectedItem().getKcal_name());
			// tf2.setText(selList.getSelectionModel().getSelectedItem().getKcal_calory() +
			// "");
			// tf3.setText(selList.getSelectionModel().getSelectedItem().getKcal_num() +
			// "");
			UpdateButtonController.name = selList.getSelectionModel().getSelectedItem().getKcal_name();
			// System.out.println(UpdateButtonController.name);
			UpdateButtonController.calory = selList.getSelectionModel().getSelectedItem().getKcal_calory();
			// System.out.println(selList.getSelectionModel().getSelectedItem().getKcal_num());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Pane rootPane = ((Pane) (setName.getScene().getRoot()));
		// rootPane.getChildren().clear();
		// rootPane.getChildren().addAll(parent);
		// mainView.setCenter(null);
		// mainView.setCenter(parent);
		changeScene("updateKcalButton.fxml");
	}

	@FXML
	public void deleteButtonClick(ActionEvent event) {
		// 클릭한 상태로 버튼 클릭 조건.
		if (selList.getSelectionModel().getSelectedItems().isEmpty()) {
			return;
		}
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);

		alertConfirm.setTitle("삭제 확인");
		alertConfirm.setContentText("정말 삭제하시겠습니까?");

		ButtonType confirmResult = alertConfirm.showAndWait().get();

		if (confirmResult == ButtonType.OK) {
			try {
				KcalService.deleteFoodKcal(selList.getSelectionModel().getSelectedItem().getKcal_num());
				selList.setItems(FXCollections.observableArrayList(KcalService.getAllFoodKcalList()));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			return;
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
