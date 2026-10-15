public class Inventory{
    private Product[] products;
    private int size;
    Inventory(int capacity){
        if(capacity < 1){
            capacity = 10;
        }
        this.products = new Product[capacity];
    }
    Inventory(){
        this(10);
    }
    public boolean addProduct(Product p){
        if(p == null){
            return false;
        }
        for(int i = 0; i < size; i++){
            if(p.getSku().equals(products[i].getSku())){
                System.out.println("Duplicate SKU: " + products[i].getSku());
                return false;
            }
        }
        if(size == products.length){
            System.out.println("Inventory full");
            return false;
        }
        else{
            products[size] = p;
            size++;
            return true;
        }
    }
    public Product findBySku(String sku){
        for(int i = 0; i < size; i++){
            if(sku.equals(products[i].getSku())){
                return products[i];
            }
        }
        return null;
    }
    public boolean sell(String sku, int amount){
        Product p = findBySku(sku);
        if(p == null){
            System.out.println("Unknown SKU: " + sku);
            return false;
        }
        if(p.sell(amount)){
            System.out.println("Sold " + amount + " x " + p.getName());
            return true;
        }
        else{
            System.out.println("Sale failed: " + sku);
            return false;
        }
    }
    public void printLowStock(int threshold){
        System.out.println("Low stock (below " + threshold + "):");
        boolean foundLowStock = false;
        for(int i = 0; i < size ; i++){
            if(products[i].getQuantity() < threshold){
                products[i].display();
                foundLowStock = true;
            }
        }
        if(!foundLowStock){
            System.out.println("none");
        }
    }
    public double getTotalValue(){
        double totalValue = 0;
        for(int i = 0; i < size ; i++){
            totalValue += products[i].getStockValue();
        }
        return totalValue;
    }
}