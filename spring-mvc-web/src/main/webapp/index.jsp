<%@ page pageEncoding="utf-8"%>  
<!DOCTYPE html>  
<html>  
<head>  
<meta charset="utf-8">  
<title>上传图片</title>  
</head>  
<body>  
	<!-- <form action="/spring-mvc-web/upload" method="POST" enctype="multipart/form-data">  
		<input type="file" name="file" /> <input type="submit" value="Submit" />
	</form>  --> 
	
	
	<form name="serForm" action="/spring-mvc-web/fileUpload" method="post"  enctype="multipart/form-data">
		<h1>采用流的方式上传文件</h1>
		<input type="file" name="file">
		<input type="submit" value="upload"/>
	</form>
</body>  
</html>  