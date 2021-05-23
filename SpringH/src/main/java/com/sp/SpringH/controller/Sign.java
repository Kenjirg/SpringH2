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
		mv.addObject("msg",signService.signUp(user));
		return mv;
	}
	
}
