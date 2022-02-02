package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public List<GuestbookVo> list() {

		List<GuestbookVo> glist = guestbookDao.list();

		return glist;
	}

	public void write(GuestbookVo guestbookVo) {

		guestbookDao.write(guestbookVo);
	}

	
	public void delete(GuestbookVo guestbookVo) {
		
		guestbookDao.delete(guestbookVo);

	}

	
	//방명록 글 저장 -> 저장글 리턴
	public GuestbookVo addGuestResultVo(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService/addGuestResultVo");
		
		//저장하기
		int count = guestbookDao.insertSelectKey(guestbookVo);
		
		//저장한 내용 가져오기 (화면에 그리기 위해)
		int no = guestbookVo.getNo();
		return guestbookDao.selectGuest(no);
	}
			
	
}
