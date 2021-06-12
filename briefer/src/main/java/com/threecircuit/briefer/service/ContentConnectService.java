package com.threecircuit.briefer.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.threecircuit.briefer.model.ResponseVO;

@Service
public class ContentConnectService {
	public ResponseVO getWeather(boolean isBrief) {
		
		String result_text="";
		String result_voice="";
		
		String url = "https://weather.naver.com/today";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("div.weather_area");
		
		String current = element.select(".current").text();
		String today_temp = current.substring(5, 8);
		String today_weather = element.select("span.weather.before_slash").text();

		ResponseVO responseVO = new ResponseVO();
		
		if (isBrief!=true) {
			result_text = "오늘 날씨 " + today_weather +", 온도 "+today_temp;
			result_voice = "오늘 날씨 " + today_weather +", 온도 "+today_temp;
			
			responseVO.setText(Common.getResponseText(Common.getLinkText(result_text, url)));

		}
		else {
			result_text = "날씨 " + today_weather +", 온도 "+today_temp;
			result_voice = "날씨 " + today_weather +", 온도 "+today_temp;
			
			responseVO.setText(result_text);
			
		}
			
		responseVO.setVoice(result_voice);

		
		return responseVO;
		
		
	}
	
	public ResponseVO getCovid(boolean isBrief) {
		
		String result_text="";
		String result_voice="";
		
		
		String url = "https://m.news.naver.com/covid19/index.nhn";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("div.confirmed_increase");
		
		String current = element.select("strong").text();

		
//		System.out.println(current);
		
		String[] str = current.split(" ");
		String today_covid = str[0];
		
		
		ResponseVO responseVO = new ResponseVO();
		
		if (isBrief!=true) {

			result_text = "신규 확진자수: "+ today_covid+"명";
			result_voice = "오늘 신규 확진자수: "+ today_covid+"명";
			
			responseVO.setText(Common.getResponseText(Common.getLinkText(result_text,url)));
		
		} else {
			
			result_text = "신규 확진자수: "+ today_covid+"명";
			result_voice = " 신규 확진자수는 "+ today_covid+"명";
			
			responseVO.setText(result_text);
		}
				
		responseVO.setVoice(result_voice);
		
		return responseVO;
		
		
	}
	public ResponseVO getBaseball(boolean isBrief) {
		
		String result_text="";
		String result_voice="";
		
		String team1_name = "";
		String team2_name = "";
		String team1_score = "";
		String team2_score = "";
		
		String order_voice="";
		
		String url = "https://www.msn.com/ko-kr/sports/baseball/kbo-baseball/scores";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("table.baseballscorestable");
		Element el_first = element.first();
		
        String issue_date= el_first.select("th").get(0).text();

        Elements el_score = el_first.select("tbody");
        
        int i=0;
        
       
        
        for(Element el:el_score) {
        	
//        	System.out.println(el.text());
        	
        	team1_name = el.select("td").get(2).text();
        	team1_score = el.select("td").get(6).text();
        	team2_name = el.select("td").get(10).text();
        	team2_score = el.select("td").get(14).text();
        	
        	
        	switch (i) {
			case 0:
				order_voice="첫번쨰";
				break;
			case 1:
				order_voice="두번쨰";
				break;
			case 2:
				order_voice="세번쨰";
				break;
			case 3:
				order_voice="네번쨰";
				break;
			case 4:
				order_voice="다섯번쨰";
				break;
			case 5:
				order_voice="여섯번쨰";
				break;
			}
        	
        	result_text = result_text + (i+1) + ") " + team1_name + " " + team1_score + " : " + team2_name + " " +  team2_score + "<br>";
        	result_voice = result_voice + order_voice +", " + team1_name + " " + team1_score + " 대,  " + team2_name + " " +  team2_score + " ,  ";
        	
        	i++;
        }
        
		
		ResponseVO responseVO = new ResponseVO();
	
		result_text = "프로야구 경기 결과 - " + issue_date + "<br>" +result_text;
		result_voice ="프로야구 경기 결과 "+ result_voice;
		
		if (isBrief!=true) {
			responseVO.setText(Common.getResponseText(result_text));
			responseVO.setVoice(result_voice);
		} else {
			responseVO.setText(result_text);
			responseVO.setVoice(result_voice);
			
		}
		
		return responseVO;
		
		
	}
		
	

