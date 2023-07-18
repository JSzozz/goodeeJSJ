package com.bs.spring.board.model.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService service;

	public BoardController(BoardService service) {
		this.service=service;
	}
	
	
	@RequestMapping("/boardList.do")
	public String selectBoardAll(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="5") int numPerpage,
			Model m) {
		//페이지에 맞는 데이터를 가져와야함.
		List<Board> boards=service.selectBoardAll(Map.of("cPage",cPage,"numPerpage",numPerpage));
		int totalData=service.selectBoardCount();
		//paging
		m.addAttribute("pageBar",PageFactory.getPage(cPage,numPerpage,totalData,"boardList.do"));
		m.addAttribute("totalData",totalData);
		m.addAttribute("boards",boards);
		boards.stream().forEach(System.out::println);
		return "board/boardList";
	}
	
	@RequestMapping("/boardForm.do")
	public String boardForm() {
		return "board/boardForm";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(Board b) {
		
		return "";
	}
	
	@RequestMapping("/boardView.do")
	public String selectBoardByNo(Model model, int no) {
		model.addAttribute("board",service.selectBoardById(no));
		return "board/boardView";
	}
	
	
}
