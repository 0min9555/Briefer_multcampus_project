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
	<script src="<c:url value='/js/idCheck.js'/>"></script>	
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
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </button>
                        
                        
                        <%@ include file="/WEB-INF/views/menu.jsp" %>
                    </div>
                    <br>
                </div>
                <div class="panel-body" id="chatBody">
                	  <form name="joinForm" method="post" action="register" >
					    <h1 class="h3 mb-3 fw-normal">회원 가입</h1>
					
					    <div class="form-floating">
					      <input type="text" class="form-control" id="id" name="id">
					      <label>이메일(아이디)</label>
					    </div>
					    <div class="mb-3">
		              		<input type="button" class="btn btn-primary" id="idCheck" value="ID 중복 체크"> 
	             		</div>
					    <div class="form-floating">
					      <input type="password" class="form-control" id="pwd" name="pwd">
					      <label>비밀번호</label>
					    </div>
					     <div class="form-floating">
					      <input type="text" class="form-control" id="name" name="name">
					      <label>이름</label>
					    </div>
					    <div class="checkbox mb-3">
					      <label>
					        <input type="checkbox" value="true" checked> 이용 약관 및 개인정보 취급 방침에 등의 합니다.
					      </label>
					    </div>
					    <div class="mb-3">
				           	<input type="submit" class="btn btn-primary" value="가입">
				           	<input type="reset" class="btn btn-primary" value="취소">
				         </div>  
	  				</form>
                </div>
                
            	</div>
        	</div>
  
    	</div>
	</div>
   </body>
</html>