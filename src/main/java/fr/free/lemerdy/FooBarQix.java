package fr.free.lemerdy;



public class FooBarQix {

    protected static Boolean isDividableBy(Integer i, Integer divider) {
        return i % divider == 0;
    }

    protected static Boolean contains(Integer i, Integer pattern) {
        return i.toString().contains(pattern.toString());
    }

    public static String fooBarQix(Integer i) {
        StringBuilder fooBarQix = new StringBuilder();
        
        if (isDividableBy(i, 3)) {
            fooBarQix.append("Foo");
        }
        if (isDividableBy(i, 5)) {
            fooBarQix.append("Bar");
        }
        if (isDividableBy(i, 7)) {
            fooBarQix.append("Qix");
        }
        
        if (contains(i, 3)) {
            fooBarQix.append("Foo");
        }
        if (contains(i, 5)) {
            fooBarQix.append("Bar");
        }
        if (contains(i, 7)) {
            fooBarQix.append("Qix");
        }
        
        if (fooBarQix.length() == 0) {
            return i.toString();
        }
        return fooBarQix.toString();
    }

}
