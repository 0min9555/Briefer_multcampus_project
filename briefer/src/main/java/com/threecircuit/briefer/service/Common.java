package com.threecircuit.briefer.service;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.threecircuit.briefer.model.ResponseVO;

public class Common {
	
	
public static String getBriefingText(String text) {
		
		String result="";
		
		if(text.length()>4) {
			if (text.substring(text.length()-4,text.length()).equals("<br>")) {
				
				result = text.substring(0,text.length()-4);
			}
		}
		
		return result;
}
	
	public static String getResponseText(String text) {
		
		String result="";
		
		if(text.length()>4) {
			if (text.substring(text.length()-4,text.length()).equals("<br>")) {
				
				text = text.substring(0,text.length()-4);
			}
		}
		
		
//		String result = "<div class='msgBox receive'><br>챗봇<br><span>" + text + "</span></div><br><br>";
		
		
		
		
		
		
		result = "<li class='left clearfix'><span class='chat-img pull-left'><img src='http://placehold.it/50/55C1E7/fff&text=BF' alt='User Avatar' class='img-circle'/></span>";
		result = result +"<div class='recBox'><p>" + text + "</p></div></li>";
		
		return result;
	}
	
	public static String getLinkText(String text, String link) {
		
		
		String result = "<a href='" + link + "' target='_blank'>"+text + "</a><br>";
		
		
		return result;
	}
	
	public static String getVoiceText(String text) {
		
		String result ="";
		
		result = text.replace("<br>", ",");
		
		return result;
		
	}
	
	public static String getImage(String text, String url,int width,int height) {
		
		String result="";
		
		result=text+"<img src='" +  url +  "' width='"+width+"' height='"+height+"' alt=' 이미지 없음' ><br>";	
		
		return result;
		
		
	}
	
	
//	public static getResponseArray(ArrayList<ResponseVO> arrayList) {
//			
//			String requestBody = "";
//				
//		 try {
//				
//		        JSONObject obj = new JSONObject();
//		        
//		
//		        long timestamp = new Date().getTime();
//		
//		        System.out.println("##"+timestamp);
//		        
//		        obj.put("service", "briefer");
//		        obj.put("timestamp", timestamp);
//		        
//		        for (ResponseVO responseVO:arrayList) {
//			        JSONObject response_obj = makeResponseJson(responseVO);
//			        
//			        JSONArray response_array = new JSONArray();
//			        response_array.put(response_obj);
//			          
//			        obj.put("response", response_array);
//		        }
//		        requestBody = obj.toString();
//		
//		    } catch (Exception e){
//		        System.out.println("## Exception : " + e);
//		    }
//		
//		    return requestBody;
//				
//				
//		}
		
	public static String getResponse(ResponseVO responseVO) {
			
		    String requestBody = "";
		    
		    
		    try {
		
		        JSONObject obj = new JSONObject();
		        
		
		        long timestamp = new Date().getTime();
		
		        System.out.println("##"+timestamp);
		        
		        obj.put("service", "briefer");
		        obj.put("timestamp", timestamp);
		        
	
		        JSONObject response_obj = makeResponseJson(responseVO);
		        
		        JSONArray response_array = new JSONArray();
		        response_array.put(response_obj);
		          
		        obj.put("response", response_array);
		        
		        requestBody = obj.toString();
		
		    } catch (Exception e){
		        System.out.println("## Exception : " + e);
		    }
		
		    return requestBody;
		
		}
		
		
	public static JSONObject makeResponseJson(ResponseVO responseVO) {
			
			JSONObject response_obj = new JSONObject();
			
			String result_text="";
		    String result_voice="";
	
	        
	    	result_text = responseVO.getText();
	    	result_voice = responseVO.getVoice();
	        
	        response_obj.put("text",result_text);
	        response_obj.put("voice",result_voice);
			
			return response_obj;
		}
}
