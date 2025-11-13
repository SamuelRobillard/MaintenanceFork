package ui;

import app.McDonaldSystem;
import model.Item;
import service.IInventoryService;

public class InventoryMode {

    private final McDonaldSystem app;
    private final IInventoryService inventoryService;

    public InventoryMode(McDonaldSystem app, IInventoryService inventoryService) {
        this.app = app;
        this.inventoryService = inventoryService;
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

            int choice = app.readInt();
            switch (choice) {
                case 1 -> inventoryService.printInventory();
                case 2 -> addStock();
                case 3 -> removeStock();
                case 4 -> addNewItem();
                case 5 -> isRunning = false;
                default -> System.out.println("Choix invalide!");
            }
        }
    }

    private void addStock() {
        try {
            System.out.print("Nom de l'item: ");
            String name = app.readLine();

            var list = inventoryService.getAllItems(); // récupère tous les items
            Item found = list.stream()
                    .filter(i -> i.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(null);

            if (found == null) {
                System.out.println("Item introuvable !");
                return;
            }

            System.out.print("Quantité à ajouter: ");
            int qty = app.readInt();
            if (qty < 0) {
                System.out.println("Quantité invalide !");
                return;
            }

            inventoryService.addStock(found.getName(), qty);
            System.out.println("Stock ajouté !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du stock: " + e.getMessage());
        }
    }

    private void removeStock() {
        try {
            System.out.print("Nom de l'item: ");
            String name = app.readLine();

            var list = inventoryService.getAllItems(); // récupère tous les items
            Item found = list.stream()
                    .filter(i -> i.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(null);

            if (found == null) {
                System.out.println("Item introuvable !");
                return;
            }

            System.out.print("Quantité à retirer: ");
            int qty = app.readInt();
            if (qty < 0 || qty > found.getStock()) {
                System.out.println("Quantité invalide ou supérieure au stock actuel !");
                return;
            }

            inventoryService.removeStock(found.getName(), qty);
            System.out.println("Stock retiré !");
        } catch (Exception e) {
            System.out.println("Erreur lors du retrait du stock: " + e.getMessage());
        }
    }

    private void addNewItem() {
        try {
            System.out.print("Nom: ");
            String name = app.readLine();
            if (name.isBlank()) {
                System.out.println("Nom invalide !");
                return;
            }

            System.out.print("Prix: ");
            double price = app.readDouble();
            if (price < 0) {
                System.out.println("Prix invalide !");
                return;
            }

            System.out.print("Stock initial: ");
            int stock = app.readInt();
            if (stock < 0) {
                System.out.println("Stock invalide !");
                return;
            }

            System.out.print("Type (main/snack/drink): ");
            String type = app.readLine().toLowerCase();
            if (!type.equals("main") && !type.equals("snack") && !type.equals("drink")) {
                System.out.println("Type invalide !");
                return;
            }

            Item newItem;
            if (type.equals("drink")) {
                System.out.print("Taille: ");
                String size = app.readLine();
                newItem = new model.Drink(name, price, stock, size);
            } else if (type.equals("snack")) {
                newItem = new model.Snack(name, price, stock);
            } else {
                newItem = new model.MainFood(name, price, stock);
            }

            inventoryService.addItem(newItem);
            System.out.println("Item ajouté !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du nouvel item: " + e.getMessage());
        }
    }
}
