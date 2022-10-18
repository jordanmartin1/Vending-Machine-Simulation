import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Junit tests for the SnackMachine class.
 * 
 * @author CS159 Instructors
 * @version V1, 2/2021
 *
 */

public class SnackMachineTest {

    private ArrayList<Product> pList;
    private SnackMachine sMachine;

    @BeforeEach
    public void setUp() {
        VendingMachine.resetTotalProfit();
        pList = new ArrayList<Product>(2);

        Product p1 = new Product("KitKat", 100, 125);
        Product p2 = new Product("Cheetos", 100, 125);

        pList.add(p1);
        pList.add(p2);

        sMachine = new SnackMachine(pList);

    }

    /** Tests constructor. **/
    @Test
    public void testConstructorSlotCount() {

        assertEquals(pList.size(), sMachine.getSlotCount());
    }

    @Test
    public void testConstructorCorrectProducts() {

        assertEquals("KitKat", sMachine.nextProduct(0).getName());
        assertEquals("Cheetos", sMachine.nextProduct(1).getName());
    }

    @Test
    public void testLoad() {

        for (int i = 0; i < TestingConstants.SLOT_SIZE; i++) {
            sMachine.buy(0);
            sMachine.buy(1);
        }
        assertFalse(sMachine.buy(0));
        assertFalse(sMachine.buy(1));

        sMachine.load();
        for (int i = 0; i < TestingConstants.SLOT_SIZE; i++) {
            assertEquals("KitKat", sMachine.nextProduct(0).getName());
            assertEquals("Cheetos", sMachine.nextProduct(1).getName());
            assertTrue(sMachine.buy(0));
            assertTrue(sMachine.buy(1));
        }

    }

    @Test
    public void testDoesNotAliasProductList() {

        for (int i = 0; i < TestingConstants.SLOT_SIZE; i++) {
            sMachine.buy(0);
            sMachine.buy(1);
        }
        pList.clear();
        pList.add(new Product());
        pList.add(new Product());
        sMachine.load();
        assertEquals("KitKat", sMachine.nextProduct(0).getName());
        assertEquals("Cheetos", sMachine.nextProduct(1).getName());
    }

    @Test
    public void testBuyWithMoney() {
        int change = sMachine.buy(0, 0, 2);
        assertEquals(75, change);
        change = sMachine.buy(0, 0, 1);
        assertEquals(-1, change);
    }

    @Test
    public void testMachineProfit() {
        int change = sMachine.buy(0, 0, 2);
        assertEquals(75, change);
        change = sMachine.buy(0, 0, 1);
        assertEquals(-1, change);
        assertEquals(-1875, sMachine.getMachineProfit());
    }

    @Test
    public void testTotalProfit() {
        SnackMachine sMachine1 = new SnackMachine(pList);
        int change = sMachine.buy(0, 0, 2);
        assertEquals(75, change);
        change = sMachine.buy(0, 0, 1);
        assertEquals(-1, change);
        assertEquals(-3875, VendingMachine.getTotalProfit());
    }

    @Test
    public void testToString() {

        String expected = "Vending Machine\n" + "SlotCount: 10 of\n"
                + "Product: KitKat Cost: 1.00 Price: 1.25.\n"
                + "Product: KitKat Cost: 1.00 Price: 1.25.\n"
                + "Product: KitKat Cost: 1.00 Price: 1.25.\n"
                + "Product: KitKat Cost: 1.00 Price: 1.25.\n"
                + "Product: KitKat Cost: 1.00 Price: 1.25.\n"
                + "Product: KitKat Cost: 1.00 Price: 1.25.\n"
                + "Product: KitKat Cost: 1.00 Price: 1.25.\n"
                + "Product: KitKat Cost: 1.00 Price: 1.25.\n"
                + "Product: KitKat Cost: 1.00 Price: 1.25.\n"
                + "Product: KitKat Cost: 1.00 Price: 1.25.\n"
                + "SlotCount: 10 of\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "Total Profit: -20.00 Machine Profit: -20.00.";
        assertEquals(expected.trim(), sMachine.toString().trim());
    }

}
