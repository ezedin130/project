import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
public class Pharmacy {
    public static final String  name = "WOLLO PHARMACY";
    private List<Sale> sales;
    private List<Item> inventory;
    Scanner scanner = new Scanner(System.in);
    public Pharmacy() {
        this.sales = new ArrayList<>();
        this.inventory = new ArrayList<>();
        readData();
        readReports();
    }
    public List<Item> getinventory(){
        return inventory;
    }
    public List<Sale> getSales() {
        return sales;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }
    public void purchaseItem(Item item, int quantity) {
        boolean itemExists = false;
        for (Item existingItem : inventory) {
            if (existingItem.getName().equalsIgnoreCase(item.getName())) {
                itemExists = true;
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                System.out.println("Purchase successful: " + quantity + " " + item.getName() + "(s) added to inventory.");
                break;
            }
        }
        if (!itemExists) {
            inventory.add(item);
            item.setQuantity(quantity);
            System.out.print("Enter price per unit for the new item: ");
            double pricePerUnit = scanner.nextDouble();
            item.setPrice(pricePerUnit);
            scanner.nextLine();
            System.out.println("Enter the new expiry date");
            String expiry_date = scanner.nextLine();
            item.setexpiry_date(expiry_date);
            System.out.println("New item added to inventory succesfully.");
        }
    }
    public void sellItem(int itemKey, int quantity) {
        for (Item item : inventory) {
            if(itemKey == item.getKey())
                if (item.getQuantity() >= quantity) {
                    
                    item.setQuantity(item.getQuantity() - quantity);
                    System.out.println("Sold " + quantity + " " + item.getName() + "(s)." );
                    double saleAmount = item.getPrice() * quantity;
                    LocalDate today = LocalDate.now();
                    Sale sale = new Sale(itemKey ,today, item.getName(), quantity, saleAmount);
                     sales.add(sale);
                     sale.printBill();
                }
                 else {
                    System.out.println("Not enough stock available for " + item.getName() + ".");
                }
            
        }
    }
    public void removeItem(int Key) {
        Iterator<Item> iterator = inventory.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getKey()== Key) {
                iterator.remove();
                System.out.println("Item '" + item.getName() + "' removed from inventory.");
                return; 
            }
        }
        System.out.println("Item '" + Key + "' not found in inventory.");
    }
    public void search(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                // Display item details if found
                System.out.println("Item details:");
                System.out.println("Key: " + item.getKey());
                System.out.println("Name: " + item.getName());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Price: " + item.getPrice());
                System.out.println("Expiry Date: " + item.getexpiry_date());
                
                return; 
            }
        }
        System.out.println("Item '" + itemName + "' not found.");
    }
    public void displayInventory() {
        System.out.println("Inventory:");
        for (Item item : inventory) {
            System.out.println(item);
        }
    }
    public void displaySales(){
        System.out.println("Sales:");
        for (Sale sales : sales) {
            System.out.println(sales);
        }
    }
    public void displaySalesReport() {
       LocalDate today = LocalDate.now();

    LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
    LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);
    LocalDate startOfMonth = today.withDayOfMonth(1);
    LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

    

      Report dailyReport = new DailyReport(sales);
      Report weeklyReport = new WeeklyReport(sales,startOfWeek,endOfWeek);
      Report monthlyReport = new MonthlyReport(sales , startOfMonth , endOfMonth);

      dailyReport.generateReport();
      weeklyReport.generateReport();
      monthlyReport.generateReport();
  }
    public void writeData() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("pharmacymeds.txt"));
            for (Item item : inventory) {
                writer.println(item.getKey()+","+item.getName() + "," + item.getQuantity() + "," + item.getPrice()+","+item.getexpiry_date());
            }
            System.out.println("Data written successfully.");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error occurred while writing data.");
            //e.printStackTrace();
        }
    }
public void readData() {
        try  {
            Scanner scanner = new Scanner(new FileReader("pharmacymeds.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int key = Integer.parseInt(parts[0].trim());
                    String itemName = parts[1].trim();
                    int quantity = Integer.parseInt(parts[2].trim());
                    double price = Double.parseDouble(parts[3].trim());
                    String expiry_date = parts[4].trim();
                    addItem(new Item(key,itemName, quantity, price,expiry_date));
                } else {
                    System.out.println("Invalid data format in the file: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found ");
        }
    }
    public void writeReports() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("sales_reports.txt"))) {
            for (Sale sale : sales) {
                writer.println( sale.getitemKey()+ "," + sale.getsaleDate() + "," + sale.getitemName() + "," + sale.getquantity() + "," + sale.gettotalAmount());
            }
            System.out.println("Sales reports written successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing sales reports.");
            e.printStackTrace();
        }
    }

    public void readReports() {
        try (Scanner scanner = new Scanner(new FileReader("sales_reports.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 5 ) {
                    int Key = Integer.parseInt(parts[0].trim());
                    LocalDate date = LocalDate.parse(parts[1].trim());
                    String itemName = parts[2].trim();
                    int quantity = Integer.parseInt(parts[3].trim());
                    double totalAmount = Double.parseDouble(parts[4].trim());
                    sales.add(new Sale( Key,date, itemName, quantity, totalAmount));
                } else {
                    System.out.println("Invalid data format in the sales reports file: " + line);
                }
            }
            System.out.println("Sales reports read successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Sales reports file not found.");
        }
    }
    }
    