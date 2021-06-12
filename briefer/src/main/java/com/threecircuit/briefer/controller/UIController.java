package com.threecircuit.briefer.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.threecircuit.briefer.model.MemberVO;
import com.threecircuit.briefer.model.PersonalVO;
import com.threecircuit.briefer.service.MemberService;
import com.threecircuit.briefer.service.PersonalService;

@Controller
public class UIController {
	
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PersonalService personalService;
		
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("/login")
	public String loginCheck(@RequestParam("id") String id, 
			   @RequestParam("pwd") String pwd,
			   HttpSession session) {

		MemberVO vo = memberService.loginCheck(id, pwd);
		
		if(vo != null) {
			session.setAttribute("sid", vo.getId());
			return "redirect:/briefer";  //index 페이지로 포워딩
		} else {
		
			return "redirect:/";  //index 페이지로 포워딩
		}
			
			
	}
	@RequestMapping("/logout")
	public String userLogout(HttpSession session) {
		
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/join")
	public String join() {
		return "join"; 
	}
	
	@ResponseBody 
	@RequestMapping("/idCheck")
	public String idCheck(@RequestParam("id") String id) {
		
		MemberVO vo = memberService.idCheck(id);
		
		String result="0";
		if(vo !=null) result = "1";
		
		return result;
	}
	
	@RequestMapping("/register")
	public String register(MemberVO vo) {
		
		System.out.println("Member:"+vo.toString());
		
		
		memberService.insertMember(vo);
		return "briefer"; 
	}
	

	@RequestMapping("/profile")
	public String profile(HttpSession session) {
		
		if (session.getAttribute("sid") != null) {
			return "profile"; 
		} else {
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping("/briefer")
	public String brifer(Model model,HttpSession session) {
		
		String cust_id ="user@user.com";
		String cont_id_shortcut="FUN002";
		
		String shortcut1="";
		String shortcut2="";
		String shortcut3="";
		String shortcut4="";

		
		PersonalVO shortcutVO = personalService.getPersonalInfo(cust_id,cont_id_shortcut);
		
		System.out.println("#1:"+shortcutVO.getValue1());
		
		if (shortcutVO !=null) {
			
			if (shortcutVO.getValue1().equals("")) {
				shortcut1="비었음";
				
			} else {
				shortcut1=shortcutVO.getValue1();
			}
			
			if (shortcutVO.getValue2().equals("")) {
				shortcut2="비었음";
				
			} else {
				shortcut2=shortcutVO.getValue2();
			}
			
			if (shortcutVO.getValue3().equals("")) {
				shortcut3="비었음";
				
			} else {
				shortcut3=shortcutVO.getValue3();
			}
			
			if (shortcutVO.getValue4().equals("")) {
				shortcut4="비었음";
				
			} else {
				shortcut4=shortcutVO.getValue4();
			}
			
			
		} else {    //default 셑팅
			shortcut1="브리핑";
			shortcut2="확진자";
			shortcut3="날씨";
			shortcut4="주가 지수";
		}
		
		model.addAttribute("shortcut1",shortcut1);
		model.addAttribute("shortcut2",shortcut2);
		model.addAttribute("shortcut3",shortcut3);
		model.addAttribute("shortcut4",shortcut4);
		
		if (session.getAttribute("sid") != null) {
			return "briefer"; 
		} else {
			return "redirect:/";
		}
		
		
		
	}
	@RequestMapping("/personalize")
	public String personalize(Model model, HttpSession session) {
		
	    
		//String cust_id  = (String) session.getAttribute("sid");
		
		String cust_id ="user@user.com";
		
		
		
	    
		
		//바로가기 설정
		String cont_id_shortcut="FUN002";
		PersonalVO shortcutVO = personalService.getPersonalInfo(cust_id,cont_id_shortcut);
		
//		System.out.println(shortcutVO.getValue2());
		
		List<HashMap<String, String>> cont_list_all = personalService.getContentsListAll();
		
		String combo_shortcut = makeShortcutCombo(cont_list_all,shortcutVO);
		
		model.addAttribute("combo_shortcut",combo_shortcut);
		
		
		//브피핑 설정
		
		String cont_id_briefing="FUN001";
		PersonalVO briefingVO = personalService.getPersonalInfo(cust_id,cont_id_briefing);
		

		List<HashMap<String, String>> cont_list_brief = personalService.getContentsList();
		
		String combo_briefing = makeBriefingCombo(cont_list_brief,briefingVO);
		
		model.addAttribute("combo_briefing",combo_briefing);
		
		System.out.println("briefing combo:"+combo_briefing);
		
		
		return "personalize"; 
	}
	
	@ResponseBody
	@RequestMapping("/update_shortcut")
	public String update_shortcut(@RequestParam("selected") String selected) {
		
		String result="";
		String cust_id = "user@user.com";
		String cont_id = "FUN002";
		
		
		System.out.println("shortcut selected item:"+selected);
		
		String[] selecteditem = selected.split(",");
		
		PersonalVO newpersonalVO = new PersonalVO();
		
		newpersonalVO.setCust_id(cust_id);
		newpersonalVO.setCont_id(cont_id);
		
		
		
		if (selecteditem.length>0) {
			newpersonalVO.setValue1(selecteditem[0]);
		} 
		if (selecteditem.length>1) {
			newpersonalVO.setValue2(selecteditem[1]);
		} 
		if (selecteditem.length>2) {
			newpersonalVO.setValue3(selecteditem[2]);
		} 
		if (selecteditem.length>3) {
			newpersonalVO.setValue4(selecteditem[3]);
		}

		
		PersonalVO personalVO = personalService.getPersonalInfo(cust_id,cont_id);
		
		if (personalVO != null) {
			personalService.updatePersonalInfo(newpersonalVO);
			result = "업데이트 되었습니다.";
		} else {
			personalService.insertPersonalInfo(newpersonalVO);
			result = "업데이트 되었습니다.";
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping("/update_briefing")
	public String update_briefing(@RequestParam("selected") String selected) {
		
		String result="";
		String cust_id = "user@user.com";
		String cont_id = "FUN001";
		
		
		System.out.println("briefing selected item:"+selected);
		
		String[] selecteditem = selected.split(",");
		
		PersonalVO newpersonalVO = new PersonalVO();
		
		newpersonalVO.setCust_id(cust_id);
		newpersonalVO.setCont_id(cont_id);
		
		
		
		if (selecteditem.length>0) {
			newpersonalVO.setValue1(selecteditem[0]);
		} 
		if (selecteditem.length>1) {
			newpersonalVO.setValue2(selecteditem[1]);
		} 
		if (selecteditem.length>2) {
			newpersonalVO.setValue3(selecteditem[2]);
		} 
		if (selecteditem.length>3) {
			newpersonalVO.setValue4(selecteditem[3]);
		}
		if (selecteditem.length>4) {
			newpersonalVO.setValue5(selecteditem[4]);
		}

		
		PersonalVO personalVO = personalService.getPersonalInfo(cust_id,cont_id);
		
		if (personalVO != null) {
			personalService.updatePersonalInfo(newpersonalVO);
			result = "업데이트 되었습니다.";
		} else {
			personalService.insertPersonalInfo(newpersonalVO);
			result = "업데이트 되었습니다.";
		}
		
		return result;
		
	}
	
	private String makeShortcutCombo(List<HashMap<String, String>> list, PersonalVO personalVO) {
		
		String result="";
		String option_text="";
		String option_value="";
		
		
//		System.out.println(personalVO.getValue2());
		
		for(HashMap<String,String> item:list)
		{
			
			option_text = item.get("title");
			option_value = option_text;
			
			if (personalVO !=  null) {
				
				if (personalVO.getValue1().equals(option_value)||personalVO.getValue2().equals(option_value)||personalVO.getValue3().equals(option_value)||personalVO.getValue4().equals(option_value)) {
					result = result+ "<option value='"+option_value+"' selected>"+option_text+"</option>";
				} else {
					result = result+ "<option value='"+option_value+"'>"+option_text+"</option>";
				}
				
					
			} else {
				result = result+ "<option value='"+option_value+"'>"+option_text+"</option>";
				
			}
		}
		
		
		System.out.println("Combo:"+result);
		
		return result;
	}
	
private String makeBriefingCombo(List<HashMap<String, String>> list, PersonalVO personalVO) {
		
		String result="";
		String option_text="";
		String option_value="";
		
		
		for(HashMap<String,String> item:list)
		{
			
			option_text = item.get("title");
			option_value = item.get("id");
			
			if (personalVO !=  null) {
				
				if (personalVO.getValue1().equals(option_value)||personalVO.getValue2().equals(option_value)||personalVO.getValue3().equals(option_value)||personalVO.getValue4().equals(option_value)) {
					result = result+ "<option value='"+option_value+"' selected>"+option_text+"</option>";
				} else {
					result = result+ "<option value='"+option_value+"'>"+option_text+"</option>";
				}
				
					
			} else {
				result = result+ "<option value='"+option_value+"'>"+option_text+"</option>";
				
			}
		}
		
		
		System.out.println("Combo:"+result);
		
		return result;
	}
	

}





















