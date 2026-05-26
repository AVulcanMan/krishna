package org.example.rdf4j_getting_started;

import java.util.Scanner;

public class TaskControl {

    public static void run(Scanner sc) {

        TaskManager manager = new TaskManager();

        while (true) {

            System.out.println("\n=== TASK MANAGER ===");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Remove Task");
            System.out.println("5. Exit");

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {

                System.out.print("Task name: ");
                String name = sc.nextLine();
                manager.addTask(name);

            } else if (choice == 2) {

                manager.viewTasks();

            } else if (choice == 3) {

                System.out.print("Task number: ");
                int num = sc.nextInt();
                manager.completeTask(num - 1);

            } else if (choice == 4) {

                System.out.print("Task number: ");
                int num = sc.nextInt();
                manager.removeTask(num - 1);

            } else if (choice == 5) {

                break;

            } else {

                System.out.println("Invalid choice.");
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        run(sc);
        sc.close();
    }
}