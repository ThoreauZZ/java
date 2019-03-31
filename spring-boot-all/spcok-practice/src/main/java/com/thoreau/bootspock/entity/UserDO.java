package com.thoreau.bootspock.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 2019/3/28 11:09 PM.
 *
 * @author zhaozhou
 */
@Setter
@Getter
@ToString
public class UserDO implements Serializable {
    private Long id;
    private String nick;
    private String email;
    private String gender;
    private long reposCount;
    private List<GithubRepo> repos;
    private Long createTime;
    private Long updateTime;
}
