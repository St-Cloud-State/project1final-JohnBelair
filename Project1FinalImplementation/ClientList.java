import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ClientList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Client> clients;

    public ClientList() {
        clients = new LinkedList<>();
    }

    public boolean addClient(Client client) {
        return clients.add(client);
    }

    public Client getClient(String clientId) {
        for (Client client : clients) {
            if (client.getId().equals(clientId)) {
                return client;
            }
        }
        return null; // Client not found
    }

    public boolean isEmpty() {
        return clients.isEmpty();
    }

    public void displayAllClients() {
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            for (Client client : clients) {
                System.out.println(client);
            }
        }
    }

    public String[] getClientIds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientIds'");
    }
}