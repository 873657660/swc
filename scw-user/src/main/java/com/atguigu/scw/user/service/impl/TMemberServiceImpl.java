package com.atguigu.scw.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.atguigu.scw.enums.UserExceptionEnum;
import com.atguigu.scw.user.bean.TMember;
import com.atguigu.scw.user.bean.TMemberExample;
import com.atguigu.scw.user.exp.LoginRegistException;
import com.atguigu.scw.user.mapper.TMemberMapper;
import com.atguigu.scw.user.service.TMemberService;

@Service
public class TMemberServiceImpl implements TMemberService {

	@Autowired
	TMemberMapper memberMapper;

	@Override
	public void saveMember(TMember member) {
		// 1.验证账号是否被占用

		// 2.验证邮箱是否被占用

		member.setUserpswd(new BCryptPasswordEncoder().encode(member.getUserpswd()));
		memberMapper.insertSelective(member);
	}

	@Override
	public TMember getMemberByLogin(String loginacct, String password) {

		TMemberExample example = new TMemberExample();
		example.createCriteria().andLoginacctEqualTo(loginacct);

		List<TMember> list = memberMapper.selectByExample(example);
		if (list.size() == 0) {
			throw new LoginRegistException(UserExceptionEnum.LOGINACCT_NO_EXIST);
		}

		TMember member = list.get(0);

		// BCryptPasswordEncoder每次生成密码不一样的。必须使用matches方法比较
		if (!new BCryptPasswordEncoder().matches(password, member.getUserpswd())) {
			throw new LoginRegistException(UserExceptionEnum.USERPSWD_ERROR);
		}
		// 擦除重要数据
		member.setUserpswd(null);
		return member;
	}

	@Override
	public TMember getTMemberById(Integer id) {
		return memberMapper.selectByPrimaryKey(id);
	}

}
