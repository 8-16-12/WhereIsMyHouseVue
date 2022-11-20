package com.ssafy.model.service;

import java.util.List;

import com.ssafy.dto.Notice;
import com.ssafy.dto.PageBean;

public interface NoticeService {
	public List<Notice>  searchAll(PageBean bean);
	public Notice search(int no);
	public void insert(Notice notice);
	public void update(Notice notice);
	public void delete(int no);
	public void hit(Notice notice);
}
