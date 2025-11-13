package ui;

import app.McDonaldSystem;
import service.InventoryService;
import model.Drink;
import model.MainFood;
import model.Snack;
import model.Item;

public class InventoryMode {

    private final McDonaldSystem app;

    public InventoryMode(McDonaldSystem app) {
        this.app = app;
    }

    public void start() {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== INVENTAIRE ===");
            System.out.println("1. Afficher inventaire");
            System.out.println("2. Ajouter stock");
            System.out.println("3. Retirer stock");
            System.out.println("4. Ajouter nouvel item");
            System.out.println("5. Retour");
            System.out.print("Choix: ");

            int userChoice = 0;
            try {
                userChoice = app.readInt();
            } catch (Exception e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                app.getScanner().nextLine(); // consommer l'entrée invalide
                continue;
            }

            switch (userChoice) {
                case 1 -> InventoryService.printInventory();
                case 2 -> addStock();
                case 3 -> removeStock();
                case 4 -> addNewItem();
                case 5 -> isRunning = false;
                default -> System.out.println("Choix invalide!");
            }
        }
    }

    private void addStock() {
        System.out.print("Nom de l'item: ");
        String itemName = app.readLine();
        boolean itemFound = false;

        for (Item item : InventoryService.inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemFound = true;
                System.out.print("Quantité à ajouter: ");
                try {
                    int quantityToAdd = app.readInt();
                    item.addStock(quantityToAdd);
                    System.out.println("Stock ajouté!");
                } catch (Exception e) {
                    System.out.println("Quantité invalide.");
                    app.getScanner().nextLine();
                }
                break;
            }
        }

        if (!itemFound) System.out.println("Item non trouvé.");
    }

    private void removeStock() {
        System.out.print("Nom de l'item: ");
        String itemName = app.readLine();
        boolean itemFound = false;

        for (Item item : InventoryService.inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                itemFound = true;
                System.out.print("Quantité à retirer: ");
                try {
                    int quantityToRemove = app.readInt();
                    if (item.getStock() >= quantityToRemove) {
                        item.removeStock(quantityToRemove);
                        System.out.println("Stock retiré!");
                    } else {
                        System.out.println("Pas assez de stock!");
                    }
                } catch (Exception e) {
                    System.out.println("Quantité invalide.");
                    app.getScanner().nextLine();
                }
                break;
            }
        }

        if (!itemFound) System.out.println("Item non trouvé.");
    }

    private void addNewItem() {
        System.out.print("Nom: ");
        String itemName = app.readLine();

        double itemPrice = 0;
        int initialStock = 0;
        try {
            System.out.print("Prix: ");
            itemPrice = app.readDouble();
            System.out.print("Stock initial: ");
            initialStock = app.readInt();
        } catch (Exception e) {
            System.out.println("Prix ou stock invalide.");
            app.getScanner().nextLine();
            return;
        }

        System.out.print("Type (main/snack/drink): ");
        String itemType = app.readLine();

        if (itemType.equalsIgnoreCase("drink")) {
            System.out.print("Taille: ");
            String drinkSize = app.readLine();
            InventoryService.inventory.add(new Drink(itemName, itemPrice, initialStock, drinkSize));
        } else if (itemType.equalsIgnoreCase("snack")) {
            InventoryService.inventory.add(new Snack(itemName, itemPrice, initialStock));
        } else {
            InventoryService.inventory.add(new MainFood(itemName, itemPrice, initialStock));
        }

        System.out.println("Item ajouté!");
    }
}
