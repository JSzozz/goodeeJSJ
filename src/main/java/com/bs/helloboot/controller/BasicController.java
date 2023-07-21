package com.bs.helloboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BasicController {

	
	@PostMapping("/fileUpload")
	public String fileUpload(MultipartFile upFile) {
		log.debug(upFile.getOriginalFilename());
		log.debug("{}",upFile.getSize());
		
		//upFile.transferTo(new File(path+fileName))
		
		return "redirect:/";
	}
}
