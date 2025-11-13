package ui;

import app.McDonaldSystem;
import service.*;
import java.util.Scanner;

public class ClientMode {
    private static final Scanner sc = McDonaldSystem.sc;

    public static void start() {
        System.out.print("Nom: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println("Bienvenue " + name);

        CartService.clear();
        boolean loop = true;

        while (loop) {
            System.out.println("\n1. Voir menu");
            System.out.println("2. Ajouter TRIO");
            System.out.println("3. Ajouter item");
            System.out.println("4. Voir panier");
            System.out.println("5. Retirer du panier");
            System.out.println("6. Passer commande");
            System.out.println("7. Retour");
            System.out.print("Choix: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> CartService.printMenu();
                case 2 -> CartService.addTrio();
                case 3 -> CartService.addItem();
                case 4 -> CartService.viewCart();
                case 5 -> CartService.removeItem();
                case 6 -> OrderService.placeOrder();
                case 7 -> loop = false;
                default -> System.out.println("Choix invalide!");
            }
        }
    }
}
