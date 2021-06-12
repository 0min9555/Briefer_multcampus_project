<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE HTML>
<html>
   <head>
<!-- Scripts jQuery, bootstrap -->
	<script src="<c:url value='js/jquery-3.6.0.min.js'/>"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/chatbot.css" >
	<title>나만의 개인비서 Briefer</title>
   </head>
   <body>
   <br>
       
    <div class="container">
    	<div class="row">

        <div class="col-md-6 col-md-offset-3">
           <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-comment"></span> Briefer
                    
                    <br>
                </div>
                <div class="panel-body" id="chatBody">
                	  <form name="loginForm" method="post" action="login" >
					    <h1 class="h3 mb-3 fw-normal">회원 로그인</h1>
					
					    <div class="form-floating">
					      <input type="text" class="form-control" id="id" name="id">
					      <label>아이디</label>
					    </div>
					    <div class="form-floating">
					      <input type="password" class="form-control" id="pwd" name="pwd">
					      <label>비밀번호</label>
					    </div>
					
					    <div class="checkbox mb-3">
					      <label>
					        <input type="checkbox" value="true" checked> Remember me
					      </label>
					    </div>
					     <div class="mb-3">
						    <button class="btn btn-primary" type="submit">로그인</button>  
						    <a class="btn btn-primary" href="join" role="button">회원 가입</a>
						    
	  					</div>
	  				</form>
                </div>
                
            	</div>
        	</div>
  
    	</div>
	</div>
   </body>
</html>