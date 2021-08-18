package melbourneEats;

import java.util.ArrayList;

public interface MelbourneEatsPackage {
    String getName();

    String getCategory();

    Double getDelivery_fee();

    ArrayList<Food> getList();

    boolean addFood(Food food);
}