	public ResponseVO getTicket_KoreaNdancing() {
		
		String result_text="";
		String result_voice="";
		
		
		String url ="http://www.playdb.co.kr/ranking/Ticket/TPBoxOffice.asp?KindOfGoods=01005&Flag=D";
		
	
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("tr"); //뮤지컬
		
		
		for(Element el:element) {
			
			if (el.text().length()>2) {
				
				if (el.text().substring(0,2).equals("1위")||el.text().substring(0,2).equals("2위")||el.text().substring(0,2).equals("3위")||el.text().substring(0,2).equals("4위")||el.text().substring(0,2).equals("5위")) {

					String rank = el.text().substring(0,1);
					String title = el.select("b").text();
					
					Elements ela = el.select("a");
					
					String href = ela.get(0).attr("href");
					String link =  "http://www.playdb.co.kr/gate/PlayGoods_Gate.asp?GoodsCode=" + href.substring(16,24);
					
					result_text = result_text+rank+") " + Common.getLinkText(title, link);
					result_voice = result_voice+ rank+ "위 " + title + ",";

				}
			}
			
		}
		

		
		ResponseVO responseVO = new ResponseVO();
		
		result_text = "국악 예매 순위 <br>" + result_text;
		result_voice ="국악 예매 순위 "+ result_voice;
		
		responseVO.setText(Common.getResponseText(result_text));
		responseVO.setVoice(result_voice);
		
		return responseVO;
		
	}	
	
	public ResponseVO getTicket_Classic() {
		
		String result_text="";
		String result_voice="";
		
		
		String url ="http://www.playdb.co.kr/ranking/Ticket/TPBoxOffice.asp?KindOfGoods=01004&Flag=D";
		
	
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("tr"); //뮤지컬
		
		
		for(Element el:element) {
			
			if (el.text().length()>2) {
				
				if (el.text().substring(0,2).equals("1위")||el.text().substring(0,2).equals("2위")||el.text().substring(0,2).equals("3위")||el.text().substring(0,2).equals("4위")||el.text().substring(0,2).equals("5위")) {

					String rank = el.text().substring(0,1);
					String title = el.select("b").text();
					
					Elements ela = el.select("a");
					
					String href = ela.get(0).attr("href");
					String link =  "http://www.playdb.co.kr/gate/PlayGoods_Gate.asp?GoodsCode=" + href.substring(16,24);
					
					
					result_text = result_text+rank+") " + Common.getLinkText(title, link);
					result_voice = result_voice+ rank+ "위 " + title + ",";

				}
			}
			
		}
		

		
		ResponseVO responseVO = new ResponseVO();
		
		result_text = "클래식 예매 순위 <br>" + result_text;
		result_voice ="클래식 예매 순위 "+ result_voice;
		
		responseVO.setText(Common.getResponseText(result_text));
		responseVO.setVoice(result_voice);
		
		return responseVO;
		
	}	
		
