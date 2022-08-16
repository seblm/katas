package name.lemerdy;

import java.math.BigDecimal;

public record Money(String amount, String currency) {

    public Money add(Money money) {
        return new Money(new BigDecimal(amount).add(new BigDecimal(money.amount)).toString(), currency);
    }

}
