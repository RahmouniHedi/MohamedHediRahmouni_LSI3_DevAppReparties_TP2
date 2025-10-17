package clientPackage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            System.out.println("Connecté au serveur de calculatrice.");

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            Scanner scanner = new Scanner(System.in);
            String line;

            System.out.println("Entrez une opération (ex: 5 * 2) ou 'exit' pour quitter :");

            while (!(line = scanner.nextLine()).equalsIgnoreCase("exit")) {
                String[] parts = line.split(" ");

                if (parts.length == 3) {
                    writer.println(parts[0]);
                    writer.println(parts[1]);
                    writer.println(parts[2]);


                    String serverResponse = reader.readLine();
                    System.out.println("Serveur : " + serverResponse);
                } else {
                    System.out.println("Format d'opération incorrect. Veuillez utiliser : nombre opérateur nombre");
                }

                System.out.println("\nEntrez une nouvelle opération ou 'exit' pour quitter :");
            }

            System.out.println("Déconnexion.");
            socket.close();
            scanner.close();

        } catch (IOException e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}