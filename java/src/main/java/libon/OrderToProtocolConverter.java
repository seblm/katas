package libon;

public class OrderToProtocolConverter {

    public static String toProtocol(Order order) {
        return getProtocolSugar(order) + ":" + getProtocolStick(order);
    }

    private static String getProtocolStick(Order order) {
        return order.hasSugar() ? "0" : "";
    }

    private static String getProtocolSugar(Order order) {
        return order.hasSugar() ? String.valueOf(order.sugars) : "";
    }
}
