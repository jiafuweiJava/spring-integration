package com.jiafuwei.spring.controller;

import java.io.BufferedInputStream;
import java.io.File;  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;  
import java.util.HashMap;
import java.util.Map;
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
  
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
    
    /**
     * APP 合成二维码生成
     * @param request
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("synthesisQRCode")
    @ResponseBody
    public JsonResult  SynthesisQRCode(HttpServletRequest request,
    		@RequestParam(value="file",required=false) CommonsMultipartFile logoFile,
    		@RequestParam(value="ios_url",required=false) String ios_url,
    		@RequestParam(value="ios_url",required=false) String android_url,
    		ModelMap model) throws IOException {
    	
    	 logger.info("SynthesisQRCode - {}", "开始了");
    	 logger.info("path - {}", request.getSession().getServletContext().getRealPath("/"));
    	 
         StringBuffer url = request.getRequestURL();  
         String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).append("/").toString(); 
         
         
         String path = request.getSession().getServletContext().getRealPath("upload");
         String fileName = new Date().getTime() + "qrcode.png";
         File createFile = new File(path+"/"+fileName);
         
         String urltxt = "";
         //生成二维码
         String imageBase64QRCode = "";
         if(logoFile != null){
             String logoFileName = logoFile.getOriginalFilename();
             File targetFile = new File(path, logoFileName);  
             if(!targetFile.exists()){  
                 targetFile.mkdirs();  
             } 
             logoFile.transferTo(targetFile);
        	 imageBase64QRCode = ZXingCodeUtil.getLogoQRCode(urltxt, request, "", targetFile, createFile);
        	 //删除上传的logo
        	 targetFile.delete();
         }else{
        	 imageBase64QRCode = ZXingCodeUtil.getQRCode(urltxt, request, "", createFile);
         }
         
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
         jsonResult.setRes(1);
     
        return jsonResult; 
    }
    
    /**
     * url链接或者文本生成二维码
     * @param request
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("urlQRCode")
    @ResponseBody
    public JsonResult  UrlQRCode(HttpServletRequest request,ModelMap model,
    		@RequestParam(value="file",required=false) CommonsMultipartFile logoFile,
    		@RequestParam(value="urltxt",required=false) String urltxt) throws IOException {
    	
    	 logger.info("UrlQRCode - {}", "开始了");
    	 logger.info("页面传递的文本内容- {}", urltxt);
    	 
         StringBuffer url = request.getRequestURL();  
         String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).append("/").toString(); 
         
         
         String path = request.getSession().getServletContext().getRealPath("upload");
         String fileName = new Date().getTime() + "url.png";
         File createFile = new File(path+"/"+fileName);
         //生成二维码
         String imageBase64QRCode = "";
         if(logoFile != null){
             String logoFileName = logoFile.getOriginalFilename();
             File targetFile = new File(path, logoFileName);  
             if(!targetFile.exists()){  
                 targetFile.mkdirs();  
             } 
             logoFile.transferTo(targetFile);
        	 imageBase64QRCode = ZXingCodeUtil.getLogoQRCode(urltxt, request, "", targetFile, createFile);
        	 //删除上传的logo
        	 targetFile.delete();
         }else{
        	 imageBase64QRCode = ZXingCodeUtil.getQRCode(urltxt, request, "", createFile);
         }
         
         //二维码地址
         String qrcode_path = tempContextUrl+"upload/"+fileName;
         
         JsonResult jsonResult = new JsonResult();
         Map data = new HashMap<String, String>();
         data.put("qrcode_path", qrcode_path);
         data.put("qrcode", imageBase64QRCode);
         jsonResult.setData(data);
         jsonResult.setMeg("生成成功");
         jsonResult.setRes(1);
     
        return jsonResult; 
    }
    
    @RequestMapping("/download")  
    public void downloadFile(@RequestParam(value="qrcode_path",required=true) String qrcode_path,
    		HttpServletRequest request, HttpServletResponse response) throws IOException {    
        
        String destUrl = qrcode_path;
        // 建立链接    
        URL url = new URL(destUrl);    
        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();    
        // 连接指定的资源    
        httpUrl.connect();    
        // 获取网络输入流    
        BufferedInputStream bis = new BufferedInputStream(httpUrl.getInputStream());    
      
        response.setContentType("application/x-msdownload");    
        response.setHeader("Content-Disposition", "attachment; filename="+java.net.URLEncoder.encode(new Date().getTime()+"url.png","UTF-8"));    
        OutputStream out = response.getOutputStream();    
        byte[] buf = new byte[1024];    
        if (destUrl != null) {    
            BufferedInputStream br = bis;    
            int len = 0;    
            while ((len = br.read(buf)) > 0){    
                out.write(buf, 0, len);    
            }                   
            br.close();    
        }    
        out.flush();    
        out.close();    
      
    } 
} 
