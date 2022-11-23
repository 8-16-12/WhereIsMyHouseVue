package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.model.dao.QnaDao;
import com.ssafy.dto.Qna;
import com.ssafy.dto.PageBean;
import com.ssafy.util.PageUtility;

@Service
public class QnaServiceImp implements QnaService {
	
	@Autowired
	private QnaDao dao;
		
	
	@Transactional
	public List<Qna> searchAll(PageBean bean) {
		int cnt = dao.totalCount(bean);
		try {
			PageUtility page = new PageUtility(bean.getInterval()
											, cnt
											, bean.getPageNo()
											, "");
			bean.setPageLink(page.getPageBar());
		} catch (Exception e) {
			
		}
		return dao.searchAll(bean);
	}
	
	@Transactional(readOnly = true)
	public Qna search(int no) {
		return dao.search(no);
	}
	
	@Transactional
	public void insert(Qna qna) {
		dao.insert(qna);
//		if(true) {
//			throw new RuntimeException("롤백 되나요?");
//		}
	}
	
	
	@Transactional
	public void update(Qna qna) {
		dao.update(qna);
	}
	
	@Transactional
	public void delete(int no) {
		dao.delete(no);
	}
}




