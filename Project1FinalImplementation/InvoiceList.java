import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InvoiceList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Invoice> invoices;

    public InvoiceList() {
        invoices = new ArrayList<>();
    }

    // Add a new invoice to the list
    public boolean addInvoice(Invoice invoice) {
        return invoices.add(invoice);
    }

    // Get all invoices for a specific client by clientId
    public List<Invoice> getInvoicesByClientId(String clientId) {
        List<Invoice> clientInvoices = new ArrayList<>();
        for (Invoice invoice : invoices) {
            if (invoice.getClient().getId().equals(clientId)) {
                clientInvoices.add(invoice);
            }
        }
        return clientInvoices;
    }

    // Check if the invoice list is empty
    public boolean isEmpty() {
        return invoices.isEmpty();
    }
}

