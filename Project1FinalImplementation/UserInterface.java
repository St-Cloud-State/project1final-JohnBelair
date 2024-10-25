import java.util.Scanner;

public class UserInterface {
    private Warehouse warehouse;
    private Scanner scanner;

    public UserInterface() {
        warehouse = new Warehouse();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addClient();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    addWishlist();
                    break;
                case 4:
                    addOrder();
                    break;
                case 5:
                    displayAllClients();
                    break;
                case 6:
                    displayAllProducts();
                    break;
                case 7:
                    displayWishlist();
                    break;
                case 8:
                    displayOrderById();
                    break;
                case 9:
                    displayWaitlist();
                    break;
                case 10:
                    receiveShipment();
                    break;
                case 11:
                    recordPayment();
                    break;
                case 12:
                    printInvoicesByClientId();
                    break;
                case 13:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Warehouse Management System ---");
        System.out.println("1. Add Client");
        System.out.println("2. Add Product");
        System.out.println("3. Add Product to Client's Wishlist");
        System.out.println("4. Add Product to Client's Order");
        System.out.println("5. Display All Clients");
        System.out.println("6. Display All Products");
        System.out.println("7. Display Client's Wishlist");
        System.out.println("8. Display Order By Client ID");
        System.out.println("9. Display Waitlist By Client ID");
        System.out.println("10. Receive Shipment to Warehouse");
        System.out.println("11. Record Payment by Client ID");
        System.out.println("12. Display Invoice by Client ID");
        System.out.println("13. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        return Integer.parseInt(scanner.nextLine());
    }

    private void addClient() {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter client address: ");
        String address = scanner.nextLine();
        boolean success = warehouse.addClient(name, address);
        System.out.println(success ? "Client added successfully." : "Failed to add client.");
    }

    private void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter product price: ");
        double price = Double.parseDouble(scanner.nextLine());
        boolean success = warehouse.addProduct(name, quantity, price);
        System.out.println(success ? "Product added successfully." : "Failed to add product.");
    }

    private void addWishlist() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        boolean success = warehouse.addItemToWishlist(clientId, productId, quantity);
        System.out.println(success ? "Product added to wishlist." : "Failed to add product to wishlist.");
    }

    private void addOrder() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        boolean success = warehouse.createOrderFromWishlist(clientId);
        System.out.println(success ? "Order created from wishlist." : "Failed to create order.");
    }

    private void displayAllClients() {
        warehouse.displayClients();
    }

    private void displayAllProducts() {
        warehouse.displayProducts();
    }

    private void displayWishlist() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        warehouse.displayWishlist(clientId);
    }

    private void displayOrderById() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        warehouse.displayOrders(clientId);
    }

    private void displayWaitlist() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        warehouse.displayWaitlist(clientId);
    }

    private void receiveShipment(){
        System.out.print("Enter product ID:");
        String productId = scanner.nextLine();
        System.out.print("Enter product QTY:");
        int productQty = Integer.parseInt(scanner.nextLine());
        warehouse.receiveShipmentFromUser(productId, productQty);
    }

    private void recordPayment() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine(); // Get client ID input
        System.out.print("Enter payment amount: ");
        double amount = Double.parseDouble(scanner.nextLine()); // Get payment amount
        warehouse.recordPayment(clientId, amount); // Call warehouse to handle payment
    }

    private void printInvoicesByClientId() {
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine();
        warehouse.printInvoicesByClientId(clientId);
    }
    
    
}


