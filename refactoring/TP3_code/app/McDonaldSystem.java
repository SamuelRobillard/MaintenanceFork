package app;

import service.InventoryService;
import ui.ClientMode;
import ui.InventoryMode;

import java.util.Scanner;

public class McDonaldSystem {

    private final Scanner sc = new Scanner(System.in);

    public Scanner getScanner() { return sc; }

    public static void main(String[] args) {
        McDonaldSystem app = new McDonaldSystem();
        InventoryService.addDefaultItems();

        System.out.println("=== MCDONALDS ===");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Mode Client");
            System.out.println("2. Mode Inventaire");
            System.out.println("3. Quitter");
            System.out.print("Choix: ");

            int choice = app.readInt();
            switch (choice) {
                case 1 -> new ClientMode(app).start();
                case 2 -> new InventoryMode(app).start();
                case 3 -> running = false;
                default -> System.out.println("Choix invalide!");
            }
        }

        System.out.println("Au revoir!");
    }

    // Lecture sécurisée d'un entier
    public int readInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide, réessayez: ");
            }
        }
    }

    // Lecture sécurisée d'un double
    public double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrée invalide, réessayez: ");
            }
        }
    }

    public String readLine() {
        return sc.nextLine();
    }
}
