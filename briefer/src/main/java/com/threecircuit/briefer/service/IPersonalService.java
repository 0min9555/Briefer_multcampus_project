package com.threecircuit.briefer.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.threecircuit.briefer.model.PersonalVO;

public interface IPersonalService {
	
	PersonalVO getPersonalInfo(String cust_id,String cont_id);
	ArrayList<PersonalVO> getPersonalList(String cust_id);
	List<HashMap<String,String>> getContentsList();
	List<HashMap<String,String>> getContentsListAll();
	void insertPersonalInfo(PersonalVO personalVO);
	void updatePersonalInfo(PersonalVO personalVO);

}
