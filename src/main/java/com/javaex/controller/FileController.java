package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileService;

@Controller
@RequestMapping("/fileupload")
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	
	//파일업로드 폼
	@RequestMapping("/form")
	public String form() {
		System.out.println("FileController.form()");
		
		return "fileupload/form";
	}

	//파일업로드 처리
	@RequestMapping("/upload")		 //form.jsp 폼의 name="file"
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		System.out.println("FileController.upload()");
		//System.out.println(file);	//이거의 경우 첨부파일이 없어도 값이 console에 찍힘...
		//System.out.println(file.getOriginalFilename());	//파일이름 찍는걸로 확인하기.
		//System.out.println(file.getSize());	//파일 사이즈
		
		String saveName = fileService.restore(file);
		model.addAttribute("saveName", saveName);
		
		return "fileupload/result";
	}
	
	
	
	
}
