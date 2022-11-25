package com.shop.goodee.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("notice/*")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("detail")
	public void getDetail()throws Exception{
		
	}
	
}
