/**
 * The DrinkMachine class.
 * 
 * @author jordan martin
 * @version 3/1/2021
 * I abide by the Honor Code.
 *
 */
public class DrinkMachine extends VendingMachine {
    
    public static final int COOLING_CHARGE = 10;
    
    
    /**
     * Creates a new DrinkMachine with default constructor.
     * 
     */
    public DrinkMachine() {
        super(DEFAULT_SIZE);
    }
    
    /**
     * Creates a new DrinkMachine with two parameters.
     * 
     * @param size of the slots
     * @param product to be placed in the machine
     */
    public DrinkMachine(int size, Product product) {
        super(size, product);
    }
    
    
    /**
     * Buy out a slot in the machine.
     * 
     * @param slotNum to be bought out
     * @return boolean based on if slot could be bought out
     */
    public boolean buy(int slotNum) {
        totalProfit -= 10;
        machineProfit -= 10;
        return super.buy(slotNum);
    }

}
