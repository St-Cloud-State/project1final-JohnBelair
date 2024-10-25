import java.io.*;

public class ClientIdServer implements Serializable {
    private int idCounter;
    private static ClientIdServer server;

    private ClientIdServer() {
        idCounter = 1; // Starting point for Client ID
    }

    public static ClientIdServer instance() {
        if (server == null) {
            server = new ClientIdServer();
        }
        return server;
    }

    public String getId() {
        return "C" + (idCounter++); // Prefix "C" for client IDs
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(server);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        if (server == null) {
            server = (ClientIdServer) in.readObject();
        }
    }
}