package com.bs.spring.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

@RestController
public class RestMemberController {
	
	// REST API, RESTFul 예)
	// GET localhost:9090/spring/member -> 전체회원조회
	// GET localhost:9090/spring/member/{id}1||admin -> 회원 1명 조회
	// POST localhost:9090/spring/member -> 회원추가
	// PUT localhost:9090/spring/member -> 회원수정
	// DELETE localhost:9090/spring/member -> 회원삭제

	private MemberService service;
	
	public RestMemberController(MemberService service) {
		this.service=service;
	}
	
	@GetMapping
	public List<Member> selectMemberAll(){
		return service.selectMemberAll();
	}
	
	@GetMapping("/{id}")
	public Member selectMemberById(@PathVariable("id") String id){
		return service.selectMemberById(Map.of("userId",id));
	}
	
	@PostMapping
	public int insertMember(@RequestBody Member m) {
		return service.insertMember(m);
	}
	
//	@PutMapping
//	public int insertMember(@RequestBody Member m) {
//		return service.updateMember(m);
//	}
	
//	@DeleteMapping("/{id}")
//	public int deleteMember(@PathVariable("id") String id) {
//		return service.deleteMember(m);
//	}
	
	//특정 게시글의 댓글들 가져오기(URL설계)
	//ex. 서버주소/board/{no}/comment/{commentno}
}
