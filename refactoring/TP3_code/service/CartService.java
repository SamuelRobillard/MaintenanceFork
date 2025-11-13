package service;

import model.*;
import app.McDonaldSystem;
import java.util.ArrayList;
import java.util.List;

public class CartService {

    private static final List<CartItem> shoppingCart = new ArrayList<>();

    // Vide le panier
    public static void clear() { shoppingCart.clear(); }

    // Affiche le menu complet
    public static void printMenu() {
        System.out.println("\n=== MENU ===");
        int itemNumber = 1;
        for (Item menuItem : InventoryService.inventory) {
            System.out.printf("%d. %s - %.2f$ (stock: %d)\n",
                    itemNumber++, menuItem.getName(), menuItem.getPrice(), menuItem.getStock());
        }
    }

    // Ajoute un item individuel au panier
    public static void addItem(McDonaldSystem app) {
        printMenu();
        System.out.print("Choix: ");
        int itemIndex = app.readInt() - 1;

        if (itemIndex >= 0 && itemIndex < InventoryService.inventory.size()) {
            Item selectedItem = InventoryService.inventory.get(itemIndex);
            if (selectedItem.getStock() > 0) {
                shoppingCart.add(new CartItem(selectedItem));
                System.out.println("✓ " + selectedItem.getName() + " ajouté!");
            } else {
                System.out.println("Stock épuisé!");
            }
        } else {
            System.out.println("Choix invalide!");
        }
    }

    // Ajoute un trio au panier
    public static void addTrio(McDonaldSystem app) {
        Item mainItem = selectItem(app, InventoryService.getByType("main"), "Plat principal");
        Item sideItem = selectItem(app, InventoryService.getByType("snack"), "Accompagnement");
        Item drinkItem = selectItem(app, InventoryService.getByType("drink"), "Boisson");

        if (mainItem != null && sideItem != null && drinkItem != null) {
            shoppingCart.add(new CartItem(mainItem, sideItem, drinkItem));
            System.out.println("✓ Trio ajouté au panier!");
        } else {
            System.out.println("Choix invalide pour le trio.");
        }
    }

    // Méthode générique pour sélectionner un item dans une liste
    private static Item selectItem(McDonaldSystem app, List<Item> items, String categoryLabel) {
        if (items.isEmpty()) return null;

        System.out.println("\n" + categoryLabel + ":");
        for (int i = 0; i < items.size(); i++) {
            Item menuItem = items.get(i);
            System.out.printf("%d. %s - %.2f$\n", i + 1, menuItem.getName(), menuItem.getPrice());
        }

        System.out.print("Choix: ");
        int selectedIndex = app.readInt() - 1;
        return (selectedIndex >= 0 && selectedIndex < items.size()) ? items.get(selectedIndex) : null;
    }

    // Affiche le contenu du panier
    public static void viewCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("Panier vide!");
            return;
        }

        double totalPrice = 0;
        int itemNumber = 1;
        for (CartItem cartItem : shoppingCart) {
            System.out.printf("%d. %s - %.2f$\n", itemNumber++, cartItem.getDescription(), cartItem.getPrice());
            totalPrice += cartItem.getPrice();
        }
        System.out.printf("TOTAL: %.2f$\n", totalPrice);
    }

    // Supprime un item du panier
    public static void removeItem(McDonaldSystem app) {
        viewCart();
        if (shoppingCart.isEmpty()) return;

        System.out.print("Numéro à retirer: ");
        int itemIndex = app.readInt() - 1;

        if (itemIndex >= 0 && itemIndex < shoppingCart.size()) {
            System.out.println("Retiré: " + shoppingCart.remove(itemIndex).getDescription());
        } else {
            System.out.println("Choix invalide!");
        }
    }

    // Getter pour récupérer le panier
    public static List<CartItem> getCart() { return shoppingCart; }
}
