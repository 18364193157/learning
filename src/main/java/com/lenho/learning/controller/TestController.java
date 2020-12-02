package com.lenho.learning.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

/**
 * @author langyonghe
 * @date 2020/10/28 15:29
 */
@RestController
@RequestMapping("/learn")
public class TestController {


    @ResponseBody
    @PostMapping("/test")
    public String test(@RequestBody String param) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(param);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            String value = request.getHeader(s);
            System.out.println(s + " : "+ value);
        }
        System.out.println(param);
        return param;
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

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest("password1234".getBytes("utf-8"));
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr=base64en.encode(digest);
        System.out.println(newstr);
    }
}
