import java.util.ArrayList;

/**
 * The Slot class.
 * 
 * @author jordan martin
 * @version 2/8/2021
 * I abide by the Honor Code.
 */

public class Slot {
    
    public static final int SLOT_SIZE = 10;
    private ArrayList<Product> products;
    
    /**
     * Creates a new Slot with default constructor.
     * 
     */
    public Slot() {
        
        this.products = new ArrayList<Product>();
        
    }
    
    /**
     * Creates a new Slot.
     * 
     * @param product to fill the slot
     * 
     */
    public Slot(Product product) {
        
        products = new ArrayList<Product>();
        
        for (int i = 0; i < SLOT_SIZE; i++) {
            products.add(product);
        }
        
    }
    
    /**
     * Loads products into a slot.
     * 
     * @param product to be loaded
     * @return number of products to fill slot
     * 
     */ 
    public int load(Product product) {
        int count = 0;

        while (products.size() < SLOT_SIZE) {
            products.add(product);
            count++;
        }
        
        return count;
    }
    
    /**
     * Loads a certain number of products into the slot.
     * 
     * @param product to be loaded
     * @param count number of products to be added
     * @return number of the products filled to reach count
     * 
     */
    public int load(Product product, int count) { 
        int numUsed = 0;
        
        while (numUsed < count && products.size() < SLOT_SIZE) {
            products.add(product);
            numUsed++;
        }
        
        return numUsed;
    }
    
    /**
     * Returns the next product in the slot.
     * 
     * @return first Product
     */
    public Product nextProduct() {
        if (products.isEmpty()) {
            return null;
        } else {
            return products.get(0);
        }
    }
    
    /**
     * Simulates a user buying a product.
     * 
     * @return Product sold
     */
    public Product buyOne() {
        Product product;
        
        if (products.isEmpty()) {
            return null;
        } else {
            product = products.get(0);
            products.remove(0);
            return product;
        }
    }
    
    /**
     * Creates a String of Products in Slot.
     * 
     * @return String of Products in Slot
     */
    public String toString() {
        StringBuilder text = new StringBuilder();
        String topLine = "SlotCount: " + products.size() + " of\n";
        text.append(topLine);
        
        for (int i = 0; i < products.size(); i++) {
            String line = products.get(i).toString();
            text.append(line);
            text.append("\n");
        }
        
        return text.toString();
    }
    
    

}
