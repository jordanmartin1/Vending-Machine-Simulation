import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests the Slot class.
 * 
 * @author CS159 Instructors
 * @version 03/01/2021
 */
public class SlotTest {

    /**
     * Tests default constructor.
     */
    @Test
    public void testSlot1() {
        Slot s = new Slot();
        assertEquals("SlotCount: 0 of\n", s.toString());
    }

    /**
     * Tests product constructor.
     */
    @Test
    public void testSlot2() {
        Product p = new Product();
        Slot s = new Slot(p);
        String expect = "SlotCount: 10 of\n"
                + "Product: Generic Cost: 0.25 Price: 0.50.\n"
                + "Product: Generic Cost: 0.25 Price: 0.50.\n"
                + "Product: Generic Cost: 0.25 Price: 0.50.\n"
                + "Product: Generic Cost: 0.25 Price: 0.50.\n"
                + "Product: Generic Cost: 0.25 Price: 0.50.\n"
                + "Product: Generic Cost: 0.25 Price: 0.50.\n"
                + "Product: Generic Cost: 0.25 Price: 0.50.\n"
                + "Product: Generic Cost: 0.25 Price: 0.50.\n"
                + "Product: Generic Cost: 0.25 Price: 0.50.\n"
                + "Product: Generic Cost: 0.25 Price: 0.50.\n";
        assertEquals(expect, s.toString());
    }

    /**
     * Tests fully loading a product.
     */
    @Test
    public void testLoad1() {
        Product p = new Product();
        Slot s = new Slot();
        assertEquals(Slot.SLOT_SIZE, s.load(p));
        assertEquals(0, s.load(p));
    }

    /**
     * Tests partially loading a product.
     */
    @Test
    public void testLoad2() {
        Product p = new Product();
        Slot s = new Slot();
        assertEquals(3, s.load(p, 3));
        assertEquals(7, s.load(p, 15));
    }

    /**
     * Tests the nextProduct method.
     */
    @Test
    public void testNext() {
        Product p = new Product();
        Slot s = new Slot();
        assertNull(s.nextProduct());
        assertEquals(1, s.load(p, 1));
        assertEquals(p, s.nextProduct());
        assertEquals(p, s.nextProduct());
    }

    /**
     * Tests the buyOne method.
     */
    @Test
    public void testBuyOne() {
        Product p = new Product();
        Slot s = new Slot();
        assertNull(s.buyOne());
        assertEquals(1, s.load(p, 1));
        assertEquals(p, s.buyOne());
        assertNull(s.buyOne());
    }

}
