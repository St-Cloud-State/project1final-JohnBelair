import java.io.Serializable;
import java.util.List;

public class Invoice implements Serializable {
    private static final long serialVersionUID = 1L;
    private Client client;
    private List<Product> products;
    private double totalPrice;

    public Invoice(Client client, List<Product> products, double totalPrice) {
        this.client = client;
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        StringBuilder invoiceString = new StringBuilder("Invoice for Client [ID: " + client.getId() + ", Name: " + client.getName() + ", Address: " + client.getAddress() + "]\n");
        invoiceString.append("Products ordered:\n");
        for (Product product : products) {
            invoiceString.append("Product [ID: " + product.getId() + ", Name: " + product.getName() + ", Quantity: " + product.getQuantity() + ", Price per unit: $" + product.getSalePrice() + "]\n");
        }
        invoiceString.append("Total Price: $" + String.format("%.2f", totalPrice) + "\n");
        return invoiceString.toString();
    }
}

