package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;

	
	
	public List<BoardVo> getList() {
		System.out.println("[Dao - getList]");
		
		List<BoardVo> bList = sqlSession.selectList("board.getList");

		return bList;
	}

	public void delete(int no) {
		System.out.println("dao: " + no);
		
		sqlSession.delete("board.delete", no);
		
	}
	
	public BoardVo read(int no) {
		System.out.println(no);
		
		BoardVo boardVo = sqlSession.selectOne("board.read", no);
				
		System.out.println(boardVo);
		return boardVo;
	}
	
	public BoardVo modifyForm(int no) {
		
		BoardVo boardVo = sqlSession.selectOne("board.read", no);
		
		return boardVo;
	}
	
	public void modify(BoardVo boardVo) {
		System.out.println("Dao: "+ boardVo);
		
		sqlSession.update("board.modify", boardVo);		
	}
	
	public void write(BoardVo boardVo) {
		
		sqlSession.insert("board.write", boardVo);
	}
}
