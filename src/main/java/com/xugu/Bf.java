package com.xugu;

import static java.lang.Thread.sleep;

public class Bf {

    static  int method1(StringBuffer sb)throws Exception{
        sb.append('a');
        sb.append('e');
        sb.append('c');
        sleep(500);
        sb.append('c');
        sb.append('u');
        sb.append('u');
        return 0;
    }
    static  int method2(StringBuilder sb)throws Exception{
        sb.append('a');
        sb.append('e');
        sb.append('c');
        sleep(500);
        sb.append('c');
        sb.append('u');
        sb.append('u');
        return 0;
    }

    public static void main(String[] args) {
        StringBuffer sb=new StringBuffer();
        StringBuilder sb1=new StringBuilder();
        try{
        new Thread(()->{
            sb.append('a');
            sb.append('f');
            sb.append('d');
            try {
                sleep(500);
            }catch(Exception e){

            }
            sb.append('d');
            sb.append('k');
            sb.append('k');
        }).start();
        method1(sb);
        new Thread(()->{
                    sb1.append('a');
                    sb1.append('f');
                    sb1.append('d');
                    try {
                        sleep(500);
                    }catch(Exception e){

                    }
                    sb1.append('d');
                    sb1.append('k');
                    sb1.append('k');
                }).start();
        method2(sb1);
        sleep(1000);}catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(sb);
        System.out.println(sb1);
    }
}
