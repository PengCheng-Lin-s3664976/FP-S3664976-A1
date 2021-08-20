package rmit.edu.au;

import melbourneEats.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MelbourneEatsSystemTest {

    MelbourneEatsSystem melbourneEatsSystem;
    Map<String, Double> foodListFalse;
    Map<String, Double> foodAmounts;
    Restaurant restaurant;

    @BeforeEach
    void setUp() {
        melbourneEatsSystem = new MelbourneEatsSystem();
    }

    @Test
    void allOrderFoodCost() {
        foodListFalse = new HashMap<>();
        foodListFalse.put("Funky Burger",12.00);
        foodAmounts = new HashMap<>();
        foodAmounts.put("Funky Burger",2.00);
        melbourneEatsSystem.addOrder
                (new Order("Burger King",foodListFalse,foodAmounts,5.00));
        assertEquals(24,melbourneEatsSystem.allOrderFoodCost());
    }

    @Test
    void readRestaurants_And_ReadDiscounts() {
        assertEquals(true,melbourneEatsSystem.readDiscounts());
        assertEquals(true,melbourneEatsSystem.readRestaurants());
    }
    @Test
    void addRestaurant() {
        ArrayList<Food> list = new ArrayList<>();
        list.add(new Food("Funky Burger",12.00));
        list.add(new Food("French Fries",7.50));
        restaurant = new Restaurant("Burger","Fast food",5.00, list);
        assertTrue(melbourneEatsSystem.addRestaurant(restaurant));
    }

    @Test
    void addOrder() {
        foodListFalse = new HashMap<>();
        foodListFalse.put("Funky Burger",12.00);
        foodAmounts = new HashMap<>();
        foodAmounts.put("Funky Burger",2.00);

        assertTrue(melbourneEatsSystem.addOrder
                (new Order("Burger King",foodListFalse,foodAmounts,5.00)));
    }

    @Test
    void addDis() {
        assertTrue(melbourneEatsSystem.addDis(new Discount(1,2,50)));
    }

    @Test
    void addDiscountDelivery() {
        assertTrue(melbourneEatsSystem.addDiscountDelivery(new DisDelivery(1,50)));
    }

    @Test
    void allOrderFoodDiscountsDeliveryCost() {
        foodListFalse = new HashMap<>();
        foodListFalse.put("Funky Burger",12.00);
        foodAmounts = new HashMap<>();
        foodAmounts.put("Funky Burger",2.00);
        melbourneEatsSystem.addOrder
                (new Order("Burger King",foodListFalse,foodAmounts,5.00));
        assertEquals(5,melbourneEatsSystem.allOrderFoodDiscountsDeliveryCost());
    }

    @Test
    void allOrderFoodDiscountsCost() {
        foodListFalse = new HashMap<>();
        foodListFalse.put("Funky Burger",12.00);
        foodAmounts = new HashMap<>();
        foodAmounts.put("Funky Burger",3.00);
        melbourneEatsSystem.addOrder
                (new Order("Burger King",foodListFalse,foodAmounts,5.00));
        assertEquals(28.8,melbourneEatsSystem.allOrderFoodDiscountsCost());
    }

    @Test
    void allOrderFoodDeliveryCost() {
        foodListFalse = new HashMap<>();
        foodListFalse.put("Funky Burger",12.00);
        foodAmounts = new HashMap<>();
        foodAmounts.put("Funky Burger",3.00);
        melbourneEatsSystem.addOrder
                (new Order("Burger King",foodListFalse,foodAmounts,5.00));
        assertEquals(5.00,melbourneEatsSystem.allOrderFoodDeliveryCost());
    }
}