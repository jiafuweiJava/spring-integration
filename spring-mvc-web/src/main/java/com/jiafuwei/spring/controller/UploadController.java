package com.jiafuwei.spring.controller;

import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;  
import java.util.HashMap;
import java.util.Map;
  
import javax.servlet.http.HttpServletRequest;  
  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.ModelMap;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;  
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jiafuwei.spring.po.JsonResult;
import com.jiafuwei.spring.util.ZXingCodeUtil;
  
@Controller  
public class UploadController {  
	final Logger logger = LoggerFactory.getLogger(getClass());
 
    /*
     * 通过流的方式上传文件
     * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
     */
    @RequestMapping("fileUpload")
    public String  fileUpload(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request,
    		ModelMap model) throws IOException {
         
         
        //用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        String path = request.getSession().getServletContext().getRealPath("upload"); 
        String fileName = file.getOriginalFilename();
        System.out.println("fileName："+fileName);
        
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        } 
        file.transferTo(targetFile);
        
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);  
        
       
        long  endTime=System.currentTimeMillis();
        System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "/result"; 
    }
  
    
    @RequestMapping("fileUploadLogo")
    @ResponseBody
    public String  fileUploadLogo(@RequestParam("file") CommonsMultipartFile file,HttpServletRequest request,
    		ModelMap model) throws IOException {
         
         
        //用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        String path = request.getSession().getServletContext().getRealPath("upload"); 
        String fileName = file.getOriginalFilename();
        System.out.println("fileName："+fileName);
        
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        } 
        file.transferTo(targetFile);
        
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);  
        
       
        long  endTime=System.currentTimeMillis();
        System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "result"; 
    }
    
    @RequestMapping("synthesisQRCode")
    @ResponseBody
    public JsonResult  SynthesisQRCode(HttpServletRequest request,
    		ModelMap model) throws IOException {
    	
    	 logger.info("SynthesisQRCode - {}", "开始了");
    	 logger.info("path - {}", request.getSession().getServletContext().getRealPath("/"));
    	 
         StringBuffer url = request.getRequestURL();  
         String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).append("/").toString(); 
         
         
         String path = request.getSession().getServletContext().getRealPath("upload");
         String fileName = new Date().getTime() + "qrcode.png";
         File file = new File(path+"/"+fileName);
         //生成二维码
         ZXingCodeUtil.getQRCode("http://www.baidu.com", request, "", file);
         
         //二维码地址
         String qrcode_path = tempContextUrl+"upload/"+fileName;
         
         JsonResult jsonResult = new JsonResult();
         Map data = new HashMap<String, String>();
         data.put("recreateFlag", "1");
         data.put("qrcode_path", qrcode_path);
         data.put("accessKey", "24108m");
         data.put("shortUrl", "http://t.zmapp.com.cn/24108m");
         jsonResult.setData(data);
         jsonResult.setMeg("生成成功");
         jsonResult.setRes(0);
     
        return jsonResult; 
    }
} 
