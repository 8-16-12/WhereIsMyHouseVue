package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.dto.Notice;
import com.ssafy.dto.PageBean;
import com.ssafy.model.dao.NoticeDao;
import com.ssafy.util.PageUtility;

@Service
public class NoticeServiceImp implements NoticeService {
	
	@Autowired
	private NoticeDao dao;
	
	@Transactional(readOnly = true)
	public List<Notice> searchAll(PageBean bean) {
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
	public Notice search(int no) {
		return dao.search(no);
	}

	@Transactional
	public void insert(Notice notice) {
		dao.insert(notice);
	}

	@Transactional
	public void update(Notice notice) {
		dao.update(notice);
	}

	@Transactional
	public void delete(int no) {
		dao.delete(no);
	}

}
