package kr.or.ddit.view.join;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import kr.or.ddit.clientMain.ClientMainController;
import kr.or.ddit.service.member.IMemberService;
import kr.or.ddit.util.MethodUtil;
import kr.or.ddit.vo.MemberVO;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;

public class joinMainCotroller implements Initializable{

	@FXML BorderPane joinView;
	@FXML TextField id;
	@FXML TextField pass;
	@FXML TextField pass2;
	@FXML TextField name;
	@FXML TextField birth;
	@FXML TextField tel;
	@FXML TextField email;
	@FXML TextField addr;
	@FXML JFXTextField txtCaptcha;
	@FXML JFXRadioButton man;
	@FXML JFXRadioButton woman;
	@FXML FontAwesomeIconView check;
	@FXML FontAwesomeIconView replay;
	@FXML ImageView captchaImage;

	Registry reg;
	IMemberService member;
	Pattern p;
	Matcher m;
	private String captcha_str; 
	int chk_cnt = 0;
	int pattern_cnt = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		makeCaptcha();
		replay.setOnMouseClicked(e->{
			makeCaptcha();
		});
		try {
			reg = LocateRegistry.getRegistry(8888);
			member = (IMemberService) reg.lookup("member");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void doubleBtnClick() {
		// 회원 아이디 중복 체크
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO mv = new MemberVO();
		mv.setMem_id(id.getText());
		
		try {
			list = member.searchId(mv);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (list.size() == 0) {
			MethodUtil.alertShow("INFORMATION", "아이디 중복 체크", "사용하실 수 있는 아이디입니다.");
			chk_cnt++;
			pattern_cnt++;
			System.out.println("아이디 중복체크" + chk_cnt);
		} else {
			MethodUtil.alertShow("INFORMATION", "아이디 중복 체크", "이미 사용하고 있는 아이디입니다.");
			return;
		}
		
	/*	// 아이디 정규식
		p = Pattern.compile("^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$");
		m = p.matcher(id.getText());
		if(m.find()) {
			if (list.size() == 0) {
				MethodUtil.alertShow("INFORMATION", "아이디 중복 체크", "사용하실 수 있는 아이디입니다.");
				chk_cnt++;
				pattern_cnt++;
				System.out.println("아이디 중복체크" + chk_cnt);
			} else {
				MethodUtil.alertShow("INFORMATION", "아이디 중복 체크", "이미 사용하고 있는 아이디입니다.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "영어, 숫자로만 5~12자 사이로 입력해 주세요", "ID 체크", JOptionPane.WARNING_MESSAGE);
			tfMemId.clear();
			return;
		}*/
		
	}
	
	
	// 회원가입 확인 버튼
	@FXML public void okBtnClick() throws Exception{
		MemberVO mv = new MemberVO();
		
		mv.setMem_id(id.getText());
		
		//비밀번호 정규식, 확인체크
		p = Pattern.compile("^[a-zA-Z0-9]{1,16}$");
		m = p.matcher(pass.getText());
		if(m.find()) {
			if(pass.getText().equals(pass2.getText())) {
//				mv.setMem_pass(AES128Test.encrypt(pass.getText()));
				mv.setMem_pass(pass.getText());
			}else {
				MethodUtil.alertShow("INFORMATION", "비밀번호 체크!", "비밀번호가 일치하지 않습니다.");
				return;
			}
		}else {
			MethodUtil.alertShow("INFORMATION", "비밀번호 체크!", "영문대소문자, 숫자, 특수문자 포함");
			pass.clear();
			pass2.clear();
			return;
		}
		
		
		//이름 정규식
		p = Pattern.compile("^[가-힣]*$");
		m = p.matcher(name.getText());
		if(m.find()) {
			mv.setMem_name(name.getText());
		}else {
			MethodUtil.alertShow("INFORMATION", "이름 체크!", "이름은 한글만 입력해주세요.");
			name.clear();
			return;
		}
		
		mv.setMem_bir(birth.getText());
		mv.setMem_email(email.getText());
		
		//전화번호 정규식
		p = Pattern.compile("^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$");
		m = p.matcher(tel.getText());
		if(m.find()) {
			mv.setMem_tel(tel.getText());
		}else {
			MethodUtil.alertShow("INFORMATION", "전화번호 체크!", "010-0000-0000 형식으로 입력해 주세요.");
			tel.clear();
			return;
		}
		
		ToggleGroup group = new ToggleGroup();
		man.setToggleGroup(group);
		man.setUserData("남자");
		woman.setToggleGroup(group);
		woman.setUserData("여자");
		
		String gender = "";
		if(group.getSelectedToggle().getUserData() != null) {
			gender = group.getSelectedToggle().getUserData().toString();
		}
		mv.setMem_gender(gender);
		
		mv.setMem_addr(addr.getText());
		mv.setMem_grade("실버");
//		mv.setMem_check("0");
		mv.setMem_no(7);
		
		member.insertMember(mv);
		MethodUtil.alertShow("INFORMATION", "회원가입 완료!", "회원가입이 완료되었습니다.");
		changeScene("/kr/or/ddit/clientMain/home.fxml");
		
		if(captcha_str.equals(txtCaptcha.getText())) {
			member.insertMember(mv);
			MethodUtil.alertShow("INFORMATION", "회원가입 완료!", "회원가입이 완료되었습니다.");
			joinView.setTop(null);
			joinView.setCenter(null);
			changeScene("/kr/or/ddit/clientMain/home.fxml");
		}
		


		
	}
	
	@FXML public void cancelBtnClick() {
		joinView.setTop(null);
		joinView.setCenter(null);
		
		changeScene("/kr/or/ddit/clientMain/home.fxml");
	} 
	
	public void makeCaptcha() {
    	
	     /*  *  [자동가입문자 정의 부분]
	       *  200 * 50 에해당하는 이미지 사이즈를 지정하고, 자동가입방지 문자 개수를 설정한다.
	       *  gimp(),addNoise(),addText() 함수는 여러번 호출할 수 있다.*/
	       
	       Captcha captcha = new Captcha.Builder(250, 150)
	    		   					.addBackground(new GradiatedBackgroundProducer())
	                                .addText() //Default로 5개의 랜덤한 알파벳과 숫자를 생성
	                                //.addBackground() //바탕색 흰색 - Default
	                                .addBackground(new GradiatedBackgroundProducer()) //Gradiated 백그라운드 효과 추가
	                                .addNoise()// 한번 호출할 때마다 하나의 라인이 추가된다.
	                                //.gimp(new DropShadowGimpyRenderer()) //그림자 효과 추가
	                                .gimp()
	                                .addBorder() //검정 테두리 선 생성
	                                .build(); //필수 호출 함수

      
	        //**** 핵심코드 **************************************************
	        // 자동가입 문자 Image를 생성한다

	        BufferedImage bimg = captcha.getImage();
	        try {
	            //BufferedImage bi = getMyImage();  // retrieve image
	            
	            // BufferedImage를 byte배열로 변환하기
	            byte[] imageInByte;
	            // convert BufferedImage to byte array
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(bimg, "jpg", baos);
	            baos.flush();
	            imageInByte = baos.toByteArray();
	            baos.close();
	            // convert byte array back to BufferedImage
	            InputStream in = new ByteArrayInputStream(imageInByte);
	            
	            Image captcharImg = new Image(in);
	            
	            captchaImage.setImage(captcharImg);

	        } catch (IOException e) {
	        	captcha_str = null;
	            e.printStackTrace();
	        }
	        
	        // Captcha가 생성한 자동가입방지 문자를 return 받아서 String 변수에 할당
	        captcha_str = captcha.getAnswer();
    }
	
	
	
	
	public FXMLLoader changeScene(String fxmlURL) {
		joinView.setCenter(null);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlURL));
		Parent parent = null;
		try {
			parent = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		joinView.setCenter(parent);
		return loader;
	}

}













