package kr.or.ddit.view.myReport.diary;

import javafx.scene.control.Alert;

//Alert message show when user will click on save changes button
public class AlertShow {

	public static void setAlert()
	{
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setHeaderText(null);
    	alert.setContentText("Data are successfully saved in database");
    	alert.show();
	}
}
