package service;

import model.*;

public class OrderService {
    public static void placeOrder() {
        if (CartService.cart.isEmpty()) {
            System.out.println("\nPanier vide!");
            return;
        }

        boolean stockOk = true;
        for (CartItem ci : CartService.cart) {
            if (ci.isTrio) {
                if (ci.item.stock <= 0 || ci.trioSnack.stock <= 0 || ci.trioDrink.stock <= 0) {
                    stockOk = false;
                    System.out.println("Stock insuffisant pour " + ci.getDescription());
                }
            } else if (ci.item.stock <= 0) {
                stockOk = false;
                System.out.println("Stock insuffisant pour " + ci.item.name);
            }
        }

        if (!stockOk) return;

        for (CartItem ci : CartService.cart) {
            if (ci.isTrio) {
                ci.item.stock--;
                ci.trioSnack.stock--;
                ci.trioDrink.stock--;
            } else {
                ci.item.stock--;
            }
        }

        double total = 0;
        System.out.println("\n========= REÇU =========");
        for (CartItem ci : CartService.cart) {
            System.out.printf("%s - %.2f$\n", ci.getDescription(), ci.getPrice());
            total += ci.getPrice();
        }
        System.out.printf("------------------------\nTOTAL: %.2f$\n", total);
        System.out.println("========================");
        CartService.clear();
    }
}
