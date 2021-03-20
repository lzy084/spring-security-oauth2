package com.dd.order.common;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class GeneChar {
    public static int getNumRandom(){
        int max=10000,min=1;
        long randomNum = System.currentTimeMillis();
        int ran3 = (int) (randomNum%(max-min)+min);
        return ran3;
    }
    public static String getChar(){
        StringBuilder  stringBuilder=new StringBuilder();
        for (int i = 1; i < 4; i++) {
            stringBuilder.append(getRandomChar());
        }
        return stringBuilder.toString();
    }
    private static char getRandomChar() {
        String str = "";
        int hightPos; //
        int lowPos;
        Random random = new Random();
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }
        return str.charAt(0);
    }
}
