package com.jiafuwei.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoggerController{
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/logger")
    public void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {

         resp.getWriter().println("test logger");
         
         logger.info("这是一条日志信息 - {}", "jiafuwei");
	}
}
