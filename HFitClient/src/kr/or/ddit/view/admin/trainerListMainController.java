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
import kr.or.ddit.service.trainer.ITrainerService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.view.hope.UserHopeMain;
import kr.or.ddit.vo.MatchVO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MembershipVO;
import kr.or.ddit.vo.TrainerVO;
import kr.or.ddit.vo.sampleVO;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.event.ActionEvent;

public class trainerListMainController implements Initializable {

	@FXML TableView<TrainerVO> trainerTable;
	@FXML TableColumn<TrainerVO, Integer> num;
	@FXML TableColumn<TrainerVO, String> id;
	@FXML TableColumn<TrainerVO, String> name;
	@FXML TableColumn<TrainerVO, String> gender;
	@FXML TableColumn<TrainerVO, Integer> age;
	@FXML TableColumn<TrainerVO, String> specialty;
	@FXML TableColumn<TrainerVO, Integer> check;
	public static String mem_id;
	List<TrainerVO> trainerList = new ArrayList<>();
	MatchVO mavo = new MatchVO();
	int cnt = 0;
	
	Registry reg;
	ITrainerService trainerService;
	IMatchService matchService;
	IMemberService memberService;
	ObservableList<TrainerVO> list, listRemove;
	public static BorderPane mainView;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			trainerService = (ITrainerService) reg.lookup("trainer");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			trainerList = trainerService.getAllTrainerList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		num.setCellValueFactory(new PropertyValueFactory<>("trainer_no"));
		num.setStyle("-fx-alignment:center;");
		id.setCellValueFactory(new PropertyValueFactory<>("trainer_id"));
		id.setStyle("-fx-alignment:center;");
		name.setCellValueFactory(new PropertyValueFactory<>("trainer_name"));
		name.setStyle("-fx-alignment:center;");
		gender.setCellValueFactory(new PropertyValueFactory<>("trainer_gender"));
		gender.setStyle("-fx-alignment:center;");
		age.setCellValueFactory(new PropertyValueFactory<>("trainer_age"));
		age.setStyle("-fx-alignment:center;");
		specialty.setCellValueFactory(new PropertyValueFactory<>("trainer_Specialty"));
		specialty.setStyle("-fx-alignment:center;");
		check.setCellValueFactory(new PropertyValueFactory<>("check"));
		check.setStyle("-fx-alignment:center;");
		
		list = FXCollections.observableArrayList();
		list.setAll(trainerList);
		listRemove = FXCollections.observableArrayList();
		
		check.setCellFactory(new Callback<TableColumn<TrainerVO,Integer>, TableCell<TrainerVO,Integer>>() {
		
			@Override
			public TableCell<TrainerVO, Integer> call(TableColumn<TrainerVO, Integer> param) {
				TableCell<TrainerVO, Integer> cell = new TableCell<TrainerVO, Integer>(){
					@Override
					protected void updateItem(Integer item, boolean empty) {
						if(item != null) {
							CheckBox chkBox = new CheckBox();
							
							chkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {

								@Override
								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
										Boolean newValue) {
									if(newValue.booleanValue() == true) {
										cnt = trainerList.get(getIndex()).getTrainer_no();
										for(TrainerVO tvo : trainerList) {
											if(tvo.getTrainer_no() == cnt) {
												listRemove.add(tvo);
											}
										}
										String trainer_id = trainerList.get(getIndex()).getTrainer_id();
										mavo.setMem_id(mem_id);
										mavo.setTrainer_id(trainer_id);
										System.out.println(trainer_id);
										System.out.println(mem_id);
									}
								}
							});
							setGraphic(chkBox);
						}
						else {
							setGraphic(null);
						}
					}
				};
				return cell;
			}
		});
		
		
		
		
		
		trainerTable.setItems(list);
		
		
		
	}

	@FXML public void ok(ActionEvent event) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			matchService = (IMatchService) reg.lookup("match");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			matchService.insertMatch(mavo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			memberService =  (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		try {
			memberService.updateMember(mem_id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
		MethodUtil.alertShow("매칭완료", "매칭완료", "매칭이 완료되었습니다");
		
		changeScene("memberMain.fxml");
		
		
		
	}

	@FXML public void delete(ActionEvent event) {
		
		list.removeAll(listRemove);
		
		
		
		
		
		
		
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
