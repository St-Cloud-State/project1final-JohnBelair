import java.util.*;

public class Warehouse {
    ClientList newClientList = new ClientList();
    ProductList newProductList = new ProductList();
    OrderList newOrderList = new OrderList();
    Waitlist waitlist = new Waitlist();  // Integrate Waitlist
    InvoiceList invoiceList = new InvoiceList(); // InvoiceList to manage invoices

    

    // Add a new client to the warehouse
    public boolean addClient(String name, String address) {
        Client newClient = new Client(name, address);
        return newClientList.addClient(newClient);
    }

    // Add a new product to the warehouse
    public boolean addProduct(String name, int quantity, double price) {
        Product newProduct = new Product(name, quantity, price);
        return newProductList.addProduct(newProduct);
        
    }

    // Add item to wishlist
    public boolean addItemToWishlist(String clientID, String productID, int quantity) {
        Client client = getClientById(clientID);
        if (client != null) {
            Product product = newProductList.searchProductById(productID);
            if (product != null) {
                Product wishlistItem = new Product(product.getName(), quantity, product.getId(), product.getSalePrice());
                client.addToWishlist(wishlistItem);
                return true;
            }
        }
        return false;
    }

    // Add a new order
    public boolean addOrder(String clientId, String productId, int quantity) {

        Client client = getClientById(clientId);
        Product product = newProductList.searchProductById(productId);

        double totalOrderCost = product.getSalePrice() * quantity;

            // Generate and store invoice
            List<Product> orderedProducts = new ArrayList<>();
            orderedProducts.add(product);
            Invoice invoice = new Invoice(client, orderedProducts, totalOrderCost);
            invoiceList.addInvoice(invoice);

            // Deduct total order cost from client balance
            client.setBalance(client.getBalance() - totalOrderCost);

            System.out.println("Order placed for client " + client.getName() + ". Total cost: $" + String.format("%.2f", totalOrderCost));
            System.out.println("New balance: $" + String.format("%.2f", client.getBalance()));

            return true;
    }

    // Display all clients
    public void displayClients() {
        if (newClientList.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            newClientList.displayAllClients();
        }
    }

    // Display all products
    public void displayProducts() {
        if (newProductList.isEmpty()) {
            System.out.println("No products found.");
        } else {
            newProductList.displayAllProducts();
        }
    }

    // Get client by ID
    public Client getClientById(String clientId) {
        return newClientList.getClient(clientId);
    }

    // Display wishlist for a client
    public void displayWishlist(String clientId) {
        Client client = getClientById(clientId);
        if (client != null) {
            System.out.println("Wishlist for client " + client.getName() + ":");
            client.getWishlist().displayAllProducts();
        } else {
            System.out.println("Client not found.");
        }
    }

    public boolean createOrderFromWishlist(String clientId) {
        Client client = getClientById(clientId);
        if (client != null) {
            List<Product> clientItemList = client.getWishlist().getProducts();
            double totalOrderCost = 0;
            List<Product> orderedProducts = new ArrayList<>();
    
            for (Product item : clientItemList) {
                int availableQuantity = newProductList.getQtyById(item.getId());
    
                if (availableQuantity >= item.getQuantity()) {
                    Order newOrder = new Order(clientId, item.getId(), item.getQuantity());
                    newOrderList.addOrder(newOrder);
                    newProductList.setQtyById(item.getId(), availableQuantity - item.getQuantity());
                    totalOrderCost += item.getSalePrice() * item.getQuantity();
                    orderedProducts.add(item);
                } else if (availableQuantity > 0) {
                    Order partialOrder = new Order(clientId, item.getId(), availableQuantity);
                    newOrderList.addOrder(partialOrder);
                    newProductList.setQtyById(item.getId(), 0);
                    int remainingQuantity = item.getQuantity() - availableQuantity;
                    waitlist.addToWaitlist(clientId, item.getId(), remainingQuantity);
                    totalOrderCost += item.getSalePrice() * availableQuantity;
                    orderedProducts.add(item); // Only add available portion
                } else {
                    waitlist.addToWaitlist(clientId, item.getId(), item.getQuantity());
                }

            }

            client.getWishlist().setWishList(new ArrayList<>());
    
            // Generate and store invoice in InvoiceList
            Invoice invoice = new Invoice(client, orderedProducts, totalOrderCost);
            invoiceList.addInvoice(invoice);
    
            // Deduct total order cost from client balance
            client.setBalance(client.getBalance() - totalOrderCost);
            System.out.println("Order placed for client " + client.getName() + ". Total cost: $" + String.format("%.2f", totalOrderCost));
            System.out.println("New balance: $" + String.format("%.2f", client.getBalance()));
    
            return true;
        }
        return false;
    }
    

    
    // Display waitlist for a client
    public void displayWaitlist(String clientId) {
        Client client = getClientById(clientId);
        if (client != null) {
            List<WaitlistItem> waitlistedItems = waitlist.getWaitlistForClient(clientId);
            if (waitlistedItems.isEmpty()) {
                System.out.println("No items on the waitlist for client " + client.getName() + ".");
            } else {
                System.out.println("Waitlist for client " + client.getName() + ":");
                for (WaitlistItem item : waitlistedItems) {
                    Product product = newProductList.searchProductById(item.getProductId());
                    System.out.println("Product: " + product.getName() + " | Quantity: " + item.getQuantity());
                }
            }
        } else {
            System.out.println("Client not found.");
        }
    }

    // Display orders for a client
    public void displayOrders(String clientId) {
        if (newOrderList.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            newOrderList.displayOrders(clientId);
        }
    }

    // Receive shipment to warehouse
    public void receiveShipmentFromUser(String productId, int productQty){
        //Check if any products from the waitlist need to be fufilled
        int remainingQty = waitlist.fulfillProduct(productId, productQty, this);
        // Add the remaining QTY to the product in the product list
        newProductList.setQtyById(productId, remainingQty);
        System.out.println("Shipment of " + productId + " of amount " + productQty + " received.");
    }

    public void recordPayment(String clientId, double amount) {
        Client client = getClientById(clientId); // Get the client by their ID
        if (client != null) {
            client.setBalance(client.getBalance() + amount); // Add payment to balance
            System.out.println("Payment of $" + amount + " recorded for " + client.getName() + ". New balance: $" + client.getBalance());
        } else {
            System.out.println("Client not found.");
        }
    }

    public void printInvoicesByClientId(String clientId) {
        List<Invoice> clientInvoices = invoiceList.getInvoicesByClientId(clientId);
        if (clientInvoices.isEmpty()) {
            System.out.println("No invoices found for client ID: " + clientId);
        } else {
            System.out.println("Invoices for client ID: " + clientId);
            for (Invoice invoice : clientInvoices) {
                System.out.println(invoice);
            }
        }
    }
    
    
}





