package com.threecircuit.briefer.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.threecircuit.briefer.dao.IMemberDAO;
import com.threecircuit.briefer.model.MemberVO;

@Service
public class MemberService implements IMemberService {

	@Autowired
	@Qualifier("IMemberDAO")
	IMemberDAO dao;

	@Override
	public MemberVO loginCheck(String id, String pwd) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pwd", pwd);
		return dao.loginCheck(map);
	}
	
	@Override
	public MemberVO idCheck(String id) {
		return dao.idCheck(id);
	}
	
	@Override
	public void insertMember(MemberVO vo) {
		dao.insertMember(vo);
	}
	
	
	@Override
	public void updateMember(MemberVO vo) {
		dao.updateMember(vo);
	}
	
	
	@Override
	public MemberVO selectMember(String id) {
		return dao.selectMember(id);
	}

}
