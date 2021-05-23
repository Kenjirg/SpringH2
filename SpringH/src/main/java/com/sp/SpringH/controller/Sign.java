package com.sp.SpringH.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sp.SpringH.service.SignService;
import com.sp.SpringH.entities.User;

@Controller
public class Sign {
	
	@Autowired
	private SignService signService;

	//首頁進來
	@RequestMapping("/sign")
	public ModelAndView entry() {
		System.out.println("從首頁進entry了");
		ModelAndView mv =new ModelAndView("sign.j");
		return mv;
	}
	
	//註冊
	@RequestMapping("/signUp")
	public ModelAndView signUp(
			@RequestParam("sup_username") String username,
			@RequestParam("sup_password") String password,
			@RequestParam("sup_sex") String sex,
			@RequestParam("sup_email") String email) {
		System.out.println("按了註冊鈕");
		System.out.println(username);
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setSex(sex);
		user.setEmail(email);
		
//		String result=null;
//		result=(signService.signUp(user))==true? "註冊成功":"註冊失敗";
		
		ModelAndView mv =new ModelAndView("sign.j");
		mv.addObject("message",signService.signUp(user));
		return mv;
	}//signup
	
	// 登入
		@RequestMapping("/signIn") 
		public ModelAndView signIn(
				@RequestParam("sin_username") String username,
				@RequestParam("sin_password") String password) {
			System.out.println("進signIN的controller");
			ModelAndView mv = new ModelAndView("member.j");
			User inUser = new User();
			User dbUser =null;
			String result=null;
			
			inUser.setUsername(username);
			inUser.setPassword(password);
			
			dbUser=(signService.signIn(inUser));
			if((dbUser)!=null) {//若傳回的是User 而非null 則登入成功
				result= "登入成功";
				mv.addObject("message", result);
				mv.addObject("username", dbUser.getUsername());
				mv.addObject("sex", dbUser.getSex());
				mv.addObject("email", dbUser.getEmail());
				return mv;
			}
			result="登入失敗";		
			mv.addObject("message", result);
			return mv;
		}//signin
		
		@RequestMapping("/forgot") // 忘密碼，進入寄信頁面
		public ModelAndView forgot() {
			ModelAndView mv = new ModelAndView("sendEmail.j");
			return mv;
		}
		
		@RequestMapping("/sendEmail") // 寄EMAIL
		public ModelAndView sendEmail(
				@RequestParam("username") String username) {
				System.out.println("進controller寄信給"+username);
				signService.sendEmail(username);
			
			ModelAndView mv = new ModelAndView("sign.j");
			mv.addObject("message", "已寄送到"+username+"的信箱，請收信並登入");
			return mv;
		}
		
}
