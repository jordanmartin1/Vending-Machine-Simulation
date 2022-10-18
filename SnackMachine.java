import java.util.ArrayList;
/**
 * The SnackMachine class.
 * 
 * @author jordan martin
 * @version 3/1/2021
 * I abide by the Honor Code.
 *
 */

public class SnackMachine extends ChangeMakingMachine {
    
    private ArrayList<Product> productList;
    
    /**
     * Creates a new SnackMachine with default constructor.
     * 
     * @param pList of the products
     */
    public SnackMachine(ArrayList<Product> pList) {
        super(pList.size());
        productList = new ArrayList<Product>();
        
        for (int i = 0; i < pList.size(); i++) {
            Product product = pList.get(i);
            productList.add(product);

        }
        load();
    }
    
    /**
     * Loads the SnackMachine.
     * 
     */
    public void load() {
        for (int i = 0; i < productList.size(); i++) {
            super.load(i, Slot.SLOT_SIZE, productList.get(i));
        }
    }

}
