import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Junit tests for the DrinkMachine class.
 * 
 * @author CS159 Instructors
 * @version V1, 2/2021
 *
 */

/** Tests the DrinkMachine Class methods **/
public class DrinkMachineTest {

    private DrinkMachine empty;
    private DrinkMachine loaded1;
    private DrinkMachine loaded2;
    
    private int size1;
    private int size2;
    

    private Product coke;
    private int cokeCost;
    private int cokePrice;

    @BeforeEach
    public void setUp() throws Exception {
        VendingMachine.resetTotalProfit();

        empty = new DrinkMachine();

        cokeCost = 200;
        cokePrice = 225;
        coke = new Product("Coke", cokeCost, cokePrice);

        size1 = 3;
        size2 = 4;

        loaded1 = new DrinkMachine(size1, coke);
        loaded2 = new DrinkMachine(size2, coke);

    }

    @Test
    public void testDrinkMachineCoolingCharge() {

        assertEquals(TestingConstants.COOLING_CHARGE,
                DrinkMachine.COOLING_CHARGE);
    }

    @Test
    public void testDrinkMachineDefaultConstructorSlotCount() {

        assertEquals(TestingConstants.VENDING_DEFAULT_SIZE,
                empty.getSlotCount());
    }

    @Test
    public void testDrinkMachineNextProduct() {

        assertEquals(coke, loaded2.nextProduct(0));
    }

    @Test
    public void testDrinkMachineDefaultConstructorInitialMachineProfit() {

        assertEquals(0, empty.getMachineProfit());
    }

    @Test
    public void testDrinkMachineLoadedConstructorSlotCount() {

        assertEquals(size2, loaded2.getSlotCount());
    }

    @Test
    public void testDrinkMachineLoadedConstructorMachineProfit() {

        assertEquals(-(size2 * TestingConstants.SLOT_SIZE * cokeCost),
                loaded2.getMachineProfit());
    }

    @Test
    public void testDrinkMachineBuyEmptyMachineReturningFalse() {
        for (int slot = 0; slot < TestingConstants.VENDING_DEFAULT_SIZE; slot++) {
            assertFalse(empty.buy(slot));
        }
    }

    @Test
    public void testVendingMachineBuyFullMachineReturningTrue() {
        for (int slot = 0; slot < loaded2.getSlotCount(); slot++) {
            assertTrue(loaded2.buy(slot));
        }
    }

    @Test
    public void testDrinkMachineBuyOneItemMachineProfit() {
        loaded2.buy(0);
        assertEquals(
                -(size2 * TestingConstants.SLOT_SIZE * cokeCost)
                        + (cokePrice - TestingConstants.COOLING_CHARGE),
                loaded2.getMachineProfit());
    }

    @Test
    public void testDrinkMachineBuyOneItemTotalProfit() {
        loaded1.buy(0);
        int mp = loaded2.getMachineProfit() + loaded1.getMachineProfit();
        assertEquals(mp, VendingMachine.getTotalProfit());
    }

    @Test
    public void testDrinkmachineBuyFromInvalidSlotLoadedMachine() {
        assertThrows(IllegalArgumentException.class, () -> {
            loaded1.buy(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            loaded1.buy(3);
        });

    }

    @Test
    public void testDrinkMachineMachineProfitLoadedMachineNoBuys() {
        assertEquals(-(size1 * TestingConstants.SLOT_SIZE * cokeCost),
                loaded1.getMachineProfit());
    }

    @Test
    public void testDrinkMachineTotalProfitLoadedEqualtoSumOfMachineProfits() {
        int mp = loaded2.getMachineProfit() + loaded1.getMachineProfit();
        assertEquals(mp, VendingMachine.getTotalProfit());
    }

    @Test
    public void testDrinkMachineToString() {
        DrinkMachine dm = new DrinkMachine(2, coke);
        dm.buy(0);

        String expected = "Vending Machine\n" + "SlotCount: 9 of\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "SlotCount: 10 of\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Product: Coke Cost: 2.00 Price: 2.25.\n"
                + "Total Profit: -177.85 Machine Profit: -37.85.";

        assertEquals(expected.trim(), dm.toString().trim());
    }

}
