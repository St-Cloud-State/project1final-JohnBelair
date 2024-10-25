import java.io.Serializable;

public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String id;
    private Wishlist wishlist;
    private double balance; 

    public Client(String name, String address) {
        this.name = name;
        this.address = address;
        this.id = ClientIdServer.instance().getId(); 
        this.wishlist = new Wishlist();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void addToWishlist(Product product) {
        wishlist.addProduct(product);
    }



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Client) {
            Client other = (Client) obj;
            return this.id.equals(other.id);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Client [ID: " + id + ", Name: " + name + ", Address: " + address + ", Balance: $" + balance + "]";
    }
    
}
