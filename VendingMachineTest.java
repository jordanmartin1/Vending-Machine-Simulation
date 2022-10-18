import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Junit tests for the VendingMachine class.
 * 
 * @author CS159 Instructors
 * @version V1, 2/2021
 *
 */
public class VendingMachineTest {

    private VendingMachine empty;

    private Product mm;
    private int mmCost;
    private int mmPrice;

    private Product snickers;
    private int snickersCost;
    private int snickersPrice;

    private Product genericProduct;

    @BeforeEach
    public void setUp() throws Exception {
        VendingMachine.resetTotalProfit();

        empty = new VendingMachine();

        mmCost = 102;
        mmPrice = 125;
        mm = new Product("M&Ms", mmCost, mmPrice);

        snickersCost = 101;
        snickersPrice = 150;
        snickers = new Product("Snickers", snickersCost, snickersPrice);

        genericProduct = new Product();
    }

    // ----------------------------------------------------
    // TEST CORRECT CONSTANTS
    // ----------------------------------------------------

    @Test
    public void testDefaultSizeConstant() {
        assertEquals(TestingConstants.VENDING_DEFAULT_SIZE,
                VendingMachine.DEFAULT_SIZE);
    }

    // ----------------------------------------------------
    // TESTS FOR SLOT COUNT
    // ----------------------------------------------------

    @Test
    public void testDefaultConstructorSlotCount() {
        assertEquals(TestingConstants.VENDING_DEFAULT_SIZE,
                empty.getSlotCount());
    }

    @Test
    public void testTwoArgConstructorSlotCount() {
        VendingMachine vm = new VendingMachine(3, new Product());
        assertEquals(3, vm.getSlotCount());
    }

    // ----------------------------------------------------
    // TESTS FOR BUY RETURN VALUE
    // ----------------------------------------------------
    @Test
    public void testBuyEmptyMachineReturnsFalse() {
        for (int slot = 0; slot < TestingConstants.VENDING_DEFAULT_SIZE; slot++) {
            assertFalse(empty.buy(slot));
        }
    }

    @Test
    public void testBuyFullMachineReturnsTrue() {
        empty.load();
        for (int slot = 0; slot < TestingConstants.VENDING_DEFAULT_SIZE; slot++) {
            assertTrue(empty.buy(slot));
        }
    }

    @Test
    public void testBuyUntilSlotEmptyCorrectBuyReturnValues() {
        empty.load();
        for (int slot = 0; slot < TestingConstants.VENDING_DEFAULT_SIZE; slot++) {
            for (int i = 0; i < TestingConstants.SLOT_SIZE; i++) {
                assertTrue(empty.buy(slot));
            }
            assertFalse(empty.buy(slot));
        }
    }

    // ----------------------------------------------------
    // TESTS FOR BUY THROWING CORRECT EXCEPTION
    // ----------------------------------------------------

