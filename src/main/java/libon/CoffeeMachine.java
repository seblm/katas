package libon;

public class CoffeeMachine {
    private final DrinkMaker drinkMaker;
    private int coins;

    public CoffeeMachine(DrinkMaker drinkMaker) {
        this.drinkMaker = drinkMaker;
    }

    public void process(Order order) {
        int price = order.beverage.price;
        if (price - coins > 0) {
            drinkMaker.order("M:not enough coins missing " + (price - coins) + " cents");
        } else {
            drinkMaker.order(order.beverage.toProtocol(coins, order));
        }
    }

    public CoffeeMachine insert(int coins) {
        this.coins = coins;
        return this;
    }

}
