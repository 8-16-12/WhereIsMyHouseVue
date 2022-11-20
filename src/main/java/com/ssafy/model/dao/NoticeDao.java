package com.ssafy.model.dao;

import java.util.List;

import com.ssafy.dto.Notice;
import com.ssafy.dto.PageBean;

public interface NoticeDao {
	public List<Notice>  searchAll(PageBean bean);
	public int totalCount(PageBean bean);
	public Notice search(int no);
	public void insert(Notice notice);
	public void update(Notice notice);
	public void delete(int no);
	public void hit(Notice notice);
}