	public ResponseVO getTicket_Concert() {
		
		String result_text="";
		String result_voice="";
		
		
		String url ="http://www.playdb.co.kr/ranking/Ticket/TPBoxOffice.asp?KindOfGoods=01003&Flag=D";
		
	
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
			System.out.println(doc.html());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("tr"); //뮤지컬
		
		
		
		for(Element el:element) {
			
			if (el.text().length()>2) {
				
				if (el.text().substring(0,2).equals("1위")||el.text().substring(0,2).equals("2위")||el.text().substring(0,2).equals("3위")||el.text().substring(0,2).equals("4위")||el.text().substring(0,2).equals("5위")) {

					String rank = el.text().substring(0,1);
					String title = el.select("b").text();
					
					Elements ela = el.select("a");
					
					String href = ela.get(0).attr("href");
					String link =  "http://www.playdb.co.kr/gate/PlayGoods_Gate.asp?GoodsCode=" + href.substring(16,24);
					
					result_text = result_text+rank+") " + Common.getLinkText(title, link);
					result_voice = result_voice+ rank+ "위 " + title + ",";

				}
			}
			
		}
		

		
		ResponseVO responseVO = new ResponseVO();
		
		result_text = "콘서트 예매 순위 <br>" + result_text;
		result_voice ="콘서트 예매 순위 "+ result_voice;
		
		responseVO.setText(Common.getResponseText(result_text));
		responseVO.setVoice(result_voice);
		
		return responseVO;
		
	}
	
	public ResponseVO getTicket_Theater() {
		
		String result_text="";
		String result_voice="";
		
		
		String url ="http://www.playdb.co.kr/ranking/Ticket/TPBoxOffice.asp?KindOfGoods=01009&Flag=D";
		
	
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("tr"); //뮤지컬
		
		
		for(Element el:element) {
			
			if (el.text().length()>2) {
				
				if (el.text().substring(0,2).equals("1위")||el.text().substring(0,2).equals("2위")||el.text().substring(0,2).equals("3위")||el.text().substring(0,2).equals("4위")||el.text().substring(0,2).equals("5위")) {

					String rank = el.text().substring(0,1);
					String title = el.select("b").text();
					
					Elements ela = el.select("a");
					
					String href = ela.get(0).attr("href");
					String link =  "http://www.playdb.co.kr/gate/PlayGoods_Gate.asp?GoodsCode=" + href.substring(16,24);
					
					result_text = result_text+rank+") " + Common.getLinkText(title, link);
					result_voice = result_voice+ rank+ "위 " + title + ", ";

				}
			}
			
		}
		

		
		ResponseVO responseVO = new ResponseVO();
		
		result_text = "연극 예매 순위 <br>" + result_text;
		result_voice ="연극 예매 순위 "+ result_voice;
		
		responseVO.setText(Common.getResponseText(result_text));
		responseVO.setVoice(result_voice);
		
		return responseVO;
		
	}

	public ResponseVO getBook() {
		
		String result_text="";
		String result_voice="";
		
//		String author="";
		String title ="";
		String link="";
		
		
		String url ="http://www.kyobobook.co.kr/bestSellerNew/bestseller.laf?range=1&kind=0&orderClick=DAA&mallGb=KOR&linkClass=A";
		
	
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
		Elements ellist = doc.select("ul.list_type01");
				
		Elements element = ellist.select("li");
		
		int i=0;
		
		for(Element el:element) {
			
			title = el.select("div.title").text();
//			System.out.println(el.select("div.title").text());
			
			if (title.length()>0 && i<5) {
			
//				String author_temp = el.select("div.author").text();
//				
//				String[] author_array = author_temp.split("|");
//				
//				if (author_array.length>0) {
//					
//					author = author_array[0];
//					
//					
//				}
//				
				
				Element  ela = el.select("a").first();
				
				if (ela != null) {
					link = ela.attr("href");
					
					result_text = result_text+(i+1)+") " + Common.getLinkText(title, link);
					result_voice = result_voice+(i+1)+ "위 " + title + ",";

					
				}
				
				i++;
			}	else {
				
				break;
			}
			
			
		}
		

		
		ResponseVO responseVO = new ResponseVO();
		
		result_text = "베스트 셀러 순위 <br>" + result_text;
		result_voice ="베스트 셀러 순위 "+ result_voice;
		
		responseVO.setText(Common.getResponseText(result_text));
		responseVO.setVoice(result_voice);
		
		return responseVO;
		
	}
	
