package libon;

import java.util.function.Predicate;

public class CoffeeMachine {
    private final DrinkMaker drinkMaker;
    private int coins;

    public CoffeeMachine(DrinkMaker drinkMaker) {
        this.drinkMaker = drinkMaker;
    }
    
    public void process(Order order) {
        String protocolSugar = order.sugars == 0 ? "" : String.valueOf(order.sugars);
        String protocolStick = order.sugars == 0 ? "" : "0";

        drinkMaker.order(Beverage.valueOf(order.drinkType).toProtocol(
                price -> 0 < price - coins,
                coins, protocolSugar, protocolStick));
    }

    public CoffeeMachine insert(int coins) {
        this.coins = coins;
        return this;
    }
    
    enum Beverage {
        CHOCOLATE("H", 50), 
        TEA("T", 40),
        COFFEE("C", 60);
        private final String protocolType;
        private final int price;

        Beverage(String protocolType, int price) {
            this.protocolType = protocolType;
            this.price = price;
        }

        public String toProtocol(Predicate<Integer> enoughCoins, int coins, String protocolSugar, String protocolStick) {
            if(enoughCoins.test(price)) {
                return "M:not enough coins missing " + (price - coins) + " cents";
            }
            return protocolType + ":" + protocolSugar + ":" + protocolStick ;
        }
    }
    
}
