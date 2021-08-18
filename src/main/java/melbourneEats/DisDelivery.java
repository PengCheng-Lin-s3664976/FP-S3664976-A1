package melbourneEats;

public class DisDelivery {
    private int orderAmount;
    private int disRate;

    public DisDelivery(int orderAmount, int disRate) {
        this.orderAmount = orderAmount;
        this.disRate = disRate;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public int getDisRate() {
        return disRate;
    }
}
