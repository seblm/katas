package fr.free.lemerdy;



public class FooBarQix {

    protected static Boolean isDividableBy(Integer i, Integer divider) {
        return i % divider == 0;
    }

    protected static Boolean contains3(Integer i) {
        return i.toString().contains("3");
    }
    
    protected static Boolean contains5(Integer i) {
        return i.toString().contains("5");
    }

    public static String fooBarQix(Integer i) {
        StringBuilder fooBarQix = new StringBuilder();
        if (isDividableBy(i, 3)) {
            fooBarQix.append("Foo");
        }
        if (contains3(i)) {
            fooBarQix.append("Foo");
        }
        if (isDividableBy(i, 5)) {
            fooBarQix.append("Bar");
        }
        if (i == 5) {
            fooBarQix.append("Bar");
        }
        if (i == 7) {
            fooBarQix.append("QixQix");
        }
        if (fooBarQix.length() == 0) {
            return i.toString();
        }
        return fooBarQix.toString();
    }

}
