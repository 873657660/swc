package com.atguigu.scw.user.service;

import com.atguigu.scw.user.bean.TMember;

public interface TMemberService {

	void saveMember(TMember member);

	TMember getMemberByLogin(String loginacct, String password);

	TMember getTMemberById(Integer id);
}
