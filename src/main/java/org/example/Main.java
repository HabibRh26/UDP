package org.example;

import org.example.config.DbConnector;
import org.example.service.ParentService;

import java.util.Scanner;

public class Main extends DbConnector {
    public static void main(String[] args) {
        ParentService parentService = new ParentService();
        Scanner sc = new Scanner(System.in);
        System.out.println("Type G for operation mode");
        String input = sc.next().toLowerCase();
        while (!input.equals("e")) {
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Type v for view table, c for create, d for delete, u for update, e for exit ");
            input = sc.next().toLowerCase();

            switch (input) {
                case "c":
                    parentService.createParent();
                    break;
                case "d":
                    parentService.deleteParent();
                    break;
                case "u":
                    parentService.updateParent();
                    break;
                case "v":
                    parentService.displayCurrentTable();
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please enter c, d, u, or e.");
                    break;
            }

        }
    }
}