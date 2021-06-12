 $(function(){
	
	
	callChatbot(""); // 웰컴메세지 호출
	
	$('audio').hide();

	const mic_off = document.getElementById("micOff");
	const mic_on = document.getElementById("micOn");
	
	const speaker = document.getElementById("speaker");
	const chatbody = document.getElementById("chatBody");
	
    //    var mic_on = false;
    
    //const audioCtx = new(window.AudioContext || window.webkitAudioContext)(); // 오디오 컨텍스트 정의

    if (navigator.mediaDevices) {
        var constraints = {
            audio: true
        }
        
        let chunks = [];

        navigator.mediaDevices.getUserMedia(constraints)
            .then(stream => {
                const  mediaRecorder = new MediaRecorder(stream);
                
                mic_off.onclick = () => {
                
                	//if(!mic_on) {
                	
                    mediaRecorder.start();
                    
                    //mic_on.style.background = "red";
                    mic_on.style.display = "block";
                   
                    mic_off.style.display = "none";
                    
                    
                    
                }
               mic_on.onclick = () => {
                    
                    mediaRecorder.stop();//녹음 정지                       
                    //mic_off.style.background = "";
                    //mic_on.style.color = "";
		                 //mic.innerHTML="Off";
		                 //mic_on=false
		                 
		            mic_on.style.display = "none";
		            mic_off.style.display = "block";
	               
                    
                    
                }

                mediaRecorder.onstop = e => {
                  
                   	// audio.controls = true;
                    const blob = new Blob(chunks, {
                        'type': 'audio/mp3 codecs=opus'
                    }); 
                    
                    chunks = [];
                    callSTT(blob); //파일 데이터와 파일명 전달
					
									
                }

                	mediaRecorder.ondataavailable = e => {
                    chunks.push(e.data)
                }
                
            })
            .catch(err => {
                console.log('The following error occurred: ' + err)
            })
    }  else {
    	alert("마이크를 사용할수 없습니다.");
    }
	/* 서버에 업로드 */
	function callSTT(blob){
		// 파일 업로드 부분 추가
		var formData = new FormData();
		formData.append('voiceFile', blob, "voiceMsg.mp3");
		
		$.ajax({
			type:"post",
			url:"brieferSTT",
			data: formData, // 폼 데이터 전송
			processData:false, //필수
			contentType:false, //필수
			success:function(result){
				
				chat_send(result);
				$('#message').val(result);	
				callChatbot(result);		
				$('#message').val('');
				chatbody.scrollTop = chatbody.scrollHeight;
			},
			error:function(e){
				alert("에러 발생 : " + e);
			}			
		});
	}
	
	$('#chatForm').on('submit', function(event){
		event.preventDefault(); //submit 후에  reload 안 되게
		
		if($('#message').val() != ""){
			chat_send($('#message').val());	
		}
		
		chatbody.scrollTop = chatbody.scrollHeight;
		
		callChatbot($('#message').val());
		
	}); 
	
	$('#keyword1').on('click', function(event){
	
		keyword = document.getElementById("kw_val1");
		
		if (keyword.value !="비었음"){
		
			chat_send(keyword.value);
			chatbody.scrollTop = chatbody.scrollHeight;	
			callChatbot(keyword.value);
		}
	});
	
	$('#keyword2').on('click', function(event){
		
		keyword = document.getElementById("kw_val2");
		
		
		if (keyword.value !="비었음"){
			chat_send(keyword.value);
			chatbody.scrollTop = chatbody.scrollHeight;	
			callChatbot(keyword.value);
		}
	});
	$('#keyword3').on('click', function(event){
		
		keyword = document.getElementById("kw_val3");
		if (keyword.value  !="비었음"){
			chat_send(keyword.value);	
			chatbody.scrollTop = chatbody.scrollHeight;
			callChatbot(keyword.value);
		}
	});
	$('#keyword4').on('click', function(event){
		
		keyword = document.getElementById("kw_val4");	
		if (keyword.value  !="비었음"){									
		    chat_send(keyword.value);							
			chatbody.scrollTop = chatbody.scrollHeight;	
			callChatbot(keyword.value);
		}
	}); 
	
	
	
	function chat_send(text) {
	
		result = '<li class="right clearfix"><span class="chat-img pull-right"><img src="http://placehold.it/50/FA6F57/fff&text=ME" alt="User Avatar" class="img-circle" /></span>';
		result = result + '<div class="sendBox"><p>'+text+'</p></div></li>';
	
		$('#chatBox').append(result);
		
	
	}
	
	
	
	function callChatbot(text){
		$.ajax({
			type:"post",
			url:"brieferCall",
			data:{message:text},
			dataType:'json',
			success:function(result){	
				
				var response = result.response;
				
				for(var b in response){
		
					result_text = response[b].text;
					
					$('#chatBox').append(result_text);
					
					if (speaker.checked) {
						callTTS(response[b].voice);
					}
				}
					
				$('#message').val('');
				
				chatbody.scrollTop = chatbody.scrollHeight;
		
			},
			error:function(e){
				alert("에러 발생 : " + e);
			}			
		});
	}
	
	
	
	function callTTS(result){
		$.ajax({
			type:"post",
			url:"brieferTTS",
			data:{message:result},
			success:function(result){				
				$('audio').prop("src", '/ai/' + result)[0].play();
			},
			error:function(e){
				alert("에러 발생 : " + e);
			}			
		});
		
	}
	
}); // $(function()  끝


