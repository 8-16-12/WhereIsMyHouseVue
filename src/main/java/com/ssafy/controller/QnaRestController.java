package com.ssafy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.dto.Qna;
import com.ssafy.dto.QnaException;
import com.ssafy.dto.PageBean;
import com.ssafy.model.service.QnaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/admin")
public class QnaRestController {

	private Logger logger = LoggerFactory.getLogger(QnaRestController.class);
	
	@Autowired
	private QnaService qnaService;
	
	private static final String SUCCESS = "success";
	
	@ExceptionHandler
	public ResponseEntity<String> handler(Exception e) {
		logger.error("ErrorHandler..............................");
		logger.error("Error Message............................{}", e.getMessage());
		e.printStackTrace();
		
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json:charset=UTF-8");
		if (e instanceof QnaException) {
			return new ResponseEntity<String>(e.getMessage(), resHeaders, HttpStatus.FAILED_DEPENDENCY);
		}else {
			return new ResponseEntity<String>("처리 중 오류 발생", resHeaders, HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@ApiOperation(value = "책 목록 정보", notes = "도서 정보를 검색 조건에 맞게 검색한 결과")
	@GetMapping("/qna")
	public ResponseEntity<?> qnaList(PageBean bean) {
		logger.debug("bookList............................{}", bean);
		List<Qna> qnas = qnaService.searchAll(bean);
		logger.debug("bookList............................{}",qnas);
		if (qnas!=null && !qnas.isEmpty()) {
			return new ResponseEntity<List<Qna>>(qnas, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	

	@ApiOperation(value = "책 정보 등록", notes = "전달 받은 데이터를 이용해서 도서 정보를 등록")
	@PostMapping("/qna")
	public ResponseEntity<String> regist(@RequestBody Qna qna) {
		logger.debug("qna regist............................");
		qnaService.insert(qna);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);	// 궁금한 점. 그냥 ok만 보내주고 Void로 하면 안돼요?
	}
	
	@ApiOperation(value = "책 정보 검색", notes = "isbn에 해당하는 책 정보를 검색")
	@GetMapping("/qna/{no}")
	public ResponseEntity<?> search(@RequestParam int no) {
		logger.debug("QnaController.search....................no:{}", no);
		Qna qna = qnaService.search(no);
		logger.debug("BookController.search....................qna:{}", qna);
		if (qna != null) {
			return new ResponseEntity<Qna>(qna, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "책 정보 수정", notes = "전달 받은 데이터를 이용해서 도서 정보를 수정")
	@PutMapping("/qna")
	public ResponseEntity<String> udpate(@RequestBody Qna qna) {
		logger.debug("QnaController.udpate....................no:{}", qna);
		qnaService.update(qna);
		logger.debug("QnaController.udpate....................qna:{}", qna);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@ApiOperation(value = "책 정보 삭제", notes = "isbn에 해당하는 책 정보를 삭제")
	@DeleteMapping("/qna")
	public ResponseEntity<String> remove(@RequestParam int no) {
		logger.debug("BookController.remove....................isbn:{}", no);
		qnaService.delete(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}















