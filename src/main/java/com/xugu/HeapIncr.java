package com.xugu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HeapIncr {
    public void A(){

    }
    byte [] buffer=new byte[new Random().nextInt(1024*1024)];

    public static void main(String[] args) {
        List<HeapIncr> list = new ArrayList <> ();
        while(true){
            HeapIncr hi=new HeapIncr();
            list.add(hi);
            try {
                Thread.sleep(500);
            }catch (Exception e){

            }
        }
    }

}
