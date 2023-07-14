package com.bs.spring.memo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bs.spring.memo.model.dto.Memo;
import com.bs.spring.memo.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {

//	@Autowired
	public MemoService service;
		
	public MemoController(MemoService service) {
		this.service=service;
	}//v
	
	@RequestMapping("/memo.do")
	public String memo(Model model) {
		List<Memo> memos = service.selectMemoAll();
		model.addAttribute("memos", memos);
		return "memo/memo";
	}
	
	@RequestMapping(value="/insertMemo.do", method=RequestMethod.POST)
	//@PostMapping("/insertMemo.do")
	public String inertMemo(Memo memo) {
		int result=service.insertMemo(memo);
		if(result==0) {
			return "common/msg";
		}
		return "redirect:/memo/memo.do";
	}
	
	@RequestMapping("/memoDelete.do")
	public String memoDelete(int memoNo) {
		System.out.println(memoNo);
		int result=service.memoDelete(memoNo);
		
		
		return "redirect:/memo/memo.do";
	}
	
}
