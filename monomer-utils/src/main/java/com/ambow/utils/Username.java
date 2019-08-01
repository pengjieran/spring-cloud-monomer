package com.ambow.utils;

import cn.hutool.core.lang.Validator;

public class Username {

	private static final int PHONE = 1;//手机号
	
	private static final int EMAIL = 2;//邮箱
	
	private static final int OTHER = 3;//其它
	
	public static int type(String username) {
		
		if (PhoneNumberUtils.isValidPhoneNumber(username)) return PHONE;
		if (Validator.isEmail(username)) return EMAIL;
		return OTHER;
	}
}