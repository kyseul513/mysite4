package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	//리스트 가져오기
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("[Controller - list]");
	
		List<BoardVo> bList = boardService.getList();
		model.addAttribute("boardList", bList);

		return "/board/list";
	}
	
	
	//삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam("no") int no) {

		boardService.delete(no);
		
		return "redirect:/board/list";
	}
	
	
	//글읽기
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam("no") int no, Model model) {
		
		BoardVo boardVo = boardService.read(no);
		model.addAttribute(boardVo);
		
		System.out.println(boardVo);
		return "/board/read";
	}
	
	
	//수정폼
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no, Model model) {
		
		BoardVo boardVo = boardService.modifyContent(no);
		model.addAttribute(boardVo);
		
		System.out.println(boardVo);
		return "/board/modifyForm";
	}
	
	
	//수정
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("controller: "+ boardVo);
		
		boardService.modify(boardVo);
		
		return "redirect:/board/list";
	}
	
	
	//작성폼
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		
		return "/board/writeForm";
		
	}
	
	
	//작성
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		
		boardService.write(boardVo);
		
		return "redirect:/board/list";
	}
	
	
}
