package main.java;

public class Derived extends Base {

    public Derived(){

    }
    @Override
public void f() {
//        super.f();
        System.out.println("Derived f()方法");
    }

    @Override
    public void g() {
//        super.f();
        System.out.println("子类 g()方法");



    }
}
