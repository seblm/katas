package libon;

public class Order {
    final int sugars;
    private boolean extraHot;
    final Beverage beverage;

    public Order(Beverage beverage, int sugars, boolean extraHot) {
        this.beverage = beverage;
        this.sugars = sugars;
        this.extraHot = extraHot;
    }

    public boolean hasSugar() {
        return sugars > 0;
    }

    public boolean isExtraHot() {
        return extraHot;
    }
}
