package com.lenho.learning.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author langyonghe
 * @date 2021/2/3 10:27
 */
@RestController
@RequestMapping("/learn")
public class LenhoController {


    @GetMapping("/test/many")
    public String testMany(){
        return "testmany";
    }


    private final Semaphore permit = new Semaphore(10, true);
    private static int count;
    @PostMapping("/test")
    public String test(){
        try {
            permit.acquire();
            log.info("处理请求===============>" + count++);

            Thread.sleep(2000);
        }catch (Exception e){
            log.info("处理请求失败===============>");
            log.error("error");
        }finally {
            permit.release();
            log.info(String.valueOf(count));
        }
        return "success";
    }




    private final RateLimiter limiter = RateLimiter.create(5.0);
    private static final Logger log = LoggerFactory.getLogger(LenhoController.class);

    @PostMapping("/test2")
    public String test2(){
        try {
            boolean flag = limiter.tryAcquire(1,3, TimeUnit.SECONDS);
            if (flag){
                log.info("处理请求===============>");
                Thread.sleep(1000);
            }else {
                return "系统繁忙！";
            }

        }catch (Exception e){
            log.error("error");
        }
        return "success";
    }
}
