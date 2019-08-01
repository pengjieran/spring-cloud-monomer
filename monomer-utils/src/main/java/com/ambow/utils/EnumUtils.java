package com.ambow.utils;

import org.apache.commons.lang3.StringUtils;

public class EnumUtils {

    public static <T extends CommonEnum> String getMsgByCode(Class<T> enumClass, Integer code) {
        if(code == null){
            return null;
        }
        for (T each : enumClass.getEnumConstants()) {
            if(each.getCode().equals(code)){
                return each.getMsg();
            }
        }
        return null;
    }

    public static <T extends CommonEnum> Integer getCodeByMsg(Class<T> enumClass, String name) {
        if(StringUtils.isBlank(name)){
            return null;
        }
        for (T each : enumClass.getEnumConstants()) {
            if(each.getMsg().equals(name)){
                return each.getCode();
            }
        }
        return null;
    }
}
