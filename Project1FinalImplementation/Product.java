import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int quantity;
    private double salePrice;
    private String id;

    public Product(String name, int quantity, double salePrice) {
        this.name = name;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.id = ProductIdServer.instance().getId(); // Generates a unique product ID
    }

    public Product(String name, int quantity, String id,double price) {
        this.name = name;
        this.quantity = quantity;
        this.id = id;
        this.salePrice = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product other = (Product) obj;
            return this.id.equals(other.id);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Product [ID: " + id + ", Name: " + name + ", Quantity: " + quantity + ", Sale Price: $" + salePrice + "]";
    }
}
