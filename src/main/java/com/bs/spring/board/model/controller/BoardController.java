package com.bs.spring.board.model.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
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
	public String insertBoard(Board b, MultipartFile[] upFile, HttpSession session, Model model) {
		log.info("{}",b);
		log.info("{}",upFile);
		
		//MultipartFile에서 제공하는 메소드를 이용해서
		//파일을 저장할 수 있음 -> transferTo()메소드를 이용
		//절대경로 가져오기
		String path = session.getServletContext().getRealPath("/resources/upload/board/");
		//파일명에 대한 renamed규칙을 설정
		//직접 리네임 규칙을 만들어서 저장해보자.
		//yyyyMMdd_HHmmssSSS_랜덤값
//		List<Attachment> files=new ArrayList();
		if(upFile!=null) {
			for(MultipartFile mf:upFile) {
				if(!mf.isEmpty()) {
					String oriName=mf.getOriginalFilename();
					String ext=oriName.substring(oriName.lastIndexOf("."));//v
					Date today=new Date(System.currentTimeMillis());//import java.util.Date
					SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
					int rdn=(int)(Math.random()*10000)+1;
					String rename=sdf.format(today)+"_"+rdn+ext;
					try {
						mf.transferTo(new File(path+rename));
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					Attachment file=Attachment.builder()
								.originalFilename(oriName)
								.renamedFilename(rename)
								.build();
					//b.setFile(file);
					b.getFile().add(file);
				}
			}
		}
		try {
			service.insertBoard(b);
		}catch(RuntimeException e) {
			//rollback해야하는 경우
			for(Attachment a: b.getFile()) {
				File delFile=new File(path+a.getRenamedFilename());
				delFile.delete();
			}
			model.addAttribute("msg","글쓰기 등록실패! :P");
			model.addAttribute("loc", "/board/boardForm.do");
			return "common/msg";
		}
		
		return "redirect:/board/boardList.do";
	}
	
	@RequestMapping("/boardView.do")
	public String selectBoardByNo(Model model, int no) {
		model.addAttribute("board",service.selectBoardById(no));
		return "board/boardView";
	}
	
	@RequestMapping("/filedownload")
	public void fileDown(String oriname, String rename, OutputStream out,
				@RequestHeader(value="user-agent") String header,
				HttpSession session, HttpServletResponse response) {
		
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		File downloadFile = new File(path+rename);
		try(FileInputStream fis=new FileInputStream(downloadFile); 
			BufferedInputStream bis=new BufferedInputStream(fis);
			BufferedOutputStream bos=new BufferedOutputStream(out);){
			boolean isMs=header.contains("Trident")||header.contains("MSIE");
			String encodeRename="";
			if(isMs) {
				encodeRename=URLEncoder.encode(oriname, "UTF-8");
				encodeRename=encodeRename.replaceAll("\\+", "%20");
			}else {
				encodeRename=new String(oriname.getBytes("UTF-8"), "ISO-8859-1");
			}
			response.setContentType("application/octet-stream;charset=utf-8");
			response.setHeader("Content-Disposition","attachment;filename=\""+encodeRename+"\"");
			
			int read=-1;
			while((read=bis.read())!=-1) {
				bos.write(read);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
