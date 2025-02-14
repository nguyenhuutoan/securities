package com.realpro.course.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private ResourceLoader resourceLoader;
	
	@GetMapping("/{urldetail}")
	public String loadCourseDetail(@PathVariable String urldetail) {
		return "course/" + urldetail;
	}
	
	@GetMapping("/{language}/{topic}/{id}")
	public String loadCourseTopicDetail(@PathVariable String language, @PathVariable String topic,
			@PathVariable String id) {
		return "course/" + language + "/" + topic + "/" + id;
	}
	
	@RequestMapping(value="/downloadFile")
	public void getLogFile(HttpSession session,HttpServletResponse response,
			@RequestParam("filename") String filename) throws Exception {
	    try {
	    	
	    	Resource resource = resourceLoader.getResource("classpath:/static/course_free/"+ filename +".pdf");
	    	File file = resource.getFile();
	        InputStream inputStream = new FileInputStream(file);
	        response.setContentType("application/force-download");
	        response.setHeader("Content-Disposition", "attachment; filename="+ filename +".pdf"); 
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	    } catch (Exception e){
	       
	        e.printStackTrace();
	    }

	}
	
}
