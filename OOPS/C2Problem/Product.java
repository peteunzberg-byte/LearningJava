public class Product{
    private String sku; 
    private String name;
    private double price;
    private int quantity;
    Product(String sku, String name, double price, int quantity){
        this.sku = sku;
        this.name = name;
        setPrice(price);
        if(quantity < 0){
            this.quantity = 0;
        }
        else{
            this.quantity = quantity;
        }
    }
    public void setPrice(double price){
        if(price < 0){
            System.out.println("Invalid price");
        }
        else{
            this.price = price;
        }
    }
    public String getSku(){
        return sku;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public void restock(int amount){
        if(amount <= 0){
            System.out.println("Invalid amount");
        }
        else{
            this.quantity += amount;
        }
    }
    public boolean sell(int amount){
        if(((amount > 0) && (amount <= this.quantity))){
            this.quantity -= amount;
            return true;
        }
        return false;
    }
    public double getStockValue(){
        return (this.price * this.quantity);
    }
    public void display(){
        System.out.printf("%s | %s | $%.2f | qty %d%n", this.sku, this.name, this.price, this.quantity);
    }
}   