package com.jiafuwei.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jiafuwei.spring.common.StringUtil;
import com.jiafuwei.spring.po.QRCode;
import com.jiafuwei.spring.po.User;
import com.jiafuwei.spring.service.IQRCodeService;
import com.jiafuwei.spring.service.IUserService;

@Controller
public class FindAppUrlController{
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IQRCodeService qRCodeService;
	
	@RequestMapping("/findAppUrl/{key_id}")
    public ModelAndView handleRequest(@PathVariable String key_id, HttpServletRequest request, HttpServletResponse resp) throws Exception {
		if(StringUtil.isEmpty("")){
			System.out.println("123");
		}
		
        logger.info("这是一条日志信息 - {}", "findAppUrl");
        logger.info("key_id - {}", key_id);
        
        QRCode qRCode = qRCodeService.getQRCodeById(key_id);
        
        ModelAndView mv = new ModelAndView();
     	//封装要显示到视图的数据
     	mv.addObject("android_url", qRCode.getAndroid_url());
     	mv.addObject("ios_url", qRCode.getIos_url());
     	//视图名
     	mv.setViewName("app");
     	
     	return mv;
	}
}
