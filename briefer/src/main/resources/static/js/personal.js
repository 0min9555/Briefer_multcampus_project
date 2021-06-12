 
 $(function(){
	$('#btn_shortcut').on('click',function(){
		
		//alert($('#select_shortcut').val());
		
		selected = $('#select_shortcut').val()
		
		
		selected_string =selected.join(",")
				
		$.ajax({
			
			url:"update_shortcut",
			method:"post",
			data:{"selected":selected_string},
			success:function(result,textStatus){
				alert(result);
			},
			error:function(result,textStatus){
				alert("에러가 발생했습니다.");
			},
			complete:function(result,textStatus){
				
			}
			
		});
	});
	
	
	$('#btn_breifing').on('click',function(){
		

		
		selected = $('#select_breifing').val()
		
		selected_string =selected.join(",")
				
		$.ajax({
			
			url:"update_briefing",
			method:"post",
			data:{"selected":selected_string},
			success:function(result,textStatus){
				alert(result);
			},
			error:function(result,textStatus){
				alert("에러가 발생했습니다.");
			},
			complete:function(result,textStatus){
				
			}
			
		});
	});
	
});