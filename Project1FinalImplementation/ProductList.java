import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Product> products;

    public ProductList() {
        products = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }

    public boolean removeProduct(Product product) {
        return products.remove(product);
    }

    public Product searchProductById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null; // Return null if no product is found with the given ID
    }

    public int getQtyById(String productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product.getQuantity();
            }
        }
        return -1; // Return -1 if product not found
    }

    public void setQtyById(String productId, int newQuantity){
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                product.setQuantity(newQuantity);
            }
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

   public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}