    @Test
    public void testBuyNegativeSlotNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            empty.buy(-1);
        });

    }

    @Test
    public void testBuySlotNumberTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> {
            empty.buy(TestingConstants.VENDING_DEFAULT_SIZE);
        });

    }

    // ----------------------------------------------------
    // TESTS FOR LOAD THROWING CORRECT EXCEPTION
    // ----------------------------------------------------

    @Test
    public void testLoadNegativeSlotThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            empty.load(-1, 0, new Product());
        });

    }

    @Test
    public void testLoadSlotTooLargeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            empty.load(TestingConstants.VENDING_DEFAULT_SIZE, 0, new Product());
        });

    }

    @Test
    public void testLoadNegativeCountThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            empty.load(0, -1, new Product());
        });

    }

    @Test
    public void testLoadNullProductThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            empty.load(0, 1, null);
        });

    }

    // ----------------------------------------------------
    // TESTS FOR NEXTPRODUCT CORRECT RETURN VALUE
    // ----------------------------------------------------

    @Test
    public void testNextProductReturnsNullForEmptyMachine() {
        assertNull(empty.nextProduct(0));
    }

    @Test
    public void testNextProductCorrectReturnNonEmpty() {
        empty.load();
        for (int slotNum = 0; slotNum < TestingConstants.VENDING_DEFAULT_SIZE; slotNum++) {
            assertEquals("Generic", empty.nextProduct(slotNum).getName());
        }
    }

    @Test
    public void testNextProductCorrectReturnNonGenericAfterBuy() {
        empty.load(1, 1, mm);
        empty.load(1, 1, snickers);
        
        empty.load(2, 1, snickers);
        empty.load(2, 1, mm);

        assertEquals("M&Ms", empty.nextProduct(1).getName());
        empty.buy(1);
        assertEquals("Snickers", empty.nextProduct(1).getName());
        
        assertEquals("Snickers", empty.nextProduct(2).getName());
        empty.buy(2);
        assertEquals("M&Ms", empty.nextProduct(2).getName());
        
    }

    // ----------------------------------------------------
    // TESTS FOR NEXTPRODUCT THROWING CORRECT EXCEPTION
    // ----------------------------------------------------

    @Test
    public void testNextProductNegativeSlotNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            empty.nextProduct(-1);
        });

    }

    @Test
    public void testNextProductSlotNumberTooLarge() {
        assertThrows(IllegalArgumentException.class, () -> {
            empty.nextProduct(TestingConstants.VENDING_DEFAULT_SIZE);
        });

    }

    // ----------------------------------------------------
    // TESTS FOR PER-MACHINE PROFIT TRACKING
    // ----------------------------------------------------

    @Test
    public void testMachineProfitDefaultMachineAfterLoad() {
        empty.load();
        assertEquals(-3750, empty.getMachineProfit());
    }

    @Test
    public void testMachineProfitTwoArgConstructorNonDefaultProduct() {
        VendingMachine vm = new VendingMachine(3, mm);
        assertEquals(-mmCost * TestingConstants.SLOT_SIZE * 3,
                vm.getMachineProfit());
    }

    @Test
    public void testMachineProfitTwoArgConstructorAfterBuys() {
        VendingMachine vm = new VendingMachine(3, mm);
        vm.buy(1);
        int totalCost = -mmCost * TestingConstants.SLOT_SIZE * 3;
        assertEquals(totalCost + mmPrice, vm.getMachineProfit());
    }

    @Test
    public void testMachineProfitTwoArgConstructorAfterBuysAndGenericLoad() {
        VendingMachine vm = new VendingMachine(3, mm);
        vm.buy(1);
        int totalCost = -mmCost * TestingConstants.SLOT_SIZE * 3;
        vm.load();
        assertEquals(totalCost + mmPrice - genericProduct.getCost(),
                vm.getMachineProfit());
    }

    @Test
    public void testMachineProfitTwoArgConstructorAfterEverythingBought() {
        VendingMachine vm = new VendingMachine(1, mm);
        for (int i = 0; i < TestingConstants.SLOT_SIZE; i++) {
            vm.buy(0);
        }
        int totalCost = -mmCost * TestingConstants.SLOT_SIZE;

        assertEquals(totalCost + mmPrice * TestingConstants.SLOT_SIZE,
                vm.getMachineProfit());
    }

    @Test
    public void testMachineProfitTwoArgConstructorAfterMoreBuysThanAllowed() {
        VendingMachine vm = new VendingMachine(3, mm);
        for (int i = 0; i < TestingConstants.SLOT_SIZE + 10; i++) {
            vm.buy(1);
        }
        int totalCost = -mmCost * TestingConstants.SLOT_SIZE * 3;

        assertEquals(totalCost + mmPrice * TestingConstants.SLOT_SIZE,
                vm.getMachineProfit());
    }

    @Test
    public void testMachineProfitWithThreeArgLoadNoBuys() {
        VendingMachine vm = new VendingMachine();
        vm.load(0, 1, mm);
        vm.load(1, 1, mm);
        vm.load(2, 1, mm);
        vm.load(2, 1, snickers);

        int totalCost = -mmCost * 3 - snickersCost;

        assertEquals(totalCost, vm.getMachineProfit());
    }

    @Test
    public void testMachineProfitWithThreeArgLoadAndBuys() {
        VendingMachine vm = new VendingMachine();
        vm.load(0, 1, mm);
        vm.load(1, 1, mm);
        vm.load(2, 1, mm);
        vm.load(2, 1, snickers);
        vm.load(3, 1, snickers);

        int totalCost = -mmCost * 3 - snickersCost * 2;
        vm.buy(0);
        vm.buy(3);

        assertEquals(totalCost + mmPrice + snickersPrice,
                vm.getMachineProfit());
    }
    
    // ----------------------------------------------------
    // TESTS FOR TOTAL PROFIT TRACKING SINGLE MACHINE
    // ----------------------------------------------------

    @Test
    public void testTotalProfitDefaultMachineAfterLoad() {
        empty.load();
        assertEquals(-3750, VendingMachine.getTotalProfit());
    }
    
    @Test
    public void testResetTotalProfitDoesNotResetMachineProfit() {
        empty.load();
        assertEquals(-3750, VendingMachine.getTotalProfit());
        VendingMachine.resetTotalProfit();
        assertEquals(0, VendingMachine.getTotalProfit());
        assertEquals(-3750, empty.getMachineProfit());
    }

    @Test
    public void testTotalProfitTwoArgConstructorAfterBuysAndGenericLoad() {
        VendingMachine vm = new VendingMachine(3, mm);
        vm.buy(1);
        int totalCost = -mmCost * TestingConstants.SLOT_SIZE * 3;
        vm.load();
        assertEquals(totalCost + mmPrice - genericProduct.getCost(),
                VendingMachine.getTotalProfit());
    }

    @Test
    public void testTotalProfitTwoArgConstructorAfterEverythingBought() {
        VendingMachine vm = new VendingMachine(1, mm);
        for (int i = 0; i < TestingConstants.SLOT_SIZE; i++) {
            vm.buy(0);
        }
        int totalCost = -mmCost * TestingConstants.SLOT_SIZE;

        assertEquals(totalCost + mmPrice * TestingConstants.SLOT_SIZE,
                VendingMachine.getTotalProfit());
    }

    @Test
    public void testTotalProfitTwoArgConstructorAfterMoreBuysThanAllowed() {
        VendingMachine vm = new VendingMachine(3, mm);
        for (int i = 0; i < TestingConstants.SLOT_SIZE + 10; i++) {
            vm.buy(1);
        }
        int totalCost = -mmCost * TestingConstants.SLOT_SIZE * 3;

        assertEquals(totalCost + mmPrice * TestingConstants.SLOT_SIZE,
                VendingMachine.getTotalProfit());
    }

    @Test
    public void testTotalProfitWithThreeArgLoadNoBuys() {
        VendingMachine vm = new VendingMachine();
        vm.load(0, 1, mm);
        vm.load(1, 1, mm);
        vm.load(2, 1, mm);
        vm.load(2, 1, snickers);

        int totalCost = -mmCost * 3 - snickersCost;

        assertEquals(totalCost, VendingMachine.getTotalProfit());
    }

    @Test
    public void testTotalProfitWithThreeArgLoadAndBuys() {
        VendingMachine vm = new VendingMachine();
        vm.load(0, 1, mm);
        vm.load(1, 1, mm);
        vm.load(2, 1, mm);
        vm.load(2, 1, snickers);
        vm.load(3, 1, snickers);

        int totalCost = -mmCost * 3 - snickersCost * 2;
        vm.buy(0);
        vm.buy(3);

        assertEquals(totalCost + mmPrice + snickersPrice,
                VendingMachine.getTotalProfit());
    }

    // ----------------------------------------------------
    // TESTS FOR TOTAL PROFIT TRACKING MULTIPLE MACHINES
    // ----------------------------------------------------
    @Test
    public void testTotalProfitTwoNewVendingMachinesMultipleProducts() {
        new VendingMachine(2, mm);
        new VendingMachine(3, snickers);
        int totalCost = mmCost * 2 * TestingConstants.SLOT_SIZE
                + snickersCost * 3 * TestingConstants.SLOT_SIZE;

        assertEquals(-totalCost, VendingMachine.getTotalProfit());
    }

    @Test
    public void testTotalProfitTwoVendingMachinesMultipleProductsAllBought() {
        VendingMachine vm1 = new VendingMachine(2, mm);
        VendingMachine vm2 = new VendingMachine(3, snickers);
        int totalCost = mmCost * 2 * TestingConstants.SLOT_SIZE
                + snickersCost * 3 * TestingConstants.SLOT_SIZE;
        int totalIncome = mmPrice * 2 * TestingConstants.SLOT_SIZE
                + snickersPrice * 3 * TestingConstants.SLOT_SIZE;

        for (int i = 0; i < TestingConstants.SLOT_SIZE; i++) {
            vm1.buy(0);
            vm1.buy(1);

            vm2.buy(0);
            vm2.buy(1);
            vm2.buy(2);
        }

        assertEquals(totalIncome - totalCost, VendingMachine.getTotalProfit());
    }

    @Test
    public void testTotalProfitTwoVendingMachinesMultipleProductsSomeBought() {
        VendingMachine vm1 = new VendingMachine(2, mm);
        VendingMachine vm2 = new VendingMachine(3, snickers);

        int totalCost = mmCost * 2 * TestingConstants.SLOT_SIZE
                + snickersCost * 3 * TestingConstants.SLOT_SIZE;
        int totalIncome = mmPrice * 2 + snickersPrice * 3;

        vm1.buy(0);
        vm1.buy(1);

        vm2.buy(0);
        vm2.buy(1);
        vm2.buy(2);

        assertEquals(totalIncome - totalCost, VendingMachine.getTotalProfit());
    }

    // TESTS FOR TOSTRING

    @Test
    public void testToStringZeroSlotMachine() {
        VendingMachine vm = new VendingMachine(0, new Product());
        String expected = "Vending Machine\n"
                + "Total Profit: 0.00 Machine Profit: 0.00.";
        assertEquals(expected.trim(), vm.toString().trim());
    }

    @Test
    public void testToStringTwoSlotMachineFullGeneric() {
        VendingMachine vm = new VendingMachine(2, new Product());

        String slotString = "SlotCount: 10 of\n";
        String productString = "Product: Generic Cost: 0.25 Price: 0.50.";
        for (int i = 0; i < 10; i++) {
            slotString += productString + "\n";
        }

        String expected = "Vending Machine\n" + slotString + slotString
                + "Total Profit: -5.00 Machine Profit: -5.00.";
        assertEquals(expected.trim(), vm.toString().trim());
    }

    @Test
    public void testToStringTwoSlotPartiallyFull() {
        VendingMachine vm = new VendingMachine(2);
        vm.load(0, 2, mm);
        vm.load(0, 3, snickers);
        vm.load(1, 3, snickers);
        vm.load(1, 1, mm);
        String expected = "Vending Machine\n"
                + "SlotCount: 5 of\n"
                + "Product: M&Ms Cost: 1.02 Price: 1.25.\n"
                + "Product: M&Ms Cost: 1.02 Price: 1.25.\n"
                + "Product: Snickers Cost: 1.01 Price: 1.50.\n"
                + "Product: Snickers Cost: 1.01 Price: 1.50.\n"
                + "Product: Snickers Cost: 1.01 Price: 1.50.\n"
                + "SlotCount: 4 of\n"
                + "Product: Snickers Cost: 1.01 Price: 1.50.\n"
                + "Product: Snickers Cost: 1.01 Price: 1.50.\n"
                + "Product: Snickers Cost: 1.01 Price: 1.50.\n"
                + "Product: M&Ms Cost: 1.02 Price: 1.25.\n"
                + "Total Profit: -9.12 Machine Profit: -9.12.";
        
        assertEquals(expected.trim(), vm.toString().trim());
    }

}
