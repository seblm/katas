package libon;

enum Beverage {
    CHOCOLATE("chocolate", "H", 50, true),
    TEA("tea", "T", 40, true),
    COFFEE("coffee", "C", 60, true),
    ORANGE_JUICE("orange juice", "O", 60, false);

    public final int price;
    private final String label;
    private final String protocolType;
    private final boolean warmable;

    Beverage(String label, String protocolType, int price, boolean warmable) {
        this.label = label;
        this.protocolType = protocolType;
        this.price = price;
        this.warmable = warmable;
    }

    public String toProtocol(int coins, Order order) {
        StringBuilder protocol = new StringBuilder(protocolType);
        if(order.isExtraHot() && order.beverage.warmable){
            protocol.append("h");
        }
        protocol.append(":").append(OrderToProtocolConverter.toProtocol(order));
        return protocol.toString();
    }

    public static final Beverage toBeverage(String label) {
        for (Beverage beverage : values()) {
            if (beverage.label.equals(label)) {
                return beverage;
            }
        }
        throw new IllegalArgumentException("no enum value found for " + label);
    }

}
