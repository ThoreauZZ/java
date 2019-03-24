package com.thoreauz.bootlearn.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 2019/3/24 1:25 AM.
 *
 * @author zhaozhou
 */
@Getter
@Setter
public class UserDO {
    private String id;
    private String name;
    private Integer age;

    public UserDO(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
