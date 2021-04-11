package com.lenho.learning.controller;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import java.sql.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
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

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverName;

    @RequestMapping("/first")
    public String showEmp() throws SQLException {
//        ModelAndView mav=new ModelAndView("/stack");
        Connection connection = getConnection(url, "root", "123456", driverName);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SHOW VARIABLES LIKE '%explicit_defaults_for_timestamp%'");
        while (resultSet.next()){
            String str = resultSet.getString("Value");
            System.out.println(str);
        }

        //解决timestamp为空的问题
//        Boolean delDefaultNull = Boolean.FALSE;
//        ResultSet resultSet = st.executeQuery("SHOW VARIABLES LIKE '%explicit_defaults_for_timestamp%'");
//        if (resultSet.next()) {
//            String str = resultSet.getString("Value");
//            if (StringUtils.isNotBlank(str) && Objects.equals("ON", str)) {
//                delDefaultNull = Boolean.TRUE;
//            }
//        }
//  解决sql int(10) unsigned 的问题
//        if(!colunmType.includes("unsigned")){
//            sql += data[i].colunmName + space + data[i].colunmType + "(" + data[i].colunmSize + ") ";
//        }else {
//            let colunmType1 = data[i].colunmType.replace("UNSIGNED","");
//            sql += data[i].colunmName + space + colunmType1 + "(" + data[i].colunmSize + ") " + "UNSIGNED";
//        }


        DatabaseMetaData metaData = connection.getMetaData();
        String databaseProductVersion = metaData.getDatabaseProductVersion();

        System.out.println(databaseProductVersion);
        return databaseProductVersion;
    }

    public  Connection getConnection(String url, String user, String password, String driverName) throws SQLException {

        Driver driver;
        try {
            Class driverClass = Class.forName(driverName);
            driver = (Driver) driverClass.newInstance();
        } catch (Exception e) {
            throw new SQLException(e);
        }
        Properties props = new Properties();
        if (user != null) {
            props.put("user", user);

        }
        if (password != null) {
            props.put("password", password);
        }
        return driver.connect(url, props);
    }

    @GetMapping("/get")
    public String testGet() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            System.out.println(paramName + "=" + paramValue);
        }
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()){
//            String s = headerNames.nextElement();
//            String value = request.getHeader(s);
//            System.out.println(s + " : "+ value);
//        }
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
