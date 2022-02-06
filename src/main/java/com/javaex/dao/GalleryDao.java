package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;

	public void restore(GalleryVo galleryVo) {
		System.out.println("GalleryDao.restore()");

		sqlSession.insert("gallery.restore", galleryVo);
	}

	public List<GalleryVo> list() {
		List<GalleryVo> gList = sqlSession.selectList("gallery.list");
		
		return gList;
	}

	
	public GalleryVo getImg(int no) {
		
		GalleryVo imgVo = sqlSession.selectOne("gallery.getImg", no);	
		return imgVo;
	}
}
