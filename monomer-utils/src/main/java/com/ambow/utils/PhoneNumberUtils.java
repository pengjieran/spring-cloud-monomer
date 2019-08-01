package com.ambow.utils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

/**
* @ClassName: com.mtoliv.model.response.ResponseBody
* @Description: DONE
* @author Aaron
* @date 2017年11月4日 下午3:38:49 
*
 */
public class PhoneNumberUtils {

    /**
     * 判断是否是手机号，采用国际通用规则
     * @param phoneNumber
     * @return
     */
	public static boolean isValidPhoneNumber(String phoneNumber) {
		
		PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
		try {
			
			PhoneNumber number = phoneNumberUtil.parse(phoneNumber, "CN");
			return phoneNumberUtil.isValidNumber(number);
		} catch (NumberParseException e) {
			
			return false;
		}
	}
	
	private PhoneNumberUtils() {}
}