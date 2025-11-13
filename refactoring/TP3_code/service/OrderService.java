package service;

import model.CartItem;

public class OrderService {

    public static void placeOrder() {
        if (CartService.getCart().isEmpty()) {
            System.out.println("\nPanier vide !");
            return;
        }

        boolean allStockSufficient = true;

        // Vérification du stock pour chaque élément du panier
        for (CartItem cartItem : CartService.getCart()) {
            if (cartItem.isTrio()) {
                if (cartItem.getItem().getStock() <= 0 ||
                        cartItem.getTrioSnack().getStock() <= 0 ||
                        cartItem.getTrioDrink().getStock() <= 0) {
                    allStockSufficient = false;
                    System.out.println("Stock insuffisant pour le trio : " + cartItem.getDescription());
                }
            } else if (cartItem.getItem().getStock() <= 0) {
                allStockSufficient = false;
                System.out.println("Stock insuffisant pour l'item : " + cartItem.getItem().getName());
            }
        }

        if (!allStockSufficient) return;

        double totalOrderPrice = 0;
        System.out.println("\n========= REÇU =========");

        // Décrémentation du stock et affichage du reçu
        for (CartItem cartItem : CartService.getCart()) {
            try {
                if (cartItem.isTrio()) {
                    cartItem.getItem().setStock(cartItem.getItem().getStock() - 1);
                    cartItem.getTrioSnack().setStock(cartItem.getTrioSnack().getStock() - 1);
                    cartItem.getTrioDrink().setStock(cartItem.getTrioDrink().getStock() - 1);
                } else {
                    cartItem.getItem().setStock(cartItem.getItem().getStock() - 1);
                }
            } catch (Exception e) {
                System.out.println("Erreur lors de la mise à jour du stock pour " + cartItem.getDescription());
                continue;
            }

            System.out.printf("%s - %.2f$\n", cartItem.getDescription(), cartItem.getPrice());
            totalOrderPrice += cartItem.getPrice();
        }

        System.out.printf("------------------------\nTOTAL: %.2f$\n", totalOrderPrice);
        System.out.println("========================");

        // Vider le panier après la commande
        CartService.clear();
    }
}
