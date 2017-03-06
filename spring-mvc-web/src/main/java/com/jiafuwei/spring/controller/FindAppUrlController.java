package com.jiafuwei.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jiafuwei.spring.po.User;
import com.jiafuwei.spring.service.IUserService;

@Controller
public class FindAppUrlController{
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/findAppUrl")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse resp) throws Exception {

        logger.info("这是一条日志信息 - {}", "findAppUrl");
        
        ModelAndView mv = new ModelAndView();
     	//封装要显示到视图的数据
     	mv.addObject("jiafuwei","Hello");
     	//视图名
     	mv.setViewName("app");
     	
     	return mv;
	}
}
