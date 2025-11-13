package service;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryService implements IInventoryService {

    private final List<Item> items = new ArrayList<>();

    @Override
    public void addDefaultItems() {
        try {
            items.add(new MainFood("Big Mac", 6.99, 50));
            items.add(new MainFood("Quarter Pounder", 7.49, 40));
            items.add(new MainFood("McChicken", 5.99, 45));
            items.add(new Snack("Frites", 3.49, 100));
            items.add(new Snack("Nuggets (6)", 4.99, 60));
            items.add(new Drink("Coca-Cola", 2.49, 80, "Medium"));
            items.add(new Drink("Sprite", 2.49, 70, "Medium"));
            items.add(new Drink("Jus d'orange", 2.99, 50, "Medium"));
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout des items par défaut: " + e.getMessage());
        }
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList<>(items); // retourne une copie pour éviter modification externe
    }

    @Override
    public List<Item> getByType(String type) {
        try {
            List<Item> result = new ArrayList<>();
            for (Item item : items) {
                if (item.getType().equalsIgnoreCase(type)) {
                    result.add(item);
                }
            }
            return result;
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des items par type: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void printInventory() {
        try {
            System.out.println("\n--- STOCK ACTUEL ---");
            if (items.isEmpty()) {
                System.out.println("L'inventaire est vide.");
                return;
            }
            for (Item item : items) {
                System.out.printf("%s: %d unités (%.2f$)\n",
                        item.getName(), item.getStock(), item.getPrice());
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'affichage de l'inventaire: " + e.getMessage());
        }
    }

    @Override
    public void addItem(Item item) {
        try {
            items.add(item);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout d'un item: " + e.getMessage());
        }
    }

    @Override
    public void addStock(String name, int quantity) {
        try {
            boolean found = false;
            for (Item item : items) {
                if (item.getName().equalsIgnoreCase(name)) {
                    item.addStock(quantity);
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Item non trouvé pour ajouter le stock !");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de stock: " + e.getMessage());
        }
    }

    @Override
    public void removeStock(String name, int quantity) {
        try {
            boolean found = false;
            for (Item item : items) {
                if (item.getName().equalsIgnoreCase(name)) {
                    if (item.getStock() >= quantity) {
                        item.removeStock(quantity);
                    } else {
                        System.out.println("Pas assez de stock pour retirer !");
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Item non trouvé pour retirer le stock !");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors du retrait de stock: " + e.getMessage());
        }
    }
}
