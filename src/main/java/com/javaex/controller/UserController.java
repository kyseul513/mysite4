package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")	
public class UserController {

	@Autowired
	private UserService userService;	// new UserService를 spring에게 맡기기
										// new dao는 service에 맡김
	
	
	//////////////////////////////////
	// 로그인폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })	// "/loginForm"만도 입력 가능.
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");

		return "/user/loginForm";
	}
	
	
	//로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {	//값을 userVo 및 session으로 받기
		System.out.println("[UserController.login()]");
		//System.out.println(userVo);
		
		UserVo authUser = userService.login(userVo);	//service에 있는 login이라는 메소드에게 userVo 던져줌.
		System.out.println(authUser);
		
		if(authUser != null) {	//로그인 성공

			//세션에 저장
			System.out.println("로그인성공");
			session.setAttribute("authUser", authUser);
			
			System.out.println(authUser);
			//리다이렉트 - 메인
			return "redirect:/";
			
		}else{	//로그인 실패
			System.out.println("로그인실패");
			//리다이렉트 - 로그인폼
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	
	//로그아웃
		@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })	// "/user/loginForm"만도 입력 가능.
		public String logout(HttpSession session) {
			System.out.println("[UserController.logout()]");

			session.removeAttribute("authUser");
			session.invalidate();
			
			return "redirect:/";
		}
	
		
	//////////////////////////////////	
	//회원가입폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })	// "/user/loginForm"만도 입력 가능.
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");

		return "/user/joinForm";
	}

	
	//회원가입
	@RequestMapping(value = "/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println(userVo);
		
		userService.join(userVo);
		
		return "redirect:/";
	}
	
	
	//////////////////////////////////
	//회원정보 수정폼
	@RequestMapping(value = "/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm() {
		System.out.println("[UserController.modifyForm()]");
		
		return "/user/modifyForm";
	}
	
	
	//회원정보 수정
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.modify()]");
		
		userService.modify(userVo);
		session.setAttribute("authUser", userVo);
		
		return "redirect:/";

	}
	
}
