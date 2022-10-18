import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Simulator Class.
 * 
 * @author CS159 Instructors
 * @version 2/2021
 *
 */
public class SimulatorTest {

    private Product mm;
    private int mmCost;
    private int mmPrice;

    private Product snickers;
    private int snickersCost;
    private int snickersPrice;

    @BeforeEach
    public void resetProfits() {
        VendingMachine.resetTotalProfit();
    }

    @BeforeEach
    public void setUp() throws Exception {
        VendingMachine.resetTotalProfit();

        mmCost = 102;
        mmPrice = 125;
        mm = new Product("M&Ms", mmCost, mmPrice);

        snickersCost = 101;
        snickersPrice = 150;
        snickers = new Product("Snickers", snickersCost, snickersPrice);
    }

    @Test
    public void testPartAQuizCode() {
        VendingMachine vm = new VendingMachine();

        vm.load();

        ArrayList<VendingMachine> vmList = new ArrayList<>();

        vmList.add(vm);

        Simulator sim = new Simulator(vmList);
        int result = sim.simulate(10);
        assertEquals(3750, result);
    }

    @Test
    public void testSimulatorTwoVendingMachinesFromDefaultConstructorBuyAll() {
        VendingMachine vm1 = new VendingMachine();
        VendingMachine vm2 = new VendingMachine();

        vm1.load();
        vm2.load();

        ArrayList<VendingMachine> vmList = new ArrayList<>();

        vmList.add(vm1);
        vmList.add(vm2);

        Simulator sim = new Simulator(vmList);

        assertEquals(7500, sim.simulate(10));
    }

    @Test
    public void testSimulatorTwoVendingMachinesFromDefaultConstructorOneBuy() {
        VendingMachine vm1 = new VendingMachine();
        VendingMachine vm2 = new VendingMachine();

        vm1.load();
        vm2.load();

        ArrayList<VendingMachine> vmList = new ArrayList<>();

        vmList.add(vm1);
        vmList.add(vm2);

        Simulator sim = new Simulator(vmList);

        assertEquals(-6000, sim.simulate(1));
    }

    @Test
    public void testSimulatorTwoVendingMachinesFromConstructorZeroBuy() {
        VendingMachine vm1 = new VendingMachine();
        VendingMachine vm2 = new VendingMachine();

        vm1.load();
        vm2.load();

        ArrayList<VendingMachine> vmList = new ArrayList<>();

        vmList.add(vm1);
        vmList.add(vm2);

        Simulator sim = new Simulator(vmList);

        assertEquals(-7500, sim.simulate(0));
    }

    @Test
    public void testSimulatorVendingMachinesFromDefaultConstructorAndAddVM() {
        VendingMachine vm1 = new VendingMachine();
        VendingMachine vm2 = new VendingMachine();
        VendingMachine vm3 = new VendingMachine();

        vm1.load();
        vm2.load();
        vm3.load();

        ArrayList<VendingMachine> vmList = new ArrayList<>();

        vmList.add(vm1);
        vmList.add(vm2);

        Simulator sim = new Simulator(vmList);
        sim.addVM(vm3);

        assertEquals(11250, sim.simulate(10));
    }

    @Test
    public void testSimulatorAddVMLoadedAfterConstructor() {
        VendingMachine vm1 = new VendingMachine();
        VendingMachine vm2 = new VendingMachine();
        VendingMachine vm3 = new VendingMachine();

        vm1.load();
        vm2.load();

        ArrayList<VendingMachine> vmList = new ArrayList<>();

        vmList.add(vm1);
        vmList.add(vm2);

        Simulator sim = new Simulator(vmList);
        vm3.load();
        sim.addVM(vm3);

        assertEquals(11250, sim.simulate(10));
    }

    @Test
    public void testSimulatorMultipleVendingMachineTypes() {
        VendingMachine vm1 = new VendingMachine();
        VendingMachine vm2 = new DrinkMachine();

        ArrayList<Product> products = new ArrayList<>();
        products.add(snickers);
        products.add(mm);

        VendingMachine vm3 = new SnackMachine(products);

        vm1.load();
        vm2.load();

        ArrayList<VendingMachine> vmList = new ArrayList<>();

        vmList.add(vm1);
        vmList.add(vm2);
        vmList.add(vm3);

        Simulator sim = new Simulator(vmList);

        assertEquals(6720, sim.simulate(10));
    }

    @Test
    public void testSimulatorDoesntAliasArrayList() {
        VendingMachine vm1 = new VendingMachine();
        VendingMachine vm2 = new VendingMachine();
        VendingMachine vm3 = new VendingMachine();

        vm1.load();
        vm2.load();
        vm3.load();

        ArrayList<VendingMachine> vmList = new ArrayList<>();

        vmList.add(vm1);
        vmList.add(vm2);

        Simulator sim = new Simulator(vmList);

        vmList.add(vm3);

        assertEquals(3750, sim.simulate(10));
    }

}
