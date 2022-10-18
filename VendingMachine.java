
/**
 * The VendingMachine class.
 * 
 * @author jordan martin
 * @version 2/8/2021
 * I abide by the Honor Code.
 */
public class VendingMachine {

    public static final int DEFAULT_SIZE = 15;
    protected static int totalProfit;
    protected int machineProfit;
    private Slot[] slots;
    
    
    /**
     * Creates default constructor.
     * 
     * 
     */
    public VendingMachine() {
        slots = new Slot[DEFAULT_SIZE]; 
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            this.slots[i] = new Slot();
        }
    }
    
    /**
     * Creates a constructor with one parameter.
     * 
     * @param size of the slot
     * 
     */
    public VendingMachine(int size) {
        slots = new Slot[size];
        for (int i = 0; i < size; i++) {
            this.slots[i] = new Slot();
        }
    }
    
    /**
     * Creates a constructor with two parameters.
     * 
     * @param size of the slot
     * @param product to fill the slot
     * 
     */
    public VendingMachine(int size, Product product) {
        this.slots = new Slot[size];
        
        for (int i = 0; i < size; i++) {
            this.slots[i] = new Slot();
            this.slots[i].load(product);
        }
    }
    
    /**
     * Loads with a generic Product.
     * 
     * 
     */
    public void load() {
        
        int cost = 0;
        int profit = 0;
        Product generic = new Product();
        
        for (int i = 0; i < slots.length; i++) {
            slots[i].load(generic);
            cost += 10 * generic.getCost();
            profit += 10 * generic.getPrice();
        }
        
        machineProfit += profit - cost;
        totalProfit += profit - cost;
        
    }
    
    
    /**
     * Loads a slot with a product to a certain number.
     * 
     * @param slotNum to be loaded
     * @param count to be loaded to
     * @param product to be added
     * 
     */
    public void load(int slotNum, int count, Product product) {
        if (slotNum < 0 || slotNum >= slots.length
                || count < 0 || product == null) {
            throw new IllegalArgumentException();
        }
        int cost = 0;
        
        int used = slots[slotNum].load(product, count);
        cost = used * product.getCost();
        
        machineProfit -= cost;
        totalProfit -= cost;
        
        
    }
    
    /**
     * Returns the next product.
     * 
     * @param slotNum to be checked
     * @return the next product
     * 
     */
    public Product nextProduct(int slotNum) {
        if (slotNum >= slots.length || slotNum < 0) {
            throw new IllegalArgumentException();
        } else if (slots[slotNum].nextProduct() == null) {
            return null;
        } else {
            return slots[slotNum].nextProduct();
        }
    }
    
    /**
     * Buys all the products in a slot.
     * 
     * @param slotNum to be bought out
     * @return boolean based on whether slot could be bought out
     * 
     */
    public boolean buy(int slotNum) {
        if (slotNum < 0 || slotNum >= slots.length) {
            throw new IllegalArgumentException();
        } else if (slots[slotNum].nextProduct() == null) {
            return false;
        } else {
            machineProfit += slots[slotNum].nextProduct().getPrice();
            totalProfit += slots[slotNum].nextProduct().getPrice();
            slots[slotNum].buyOne();
            return true;

        }
    }
    
    
    /**
     * Gets the number of slots.
     * 
     * @return number of slots
     * 
     */
    public int getSlotCount() {
        return slots.length;
    }
    
    /**
     * Gets the total profit.
     * 
     * @return total profit
     * 
     */
    public static int getTotalProfit() {
        return totalProfit;
    }
    
    
    /**
     * Resets the totalProfit.
     * 
     * 
     */
    public static void resetTotalProfit() {
        totalProfit = 0;
    }
    
    
    /**
     * Gets the current machineProfit.
     * 
     * @return the machineProfit
     * 
     */
    public int getMachineProfit() {
        return machineProfit;
    }
    
    /**
     * Converts the VendingMachine to a String.
     * 
     * @return String form of VendingMachine
     * 
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        String topLine = "Vending Machine\n";
        text.append(topLine);
        
        for (int i = 0; i < slots.length; i++) {
            text.append(slots[i].toString());
        }
        
        double newTotal = (double) totalProfit;
        double newMachine = (double) machineProfit;
        
        
        String lastLine = String.format("Total Profit: %.2f"
                + " Profit: %.2f.", newTotal, newMachine);
        text.append(lastLine);
        
        return text.toString();
    }

}
