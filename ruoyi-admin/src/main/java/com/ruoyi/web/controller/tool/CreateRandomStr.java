package com.ruoyi.web.controller.tool;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * 描述: Java 随机生成字符串
 *
 * @author: yanglin
 * @Date: 2020-11-19-8:36
 * @Version: 1.0
 */
public class CreateRandomStr {

    /**
     * 1.生成的字符串每个位置都有可能是str中的一个字母或数字，需要导入的包是import java.util.Random;
     * @param length
     * @return
     */
    public static String createRandomStr1(int length){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }


    /**
     * 2.可以指定某个位置是a-z、A-Z或是0-9，需要导入的包是import java.util.Random;
     * @param length
     * @return
     */
    public static String createRandomStr2(int length){
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(3);
            long result = 0;
            switch (number) {
                case 0:
                    result = Math.round(Math.random()*25+65);
                    stringBuffer.append(String.valueOf((char)result));
                    break;
                case 1:
                    result = Math.round(Math.random()*25+97);
                    stringBuffer.append(String.valueOf((char)result));
                    break;
                case 2:
                    stringBuffer.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 3.org.apache.commons.lang包下有一个RandomStringUtils类，
     * 其中有一个randomAlphanumeric(int length)函数，可以随机生成一个长度为length的字符串。
     * @param length
     * @return
     */
    public static String createRandomStr3(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }


    public static void main(String[] args) {
        System.out.println("---------------------------------------");
        System.out.println(createRandomStr1(16));
        System.out.println("---------------------------------------");
        System.out.println(createRandomStr2(16));
        System.out.println("---------------------------------------");
        System.out.println(createRandomStr3(16));
    }
}

