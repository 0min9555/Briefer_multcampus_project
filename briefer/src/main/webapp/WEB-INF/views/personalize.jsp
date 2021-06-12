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
	<script src="<c:url value='js/personal.js'/>"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/awesome-bootstrap-checkbox/1.0.2/awesome-bootstrap-checkbox.min.css" integrity="sha512-zAQ4eQ+PGbYf6BknDx0m2NhTcOnHWpMdJCFaPvcv5BpMTR5edqr/ZRuluUcNave6IEpRCoT67/3hVVmm5ODpqA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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
					    <h1 class="h3 mb-3 fw-normal">개인화</h1>
					    <h3 class="h3 mb-3 fw-normal">바로가기 설정</h3>
					    <hr>
<!-- 					    <div class="container"> -->
<!-- 					    	<form name="shortcutForm" method="post" action="update_shortcut" > -->
						    	<div class="row">
									<div class="col">
										<select class="selectpicker"  id="select_shortcut" multiple data-max-options="4"   data-width="75%" title="4개까지 선택이 가능합니다.">
										  ${combo_shortcut}
										</select>
									</div>
					         	</div>
								<br>
				         		<input type="button" class="btn btn-success" id="btn_shortcut" value="적용">
<!-- 							</form> -->
<!-- 						</div> -->
						<br>
						<h3 class="h3 mb-3 fw-normal">브리핑 설정</h3>
					    <hr>
<!-- 					   <div class="container"> -->
<!-- 					    	<form name="shortcutForm" method="post" action="update_briefing" > -->
						    	<div class="row">
									<div class="col">
										<select class="selectpicker" id="select_breifing" multiple data-max-options="5"  data-width="75%" title="5개까지 선택이 가능합니다.">
										   ${combo_briefing}
										</select>
									</div>
					         	</div>
								<br>
				         		<input type="button" class="btn btn-success" id="btn_breifing" value="적용">
<!-- 							</form> -->
<!-- 						</div> -->
											    
                </div>
                
            	</div>
        	</div>
  
    	</div>
	</div>
   </body>
</html>