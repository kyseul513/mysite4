package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guestbook")
public class GuestbookController {

	// 필드
	@Autowired
	private GuestbookService guestbookService;
	// 생성자
	// 메소드 gs
	// 메소드 일반

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String guestbook(Model model) {
		System.out.println("GuestbookController > guestbook");

		List<GuestbookVo> glist = guestbookService.list();
		model.addAttribute("guestbookList", glist);

		return "/guestbook/addList";

	}

	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("GuestbookController > write");

		guestbookService.write(guestbookVo);
		System.out.println(guestbookVo);

		return "redirect:/guestbook/list";
	}

	
	
	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(@RequestParam("no")int no) {
		
		return "/guestbook/deleteForm";
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no")int no,
						 @RequestParam("pw")String pw) {
		
		System.out.println(no);
		System.out.println(pw);

		guestbookService.delete(no, pw);
		
		return "redirect:/guestbook/list";
	}
	

}
