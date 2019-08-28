package kr.or.ddit.view.myReport;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.chart.IChartService;
import kr.or.ddit.vo.ChartVO;

public class Main extends Application {

	Registry reg;
	IChartService chart;

	public void start(Stage stage) {
		try {
			reg = LocateRegistry.getRegistry("localhost", 8888);
			chart = (IChartService) reg.lookup("chart");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

		final CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Number of Day");

		final NumberAxis yAxis = new NumberAxis();

		LineChart<String, Number> lineChart;
		lineChart = new LineChart<String, Number>(xAxis, yAxis);
		lineChart.setTitle("My Healthy Chart");

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("몸무게");

//		XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
//		series2.setName("BMI");

		ChartVO cv = new ChartVO();
		cv.setMem_id("kjs132");

		List<ChartVO> list = null;
		try {
			list = chart.getAllChart(cv);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdmf = new SimpleDateFormat("yymmdd");
		for(ChartVO cvResult :list) {
		String date = sdmf.format(cvResult.getChart_date());
		series.getData().add(new XYChart.Data<String, Number>(date, cvResult.getMem_kg()));
		}
		
		lineChart.getData().add(series);

		Scene scene = new Scene(lineChart, 800, 600);

		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
