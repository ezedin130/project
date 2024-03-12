import java.util.InputMismatchException;
import java.util.Scanner;

public class PharmacyManagment {
    public static void main(String[] args) {
        Pharmacy pharmacy = new Pharmacy();
         Scanner scanner = new Scanner(System.in);

           /* pharmacy.addItem(new Item(1,"Aspirin", 100, 45.00,"2025-01-30"));
            pharmacy.addItem(new Item(2,"Paracetamol", 50, 23.00,"2026-01-30"));
            pharmacy.addItem(new Item(3,"Ibuprofen",25,25.0,"2024-12-01"));*/
            try{
            while (true) {
                System.out.println("\n***************************\n");
                System.out.println("\nPharmacy Management System\n");
                System.out.println("1. Sell Item");
                System.out.println("2. Display Inventory");
                System.out.println("3. Display Sales");
                System.out.println("4. Display sales report");
                System.out.println("5. Purchase item");
                System.out.println("6. Remove item");
                System.out.println("7. Search item");
                System.out.println("8. Exit ");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter the item key to sell: ");
                        int itemKey = scanner.nextInt();
                        boolean itemexists = false;
                        for(Item item : pharmacy.getinventory()){
                            if(item.getKey() == itemKey){
                                itemexists = true;
                            }
                        }
                        if(itemexists == true ){
                            System.out.print("Enter quantity to sell: ");
                            int quantity = scanner.nextInt();
                            pharmacy.sellItem(itemKey, quantity);
                            pharmacy.writeReports();
                        }
                        else{
                            System.out.println(itemKey + "  doesn't exist here ");
                        }
                        break;
                    case 2:
                        pharmacy.displayInventory();
                        break;
                    case 3:
                         pharmacy.displaySales();
                         break;    
                    case 4:
                        pharmacy.displaySalesReport();
                        break;
                    case 5:
                    System.out.print("Enter item name to purchase: ");
                    String itemNameToPurchase = scanner.nextLine();
                    Item purchaseItem = new Item(0,itemNameToPurchase, 0, 0.0, null);
                    System.out.print("Enter quantity to purchase: ");
                    int purchaseQuantity = scanner.nextInt();
                    pharmacy.purchaseItem(purchaseItem, purchaseQuantity);
                    break;
                    case 6:
                    System.out.println("Enter the item key to remove");
                    int Key = scanner.nextInt();
                    pharmacy.removeItem(Key);
                    break;

                    case 7:
                    
                    System.out.println("Enter the name of the item you want to search");
                    String itemName = scanner.nextLine();
                    pharmacy.search(itemName);
                    break;    
                    case 8:
                    pharmacy.writeData();
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("INCORRECT CHOICE");
        }
        finally{
            scanner.close();
        }
    
    }
}
