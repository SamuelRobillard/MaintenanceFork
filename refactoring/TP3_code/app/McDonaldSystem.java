package app;

import ui.ClientMode;
import ui.InventoryMode;
import service.InventoryService;
import java.util.Scanner;

public class McDonaldSystem {

    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        InventoryService.addDefaultItems();

        System.out.println("=== MCDONALDS ===");

        while (true) {
            System.out.println("\n1. Mode Client");
            System.out.println("2. Mode Inventaire");
            System.out.println("3. Quitter");
            System.out.print("Choix: ");

            int c = sc.nextInt();

            switch (c) {
                case 1 -> ClientMode.start();
                case 2 -> InventoryMode.start();
                case 3 -> {
                    System.out.println("Au revoir!");
                    return;
                }
                default -> System.out.println("Choix invalide!");
            }
        }
    }
}
