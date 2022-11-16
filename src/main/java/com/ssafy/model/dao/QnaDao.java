package com.ssafy.model.dao;

import java.util.List;

import com.ssafy.dto.Qna;
import com.ssafy.dto.PageBean;

public interface QnaDao {
	public List<Qna>  searchAll(PageBean bean);
	public int totalCount(PageBean bean);
	public Qna  search(int no);
	public void insert(Qna qna);
	public void update(Qna qna);
	public void delete(int no);
}
