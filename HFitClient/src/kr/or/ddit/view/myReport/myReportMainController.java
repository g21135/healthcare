package kr.or.ddit.view.myReport;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.chart.IChartService;
import kr.or.ddit.service.trainerTalk.ChatServer;
import kr.or.ddit.vo.ChartVO;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class myReportMainController implements Initializable {
	public static int sub;

	@FXML
	JFXButton dairybtn;
	@FXML
	BorderPane borderPane;
	@FXML
	StackPane stackPane;
	@FXML
	JFXButton trainerTalk;

	Parent parent;
	IChartService chart;
	Registry reg;

	@FXML
	JFXButton mySubscription;

	@FXML
	LineChart<String, Number> lineChart;
	@FXML
	CategoryAxis xAxis;
	@FXML
	NumberAxis yAxis;

	@FXML
	Label result;
	@FXML
	TextField weight;
	@FXML
	TextField height;
	XYChart.Series<String, Number> series2;
	XYChart.Series<String, Number> series;
	boolean sw = false;
	ChatServer server;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			chart = (IChartService) reg.lookup("chart");
			server = (ChatServer) reg.lookup("server");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		chartSet();
	}

	public void modalDialog(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage stage = new Stage();

			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.setOnCloseRequest(e->{
				try {
					server.memberDisconnect(LoginSession.memSession);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			});
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void dairyBtnClick() {
		try {
			Parent diary = FXMLLoader.load(getClass().getResource("./diary/DiaryMain.fxml"));

			stackPane.getChildren().add(diary);

			diary.setTranslateX(350);
			Timeline timeline = new Timeline();
			KeyValue keyValue = new KeyValue(diary.translateXProperty(), 0);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(100), keyValue);
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void chartSet() {
		lineChart.getData().clear();
		
	
		xAxis.setLabel("Number of Day");

		yAxis.setAutoRanging(false);
		yAxis.setLowerBound(10);
		yAxis.setUpperBound(100);
		yAxis.setTickUnit(10);

		lineChart.setTitle("My Healthy Chart");
		series = new XYChart.Series<String, Number>();
		series.setName("몸무게");
		
		series2 = new XYChart.Series<String, Number>();
		series2.setName("BMI");
		
		ChartVO cv = new ChartVO();
		cv.setMem_id(LoginSession.memSession.getMem_id());

		List<ChartVO> list = null;
		try {
			list = chart.getAllChart(cv);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdmf = new SimpleDateFormat("MM월 dd일");

		for (ChartVO cvResult : list) {
			double bmi = Math.round(cvResult.getMem_kg() /Math.pow(cvResult.getMem_height(), 2) *10000);
			String date = sdmf.format(cvResult.getChart_date());
			series.getData().add(new XYChart.Data<String, Number>(date, cvResult.getMem_kg()));
			series2.getData().add(new XYChart.Data<String, Number>(date, bmi));
		}
		
		lineChart.getData().addAll(series,series2);
	}
	@FXML
	public void trainerTalkBtnClick() {
		// 트레이너가 접속중일 때만 실행 가능
		try {
			modalDialog("trainerTalk/trainerTalkMain.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void okBtnClick(ActionEvent event) {
		int w = 0;
		int h = 0;

		try {
			w = Integer.parseInt(weight.getText());
			h = Integer.parseInt(height.getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		ChartVO cv = new ChartVO();
		cv.setMem_id(LoginSession.memSession.getMem_id());
		cv.setMem_kg(w);
		cv.setMem_height(h);
		cv.setChart_date(new Date());
		try {
			chart.insert(cv);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		chartSet();
		
		double bmi = Math.round(w /Math.pow(h, 2) *10000);
		if (bmi<15.0){
			result.setText(bmi + " : 병적인 저체중");
			}
			else if (15.0<=bmi && bmi<18.5){
				result.setText(bmi + " : 저체중");
			}
			else if (18.5<=bmi && bmi<23.0){
				result.setText(bmi + " : 정상");
			}
			else if (23.0<=bmi && bmi<=27.5){
				result.setText(bmi + " : 과체중");
			}
			else if (27.5<bmi && bmi<=40.0){
				result.setText(bmi + " : 비만");
			}
			else if (40.0<bmi){
				result.setText(bmi + " : 병적인 비만");
			}
	}

	@FXML
	public void mySubscriptionBtnClick() {
		Parent mySub = null;
		try {
			mySub = FXMLLoader.load(getClass().getResource("./mySub/MySub.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// borderPane.setCenter(mySub);
		stackPane.getChildren().add(mySub);

		mySub.setTranslateX(350);
		Timeline timeline = new Timeline();
		KeyValue keyValue = new KeyValue(mySub.translateXProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(100), keyValue);
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();

	}

}
