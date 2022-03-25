package com.xugu;

public class Trans {

    public static synchronized void printNum(int num){
        System.out.print(Thread.currentThread());
        for(int i=0;i<25;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        try {
            Thread.sleep(10000000);
        }catch ( Exception e){

        }
        }
}