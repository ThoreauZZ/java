package com.thoreau.bootspock.resource.vo;

import com.thoreau.bootspock.entity.GithubRepo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 2019/3/31 2:41 PM.
 *
 * @author zhaozhou
 */
@Getter
@Setter
@ToString
public class UserVO {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private Long reposCount;
    private List<GithubRepo> repos;
    private Long createTime;
    private Long updateTime;
}
