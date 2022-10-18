import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Junit tests for the ChangeMakingMachine class.
 * 
 * @author CS159 Instructors
 * @version V1, 2/28/2021
 *
 */

public class ChangeMakingMachineTest {

    private ChangeMakingMachine empty;
    private ChangeMakingMachine unLoaded;
    private ChangeMakingMachine loaded;

    private Product chip;
    private int chipCost;
    private int chipPrice;

    private int sizeUnloaded;
    private int sizeLoaded;

    @BeforeEach
    public void setUp() throws Exception {
        VendingMachine.resetTotalProfit();

        chipCost = 100;
        chipPrice = 125;
        chip = new Product("Cheetos", chipCost, chipPrice);

        sizeLoaded = 3;
        sizeUnloaded = 4;

        empty = new ChangeMakingMachine();
        loaded = new ChangeMakingMachine(sizeLoaded, chip);
        unLoaded = new ChangeMakingMachine(sizeUnloaded);

    }

    @Test
    public void testEmptyConstructorSlotCount() {

        assertEquals(TestingConstants.VENDING_DEFAULT_SIZE,
                empty.getSlotCount());
    }

    @Test
    public void testEmptyConstructorInitialMachineProfit() {

        assertEquals(0, empty.getMachineProfit());
    }

    @Test
    public void testTotalProfitNoBuys() {

        assertEquals(-sizeLoaded * TestingConstants.SLOT_SIZE * chipCost,
                VendingMachine.getTotalProfit());
    }

    @Test
    public void testSingeParamConstructorSlotCount() {

        assertEquals(sizeUnloaded, unLoaded.getSlotCount());
    }

    @Test
    public void testSingeParamConstructorMachineProfit() {

        assertEquals(0, unLoaded.getMachineProfit());
    }

    @Test
    public void testDoubleParamConstructorSlotCount() {

        assertEquals(sizeLoaded, loaded.getSlotCount());
    }

    @Test
    public void testDoubleParamConstructorMachineProfit() {

        assertEquals(-sizeLoaded * TestingConstants.SLOT_SIZE * chipCost,
                loaded.getMachineProfit());
    }

    // -----------------------------------------------
    // Tests for buy
    // -----------------------------------------------

    @Test
    public void testEmptyConstructorBuyReturnFalse() {
        for (int slot = 0; slot < empty.getSlotCount(); slot++) {
            assertEquals(false, empty.buy(slot));
        }
    }

    @Test
    public void testSingleParamConstructorBuyReturnFalse() {
        for (int slot = 0; slot < unLoaded.getSlotCount(); slot++) {
            assertEquals(false, unLoaded.buy(slot));
        }
    }

    @Test
    public void testCorrectChange1() {
        int actual = loaded.buy(0, 0, 2);
        int expected = 2 * 100 - chipPrice;
        assertEquals(expected, actual);

        actual = loaded.buy(0, 1, 2);
        expected = (2 * 100 + 1 * 25) - chipPrice;
        assertEquals(expected, actual);

        actual = loaded.buy(0, 6, 2);
        expected = (2 * 100 + 6 * 25) - chipPrice;
        assertEquals(expected, actual);
    }
    
    @Test
    public void testCorrectChange2() {
        chipCost = 200;
        chipPrice = 225;
        chip = new Product("Doritos", chipCost, chipPrice);
        
        ChangeMakingMachine vm = new ChangeMakingMachine(1, chip);
        
        int actual = vm.buy(0, 1, 2);
        int expected = 0;
        assertEquals(expected, actual);

        actual = vm.buy(0, 1, 3);
        expected = (3 * 100 + 1 * 25) - chipPrice;
        assertEquals(expected, actual);

        actual = vm.buy(0, 9, 0);
        expected = 0;
        assertEquals(expected, actual);
        
        actual = vm.buy(0, 3, 4);
        expected = (4 * 100 + 3 * 25) - chipPrice;
        assertEquals(expected, actual);
    }

    @Test
    public void testBuyInsufficientPaymentReturnNeg() {
        assertEquals(-1, loaded.buy(0, 0, 1));
        assertEquals(-1, loaded.buy(0, 4, 0));
    }

    @Test
    public void testNonPaymentBuy() {
        for (int buy = 0; buy < TestingConstants.SLOT_SIZE; buy++) {
            assertTrue(loaded.buy(0));
        }
        assertFalse(loaded.buy(0));
    }

    @Test
    public void testMachineProfitAfterValidBuyChangeMakingMachine() {
        loaded.buy(0, 6, 2);
        assertEquals(-(sizeLoaded * TestingConstants.SLOT_SIZE * chipCost)
                + chipPrice, loaded.getMachineProfit());
    }

    @Test
    public void testTotalProfitAfterValidBuyChangeMakingMachine() {
        loaded.buy(0, 6, 2);
        assertEquals(-(sizeLoaded * TestingConstants.SLOT_SIZE * chipCost)
                + chipPrice, VendingMachine.getTotalProfit());
    }

    @Test
    public void testChangeMakerBuySingleArgFromInvalidSlotLoadedMachine() {
        assertThrows(IllegalArgumentException.class, () -> {
            loaded.buy(sizeLoaded);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            loaded.buy(-1);
        });

    }
    
    @Test
    public void testChangeMakerBuyThreeArgFromInvalidSlotLoadedMachine() {
        assertThrows(IllegalArgumentException.class, () -> {
            loaded.buy(sizeLoaded, 0, 1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            loaded.buy(-1, 0, 1);
        });

    }
    
    @Test
    public void testToString() {
        VendingMachine.resetTotalProfit();
        ChangeMakingMachine cm = new ChangeMakingMachine(2);
        Product chip1 = new Product("Doritos", 201, 225);
        cm.load(0, 2, chip);
        cm.load(1, 4, chip1);
        cm.buy(1, 6, 2);
        
        String expected = "Vending Machine\n" + "SlotCount: 2 of\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "Product: Cheetos Cost: 1.00 Price: 1.25.\n"
                + "SlotCount: 3 of\n"
                + "Product: Doritos Cost: 2.01 Price: 2.25.\n"
                + "Product: Doritos Cost: 2.01 Price: 2.25.\n"
                + "Product: Doritos Cost: 2.01 Price: 2.25.\n"
                + "Total Profit: -7.79 Machine Profit: -7.79.";

        assertEquals(expected.trim(), cm.toString().trim());
    }




}
