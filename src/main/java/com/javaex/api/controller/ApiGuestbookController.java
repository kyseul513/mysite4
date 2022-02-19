package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping("/api/guestbook")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/addList")
	public String addList() {
		System.out.println("ApiGuestbookController.addList()");
	
		return "aGuestbook/addList";
	}
	
	@ResponseBody		//response의 body에 붙여서 guestbookList를 보낸다(데이터만 보내기)
	@RequestMapping("/List")
	public List<GuestbookVo> list() {		//데이터만 가져오기
		System.out.println("ApiGuestbookController.list()");
		
		List<GuestbookVo> guestbookList = guestbookService.list();
		System.out.println(guestbookList);
		
		return guestbookList;	//java를 json으로 바꿔주는게 필요. -> jackson
	}
	
	@ResponseBody
	@RequestMapping("/write")
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {	//파라미터로 온 값을 guestbookVo로 받기
		System.out.println("ApiGuestbookController.list().write()");
		
		//저장하고 저장된 값 리턴
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo);
		System.out.println("gVo" + gVo);
		
		return gVo;		//json으로 바꿔서 body에 붙여 보냄(@ResponseBody)
	}
	
	//방명록 글 저장 -> 저장글 리턴
	@ResponseBody
	@RequestMapping("/write2")
	public GuestbookVo write2(@RequestBody GuestbookVo guestbookVo) {	//json으로 받은건 RequestBody로 명시..
		System.out.println("ApiGuestbookController.list().write2()");
		System.out.println(guestbookVo);
		
		//저장하고 저장된 값 리턴
		GuestbookVo gVo = guestbookService.addGuestResultVo(guestbookVo);
		System.out.println("gVo" + gVo);
		
		return gVo;
	}
	
	
	@ResponseBody
	@RequestMapping("/remove")
	public String remove(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("ApiGuestbookController.list().remove()");
		System.out.println(guestbookVo);
		
		String result = guestbookService.remove(guestbookVo);
		System.out.println(result);
		
		return result;
	}
	
}
