package com.thoreau.bootspock.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 2019/3/28 11:16 PM.
 *
 * @author zhaozhou
 */
@Getter
@Setter
@ToString
public class GithubRepo {
    private Long id;
    private String name;
    private String url;
}
