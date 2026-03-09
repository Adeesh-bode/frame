package org.designpattern.singleton;

class Singleton{
    private Singleton(){};


    private static class InnerClass{
        // Instance created in innerclass
        private static Singleton INSTANCE= new Singleton();
    }

    public static Singleton getInstance(){
        return InnerClass.INSTANCE;
    }
}

public class StaticInnerClass {
    public static void main(String[] args){
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);
    }
}
