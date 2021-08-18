package rmit.edu.au;

import melbourneEats.Food;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FoodTest {
    Food food;
    @Test
    public void constructorExceptionTest(){
        assertThrows(IllegalArgumentException.class,()-> food= new Food(" ",-12.00),
                "Invalid food ");
    }
}