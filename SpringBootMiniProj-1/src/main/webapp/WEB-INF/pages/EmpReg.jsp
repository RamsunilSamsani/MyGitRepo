<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" ></script>
<script type="text/javascript">
$(document).ready(function(){
	//alert("hello")
	$("#email").change(function(){
		$.ajax({
			url : 'checkEmail',
			data: {'email':$("#email").val()},
			success:function(resTxt) {
				$("#emailError").text(resTxt);
			}
		});
	});
});
</script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<H3>WELCOME TO EMPLOYEE REGISTER PAGE!!</H3>
<form action="save" method="POST">
<pre>
NAME   : <input type="text" name="ename"/>
EMAIL  : <input type="text" name="email" id="email"/><span id="emailError"></span>
SALARY : <input type="text" name="esal"/>
DEPT   : <select name="dept">
			<option value="">-SELECT-</option>
			<option value="DEV">DEV</option>
			<option value="QA">QA</option>
			<option value="BA">BA</option>
		</select>
		
   <input type="submit" value="Register Employee"/><br>
   
   <a href="/SpringBootMiniProj-1/"><input type="button" value="Home"/></a>
</pre>
</form>
${msg}
</body>
</html>