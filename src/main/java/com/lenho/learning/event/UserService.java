package com.lenho.learning.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author langyonghe
 * @date 2021/4/8 10:42
 */
@Slf4j
@Service
public class UserService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register(String username) {
        //执行注册逻辑
        log.info("register 执行用户{}逻辑", username);

        //发布事件
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this,username));
    }
}
