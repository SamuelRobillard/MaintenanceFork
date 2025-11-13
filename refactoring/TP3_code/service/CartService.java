package service;

import model.*;
import java.util.*;

public class CartService {
    private static final Scanner sc = app.McDonaldSystem.sc;
    public static final List<CartItem> cart = new ArrayList<>();

    public static void clear() { cart.clear(); }

    public static void printMenu() {
        System.out.println("\n=== MENU ===");
        int i = 1;
        for (Item it : InventoryService.inventory) {
            System.out.printf("%d. %s - %.2f$ (stock: %d)\n", i++, it.name, it.price, it.stock);
        }
    }

    public static void addItem() {
        printMenu();
        System.out.print("Choix: ");
        int idx = sc.nextInt() - 1;

        if (idx >= 0 && idx < InventoryService.inventory.size()) {
            Item item = InventoryService.inventory.get(idx);
            if (item.stock > 0) {
                cart.add(new CartItem(item));
                System.out.println("✓ " + item.name + " ajouté!");
            } else {
                System.out.println("Stock épuisé!");
            }
        }
    }

    public static void addTrio() {
        Item main = select(InventoryService.getByType("main"), "Plat principal");
        Item snack = select(InventoryService.getByType("snack"), "Accompagnement");
        Item drink = select(InventoryService.getByType("drink"), "Boisson");

        if (main != null && snack != null && drink != null)
            cart.add(new CartItem(main, snack, drink));
    }

    private static Item select(List<Item> items, String label) {
        if (items.isEmpty()) return null;
        System.out.println("\n" + label + ":");
        for (int i = 0; i < items.size(); i++)
            System.out.printf("%d. %s (%.2f$)\n", i + 1, items.get(i).name, items.get(i).price);

        System.out.print("Choix: ");
        int c = sc.nextInt() - 1;
        return (c >= 0 && c < items.size()) ? items.get(c) : null;
    }

    public static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Panier vide!");
            return;
        }
        double total = 0;
        int i = 1;
        for (CartItem ci : cart) {
            System.out.printf("%d. %s - %.2f$\n", i++, ci.getDescription(), ci.getPrice());
            total += ci.getPrice();
        }
        System.out.printf("TOTAL: %.2f$\n", total);
    }

    public static void removeItem() {
        viewCart();
        System.out.print("Numéro à retirer: ");
        int c = sc.nextInt() - 1;
        if (c >= 0 && c < cart.size()) {
            System.out.println("Retiré: " + cart.remove(c).getDescription());
        }
    }
}
