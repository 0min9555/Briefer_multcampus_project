package com.threecircuit.briefer.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.threecircuit.briefer.model.PersonalVO;

public interface IPersonalDAO {
	
	PersonalVO getPersonalInfo(HashMap<String, String> map);
	ArrayList<PersonalVO> getPersonalList(String cust_id);
	List<HashMap<String,String>> getContentsList();
	List<HashMap<String,String>> getContentsListAll();
	void insertPersonalInfo(PersonalVO personalVO);
	void updatePersonalInfo(PersonalVO personalVO);

}
