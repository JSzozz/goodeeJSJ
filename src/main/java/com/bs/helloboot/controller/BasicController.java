package com.bs.helloboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bs.helloboot.dto.MemberDto;
import com.bs.helloboot.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@CrossOrigin(origins = "http://localhost:8889")
public class BasicController {

	@Value("${linux.url}")
	private String url;
	@Value("${linux.baseDir}")
	private String baseDir;
	
	private MemberService service;
	
	public BasicController(MemberService service) {
		this.service=service;
	}
	
	@PostMapping("/fileUpload")
	public String fileUpload(MultipartFile[] upFile) {
//		log.debug(upFile.getOriginalFilename());
//		log.debug("{}",upFile.getSize());
		
		for(MultipartFile f : upFile) {
			log.debug(f.getOriginalFilename());
			log.debug("{}",f.getSize());
		}
		//upFile.transferTo(new File(path+fileName))
		
		return "redirect:/";
	}
	
	@GetMapping("/values")
	public String valuesCheck() {
		log.debug(url);
		log.debug(baseDir);
		return "redirect:/";
	}
	
	@PostMapping("/datatest")
	public String datatest(String data) {
		log.debug(data);
		return "redirect:/";
	}
	
	@PostMapping("/memberId")
	@ResponseBody
	public MemberDto selectMemberById(String userId) {
		return service.selectMemberById(userId);
	}
	
	
	
	
}
