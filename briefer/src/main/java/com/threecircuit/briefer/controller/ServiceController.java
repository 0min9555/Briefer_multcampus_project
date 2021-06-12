package com.threecircuit.briefer.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.threecircuit.briefer.model.PersonalVO;
import com.threecircuit.briefer.model.ResponseVO;
import com.threecircuit.briefer.service.ChatVoiceService;
import com.threecircuit.briefer.service.ChatbotService;
import com.threecircuit.briefer.service.Common;
import com.threecircuit.briefer.service.ContentConnectService;
import com.threecircuit.briefer.service.ContentCreateService;
import com.threecircuit.briefer.service.PersonalService;

@RestController
public class ServiceController {
	
	@Autowired
	private ChatbotService chatbotService;
	
	@Autowired
	private ChatVoiceService chatvoiceService;
	
	@Autowired
	private ContentConnectService contentconnectService;
		
	@Autowired
	private ContentCreateService contentcreateService;
	
	@Autowired
	private PersonalService personalService;
	
	@Value("${voicefile.SavePath}")	
	String voicefilePath; //=  "c:/ai/";
	
	
	@RequestMapping("/brieferCall")
	public String brieferCall(@RequestParam("message") String message) {
		
		String result_json = "";
		
		ResponseVO responseVO = new ResponseVO();
		
		if (message.equals("")) {
			 responseVO = contentconnectService.getWelcome();
		}
		else {
			
			String response = chatbotService.main(message);
			 responseVO  = MakeResponse(response);
		}
		
	
		
	
		result_json = Common.getResponse(responseVO);
		System.out.println("Json:"+result_json);
		
		return result_json;
	}
	@RequestMapping("/brieferVoice")
	public String brieferVoice(@RequestParam("message") String message) {
		
		String result_json = "";
		
		ResponseVO responseVO = new ResponseVO();
		
		if (message.equals("")) {
			 responseVO = contentconnectService.getWelcome();
		}
		else {
			
			String response = chatbotService.main(message);
			 responseVO  = MakeResponse(response);
		}
		
	
		
	
//		result_json = Common.getResponse(responseVO);
//		System.out.println("Json:"+result_json);
		
		result_json = responseVO.getVoice();
		
		return result_json;
	}
	
	
	@RequestMapping("/brieferSTT")
	public String brieferSTT(@RequestParam("voiceFile") MultipartFile file) {
		String result = "";
		
		try {
			  
		
			  String originalFileName = file.getOriginalFilename();  
			  
			  UUID uid = UUID.randomUUID();
				
			  String savedFileName = uid.toString() + "_" + originalFileName;
			  String filePathName = voicefilePath + savedFileName;
			  File file1 = new File(filePathName);
			  file.transferTo(file1);
			  
			  result = chatvoiceService.clovaSpeechToText(filePathName,"Kor");
			  System.out.println("STT Result:" + result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		return result;
	}
	
	
	@RequestMapping("/brieferTTS")
	public String brieferTTS(@RequestParam("message") String message) {
		
		String result = "";
		
//		System.out.println("TTS Message:"+message);
		
		result = chatvoiceService.clovaTextToSpeech(message,"nara");	
		
		System.out.println("TTS Result:"+result);

		return result;
	}
	
	@RequestMapping("/brieferVoiceCall")
	public @ResponseBody byte[] brieferVoiceCall(@RequestParam("message") String message, HttpServletResponse responseObj) throws IOException {
		
		String result = "";
//		String message="";
		String response="";
		
		
		System.out.println("Source:Speaker "+message);
		
//		try {
			  
			
//				if (file != null) {
//				 	String originalFileName = file.getOriginalFilename();  
//				  
//				 	System.out.println("OriginalFileName: "+ originalFileName);
//				 
//				 	UUID uid = UUID.randomUUID();
//				
//				 	String savedFileName = uid.toString() + "_" + originalFileName;
//				 	String filePathName = voicefilePath + savedFileName;
//					File file1 = new File(filePathName);
//					file.transferTo(file1);
//					  
//				    System.out.println("filePathName: "+ filePathName);
//					  
//					message  = chatvoiceService.clovaSpeechToText(filePathName,"Kor");
//					  
//					System.out.println("STT: " +  message);
//				} else {
//					message="";
//				}
				  
		ResponseVO responseVO = new ResponseVO();
			
		if (message.equals("")) {
			 responseVO = contentconnectService.getWelcome();
		}
		else {
			
			response = chatbotService.main(message);
			responseVO  = MakeResponse(response);
		}
		System.out.println("Source:Speaker "+responseVO.getVoice());  
		
		result =  chatvoiceService.clovaTextToSpeech2(responseVO.getVoice(),"nara");	
				
				
			  
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		responseObj.setContentType("audio/mpeg3");
		
		FileInputStream in = new FileInputStream( new File(result));

		return IOUtils.toByteArray(in);
	}
	
	
	
	private ResponseVO MakeResponse(String response) {
		
		
		String cust_id="user@user.com";
		
		ResponseVO responseVO = new ResponseVO();
	
		if (response.equals("날씨")) {
			
			responseVO = contentconnectService.getWeather(false);
		} 
		else if (response.equals("주가")) {
			
			responseVO = contentconnectService.getStock(false);
		}
		else if (response.equals("미세먼지")) {
			
			responseVO = contentconnectService.getSmog(false);
		} 
		else if (response.equals("코로나")) {
			
			responseVO = contentconnectService.getCovid(false);
		}
		else if (response.equals("로또추천")) {
			
			responseVO = contentcreateService.getLottoNum(false);
		}
		else if (response.equals("메뉴추천")) {
			
			responseVO = contentcreateService.getFoodMenu(false);
		}
		else if (response.equals("뉴스")) {
			
			responseVO = contentconnectService.getNews();
		}
		else if (response.equals("검색순위")) {
			
			responseVO = contentconnectService.getRank(false);
		}
		else if (response.equals("교통")) {
			
			responseVO = contentconnectService.getTraffic();
		}
		else if (response.equals("영화")) {
			
			responseVO = contentconnectService.getMovie();
		}
		else if (response.equals("도서")) {
			
			responseVO = contentconnectService.getBook();
		}
		else if (response.equals("야구")) {
			
			responseVO = contentconnectService.getBaseball(false);
		}
		
		else if (response.equals("요리")) {
			
			responseVO = contentconnectService.getRecipe();
		}
		else if (response.equals("뮤지컬")) {
			
			responseVO = contentconnectService.getTicket_Musical();
		}
		else if (response.equals("연극")) {
			
			responseVO = contentconnectService.getTicket_Theater();
		}
		else if (response.equals("콘서트")) {
			
			responseVO = contentconnectService.getTicket_Concert();
		}
		else if (response.equals("오페라")) {
			
			responseVO = contentconnectService.getTicket_Classic();
		}
		else if (response.equals("무용")) {
			
			responseVO = contentconnectService.getTicket_KoreaNdancing();
		}
		
		else if (response.equals("브리핑")) {
			
			responseVO = getBrief(cust_id);
		}
		
		else {
			responseVO = contentconnectService.getNative(response); 
		}
		
		return responseVO;
		
	}
	
	public ResponseVO getBrief(String cust_id) {
		
		String result_text="";
		String result_voice="";
		String cont_id="FUN001";
		
		
		
		Date time = new Date();
		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm");
		String mytime = format.format(time);

		String head_text = "[브리핑] "+mytime+"<br>";
		String head_voice = "[브리핑] "+mytime+", ";
		

		
		PersonalVO personalVO =  personalService.getPersonalInfo(cust_id, cont_id);
		
		if (!personalVO.getValue1().equals("")) {
			ResponseVO vo_result = getBeiefVO(personalVO.getValue1());
			String value_text = vo_result.getText()+"<br>";
			String value_voice = vo_result.getVoice()+", ";
			
			result_text = result_text+value_text;
			result_voice = result_voice+value_voice;
			
		}
		if (!personalVO.getValue2().equals("")) {
			ResponseVO vo_result = getBeiefVO(personalVO.getValue2());
			String value_text = vo_result.getText()+"<br>";
			String value_voice = vo_result.getVoice()+", ";
			
			result_text = result_text+value_text;
			result_voice = result_voice+value_voice;
		}
		if (!personalVO.getValue3().equals("")) {
			ResponseVO vo_result = getBeiefVO(personalVO.getValue3());
			String value_text =  vo_result.getText()+"<br>";
			String value_voice  = vo_result.getVoice()+", ";
			
			result_text = result_text+value_text;
			result_voice = result_voice+value_voice;
		}
		if (!personalVO.getValue4().equals("")) {
			ResponseVO vo_result = getBeiefVO(personalVO.getValue4());
			String value_text = vo_result.getText()+"<br>";
			String value_voice = vo_result.getVoice()+", ";
			
			result_text = result_text+value_text;
			result_voice = result_voice+value_voice;
		}
		if (!personalVO.getValue5().equals("")) {
			ResponseVO vo_result = getBeiefVO(personalVO.getValue5());
			String value_text = vo_result.getText();
			String value_voice  = vo_result.getVoice();
			
			result_text = result_text+value_text;
			result_voice = result_voice+value_voice;
		}
				
		result_text = head_text+ result_text;
		result_voice = head_voice + result_voice;
		ResponseVO responseVO = new ResponseVO();

		responseVO.setText(Common.getResponseText(result_text));
		responseVO.setVoice(result_voice);
		
		System.out.println("Brefing :"+result_voice);
		
		return responseVO;
		
	}
	
	private ResponseVO getBeiefVO(String cont_id) {
		
		ResponseVO responseVO = new ResponseVO();
		
		if (cont_id.equals("LIF001")) {
			responseVO = contentconnectService.getWeather(true);
		} else if(cont_id.equals("LIF002")) {
			responseVO = contentconnectService.getSmog(true);
		} else if(cont_id.equals("LIF003")) {
			responseVO = contentconnectService.getCovid(true);
		} else if(cont_id.equals("FIN001")) {
			responseVO = contentconnectService.getStock(true);
		} else if(cont_id.equals("TRD001")) {
			responseVO = contentconnectService.getRank(true);
		} else if(cont_id.equals("SPO001")) {
			responseVO = contentconnectService.getBaseball(true);
		} else if(cont_id.equals("TRD006")) {
			responseVO = contentcreateService.getFoodMenu(true);
		}
		
		return responseVO;
	}
	
}







