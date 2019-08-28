package kr.or.ddit.view.board.challenge;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import kr.or.ddit.LoginSession.LoginSession;
import kr.or.ddit.service.check.ICheckService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.ChallApplyVO;
import kr.or.ddit.vo.CheckVO;

public class CheckController implements Initializable {

	@FXML
	GridPane gridCheckList;
	ICheckService checkS;
	Registry reg;

	public void initialize(URL location, ResourceBundle resources) {
		try {
			this.reg = LocateRegistry.getRegistry(8888);
			this.checkS = ((ICheckService) this.reg.lookup("check"));
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		List list = null;
		try {
			ChallApplyVO cav = new ChallApplyVO();
			cav.setChall_no(CheckBoardController.applyChallComboNum.intValue());
			cav.setMem_id(LoginSession.memSession.getMem_id());
			list = this.checkS.selectIdChallNo(cav);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		int size = 0;
		for (int i = 0; i < list.size(); i++) {
			if (((CheckVO) list.get(i)).getCheck_approve() == 1) {
				size++;
				if (size == 2) {
					MethodUtil.alertShow("알림", "성공!", "첼린지미션에 성공하셨습니다!");
					break;
				}
			}
		}
		int size2 = 0;
		ImageView img = null;
		try {
			img = new ImageView(
					new Image(new BufferedInputStream(new FileInputStream("img/etc/check.jpg"))));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		img.setFitWidth(70.0D);
		img.setFitHeight(70.0D);

		if (size != 0)
			outer : for (int i = 0; i < 5; i++)
				for (int j = 0; j < 5; j++) {
						this.gridCheckList.add(img, j, i);
					size2++;
					if (size2 == size)
						break outer;
				}
	}
}