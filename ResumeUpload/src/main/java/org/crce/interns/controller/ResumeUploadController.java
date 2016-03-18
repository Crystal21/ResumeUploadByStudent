package org.crce.interns.controller;

import javax.servlet.http.HttpServletRequest;


import org.crce.interns.service.ResumeUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResumeUploadController {

	
	@Autowired
	private ResumeUploadService resumeUploadService;
	
	@RequestMapping("/")
	public ModelAndView welcome() {
				return new ModelAndView("index");
	}
	
	@RequestMapping( value = "/uploadFile", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, @RequestParam CommonsMultipartFile fileUpload,@RequestParam("username")String username)
			throws Exception {

		
		//System.out.println(year);
		
		resumeUploadService.handleFileUpload(request,fileUpload,username);
		// loadCopyFile("user_schema.userdetails");
		
		// returns to the same index page
		return "index";
	}
}
