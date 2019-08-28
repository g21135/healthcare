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
import kr.or.ddit.clientMain.ClientMainController;
import kr.or.ddit.service.trainer.ITrainerService;
import kr.or.ddit.vo.TrainerVO;

public class trainerMainController implements Initializable {

	@FXML
	TableView<TrainerVO> trainerTable;
	@FXML
	TableColumn<TrainerVO, Integer> num;
	@FXML
	TableColumn<TrainerVO, String> id;
	@FXML
	TableColumn<TrainerVO, String> name;
	@FXML
	TableColumn<TrainerVO, String> gender;
	@FXML
	TableColumn<TrainerVO, String> age;
	@FXML
	TableColumn<TrainerVO, String> specialty;
	@FXML
	TableColumn<TrainerVO, Integer> check;
	Registry reg;
	ITrainerService trainerService;
	ObservableList<TrainerVO> list, listRemove;
	ObservableList<CheckBox> boxList;
	List<TrainerVO> trainerList;
	int cnt = 1;
	public static BorderPane mainView;
	
	
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
			trainerService = (ITrainerService) reg.lookup("trainer");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		trainerTable.setEditable(true);
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
		try {
			trainerList = trainerService.getAllTrainerList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		list = FXCollections.observableArrayList();
		list.setAll(trainerList);
		ArrayList<Integer> arrayList = new ArrayList<>();
//		ArrayList<CheckBox> boxList = new ArrayList<>();
		listRemove = FXCollections.observableArrayList();
//		boxList = FXCollections.observableArrayList();
		
		id.setCellFactory(TextFieldTableCell.forTableColumn());
		id.setOnEditCommit(
				(CellEditEvent<TrainerVO, String> t) -> {
					((TrainerVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setTrainer_id(t.getNewValue());
		});
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(
				(CellEditEvent<TrainerVO, String> t) -> {
					((TrainerVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setTrainer_name(t.getNewValue());
		});
		gender.setCellFactory(TextFieldTableCell.forTableColumn());
		gender.setOnEditCommit(
				(CellEditEvent<TrainerVO, String> t) -> {
					((TrainerVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setTrainer_gender(t.getNewValue());
		});
		specialty.setCellFactory(TextFieldTableCell.forTableColumn());
		specialty.setOnEditCommit(
				(CellEditEvent<TrainerVO, String> t) -> {
					((TrainerVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setTrainer_Specialty(t.getNewValue());
		});
		
		
		
		check.setCellFactory(new Callback<TableColumn<TrainerVO, Integer>, TableCell<TrainerVO, Integer>>() {
			@Override
			public TableCell<TrainerVO, Integer> call(TableColumn<TrainerVO, Integer> param) {
				TableCell<TrainerVO, Integer> cell = new TableCell<TrainerVO, Integer>() {
					@Override
					protected void updateItem(Integer item, boolean empty) {
						if (item != null) {
							CheckBox chkBox = new CheckBox();
							
							chkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
								@Override
								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
										Boolean newValue) {
									if(newValue.booleanValue() == true) {
										cnt = trainerList.get(getIndex()).getTrainer_no();
										TrainerVO vo = new TrainerVO();
										for(TrainerVO tvo : trainerList) {
											if(tvo.getTrainer_no() == cnt) {
												listRemove.add(tvo);
//												boxList.add(chkBox);
											}
										}
										for(TrainerVO tvo : listRemove) {
											System.out.println(tvo.getTrainer_name());
										}
										
										System.out.println("추가된 행번호 " +cnt);
										arrayList.add(cnt);
										System.out.println(arrayList);
									}
									
									if(newValue.booleanValue() == false) {
										cnt = trainerList.get(getIndex()).getTrainer_no();
										TrainerVO vo = new TrainerVO();
										for(TrainerVO tvo : trainerList) {
											if(tvo.getTrainer_no() == cnt) {
												listRemove.remove(tvo);
											}
										}
										for(TrainerVO tvo : listRemove) {
											System.out.println(tvo.getTrainer_name());
										}
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

		trainerTable.setItems(list);
		// }

	}

	@FXML
	public void updateBtnClick(ActionEvent event) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			trainerService =  (ITrainerService) reg.lookup("trainer");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		for(TrainerVO vo : list) {
			try {
				trainerService.updateTrainer(vo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	public void deleteBtnClick(ActionEvent event) {
		list.removeAll(listRemove);
		
		/*for(MemberVO vo : list) {
			try {
				memberService.deleteMember(vo.getMem_id());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}*/
		
	}

	@FXML public void applyList(ActionEvent event) {
		changeScene("applyList.fxml");
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
