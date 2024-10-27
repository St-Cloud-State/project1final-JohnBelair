import java.io.Serializable;
import java.io.IOException;

public class ProductIdServer implements Serializable {
    private static final long serialVersionUID = 1L;
    private static ProductIdServer server;
    private int idCounter;


    private ProductIdServer() {
        idCounter = 1; // Starting ID
    }

    public static ProductIdServer instance() {
        if (server == null) {
            server = new ProductIdServer();
        }
        return server;
    }

    public String getId() {
        return "P" + idCounter++; // Returns a unique product ID, e.g., P1, P2, P3, etc.
    }

    // For serialization purposes
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (server == null) {
            server = this;
        }
    }
}
