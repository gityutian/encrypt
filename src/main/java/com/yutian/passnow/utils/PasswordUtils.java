package com.yutian.passnow.utils;

import java.util.Random;

public class PasswordUtils {

    public static char getRandom(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return (char) s;

    }

    public static String generatePassword(int length){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        //安全度最高的 大写小写特殊字符数字
        builder.append(getRandom(48,57));
        builder.append(getRandom(65,90));
        builder.append(getRandom(97,122));
        int drandom = random.nextInt(3)+1;
        if(drandom == 1){
            builder.append(getRandom(33,47));
        }else if(drandom == 1){
            builder.append(getRandom(58,64));
        }else{
            builder.append(getRandom(123,126));
        }
        for(int i = 0; i<length-4; i++){
            builder.append(getRandom(33,126));
        }
        return shuffle3(builder.toString());
    }

    // 高效，约为shuffle1的一成时间
    public static String shuffle3(String original){
        char[] arr=original.toCharArray();
        Random rnd=new Random();
        char tmp;
        int j;
        for(int i=arr.length;i>1;i--){
            j=rnd.nextInt(i);
            tmp=arr[i-1];
            arr[i-1]=arr[j];
            arr[j]=tmp;
        }
        return String.valueOf(arr);
    }
}