package kr.or.ddit.view.admin;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.service.match.IMatchService;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.service.membership.IMemberShipService;
import kr.or.ddit.view.hope.UserHopeMain;
import kr.or.ddit.vo.MatchVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MembershipVO;
import kr.or.ddit.vo.sampleVO;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;

public class memberListMainController implements Initializable {

	Registry reg;
	IMatchService matchService;
	IMemberShipService membershipService;
	IMemberService memberService;

	List<sampleVO> sampleList = new ArrayList<>();
	List<MemberVO> memList = new ArrayList<>();
	List<MembershipVO> membershipList = new ArrayList<>();
	List<MatchVO> matchList = new ArrayList<>();
	@FXML
	TableView<sampleVO> matchingTable;
	@FXML
	TableColumn<sampleVO, Integer> num;
	@FXML
	TableColumn<sampleVO, String> id;
	@FXML
	TableColumn<sampleVO, String> name;
	@FXML
	TableColumn<sampleVO, Date> startdate;
	@FXML
	TableColumn<sampleVO, String> coach;
	@FXML
	TableColumn<sampleVO, String> grade;
	@FXML
	TableColumn<sampleVO, Integer> check;

	ObservableList<sampleVO> list;
	public static BorderPane mainView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			matchService = (IMatchService) reg.lookup("match");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			membershipService = (IMemberShipService) reg.lookup("memberShip");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			memberService = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		try {
			memList = memberService.getMember();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			membershipList = membershipService.getMembershipList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		try {
			matchList = matchService.getMatchList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		/*
		 * for(MemberVO vo : memList) { System.out.println(vo.getMem_no());
		 * System.out.println(vo.getMem_id()); System.out.println(vo.getMem_name()); }
		 * for(MembershipVO vo : membershipList) {
		 * System.out.println(vo.getMembership_startdate()); } for(MatchVO vo :
		 * matchList) { System.out.println(vo.getMem_id());
		 * System.out.println(vo.getTrainer_id()); }
		 */

		int mem_no = 0;
		;
		String mem_id = "";
		String mem_name = "";
		Date membership_startdate = null;
		String trainer_id = "";
		String mem_grade = "";
		
		boolean sw = false;
		
		for (MemberVO mvo : memList) {
			sampleVO vo = new sampleVO();
			for (MembershipVO msvo : membershipList) {
				if (mvo.getMem_id().equals(msvo.getMem_id())) {
					sw = false;
					for (MatchVO mhvo : matchList) {
						if (mvo.getMem_id().equals(mhvo.getMem_id())) {
							mem_no = mvo.getMem_no();
							mem_id = mvo.getMem_id();
							mem_name = mvo.getMem_name();
							membership_startdate = msvo.getMembership_startdate();
							trainer_id = mhvo.getTrainer_id();
							mem_grade = mvo.getMem_grade();

							vo.setMem_no(mem_no);
							vo.setMem_id(mem_id);
							vo.setMem_name(mem_name);
							vo.setMembership_startdate(membership_startdate);
							vo.setTrainer_id(trainer_id);
							vo.setMem_grade(mem_grade);
							sampleList.add(vo);
							sw = true;
							break;
						}
					}
					if (!sw) {
						mem_no = mvo.getMem_no();
						mem_id = mvo.getMem_id();
						mem_name = mvo.getMem_name();
						membership_startdate = msvo.getMembership_startdate();
						mem_grade = mvo.getMem_grade();

						vo.setMem_no(mem_no);
						vo.setMem_id(mem_id);
						vo.setMem_name(mem_name);
						vo.setMembership_startdate(membership_startdate);
						vo.setTrainer_id("");
						vo.setMem_grade(mem_grade);
						sampleList.add(vo);
					}
				}
			}
		}

		/*
		 * for (sampleVO svo : sampleList) { System.out.print(svo.getMem_no());
		 * System.out.print(svo.getMem_id()); System.out.print(svo.getMem_name());
		 * System.out.print(svo.getMembership_startdate());
		 * System.out.print(svo.getTrainer_id()); System.out.println(); }
		 * 
		 * System.out.println(sampleList.size());
		 */

		Collections.sort(sampleList);

		list = FXCollections.observableArrayList();
		num.setCellValueFactory(new PropertyValueFactory<>("mem_no"));
		num.setStyle("-fx-alignment:center;");
		id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		id.setStyle("-fx-alignment:center;");
		name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		name.setStyle("-fx-alignment:center;");
		startdate.setCellValueFactory(new PropertyValueFactory<>("membership_startdate"));
		startdate.setStyle("-fx-alignment:center;");
		coach.setCellValueFactory(new PropertyValueFactory<>("trainer_id"));
		coach.setStyle("-fx-alignment:center;");
		grade.setCellValueFactory(new PropertyValueFactory<>("mem_grade"));
		grade.setStyle("-fx-alignment:center;");
		check.setCellValueFactory(new PropertyValueFactory<>("check"));
		check.setStyle("-fx-alignment:center;");

		check.setCellFactory(new Callback<TableColumn<sampleVO, Integer>, TableCell<sampleVO, Integer>>() {

			@Override
			public TableCell<sampleVO, Integer> call(TableColumn<sampleVO, Integer> param) {
				TableCell<sampleVO, Integer> cell = new TableCell<sampleVO, Integer>() {
					protected void updateItem(Integer item, boolean empty) {
						if (item != null) {
							CheckBox chkBox = new CheckBox();
							String mem_grade = sampleList.get(getIndex()).getMem_grade();
							if(mem_grade.equals("골드")) {
								chkBox.setSelected(true);
								chkBox.setDisable(true);
							}
							chkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
								@Override
								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
										Boolean newValue) {
									if(newValue.booleanValue() == true) {
										String mem_id = sampleList.get(getIndex()).getMem_id();
										trainerListMainController.mem_id = mem_id;
										System.out.println(mem_id);
									}
								}
							});
							String name = sampleList.get(getIndex()).getMem_name();
							System.out.println(name);
							setGraphic(chkBox);
						} else {
							setGraphic(null);
						}

					}
				};
				return cell;
			}
		});

		list.addAll(sampleList);
		matchingTable.setItems(list);

	}

	public void modalDialog(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			// stage.initOwner(primaryStage);
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML public void trainer_Assignment(ActionEvent event) {
		changeScene("trainerListMain.fxml");
	}

	@FXML public void cancel(ActionEvent event) {
		
		
		
		
		
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
