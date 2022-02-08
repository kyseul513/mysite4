package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	
	//글리스트 가져오기(리스트+페이징)
	public List<BoardVo> selectList2(int startRnum, int endRnum){
		System.out.println("boardDao/selectList2()");
		System.out.println(startRnum + "," + endRnum);
		
		//vo대신 map사용해서 만들어보기. (start/endRnum은 여기서만 쓰이니까 굳이 vo 만들기는 좀 그래서...)
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList2", map);
		//System.out.println(boardList);
		
		return boardList;
		
	}
	
	
	//전체 글갯수 가져오기
	public int selectTotal() {
		System.out.println("boardDao/selectTotal");
		
		return sqlSession.selectOne("board.totalCnt");
	
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
