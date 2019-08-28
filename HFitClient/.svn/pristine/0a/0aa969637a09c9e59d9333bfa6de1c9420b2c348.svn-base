package kr.or.ddit.view.admin;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import api.Mail;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import kr.or.ddit.service.apply.IApplyService;
import kr.or.ddit.service.trainer.ITrainerService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.ApplyVO;
import kr.or.ddit.vo.TrainerVO;

public class applyListController implements Initializable {

	@FXML
	TableView<ApplyVO> applyTable;
	@FXML
	TableColumn<ApplyVO, Integer> num;
	@FXML
	TableColumn<ApplyVO, String> name;
	@FXML
	TableColumn<ApplyVO, Integer> bir;
	@FXML
	TableColumn<ApplyVO, String> addr;
	@FXML
	TableColumn<ApplyVO, String> tel;
	@FXML
	TableColumn<ApplyVO, String> career;
	@FXML
	TableColumn<ApplyVO, String> gender;
	@FXML
	TableColumn<ApplyVO, Integer> check;
	Registry reg;
	IApplyService applyService;
	ITrainerService trainerService;
	ObservableList<ApplyVO> list, listRemove;
	ObservableList<CheckBox> boxList;
	List<ApplyVO> applyList;
	int cnt = 1;
	public static BorderPane mainView;
	Mail mail = new Mail();
	TrainerVO tvo = new TrainerVO();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		/*mainView.setTop(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("topView.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainView.setTop(parent);*/
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			applyService = (IApplyService) reg.lookup("apply");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		applyTable.setEditable(true);
		num.setCellValueFactory(new PropertyValueFactory<>("apply_no"));
		num.setStyle("-fx-alignment:center;");
		name.setCellValueFactory(new PropertyValueFactory<>("apply_name"));
		name.setStyle("-fx-alignment:center;");
		bir.setCellValueFactory(new PropertyValueFactory<>("apply_bir"));
		bir.setStyle("-fx-alignment:center;");
		addr.setCellValueFactory(new PropertyValueFactory<>("apply_addr"));
		addr.setStyle("-fx-alignment:center;");
		tel.setCellValueFactory(new PropertyValueFactory<>("apply_tel"));
		tel.setStyle("-fx-alignment:center;");
		bir.setCellValueFactory(new PropertyValueFactory<>("apply_tel"));
		bir.setStyle("-fx-alignment:center;");
		career.setCellValueFactory(new PropertyValueFactory<>("apply_careeri"));
		career.setStyle("-fx-alignment:center;");
		gender.setCellValueFactory(new PropertyValueFactory<>("apply_gender"));
		gender.setStyle("-fx-alignment:center;");
		check.setCellValueFactory(new PropertyValueFactory<>("check"));
		check.setStyle("-fx-alignment:center;");
		applyList = applyService.getAllApplyList();
		list = FXCollections.observableArrayList();
		list.setAll(applyList);
		ArrayList<Integer> arrayList = new ArrayList<>();
//		ArrayList<CheckBox> boxList = new ArrayList<>();
		listRemove = FXCollections.observableArrayList();
//		boxList = FXCollections.observableArrayList();
		
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(
				(CellEditEvent<ApplyVO, String> t) -> {
					((ApplyVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setApply_name(t.getNewValue());
		});
		addr.setCellFactory(TextFieldTableCell.forTableColumn());
		addr.setOnEditCommit(
				(CellEditEvent<ApplyVO, String> t) -> {
					((ApplyVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setApply_addr(t.getNewValue());
		});
		tel.setCellFactory(TextFieldTableCell.forTableColumn());
		tel.setOnEditCommit(
				(CellEditEvent<ApplyVO, String> t) -> {
					((ApplyVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setApply_tel(t.getNewValue());
		});
		career.setCellFactory(TextFieldTableCell.forTableColumn());
		career.setOnEditCommit(
				(CellEditEvent<ApplyVO, String> t) -> {
					((ApplyVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setApply_careeri(t.getNewValue());
		});
		gender.setCellFactory(TextFieldTableCell.forTableColumn());
		gender.setOnEditCommit(
				(CellEditEvent<ApplyVO, String> t) -> {
					((ApplyVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setApply_gender(t.getNewValue());
		});
		
		
		
		
		check.setCellFactory(new Callback<TableColumn<ApplyVO, Integer>, TableCell<ApplyVO, Integer>>() {
			@Override
			public TableCell<ApplyVO, Integer> call(TableColumn<ApplyVO, Integer> param) {
				TableCell<ApplyVO, Integer> cell = new TableCell<ApplyVO, Integer>() {
					@Override
					protected void updateItem(Integer item, boolean empty) {
						if (item != null) {
							CheckBox chkBox = new CheckBox();
							
							chkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
								@Override
								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
										Boolean newValue) {
									if(newValue.booleanValue() == true) {
										cnt = applyList.get(getIndex()).getApply_no();
										ApplyVO vo = new ApplyVO();
										for(ApplyVO avo : applyList) {
											if(avo.getApply_no() == cnt) {
												listRemove.add(avo);
//												boxList.add(chkBox);
											}
										}
//										for(ApplyVO avo : listRemove) {
//											System.out.println(avo.getTrainer_name());
//										}
										
										System.out.println("추가된 행번호 " +cnt);
										arrayList.add(cnt);
										System.out.println(arrayList);
									}
									
									if(newValue.booleanValue() == false) {
										cnt = applyList.get(getIndex()).getApply_no();
										ApplyVO vo = new ApplyVO();
										for(ApplyVO avo : applyList) {
											if(avo.getApply_no() == cnt) {
												listRemove.remove(avo);
//												boxList.add(chkBox);
											}
										}
//										for(TrainerVO tvo : listRemove) {
//											System.out.println(tvo.getTrainer_name());
//										}
										System.out.println("삭제된 행번호 "+ cnt);
										arrayList.remove(arrayList.indexOf(cnt));
										System.out.println(arrayList);
									}
								}
							});
							setGraphic(chkBox);
						} else {
							setGraphic(null);
						}
					}
				};
				return cell;
			}
		});

		applyTable.setItems(list);
		// }

	}

	@FXML public void yesBtnClick(ActionEvent event) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			trainerService = (ITrainerService) reg.lookup("trainer");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		for(ApplyVO vo : listRemove) {
			TrainerVO tvo = new TrainerVO();
			tvo.setTrainer_name(vo.getApply_name());
			tvo.setTrainer_gender(vo.getApply_gender());
			tvo.setTrainer_tel(vo.getApply_tel());
			tvo.setTrainer_bir(String.valueOf((vo.getApply_bir())));
			tvo.setTrainer_email(vo.getApply_email());
			tvo.setTrainer_addr(vo.getApply_addr());
			tvo.setTrainer_career(vo.getApply_careeri());
			tvo.setTrainer_age(25);
			tvo.setTrainer_Specialty("다이어트");
			
			try {
				trainerService.insertTrainer(tvo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			list.removeAll(listRemove);
			applyService.deleteApply(vo.getApply_no());
			
//			System.out.println(tvo.getTrainer_name());
			TrainerVO vo2 = new TrainerVO();
			try {
				vo2 = trainerService.getTrainer(tvo.getTrainer_name());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
//			System.out.println(vo2.getTrainer_id());
//			System.out.println();
			
			mail.sendMail(vo2.getTrainer_email(), vo2.getTrainer_id(), vo2.getTrainer_pass(), "register");
			
		}
		
		MethodUtil.alertShow("승인", "승인완료", "아이디와 비밀번호를 해당 메일에서 확인해주세요.");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../clientMain/home.fxml"));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainView.setCenter(null);
		mainView.setCenter(parent);
		
		
		
	}

	@FXML public void noBtnClick(ActionEvent event) {
		list.removeAll(listRemove);
		
		for(ApplyVO vo : listRemove) {
			applyService.deleteApply(vo.getApply_no());
		}
	}
}
