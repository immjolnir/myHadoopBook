package com.myhp.basicUtil.security;

import com.myhp.basicUtil.io.StreamTokenizerShow;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 计算给定的字节流的 MD5 值. 给出字节数据或是十六进制字串或字符串类型的源数据 以静态方式提供,不需要实例化
 * </p>
 * MD5加密算法
 */
public class MD5inAction {

    public static String MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        char[] chars = inStr.toCharArray();
        byte[] bytes = new byte[chars.length];

        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) chars[i];
        }

        byte[] md5Bytes = md5.digest(bytes);
        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = md5Bytes[i] & 0xff;
            if (val < 16) hexValue.append('0');

            hexValue.append(Integer.toHexString(val));
        }


        return hexValue.toString();
    }


    //可逆的加密算法
    public static String enCoder(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }

    // 加密后解密
    public static String deCoder(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String k = new String(a);
        return k;
    }

    public static void main(String args[]) {
        String s = new String("123456");
        System.out.println("Original: " + s);
        System.out.println("After MD5 coded: " + MD5(s));
        System.out.println("enCoder MD5 result: " + enCoder(MD5(s)));
        System.out.println("Decoder md5 result: " + deCoder(enCoder(MD5(s))));
        System.out.println(2^4^4);
    }


}
