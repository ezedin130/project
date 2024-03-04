import java.time.LocalDate;

public class Sale {
    private String itemName;
    private int quantity;
    private double totalAmount;
    private LocalDate saleDate;

    public Sale(LocalDate saledate,String itemName, int quantity, double totalAmount) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.saleDate = LocalDate.now();
    }

    public void printBill() {
        System.out.println("---------------------------------------------------");
        System.out.println(Pharmacy.name);
        System.out.println("Item sold = " + itemName + " with price = " + (totalAmount / quantity));
        System.out.println("Quantity = " + quantity);
        System.out.println("Total Amount = " + totalAmount);
        System.out.println("Sale Date = " + saleDate);
        System.out.println("---------------------------------------------------");
    }
    public String getitemName(){
      return itemName;
    }
    public int getquantity(){
      return quantity;
    }
    public double gettotalAmount(){
      return totalAmount;
    }
    public LocalDate getsaleDate(){
      return saleDate;
    }

    public String toString() {
        return "Item: " + itemName + ", Quantity: " + quantity + ", Total Amount: " + totalAmount + ", Sale Date: " + saleDate;
    }
    
}
