package kr.or.ddit.view.myReport.diary;

import java.net.URL;
import java.util.ResourceBundle;

import com.calendarfx.view.CalendarView;
import com.calendarfx.view.page.WeekPage;
import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class DiaryMainController implements Initializable {

	// Fx:id of controls
	@FXML
	private StackPane weekPage;
	@FXML
	private JFXButton save;

	public WeekPage detailedWeekView;
	@FXML
	private JFXButton print;
	@FXML
	private JFXButton diet;
	@FXML
	private JFXButton exercise;
	@FXML
	private JFXButton etc;
	@FXML
	private JFXButton myReportBtn;
	@FXML
	private StackPane myReportPane;
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		CalendarView cl = new CalendarView(); // Main agenda view in application
		// It show only the week page
		cl.setShowSourceTray(false);
		cl.setShowAddCalendarButton(false);
		cl.setShowPrintButton(false);
		cl.setShowPageToolBarControls(false);
		cl.setShowPageSwitcher(false);
		cl.showWeekPage();
		// add application.css file to CalendarView
		cl.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());

		// call setWeekPage method from MainScreen and pass the CalendarView Object
		MainScreen.setWeekPage(cl);
		weekPage.getChildren().add(cl);
//		Stage primaryStage = (Stage) weekPage.getScene().getWindow(); 
//		primaryStage.setOnCloseRequest(e-> {
//			Platform.exit();	
//		});

	}

	// Action Event on Save Changes Button
	@FXML
	void saveData(ActionEvent event) {

		MainScreen.saveEntry();

	}

	// Above buttons event
	@FXML
	void addEtcEntry(ActionEvent event) {

		SaveDiet.setDiet(MainScreen.getDiet());
	}

	@FXML
	void addExerciseEntry(ActionEvent event) {

		SaveExercise.setExercise(MainScreen.getExercise());
	}

	@FXML
	void addDietEntry(ActionEvent event) {

		SaveEtc.setEtc(MainScreen.getEtc());
	}
	
	@FXML
	void myReportBtnClick(ActionEvent event) {
		try {
			ObservableList<Node> list = FXCollections.observableArrayList(myReportBtn.getScene().getRoot().getChildrenUnmodifiable());
			
			myReportPane.setTranslateX(0);
			
			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(myReportPane.translateXProperty(), 350);
			KeyFrame keyFrame = new KeyFrame(
	    		Duration.millis(100), 
	    		new EventHandler<ActionEvent>() {
		        	@Override
		        	public void handle(ActionEvent event) {
		        		((StackPane) list.get(1)).getChildren().remove(myReportPane);
		        	}
		        }, 
		        keyValue
	        );
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
