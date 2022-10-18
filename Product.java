/**
 * The Product class.
 * 
 * @author jordan martin
 * @version 3/1/2021
 * I abide by the Honor Code.
 *
 */
public class Product {
    
    public static final int ROUND_PRICE = 25;
    private String name;
    private int cost;
    private int price;
    
    /**
     * Creates a new Product with default constructor.
     * 
     */
    public Product() {
        this.name = "Generic";
        this.cost = ROUND_PRICE;
        this.price = ROUND_PRICE * 2;
    }
    
    /**
     * Creates a new Product.
     * 
     * @param name of the product
     * @param cost of the product
     * @param price of the product
     */
    public Product(String name, int cost, int price) {
        int newPrice = price;
        if (name == null || cost < 0 || price < 0) {
            throw new IllegalArgumentException();
        } else if (price % ROUND_PRICE != 0) {
            while (newPrice % ROUND_PRICE != 0 || cost >= newPrice) {
                newPrice++;
            }
        }
        
        this.name = name;
        this.cost = cost;
        this.price = newPrice;
     
    }
    
    /**
     * Gets the current name.
     * 
     * @return name of the Product
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gets the current cost.
     * 
     * @return cost of the Product
     */
    public int getCost() {
        return this.cost;
    }
    
    /**
     * Gets the current price.
     * 
     * @return price of the Product
     */
    public int getPrice() {
        return this.price;
    }
    
    /**
     * Puts the Product in a String form.
     * 
     * @return String form of the Product
     */
    public String toString() {
        double dCost = this.cost;
        double dPrice = this.price;
        
        dCost = dCost / 100;
        dPrice = dPrice / 100;
        
        return String.format("Product: " + name 
                + " Cost: %.2f" + " Price: %.2f" 
                + ".", dCost, dPrice);
    }

}
