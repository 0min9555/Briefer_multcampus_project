package com.threecircuit.briefer.dao;

import java.util.HashMap;

import com.threecircuit.briefer.model.MemberVO;

public interface IMemberDAO {

	MemberVO loginCheck(HashMap<String, Object> map);
	MemberVO idCheck(String id);
	void insertMember(MemberVO vo);
	MemberVO selectMember(String id);
	void updateMember(MemberVO vo);

}
