package com.webjjang.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webjjang.board.service.BoardService;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.util.page.PageObject;

import lombok.extern.log4j.Log4j2;

// 자동생성 & uri
@RestController
// uri module mapping
@RequestMapping("/board")
@Log4j2
public class BoardRestController {
	
	@Autowired
	private BoardService service;

	// 일반 게시판 리스트
	@GetMapping("/list.do")
	public ResponseEntity<Map<String, Object>> list(PageObject pageObject) throws Exception {

		//PageObject pageObject = PageObject.getInstance(request);
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", service.list(pageObject));
		map.put("pageObject", pageObject);
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	// 일반 게시판 글보기
	@GetMapping("/view.do")
	public ResponseEntity<BoardVO> view(@RequestParam("no") Long no, @RequestParam("inc") int inc) {
		
		log.info("no=" + no + ", inc=" + inc);
		
		BoardVO vo = service.view(no, inc);
		
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	// 일반 게시판 글등록 폼 - react에서 작성한다.
	// 일반 게시판 글등록
	@PostMapping("/write.do")
	public ResponseEntity<String> write(@RequestBody BoardVO vo) {
	
		service.write(vo);
				
		return new ResponseEntity<>("일반 게시판 글등록이 성공적으로 되었습니다.", HttpStatus.OK);
		
	}

	@PostMapping("/update.do")
	public ResponseEntity<String> update(@RequestBody BoardVO vo) {
	
		service.write(vo);
				
		return new ResponseEntity<>("일반 게시판 글등록이 성공적으로 되었습니다.", HttpStatus.OK);
		
	}

}
