package com.thoreau.bootspock.service;

import com.thoreau.bootspock.entity.GithubRepo;

import java.util.List;

/**
 * 2019/3/28 11:18 PM.
 *
 * @author zhaozhou
 */
public interface GithubClient {
    List<GithubRepo> githubRepos(String userName);
    List<GithubRepo> githubRepos(Long userId);
}
