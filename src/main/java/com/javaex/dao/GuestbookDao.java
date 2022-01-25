package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	
	//리스트 가져오기
	public List<GuestbookVo> list() {
		System.out.println("GuestbookDao.getList()");
		
		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList");
		System.out.println(list);
		
		return list;
	}
	
	public void write(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.write()");
		System.out.println(guestbookVo);
		
		sqlSession.insert("guestbook.write", guestbookVo);
		
	}
	
	public void delete(int no, String pw) {
		
		GuestbookVo guestbookVo = new GuestbookVo(no,pw);
		sqlSession.delete("guestbook.delete", guestbookVo);
	}
	
	
}
