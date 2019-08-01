package com.ambow.enums;

import com.ambow.utils.CommonEnum;

/**
 * Created by IntelliJ IDEA.
 *
 * @PS: 平台常量共享
 * @Author: ruifeng.zhao
 * @Email: ruifeng.zhao@ambow.com
 * @Date: 2019/5/16 星期四 14:32
 */
public class PlatformEnums {

    /**
     * 平台枚举
     */
    public enum platformEnum implements CommonEnum {

        HUANYULIREN(1, "寰宇利人"),
        EDURP(2, "EDURP");
        private Integer code;
        private String  msg;

        private platformEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }
}