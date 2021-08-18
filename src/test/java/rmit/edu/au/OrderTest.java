package rmit.edu.au;

import melbourneEats.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;
    Map<String, Double> foodList;
    Map<String, Double> foodListFalse;
    Map<String, Double> foodAmounts;

    @BeforeEach
    void setUp() {
        foodListFalse = new HashMap<>();
        foodListFalse.put("Funky Burger",12.00);
        foodAmounts = new HashMap<>();
        foodAmounts.put("Funky Burger",2.00);
        order = new Order("Burger King",foodListFalse,foodAmounts,5.00);
        order.addFood("French Fries",7.50);
        order.addFoodAmount("French Fries",3.00);
    }

    @Test
    public void constructorExceptionTest(){
        assertThrows(IllegalArgumentException.class,()-> order= new Order(" ",foodList,foodAmounts,-12.00),
                "Invalid order ");
    }

    @Test
    void addFood() {
        assertAll("food",
                ()->assertTrue(order.addFood("Coca Cola",2.00)));
    }

    @Test
    void addFoodAmount() {
        assertAll("food Amounts",
                ()->assertTrue(order.addFoodAmount("Coca Cola",2.00)));
    }

    @Test
    void allFoodCost() {
        assertEquals(46.5,order.allFoodCost());
    }
}