package com.ssafy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.dto.Notice;
import com.ssafy.dto.PageBean;
import com.ssafy.dto.QnaException;
import com.ssafy.model.service.NoticeService;

@RestController
@RequestMapping("/admin")
public class NoticeRestController {

	private Logger logger = LoggerFactory.getLogger(QnaRestController.class);
	
	@Autowired
	private NoticeService noticeService;
	
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
	
	@GetMapping("/notice")
	public ResponseEntity<?> noticeList(PageBean bean) {
		logger.debug("noticeList............................{}", bean);
		List<Notice> notices = noticeService.searchAll(bean);
		logger.debug("noticeList............................{}",notices);
		if (notices!=null && !notices.isEmpty()) {
			return new ResponseEntity<List<Notice>>(notices, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/notice")
	public ResponseEntity<String> regist(@RequestBody Notice notice) {
		logger.debug("notice regist............................");
		noticeService.insert(notice);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);	// 궁금한 점. 그냥 ok만 보내주고 Void로 하면 안돼요?
	}
	
	@GetMapping("/notice/{no}")
	public ResponseEntity<?> search(@PathVariable int no) {
		logger.debug("NoticeController.search....................no:{}", no);
		Notice notice = noticeService.search(no);
		logger.debug("NoticekController.search....................notice:{}", notice);
		if (notice != null) {
			noticeService.hit(notice);
			return new ResponseEntity<Notice>(notice, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/notice")
	public ResponseEntity<String> udpate(@RequestBody Notice notice) {
		logger.debug("NoticeController.udpate....................no:{}", notice);
		noticeService.update(notice);
		logger.debug("NoticeController.udpate....................qna:{}", notice);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@DeleteMapping("/notice")
	public ResponseEntity<String> remove(@RequestParam int no) {
		logger.debug("BookController.remove....................isbn:{}", no);
		noticeService.delete(no);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
