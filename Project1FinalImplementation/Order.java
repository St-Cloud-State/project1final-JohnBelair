import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderId;
    private String clientId;
    private String productId;
    private int quantity;

    public Order(String clientId, String productId, int quantity) {
        this.orderId = OrderIdServer.instance().getId();
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getId() {
        return orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setproductId(String productId) {
        this.productId = productId;
    }

    public void quantity(int quantity) {
        this.quantity = quantity;
    }

    
     

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Order) {
            Order other = (Order) obj;
            return this.orderId.equals(other.orderId);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Order [ID: " + orderId + " Client ID: " + clientId + " Product ID: " + productId + " Product Quantity: " + quantity +"]";
    }
}