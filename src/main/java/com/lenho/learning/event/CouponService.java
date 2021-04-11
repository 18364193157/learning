package com.lenho.learning.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author langyonghe
 * @date 2021/4/8 10:52
 */
@Slf4j
@Service
public class CouponService {


    @EventListener
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        log.info("CouponService 给用户{}发送优惠券", userRegisterEvent.getUsername());
    }
}
