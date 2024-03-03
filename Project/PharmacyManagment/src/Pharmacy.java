import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class Pharmacy {
    public static final String  name = "WOLLO PHARMACY";
    private List<Sale> sales;
    private List<Item> inventory;
    Scanner scanner = new Scanner(System.in);
    public Pharmacy() {
        this.sales = new ArrayList<>();
        this.inventory = new ArrayList<>();
        readData("pharmacymeds.txt");
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
            if (existingItem.getName().equals(item.getName())) {
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
            System.out.println("New item added to inventory with price set.");
        }
    }
    public void sellItem(String itemName, int quantity) {
        for (Item item : inventory) {
            if (item.getName().equals(itemName)) {
                if (item.getQuantity() >= quantity) {
                    item.setQuantity(item.getQuantity() - quantity);
                    System.out.println("Sold " + quantity + " " + itemName + "(s).");
                    double saleAmount = item.getPrice() * quantity;
                     Sale sale = new Sale(itemName, quantity, saleAmount);
                     sales.add(sale);
                     sale.printBill();
                }
                 else {
                    System.out.println("Not enough stock available for " + itemName + ".");
                }
            }
        }
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
        Report dailyReport = new DailyReport(sales);
        Report weeklyReport = new WeeklyReport(sales);
        Report monthlyReport = new MonthlyReport(sales);

        dailyReport.generateReport();
        weeklyReport.generateReport();
        monthlyReport.generateReport();
    }
    public void writeData() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("pharmacymeds.txt"));
            for (Item item : inventory) {
                writer.println(item.getName() + "," + item.getQuantity() + "," + item.getPrice());
            }
            System.out.println("Data written successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing data.");
            e.printStackTrace();
        }
    }
public void readData(String pharmacymeds) {
        try  {
            Scanner scanner = new Scanner(new FileReader("pharmacymeds.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String itemName = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    double price = Double.parseDouble(parts[2].trim());
                    addItem(new Item(itemName, quantity, price));
                } else {
                    System.out.println("Invalid data format in the file: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found ");
        }
    }
    }
    