	public ResponseVO getMovie() {
		
		String result_text="";
		String result_voice="";
		
//		String author="";
		String title ="";
		String link="";
		String image="";
		
		
		String url ="https://movie.naver.com/movie/running/current.nhn";
		
	
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
//        System.out.println(doc.html());

		
		
		Elements ellist = doc.select("div.current_open");
		
		
//		System.out.println(ellist.text());
		
		Elements element = ellist.select("li");
		
		
//		System.out.println(element.text());
		
		int i=0;
		
		String link_head="https://movie.naver.com";
		
		for(Element el:element) {
			
//			System.out.println(el.html());
			
			if (i<5) {
				
				Element  ela = el.select("a").first();
				Element  elimg = el.select("img").first();
				
				
				title = ela.attr("title");
				link = link_head+ela.attr("href");
				image = elimg.attr("src");
//				System.out.println(title+link+image);
				
//				result_text = result_text+(i+1)+") " + Common.getLinkText(title, link);
				
				result_text = result_text+(i+1)+") " + Common.getImage(Common.getLinkText(title, link),image,77,110);
				result_voice = result_voice+(i+1)+ "위 " + title + ",";

				i++;
			} else {
				
				break;
			}
			
		}
		
		ResponseVO responseVO = new ResponseVO();
		
		result_text = "영화 예매 순위 <br>" + result_text;
		result_voice ="영화 예매 순위 "+ result_voice;
		
		responseVO.setText(Common.getResponseText(result_text));
		responseVO.setVoice(result_voice);
		
		return responseVO;
		
	}
	
	public ResponseVO getTicket_Musical() {
		
		String result_text="";
		String result_voice="";
		
		
		String url ="http://www.playdb.co.kr/ranking/Ticket/TPBoxOffice.asp?KindOfGoods=01011&Flag=D";
	
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("tr"); //뮤지컬
		
		
		for(Element el:element) {
			
			if (el.text().length()>2) {
				
				if (el.text().substring(0,2).equals("1위")||el.text().substring(0,2).equals("2위")||el.text().substring(0,2).equals("3위")||el.text().substring(0,2).equals("4위")||el.text().substring(0,2).equals("5위")) {

					String rank = el.text().substring(0,1);
					String title = el.select("b").text();
					
					Elements ela = el.select("a");
					
					String href = ela.get(0).attr("href");
					String link =  "http://www.playdb.co.kr/gate/PlayGoods_Gate.asp?GoodsCode=" + href.substring(16,24);
					
					result_text = result_text+rank+") " + Common.getLinkText(title, link);
					result_voice = result_voice+ rank+ "위 " + title + ",";

				}
			}
			
		}
		

		
		ResponseVO responseVO = new ResponseVO();
		
		result_text = "뮤지컬 예매 순위 <br>" + result_text;
		result_voice ="뮤지컬 예매 순위 "+ result_voice;
		
		responseVO.setText(Common.getResponseText(result_text));
		responseVO.setVoice(result_voice);
		
		return responseVO;
		
	}
	
	public ResponseVO getRecipe() {
		
		String result_text="";
		String result_voice="";

		
		String[] receipes = new String[5];
		String[] links = new String[5];
		
		
		String url ="https://www.10000recipe.com/ranking/home_new.html";
		String url_head = "https://www.10000recipe.com";
		
	
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Elements element = doc.select("div.common_sp_caption"); 
		
		Elements element2 = doc.select("div.common_sp_thumb");
		
		int i=0;
		for(Element el: element) {
		    
			if (i<5) {
				receipes[i] =  el.select("div.common_sp_caption_tit.line2").text();
				
			}
			else {
				break;
			}
			
			i=i+1;
			
		}
		
		int j=0;
		
		for(Element el2: element2.select("a")) {
//		    System.out.println(el2.attr("href"));
		    
		    if (j<5) {
				links[j] =  el2.attr("href");
//				System.out.println(links[j]);
			}
			else {
				break;
			}
			
			j=j+1;
		}
		
		for(int k=0;k<5;k++) {	// 하위 뉴스 기사들을 for문 돌면서 출력
				
			String receipe = receipes[k];
			String link = url_head+ links[k];
			
			result_text =  result_text+(k+1)+ ") "+ Common.getLinkText(receipe, link);
			result_voice = result_voice+(k+1)+"위 " + receipe + ", ";
			
		}
		
		result_text = "오늘 추천 레시피는<br>" + result_text;
		result_voice = "오늘 추천 레시피는 " + result_voice;

		ResponseVO responseVO = new ResponseVO();

		responseVO.setText(Common.getResponseText(result_text));
		responseVO.setVoice(result_voice);
		
		
		return responseVO;
		
		
	}
	
