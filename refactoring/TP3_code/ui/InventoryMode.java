package ui;
import app.McDonaldSystem;
import service.*;
import model.*;
import java.util.Scanner;

public class InventoryMode {

    private static final Scanner sc = McDonaldSystem.sc;
    public static void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== INVENTAIRE ===");
            System.out.println("1. Afficher inventaire");
            System.out.println("2. Ajouter stock");
            System.out.println("3. Retirer stock");
            System.out.println("4. Ajouter nouvel item");
            System.out.println("5. Retour");
            System.out.print("Choix: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> showInventory();
                case 2 -> addStock();
                case 3 -> removeStock();
                case 4 -> addNewItem();
                case 5 -> running = false;
                default -> System.out.println("Choix invalide!");
            }
        }
    }

    private static void showInventory() {
        System.out.println("\n--- STOCK ACTUEL ---");
        for (Item it : InventoryService.inventory)
            System.out.printf("%s: %d unités (%.2f$)\n", it.name, it.stock, it.price);
    }

    private static void addStock() {
        sc.nextLine();
        System.out.print("Nom de l'item: ");
        String name = sc.nextLine();
        for (Item it : InventoryService.inventory) {
            if (it.name.equalsIgnoreCase(name)) {
                System.out.print("Quantité à ajouter: ");
                it.stock += sc.nextInt();
                System.out.println("Stock ajouté!");
                return;
            }
        }
        System.out.println("Item non trouvé.");
    }

    private static void removeStock() {
        sc.nextLine();
        System.out.print("Nom de l'item: ");
        String name = sc.nextLine();
        for (Item it : InventoryService.inventory) {
            if (it.name.equalsIgnoreCase(name)) {
                System.out.print("Quantité à retirer: ");
                int q = sc.nextInt();
                if (it.stock >= q) {
                    it.stock -= q;
                    System.out.println("Stock retiré!");
                } else System.out.println("Pas assez de stock!");
                return;
            }
        }
        System.out.println("Item non trouvé.");
    }

    private static void addNewItem() {
        sc.nextLine();
        System.out.print("Nom: ");
        String n = sc.nextLine();
        System.out.print("Prix: ");
        double p = sc.nextDouble();
        System.out.print("Stock initial: ");
        int s = sc.nextInt();
        System.out.print("Type (main/snack/drink): ");
        String t = sc.next();

        if (t.equals("drink")) {
            System.out.print("Taille: ");
            String size = sc.next();
            InventoryService.inventory.add(new Drink(n, p, s, size));
        } else if (t.equals("snack")) {
            InventoryService.inventory.add(new Snack(n, p, s));
        } else {
            InventoryService.inventory.add(new MainFood(n, p, s));
        }
        System.out.println("Item ajouté!");
    }
}
