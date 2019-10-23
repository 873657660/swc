package com.atguigu.scw.user.exp;

import com.atguigu.scw.enums.UserExceptionEnum;

public class LoginRegistException extends RuntimeException {

	public LoginRegistException() {}
	
	public LoginRegistException(UserExceptionEnum enums) {
		super(enums.getMsg());
	}
	
}
