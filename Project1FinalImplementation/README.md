# Final Implementation for Project 1.

**Complete by October 25 (Friday). One submission per group.**

This is to be done on Github, one per group. The assignment requires you to create a final
version of the program. Start with the code developed for the Stage 1 implementation and add
the necessary code for the features designed in Stage 2
## Features to Implement

### 1. Simple Version of Warehouse (75 points, no waitlists)

In the simple version, we don’t
have waitlists. If a product runs short or is out of stock when an order is placed, the
unfulfilled part of the order is put back on the wishlist. When a shipment of a product is
received, the quantity of product that arrives is added to the stock for the product. Orders
that have not been filled will have to be individually processed again, when needed.

### 2. Full Version of Warehouse (150 points)

In the full version, all the features are
implemented. If a product runs short or is out of stock when an order is placed, the
unfulfilled part of the order is added to the waitlist for the product. When a shipment of a
product is received, the waitlist items are filled first. For each waitlist item filled, an
invoice is generated for the associated customer. The remaining quantity of product is
added to the stock for the product.

## How to Demonstrate Testing?

1. Start a script session in your folder:

    ```bash
    script warehouseTest.txt
    ```

2. Compile the program:

    ```bash
    javac *.java
    ```

3. Run the program:

    ```bash
    java UserInterface
    ```

4. Perform the following sequence of operations through the UI's menu options.

### Test Case Sequence

- **Create five clients**: C1 through C5. 
- **Print/display all clients**: (Should show the credit/debit balance for each client). 
- **Create five products**: P1 through P5, with quantities 10, 20, 30, 40, 50 respectively and unit prices $1, $2, $3, $4, $5 respectively
- **Print/display all products**: (should show price and qty for each)
- **Add to C1's wishlist**: 5 each of P1, P3, and P5.
- **Print C1's wishlist**.
- **Add to C2's wishlist**: 7 each of P1, P2, and P4.
- **Print C2's wishlist**.
- **Add to C3's wishlist**: 6 each of P1, P2, and P5.
- **Print C3's wishlist**.
- **Place order for C2**:  buy everything (if available) in wishlist
- **Print/display all clients**: (should show the credit/debit balance for each)
- **Place order for C3**:  buy everything (if available) in wishlist
- **Print/display all clients**: (should show the credit/debit balance for each)
- **Print C2's wishlist**.
- **Print C3's wishlist**.
- **Print P1's waitlist** (for full version of program).
- **Print P2's waitlist** (for full version of program).
- **Place order for C1**:  buy everything (if available) in wishlist
- **Print/display all clients**: (should show the credit/debit balance for each)
- **Print C1's wishlist**.
- **Record receipt**: $100 payment for C1 and C2.
- **Print/display all clients**: (should show the credit/debit balance for each)
- **Receive a shipment of 100 items of P1**.
- **Print/display all products**: (should show price and qty for each)
- **Print/display all clients**: (should show the credit/debit balance for each)
- **Print all invoices for C1**.
- **Print all invoices for C2**.
