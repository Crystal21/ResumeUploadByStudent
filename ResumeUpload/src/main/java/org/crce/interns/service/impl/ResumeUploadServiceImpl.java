package org.crce.interns.service.impl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.crce.interns.dao.ResumeUploadDao;
import org.crce.interns.service.ResumeUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service("resumeUploadService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ResumeUploadServiceImpl implements ResumeUploadService {

	@Autowired
	private ResumeUploadDao resumeUploadDao;

	private String saveDirectory = "C:/Users/Crystal/workspace1/ResumeUpload/src/main/resources/Resume/";
	

	//@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void handleFileUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile fileUpload, String username)
			throws Exception {
		if (!fileUpload.isEmpty()) {

			
			System.out.println("Saving file: " + fileUpload.getOriginalFilename());

			if (!fileUpload.getOriginalFilename().equals(""))

				fileUpload.transferTo(new File(saveDirectory + fileUpload.getOriginalFilename()));

		}
		
		resumeUploadDao.addNewResume(username,saveDirectory + fileUpload.getOriginalFilename());
	}
}
