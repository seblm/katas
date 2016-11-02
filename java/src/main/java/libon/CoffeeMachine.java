package libon;

public class CoffeeMachine {
    private final DrinkMaker drinkMaker;
    private PriceReporter priceReporter;
    private int coins;

    public CoffeeMachine(DrinkMaker drinkMaker, PriceReporter priceReporter) {
        this.drinkMaker = drinkMaker;
        this.priceReporter = priceReporter;
    }

    public CoffeeMachine process(Order order) {
        int price = order.beverage.price;
        if (price - coins > 0) {
            drinkMaker.order("M:not enough coins missing " + (price - coins) + " cents");
        } else {
            drinkMaker.order(order.beverage.toProtocol(coins, order));
            priceReporter.record(order);
        }
        return this;
    }

    public CoffeeMachine insert(int coins) {
        this.coins = coins;
        return this;
    }

}
