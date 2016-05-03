package com.lkl.springcloud.hystrix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 依赖服务
 * Created by liaokailin on 16/5/1.
 */
@Service
public class HystrixService {

    @Autowired
    private CallDependencyService dependencyService;
    public String callDependencyService() {
        return dependencyService.mockGetUserInfo();
    }
}
