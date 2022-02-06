package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	
	@RequestMapping("/list")
	public String gallery(Model model) {

		List<GalleryVo> gList = galleryService.list();
		model.addAttribute("gList", gList);
		
		//System.out.println(gList);
		return "/gallery/list";
	}

	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file, 
						 @ModelAttribute GalleryVo galleryVo) {
		
		System.out.println("gallery.upload()");
		//System.out.println(galleryVo);
		
		galleryService.restore(file, galleryVo);
		
		return "redirect:/gallery/list";
	}
	
	
	@ResponseBody
	@RequestMapping("/getImg")
	public GalleryVo getImg(@RequestParam("no") int no) {
		System.out.println("GalleryController.getImg()");
		
		GalleryVo imgVo = galleryService.getImg(no);				
		//System.out.println(imgVo);
		
		return imgVo;
	}
}
