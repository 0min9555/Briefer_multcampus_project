package com.threecircuit.briefer.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.threecircuit.briefer.model.ResponseVO;

@Service
public class ContentCreateService {
	
	public ResponseVO getLottoNum(boolean isBrief){ 
		Map<String, String> lottoMap = new HashMap<String, String> (); 
		Random rd = new Random();
		
		String result_text="";
		String result_voice="";
		String lotto_text="";
		String lotto_voice="";
		
		while(true) { 
			int lottoNo = rd.nextInt(45)+1; 
			lottoMap.put(String.valueOf(lottoNo), ""); 
			if(lottoMap.size() >= 6) break; 
		}
		
		for(String lottoNo : lottoMap.keySet()) 
		{ 

			lotto_text = lotto_text + lottoNo + " ";
			lotto_voice = lotto_voice + lottoNo + ", ";
		}
		
		
		ResponseVO responseVO = new ResponseVO();
		
		if (isBrief!=true) {
			result_text = "로또 추천 번호: "+lotto_text;
			responseVO.setText(Common.getResponseText(result_text));
			result_voice = "로또 추천 번호는 "+ lotto_voice;
		
		} else {
			result_text = "로또 추천 번호: "+lotto_text;
			result_voice = "로또 추천 번호는 "+ lotto_voice;
			responseVO.setText(result_text);
		}
		
		responseVO.setVoice(result_voice);

		return responseVO;
	}
	
	public ResponseVO getFoodMenu(boolean isBrief){ 
		
		Map<String, String> FoodMenuMap = new HashMap<String, String> (); 
		Random rd = new Random();
		
		String result_text="";
		String result_voice="";
		
		String menu_text="";
		String menu_voice="";
		
		
		String menu_cats="한식,양식,일식,중식,분식,패스트푸드";
		String menu_korea="불고기,비빔밥,냉면,육회,설렁탕,갈비탕,제육볶음,갈치조림,육개장,순두부찌개,김치찌개,해물된장찌개,보리굴비,갈비,떡갈비,간장게장,생선구이,쌈밥,돌솥밥,청국장,돈까스";
		String menu_west="스파게티,파스타,알리오올리오,봉골레,로제,리조또,스테이크,오므라이스,함박스테이크";
		String menu_japan="회덮밥,동태탕,알탕,차슈라멘,스시롤,복매운탕,우럭매운탕,모밀국수,튀김우동,유부우동,회정식,초밥,장어덮밥,물회,초밥정식,로스카츠,히레카츠,돈카츠,에비동,규동,사케동";
		String menu_china="짜장면,짬뽕,고추잡채/꽃빵,마라탕,유린기,탕수육,팔보채,굴짬뽕,볶음밥,깐풍새우,군만두,물만두,짜장밥,짬뽕밥,교자,샥스핀,해파리냉채,기스면,유산슬,잡채밥,콩국수,어향동고,양장피,라조육,전가복,동파육,새우요리";
		String menu_school="김밥,참치김밥,매운김밥,라면,우동,불고기덮밥,군만두,볶음밥,갈비탕,비빔국수,잔치국수,콩국수,백반,콩나물국,육개장,오므라이스,오징어덮밥,불고기덮밥,떡라면,치즈라면,쫄면,떡볶이,라볶이,순대,모듬튀김,돈까스,왕돈까스,치즈돈까스,고구마돈까스,김치볶음밥,비빔면,밀면,토스트,어묵";
		String menu_fast="햄버거,피자,치킨";
		//String menu_night="족발/보쌈,치킨,햄버거,피자,회,고기,닭발,알찜,중식";
				
		String menu_array[] = null;
		String menu_cat_array[] = null;
		
		while(true) { 
			
			menu_cat_array = menu_cats.split(",");
			
			int menu_cat_no = rd.nextInt(menu_cat_array.length);
			
			
			switch(menu_cat_array[menu_cat_no]) {
			
				case "한식": 
					menu_array = menu_korea.split(",");
					break;
				case "양식": 
					menu_array = menu_west.split(",");
					break;
				case "일식": 
					menu_array = menu_japan.split(",");
					break;
				case "중식": 
					menu_array = menu_china.split(",");
					break;
				case "분식": 
					menu_array = menu_school.split(",");
					break;
				case "패스트푸드": 
					menu_array = menu_fast.split(",");
					break;
			}			
		
			int menu_no = rd.nextInt(menu_array.length);
			
			FoodMenuMap.put(menu_array[menu_no], ""); 
			if(FoodMenuMap.size() >= 3) break; 
		}
		
		for(String selected : FoodMenuMap.keySet()) 
		{ 

			menu_text = menu_text + selected + ", ";
			menu_voice = menu_voice + selected + ", ";
		}
		
		
		menu_text = menu_text.substring(0,menu_text.length()-2);
		menu_voice = menu_voice.substring(0,menu_voice.length()-2);
		
		
		ResponseVO responseVO = new ResponseVO();
		
		if (isBrief!=true) {
			result_text = "추천 음식: "+ menu_text;
			responseVO.setText(Common.getResponseText(result_text));
			result_voice = "추천 음식은 "+ menu_voice;
		
		} else {
			result_text = "추천 음식: "+menu_text;
			result_voice = "추천 음식은 "+ menu_voice;
			responseVO.setText(result_text);
		}
		
		responseVO.setVoice(result_voice);

		return responseVO;
	}
}
