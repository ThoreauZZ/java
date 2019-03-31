package com.thoreauz.bootlearn.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 2019/3/24 1:25 AM.
 *
 * @author zhaozhou
 */
@Getter
@Setter
@ToString
public class UserVO {
    private String id;
    private String name;
    private Integer age;
    private Date createTime;
    private Date modifyTime;

    public UserVO() {
    }

    public UserVO(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
