package com.lenho.learning.controller;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.Map;

/**
 * adsfasdfa
 * @author langyonghe
 * @date 2020/10/28 15:29
 */
@RestController
@RequestMapping("/learn")
public class TestController {
    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @PostMapping("/post")
    public String test( @RequestBody Map<String, String> parameters) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = request.getParameter("userId");
        System.out.println(userId);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            String value = request.getHeader(s);
            System.out.println(s + " : "+ value);
        }
        System.out.println(parameters);
        return "12";
    }

    @RequestMapping("/first")
    public ModelAndView showEmp(){
        ModelAndView mav=new ModelAndView("/stack");
        return mav;
    }

    @GetMapping("/get")
    public String testGet(@RequestParam("param") String param) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = request.getParameter("userId");
        System.out.println(userId);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            String value = request.getHeader(s);
            System.out.println(s + " : "+ value);
        }
        System.out.println(234);
        System.gc();

        return "sfggs";
    }

    public String EncodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        /**
         * 经过md5后的字符数组中含有负数元素，因此如果将这个字符数组进行转换成字符串，查询GB2312或者UTF-8都是不合适的会出现乱码,然后再用base64编码为32位字符串
         *      避免乱码;易于存储到数据库
         */
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    public Boolean checkPassowrd(String newPassword,String oldPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        boolean equals = EncodeByMD5(newPassword).equals("vch7nIlNpRaAWeAOv/uQdw==");
        if(equals){
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest("password1234".getBytes("utf-8"));
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr=base64en.encode(digest);
        System.out.println(newstr);
    }
}
