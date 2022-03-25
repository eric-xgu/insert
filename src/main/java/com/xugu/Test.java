package com.xugu;

class MyThread implements Runnable {
    private Trans trans;
    private int num;

    public MyThread(Trans trans, int num) {
        this.trans = trans;
        this.num = num;
    }

    public void run() {
        while (true)
        {
            trans.printNum(num);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class Test {


    public static void main(String[] args) {

        Trans t = new Trans();
        Trans t1 = new Trans();
        Thread a = new Thread(new MyThread(t, 1));
        Thread b = new Thread(new MyThread(t1, 2));

        a.start();
        b.start();

    }

}