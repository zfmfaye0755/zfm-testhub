package main.java;

public class TestFC {
    public static void main(String[] args){
        Base b = new Base();
        Thread thread = new Thread();
        thread.start();
        b.f();
        b.g();
    }
}
