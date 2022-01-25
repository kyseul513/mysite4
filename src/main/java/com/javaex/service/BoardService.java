package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public List<BoardVo> getList() {

		List<BoardVo> bList = boardDao.getList();
		System.out.println(bList);

		return bList;
	}

	public void delete(int no) {
		System.out.println("service: " + no);

		boardDao.delete(no);
	}

	public BoardVo read(int no) {
		System.out.println(no);

		BoardVo boardVo = boardDao.read(no);

		return boardVo;
	}

	public BoardVo modifyContent(int no) {

		BoardVo boardVo = boardDao.modifyForm(no);

		return boardVo;
	}

	public void modify(BoardVo boardVo) {
		
		boardDao.modify(boardVo);
	}

	public void write(BoardVo boardVo) {
		
		boardDao.write(boardVo);
	}
	
}
