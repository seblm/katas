package libon;

import static libon.Beverage.toBeverage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineTest {
    
    @Mock
    private DrinkMaker drinkMaker;
    
    @Test
    public void drink_maker_should_receive_correct_instruction_for_chocolate() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, new PriceReporter());
        
        coffeeMachine.insert(100).process(new Order(toBeverage("chocolate"), 0, false));
        
        verify(drinkMaker).order("H::");
    }
    
    @Test
    public void drink_maker_should_warn_if_not_enough_coins_for_choco() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, new PriceReporter());
        
        coffeeMachine.insert(30).process(new Order(toBeverage("chocolate"), 0, false));
        
        verify(drinkMaker).order("M:not enough coins missing 20 cents");
        verifyNoMoreInteractions(drinkMaker);
    }

    @Test
    public void drink_maker_should_warn_if_not_enough_coins_for_tea() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, new PriceReporter());

        coffeeMachine.insert(30).process(new Order(toBeverage("tea"), 0, false));

        verify(drinkMaker).order("M:not enough coins missing 10 cents");
        verifyNoMoreInteractions(drinkMaker);
    }

    @Test
    public void drink_maker_should_receive_correct_instruction_for_chocolate_and_1_sugar() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, new PriceReporter());
        
        coffeeMachine.insert(100).process(new Order(toBeverage("chocolate"), 1, false));
        
        verify(drinkMaker).order("H:1:0");
    }
    
    @Test
    public void drink_maker_should_receive_correct_instruction_for_several_sugars() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, new PriceReporter());
        
        coffeeMachine.insert(100).process(new Order(toBeverage("chocolate"), 3, false));
        
        verify(drinkMaker).order("H:3:0");
    }
    
    @Test
    public void drink_maker_should_receive_correct_instruction_when_ordering_tea() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, new PriceReporter());
        
        coffeeMachine.insert(100).process(new Order(toBeverage("tea"), 1, false));

        verify(drinkMaker).order("T:1:0");
    }
    
    @Test
    public void drink_maker_should_receive_correct_instruction_when_ordering_coffee() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, new PriceReporter());
        
        coffeeMachine.insert(100).process(new Order(toBeverage("coffee"), 1, false));
        
        verify(drinkMaker).order("C:1:0");
    }

    @Test
    public void drink_maker_should_receive_correct_instruction_when_ordering_orange_juice() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, new PriceReporter());

        coffeeMachine.insert(100).process(new Order(toBeverage("orange juice"), 1, false));

        verify(drinkMaker).order("O:1:0");
    }

    @Test
    public void drink_maker_should_warn_if_not_enough_coins_for_orange_juice() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, new PriceReporter());

        coffeeMachine.insert(40).process(new Order(toBeverage("orange juice"), 0, false));

        verify(drinkMaker).order("M:not enough coins missing 20 cents");
        verifyNoMoreInteractions(drinkMaker);
    }

    @Test
    public void should_order_extra_hot_coffee() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, new PriceReporter());

        coffeeMachine.insert(100).process(new Order(toBeverage("coffee"), 0, true));

        verify(drinkMaker).order("Ch::");
        verifyNoMoreInteractions(drinkMaker);
    }

    @Test
    public void should_not_order_an_extra_hot_orange_juice() throws Exception {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker, new PriceReporter());
        coffeeMachine.insert(60).process(new Order(toBeverage("orange juice"), 0, true));

        verify(drinkMaker).order("O::");
    }

}
