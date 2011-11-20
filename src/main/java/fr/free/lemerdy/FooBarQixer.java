package fr.free.lemerdy;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FooBarQixer {
    
    private static final Map<Integer, String> substitutions = new LinkedHashMap<Integer, String>(3);
    
    private final Integer fooBarQixNumber;
    
    private Boolean hasFooBarQix;
    
    static {
        substitutions.put(3, "Foo");
        substitutions.put(5, "Bar");
        substitutions.put(7, "Qix");
    }
    
    public FooBarQixer(int i) {
        fooBarQixNumber = i;
        hasFooBarQix = false;
    }
    
    private Boolean isDividableBy(Integer i, Integer divider) {
        return i % divider == 0;
    }

    private String toFooBarQix(Integer i) {
        final StringBuilder fooBarQix = new StringBuilder();
        if (i < 10) {
            for (Map.Entry<Integer, String> substitution : substitutions.entrySet()) {
                if (i == substitution.getKey()) {
                    hasFooBarQix = true;
                    fooBarQix.append(substitution.getValue());
                }
            }
        } else {
            final int padUnit = i / 10;
            fooBarQix.append(toFooBarQix(padUnit));
            fooBarQix.append(toFooBarQix(i - padUnit * 10));
        }
        return fooBarQix.toString();
    }

    protected String dividers() {
        StringBuilder out = new StringBuilder();
        for (Map.Entry<Integer, String> substitution : substitutions.entrySet()) {
            if (isDividableBy(fooBarQixNumber, substitution.getKey())) {
                hasFooBarQix = true;
                out.append(substitution.getValue());
            }
        }
        return out.toString();
    }

    protected String substitute() {
        if (!Pattern.matches(".*[357]+.*", fooBarQixNumber.toString())) {
            return "";
        }
        return toFooBarQix(Math.abs(fooBarQixNumber));
    }
    
    public String toString() {
        StringBuilder fooBarQix = new StringBuilder();
        
        fooBarQix.append(dividers());
        fooBarQix.append(substitute());
        
        if (!hasFooBarQix) {
            return fooBarQixNumber.toString();
        }
        hasFooBarQix = false;
        return fooBarQix.toString();
    }
    
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(new FooBarQixer(i).toString());
        }
    }

}
