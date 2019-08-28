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

import com.jfoenix.controls.JFXButton;

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
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.vo.MemberVO;

public class MemberMainController implements Initializable {

	@FXML
	TableView<MemberVO> memberTable;
	@FXML
	TableColumn<MemberVO, Integer> num;
	@FXML
	TableColumn<MemberVO, Integer> check;
	@FXML
	TableColumn<MemberVO, String> id;
	@FXML
	TableColumn<MemberVO, String> grade;
	@FXML
	TableColumn<MemberVO, String> name;
	@FXML
	TableColumn<MemberVO, String> gender;
	@FXML
	TableColumn<MemberVO, String> tel;
	@FXML
	JFXButton memberBtnClick;
	@FXML
	JFXButton prodBtnClick;
	@FXML
	JFXButton saleBtnClick;
	@FXML
	JFXButton matchingBtnClick;
	@FXML
	JFXButton trainerBtnClick;
	Registry reg;
	IMemberService memberService;
	ObservableList<MemberVO> list, listRemove;
	ObservableList<CheckBox> boxList;
	List<MemberVO> memList;
	int cnt = 1;
	@FXML
	JFXButton deleteBtnClick;
	@FXML TableColumn<MemberVO, String> pass;
	@FXML TableColumn<MemberVO, String> bir;
	@FXML TableColumn<MemberVO, String> email;
	@FXML TableColumn<MemberVO, String> addr;
	@FXML BorderPane mainView;

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
			memberService = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		memberTable.setEditable(true);
		num.setCellValueFactory(new PropertyValueFactory<>("mem_no"));
		num.setStyle("-fx-alignment:center;");
		check.setCellValueFactory(new PropertyValueFactory<>("mem_check"));
		check.setStyle("-fx-alignment:center;");
		id.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		id.setStyle("-fx-alignment:center;");
		pass.setCellValueFactory(new PropertyValueFactory<>("mem_pass"));
		pass.setStyle("-fx-alignment:center;");
		name.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		name.setStyle("-fx-alignment:center;");
		grade.setCellValueFactory(new PropertyValueFactory<>("mem_grade"));
		grade.setStyle("-fx-alignment:center;");
		bir.setCellValueFactory(new PropertyValueFactory<>("mem_bir"));
		bir.setStyle("-fx-alignment:center;");
		email.setCellValueFactory(new PropertyValueFactory<>("mem_email"));
		email.setStyle("-fx-alignment:center;");
		tel.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
		tel.setStyle("-fx-alignment:center;");
		addr.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
		addr.setStyle("-fx-alignment:center;");
		gender.setCellValueFactory(new PropertyValueFactory<>("mem_gender"));
		gender.setStyle("-fx-alignment:center;");
		try {
			memList = memberService.getAllMemberList();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		list = FXCollections.observableArrayList();
		list.setAll(memList);
		ArrayList<Integer> arrayList = new ArrayList<>();
//		ArrayList<CheckBox> boxList = new ArrayList<>();
		listRemove = FXCollections.observableArrayList();
//		boxList = FXCollections.observableArrayList();
		
		pass.setCellFactory(TextFieldTableCell.forTableColumn());
		pass.setOnEditCommit(
				(CellEditEvent<MemberVO, String> t) -> {
					((MemberVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setMem_pass(t.getNewValue());
		});
		name.setCellFactory(TextFieldTableCell.forTableColumn());
		name.setOnEditCommit(
				(CellEditEvent<MemberVO, String> t) -> {
					((MemberVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setMem_name(t.getNewValue());
		});
		email.setCellFactory(TextFieldTableCell.forTableColumn());
		email.setOnEditCommit(
				(CellEditEvent<MemberVO, String> t) -> {
					((MemberVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setMem_email(t.getNewValue());
		});
		tel.setCellFactory(TextFieldTableCell.forTableColumn());
		tel.setOnEditCommit(
				(CellEditEvent<MemberVO, String> t) -> {
					((MemberVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setMem_tel(t.getNewValue());
		});
		addr.setCellFactory(TextFieldTableCell.forTableColumn());
		addr.setOnEditCommit(
				(CellEditEvent<MemberVO, String> t) -> {
					((MemberVO) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setMem_addr(t.getNewValue());
		});
		
		
		
		check.setCellFactory(new Callback<TableColumn<MemberVO, Integer>, TableCell<MemberVO, Integer>>() {
			@Override
			public TableCell<MemberVO, Integer> call(TableColumn<MemberVO, Integer> param) {
				TableCell<MemberVO, Integer> cell = new TableCell<MemberVO, Integer>() {
					@Override
					protected void updateItem(Integer item, boolean empty) {
						if (item != null) {
							CheckBox chkBox = new CheckBox();
							
							chkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
								@Override
								public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
										Boolean newValue) {
									if(newValue.booleanValue() == true) {
										cnt = memList.get(getIndex()).getMem_no();
										MemberVO vo = new MemberVO();
										for(MemberVO mvo : memList) {
											if(mvo.getMem_no() == cnt) {
												listRemove.add(mvo);
//												boxList.add(chkBox);
											}
										}
										for(MemberVO mv : listRemove) {
											System.out.println(mv.getMem_name());
										}
										
										System.out.println("추가된 행번호 " +cnt);
										arrayList.add(cnt);
										System.out.println(arrayList);
									}
									
									if(newValue.booleanValue() == false) {
										cnt = memList.get(getIndex()).getMem_no();
										MemberVO vo = new MemberVO();
										for(MemberVO mvo : memList) {
											if(mvo.getMem_no() == cnt) {
												listRemove.remove(mvo);
											}
										}
										for(MemberVO mv : listRemove) {
											System.out.println(mv.getMem_name());
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

		memberTable.setItems(list);
		// }

	}

	@FXML
	public void updateBtnClick(ActionEvent event) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			memberService = (IMemberService) reg.lookup("member");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		for(MemberVO vo : list) {
			try {
				memberService.updateMember(vo);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
//			System.out.println(vo.getMem_id());
//			System.out.println(vo.getMem_name());
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

	
	
	

}
