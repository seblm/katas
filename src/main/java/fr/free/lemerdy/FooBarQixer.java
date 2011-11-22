package fr.free.lemerdy;

import static java.lang.Math.abs;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FooBarQixer {

    private static final Map<Integer, String> ORDERED_SUBSTITTIONS = new LinkedHashMap<Integer, String>(3);
    
    private static final Pattern HAS_SPECIAL_NUMBERS = Pattern.compile(".*[357]+.*");

    private final Integer fooBarQixNumber;

    private Boolean shouldBeFooBarQixed = false;

    static {
        ORDERED_SUBSTITTIONS.put(3, "Foo");
        ORDERED_SUBSTITTIONS.put(5, "Bar");
        ORDERED_SUBSTITTIONS.put(7, "Qix");
    }

    public FooBarQixer(int i) {
        fooBarQixNumber = i;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(new FooBarQixer(i).toString());
        }
    }

    @Override
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
        for (Map.Entry<Integer, String> substitution : ORDERED_SUBSTITTIONS.entrySet()) {
            if (fooBarQixNumber % substitution.getKey() == 0) {
                shouldBeFooBarQixed = true;
                foobarQixBuffer.append(substitution.getValue());
            }
        }
        return foobarQixBuffer.toString();
    }

    protected String substitutedWhenSpecialNumbersOccurs() {
        if (!HAS_SPECIAL_NUMBERS.matcher(fooBarQixNumber.toString()).matches()) {
            return "";
        }
        return toFooBarQix(abs(fooBarQixNumber));
    }

    private String toFooBarQix(Integer digit) {
        if (digit < 10) {
            final StringBuilder fooBarQixBuffer = new StringBuilder();
            for (Map.Entry<Integer, String> substitution : ORDERED_SUBSTITTIONS.entrySet()) {
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
