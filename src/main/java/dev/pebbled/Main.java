package dev.pebbled;

import dev.pebbled.network.Connection;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter path to json file: ");
        String path = scanner.nextLine();

        Connection connection = new Connection(path);

        System.out.println("Loading data, please wait");

        connection.loadNodes();


        System.out.println("Type exit to quit\n-----------------\n");

        while (true) {

            System.out.println("Starting Name: ");

            String nameOne = scanner.nextLine();

            if (nameOne.equals("exit")) {
                break;
            }

            System.out.println("\nEnding Name: ");

            String nameTwo = scanner.nextLine();

            if (nameTwo.equals("exit")) {
                System.out.println("Exiting");
                break;
            }

            System.out.println("Finding closet connection between " + nameOne + " and " + nameTwo);

            System.out.println(connection.getShortestConnectionPath(nameOne, nameTwo));
        }
    }
}