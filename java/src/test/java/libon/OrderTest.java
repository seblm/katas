package libon;

import static libon.Beverage.toBeverage;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class OrderTest {

    @Test
    public void should_have_sugar() {
        Order order = new Order(toBeverage("chocolate"), 1, false);

        boolean hasSugar = order.hasSugar();

        assertThat(hasSugar).isTrue();
    }

    @Test
    public void should_not_have_sugar() {
        Order order = new Order(toBeverage("chocolate"), 0, false);

        boolean hasSugar = order.hasSugar();

        assertThat(hasSugar).isFalse();
    }
}