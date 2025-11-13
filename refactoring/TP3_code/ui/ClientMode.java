package ui;

import app.McDonaldSystem;
import model.Item;
import service.ICartService;
import service.IOrderService;
import service.IInventoryService;

import java.util.List;

public class ClientMode {

    private final McDonaldSystem app;
    private final ICartService cartService;
    private final IOrderService orderService;
    private final IInventoryService inventoryService;

    public ClientMode(McDonaldSystem app,
                      ICartService cartService,
                      IOrderService orderService,
                      IInventoryService inventoryService) {
        this.app = app;
        this.cartService = cartService;
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }

    public void start() {
        System.out.print("Nom: ");
        String name = app.readLine();
        System.out.println("Bienvenue " + name);

        cartService.clearCart();

        boolean loop = true;
        while (loop) {
            System.out.println("\n=== MENU CLIENT ===");
            System.out.println("1. Voir menu");
            System.out.println("2. Ajouter TRIO");
            System.out.println("3. Ajouter item");
            System.out.println("4. Voir panier");
            System.out.println("5. Retirer du panier");
            System.out.println("6. Passer commande");
            System.out.println("7. Retour");
            System.out.print("Choix: ");

            int choice = app.readInt();
            switch (choice) {
                case 1 -> showMenu();
                case 2 -> addTrio();
                case 3 -> addItem();
                case 4 -> cartService.viewCart();
                case 5 -> removeItem();
                case 6 -> orderService.placeOrder();
                case 7 -> loop = false;
                default -> System.out.println("Choix invalide !");
            }
        }
    }

    // -----------------------------
    // Affichage du menu complet
    // -----------------------------
    private void showMenu() {
        List<Item> allItems = inventoryService.getByType("main");
        allItems.addAll(inventoryService.getByType("snack"));
        allItems.addAll(inventoryService.getByType("drink"));

        if (allItems.isEmpty()) {
            System.out.println("Le menu est vide !");
            return;
        }

        System.out.println("\n=== MENU COMPLET ===");
        for (int i = 0; i < allItems.size(); i++) {
            Item item = allItems.get(i);
            System.out.printf("%d. %s (%s) - %.2f$\n", i + 1, item.getName(), item.getType(), item.getPrice());
        }
    }

    // -----------------------------
    // Ajouter un item individuel
    // -----------------------------
    private void addItem() {
        List<Item> allItems = inventoryService.getByType("main");
        allItems.addAll(inventoryService.getByType("snack"));
        allItems.addAll(inventoryService.getByType("drink"));

        if (allItems.isEmpty()) {
            System.out.println("Le menu est vide !");
            return;
        }

        while (true) {
            System.out.println("\n=== AJOUTER UN ITEM ===");
            for (int i = 0; i < allItems.size(); i++) {
                Item item = allItems.get(i);
                System.out.printf("%d. %s (%s) - %.2f$\n", i + 1, item.getName(), item.getType(), item.getPrice());
            }

            System.out.print("Numéro de l'item à ajouter: ");
            int index = app.readInt() - 1;

            if (index >= 0 && index < allItems.size()) {
                cartService.addItem(allItems.get(index));
                break; // sortie de la boucle
            } else {
                System.out.println("Choix invalide, réessayez !");
            }
        }
    }

    // -----------------------------
    // Ajouter un trio (main + snack + drink)
    // -----------------------------
    private void addTrio() {
        Item main, snack, drink;

        do {
            main = selectItem("main");
            snack = selectItem("snack");
            drink = selectItem("drink");

            if (main == null || snack == null || drink == null) {
                System.out.println("Choix invalide pour le trio, veuillez recommencer.");
            }
        } while (main == null || snack == null || drink == null);

        cartService.addTrio(main, snack, drink);
    }

    // -----------------------------
    // Sélection d’un item selon le type
    // -----------------------------
    private Item selectItem(String type) {
        List<Item> items = inventoryService.getByType(type);

        if (items.isEmpty()) {
            System.out.println("Aucun item disponible pour " + type);
            return null;
        }

        while (true) {
            System.out.println("\nSélectionnez " + type + ":");
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.printf("%d. %s - %.2f$\n", i + 1, item.getName(), item.getPrice());
            }

            System.out.print("Choix: ");
            int choice = app.readInt() - 1;

            if (choice >= 0 && choice < items.size()) return items.get(choice);
            System.out.println("Choix invalide, réessayez !");
        }
    }

    // -----------------------------
    // Supprimer un item du panier
    // -----------------------------
    private void removeItem() {
        cartService.viewCart();
        if (cartService.getCart().isEmpty()) return;

        while (true) {
            System.out.print("Numéro à retirer: ");
            int index = app.readInt() - 1;
            if (index >= 0 && index < cartService.getCart().size()) {
                cartService.removeItem(index);
                break;
            } else {
                System.out.println("Choix invalide, réessayez !");
            }
        }
    }
}
