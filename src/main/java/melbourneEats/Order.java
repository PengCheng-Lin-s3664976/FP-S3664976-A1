package melbourneEats;

import java.util.Map;

public class Order {
    private String restaurantName;
    private Map<String, Double> foodList;
    private Map<String, Double> foodAmounts;
    private Double deliveryFee;


    public Order(String restaurantName, Map<String, Double> foodList, Map<String, Double> foodAmounts, Double deliveryFee) {
        if (restaurantName == null || restaurantName.isEmpty() || restaurantName.isBlank()) {
            throw new IllegalArgumentException("restaurant Name is empty");
        }
        if (foodList == null) {
            throw new IllegalArgumentException("food name is empty");
        }
        if (foodAmounts == null) {
            throw new IllegalArgumentException("food Amounts is empty");
        }
        if (deliveryFee < 0) {
            throw new IllegalArgumentException("delivery Fee is 0");
        }
        this.restaurantName = restaurantName;
        this.foodList = foodList;
        this.foodAmounts = foodAmounts;
        this.deliveryFee = deliveryFee;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Map<String, Double> getFoodList() {
        return foodList;
    }

    public Map<String, Double> getFoodAmounts() {
        return foodAmounts;
    }

    //other way to get Amounts
//    public Double getFoodAmountsValue(Double amounts) {
//        for (Map.Entry<String, Double> entry : foodAmounts.entrySet()) {
//            if (amounts == entry.getValue()) {
//                return amounts;
//            }
//        }
//        return null;
//    }

    public boolean addFood(String foodName, Double price) {
        if (foodList.containsKey(foodName)) {
            return false;
        }
        foodList.put(foodName, price);
        return true;
    }

    public boolean addFoodAmount(String foodName, Double amounts) {
        if (foodAmounts.containsKey(foodName)) {
            for (Map.Entry<String, Double> entry : foodAmounts.entrySet()) {
                if (foodName.equals(entry.getKey())) {
                    foodAmounts.put(foodName, amounts + entry.getValue());
                    return true;
                }
            }
            return false;
        }else {
            foodAmounts.put(foodName, amounts);
            return true;
        }
    }

    public double allFoodCost(){
        double cost = 0;
        for (Map.Entry<String, Double> map : foodList.entrySet()) {
            if (foodAmounts.containsKey(map.getKey())) {
                cost += map.getValue() * foodAmounts.get(map.getKey()).intValue();
            }
        }
        return cost;
    }
}
