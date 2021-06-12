package com.threecircuit.briefer.service;

import com.threecircuit.briefer.model.MemberVO;

public interface IMemberService {
	MemberVO loginCheck(String id, String pwd);
	MemberVO idCheck(String id);
	void insertMember(MemberVO vo);
	MemberVO selectMember(String id);
	void updateMember(MemberVO vo);
	
}
