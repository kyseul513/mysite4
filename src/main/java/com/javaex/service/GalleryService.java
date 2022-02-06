package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;

	public void restore(MultipartFile file, GalleryVo galleryVo) {
		System.out.println("GalleryService.restore()");

		String savDir = "C:\\javaStudy\\upload";

		//////////////// 준비단계

		// 원본파일이름 저장
		String orgName = file.getOriginalFilename();

		// 확장자 추출 - 온점의 위치를 찾아서 그 이후의 값 반환.
		String exName = orgName.substring(file.getOriginalFilename().lastIndexOf("."));

		// 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

		// 파일패스 생성
		String filePath = savDir + "\\" + saveName;

		// 파일 사이즈
		long fileSize = file.getSize();

		//////////////// 파일 저장(사용자입장에서는 업로드)
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		//////////////// DB에 관련정보 저장 - Gallery
		galleryVo.setFilePath(filePath);
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		galleryVo.setFileSize(fileSize);

		// System.out.println(galleryVo);

		galleryDao.restore(galleryVo);

	}
	
	public List<GalleryVo> list(){
		
		List<GalleryVo> gList = galleryDao.list();
		
		return gList;
	}
	
	
	public GalleryVo getImg(int no) {
		
		GalleryVo imgVo = galleryDao.getImg(no);
		return imgVo;
	}

}
