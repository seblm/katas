package libon;

import static libon.Beverage.toBeverage;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineReportingAT {

    @Mock
    private DrinkMaker drinkMaker;

    @Mock
    private PriceReporter reporter;

    @InjectMocks
    private CoffeeMachine coffeeMachine;

    @Test
    public void should_record_orders_in_the_analytics_reporter() {
        Order order = new Order(toBeverage("chocolate"), 0, false);

        coffeeMachine.insert(100).process(order);

        verify(reporter).record(order);
    }

    @Test
    public void should_not_record_order_if_order_is_impossible() {
        Order order = new Order(toBeverage("chocolate"), 0, false);

        coffeeMachine.insert(30).process(order);

        verify(reporter, never()).record(order);
    }

}
