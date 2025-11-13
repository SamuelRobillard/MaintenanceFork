package service;

import model.CartItem;

// ===================
// ORDER SERVICE
// ===================
public class OrderService implements IOrderService {

    private final ICartService cartService;

    public OrderService(ICartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void placeOrder() {
        try {
            if (cartService.getCart().isEmpty()) {
                System.out.println("\nPanier vide !");
                return;
            }

            boolean isStockSufficient = true;

            // Vérification du stock pour chaque item du panier
            for (CartItem cartItem : cartService.getCart()) {
                if (cartItem.isTrio()) {
                    if (cartItem.getItem().getStock() <= 0 ||
                            cartItem.getTrioSnack().getStock() <= 0 ||
                            cartItem.getTrioDrink().getStock() <= 0) {
                        isStockSufficient = false;
                        System.out.println("Stock insuffisant pour " + cartItem.getDescription());
                    }
                } else if (cartItem.getItem().getStock() <= 0) {
                    isStockSufficient = false;
                    System.out.println("Stock insuffisant pour " + cartItem.getItem().getName());
                }
            }

            if (!isStockSufficient) return;

            double totalPrice = 0;
            System.out.println("\n========= REÇU =========");

            // Décrémentation du stock et affichage du reçu
            for (CartItem cartItem : cartService.getCart()) {
                try {
                    if (cartItem.isTrio()) {
                        cartItem.getItem().setStock(cartItem.getItem().getStock() - 1);
                        cartItem.getTrioSnack().setStock(cartItem.getTrioSnack().getStock() - 1);
                        cartItem.getTrioDrink().setStock(cartItem.getTrioDrink().getStock() - 1);
                    } else {
                        cartItem.getItem().setStock(cartItem.getItem().getStock() - 1);
                    }
                    System.out.printf("%s - %.2f$\n", cartItem.getDescription(), cartItem.getPrice());
                    totalPrice += cartItem.getPrice();
                } catch (Exception e) {
                    System.out.println("Erreur lors de la mise à jour du stock ou de l'affichage d'un item: " + e.getMessage());
                }
            }

            System.out.printf("------------------------\nTOTAL: %.2f$\n", totalPrice);
            System.out.println("========================");

            // Vider le panier après la commande
            cartService.clearCart();

        } catch (Exception e) {
            System.out.println("Erreur lors du traitement de la commande: " + e.getMessage());
        }
    }
}
