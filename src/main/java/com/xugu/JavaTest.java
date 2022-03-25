package com.xugu;

class Animal {
    public void eat() {
    }
}

interface hunt {
    public void hunt();
}

class dog extends Animal implements hunt {
    @Override
    public void eat() {
        System.out.println("bone");
    }

    @Override
    public void hunt() {
        System.out.println("annoying");
    }
}

class cat extends Animal implements hunt {
    @Override
    public void eat() {
        System.out.println("fish");
    }

    @Override
    public void hunt() {
        System.out.println("mouse");
    }
}

class TestHunt {
    public void Testeat(Animal animal) {  //不确定的为晚链接
        animal.eat();
    }

    public void Testhunt(hunt hunt) {
        hunt.hunt();
    }
}
interface Func{
    public boolean fun(String str);
}
class Testlamda{
    public void te(Func func){
    return;
    }
}

public class JavaTest {
    public static void main(String args[]) {
//        Animal dog = new dog();
//        Animal cat = new cat();
//
//        TestHunt testHunt = new TestHunt();
//        testHunt.Testeat(dog);
//        testHunt.Testhunt((hunt)dog);
        Testlamda lamda=new Testlamda();
        Func func=s->{
            return  true;
        };
        lamda.te(func);
        lamda.te(s->{return true;});
    }
}