	public ResponseVO getTraffic() {
		
		String result_text="서울 교통정보";
		String result_voice="서울 교통정보";
	
		
		String url = "http://topis.seoul.go.kr/capture/svgImg/1lvArea.png";
		
		ResponseVO responseVO = new ResponseVO();
			
		responseVO.setText(Common.getResponseText(Common.getImage(Common.getLinkText(result_text, url),url,300,300)));
		responseVO.setVoice(result_voice);
		
		return responseVO;
		
		
	}
	
	public ResponseVO getSmog(boolean isBrief) {
		
		String result_text="";
		String result_voice="";
		
		String url = "https://weather.naver.com/today";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("div.ttl_area");
		
		
		String current = element.select("em.level_text").text();

		
		System.out.println(current);
		
		String[] str = current.split(" ");
		String today_smog = str[0];
		String today_microsmog = str[1];
//		
		
		ResponseVO responseVO = new ResponseVO();
		
		if (isBrief!=true) {
			result_text = "오늘 미세먼지: " + today_smog + "<br>초미세먼지: " + today_microsmog;
			result_voice = "오늘 미세먼지는 " + today_smog + ", 초미세먼지는 " + today_microsmog;
			responseVO.setText(Common.getResponseText(Common.getLinkText(result_text,url)));
		
		} else {
			result_text = "미세먼지: " + today_smog + ", 초미세먼지: " + today_microsmog;
			result_voice = "미세먼지는 " + today_smog + ", 초미세먼지는 " + today_microsmog;
			responseVO.setText(result_text);
		}
		
	
		responseVO.setVoice(result_voice);
		
		return responseVO;

		
	}
	
	public ResponseVO getStock(boolean isBrief) {
		
		
		String result_text="";
		String result_voice="";
		
		String voice_dir="";
		String voice_value="";
		
		
		
		String url = "https://m.stock.naver.com/";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("ul.list_chart");
		
		
		String current = element.select("span").text();

		
		System.out.println(current);
		
		String[] str = current.split(" ");
		String Kospi_price = str[0];
		String Kospi_change = str[2];
		String Kosdaq_price = str[7];
		String Kosdaq_change = str[9];
		String Kospi_change_dir = Kospi_change.substring(0,1);
		
		
		if (Kospi_change_dir.equals("+")) {
			voice_dir=" 상승 ";
			voice_value = Kospi_change.replace("%", "퍼센트");
			voice_value = voice_value.replace("+", " ");
			
		} else if (Kospi_change_dir.equals("-")) {
			voice_dir=" 하락 ";
			voice_value = Kospi_change.replace("%", "퍼센트");
			voice_value = voice_value.replace("-", " ");
			
		} else {
			voice_dir="";
			voice_value="";
		}
		
		result_voice =  "코스피 " + Kospi_price + voice_value+ voice_dir + ", ";
		
		String Kosdaq_change_dir = Kosdaq_change.substring(0,1);
		
		
		if (Kosdaq_change_dir.equals("+")) {
			voice_dir=" 상승 ";
			voice_value = Kosdaq_change.replace("%", "퍼센트");
			voice_value = voice_value.replace("+", " ");
			
		} else if (Kosdaq_change_dir.equals("-")) {
			voice_dir=" 하락 ";
			voice_value = Kosdaq_change.replace("%", "퍼센트");
			voice_value = voice_value.replace("-", " ");
			
		} else {
			voice_dir="";
			voice_value="";
		}
		
		
		ResponseVO responseVO = new ResponseVO();
		
		if (isBrief!=true) {
		
			result_text = "코스피: " + Kospi_price + "("+ Kospi_change+")" + "<br>코스닥: " + Kosdaq_price+"("+Kosdaq_change + ")";
			result_voice = result_voice+  "코스닥 " + Kosdaq_price + voice_value+voice_dir;
			
			responseVO.setText(Common.getResponseText(Common.getLinkText(result_text, url)));
		} else {
			
			result_text = "코스피: " + Kospi_price + "("+ Kospi_change+")" + ", 코스닥: " + Kosdaq_price+"("+Kosdaq_change + ")";
			result_voice = result_voice+  "코스닥 " + Kosdaq_price + voice_value+voice_dir;
			
			responseVO.setText(result_text);
		}
		
		responseVO.setVoice(result_voice);
		
		return responseVO;
		
		
	}

