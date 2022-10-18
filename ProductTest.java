import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the Product class.
 * 
 * @author CS159 Instructors
 * @version 03/01/2021
 */
public class ProductTest {

    /**
     * Test default constructor and getters.
     */
    @Test
    public void testProduct() {
        Product p = new Product();
        assertEquals("Generic", p.getName());
        assertEquals(25, p.getCost());
        assertEquals(50, p.getPrice());
    }

    /**
     * Test 3-arg constructor and price rounding.
     */
    @Test
    public void testRounding() {
        // cost < price
        Product p1 = new Product("A", 102, 125);
        assertEquals("A", p1.getName());
        assertEquals(102, p1.getCost());
        assertEquals(125, p1.getPrice());

        // cost == price
        Product p2 = new Product("B", 53, 53);
        assertEquals("B", p2.getName());
        assertEquals(53, p2.getCost());
        assertEquals(75, p2.getPrice());

        // cost > price
        Product p3 = new Product("C", 79, 75);
        assertEquals("C", p3.getName());
        assertEquals(79, p3.getCost());
        assertEquals(100, p3.getPrice());
    }

    /**
     * Test invalid arguments for constructor.
     */
    @Test
    public void testInvalidArgs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(null, 0, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("M&Ms", -1, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("M&Ms", 0, -1);
        });
    }

    /**
     * Test the toString method.
     */
    @Test
    public void testToString() {
        Product p = new Product();
        assertEquals("Product: Generic Cost: 0.25 Price: 0.50.", p.toString());
    }

}
