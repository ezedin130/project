public class Item {
    private static int nextKey = 1;
    private String name;
    private int quantity;
    private double price;
    private int Key;
    private String expiry_date;

    public Item(int Key,String name, int quantity, double price ,String expiry_date) {
        this.Key = nextKey++;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiry_date = expiry_date;
    }

    public String getName() {
        return name;
    }
    public int getKey() {
        return Key;
    }
    public void setKey(int key) {
        this.Key = key;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }
    public String getexpiry_date() {
        return expiry_date;
    }
    public void setexpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "Item{Key= " + Key + ", name=" + name + ", quantity=" + quantity + ", price='" + price+ ", expiry date="+expiry_date+'}';
    }
}
