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

	public void delete(int no, String pw) {
		
		guestbookDao.delete(no, pw);

	}
}
