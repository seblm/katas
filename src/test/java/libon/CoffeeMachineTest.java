package libon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineTest {
    
    @Mock
    private DrinkMaker drinkMaker;
    
    @Test
    public void drink_maker_should_receive_correct_instruction_for_chocolate() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);
        
        coffeeMachine.insert(100).process(new Order("chocolate", 0));
        
        verify(drinkMaker).order("H::");
    }
    
    @Test
    public void drink_maker_should_warn_if_not_enough_coins_for_choco() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);
        
        coffeeMachine.insert(30).process(new Order("chocolate", 0));
        
        verify(drinkMaker).order("M:not enough coins missing 20 cents");
        verifyNoMoreInteractions(drinkMaker);
    }

    @Test
    public void drink_maker_should_warn_if_not_enough_coins_for_tea() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);

        coffeeMachine.insert(30).process(new Order("tea", 0));

        verify(drinkMaker).order("M:not enough coins missing 10 cents");
        verifyNoMoreInteractions(drinkMaker);
    }

    @Test
    public void drink_maker_should_receive_correct_instruction_for_chocolate_and_1_sugar() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);
        
        coffeeMachine.insert(100).process(new Order("chocolate", 1));
        
        verify(drinkMaker).order("H:1:0");
    }
    
    @Test
    public void drink_maker_should_receive_correct_instruction_for_several_sugars() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);
        
        coffeeMachine.insert(100).process(new Order("chocolate", 3));
        
        verify(drinkMaker).order("H:3:0");
    }
    
    @Test
    public void drink_maker_should_receive_correct_instruction_when_ordering_tea() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);
        
        coffeeMachine.insert(100).process(new Order("tea", 1));

        verify(drinkMaker).order("T:1:0");
    }
    
    @Test
    public void drink_maker_should_receive_correct_instruction_when_ordering_coffee() {
        CoffeeMachine coffeeMachine = new CoffeeMachine(drinkMaker);
        
        coffeeMachine.insert(100).process(new Order("coffee", 1));
        
        verify(drinkMaker).order("C:1:0");
    }
}
