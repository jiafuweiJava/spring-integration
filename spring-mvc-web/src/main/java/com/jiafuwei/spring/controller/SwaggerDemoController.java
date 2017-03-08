package com.jiafuwei.spring.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "SwaggerDemoController",description = "Swagger整合Demo")
@Controller
@RequestMapping("/swagger")
public class SwaggerDemoController {
	final Logger logger = LoggerFactory.getLogger(getClass());

   
    
    
	 @RequestMapping(value="demo",method=RequestMethod.GET)
	 //方法上使用@ApiOperation
	 @ApiOperation(value="demo测试",notes="demo测试")
	 //参数使用@ApiParam
	 public Object getIndex(@ApiParam(name="ios地址",value="字符串",required=true)
	 						@RequestParam(value="ios_url",required=true) String ios_url){
		 
	    
	     
	     return ios_url;
	 }
    
    
}
