/**
 * The ChangeMakingMachine class.
 * 
 * @author jordan martin
 * @version 3/1/2021
 * I abide by the Honor Code.
 *
 */
public class ChangeMakingMachine extends VendingMachine {
    
    /**
     * Creates a new ChangeMakingMachine with default constructor.
     * 
     */
    public ChangeMakingMachine() {
        super(DEFAULT_SIZE);
    }
    
    /**
     * Creates a new ChangeMakingMachine with one parameter.
     *
     * @param size of the slot
     */
    public ChangeMakingMachine(int size) {
        super(size);
    }
    
    
    /**
     * Creates a new ChangeMakingMachine with two parameters.
     *
     * @param size of the slot
     * @param product to be loaded in
     */
    public ChangeMakingMachine(int size, Product product) {
        super(size, product);
    }
    
    /**
     * Buy out an entire slot.
     *
     * @param slotNum to be bought out
     * @param quarters number
     * @param dollars number
     * @return remainder of money
     */
    public int buy(int slotNum, int quarters, int dollars) {
        if (quarters < 0 || dollars < 0) {
            throw new IllegalArgumentException();
        } else {
            Product product = super.nextProduct(slotNum);
            int price = product.getPrice();
            
            if (quarters * 25 + dollars * 100 < price) {
                return -1;
            } else {
                totalProfit += price;
                machineProfit += price;
                return (quarters * 25 + dollars * 100) - price;
            }
            
        }

    }

}
