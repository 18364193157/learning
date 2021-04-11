package com.lenho.learning.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author langyonghe
 * @date 2021/4/8 10:40
 */
public class UserRegisterEvent extends ApplicationEvent {

    private String username;

    public UserRegisterEvent(Object source) {
        super(source);
    }

    public UserRegisterEvent(Object source,String username) {
        super(source);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
