package org.crce.interns.controller;

import javax.servlet.http.HttpServletRequest;

import org.crce.interns.service.ResumeUploadService;
import org.crce.interns.service.impl.IncorrectFileFormatException;
import org.crce.interns.service.impl.MaxFileSizeExceededError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResumeUploadController {

	//private int error = 0;
	
	@Autowired
	private ResumeUploadService resumeUploadService;
	
	@RequestMapping("/")
	public ModelAndView welcome() {
				return new ModelAndView("index");
	}
	
	@RequestMapping( value = "/uploadFile", method = RequestMethod.POST)
	public ModelAndView resumeUpload(HttpServletRequest request, @RequestParam(required = false) CommonsMultipartFile fileUpload,@RequestParam("username")String username)
			throws Exception{
		
		//System.out.println(year);
		try{
			System.out.println("in try");
		resumeUploadService.handleFileUpload(request,fileUpload,username);
		}catch (IncorrectFileFormatException e) {
			//System.out.println("exception caught");
			//error = 1;
			System.out.println(e);
			ModelAndView model = new ModelAndView("index");
			model.addObject("error", 1);
			return model;
		}catch (MaxFileSizeExceededError m) {
			System.out.println(m);
			ModelAndView model = new ModelAndView("index");
			model.addObject("error1", 1);
			return model;
		}
		return new ModelAndView("index");
	}
}
