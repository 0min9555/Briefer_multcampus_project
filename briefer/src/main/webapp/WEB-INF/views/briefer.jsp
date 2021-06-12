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
	<script src="<c:url value='js/chatbot.js'/>"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
	<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
	<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/chatbot.css" >
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
                     <ul class="chat" id="chatBox">  
                     </ul>
                </div>
                <div class="panel-footer">
                
                	<div id="shortcut"> 
						<button id="keyword1" class="btn btn-success" >#${shortcut1}</button><input type='hidden' id="kw_val1" value="${shortcut1}">
						<button id="keyword2" class="btn btn-success" >#${shortcut2}</button><input type='hidden' id="kw_val2" value="${shortcut2}">
						<button id="keyword3" class="btn btn-success" >#${shortcut3}</button><input type='hidden' id="kw_val3" value="${shortcut3}">
						<button id="keyword4" class="btn btn-success" >#${shortcut4}</button><input type='hidden' id="kw_val4" value="${shortcut4}">
						
						<span class="sp">
							<input type="checkbox" data-toggle="toggle" data-size="mini" data-onstyle="danger" id="speaker">
						</span>
						
					</div>
					<br>
						  <form id="chatForm" method="post">
                    	<div class="input-group">
	                        <input id="message" type="text" class="form-control input-sm" placeholder="질문을 입력하세요..." />
                
	                        
	                        <span class="input-group-btn">
	                              <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-mic-mute-fill" viewBox="0 0 16 16" id="micOff">
								  <path d="M13 8c0 .564-.094 1.107-.266 1.613l-.814-.814A4.02 4.02 0 0 0 12 8V7a.5.5 0 0 1 1 0v1zm-5 4c.818 0 1.578-.245 2.212-.667l.718.719a4.973 4.973 0 0 1-2.43.923V15h3a.5.5 0 0 1 0 1h-7a.5.5 0 0 1 0-1h3v-2.025A5 5 0 0 1 3 8V7a.5.5 0 0 1 1 0v1a4 4 0 0 0 4 4zm3-9v4.879L5.158 2.037A3.001 3.001 0 0 1 11 3z"/>
								  <path d="M9.486 10.607 5 6.12V8a3 3 0 0 0 4.486 2.607zm-7.84-9.253 12 12 .708-.708-12-12-.708.708z"/>
								</svg>
													
<!-- 								<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-mic-fill" viewBox="0 0 16 16" id="micOn" display="none"> -->
<!-- 								  <path d="M5 3a3 3 0 0 1 6 0v5a3 3 0 0 1-6 0V3z"/> -->
<!-- 								  <path d="M3.5 6.5A.5.5 0 0 1 4 7v1a4 4 0 0 0 8 0V7a.5.5 0 0 1 1 0v1a5 5 0 0 1-4.5 4.975V15h3a.5.5 0 0 1 0 1h-7a.5.5 0 0 1 0-1h3v-2.025A5 5 0 0 1 3 8V7a.5.5 0 0 1 .5-.5z"/> -->
<!-- 								</svg> -->
								<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-mic-fill" viewBox="0 0 16 16" id="micOn" display="none">
									  <path d="M5 3a3 3 0 0 1 6 0v5a3 3 0 0 1-6 0V3z"/>
									  <path d="M3.5 6.5A.5.5 0 0 1 4 7v1a4 4 0 0 0 8 0V7a.5.5 0 0 1 1 0v1a5 5 0 0 1-4.5 4.975V15h3a.5.5 0 0 1 0 1h-7a.5.5 0 0 1 0-1h3v-2.025A5 5 0 0 1 3 8V7a.5.5 0 0 1 .5-.5z"/>
									</svg>

								
                        	</span> 
                     
                    	</div>
                    </form>
           
						<div>
							<audio preload="auto" controls></audio>
						</div>
				
                </div>
            </div>
        </div>
  
    </div>
</div>
   </body>
</html>