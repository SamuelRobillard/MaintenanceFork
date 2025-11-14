package app;

import service.*;
import ui.ClientMode;
import ui.InventoryMode;

import java.util.Scanner;

public class McDonaldSystem {

    private final Scanner scanner = new Scanner(System.in);

    // Services avec interfaces

    private static IInventoryService inventoryService;
    private static ICartService cartService ;
    private static IOrderService orderService;

    public Scanner getScanner() { return scanner; }

    public static void main(String[] args) {
        inventoryService = McDonaldServiceFactory.createInventoryService();
        cartService = McDonaldServiceFactory.createCartService();
        orderService = McDonaldServiceFactory.createOrderService(cartService);
        McDonaldSystem app = new McDonaldSystem();
        app.inventoryService.addDefaultItems();

        System.out.println("=== BIENVENUE CHEZ MCDONALD'S ===");

        boolean running = true;
        while (running) {
            app.showMainMenu();
            try {
                int choice = app.readInt();
                switch (choice) {
                    case 1 -> {
                        try {
                            new ClientMode(app, app.cartService, app.orderService, app.inventoryService).start();
                        } catch (Exception e) {
                            System.out.println("Erreur dans le mode client : " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        try {
                            new InventoryMode(app, app.inventoryService).start();
                        } catch (Exception e) {
                            System.out.println("Erreur dans le mode inventaire : " + e.getMessage());
                        }
                    }
                    case 3 -> running = false;
                    default -> System.out.println("Choix invalide !");
                }
            } catch (Exception e) {
                System.out.println("Entrée invalide, réessayez : " + e.getMessage());
            }
        }

        System.out.println("Au revoir !");
    }

    private void showMainMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Mode Client");
        System.out.println("2. Mode Inventaire");
        System.out.println("3. Quitter");
        System.out.print("Choix: ");
    }

    public int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide, veuillez entrer un chiffre: ");
            }
        }
    }

    public double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide, veuillez entrer un nombre: ");
            }
        }
    }

    public String readLine() {
        return scanner.nextLine();
    }

    // Accesseurs aux services
    public IInventoryService getInventoryService() { return inventoryService; }
    public ICartService getCartService() { return cartService; }
    public IOrderService getOrderService() { return orderService; }
}
