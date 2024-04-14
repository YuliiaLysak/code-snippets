package edu.lysak.patterns.strategy.payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * World first console e-commerce application.
 */
public class Main {
    private static final Map<Integer, Integer> PRICE_ON_PRODUCTS = new HashMap<>();
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static final Order ORDER = new Order();
    private static PayStrategy strategy;

    static {
        PRICE_ON_PRODUCTS.put(1, 2200);
        PRICE_ON_PRODUCTS.put(2, 1850);
        PRICE_ON_PRODUCTS.put(3, 1100);
        PRICE_ON_PRODUCTS.put(4, 890);
    }

    public static void main(String[] args) throws IOException {
        while (!ORDER.isClosed()) {
            int cost;

            String continueChoice;
            do {
                System.out.print("""
                    Please, select a product:
                    1 - Mother board
                    2 - CPU
                    3 - HDD
                    4 - Memory
                    """);
                int choice = Integer.parseInt(READER.readLine());
                cost = PRICE_ON_PRODUCTS.get(choice);
                System.out.print("Count: ");
                int count = Integer.parseInt(READER.readLine());
                ORDER.setTotalCost(cost * count);
                System.out.print("Do you wish to continue selecting products? Y/N: ");
                continueChoice = READER.readLine();
            } while (continueChoice.equalsIgnoreCase("Y"));

            if (strategy == null) {
                System.out.println("""
                    Please, select a payment method:
                    1 - PalPay
                    2 - Credit Card""");
                String paymentMethod = READER.readLine();

                // Client creates different strategies based on input from user,
                // application configuration, etc.
                if (paymentMethod.equals("1")) {
                    strategy = new PayByPayPal();
                } else {
                    strategy = new PayByCreditCard();
                }
            }

            // Order object delegates gathering payment data to strategy object,
            // since only strategies know what data they need to process a
            // payment.
            ORDER.processOrder(strategy);

            System.out.print("Pay " + ORDER.getTotalCost() + " units or Continue shopping? P/C: ");
            String proceed = READER.readLine();
            if (proceed.equalsIgnoreCase("P")) {
                // Finally, strategy handles the payment.
                if (strategy.pay(ORDER.getTotalCost())) {
                    System.out.println("Payment has been successful.");
                } else {
                    System.out.println("FAIL! Please, check your data.");
                }
                ORDER.setClosed();
            }
        }
    }
}
