package melbourneEats;

import java.util.ArrayList;


public class Restaurant implements MelbourneEatsPackage {

    private String name;
    private String category;
    private Double delivery_fee;
    private ArrayList<Food> list;

    public Restaurant(String name, String category, Double delivery_fee, ArrayList<Food> list) {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("restaurant Name is empty");
        }
        if (category == null) {
            throw new IllegalArgumentException("food name is empty");
        }
        if (delivery_fee == null) {
            throw new IllegalArgumentException("food List is empty");
        }
        if (list == null) {
            throw new IllegalArgumentException("food Amounts is empty");
        }
        this.name = name;
        this.category = category;
        this.delivery_fee = delivery_fee;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getDelivery_fee() {
        return delivery_fee;
    }

    public ArrayList<Food> getList() {
        return list;
    }

    public boolean addFood(Food food) {
        if (list.contains(food)) {
            return false;
        }
        list.add(food);
        return true;
    }
}
