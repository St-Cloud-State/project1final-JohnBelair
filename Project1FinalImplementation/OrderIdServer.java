import java.io.Serializable;
import java.io.IOException;

public class OrderIdServer implements Serializable {
    private static final long serialVersionUID = 1L;
    private static OrderIdServer server;
    private int idCounter;


    private OrderIdServer() {
        idCounter = 1; // Initial id for orders
    }

    public static OrderIdServer instance() {
        if (server == null) {
            server = new OrderIdServer();
        }
        return server;
    }

    public String getId() {
        return "O" + idCounter++; // Appends O to the front of the id creating unique order ids
    }

    // Serialization
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (server == null) {
            server = this;
        }
    }
}
