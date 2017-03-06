package com.jiafuwei.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiafuwei.spring.po.QRCode;
import com.jiafuwei.spring.po.User;
import com.jiafuwei.spring.service.IQRCodeService;
import com.jiafuwei.spring.service.IUserService;
import com.jiafuwei.spring.util.UuidUtil;

@Controller
public class LoggerController{
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IQRCodeService qRCodeService;
	
	@RequestMapping("/logger")
    public void handleRequest(HttpServletRequest request, HttpServletResponse resp) throws Exception {

		QRCode qRCode = qRCodeService.getQRCodeById("123");
		logger.info("qRCode："+qRCode);
		
		qRCode.setKey_id(UuidUtil.generateShortUuid());
		int urldfdf = qRCodeService.insert(qRCode);
		logger.info("urldfdf："+urldfdf);
		
		StringBuffer url = request.getRequestURL();
		logger.info("RequestURI - {}", request.getRequestURI());
		logger.info("ContextPath - {}", request.getContextPath());
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).append("/").toString(); 
		logger.info("这是一条日志信息 - {}", tempContextUrl);
		
		User user = userService.getUserById(1);
		System.out.println(user.getUserName());
		 logger.info("值："+user.getUserName());
		
         resp.getWriter().println("test logger");
         
         logger.info("这是一条日志信息 - {}", "jiafuwei");
	}
}
