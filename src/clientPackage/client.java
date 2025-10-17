package clientPackage;

import objectPackage.Operation;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connecté au serveur de calculatrice (mode objet).");

            // Streams pour objet
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            String line;

            System.out.println("Entrez une opération (ex: 5 * 2) ou 'exit' pour quitter :");

            while (!(line = scanner.nextLine()).equalsIgnoreCase("exit")) {
                String[] parts = line.split(" ");

                if (parts.length == 3) {
                    try {
                        double op1 = Double.parseDouble(parts[0]);
                        String operator = parts[1];
                        double op2 = Double.parseDouble(parts[2]);

                        // Création de l'objet opération et envoi
                        Operation operation = new Operation(op1, operator, op2);
                        objectOutputStream.writeObject(operation);
                        objectOutputStream.flush();

                        // Lecture du résultat envoyé par le serveur
                        String result = (String) objectInputStream.readObject();
                        System.out.println("Serveur : " + result);

                    } catch (NumberFormatException e) {
                        System.out.println("Les opérandes doivent être des nombres.");
                    }
                } else {
                    System.out.println("Format incorrect. Utilisez : nombre opérateur nombre");
                }

                System.out.println("\nEntrez une nouvelle opération ou 'exit' pour quitter :");
            }

            System.out.println("Déconnexion.");
            socket.close();
            scanner.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}
