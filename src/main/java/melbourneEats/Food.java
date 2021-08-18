package melbourneEats;


public class Food {
    private String name;
    private double price;

    public Food(String name, double price) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("food name is empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("food price < 0");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
