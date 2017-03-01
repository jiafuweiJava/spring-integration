package com.jiafuwei.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiafuwei.spring.po.User;
import com.jiafuwei.spring.service.IUserService;

@Controller
public class LoggerController{
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/logger")
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		User user = userService.getUserById(1);
		System.out.println(user.getUserName());
		 logger.info("值："+user.getUserName());
		
         resp.getWriter().println("test logger");
         
         logger.info("这是一条日志信息 - {}", "jiafuwei");
	}
}
