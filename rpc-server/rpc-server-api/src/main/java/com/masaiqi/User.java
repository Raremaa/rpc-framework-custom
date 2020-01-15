package com.masaiqi;

import java.io.Serializable;

/**
 * @author sq.ma
 * @date 2020/1/15 下午1:48
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1393446543299157549L;

    private String name;

    private Integer age;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
