package com.threecircuit.briefer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.threecircuit.briefer.dao.IPersonalDAO;
import com.threecircuit.briefer.model.PersonalVO;

@Service
public class PersonalService implements IPersonalService {
	
	
	@Autowired
	@Qualifier("IPersonalDAO")
	IPersonalDAO dao;

	@Override
	public PersonalVO getPersonalInfo(String cust_id,String cont_id) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cust_id", cust_id);
		map.put("cont_id", cont_id);
		
		return dao.getPersonalInfo(map);
	}

	@Override
	public ArrayList<PersonalVO> getPersonalList(String cust_id) {
		
		return dao.getPersonalList(cust_id);
	}

	@Override
	public List<HashMap<String, String>> getContentsList() {
		
		return dao.getContentsList();
	}
	@Override
	public List<HashMap<String, String>> getContentsListAll() {
		
		return dao.getContentsListAll();
	}
	@Override
	public void insertPersonalInfo(PersonalVO personalVO) {
		dao.insertPersonalInfo(personalVO);
	}
	@Override
	public void updatePersonalInfo(PersonalVO personalVO) {
		dao.updatePersonalInfo(personalVO);
	}

}