	public ResponseVO getRank(boolean isBrief) {
	
		String result_text="";
		String result_voice="";
		
		String title="";
		String link="";
	
		
		String url = "https://trends.google.co.kr/trends/trendingsearches/daily/rss?geo=KR";
		Document doc = null;
		int i=0;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("channel");
		
		for(Element el : element.select("item")) {	// 하위 뉴스 기사들을 for문 돌면서 출력
//			System.out.println(el.text());
				
			if(i>=0 && i<5) {
				
				title = el.select("title").text();
				link = el.select("link").text();
				
				result_text = result_text+(i+1)+") " + Common.getLinkText(title, link);
				result_voice = result_voice+(i+1)+"위 " + title + ", ";
				
				
			}
			
			i=i+1;
		}
	
		ResponseVO responseVO = new ResponseVO();
		
		if(isBrief!=true) {
			result_text="인기 검색어<br>" + result_text;
			result_voice = "인기 검색어 " + result_voice;
			
			responseVO.setText(Common.getResponseText(result_text));
			responseVO.setVoice(result_voice);
		} else {
			
			result_text="인기 검색어<br>" + result_text;
			result_voice = "인기 검색어 " + result_voice;
			responseVO.setText(Common.getBriefingText(result_text));
			responseVO.setVoice(result_voice);
		}
		
		return responseVO;
	
	
	}

	public ResponseVO getNews() {
	
		String result_text="";
		String result_voice="";
		
		
		String url = "https://m.yna.co.kr/theme/topnews-history?site=footer_theme_topnews";
		Document doc = null;
		int i=0;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("div.item-box");
	
		
		for(Element el : element.select("strong")) {	// 하위 뉴스 기사들을 for문 돌면서 출력
//			System.out.println(el.text());
			if(i>=0 && i<5) {
				
			
				result_text = result_text + (i+1)+") "+el.text()+ "<br>";
				result_voice = result_voice + (i+1)+"위, "+el.text()+ ", ";
			}
			
			i=i+1;
		}
		
		ResponseVO responseVO = new ResponseVO();
		
		responseVO.setText(Common.getResponseText(result_text));
		responseVO.setVoice(result_voice);
		
		return responseVO;
	
		
	}
	
	public ResponseVO getWelcome() {
		
		String result_text="나만의 인공지능 비서 브리퍼 입니다.<br> 무엇이든지 물어 보세요.";
		String result_voice="나만의 인공지능 비서 브리퍼 입니다,  무엇이든지 물어 보세요.";
		
		ResponseVO responseVO = new ResponseVO();
		

		responseVO.setText(Common.getResponseText(result_text));
		responseVO.setVoice(result_voice);
		
		return responseVO;
	}
	
	public ResponseVO getNative(String input) {
		
		
		ResponseVO responseVO = new ResponseVO();

		responseVO.setText(Common.getResponseText(input));
		responseVO.setVoice(input);
		
		return responseVO;
		
		
	}
	
	
	
}
