package com.jiafuwei.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jiafuwei.spring.po.QRCode;
import com.jiafuwei.spring.po.User;
import com.jiafuwei.spring.service.IQRCodeService;
import com.jiafuwei.spring.service.IUserService;
import com.jiafuwei.spring.util.UuidUtil;

@Controller
public class TestHttpPostController{
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	@RequestMapping("/testpost")
    public void handleRequest(
    		@RequestParam("image") CommonsMultipartFile image,
    		@RequestParam(value="secretId",required=false) String secretId,
    		@RequestParam(value="timestamp",required=false) String timestamp,
    		@RequestParam(value="signature",required=false) String signature,
    		@RequestParam(value="nonce",required=false) String nonce,
    		HttpServletRequest request, HttpServletResponse resp) throws Exception {
		
		if(image!=null){
			String fileName = image.getOriginalFilename();
			System.out.println("fileName："+fileName);
		}
		
		System.out.println(secretId);
		System.out.println(timestamp);
		System.out.println(signature);
		System.out.println(nonce);
		
		
        resp.getWriter().println("test post");
         
        logger.info("这是一条日志信息 - {}", "jiafuwei");
	}
}
