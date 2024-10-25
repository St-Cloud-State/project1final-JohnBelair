import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class OrderList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Order> orders;

    public OrderList() {
        orders = new LinkedList<>();
    }

    public boolean addOrder(Order order) {
        return orders.add(order);
    }

    public Order getOrder(String orderId) {
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        return null; // Order not found
    }

   /* void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }*/

   public void setQtyById(String orderId, int newQuantity){
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                order.quantity(newQuantity);
            }
        }
    }

   void displayOrders(String clientId){
    for (Order order : orders) {
            if (order.getClientId().equals(clientId)) {
                System.out.println(order);
            }
        }
   }

    public boolean isEmpty() {
        return orders.isEmpty();
    }
}