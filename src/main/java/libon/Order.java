package libon;

public class Order {
    final int sugars;
    final String drinkType;

    public Order(String drinkType, int sugars) {
        this.drinkType = drinkType.toUpperCase();
        this.sugars = sugars;
    }
}
