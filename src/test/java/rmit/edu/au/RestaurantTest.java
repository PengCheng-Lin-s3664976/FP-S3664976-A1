package rmit.edu.au;

import melbourneEats.Food;
import melbourneEats.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    Restaurant restaurant1;
    ArrayList<Food> list;
    ArrayList<Food> list1;
    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        list.add(new Food("Funky Burger", 12.00));
        list.add(new Food("French Fries", 7.50));
        list1 = new ArrayList<>();
        restaurant = new Restaurant("Burger King", "Fast food", 5.00, list);
    }

    @Test
    public void constructorExceptionTest(){
        assertThrows(IllegalArgumentException.class,
                ()-> restaurant1= new Restaurant(" ", " ", 5.00, list),
                "Invalid restaurant");
    }

    @Test
    void addFood() {
        assertAll("food",
                ()->assertTrue(restaurant.addFood(new Food("Coca Cola", 2.00))));
    }
}