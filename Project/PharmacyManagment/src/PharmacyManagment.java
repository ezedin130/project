import java.util.InputMismatchException;
import java.util.Scanner;

public class PharmacyManagment {
    public static void main(String[] args) {
        Pharmacy pharmacy = new Pharmacy();
         Scanner scanner = new Scanner(System.in);
            
            /*pharmacy.addItem(new Item("Aspirin", 100, 45.00));
            pharmacy.addItem(new Item("Paracetamol", 50, 23.00));
            pharmacy.addItem(new Item("Ibuprofen",25,25.0));*/
            try{
            while (true) {
                System.out.println("\n***************************\n");
                System.out.println("\nPharmacy Management System\n");
                System.out.println("1. Sell Item");
                System.out.println("2. Display Inventory");
                System.out.println("3. Display Sales");
                System.out.println("4. Display sales report");
                System.out.println("5. Purchase item");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter item name to sell: ");
                        String itemName = scanner.nextLine();
                        boolean itemexists = false;
                        for(Item item : pharmacy.getinventory()){
                            if(item.getName().equals(itemName)){
                                itemexists = true;
                            }
                        }
                        if(itemexists == true ){
                            System.out.print("Enter quantity to sell: ");
                            int quantity = scanner.nextInt();
                            pharmacy.sellItem(itemName, quantity);
                        }
                        else{
                            System.out.println(itemName + "  doesn't exist here ");
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
                    Item purchaseItem = new Item(itemNameToPurchase, 0, 0.0);
                    System.out.print("Enter quantity to purchase: ");
                    int purchaseQuantity = scanner.nextInt();
                    pharmacy.purchaseItem(purchaseItem, purchaseQuantity);
                    break;
                    case 6:
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
