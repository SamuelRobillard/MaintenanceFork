package service;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class CartService implements ICartService {

    private final List<CartItem> cartItems = new ArrayList<>();

    @Override
    public void clearCart() {
        try {
            cartItems.clear();
        } catch (Exception e) {
            System.out.println("Erreur lors de la réinitialisation du panier: " + e.getMessage());
        }
    }

    @Override
    public void printMenu() {
        try {
            System.out.println("\n=== MENU ===");
            int index = 1;
            for (CartItem cartItem : cartItems) {
                System.out.printf("%d. %s - %.2f$\n", index++, cartItem.getDescription(), cartItem.getPrice());
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage du menu: " + e.getMessage());
        }
    }

    @Override
    public void addItem(Item item) {
        try {
            if (item.getStock() > 0) {
                cartItems.add(new CartItem(item));
                System.out.println("✓ " + item.getName() + " ajouté !");
            } else {
                System.out.println("Stock épuisé !");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de l'item: " + e.getMessage());
        }
    }

    @Override
    public void addTrio(Item mainItem, Item snackItem, Item drinkItem) {
        try {
            if (mainItem != null && snackItem != null && drinkItem != null) {
                cartItems.add(new CartItem(mainItem, snackItem, drinkItem));
                System.out.println("✓ Trio ajouté au panier !");
            } else {
                System.out.println("Choix invalide pour le trio.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du trio: " + e.getMessage());
        }
    }

    @Override
    public List<CartItem> getCart() {
        try {
            return cartItems;
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération du panier: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void viewCart() {
        try {
            if (cartItems.isEmpty()) {
                System.out.println("Panier vide !");
                return;
            }

            double totalPrice = 0;
            int index = 1;
            for (CartItem cartItem : cartItems) {
                System.out.printf("%d. %s - %.2f$\n", index++, cartItem.getDescription(), cartItem.getPrice());
                totalPrice += cartItem.getPrice();
            }
            System.out.printf("TOTAL: %.2f$\n", totalPrice);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage du panier: " + e.getMessage());
        }
    }

    @Override
    public void removeItem(int itemIndex) {
        try {
            if (itemIndex >= 0 && itemIndex < cartItems.size()) {
                System.out.println("Retiré: " + cartItems.remove(itemIndex).getDescription());
            } else {
                System.out.println("Choix invalide !");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression de l'item: " + e.getMessage());
        }
    }
}
