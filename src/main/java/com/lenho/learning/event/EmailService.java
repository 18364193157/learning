package com.lenho.learning.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author langyonghe
 * @date 2021/4/8 10:46
 */
@Slf4j
@Service
public class EmailService implements ApplicationListener<UserRegisterEvent> {


    @Override
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        log.info("EmailService 给用户{}发送邮件", userRegisterEvent.getUsername());
    }
}
