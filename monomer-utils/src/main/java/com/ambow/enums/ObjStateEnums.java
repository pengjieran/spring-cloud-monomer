package com.ambow.enums;

/**
 * Created by IntelliJ IDEA.
 *
 * @PS:
 * @Author: ruifeng.zhao
 * @Email: ruifeng.zhao@ambow.com
 * @Date: 2019/5/16 星期四 14:32
 */
public class ObjStateEnums {
    /**
     * 状态   状态:1-有效，0-删除
     */
    public enum ObjStateEnum {

        VALID("有效", 1),DELETE("删除", 0);
        private String name ;
        private int index ;

        private ObjStateEnum(String name , int index ){
            this.name = name ;
            this.index = index ;
        }
        public String getName() {
            return name;
        }
        public int getIndex() {
            return index;
        }
    }
}
