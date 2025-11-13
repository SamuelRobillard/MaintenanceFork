package service;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    public static final List<Item> inventory = new ArrayList<>();

    // Ajoute les items par défaut à l'inventaire
    public static void addDefaultItems() {
        try {
            inventory.add(new MainFood("Big Mac", 6.99, 50));
            inventory.add(new MainFood("Quarter Pounder", 7.49, 40));
            inventory.add(new MainFood("McChicken", 5.99, 45));
            inventory.add(new Snack("Frites", 3.49, 100));
            inventory.add(new Snack("Nuggets (6)", 4.99, 60));
            inventory.add(new Drink("Coca-Cola", 2.49, 80, "Medium"));
            inventory.add(new Drink("Sprite", 2.49, 70, "Medium"));
            inventory.add(new Drink("Jus d'orange", 2.99, 50, "Medium"));
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout des items par défaut : " + e.getMessage());
        }
    }

    // Récupère tous les items d'un type donné
    public static List<Item> getByType(String type) {
        List<Item> filteredItems = new ArrayList<>();
        try {
            for (Item inventoryItem : inventory) {
                if (inventoryItem.getType().equalsIgnoreCase(type)) {
                    filteredItems.add(inventoryItem);
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des items du type '" + type + "' : " + e.getMessage());
        }
        return filteredItems;
    }

    // Affiche le contenu de l'inventaire
    public static void printInventory() {
        System.out.println("\n--- STOCK ACTUEL ---");
        if (inventory.isEmpty()) {
            System.out.println("L'inventaire est vide.");
            return;
        }

        try {
            for (Item inventoryItem : inventory) {
                System.out.printf("%s: %d unités (%.2f$)\n",
                        inventoryItem.getName(), inventoryItem.getStock(), inventoryItem.getPrice());
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage de l'inventaire : " + e.getMessage());
        }
    }
}
