package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository // 자동으로 주입되는 대상이라는 표시.... applicationContext.xml 파일도 만들기
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 유저정보 가져오기(로그인시 사용)
	public UserVo selectUser(UserVo userVo) {
	      //자료형
		System.out.println("[UserDao.selectUser()]");

		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		return authUser;
	}

	// 가입
	public void join(UserVo userVo) {

		sqlSession.insert("user.join", userVo);

	}

	
	// 회원정보 수정
	public void modify(UserVo userVo) {
		
		sqlSession.update("user.modify", userVo);

	}

}
