package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
	//리스트(리스트+페이징)
	public Map<String, Object> getBoardList2(int crtPage){
		System.out.println("boardService/getBoardList2");
		
		////////////////리스트 가져오기////////////////
		
		//페이지당 글개수
		int listCnt = 10;
		
		//페이지 숫자가 유효하지 않을 경우
		//if(crtPage <= 10) {
		//	crtPage = 1;
		//}
		
		//현재페이지 처리(삼항연산자)(참고)
		crtPage = (crtPage>0) ? crtPage : (crtPage=1);
				  //(조건문) ? 참일때 : 거짓일때
		
		//시작글 번호 (ex 1p: 1, 6p: 51)
		int startRnum = (crtPage-1)*listCnt + 1; 
		
		//끝글 번호
		int endRnum = (startRnum + listCnt) - 1;
		
		//dao로 넘기기
		List<BoardVo> boardList = boardDao.selectList2(startRnum, endRnum);
		
		
		////////////////페이징 버튼////////////////
		
		//전체 글갯수 가져오기
		int totalCnt = boardDao.selectTotal();
		//System.out.println("totalCnt =" + totalCnt);
		
		//페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		//마지막 버튼 번호 (1p~5p: 1~'5', 6p~10p: 6~'10')
			//1 1~5  0.2		Math.ceil 이용해 올림처리하면 1~5 모두 1
			//2 1~5  0.4
			//3 1~5  0.6
			//4 1~5  0.8
			//5 1~5  1
			//10 6~10  1.2		올림처리하면 모두 2
			//11 11~15 2.2  	올림처리하면 모두 3
		
		int endPageBtnNo = (int)(Math.ceil(crtPage/(double)pageBtnCount))*pageBtnCount;
							//crtPage, pageBtnCount를 double형으로 나누었으니 이걸 다시 int로 형변환.
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1); 
		
		//다음 화살표 유무
		boolean next = false;
		if(endPageBtnNo*listCnt < totalCnt) {
			next = true;
		}else{
			//다음 화살표가 안보이면 마지막 버튼값을 다시 계산한다.
			endPageBtnNo = (int)(Math.ceil(totalCnt/(double)listCnt));
		}
		
		//이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		
		////////////////포장////////////////
		Map<String, Object> pMap = new HashMap<String, Object>();
				//담아야 하는 값들이 int, boolean, list로 다양하니 Object로 받아줌
		
		pMap.put("prev", prev);
		pMap.put("startPageBtnNo", startPageBtnNo);
		pMap.put("endPageBtnNo", endPageBtnNo);
		pMap.put("next", next);
		pMap.put("boardList", boardList);
		
		System.out.println("--------------------------------------");
		System.out.println(pMap);
		System.out.println("--------------------------------------");
		
		return pMap;
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
		/*
		//페이지 데이터 추가 123개
		for(int i=1; i<=123; i++) {
			boardVo.setTitle(i + "번째글 제목입니다.");
			boardVo.setContent(i + "번째글 재용입니다.");
			boardVo.setUser_no(i);
			boardVo.setHit(0);
			boardVo.setUser_no(1);
			boardVo.setName("정우성");
			
			boardDao.write(boardVo);
		}
		*/
		boardDao.write(boardVo);
	}
	
}
