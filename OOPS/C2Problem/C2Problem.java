public class C2Problem {
    public static void main(String[] args){
        Product p1 = new Product("SKU-101", "Notebook", 3.50, 40);
        Product p2 = new Product("SKU-102", "Pen", 1.25, 8);
        Product p3 = new Product("SKU-103", "Backpack", -20.0, 2);
        Product p4 = new Product("SKU-101", "Fake Notebook", 1.00, 5);
        Product p5 = new Product("SKU-104", "Stapler", 6.00, 3);
        p3.setPrice(29.99);
        Inventory i1 = new Inventory(3);
        i1.addProduct(p1);
        i1.addProduct(p2);
        i1.addProduct(p3);
        i1.addProduct(p4);
        i1.addProduct(p5);
        i1.sell("SKU-102", 5);
        i1.sell("SKU-102", 5);
        i1.sell("SKU-999", 1);
        i1.printLowStock(5);
        i1.findBySku("SKU-103").restock(10);
        i1.printLowStock(5);
        System.out.printf("Total inventory value: $%.2f%n", i1.getTotalValue());

    }
}

/*
Written questions:
1.Step 6 restocks a product obtained from findBySku, yet the inventory sees the change. Why?
Answer: Being an Inventory method isn’t the reason. Any method could return something. What matters is what it returns:
 a reference to the exact same Product object that sits inside the inventory’s array, not a copy.
inventory.products[2] ──┐
                        ▼
                  [ Backpack, qty 2 ]
                        ▲
backpack (in main) ─────┘
Two arrows, one object. So backpack.restock(10) changes that object, and the inventory sees it because it’s pointing at the same thing. 
If findBySku returned a copy (like a copy constructor would make), restocking it would change only the copy, and the inventory would still say 2.
2.Suppose Inventory had public Product[] getProducts() { return products; }. What could code in main break with it, and how would you prevent that?
Answer: return products; hands main an arrow to the inventory’s own private array. private stops main from writing inv.products, but once you 
return the array itself, main holds the same arrow and can do anything to it:
Product[] arr = inv.getProducts();
arr[0] = null;                                        // hole in the middle
arr[1] = new Product("SKU-102", "Fake Pen", 0.01, 99); // duplicate SKU, no check!
Now the inventory is broken, even though none of its methods were called:
size still says 3, but slot 0 is null, so findBySku crashes with a NullPointerException when it calls .getSku() on it.
There are now two SKU-102s, even though addProduct was supposed to prevent duplicates.
All your careful rules (duplicate check, capacity check) only work if every change goes through Inventory’s methods. 
Returning the raw array is a back door around them.
How to prevent it:
Best: don’t expose the array at all. Give main the methods it actually needs (findBySku, printLowStock and so on).
If you must return the list: return a new array containing the products, so main can’t add, remove or null out slots in the real one. 
Note that it’d still be a shallow copy: main could still change a Product’s price through it. A deep copy would protect against that too.
*/

/*
C2. Store inventory
Model a small store's stock system: products with validated data, and an inventory that manages an array of them.
Class Product
• Fields: sku (String), name (String), price (double), quantity (int).
• Constructor Product(String sku, String name, double price, int quantity). Set the price by calling setPrice, so a negative price is rejected there. If quantity is negative, use 0.
• Getters for all four fields.
• void setPrice(double price): if negative, print Invalid price and keep the old value. Otherwise update it.
• void restock(int amount): if amount is 0 or less, print Invalid amount. Otherwise add it to quantity.
• boolean sell(int amount): returns false without changing anything if amount is 0 or less, or greater than quantity. Otherwise subtracts and returns true. Prints nothing.
• double getStockValue(): price times quantity.
• void display(): prints SKU | name | $price | qty N with price to 2 decimals, for example SKU-102 | Pen | $1.25 | qty 3.
Class Inventory
• Fields: private Product[] products and private int size.
• Inventory(int capacity): if capacity is less than 1, use 10. Inventory(): capacity 10, chained.
• boolean addProduct(Product p): return false for null. Check for a duplicate SKU first (compare with .equals) and print Duplicate SKU: <sku>. Then check capacity and print Inventory full. Otherwise add it and return true.
• Product findBySku(String sku): returns the matching product, or null if none.
• boolean sell(String sku, int amount): if the SKU isn't found, print Unknown SKU: <sku>. If the product's sell fails, print Sale failed: <sku>. On success, print Sold <amount> x <name>.
• void printLowStock(int threshold): prints Low stock (below N):, then display() for every product with quantity below the threshold, in insertion order. If there are none, print none.
• double getTotalValue(): the sum of every product's stock value.
Main (in this order)
1. Create Product("SKU-101", "Notebook", 3.50, 40), Product("SKU-102", "Pen", 1.25, 8), Product("SKU-103", "Backpack", -20.0, 2), Product("SKU-101", "Fake Notebook", 1.00, 5) and Product("SKU-104", "Stapler", 6.00, 3).
2. Fix the backpack with setPrice(29.99).
3. Create new Inventory(3). Add the five products in the order created.
4. Sell 5 of SKU-102, then 5 more of SKU-102, then 1 of SKU-999.
5. Call printLowStock(5).
6. Get the backpack with findBySku("SKU-103") and restock it by 10.
7. Call printLowStock(5) again.
8. Print Total inventory value: $ followed by the total to 2 decimals.

Expected output:
Invalid price
Duplicate SKU: SKU-101
Inventory full
Sold 5 x Pen
Sale failed: SKU-102
Unknown SKU: SKU-999
Low stock (below 5):
SKU-102 | Pen | $1.25 | qty 3
SKU-103 | Backpack | $29.99 | qty 2
Low stock (below 5):
SKU-102 | Pen | $1.25 | qty 3
Total inventory value: $503.63
*/