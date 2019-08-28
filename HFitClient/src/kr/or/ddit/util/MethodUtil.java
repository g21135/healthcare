package kr.or.ddit.util;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import kr.or.ddit.service.mediaUtil.IMdeiaUtil;
import javafx.scene.control.Alert.AlertType;

/**
 * dialog 창 띄우기 위한 용도
 * 
 * @author kjs94
 *
 */
public class MethodUtil {
	
	public static void alertShow(String title, String header, String content) {
		Alert alertInformation = null;
		alertInformation = new Alert(AlertType.INFORMATION); // 아이콘 지정
		alertInformation.setTitle(title);
		alertInformation.setHeaderText(header);
		alertInformation.setContentText(content);
		alertInformation.showAndWait(); // alert창 보이기
	}

	public static ButtonType alertConfirmShow(String title, String header, String content) {
		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);

		alertConfirm.setTitle(title);
		alertConfirm.setHeaderText(header);
		alertConfirm.setContentText(content);

		ButtonType confirmResult = alertConfirm.showAndWait().get();

		return confirmResult;
	}

	static Registry rs;
	static IMdeiaUtil media;
	


}