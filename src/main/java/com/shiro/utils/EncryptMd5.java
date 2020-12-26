package com.shiro.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @ClassName EncryptMd5
 * @Description MD5密码加密
 * @Author gaopeng
 * @Date 2020/12/26 13:11
 * @Version 1.0
 **/

public class EncryptMd5 {


    /**
      * @Author gaopeng
      * @Description
      * @Date 13:15 2020/12/26
      * @param algorithmName  加密算法
      * @param source 明文密码
      * @param salt 盐值
      * @param hashIterations 散列次数
      * @return java.lang.String
      **/
    public static String passWordEncode (String algorithmName, String source, ByteSource salt, int hashIterations){
        SimpleHash simpleHash = new SimpleHash(algorithmName, source, salt, hashIterations);
        return simpleHash.toString();

    }


}
