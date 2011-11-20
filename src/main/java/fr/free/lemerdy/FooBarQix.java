package fr.free.lemerdy;



public class FooBarQix {

    public static Boolean is3Dividable(int i) {
        return i % 3 == 0;
    }

    public static Boolean contains3(int i) {
        return new Integer(i).toString().contains("3");
    }

    public static String fooBarQix(Integer i) {
        if (i == 1 || i == 2) {
            return new Integer(i).toString();
        }
        return "FooFoo";
    }

}
