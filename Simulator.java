import java.util.ArrayList;
/**
 * The Simulator class.
 * 
 * @author jordan martin
 * @version 3/1/2021
 * I abide by the Honor Code.
 *
 */

public class Simulator {
    private ArrayList<VendingMachine> vmList;
    
    /**
     * Creates a Simulator with the default constructor.
     *
     * @param vmList of VendingMachines
     */
    public Simulator(ArrayList<VendingMachine> vmList) {
        this.vmList = vmList;
    }
    
    /**
     * Adds VendingMachine to the end.
     *
     * @param vm to be added
     */
    public void addVM(VendingMachine vm) {
        vmList.add(vm);
    }
    
    /**
     * Simulates the VendingMachines.
     *
     * @param pCount to be simulate
     * @return the total profit 
     */
    public int simulate(int pCount) {
        int totalProfit = 0;
        for (int i = 0; i < vmList.size(); i++) {
            VendingMachine vending = vmList.get(i);
            for (int j = 0; j < vmList.get(i).getSlotCount(); j++) {
                vending.buy(j);
                
            }
            totalProfit += vending.getTotalProfit();
        }
        

        
        return totalProfit;
    }
}
