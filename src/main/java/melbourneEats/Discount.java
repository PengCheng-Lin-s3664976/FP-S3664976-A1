package melbourneEats;

public class Discount {
    private int min;
    private int max;
    private int disRate;

    public Discount(int min, int max, int disRate) {
        this.min = min;
        this.max = max;
        this.disRate = disRate;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getDisRate() {
        return disRate;
    }
}
