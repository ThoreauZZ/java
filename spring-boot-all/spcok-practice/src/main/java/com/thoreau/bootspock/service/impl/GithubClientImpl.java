package com.thoreau.bootspock.service.impl;

import com.thoreau.bootspock.entity.GithubRepo;
import com.thoreau.bootspock.service.GithubClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2019/3/28 11:19 PM.
 *
 * @author zhaozhou
 */
@Service("githubClient")
public class GithubClientImpl implements GithubClient {

    @Override
    public List<GithubRepo> githubRepos(String userName) {
        return null;
    }

    @Override
    public List<GithubRepo> githubRepos(Long userId) {
        return null;
    }
}
