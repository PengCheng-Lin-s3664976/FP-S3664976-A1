package melbourneEats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MelbourneEatsSystem {

    ArrayList<MelbourneEatsPackage> restaurantList = new ArrayList<>();
    ArrayList<Order> order = new ArrayList<>();
    ArrayList<Discount> disFood = new ArrayList<>();
    ArrayList<DisDelivery> disDelivery = new ArrayList<>();

    public MelbourneEatsSystem() {
        readRestaurants();
        readDiscounts();
    }

    public ArrayList<Order> getOrder() {
        return order;
    }

    public ArrayList<MelbourneEatsPackage> getRestaurantList() {
        return restaurantList;
    }

    public boolean addRestaurant(MelbourneEatsPackage bayPackage) {
        for (MelbourneEatsPackage i : restaurantList) {
            if (i.getName().equals(bayPackage.getName())) {
                return false;
            }
        }
        restaurantList.add(bayPackage);
        return true;
    }

    public boolean addOrder(Order singleOrder) {
        if (order.size() != 0) {
            for (Order elem : order) {
                //if you want same name
                if (singleOrder.getRestaurantName().equals(elem.getRestaurantName())) {
                    // user want
                    Map<String, Double> foodOder = new HashMap<>(singleOrder.getFoodList());
                    Map<String, Double> foodOderAmounts = new HashMap<>(singleOrder.getFoodAmounts());
                    // user wanted
                    Map<String, Double> foodOderWanted = new HashMap<>(elem.getFoodList());
                    Map<String, Double> foodOderAmountsWanted = new HashMap<>(elem.getFoodAmounts());
                    // Override food price
                    foodOderWanted.putAll(foodOder);
                    //if you want same food
                    for (Map.Entry<String, Double> entry : foodOderAmounts.entrySet()) {
                        if (foodOderAmountsWanted.containsKey(entry.getKey())) {
                            foodOderAmountsWanted.put(entry.getKey(),
                                    foodOderAmountsWanted.get(entry.getKey()) + entry.getValue());
                        } else {
                            foodOderAmountsWanted.put(entry.getKey(), entry.getValue());
                        }
                    }

                    order.add(new Order(singleOrder.getRestaurantName(),
                            foodOderWanted, foodOderAmountsWanted, singleOrder.getDeliveryFee()));
                    order.remove(elem);
                    return true;
                }
            }
        }
        order.add(singleOrder);
        return true;
    }


    public boolean addDis(Discount discount) {
        if (discount != null) {
            disFood.add(discount);
            return true;
        }
        return false;
    }

    public boolean addDiscountDelivery(DisDelivery discount) {
        if (discount != null) {
            disDelivery.add(discount);
            return true;
        }
        return false;
    }

    public double allOrderFoodCost() {
        double cost = 0;
        for (Order list : order) {
            cost += list.allFoodCost();
        }
        return cost;
    }

    public double allOrderFoodDiscountsDeliveryCost() {
        double cost = 0;
        for (DisDelivery list : disDelivery) {
            if (order.size() >= list.getOrderAmount()) {
                for (Order i : order) {
                    cost += i.getDeliveryFee();
                }
                cost *= (list.getDisRate() * 0.01);
            } else {
                for (Order i : order) {
                    cost += i.getDeliveryFee();
                }
            }
        }
        return cost;
    }

    public double allOrderFoodDeliveryCost() {
        double cost = 0;
                for (Order i : order) {
                    cost += i.getDeliveryFee();
                }

        return cost;
    }

    public double allOrderFoodDiscountsCost() {
        double cost = 0;
        for (Discount list : disFood) {
            if (allOrderFoodCost() >= list.getMin() && allOrderFoodCost() < list.getMax()) {
                cost = allOrderFoodCost() - allOrderFoodCost() * (list.getDisRate() * 0.01);
            }
        }
        return cost;
    }


    private void readDiscounts() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("Discounts.txt"));
            String s;
            while ((s = reader.readLine()) != null) {
                String[] split = s.split("[\\[,)%]");
                if (split.length < 3) {
                    addDiscountDelivery(new DisDelivery(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                } else if (split[2].isBlank()) {
                    addDis(new Discount(Integer.parseInt(split[1]), 999, Integer.parseInt(split[4])));
                } else {
                    addDis(new Discount(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[4])));
                }
            }
        } catch (Exception e) {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception ignored) {

            }
        }
    }

    private void readRestaurants() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("Restaurants.txt"));
            String s;
            while ((s = reader.readLine()) != null) {
                String[] split = s.split("[,\\-$]");

                ArrayList<Food> list1 = new ArrayList<>();

                for (int i = 4; i < split.length; i++) {
                    String FoodName = split[i++];
                    double FoodPrice = Double.parseDouble(split[++i]);
                    list1.add(new Food(FoodName, FoodPrice));
                }
                Restaurant restaurant1 = new Restaurant(split[0], split[1],
                        Double.parseDouble(split[3]), list1);
                addRestaurant(restaurant1);
            }
        } catch (Exception e) {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception ignored) {

            }
        }
    }
}
