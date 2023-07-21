package com.bs.helloboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BasicController {

	@Value("${linux.url}")
	private String url;
	@Value("${linux.baseDir}")
	private String baseDir;
	
	
	
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
	
	
	
	
	
	
	
	
}
