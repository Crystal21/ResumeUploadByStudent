package org.crce.interns.service.impl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.crce.interns.dao.ResumeUploadDao;
import org.crce.interns.exception.IncorrectFileFormatException;
import org.crce.interns.exception.MaxFileSizeExceededError;
import org.crce.interns.service.ResumeUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



@Service("resumeUploadService")
public class ResumeUploadServiceImpl implements ResumeUploadService {

	@Autowired
	private ResumeUploadDao resumeUploadDao;

	private String saveDirectory = "C:/Users/Crystal/workspace1/ResumeUpload/src/main/resources/Resume/";
	
	public void handleFileUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile fileUpload, String username)
			throws Exception {
		final String fullPath = saveDirectory + fileUpload.getOriginalFilename();
		if (!fileUpload.isEmpty()) {
			
			
			IncorrectFileFormatException e = new IncorrectFileFormatException();
			MaxFileSizeExceededError m = new MaxFileSizeExceededError();
			
			
			File file = new File(fullPath);
			final String extension = FilenameUtils.getExtension(fullPath);
				
			System.out.println("Saving file: " + fileUpload.getOriginalFilename());
			System.out.println(extension);		
			
			
			if(!(extension.equals("pdf") || extension.equals("docx") || extension.equals("odt")))
				throw e;
			
			if (!fileUpload.getOriginalFilename().equals(""))
				fileUpload.transferTo(new File(fullPath));
			
				final long size = FileUtils.sizeOf(file);
				System.out.println(size);
				if(size > 512520)
					throw m;
		}
		
		resumeUploadDao.addNewResume(username,fullPath);
	}
}
