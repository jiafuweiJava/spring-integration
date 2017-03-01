package com.jiafuwei.spring.controller;

import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;  
  
import javax.servlet.http.HttpServletRequest;  
  
import org.springframework.stereotype.Controller;  
import org.springframework.ui.ModelMap;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.multipart.MultipartFile;  
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
  
@Controller  
public class UploadController {  
  
 
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
  
} 
