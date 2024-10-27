import java.util.*;

public class Wishlist {
    private List<Product> products;

    public Wishlist() {
        products = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        return products.add(product);
    }
    
    public boolean removeProduct(Product product) {
        return products.remove(product);
    }
    
    
    public List<Product> getProducts() {
        return products;
    }

    public void setWishList(List<Product> products) {
        this.products = products;
    }

     public void clear() {
        products.clear();
    }
    

    public void displayAllProducts() {
        int total = 0;
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : products) {
                System.out.println(product);
                total += product.getSalePrice() * product.getQuantity();

            }
            System.out.println("Total is: " + total);
        }
    }
}
