package com.ssafy.model.service;

import java.util.List;

import com.ssafy.dto.Qna;
import com.ssafy.dto.PageBean;

public interface QnaService {
	public List<Qna>  searchAll(PageBean bean);
	public Qna search(int no);
	public void insert(Qna qna);
	public void update(Qna qna);
	public void delete(int no);
}
