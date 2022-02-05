package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public String restore(MultipartFile file) {
		System.out.println("FileService.restore()");

		String savDir = "C:\\javaStudy\\upload";

		//////////////// 준비단계

		// 원본파일이름 저장
		String orgName = file.getOriginalFilename();

		// 확장자 추출 - 온점의 위치를 찾아서 그 이후의 값 반환.
		String exName = orgName.substring(file.getOriginalFilename().lastIndexOf("."));
		// file.getOriginalFilename(); 형태로도 가능

		// 저장파일이름 (UUID이용해 이름을 랜덤하게 만듬)(혹시모를 중복방지 위해 현재시간+UUID 조합으로 씀)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);

		// 파일패스 생성
		String filePath = savDir + "\\" + saveName;

		// 파일 사이즈
		long fileSize = file.getSize(); // int가 아니고 long인거 주의

		
		//////////////// 파일 저장(사용자입장에서는 업로드)
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath); // 여기에 파일 만들기..
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		//DB에 관련정보 저장(과제)
		
		return saveName;		//세이브 파일이름 리턴


	}

}
