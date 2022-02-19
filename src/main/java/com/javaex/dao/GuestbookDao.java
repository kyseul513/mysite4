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

	// 리스트 가져오기
	public List<GuestbookVo> list() {
		System.out.println("GuestbookDao.getList()");

		List<GuestbookVo> list = sqlSession.selectList("guestbook.getList");
		System.out.println(list);

		return list;
	}

	// 방명록 글 1개 가져오기
	public GuestbookVo selectGuest(int no) {
		System.out.println("GuestbookDao.selectGuest()");

		// GuestbookVo guestbookVo = sqlSession.selectOne("guestbook.selectByNo", no);
		// return guestbookVo 이 형태와 아래 형태는 같은 역할을 함.

		return sqlSession.selectOne("guestbook.selectByNo", no); // 변수 안만들고 바로 리턴시키기
	}

	// 방명록 글 저장
	public void write(GuestbookVo guestbookVo) {
		System.out.println("GuestbookDao.write()");
		System.out.println(guestbookVo);

		sqlSession.insert("guestbook.write", guestbookVo);

	}

	// 방명록 글 저장(selectKey) - 리턴 성공한 갯수
	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("guestbookDao/insertSelectKey");
		// System.out.println(guestbookVo); //이름, 비밀번호, 내용

		// sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
		// System.out.println(guestbookVo); //번호, 이름 ,비밀번호, 내용

		return sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
	}

	// 방명록 글 삭제
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("guestbookDao/delete");

		return sqlSession.delete("guestbook.delete", guestbookVo);
	}

}
