package api;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
   public static void main(String[] args) {
  //    sendMail("lovelysh24@gmail.com", "test", "forgotPW");
   }
   
   public static String pw;
   public static String pw() {
//	   String pw = "";
	   for (int i = 0; i < 12; i++) {
		   pw += (char) ((Math.random() * 26) + 97);
	   }
	return pw;
   }   
   
   
   /**
    * 인증번호, 임시비밀번호 전송용 메서드
    * @param mem_id 메일을 보낼 ID(E-mail)
    * @param code RandomCodeGenerator 클래스로 생성한 무작위 영문 대문자 8자리 코드
    * @param type 회원가입 인증시(register) / 비밀번호 분실시(forgotPW)를 구별하는 String값
    */
   public static void sendMail(String mem_id, String code, String code1, String type) {
      String host = "gmail-smtp-in.google.com";
      
//      final String user = "nuinuimusic@gmail.com";
//      final String password = "nuinuimusic1!!";
      final String user = "healthnfit48@gmail.com";
      final String password = "hfit0531";
      
      String to = mem_id;
      
      Properties props = new Properties();
      
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");
      

      Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                  return new PasswordAuthentication(user, password);
               }
            });
      
      // Compose the message
            try {
               MimeMessage message = new MimeMessage(session);
               message.setFrom(new InternetAddress(user));
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                     to));
               
               if(type.equals("forgotPW")){
            	   message.setSubject("[Health&Fit] 회원님의 임시 비밀번호입니다.");
            	   message.setText("회원님의 임시 비밀번호 : " + code + "\n\n위의 임시비밀번호를 입력하신 후 반드시 비밀번호를 변경해주세요.");
               }else if(type.equals("register")){
            	   message.setSubject("[Health&Fit] 트레이너 등록이 완료되었습니다.");
            	   message.setText("트레이너 아이디 : " + code + "\n트레이너 비밀번호:"  + code1 + "\n\n트레이너 등록이 완료되었으므로, 부여받은 아이디와 비밀번호로 활동을 시작해주세요.");
            	   //message.setText("트레이너 비밀번호 : " + code + "\n\n트레이너 등록이 완료되었으므로, 부여받은 아이디와 비밀번호로 활동을 시작해주세요.");
               }

             /*  if(type.equals("register")) {
                  // 메일 제목
                  message.setSubject("[Health&Fit] 회원가입 인증번호입니다.");
                  
                  // 메일 내용
                  message.setText("인증번호 : " + code + "\n\n회원가입창의 인증번호란에 인증번호를 입력해주세요.");
               } else if(type.equals("forgotPW")){
                  // 메일 제목
                  message.setSubject("[NuiNuiMusic] 회원님의 임시 비밀번호입니다.");
                  
                  // 메일 내용
                  message.setText("회원님의 임시 비밀번호 : " + code + "\n\n위의 임시비밀번호로 로그인하신뒤 반드시 비밀번호를 변경해주세요.");
               }*/
               
               // send the message
               Transport.send(message);
               System.out.println("message sent successfully...");

            } catch (MessagingException e) {
               e.printStackTrace();
            }
            
            //pw = pw();
   }
   
   /*String uuid = UUID.randomUUID().toString().replaceAll("-", "");
   uuid = uuid.substring(0, 10); */

 
}