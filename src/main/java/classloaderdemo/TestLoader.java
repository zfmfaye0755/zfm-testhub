package main.java.classloaderdemo;

public class TestLoader {
    public static void main(String[] args){
        //调用class加载器
        ClassLoader clApp = TestLoader.class.getClassLoader();
        System.out.println(clApp);

        //调用上一层Class加载器
        ClassLoader clExt = clApp.getParent();

        System.out.println(clExt);

        //调用根部Class加载器
        ClassLoader clBoot = clExt.getParent();
        System.out.println(clBoot);
    }
}
