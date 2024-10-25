import java.util.*;

public class Waitlist {
    private Map<String, List<WaitlistItem>> waitlistMap;
    

    public Waitlist() {
        waitlistMap = new HashMap<>();
    }

    // Add items to waitlist
    public void addToWaitlist(String clientId, String productId, int quantity) {
        List<WaitlistItem> clientWaitlist = waitlistMap.getOrDefault(clientId, new ArrayList<>());
        clientWaitlist.add(new WaitlistItem(productId, quantity));
        waitlistMap.put(clientId, clientWaitlist);
    }

    // Get waitlist for a specific client
    public List<WaitlistItem> getWaitlistForClient(String clientId) {
        return waitlistMap.getOrDefault(clientId, new ArrayList<>());
    }

    // Method to fulfill items from the waitlists based on specified quantity and productId
    public int fulfillProduct(String productId, int productQty, Warehouse warehouse) {
    int remainingQty = productQty;

        // Iterate over the waitlist for each client
        for (Map.Entry<String, List<WaitlistItem>> entry : waitlistMap.entrySet()) {
            List<WaitlistItem> clientWaitlist = entry.getValue(); // Get the client's waitlist
            Iterator<WaitlistItem> iterator = clientWaitlist.iterator(); // Use an iterator to safely remove items

            // Check each waitlist item for the specified productId
            while (iterator.hasNext() && remainingQty > 0) {
                WaitlistItem item = iterator.next();

                // If the item matches the productId
                if (item.getProductId().equals(productId)) {
                    int itemQty = item.getQuantity();

                    // Fulfill the waitlist item fully or partially
                    if (itemQty <= remainingQty) {
                        // Fully fulfill the item, subtract its quantity from remainingQty
                        remainingQty -= itemQty;
                       // Create a new order for the fulfilled quantity
                        warehouse.addOrder(entry.getKey(), productId, itemQty);
                        iterator.remove(); // Remove the item since it is fully fulfilled
                    } else {
                        // Partially fulfill the item, reduce its quantity
                        item.setQuantity(itemQty - remainingQty);
                        // Create a new order for the fulfilled quantity
                        warehouse.addOrder(entry.getKey(), productId, remainingQty);
                        remainingQty = 0; // All the productQty has been used up
                    }

                }
            }

            // After processing, update the waitlist for this client (if needed)
            waitlistMap.put(entry.getKey(), clientWaitlist);
        }

        return remainingQty; // Return any unfulfilled quantity, if there's any left
    }
}
