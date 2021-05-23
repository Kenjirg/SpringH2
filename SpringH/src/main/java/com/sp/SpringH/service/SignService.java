package com.sp.SpringH.service;

import static org.junit.Assert.assertNotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.conf.PropertyDefinitions.ZeroDatetimeBehavior;
import com.sp.SpringH.entities.User;
import com.sp.SpringH.repository.TestH;
import com.sp.SpringH.repository.UserRepository;

@Service
public class SignService {

	@Autowired
	private UserRepository userRepository;

	public String signUp(User user) {// 註冊會員
		System.out.println("進svc註冊");
		String result=null;
		// 檢查Email
		if (!emailVali(user.getEmail())) {//若格式錯誤
			System.out.println("email錯誤");
			return "註冊失敗，email格式錯誤";
		}
		// encode
		System.out.println(user);
		if (!"".equals(user.getPassword())) {//若鍵入的碼不是空的
			String enc = encode(user.getPassword());
			user.setPassword(enc);
		}
		System.out.println(user);
		// DAO
		userRepository.createUser(user);
		return "註冊完成";
	}

//	public User signIn(User inUser) {
//		User dbUser = null;
//		String dbCode = null;
//		String inCode = null;
//
//		// DAO DB to take the User
//		dbUser = userRepository.readUser(inUser);
//		System.out.println("read is");
//		dbCode = dbUser.getPassword();
//		System.out.println("dbCode: " + dbCode);
//
//		// encode to get inCode
//		System.out.println("sign in is");
//		inCode = encode(inUser.getPassword());
//		System.out.println("inCode: " + inCode);
//
//		// 比對
//		if (dbCode.equals(inCode)) {// 密碼正確
//			System.out.println("code right");
//			return dbUser;
//		}
//		return null;// 密碼錯誤
//	}

//	public String ckUsername(String username) {
//		if(userRepository.findUsername(username)) {
//			return "此帳號已被註冊";
//		}
//		return "此帳號尚未註冊，可使用。";
//	}
	
//	public void sendEmail(String username){
//		User user=new User();
//		//提取EMAIL
//		String email =userRepository.findEmail(username);
//		System.out.println("取得"+email);
//		//產生新密碼
//		String newCode=randomPass();
//		System.out.println("新CODE是"+newCode);
//		//encode放入DB
//		String putToDbCode= encode(newCode);
//		System.out.println("放入DB的是"+putToDbCode);
//		
//		user.setUsername(username);
//		user.setPassword(putToDbCode);
//		System.out.println("放置新密碼結果: "+ userRepository.putNewCode(user));
//		//寄出
//		doSend(email,newCode);
//	}
	
	public void doSend(String to,String newCode) {
		Properties props = System.getProperties();
		String from= "phctwstudent01@gmail.com";
		String pass= "mail12!!";
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length()];
			message.addRecipients(Message.RecipientType.TO, to);
			message.setSubject("請使用新密碼通知信");
			message.setText("您的密碼已更新："+newCode+"，謝謝。");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	private Boolean emailVali(String email) {// 檢查email
		String regexEmail = "^\\w{1,20}@[a-zA-Z0-9]{2,10}\\.[a-zA-Z]{2,10}(\\.[a-zA-Z]{2,10})?$";
//		String test = "aaa@somewhere.com";
		return email.matches(regexEmail);
	}
	
	private String randomPass() {
		Random random=new Random();
		Integer num=random.nextInt(9999)+1;
		String newCode=String.valueOf(num);
		return newCode;
	}

	private String encode(String str) {
		String result = null;
		try {
			MessageDigest mDigest = MessageDigest.getInstance("MD5");
			byte[] bArray1 = str.getBytes();
			mDigest.update(bArray1);
			byte[] bArray2 = mDigest.digest();
			result = bArrToStr(bArray2);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
//		System.out.println(result);
		return result;
	}

	private String bArrToStr(byte[] b) {
		char[] dgs = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] cArray = new char[b.length * 2];

		int i = 0;
		for (byte z : b) {
			cArray[i++] = dgs[z >>> 4 & 0xf];
			cArray[i++] = dgs[z & 0xf];
		}

		String s = new String(cArray);
		return s;
	}

	@Test
	public void test() {
		
	}
}
