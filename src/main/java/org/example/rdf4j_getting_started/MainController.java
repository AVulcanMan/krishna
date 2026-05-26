package org.example.rdf4j_getting_started;

import java.util.Scanner;

public class MainController {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Alarm Clock");
            System.out.println("2. Directions");
            System.out.println("3. Task Manager");
            System.out.println("4. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {

                alarmClock.run(sc);

            } else if (choice == 2) {

                directions.run(sc);

            } else if (choice == 3) {

                TaskControl.run(sc);

            } else if (choice == 4) {

                System.out.println("Goodbye!");
                break;

            } else {

                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}