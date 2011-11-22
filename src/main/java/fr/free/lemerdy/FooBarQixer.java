package fr.free.lemerdy;

import static java.lang.Math.abs;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FooBarQixer {

    private static final Map<Integer, String> orderedSubstitutions = new LinkedHashMap<Integer, String>(3);

    private final Integer fooBarQixNumber;

    private Boolean shouldBeFooBarQixed = false;

    static {
        orderedSubstitutions.put(3, "Foo");
        orderedSubstitutions.put(5, "Bar");
        orderedSubstitutions.put(7, "Qix");
    }

    public FooBarQixer(int i) {
        fooBarQixNumber = i;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(new FooBarQixer(i).toString());
        }
    }

    public String toString() {
        StringBuilder fooBarQixBuffer = new StringBuilder();

        fooBarQixBuffer.append(substitutedWhenDivideSpecialNumbers());
        fooBarQixBuffer.append(substitutedWhenSpecialNumbersOccurs());

        if (!shouldBeFooBarQixed) {
            return fooBarQixNumber.toString();
        }
        shouldBeFooBarQixed = false;
        return fooBarQixBuffer.toString();
    }

    protected String substitutedWhenDivideSpecialNumbers() {
        StringBuilder foobarQixBuffer = new StringBuilder();
        for (Map.Entry<Integer, String> substitution : orderedSubstitutions.entrySet()) {
            if (fooBarQixNumber % substitution.getKey() == 0) {
                shouldBeFooBarQixed = true;
                foobarQixBuffer.append(substitution.getValue());
            }
        }
        return foobarQixBuffer.toString();
    }

    protected String substitutedWhenSpecialNumbersOccurs() {
        if (!Pattern.matches(".*[357]+.*", fooBarQixNumber.toString())) {
            return "";
        }
        return toFooBarQix(abs(fooBarQixNumber));
    }

    private String toFooBarQix(Integer digit) {
        if (digit < 10) {
            final StringBuilder fooBarQixBuffer = new StringBuilder();
            for (Map.Entry<Integer, String> substitution : orderedSubstitutions.entrySet()) {
                if (digit == substitution.getKey()) {
                    shouldBeFooBarQixed = true;
                    fooBarQixBuffer.append(substitution.getValue());
                }
            }
            return fooBarQixBuffer.toString();
        } else {
            final int padUnit = digit / 10;
            return toFooBarQix(padUnit) + toFooBarQix(digit - padUnit * 10);
        }
    }

}
